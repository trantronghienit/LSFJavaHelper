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

import java.util.HashMap;
import java.util.Map;

import org.alljoyn.bus.Variant;
import org.allseen.lsf.LampManagerCallback;
import org.allseen.lsf.LampState;
import org.allseen.lsf.sdk.LampDetails;
import org.allseen.lsf.sdk.LampParameters;
import org.allseen.lsf.sdk.ResponseCode;
import org.allseen.lsf.sdk.manager.AllJoynManager;
import org.allseen.lsf.sdk.manager.LampCollectionManager;
import org.allseen.lsf.sdk.manager.LightingSystemManager;
import org.allseen.lsf.sdk.model.LampAbout;
import org.allseen.lsf.sdk.model.LampDataModel;

/**
 * <b>WARNING: This class is not intended to be used by clients, and its interface may change
 * in subsequent releases of the SDK</b>.
 */
public class HelperLampManagerCallback<LAMP> extends LampManagerCallback {
    private static final int RETRY_DELAY = 1000;
    private static final int ABOUT_DELAY = 250;

    private long prevLampAboutQueryTime;
    protected LightingSystemManager<LAMP, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?> manager;
    protected Map<String, LampAbout> savedLampAbouts;

    public HelperLampManagerCallback(LightingSystemManager<LAMP, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?> manager) {
        super();

        this.manager = manager;
        this.savedLampAbouts = new HashMap<String, LampAbout>();
        this.prevLampAboutQueryTime = 0;
    }

    @Override
    public void getAllLampIDsReplyCB(ResponseCode responseCode, String[] lampIDs) {
        if (!responseCode.equals(ResponseCode.OK)) {
            manager.getLampCollectionManager().sendErrorEvent("getAllLampIDsReplyCB", responseCode);
        }

        // Process lamp IDs regardless of response code
        for (String lampID : lampIDs) {
            postUpdateLampID(lampID, 0);
        }
    }

    @Override
    public void getLampNameReplyCB(ResponseCode responseCode, String lampID, String language, String lampName) {
        if (responseCode.equals(ResponseCode.OK)) {
            postUpdateLampName(lampID, lampName);
        } else {
            postGetLampName(lampID, RETRY_DELAY);
            manager.getLampCollectionManager().sendErrorEvent("getLampNameReplyCB", responseCode, lampID);
        }
    }

    @Override
    public void setLampNameReplyCB(ResponseCode responseCode, String lampID, String language) {
        if (!responseCode.equals(ResponseCode.OK)) {
            manager.getLampCollectionManager().sendErrorEvent("setLampNameReplyCB", responseCode, lampID);
        }

        // Read back name regardless of response code
        postGetLampName(lampID, 0);
    }

    @Override
    public void lampNameChangedCB(String lampID, String lampName) {
        postUpdateLampName(lampID, lampName);
    }

    @Override
    public void lampsFoundCB(String[] lampIDs) {
        for (String lampID : lampIDs) {
            postUpdateLampID(lampID, 0);
        }
    }

    @Override
    public void lampsLostCB(String[] lampIDs) {
        for (String lampID : lampIDs) {
            postRemoveLampID(lampID);
        }
    }

    @Override
    public void getLampDetailsReplyCB(ResponseCode responseCode, String lampID, LampDetails lampDetails) {
        if (responseCode.equals(ResponseCode.OK)) {
            postUpdateLampDetails(lampID, lampDetails);
        } else {
            postGetLampDetails(lampID, RETRY_DELAY);
            manager.getLampCollectionManager().sendErrorEvent("getLampDetailsReplyCB", responseCode, lampID);
        }
    }

    @Override
    public void getLampParametersReplyCB(ResponseCode responseCode, String lampID, LampParameters lampParameters) {
        if (responseCode.equals(ResponseCode.OK)) {
            postUpdateLampParameters(lampID, lampParameters);
        } else {
            postGetLampParameters(lampID, RETRY_DELAY);
            manager.getLampCollectionManager().sendErrorEvent("getLampParametersReplyCB", responseCode, lampID);
        }
    }

