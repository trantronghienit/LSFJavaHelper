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
package org.allseen.lsf.sdk.model;

/**
 * <b>WARNING: This class is not intended to be used by clients, and its interface may change
 * in subsequent releases of the SDK</b>.
 */
public class TransitionEffectDataModelV2 extends ColorItemDataModel {
    public static final char TAG_PREFIX_TRANSITION = 'E';

    public static String defaultName = "<Loading transition effect info...>";
    public static long defaultDuration = 5000;

    protected boolean durationInitialized;

    private String presetID;
    private long duration;

    public TransitionEffectDataModelV2() {
        this(null);
    }

    public TransitionEffectDataModelV2(String transitionEffectID) {
        this(transitionEffectID, null);
    }

    public TransitionEffectDataModelV2(String transitionEffectID, String transitionEffectName) {
        super(transitionEffectID, TAG_PREFIX_TRANSITION, transitionEffectName != null ? transitionEffectName : defaultName);

        setCapability(LampCapabilities.allCapabilities);

        duration = defaultDuration;
        presetID = null;

        durationInitialized = false;
    }

    public String getPresetID() {
        return presetID;
    }

    public void setPresetID(String transtionEffectPresetID) {
        presetID = transtionEffectPresetID;
        // preset/state share same flag since only one field should be set
        stateInitialized = true;
    }

    public long getDuration() {
        return duration;
    }

    public void setDuration(long transitionEffectDuration) {
        duration = transitionEffectDuration;
        durationInitialized = true;
    }

    public boolean containsPreset(String presetID) {
        return presetID.equals(this.presetID);
    }

    @Override
    public boolean isInitialized() {
        return super.isInitialized() && stateInitialized && durationInitialized;
    }
}