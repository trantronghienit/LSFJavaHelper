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

import org.allseen.lsf.sdk.ResponseCode;

public class PresetManagerCallback extends DefaultNativeClassWrapper {

    public PresetManagerCallback() {
        createNativeObject();
    }

    public void getPresetReplyCB(ResponseCode responseCode, String presetID, LampState preset)                          { }
    public void getAllPresetIDsReplyCB(ResponseCode responseCode, String[] presetIDs)                                   { }
    public void getPresetNameReplyCB(ResponseCode responseCode, String presetID, String language, String presetName)    { }
    public void setPresetNameReplyCB(ResponseCode responseCode, String presetID, String language)                       { }
    public void presetsNameChangedCB(String[] presetIDs)                                                                { }
    public void createPresetReplyCB(ResponseCode responseCode, String presetID)                                         { }
    public void presetsCreatedCB(String[] presetIDs)                                                                    { }
    public void updatePresetReplyCB(ResponseCode responseCode, String presetID)                                         { }
    public void presetsUpdatedCB(String[] presetIDs)                                                                    { }
    public void deletePresetReplyCB(ResponseCode responseCode, String presetID)                                         { }
    public void presetsDeletedCB(String[] presetIDs)                                                                    { }
    public void getDefaultLampStateReplyCB(ResponseCode responseCode, LampState defaultLampState)                       { }
    public void setDefaultLampStateReplyCB(ResponseCode responseCode)                                                   { }
    public void defaultLampStateChangedCB()                                                                             { }
    public void createPresetWithTrackingReplyCB(ResponseCode responseCode, String presetID, long trackingID)            { }

    @Override
    protected native void createNativeObject();

    @Override
    protected native void destroyNativeObject();
}