    @Override
    public void getLampStateReplyCB(ResponseCode responseCode, String lampID, LampState lampState) {
        if (responseCode.equals(ResponseCode.OK)) {
            postUpdateLampState(lampID, lampState);
            postGetLampParameters(lampID, 0);
        } else {
            postGetLampState(lampID, RETRY_DELAY);
            manager.getLampCollectionManager().sendErrorEvent("getLampStateReplyCB", responseCode, lampID);
        }
    }

    @Override
    public void getLampStateOnOffFieldReplyCB(ResponseCode responseCode, String lampID, boolean onOff) {
        if (responseCode.equals(ResponseCode.OK)) {
            postUpdateLampStateOnOff(lampID, onOff);
            postGetLampParameters(lampID, 0);
        } else {
            postGetLampStateOnOffField(lampID, RETRY_DELAY);
            manager.getLampCollectionManager().sendErrorEvent("getLampStateOnOffFieldReplyCB", responseCode, lampID);
        }
    }

    @Override
    public void getLampStateHueFieldReplyCB(ResponseCode responseCode, String lampID, long hue) {
        if (responseCode.equals(ResponseCode.OK)) {
            postUpdateLampStateHue(lampID, hue);
        } else {
            postGetLampStateHueField(lampID, RETRY_DELAY);
            manager.getLampCollectionManager().sendErrorEvent("getLampStateHueFieldReplyCB", responseCode, lampID);
        }
    }

    @Override
    public void getLampStateSaturationFieldReplyCB(ResponseCode responseCode, String lampID, long saturation) {
        if (responseCode.equals(ResponseCode.OK)) {
            postUpdateLampStateSaturation(lampID, saturation);
        } else {
            postGetLampStateSaturationField(lampID, RETRY_DELAY);
            manager.getLampCollectionManager().sendErrorEvent("getLampStateSaturationFieldReplyCB", responseCode, lampID);
        }
    }

    @Override
    public void getLampStateBrightnessFieldReplyCB(ResponseCode responseCode, String lampID, long brightness) {
        if (responseCode.equals(ResponseCode.OK)) {
            postUpdateLampStateBrightness(lampID, brightness);
            postGetLampParameters(lampID, 0);
        } else {
            postGetLampStateBrightnessField(lampID, RETRY_DELAY);
            manager.getLampCollectionManager().sendErrorEvent("getLampStateBrightnessFieldReplyCB", responseCode, lampID);
        }
    }

    @Override
    public void getLampStateColorTempFieldReplyCB(ResponseCode responseCode, String lampID, long colorTemp) {
        if (responseCode.equals(ResponseCode.OK)) {
            postUpdateLampStateColorTemp(lampID, colorTemp);
        } else {
            postGetLampStateColorTempField(lampID, RETRY_DELAY);
            manager.getLampCollectionManager().sendErrorEvent("getLampStateColorTempFieldReplyCB", responseCode, lampID);
        }
    }

    @Override
    public void lampStateChangedCB(String lampID, LampState lampState) {
        postUpdateLampState(lampID, lampState);
        postGetLampParameters(lampID, 0);
    }

    @Override
    public void transitionLampStateOnOffFieldReplyCB(ResponseCode responseCode, String lampID) {
        if (!responseCode.equals(ResponseCode.OK)) {
            postGetLampStateOnOffField(lampID, 0);
            manager.getLampCollectionManager().sendErrorEvent("transitionLampStateOnOffFieldReplyCB", responseCode, lampID);
        }
    }

    @Override
    public void transitionLampStateHueFieldReplyCB(ResponseCode responseCode, String lampID) {
        if (!responseCode.equals(ResponseCode.OK)) {
            postGetLampStateHueField(lampID, 0);
            manager.getLampCollectionManager().sendErrorEvent("transitionLampStateHueFieldReplyCB", responseCode, lampID);
        }
    }

    @Override
    public void transitionLampStateSaturationFieldReplyCB(ResponseCode responseCode, String lampID) {
        if (!responseCode.equals(ResponseCode.OK)) {
            postGetLampStateSaturationField(lampID, 0);
            manager.getLampCollectionManager().sendErrorEvent("transitionLampStateSaturationFieldReplyCB", responseCode, lampID);
        }
    }

    @Override
    public void transitionLampStateBrightnessFieldReplyCB(ResponseCode responseCode, String lampID) {
        if (!responseCode.equals(ResponseCode.OK)) {
            postGetLampStateBrightnessField(lampID, 0);
            manager.getLampCollectionManager().sendErrorEvent("transitionLampStateBrightnessFieldReplyCB", responseCode, lampID);
        }
    }

