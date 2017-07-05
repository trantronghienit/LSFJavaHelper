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

import org.allseen.lsf.sdk.LampDetails;
import org.allseen.lsf.sdk.LampParameters;
import org.allseen.lsf.sdk.ResponseCode;

public class LampManagerCallback extends DefaultNativeClassWrapper {
    private static final NativeLibraryLoader LIBS = NativeLibraryLoader.LIBS;

    public LampManagerCallback() {
        createNativeObject();
    }

    public void getAllLampIDsReplyCB(ResponseCode responseCode, String[] lampIDs)                                                           { }
    public void getLampNameReplyCB(ResponseCode responseCode, String lampID, String language, String lampName)                              { }
    public void getLampManufacturerReplyCB(ResponseCode responseCode, String lampID, String language, String manufacturer)                  { }
    public void setLampNameReplyCB(ResponseCode responseCode, String lampID, String language)                                               { }
    public void lampNameChangedCB(String lampID, String lampName)                                                                           { }
    public void lampsFoundCB(String[] lampIDs)                                                                                              { }
    public void lampsLostCB(String[] lampIDs)                                                                                               { }
    public void getLampDetailsReplyCB(ResponseCode responseCode, String lampID, LampDetails lampDetails)                                    { }
    public void getLampParametersReplyCB(ResponseCode responseCode, String lampID, LampParameters lampParameters)                           { }
    public void getLampParametersEnergyUsageMilliwattsFieldReplyCB(ResponseCode responseCode, String lampID, long energyUsageMilliwatts)    { }
    public void getLampParametersLumensFieldReplyCB(ResponseCode responseCode, String lampID, long brightnessLumens)                        { }
    public void getLampStateReplyCB(ResponseCode responseCode, String lampID, LampState lampState)                                          { }
    public void getLampStateOnOffFieldReplyCB(ResponseCode responseCode, String lampID, boolean onOff)                                      { }
    public void getLampStateHueFieldReplyCB(ResponseCode responseCode, String lampID, long hue)                                             { }
    public void getLampStateSaturationFieldReplyCB(ResponseCode responseCode, String lampID, long saturation)                               { }
    public void getLampStateBrightnessFieldReplyCB(ResponseCode responseCode, String lampID, long brightness)                               { }
    public void getLampStateColorTempFieldReplyCB(ResponseCode responseCode, String lampID, long colorTemp)                                 { }
    public void resetLampStateReplyCB(ResponseCode responseCode, String lampID)                                                             { }
    public void lampStateChangedCB(String lampID, LampState lampState)                                                                      { }
    public void transitionLampStateReplyCB(ResponseCode responseCode, String lampID)                                                        { }
    public void pulseLampWithStateReplyCB(ResponseCode responseCode, String lampID)                                                         { }
    public void pulseLampWithPresetReplyCB(ResponseCode responseCode, String lampID)                                                        { }
    public void transitionLampStateOnOffFieldReplyCB(ResponseCode responseCode, String lampID)                                              { }
    public void transitionLampStateHueFieldReplyCB(ResponseCode responseCode, String lampID)                                                { }
    public void transitionLampStateSaturationFieldReplyCB(ResponseCode responseCode, String lampID)                                         { }
    public void transitionLampStateBrightnessFieldReplyCB(ResponseCode responseCode, String lampID)                                         { }
    public void transitionLampStateColorTempFieldReplyCB(ResponseCode responseCode, String lampID)                                          { }
    public void getLampFaultsReplyCB(ResponseCode responseCode, String lampID, long[] faultCodes)                                           { }
    public void getLampServiceVersionReplyCB(ResponseCode responseCode, String lampID, long lampServiceVersion)                             { }
    public void clearLampFaultReplyCB(ResponseCode responseCode, String lampID, long faultCode)                                             { }
    public void resetLampStateOnOffFieldReplyCB(ResponseCode responseCode, String lampID)                                                   { }
    public void resetLampStateHueFieldReplyCB(ResponseCode responseCode, String lampID)                                                     { }
    public void resetLampStateSaturationFieldReplyCB(ResponseCode responseCode, String lampID)                                              { }
    public void resetLampStateBrightnessFieldReplyCB(ResponseCode responseCode, String lampID)                                              { }
    public void resetLampStateColorTempFieldReplyCB(ResponseCode responseCode, String lampID)                                               { }
    public void transitionLampStateToPresetReplyCB(ResponseCode responseCode, String lampID)                                                { }
    public void getLampSupportedLanguagesReplyCB(ResponseCode responseCode, String lampID, String[] supportedLanguages)                     { }
    public void getLampVersionReplyCB(ResponseCode responseCode, String lampID, long lampVersion)                                           { }
    public void setLampEffectReplyCB(ResponseCode responseCode, String lampID, String effectID)                                             { }
    public void getConsolidatedLampDataSetReplyCB(ResponseCode responseCode, String lampID, String language, String lampName, LampDetails lampDetails, LampState lampState, LampParameters lampParameters) { }

    @Override
    protected native void createNativeObject();

    @Override
    protected native void destroyNativeObject();
}
