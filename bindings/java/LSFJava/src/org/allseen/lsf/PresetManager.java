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

public class PresetManager extends BaseNativeClassWrapper {
    public static final int MAX_PRESETS = 100;

    public PresetManager(ControllerClient controller, PresetManagerCallback callback) {
         createNativeObject(controller, callback);
    }

    public native ControllerClientStatus getAllPresetIDs();
    public native ControllerClientStatus getPreset(String presetID);
    public native ControllerClientStatus getPresetName(String presetID, String language);
    public native ControllerClientStatus setPresetName(String presetID, String presetName, String language);
    public native ControllerClientStatus createPreset(LampState preset, String presetName, String language);
    public native ControllerClientStatus updatePreset(String presetID, LampState preset);
    public native ControllerClientStatus deletePreset(String presetID);
    public native ControllerClientStatus getDefaultLampState();
    public native ControllerClientStatus setDefaultLampState(LampState defaultLampState);
    public native ControllerClientStatus createPresetWithTracking(TrackingID trackingID, LampState preset, String presetName, String language);
    public native ControllerClientStatus getPresetDataSet(String presetID, String language);

    protected native void createNativeObject(ControllerClient controller, PresetManagerCallback callback);

    @Override
    protected native void destroyNativeObject();
}