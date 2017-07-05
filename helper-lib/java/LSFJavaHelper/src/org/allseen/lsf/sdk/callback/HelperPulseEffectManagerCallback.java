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

import org.allseen.lsf.PulseEffectV2;
import org.allseen.lsf.PulseEffectManagerCallback;
import org.allseen.lsf.sdk.ResponseCode;
import org.allseen.lsf.sdk.TrackingID;
import org.allseen.lsf.sdk.manager.AllJoynManager;
import org.allseen.lsf.sdk.manager.LightingSystemManager;
import org.allseen.lsf.sdk.model.PulseEffectDataModelV2;

/**
 * <b>WARNING: This class is not intended to be used by clients, and its interface may change
 * in subsequent releases of the SDK</b>.
 */
public class HelperPulseEffectManagerCallback<PULSEEFFECT> extends PulseEffectManagerCallback {
    protected LightingSystemManager<?, ?, ?, ?, PULSEEFFECT, ?, ?, ?, ?, ?, ?> manager;
    protected Map<String, TrackingID> creationTrackingIDs;

    public HelperPulseEffectManagerCallback(LightingSystemManager<?, ?, ?, ?, PULSEEFFECT, ?, ?, ?, ?, ?, ?> manager) {
        super();

        this.manager = manager;
        this.creationTrackingIDs = new HashMap<String, TrackingID>();
    }

    @Override
    public void getPulseEffectReplyCB(ResponseCode responseCode, String pulseEffectID, PulseEffectV2 pulseEffect) {
        if (!responseCode.equals(ResponseCode.OK)){
            manager.getPulseEffectCollectionManager().sendErrorEvent("getPulseEffectReplyCB", responseCode, pulseEffectID);
        }

        postUpdatePulseEffect(pulseEffectID, pulseEffect);
    }

    @Override
    public void applyPulseEffectOnLampsReplyCB(ResponseCode responseCode, String pulseEffectID, String[] lampIDs) {
        if (!responseCode.equals(ResponseCode.OK)) {
            manager.getPulseEffectCollectionManager().sendErrorEvent("applyPulseEffectOnLampsReplyCB", responseCode, pulseEffectID);
        }
    }

    @Override
    public void applyPulseEffectOnLampGroupsReplyCB(ResponseCode responseCode, String pulseEffectID, String[] lampGroupIDs) {
        if (!responseCode.equals(ResponseCode.OK)) {
            manager.getPulseEffectCollectionManager().sendErrorEvent("applyPulseEffectOnLampGroupsReplyCB", responseCode, pulseEffectID);
        }
    }

    @Override
    public void getAllPulseEffectIDsReplyCB(ResponseCode responseCode, String[] pulseEffectIDs) {
        if (!responseCode.equals(ResponseCode.OK)) {
            manager.getPulseEffectCollectionManager().sendErrorEvent("getAllPulseEffectIDsReplyCB", responseCode, null);
        }

        for (final String pulseEffectID : pulseEffectIDs) {
            postProcessPulseEffectID(pulseEffectID);
        }
    }

    @Override
    public void getPulseEffectNameReplyCB(ResponseCode responseCode, String pulseEffectID, String language, String pulseEffectName) {
        if (!responseCode.equals(ResponseCode.OK)){
            manager.getPulseEffectCollectionManager().sendErrorEvent("getPulseEffectNameReplyCB", responseCode, pulseEffectID);
        }

        postUpdatePulseEffectName(pulseEffectID, pulseEffectName);
    }

    @Override
    public void setPulseEffectNameReplyCB(ResponseCode responseCode, String pulseEffectID, String language) {
        if (!responseCode.equals(ResponseCode.OK)) {
            manager.getPulseEffectCollectionManager().sendErrorEvent("setPulseEffectNameReplyCB", responseCode, pulseEffectID);
        }

        AllJoynManager.pulseEffectManager.getPulseEffectName(pulseEffectID, LightingSystemManager.LANGUAGE);
    }

    @Override
    public void pulseEffectsNameChangedCB(final String[] pulseEffectIDs) {
        manager.getQueue().post(new Runnable() {
            @Override
            public void run() {
                boolean containsNewIDs = false;

                for (final String pulseEffectID : pulseEffectIDs) {
                    if (manager.getPulseEffectCollectionManager().hasID(pulseEffectID)) {
                        AllJoynManager.pulseEffectManager.getPulseEffectName(pulseEffectID, LightingSystemManager.LANGUAGE);
                    } else {
                        containsNewIDs = true;
                    }
                }

                if (containsNewIDs) {
                    AllJoynManager.pulseEffectManager.getAllPulseEffectIDs();
                }
            }
        });
    }

    @Override
    public void createPulseEffectReplyCB(ResponseCode responseCode, String pulseEffectID, long trackingID) {
        if (!responseCode.equals(ResponseCode.OK)) {
            manager.getPulseEffectCollectionManager().sendErrorEvent("createPulseEffectReplyCB", responseCode, pulseEffectID, new TrackingID(trackingID));
        } else {
            creationTrackingIDs.put(pulseEffectID, new TrackingID(trackingID));
        }
    }

