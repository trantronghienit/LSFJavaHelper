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
package org.allseen.lsf.sdk.manager;

import java.util.Iterator;

import org.allseen.lsf.ControllerClient;
import org.allseen.lsf.LampGroupManager;
import org.allseen.lsf.LampState;
import org.allseen.lsf.sdk.ControllerClientStatus;
import org.allseen.lsf.sdk.ResponseCode;
import org.allseen.lsf.sdk.callback.HelperGroupManagerCallback;
import org.allseen.lsf.sdk.model.AllLampsDataModel;
import org.allseen.lsf.sdk.model.AllLampsLampGroup;

/**
 * <b>WARNING: This class is not intended to be used by clients, and its interface may change
 * in subsequent releases of the SDK</b>.
 */
public class SampleGroupManager extends LampGroupManager {
    protected HelperGroupManagerCallback<?> callback;

    public SampleGroupManager(ControllerClient controller, HelperGroupManagerCallback<?> callback) {
        super(controller, callback);

        this.callback = callback;
    }

    @Override
    public ControllerClientStatus getLampGroupName(String groupID, String language) {
        ControllerClientStatus status = ControllerClientStatus.OK;

        if (AllLampsDataModel.ALL_LAMPS_GROUP_ID.equals(groupID)) {
            callback.getLampGroupNameReplyCB(ResponseCode.OK, AllLampsDataModel.ALL_LAMPS_GROUP_ID, language, AllLampsDataModel.ALL_LAMPS_GROUP_NAME);
        } else {
            status = super.getLampGroupName(groupID, language);
        }

        return status;
    }

    @Override
    public ControllerClientStatus getLampGroup(String groupID) {
        ControllerClientStatus status = ControllerClientStatus.OK;

        if (AllLampsDataModel.ALL_LAMPS_GROUP_ID.equals(groupID)) {
            callback.getLampGroupReplyCB(ResponseCode.OK, AllLampsDataModel.ALL_LAMPS_GROUP_ID, AllLampsLampGroup.instance);
        } else {
            status = super.getLampGroup(groupID);
        }

        return status;
    }

    @Override
    public ControllerClientStatus transitionLampGroupState(String groupID, LampState state, long duration) {
        ControllerClientStatus status = ControllerClientStatus.OK;

        if (interceptAllLampsGroupStateChangeRequests() && AllLampsDataModel.ALL_LAMPS_GROUP_ID.equals(groupID)) {
            Iterator<String> i = getLampIDs();

            while(status == ControllerClientStatus.OK && i.hasNext()) {
                status = AllJoynManager.lampManager.transitionLampState(i.next(), state, duration);
            }
        } else {
            status = super.transitionLampGroupState(groupID, state, duration);
        }

        return status;
    }

    @Override
    public ControllerClientStatus pulseLampGroupWithState(String groupID, LampState toState, long period, long duration, long count, LampState fromState) {
        ControllerClientStatus status = ControllerClientStatus.OK;

        if (interceptAllLampsGroupStateChangeRequests() && AllLampsDataModel.ALL_LAMPS_GROUP_ID.equals(groupID)) {
            Iterator<String> i = getLampIDs();

            while(status == ControllerClientStatus.OK && i.hasNext()) {
                status = AllJoynManager.lampManager.pulseLampWithState(i.next(), toState, period, duration, count, fromState);
            }
        } else {
            status = super.pulseLampGroupWithState(groupID, toState, period, duration, count, fromState);
        }

        return status;
    }

    @Override
    public ControllerClientStatus pulseLampGroupWithPreset(String groupID, String toPresetID, long period, long duration, long count, String fromPresetID) {
        ControllerClientStatus status = ControllerClientStatus.OK;

        if (interceptAllLampsGroupStateChangeRequests() && AllLampsDataModel.ALL_LAMPS_GROUP_ID.equals(groupID)) {
            Iterator<String> i = getLampIDs();

            while(status == ControllerClientStatus.OK && i.hasNext()) {
                status = AllJoynManager.lampManager.pulseLampWithPreset(i.next(), toPresetID, period, duration, count, fromPresetID);
            }
        } else {
            status = super.pulseLampGroupWithPreset(groupID, toPresetID, period, duration, count, fromPresetID);
        }

        return status;
    }

