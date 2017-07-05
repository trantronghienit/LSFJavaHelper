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

import org.allseen.lsf.sdk.model.ControllerDataModel;
import org.allseen.lsf.sdk.model.LightingItemDataModel;

/**
 * This class provides an interface to retrieve information about a given controller
 * in the Lighting system.
 */
public class Controller extends LightingItem {
    /**
     * Specifies the default name of the Controller.
     *
     * @param defaultName Default name of the Controller
     */
    public static void setDefaultName(String defaultName) {
        if (defaultName != null) {
            ControllerDataModel.defaultName = defaultName;
        }
    }
    protected ControllerDataModel controllerModel;

    /**
     * Constructs a Controller.
     * <p>
     * <b>WARNING: This method is intended to be used internally. Client software should not instantiate
     * the Controller directly, but should instead get it from the {@link LightingDirector} using the
     * {@link LightingDirector#getLeadController()} method.</b>
     */
    protected Controller() {
        controllerModel = new ControllerDataModel();
    }

    /**
     * Renames the lead controller in the Lighting system.
     * <p>
     * <b>WARNING: This method is not yet implemented and could change in future releases.</b>
     */
    @Override
    public void rename(String name) {
        // This method is not yet implemented
        postError(ResponseCode.ERR_FAILURE);
    }

    /**
     * Returns a boolean that indicates if it is the lead controller in the Lighting system.
     *
     * @return Return true if this is the lead controller, false otherwise
     */
    public boolean isConnected() {
        return getControllerDataModel().connected;
    }

    /**
     * Returns the version of the controller.
     *
     * @return Version of the controller.
     */
    public long getVersion() {
        return getControllerDataModel().version;
    }

    /**
     * <b>WARNING: This method is not intended to be used by clients, and may change or be
     * removed in subsequent releases of the SDK.</b>
     */
    protected ControllerDataModel getControllerDataModel() {
        return controllerModel;
    }

    /**
     * <b>WARNING: This method is not intended to be used by clients, and may change or be
     * removed in subsequent releases of the SDK.</b>
     */
    @Override
    protected LightingItemDataModel getItemDataModel() {
        return getControllerDataModel();
    }

    /**
     * <b>WARNING: This method is not intended to be used by clients, and may change or be
     * removed in subsequent releases of the SDK.</b>
     */
    @Override
    protected void postError(final String name, final ResponseCode status) {
        LightingDirector.get().getLightingSystemManager().getQueue().post(new Runnable() {
            @Override
            public void run() {
                LightingDirector.get().getControllerManager().sendErrorEvent(name, status);
            }
        });
    }
}