    @Override
    public void pulseEffectsCreatedCB(String[] pulseEffectIDs) {
        AllJoynManager.pulseEffectManager.getAllPulseEffectIDs();
    }

    @Override
    public void updatePulseEffectReplyCB(ResponseCode responseCode, String pulseEffectID) {
        if (!responseCode.equals(ResponseCode.OK)) {
            manager.getPulseEffectCollectionManager().sendErrorEvent("updatePulseEffectReplyCB", responseCode, pulseEffectID);
        }
    }

    @Override
    public void pulseEffectsUpdatedCB(String[] pulseEffectIDs) {
        for (String pulseEffectID : pulseEffectIDs) {
            AllJoynManager.pulseEffectManager.getPulseEffect(pulseEffectID);
        }
    }

    @Override
    public void deletePulseEffectReplyCB(ResponseCode responseCode, String pulseEffectID) {
        if (!responseCode.equals(ResponseCode.OK)) {
            manager.getPulseEffectCollectionManager().sendErrorEvent("deletePulseEffectReplyCB", responseCode, pulseEffectID);
        }
    }

    @Override
    public void pulseEffectsDeletedCB(String[] pulseEffectIDs) {
        postDeletePulseEffects(pulseEffectIDs);
    }

    protected void postProcessPulseEffectID(final String pulseffectID) {
        manager.getQueue().post(new Runnable() {
            @Override
            public void run() {
                if (!manager.getPulseEffectCollectionManager().hasID(pulseffectID)) {
                    postUpdatePulseEffectID(pulseffectID);
                    AllJoynManager.pulseEffectManager.getPulseEffectName(pulseffectID, LightingSystemManager.LANGUAGE);
                    AllJoynManager.pulseEffectManager.getPulseEffect(pulseffectID);
                }
            }
        });
    }

    protected void postUpdatePulseEffectID(final String pulseEffectID) {
        manager.getQueue().post(new Runnable() {
            @Override
            public void run() {
                if (!manager.getPulseEffectCollectionManager().hasID(pulseEffectID)) {
                    manager.getPulseEffectCollectionManager().addPulseEffect(pulseEffectID);
                }
            }
        });

        postSendPulseEffectChanged(pulseEffectID);
    }

    protected void postUpdatePulseEffectName(final String pulseEffectID, final String pulseEffectName) {
        manager.getQueue().post(new Runnable() {
            @Override
            public void run() {
                PulseEffectDataModelV2 pulseEffectModel = manager.getPulseEffectCollectionManager().getModel(pulseEffectID);

                if (pulseEffectModel != null) {
                    boolean wasInitialized = pulseEffectModel.isInitialized();
                    pulseEffectModel.setName(pulseEffectName);
                    if (wasInitialized != pulseEffectModel.isInitialized()) {
                        postSendPulseEffectInitialized(pulseEffectID);
                    }
                }
            }
        });

        postSendPulseEffectChanged(pulseEffectID);
    }

    protected void postUpdatePulseEffect(final String pulseEffectID, final PulseEffectV2 pulseEffect) {
        manager.getQueue().post(new Runnable() {
            @Override
            public void run() {
                PulseEffectDataModelV2 pulseEffectModel = manager.getPulseEffectCollectionManager().getModel(pulseEffectID);

                if (pulseEffectModel != null) {
                    boolean wasInitialized = pulseEffectModel.isInitialized();

                    pulseEffectModel.setState(pulseEffect.getFromLampState());
                    pulseEffectModel.setEndState(pulseEffect.getToLampState());
                    pulseEffectModel.setStartPresetID(pulseEffect.getFromPresetID());
                    pulseEffectModel.setEndPresetID(pulseEffect.getToPresetID());
                    pulseEffectModel.setDuration(pulseEffect.getPulseDuration());
                    pulseEffectModel.setPeriod(pulseEffect.getPulsePeriod());
                    pulseEffectModel.setCount(pulseEffect.getNumPulses());

                    if (wasInitialized != pulseEffectModel.isInitialized()) {
                        postSendPulseEffectInitialized(pulseEffectID);
                    }
                }

                postSendPulseEffectChanged(pulseEffectID);
            }
        });
    }

    protected void postDeletePulseEffects(final String[] pulseEffectIDs) {
        manager.getQueue().post(new Runnable() {
            @Override
            public void run() {
                for (String pulseEffectID : pulseEffectIDs) {
                    manager.getPulseEffectCollectionManager().removePulseEffect(pulseEffectID);
                }
            }
        });
    }

    protected void postSendPulseEffectChanged(final String pulseEffectID) {
        manager.getQueue().post(new Runnable() {
            @Override
            public void run() {
                manager.getPulseEffectCollectionManager().sendChangedEvent(pulseEffectID);
            }
        });
    }

    protected void postSendPulseEffectInitialized(final String pulseEffectID) {
        manager.getQueue().post(new Runnable() {
            @Override
            public void run() {
                manager.getPulseEffectCollectionManager().sendInitializedEvent(pulseEffectID, creationTrackingIDs.remove(pulseEffectID));
            }
        });
    }
}