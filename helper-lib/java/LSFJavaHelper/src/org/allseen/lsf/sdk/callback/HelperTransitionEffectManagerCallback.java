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

import org.allseen.lsf.TransitionEffectV2;
import org.allseen.lsf.TransitionEffectManagerCallback;
import org.allseen.lsf.sdk.ResponseCode;
import org.allseen.lsf.sdk.TrackingID;
import org.allseen.lsf.sdk.manager.AllJoynManager;
import org.allseen.lsf.sdk.manager.LightingSystemManager;
import org.allseen.lsf.sdk.model.TransitionEffectDataModelV2;

/**
 * <b>WARNING: This class is not intended to be used by clients, and its interface may change
 * in subsequent releases of the SDK</b>.
 */
public class HelperTransitionEffectManagerCallback<TRANSITIONEFFECT> extends TransitionEffectManagerCallback {
    protected LightingSystemManager<?, ?, ?, TRANSITIONEFFECT, ?, ?, ?, ?, ?, ?, ?> manager;
    protected Map<String, TrackingID> creationTrackingIDs;

    public HelperTransitionEffectManagerCallback(LightingSystemManager<?, ?, ?, TRANSITIONEFFECT, ?, ?, ?, ?, ?, ?, ?> manager) {
        super();

        this.manager = manager;
        this.creationTrackingIDs = new HashMap<String, TrackingID>();
    }

    @Override
    public void getTransitionEffectReplyCB(ResponseCode responseCode, String transitionEffectID, TransitionEffectV2 transitionEffect) {
        if (!responseCode.equals(ResponseCode.OK)) {
            manager.getTransitionEffectCollectionManager().sendErrorEvent("getTransitionEffectReplyCB", responseCode, transitionEffectID);
        }
        postUpdateTransitionEffect(transitionEffectID, transitionEffect);
    }

    @Override
    public void applyTransitionEffectOnLampsReplyCB(ResponseCode responseCode, String transitionEffectID, String[] lampIDs) {
        if (!responseCode.equals(ResponseCode.OK)) {
            manager.getTransitionEffectCollectionManager().sendErrorEvent("applyTransitionEffectOnLampsReplyCB", responseCode, transitionEffectID);
        }
    }

    @Override
    public void applyTransitionEffectOnLampGroupsReplyCB(ResponseCode responseCode, String transitionEffectID, String[] lampGroupIDs) {
        if (!responseCode.equals(ResponseCode.OK)) {
            manager.getTransitionEffectCollectionManager().sendErrorEvent("applyTransitionEffectOnLampGroupsReplyCB", responseCode, transitionEffectID);
        }
    }

    @Override
    public void getAllTransitionEffectIDsReplyCB(ResponseCode responseCode, String[] transitionEffectIDs) {
        if (!responseCode.equals(ResponseCode.OK)) {
            manager.getTransitionEffectCollectionManager().sendErrorEvent("getAllTransitionEffectIDsReplyCB", responseCode, null);
        }

        for (final String transitionEffectID : transitionEffectIDs) {
            postProcessTransitionEffectID(transitionEffectID);
        }
    }

    @Override
    public void getTransitionEffectNameReplyCB(ResponseCode responseCode, String transitionEffectID, String language, String transitionEffectName) {
        if (!responseCode.equals(ResponseCode.OK)){
            manager.getTransitionEffectCollectionManager().sendErrorEvent("getTransitionEffectNameReplyCB", responseCode, transitionEffectID);
        }

        postUpdateTransitionEffectName(transitionEffectID, transitionEffectName);
    }

    @Override
    public void setTransitionEffectNameReplyCB(ResponseCode responseCode, String transitionEffectID, String language) {
        if (!responseCode.equals(ResponseCode.OK)) {
            manager.getTransitionEffectCollectionManager().sendErrorEvent("setTransitionEffectNameReplyCB", responseCode, transitionEffectID);
        }

        AllJoynManager.transitionEffectManager.getTransitionEffectName(transitionEffectID, LightingSystemManager.LANGUAGE);
    }

    @Override
    public void transitionEffectsNameChangedCB(final String[] transitionEffectIDs) {
        manager.getQueue().post(new Runnable() {
            @Override
            public void run() {
                boolean containsNewIDs = false;

                for (final String transitionEffectID : transitionEffectIDs) {
                    if (manager.getTransitionEffectCollectionManager().hasID(transitionEffectID)) {
                        AllJoynManager.transitionEffectManager.getTransitionEffectName(transitionEffectID, LightingSystemManager.LANGUAGE);
                    } else {
                        containsNewIDs = true;
                    }
                }

                if (containsNewIDs) {
                    AllJoynManager.transitionEffectManager.getAllTransitionEffectIDs();
                }
            }
        });
    }

    @Override
    public void createTransitionEffectReplyCB(ResponseCode responseCode, String transitionEffectID, long trackingID) {
        if (!responseCode.equals(ResponseCode.OK)) {
            manager.getTransitionEffectCollectionManager().sendErrorEvent("createTransitionEffectReplyCB", responseCode, transitionEffectID, new TrackingID(trackingID));
        } else {
            creationTrackingIDs.put(transitionEffectID, new TrackingID(trackingID));
        }
    }