    @Override
    public void transitionLampStateColorTempFieldReplyCB(ResponseCode responseCode, String lampID) {
        if (!responseCode.equals(ResponseCode.OK)) {
            postGetLampStateColorTempField(lampID, 0);
            manager.getLampCollectionManager().sendErrorEvent("transitionLampStateColorTempFieldReplyCB", responseCode, lampID);
        }
    }

    @Override
    public void getConsolidatedLampDataSetReplyCB(ResponseCode responseCode, String lampID, String language, String lampName, LampDetails lampDetails, LampState lampState, LampParameters lampParameters) {
        if (responseCode.equals(ResponseCode.OK)) {
            postUpdateLampName(lampID, lampName);
            postUpdateLampDetails(lampID, lampDetails);
            postUpdateLampState(lampID, lampState);
            postUpdateLampParameters(lampID, lampParameters);
        } else {
            postGetConsolidatedLampDataSet(lampID, RETRY_DELAY);
            manager.getLampCollectionManager().sendErrorEvent("getConsolidatedLampDataSetReplyCB", responseCode, lampID);
        }
    }

    public void postOnLampAnnouncedAboutData(final String lampID, final String peer, final short port, final Map<String, Variant> announcedData, int delay) {
        manager.getQueue().postDelayed(new Runnable() {
            @Override
            public void run() {
                LampDataModel lampModel = manager.getLampCollectionManager().getModel(lampID);

                if (lampModel != null) {
                    //Log.d("AboutManager", "updating lamp model: " + lampID);
                    LampAbout lampAbout = lampModel.getAbout();

                    lampAbout.setAnnouncedData(peer, port, announcedData);

                    postGetLampName(lampID, 0);
                    postGetLampQueriedAboutData(lampID, lampAbout);
                } else {
                    //Log.d("AboutManager", "caching about data: " + lampID);
                    LampAbout lampAbout = new LampAbout();
                    lampAbout.setAnnouncedData(peer, port, announcedData);

                    savedLampAbouts.put(lampID, lampAbout);
                }
            }
        }, delay);
    }

    public void postOnLampQueriedAboutData(final String lampID, final Map<String, Variant> queriedData, int delay) {
        manager.getQueue().postDelayed(new Runnable() {
            @Override
            public void run() {
                LampDataModel lampModel = manager.getLampCollectionManager().getModel(lampID);

                if (lampModel != null) {
                    lampModel.getAbout().setQueriedData(queriedData);
                }
            }
        }, delay);

        //TODO: we may want to distinguish when lamp details change vs. when other lamp values change
        postSendLampChanged(lampID);
    }

    protected void postUpdateLampID(final String lampID, int delay) {
        manager.getQueue().postDelayed(new Runnable() {
            @Override
            public void run() {
                LampCollectionManager<LAMP, ?> lampManager = manager.getLampCollectionManager();

                if (!lampManager.hasID(lampID)) {
                    lampManager.addLamp(lampID);
                }

                LampDataModel lampModel = lampManager.getModel(lampID);

                if (LampDataModel.defaultName.equals(lampModel.getName())) {
                    if (AllJoynManager.isControllerServiceLeaderV1()) {
                        // Since 14.12 and earlier controllers did not support the
                        // consolidated data set, we have to queue up 4 requests and
                        // process the 4 replies.
                        postGetLampName(lampID, 0);
                        postGetLampState(lampID, 0);
                        postGetLampParameters(lampID, 0);
                        postGetLampDetails(lampID, 0);
                    } else {
                        // For 15.04 controllers and later we can send just 1 request
                        // and process the 1 reply.
                        postGetConsolidatedLampDataSet(lampID, 0);
                    }
                }

                LampAbout savedLampAbout = savedLampAbouts.remove(lampID);
                //Log.d("AboutManager", "retrieving cached about data: " + lampID);

                if (savedLampAbout != null) {
                    //Log.d("AboutManager", "updating about data: " + lampID);
                    lampModel.setAbout(savedLampAbout);
                    postGetLampQueriedAboutData(lampID, savedLampAbout);
                }

                // update the timestamp
                lampModel.updateTime();
            }
        }, delay);

        postSendLampChanged(lampID);
    }

