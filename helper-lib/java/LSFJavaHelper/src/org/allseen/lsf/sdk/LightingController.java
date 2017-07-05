/*
 *    Copyright (c) Open Connectivity Foundation (OCF), AllJoyn Open Source
 *    Project (AJOSP) Contributors and others.
 *    
 *    SPDX-License-Identifier: Apache-2.0
 *    
 *    All rights reserved. This program and the accompanying materials are
 *    made available under the terms of the Apache License, Version 2.0
 *    which accompanies this distribution, and is available at
 *    http://www.apache.org/licenses/LICENSE-2.0
 *    
 *    Copyright (c) Open Connectivity Foundation and Contributors to AllSeen
 *    Alliance. All rights reserved.
 *    
 *    Permission to use, copy, modify, and/or distribute this software for
 *    any purpose with or without fee is hereby granted, provided that the
 *    above copyright notice and this permission notice appear in all
 *    copies.
 *    
 *    THE SOFTWARE IS PROVIDED "AS IS" AND THE AUTHOR DISCLAIMS ALL
 *    WARRANTIES WITH REGARD TO THIS SOFTWARE INCLUDING ALL IMPLIED
 *    WARRANTIES OF MERCHANTABILITY AND FITNESS. IN NO EVENT SHALL THE
 *    AUTHOR BE LIABLE FOR ANY SPECIAL, DIRECT, INDIRECT, OR CONSEQUENTIAL
 *    DAMAGES OR ANY DAMAGES WHATSOEVER RESULTING FROM LOSS OF USE, DATA OR
 *    PROFITS, WHETHER IN AN ACTION OF CONTRACT, NEGLIGENCE OR OTHER
 *    TORTIOUS ACTION, ARISING OUT OF OR IN CONNECTION WITH THE USE OR
 *    PERFORMANCE OF THIS SOFTWARE.
*/
package org.allseen.lsf.sdk;

import org.allseen.lsf.sdk.model.BasicControllerService;

/**
 * The LightingController provides access to the bundled controller and connects to all
 * lamps on the network as well as the LightingDirector. The LightingController also stores
 * all group, preset, effect, and scene definitions.
 * <p>
 * <b>Note: The LightingController requires a WiFi or Ethernet network connection.</b>
 * <p>
 * Please see the LSFTutorial Project for an example of how to use the LightingController class.
 */
public class LightingController {

    private static final LightingController instance = new LightingController();

    private volatile boolean controllerRunning;
    private BasicControllerService controllerService;

    /*
     * Constructs a LightingController instance.
     *
     * Note that this is private since LightingController is a singleton. See
     * LightingController.get()
     */
    private LightingController() {
        System.out.println("Initializing LightingController singleton");

        controllerRunning = false;
        controllerService = null;
    }

    /**
     * Returns a LightingController instance.
     * <p>
     * <b>Note: The {@link #start()} method must be called at some point when you're ready
     * to begin working with the LightingController.</b>
     *
     * @return Reference to LightingController
     */
    public static LightingController get() {
        return LightingController.instance;
    }

    /**
     * Initializes the LightingController using the provided controller configuration.
     * <p>
     * <b>Note: This method call should follow the {@link #get()} method call and precede the {@link #start()} method call.</b>
     *
     * @param configuration The desired configuration for the LightingController
     *
     * @return OK if the controller was initialized correctly, ERROR_INIT otherwise
     */
    public LightingControllerStatus init(LightingControllerConfiguration configuration) {
        if (controllerRunning || configuration == null) {
            return LightingControllerStatus.ERROR_INIT;
        }

        controllerService = new BasicControllerService(configuration);
        return LightingControllerStatus.OK;
    }

    /**
     * Starts the bundled Lighting Controller. Once started, the controller will connect to
     * any lamps that are on the network.
     * <p>
     * <b>Note: This method should be called before interacting with the LightingController. Subsequent
     * calls to this method must each be preceded by a call to stop().</b>
     * <p>
     * <b>Note: You should ensure WiFi or some other network connection is available before
     * calling this method.</b>
     *
     * @return OK if LightingController successfully started
     */
    public LightingControllerStatus start() {

        if (controllerService != null && controllerService.getLightingControllerConfiguration() == null) {
            return LightingControllerStatus.ERROR_INIT;
        } else if (controllerRunning) {
            return LightingControllerStatus.ERROR_ALREADY_RUNNING;
        }

        controllerRunning = true;

        new Thread(new Runnable() {
            @Override
            public void run() {
                // Note: the start() call does not return until the controller service has stopped
                controllerService.start(controllerService.getLightingControllerConfiguration().getKeystorePath());
            }
        }).start();

        return LightingControllerStatus.OK;
    }

    /**
     * Stops the Lighting Controller. All group, preset, effect, and scene definitions will
     * be preserved for when the controller is started again.
     * <p>
     * <b>Note: This method must be preceded by a call to {@link start()}.</b>
     *
     * @return OK if the LightingController successfully stopped
     */
    public LightingControllerStatus stop() {
        controllerRunning = false;

        new Thread(new Runnable() {
            @Override
            public void run() {
                // Note: the stop() call does not return until the controller service has finished stopping
                controllerService.stop();
            }
        }).start();

        return LightingControllerStatus.OK;
    }

    /**
     * Causes the Lighting Controller to reset. All group, preset, effect, and scene
     * definitions will be preserved.
     *  <p>
     * <b>Note: This method must be preceded by a call to {@link start()}.</b>
     *
     * @return OK if the LightingController successfully reset
     */
    public LightingControllerStatus lightingReset() {
        stop();
        controllerService.lightingReset();
        return LightingControllerStatus.OK;
    }

    /**
     * Causes the Lighting Controller to factory reset. Factory reset will return the
     * controller to its default factory settings. All group, preset, effect, and scene
     * definitions will be deleted.
     * <p>
     * <b>Note: This method must be preceded by a call to {@link start()}.</b>
     *
     * @return OK if the LightingController successfully factory reset
     */
    public LightingControllerStatus factoryReset() {
        stop();
        controllerService.factoryReset();
        return LightingControllerStatus.OK;
    }

    /**
     * Returns a boolean representing whether the Controller is running.
     *
     * @return True if the LightingController is running, false otherwise
     */
    public boolean isRunning() {
        return controllerRunning;
    }

    /**
     * Returns the name of the Controller.
     *
     * @return the name of the controller
     */
    public String getName() {
        return isRunning() ? controllerService.getName() : null;
    }

    /**
     * Returns a boolean representing whether the Controller is the lead controller.
     *
     * @return True if the Controller is the leader, false otherwise
     */
    public boolean isLeader() {
        return controllerService.isLeader();
    }

    /**
     * Notifies the LightingController that a WiFi or Ethernet connection is available. Once
     * a WiFi or Ethernet connection is established, the controller will begin connecting to lamps on
     * the network.
     * <p>
     * <b>Note: This method must be preceded by a call to {@link start()} and should be called any time
     * there is a change to the WiFi or Ethernet connection.</b>
     */
    public void sendNetworkConnected() {
        if (controllerRunning) {
            controllerService.sendNetworkConnected();
        }
    }

    /**
     * Notifies the LightingController that the WiFi or Ethernet connection has been lost. When a network
     * connection is the lost the controller will enter a suspended state until a valid network
     * connection is reestablished.
     * <p>
     * <b>Note: This method must be preceded by a call to {@link start()} and should be called any time
     * there is a change to the WiFi or Ethernet connection.</b>
     */
    public void sendNetworkDisconnected() {
        if (controllerRunning) {
            controllerService.sendNetworkDisconnected();
        }
    }
}