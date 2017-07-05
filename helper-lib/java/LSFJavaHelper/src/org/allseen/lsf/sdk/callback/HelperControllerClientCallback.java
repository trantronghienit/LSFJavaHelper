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
package org.allseen.lsf.sdk.callback;

import org.allseen.lsf.ControllerClientCallback;
import org.allseen.lsf.sdk.ErrorCode;
import org.allseen.lsf.sdk.manager.AllJoynManager;
import org.allseen.lsf.sdk.manager.LightingSystemManager;
import org.allseen.lsf.sdk.model.ControllerDataModel;

/**
 * <b>WARNING: This class is not intended to be used by clients, and its interface may change
 * in subsequent releases of the SDK</b>.
 */
public class HelperControllerClientCallback<CONTROLLER> extends ControllerClientCallback {
    protected LightingSystemManager<?, ?, ?, ?, ?, ?, ?, ?, ?, CONTROLLER, ?> manager;

    public HelperControllerClientCallback(LightingSystemManager<?, ?, ?, ?, ?, ?, ?, ?, ?, CONTROLLER, ?> manager) {
        super();

        this.manager = manager;
    }

    @Override
    public void connectedToControllerServiceCB(String controllerServiceDeviceID, String controllerServiceName) {
        AllJoynManager.controllerConnected = true;
        AllJoynManager.controllerServiceManager.getControllerServiceVersion();

        manager.getLampManager().getAllLampIDs();

        postOnControllerConnected(controllerServiceDeviceID, controllerServiceName, 0);

        postGetAllLampGroupIDs();
        postGetAllPresetIDs();
        postGetAllTransitionEffectIDs();
        postGetAllPulseEffectIDs();
        postGetAllSceneElementIDs();
        postGetAllBasicSceneIDs();
        postGetAllMasterSceneIDs();
    }

    @Override
    public void connectToControllerServiceFailedCB(String controllerServiceDeviceID, String controllerServiceName) {
        AllJoynManager.controllerConnected = false;
        postOnControllerDisconnected(controllerServiceDeviceID, controllerServiceName, 0);
    }

    @Override
    public void disconnectedFromControllerServiceCB(String controllerServiceDeviceID, String controllerServiceName) {
        AllJoynManager.controllerConnected = false;
        postOnControllerDisconnected(controllerServiceDeviceID, controllerServiceName, 0);
    }

    @Override
    public void controllerClientErrorCB(ErrorCode[] errorCodes) {
        manager.getControllerCollectionManager().sendErrorEvent("controllerClientErrorCB", errorCodes);
    }

    public void postOnControllerConnected(final String controllerID, final String controllerName, int delay) {
        manager.getQueue().postDelayed(new Runnable() {
            @Override
            public void run() {
                ControllerDataModel leaderModel = manager.getControllerCollectionManager().getLeaderModel();

                if (!leaderModel.equalsID(controllerID) || !leaderModel.connected) {
                    leaderModel.id = controllerID;
                    leaderModel.setName(controllerName);
                    leaderModel.connected = true;
                    leaderModel.updateTime();

                    postSendControllerChanged();
                }
            }
        }, delay);
    }

    public void postOnControllerDisconnected(final String controllerID, final String controllerName, int delay) {
        manager.getQueue().postDelayed(new Runnable() {
            @Override
            public void run() {
                AllJoynManager.controllerServiceLeaderVersion = 0;

                ControllerDataModel leaderModel = manager.getControllerCollectionManager().getLeaderModel();

                if (!leaderModel.equalsID(controllerID) || leaderModel.connected) {
                    leaderModel.setName(controllerName);
                    leaderModel.version = 0;
                    leaderModel.connected = false;
                    leaderModel.updateTime();

                    postSendControllerChanged();
                }
            }
        }, delay);
    }

    public void postOnControllerAnnouncedAboutData(final String controllerID, final String controllerName, int delay) {
        manager.getQueue().postDelayed(new Runnable() {
            @Override
            public void run() {
                ControllerDataModel leaderModel = manager.getControllerCollectionManager().getLeaderModel();

                if (leaderModel.equalsID(controllerID)) {
                    leaderModel.setName(controllerName);
                    leaderModel.updateTime();

                    postSendControllerChanged();
                }
            }
        }, delay);
    }

    protected void postGetAllLampGroupIDs() {
        manager.getQueue().postDelayed(new Runnable() {
            @Override
            public void run() {
                AllJoynManager.groupManager.getAllLampGroupIDs();
            }
        }, 100);
    }

    protected void postGetAllPresetIDs() {
        manager.getQueue().postDelayed(new Runnable() {
            @Override
            public void run() {
                AllJoynManager.presetManager.getAllPresetIDs();
            }
        }, 200);
    }

    protected void postGetAllTransitionEffectIDs() {
        manager.getQueue().postDelayed(new Runnable() {
            @Override
            public void run() {
                AllJoynManager.transitionEffectManager.getAllTransitionEffectIDs();
            }
        }, 200);
    }

    protected void postGetAllPulseEffectIDs() {
        manager.getQueue().postDelayed(new Runnable() {
            @Override
            public void run() {
                AllJoynManager.pulseEffectManager.getAllPulseEffectIDs();
            }
        }, 200);
    }

    protected void postGetAllSceneElementIDs() {
        manager.getQueue().postDelayed(new Runnable() {
            @Override
            public void run() {
                AllJoynManager.sceneElementManager.getAllSceneElementIDs();
            }
        }, 200);
    }

    // After the call to getAllSceneIDs(),  we will receive either SceneV1
    // or SceneWithSceneElements items (depending on the version of the
    // controller that we are connected to). The registered scene callback
    // must be able to handle both cases.
    protected void postGetAllBasicSceneIDs() {
        manager.getQueue().postDelayed(new Runnable() {
            @Override
            public void run() {
                // Only get the scenes after we know the version of the controller
                if (AllJoynManager.controllerServiceLeaderVersion > 0) {
                    AllJoynManager.sceneManager.getAllSceneIDs();
                } else {
                    manager.getQueue().postDelayed(this, 300);
                }
            }
        }, 300);
    }

    protected void postGetAllMasterSceneIDs() {
        manager.getQueue().postDelayed(new Runnable() {
            @Override
            public void run() {
                AllJoynManager.masterSceneManager.getAllMasterSceneIDs();
            }
        }, 400);
    }

    protected void postSendControllerChanged() {
        // if connection status is ever changed, then prompt for updating the loading information
        manager.getQueue().post(new Runnable() {
            @Override
            public void run() {
                manager.getControllerCollectionManager().sendLeaderStateChangeEvent();
            }
        });
    }
}