    @Override
    public ControllerClientStatus transitionLampGroupStateOnOffField(String groupID, boolean onOff) {
        ControllerClientStatus status = ControllerClientStatus.OK;

        if (interceptAllLampsGroupStateChangeRequests() && AllLampsDataModel.ALL_LAMPS_GROUP_ID.equals(groupID)) {
            Iterator<String> i = getLampIDs();

            while(status == ControllerClientStatus.OK && i.hasNext()) {
                status = AllJoynManager.lampManager.transitionLampStateOnOffField(i.next(), onOff);
            }
        } else {
            status = super.transitionLampGroupStateOnOffField(groupID, onOff);
        }

        return status;
    }

    @Override
    public ControllerClientStatus transitionLampGroupStateHueField(String groupID, long hue, long duration) {
        ControllerClientStatus status = ControllerClientStatus.OK;

        if (interceptAllLampsGroupStateChangeRequests() && AllLampsDataModel.ALL_LAMPS_GROUP_ID.equals(groupID)) {
            Iterator<String> i = getLampIDs();

            while(status == ControllerClientStatus.OK && i.hasNext()) {
                status = AllJoynManager.lampManager.transitionLampStateHueField(i.next(), hue, duration);
            }
        } else {
            status = super.transitionLampGroupStateHueField(groupID, hue, duration);
        }

        return status;
    }

    @Override
    public ControllerClientStatus transitionLampGroupStateSaturationField(String groupID, long saturation, long duration) {
        ControllerClientStatus status = ControllerClientStatus.OK;

        if (interceptAllLampsGroupStateChangeRequests() && AllLampsDataModel.ALL_LAMPS_GROUP_ID.equals(groupID)) {
            Iterator<String> i = getLampIDs();

            while(status == ControllerClientStatus.OK && i.hasNext()) {
                status = AllJoynManager.lampManager.transitionLampStateSaturationField(i.next(), saturation, duration);
            }
        } else {
            status = super.transitionLampGroupStateSaturationField(groupID, saturation, duration);
        }

        return status;
    }

    @Override
    public ControllerClientStatus transitionLampGroupStateBrightnessField(String groupID, long brightness, long duration) {
        ControllerClientStatus status = ControllerClientStatus.OK;

        if (interceptAllLampsGroupStateChangeRequests() && AllLampsDataModel.ALL_LAMPS_GROUP_ID.equals(groupID)) {
            Iterator<String> i = getLampIDs();

            while(status == ControllerClientStatus.OK && i.hasNext()) {
                status = AllJoynManager.lampManager.transitionLampStateBrightnessField(i.next(), brightness, duration);
            }
        } else {
            status = super.transitionLampGroupStateBrightnessField(groupID, brightness, duration);
        }

        return status;
    }

    @Override
    public ControllerClientStatus transitionLampGroupStateColorTempField(String groupID, long colorTemp, long duration) {
        ControllerClientStatus status = ControllerClientStatus.OK;

        if (interceptAllLampsGroupStateChangeRequests() && AllLampsDataModel.ALL_LAMPS_GROUP_ID.equals(groupID)) {
            Iterator<String> i = getLampIDs();

            while(status == ControllerClientStatus.OK && i.hasNext()) {
                status = AllJoynManager.lampManager.transitionLampStateColorTempField(i.next(), colorTemp, duration);
            }
        } else {
            status = super.transitionLampGroupStateColorTempField(groupID, colorTemp, duration);
        }

        return status;
    }

    @Override
    public ControllerClientStatus transitionLampGroupStateToPreset(String groupID, String presetID, long duration) {
        ControllerClientStatus status = ControllerClientStatus.OK;

        if (interceptAllLampsGroupStateChangeRequests() && AllLampsDataModel.ALL_LAMPS_GROUP_ID.equals(groupID)) {
            Iterator<String> i = getLampIDs();

            while(status == ControllerClientStatus.OK && i.hasNext()) {
                status = AllJoynManager.lampManager.transitionLampStateToPreset(i.next(), presetID, duration);
            }
        } else {
            status = super.transitionLampGroupStateToPreset(groupID, presetID, duration);
        }

        return status;
    }

