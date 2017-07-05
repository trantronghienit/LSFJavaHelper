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
package org.allseen.lsf;

import org.allseen.lsf.sdk.ControllerClientStatus;
import org.allseen.lsf.sdk.TrackingID;

public class LampGroupManager extends BaseNativeClassWrapper {
    public static final int MAX_LAMP_GROUPS = 100;

    public LampGroupManager(ControllerClient controller, LampGroupManagerCallback callback) {
           createNativeObject(controller, callback);
    }

    public native ControllerClientStatus getAllLampGroupIDs();
    public native ControllerClientStatus getLampGroupName(String groupID, String language);
    public native ControllerClientStatus setLampGroupName(String groupID, String groupName, String language);
    public native ControllerClientStatus createLampGroup(LampGroup group, String groupName, String language);
    public native ControllerClientStatus updateLampGroup(String groupID, LampGroup group);
    public native ControllerClientStatus getLampGroup(String groupID);
    public native ControllerClientStatus deleteLampGroup(String groupID);
    public native ControllerClientStatus transitionLampGroupState(String groupID, LampState state, long duration);
    public native ControllerClientStatus pulseLampGroupWithState(String groupID, LampState toState, long period, long duration, long count, LampState fromState);
    public native ControllerClientStatus pulseLampGroupWithPreset(String groupID, String toPresetID, long period, long duration, long count, String fromPresetID);
    public native ControllerClientStatus transitionLampGroupStateOnOffField(String groupID, boolean onOff);
    public native ControllerClientStatus transitionLampGroupStateHueField(String groupID, long hue, long duration);
    public native ControllerClientStatus transitionLampGroupStateSaturationField(String groupID, long saturation, long duration);
    public native ControllerClientStatus transitionLampGroupStateBrightnessField(String groupID, long brightness, long duration);
    public native ControllerClientStatus transitionLampGroupStateColorTempField(String groupID, long colorTemp, long duration);
    public native ControllerClientStatus transitionLampGroupStateToPreset(String groupID, String presetID, long duration);
    public native ControllerClientStatus resetLampGroupState(String groupID);
    public native ControllerClientStatus resetLampGroupStateOnOffField(String groupID);
    public native ControllerClientStatus resetLampGroupStateHueField(String groupID);
    public native ControllerClientStatus resetLampGroupStateSaturationField(String groupID);
    public native ControllerClientStatus resetLampGroupStateBrightnessField(String groupID);
    public native ControllerClientStatus resetLampGroupStateColorTempField(String groupID);
    public native ControllerClientStatus createLampGroupWithTracking(TrackingID trackingID, LampGroup lampGroup, String lampGroupName, String language);
    public native ControllerClientStatus getLampGroupDataSet(String lampGroupID, String language);
    public native ControllerClientStatus setLampGroupEffect(String lampGroupID, String effectID);

    protected native void createNativeObject(ControllerClient controller, LampGroupManagerCallback callback);

    @Override
    protected native void destroyNativeObject();
}