    public void postRemoveLampID(final String lampID) {
        manager.getQueue().post(new Runnable() {
            @Override
            public void run() {
                manager.getLampCollectionManager().removeLamp(lampID);
            }
        });
    }

    protected void postGetLampName(final String lampID, int delay) {
        manager.getQueue().postDelayed(new Runnable() {
            @Override
            public void run() {
                if (AllJoynManager.controllerConnected) {
                    manager.getLampManager().getLampName(lampID, LightingSystemManager.LANGUAGE);
                }
            }
        }, delay);
    }

    protected void postUpdateLampState(final String lampID, final LampState lampState) {
        manager.getQueue().post(new Runnable() {
            @Override
            public void run() {
                LampDataModel lampModel = manager.getLampCollectionManager().getModel(lampID);

                if (lampModel != null) {
                    boolean wasInitialized = lampModel.isInitialized();
                    lampModel.setState(lampState);
                    if (wasInitialized != lampModel.isInitialized()) {
                        postSendLampInitialized(lampID);
                    }
                }
            }
        });

        postSendLampChanged(lampID);
    }

    protected void postGetLampState(final String lampID, int delay) {
        manager.getQueue().postDelayed(new Runnable() {
            @Override
            public void run() {
                if (AllJoynManager.controllerConnected) {
                    manager.getLampManager().getLampState(lampID);
                }
            }
        }, delay);
    }

    protected void postUpdateLampParameters(final String lampID, final LampParameters lampParams) {
        manager.getQueue().post(new Runnable() {
            @Override
            public void run() {
                LampDataModel lampModel = manager.getLampCollectionManager().getModel(lampID);

                if (lampModel != null) {
                    lampModel.setParameters(lampParams);
                }
            }
        });

        postSendLampChanged(lampID);
    }

    protected void postGetLampParameters(final String lampID, int delay) {
        manager.getQueue().postDelayed(new Runnable() {
            @Override
            public void run() {
                if (AllJoynManager.controllerConnected) {
                    manager.getLampManager().getLampParameters(lampID);
                }
            }
        }, delay);
    }

    protected void postUpdateLampDetails(final String lampID, final LampDetails lampDetails) {
        manager.getQueue().post(new Runnable() {
            @Override
            public void run() {
                LampDataModel lampModel = manager.getLampCollectionManager().getModel(lampID);

                if (lampModel != null) {
                    boolean wasInitialized = lampModel.isInitialized();
                    lampModel.setDetails(lampDetails);
                    if (wasInitialized != lampModel.isInitialized()) {
                        postSendLampInitialized(lampID);
                    }
                }
            }
        });

        postSendLampChanged(lampID);
    }

    protected void postGetLampDetails(final String lampID, final int delay) {
        manager.getQueue().postDelayed(new Runnable() {
            @Override
            public void run() {
                if (AllJoynManager.controllerConnected) {
                    manager.getLampManager().getLampDetails(lampID);
                }
            }
        }, delay);
    }

    protected void postUpdateLampName(final String lampID, final String lampName) {
        manager.getQueue().post(new Runnable() {
            @Override
            public void run() {
                LampDataModel lampModel = manager.getLampCollectionManager().getModel(lampID);

                if (lampModel != null) {
                    boolean wasInitialized = lampModel.isInitialized();
                    lampModel.setName(lampName);
                    if (wasInitialized != lampModel.isInitialized()) {
                        postSendLampInitialized(lampID);
                    }
                }
            }
        });

        postSendLampChanged(lampID);
    }

    protected void postUpdateLampStateOnOff(final String lampID, final boolean onOff) {
        manager.getQueue().post(new Runnable() {
            @Override
            public void run() {
                LampDataModel lampModel = manager.getLampCollectionManager().getModel(lampID);

                if (lampModel != null) {
                    lampModel.getState().setOnOff(onOff);
                }
            }
        });

        postSendLampChanged(lampID);
    }

    protected void postGetLampStateOnOffField(final String lampID, int delay) {
        manager.getQueue().postDelayed(new Runnable() {
            @Override
            public void run() {
                if (AllJoynManager.controllerConnected) {
                    manager.getLampManager().getLampStateOnOffField(lampID);
                }
            }
        }, delay);
    }