    @Override
    public ControllerClientStatus resetLampGroupState(String groupID) {
        ControllerClientStatus status = ControllerClientStatus.OK;

        if (interceptAllLampsGroupStateChangeRequests() && AllLampsDataModel.ALL_LAMPS_GROUP_ID.equals(groupID)) {
            Iterator<String> i = getLampIDs();

            while(status == ControllerClientStatus.OK && i.hasNext()) {
                status = AllJoynManager.lampManager.resetLampState(i.next());
            }
        } else {
            status = super.resetLampGroupState(groupID);
        }

        return status;
    }

    @Override
    public ControllerClientStatus resetLampGroupStateOnOffField(String groupID) {
        ControllerClientStatus status = ControllerClientStatus.OK;

        if (interceptAllLampsGroupStateChangeRequests() && AllLampsDataModel.ALL_LAMPS_GROUP_ID.equals(groupID)) {
            Iterator<String> i = getLampIDs();

            while(status == ControllerClientStatus.OK && i.hasNext()) {
                status = AllJoynManager.lampManager.resetLampStateOnOffField(i.next());
            }
        } else {
            status = super.resetLampGroupStateOnOffField(groupID);
        }

        return status;
    }

    @Override
    public ControllerClientStatus resetLampGroupStateHueField(String groupID) {
        ControllerClientStatus status = ControllerClientStatus.OK;

        if (interceptAllLampsGroupStateChangeRequests() && AllLampsDataModel.ALL_LAMPS_GROUP_ID.equals(groupID)) {
            Iterator<String> i = getLampIDs();

            while(status == ControllerClientStatus.OK && i.hasNext()) {
                status = AllJoynManager.lampManager.resetLampStateHueField(i.next());
            }
        } else {
            status = super.resetLampGroupStateHueField(groupID);
        }

        return status;
    }

    @Override
    public ControllerClientStatus resetLampGroupStateSaturationField(String groupID) {
        ControllerClientStatus status = ControllerClientStatus.OK;

        if (interceptAllLampsGroupStateChangeRequests() && AllLampsDataModel.ALL_LAMPS_GROUP_ID.equals(groupID)) {
            Iterator<String> i = getLampIDs();

            while(status == ControllerClientStatus.OK && i.hasNext()) {
                status = AllJoynManager.lampManager.resetLampStateSaturationField(i.next());
            }
        } else {
            status = super.resetLampGroupStateSaturationField(groupID);
        }

        return status;
    }

    @Override
    public ControllerClientStatus resetLampGroupStateBrightnessField(String groupID) {
        ControllerClientStatus status = ControllerClientStatus.OK;

        if (interceptAllLampsGroupStateChangeRequests() && AllLampsDataModel.ALL_LAMPS_GROUP_ID.equals(groupID)) {
            Iterator<String> i = getLampIDs();

            while(status == ControllerClientStatus.OK && i.hasNext()) {
                status = AllJoynManager.lampManager.resetLampStateBrightnessField(i.next());
            }
        } else {
            status = super.resetLampGroupStateBrightnessField(groupID);
        }

        return status;
    }

    @Override
    public ControllerClientStatus resetLampGroupStateColorTempField(String groupID) {
        ControllerClientStatus status = ControllerClientStatus.OK;

        if (interceptAllLampsGroupStateChangeRequests() && AllLampsDataModel.ALL_LAMPS_GROUP_ID.equals(groupID)) {
            Iterator<String> i = getLampIDs();

            while(status == ControllerClientStatus.OK && i.hasNext()) {
                status = AllJoynManager.lampManager.resetLampStateColorTempField(i.next());
            }
        } else {
            status = super.resetLampGroupStateColorTempField(groupID);
        }

        return status;
    }

    protected Iterator<String> getLampIDs() {
        return callback.manager.getLampCollectionManager().getIDIterator();
    }

    protected boolean interceptAllLampsGroupStateChangeRequests() {
        return AllJoynManager.isControllerServiceLeaderV1();
    }
}