    @Override
    public void transitionEffectsCreatedCB(String[] transitionEffectIDs) {
        AllJoynManager.transitionEffectManager.getAllTransitionEffectIDs();
    }

    @Override
    public void updateTransitionEffectReplyCB(ResponseCode responseCode, String transitionEffectID) {
        if (!responseCode.equals(ResponseCode.OK)) {
            manager.getTransitionEffectCollectionManager().sendErrorEvent("updateTransitionEffectReplyCB", responseCode, transitionEffectID);
        }
    }

    @Override
    public void transitionEffectsUpdatedCB(String[] transitionEffectIDs) {
        for (String transitionEffectID : transitionEffectIDs) {
            AllJoynManager.transitionEffectManager.getTransitionEffect(transitionEffectID);
        }
    }

    @Override
    public void deleteTransitionEffectReplyCB(ResponseCode responseCode, String transitionEffectID) {
        if (!responseCode.equals(ResponseCode.OK)) {
            manager.getTransitionEffectCollectionManager().sendErrorEvent("deleteTransitionEffectReplyCB", responseCode, transitionEffectID);
        }
    }

    @Override
    public void transitionEffectsDeletedCB(String[] transitionEffectIDs) {
        postDeleteTransitionEffects(transitionEffectIDs);
    }

    protected void postProcessTransitionEffectID(final String transitionEffectID) {
        manager.getQueue().post(new Runnable() {
            @Override
            public void run() {
                if (!manager.getTransitionEffectCollectionManager().hasID(transitionEffectID)) {
                    postUpdateTransitionEffectID(transitionEffectID);
                    AllJoynManager.transitionEffectManager.getTransitionEffectName(transitionEffectID, LightingSystemManager.LANGUAGE);
                    AllJoynManager.transitionEffectManager.getTransitionEffect(transitionEffectID);
                }
            }
        });
    }

    protected void postUpdateTransitionEffectID(final String transitionEffectID) {
        manager.getQueue().post(new Runnable() {
            @Override
            public void run() {
                if (!manager.getTransitionEffectCollectionManager().hasID(transitionEffectID)) {
                    manager.getTransitionEffectCollectionManager().addTransitionEffect(transitionEffectID);
                }
            }
        });

        postSendTransitionEffectChanged(transitionEffectID);
    }

    protected void postUpdateTransitionEffectName(final String transitionEffectID, final String transitionEffectName) {
        manager.getQueue().post(new Runnable() {
            @Override
            public void run() {
                TransitionEffectDataModelV2 transitionEffectModel = manager.getTransitionEffectCollectionManager().getModel(transitionEffectID);

                if (transitionEffectModel != null) {
                    boolean wasInitialized = transitionEffectModel.isInitialized();
                    transitionEffectModel.setName(transitionEffectName);
                    if (wasInitialized != transitionEffectModel.isInitialized()) {
                        postSendTransitionEffectInitialized(transitionEffectID);
                    }
                }
            }
        });

        postSendTransitionEffectChanged(transitionEffectID);
    }

    protected void postUpdateTransitionEffect(final String transitionEffectID, final TransitionEffectV2 transitionEffect) {
        manager.getQueue().post(new Runnable() {
            @Override
            public void run() {
                TransitionEffectDataModelV2 transitionEffectModel = manager.getTransitionEffectCollectionManager().getModel(transitionEffectID);

                if (transitionEffectModel != null) {
                    boolean wasInitialized = transitionEffectModel.isInitialized();

                    transitionEffectModel.setState(transitionEffect.getLampState());
                    transitionEffectModel.setPresetID(transitionEffect.getPresetID());
                    transitionEffectModel.setDuration(transitionEffect.getTransitionPeriod());

                    if (wasInitialized != transitionEffectModel.isInitialized()) {
                        postSendTransitionEffectInitialized(transitionEffectID);
                    }
                }

                postSendTransitionEffectChanged(transitionEffectID);
            }
        });
    }

    protected void postDeleteTransitionEffects(final String[] transitionEffectIDs) {
        manager.getQueue().post(new Runnable() {
            @Override
            public void run() {
                for (String transitionEffectID : transitionEffectIDs) {
                    manager.getTransitionEffectCollectionManager().sendRemovedEvent(
                            manager.getTransitionEffectCollectionManager().removeTransitionEffect(transitionEffectID));
                }
            }
        });
    }

    protected void postSendTransitionEffectChanged(final String transitionEffectID) {
        manager.getQueue().post(new Runnable() {
            @Override
            public void run() {
                manager.getTransitionEffectCollectionManager().sendChangedEvent(transitionEffectID);
            }
        });
    }

    protected void postSendTransitionEffectInitialized(final String transitionEffectID) {
        manager.getQueue().post(new Runnable() {
            @Override
            public void run() {
                manager.getTransitionEffectCollectionManager().sendInitializedEvent(transitionEffectID, creationTrackingIDs.remove(transitionEffectID));
            }
        });
    }
}