    protected void postUpdateLampStateHue(final String lampID, final long hue) {
        manager.getQueue().post(new Runnable() {
            @Override
            public void run() {
                LampDataModel lampModel = manager.getLampCollectionManager().getModel(lampID);

                if (lampModel != null) {
                    lampModel.getState().setHue(hue);
                }
            }
        });

        postSendLampChanged(lampID);
    }

    protected void postGetLampStateHueField(final String lampID, int delay) {
        manager.getQueue().postDelayed(new Runnable() {
            @Override
            public void run() {
                if (AllJoynManager.controllerConnected) {
                    manager.getLampManager().getLampStateHueField(lampID);
                }
            }
        }, delay);
    }

    protected void postUpdateLampStateSaturation(final String lampID, final long saturation) {
        manager.getQueue().post(new Runnable() {
            @Override
            public void run() {
                LampDataModel lampModel = manager.getLampCollectionManager().getModel(lampID);

                if (lampModel != null) {
                    lampModel.getState().setSaturation(saturation);
                }
            }
        });

        postSendLampChanged(lampID);
    }

    protected void postGetLampStateSaturationField(final String lampID, int delay) {
        manager.getQueue().postDelayed(new Runnable() {
            @Override
            public void run() {
                if (AllJoynManager.controllerConnected) {
                    manager.getLampManager().getLampStateSaturationField(lampID);
                }
            }
        }, delay);
    }

    protected void postUpdateLampStateBrightness(final String lampID, final long brightness) {
        manager.getQueue().post(new Runnable() {
            @Override
            public void run() {
                LampDataModel lampModel = manager.getLampCollectionManager().getModel(lampID);

                if (lampModel != null) {
                    lampModel.getState().setBrightness(brightness);
                }
            }
        });

        postSendLampChanged(lampID);
    }

    protected void postGetLampStateBrightnessField(final String lampID, int delay) {
        manager.getQueue().postDelayed(new Runnable() {
            @Override
            public void run() {
                if (AllJoynManager.controllerConnected) {
                    manager.getLampManager().getLampStateBrightnessField(lampID);
                }
            }
        }, delay);
    }

    protected void postUpdateLampStateColorTemp(final String lampID, final long colorTemp) {
        manager.getQueue().post(new Runnable() {
            @Override
            public void run() {
                LampDataModel lampModel = manager.getLampCollectionManager().getModel(lampID);

                if (lampModel != null) {
                    lampModel.getState().setColorTemp(colorTemp);
                }
            }
        });

        postSendLampChanged(lampID);
    }

    protected void postGetLampStateColorTempField(final String lampID, int delay) {
        manager.getQueue().postDelayed(new Runnable() {
            @Override
            public void run() {
                if (AllJoynManager.controllerConnected) {
                    manager.getLampManager().getLampStateColorTempField(lampID);
                }
            }
        }, delay);
    }

    protected void postGetConsolidatedLampDataSet(final String lampID, int delay) {
        manager.getQueue().postDelayed(new Runnable() {
            @Override
            public void run() {
                if (AllJoynManager.controllerConnected) {
                    manager.getLampManager().getConsolidatedLampDataSet(lampID, LightingSystemManager.LANGUAGE);
                }
            }
        }, delay);
    }

    protected void postGetLampQueriedAboutData(final String lampID, final LampAbout lampAbout) {
        long currentTime = System.currentTimeMillis();
        long targetQueryTime = Math.max(prevLampAboutQueryTime, currentTime) + ABOUT_DELAY;
        long delay = targetQueryTime - currentTime;

        manager.getHelperQueue().postDelayed(new Runnable() {
            @Override
            public void run() {
                AllJoynManager.aboutManager.getLampQueriedAboutData(lampID, lampAbout.aboutPeer, lampAbout.aboutPort);
            }
        }, (int)delay);

        prevLampAboutQueryTime = targetQueryTime;
    }

    protected void postSendLampChanged(final String lampID) {
        manager.getQueue().post(new Runnable() {
            @Override
            public void run() {
                manager.getLampCollectionManager().sendChangedEvent(lampID);
            }
        });
    }

    protected void postSendLampInitialized(final String lampID) {
        manager.getQueue().post(new Runnable() {
            @Override
            public void run() {
                manager.getLampCollectionManager().sendInitializedEvent(lampID);
            }
        });
    }
}