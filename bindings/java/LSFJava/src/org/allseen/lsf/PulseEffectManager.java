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

public class PulseEffectManager extends BaseNativeClassWrapper {
    public static final int MAX_PULSE_EFFECTS = 100;

    public PulseEffectManager(ControllerClient controller, PulseEffectManagerCallback callback) {
        createNativeObject(controller, callback);
    }

    public native ControllerClientStatus getAllPulseEffectIDs();
    public native ControllerClientStatus getPulseEffect(String pulseEffectID);
    public native ControllerClientStatus applyPulseEffectOnLamps(String pulseEffectID, String[] lampIDs);
    public native ControllerClientStatus applyPulseEffectOnLampGroups(String pulseEffectID, String[] lampGroupIDs);
    public native ControllerClientStatus getPulseEffectName(String pulseEffectID, String language);
    public native ControllerClientStatus setPulseEffectName(String pulseEffectID, String pulseEffectName, String language);
    public native ControllerClientStatus createPulseEffect(TrackingID trackingID, PulseEffectV2 pulseEffect, String pulseEffectName, String language);
    public native ControllerClientStatus updatePulseEffect(String pulseEffectID, PulseEffectV2 pulseEffect);
    public native ControllerClientStatus deletePulseEffect(String pulseEffectID);
    public native ControllerClientStatus getPulseEffectDataSet(String pulseEffectID, String language);

    protected native void createNativeObject(ControllerClient controller, PulseEffectManagerCallback callback);

    @Override
    protected native void destroyNativeObject() ;
}