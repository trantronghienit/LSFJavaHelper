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

import org.allseen.lsf.LampState;

/**
 * <b>WARNING: This class is not intended to be used by clients, and its interface may change
 * in subsequent releases of the SDK</b>.
 */
public class PulseEffectDataModelV2 extends ColorItemDataModel {
    public static final char TAG_PREFIX_PULSE = 'E';

    public static String defaultName = "<Loading pulse effect info...>";
    public static long defaultPeriod = 1000;
    public static long defaultDuration = 500;
    public static long defaultCount = 10;

    private LampState endState;
    private String startPresetID;
    private String endPresetID;
    private long period;
    private long duration;
    private long count;
    private boolean startWithCurrent;

    protected boolean endStateInitialized;
    protected boolean periodInitialized;
    protected boolean durationInitialized;
    protected boolean countInitialized;

    public PulseEffectDataModelV2() {
        this((String)null);
    }

    public PulseEffectDataModelV2(String pulseEffectID) {
        this(pulseEffectID, null);
    }

    public PulseEffectDataModelV2(String pulseEffectID, String pulseEffectName) {
        super(pulseEffectID, TAG_PREFIX_PULSE, pulseEffectName != null ? pulseEffectName : defaultName);

        endState = null;
        startPresetID = null;
        endPresetID = null;
        period = defaultPeriod;
        duration = defaultDuration;
        count = defaultCount;
        startWithCurrent = false;

        endStateInitialized = false;
        periodInitialized = false;
        durationInitialized = false;
        countInitialized = false;
    }

    public PulseEffectDataModelV2(PulseEffectDataModelV2 other) {
        super(other);

        setCapability(LampCapabilities.allCapabilities);

        LampState otherEndState = other.getEndState();
        if (otherEndState != null) {
            endState = new LampState(otherEndState);
        } else {
            endState = null;
        }

        startPresetID = other.startPresetID;
        endPresetID = other.endPresetID;
        period = other.period;
        duration = other.duration;
        count = other.count;
        startWithCurrent = other.startWithCurrent;

        endStateInitialized = other.endStateInitialized;
        periodInitialized = other.periodInitialized;
        durationInitialized = other.durationInitialized;
        countInitialized = other.countInitialized;
    }

    public LampState getEndState() {
        return endState;
    }

    public void setEndState(LampState state) {
        endState = state;
        endStateInitialized = true;
    }

    public String getStartPresetID() {
        return startPresetID;
    }

    public void setStartPresetID(String presetID) {
        startPresetID = presetID;
        // start preset/state share same flag since only one field should be set
        stateInitialized = true;
    }

    public String getEndPresetID() {
        return endPresetID;
    }

    public void setEndPresetID(String endPresetID) {
        this.endPresetID = endPresetID;
        // end preset/state share same flag since only one field should be set
        endStateInitialized = true;
    }

    public long getPeriod() {
        return period;
    }

    public void setPeriod(long period) {
        this.period = period;
        periodInitialized = true;
    }

    public long getDuration() {
        return duration;
    }

    public void setDuration(long duration) {
        this.duration = duration;
        durationInitialized = true;
    }

    public long getCount() {
        return count;
    }

    public void setCount(long count) {
        this.count = count;
        countInitialized = true;
    }

    public boolean isStartWithCurrent() {
        return startWithCurrent;
    }

    public void setStartWithCurrent(boolean startWithCurrent) {
        this.startWithCurrent = startWithCurrent;
    }

    public boolean containsPreset(String presetID) {
        return presetID.equals(startPresetID) || presetID.equals(endPresetID);
    }

    @Override
    public boolean isInitialized() {
        return super.isInitialized() && stateInitialized && endStateInitialized &&
                periodInitialized && durationInitialized && countInitialized;
    }
}