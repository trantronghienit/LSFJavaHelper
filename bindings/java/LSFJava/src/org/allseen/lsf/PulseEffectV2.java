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

public class PulseEffectV2 extends DefaultNativeClassWrapper {
    public PulseEffectV2() {
        createNativeObject();
    }

    public PulseEffectV2(PulseEffectV2 other) {
        this();

        this.setToLampState(other.getToLampState());
        this.setFromLampState(other.getFromLampState());
        this.setToPresetID(other.getToPresetID());
        this.setFromPresetID(other.getFromPresetID());
        this.setPulsePeriod(other.getPulsePeriod());
        this.setPulseDuration(other.getPulseDuration());
        this.setNumPulses(this.getNumPulses());
    }

    public native void setToLampState(LampState lampState);
    public native LampState getToLampState();

    public native void setFromLampState(LampState lampState);
    public native LampState getFromLampState();

    public native void setToPresetID(String presetID);
    public native String getToPresetID();

    public native void setFromPresetID(String presetID);
    public native String getFromPresetID();

    public native void setPulsePeriod(long pulsePeriod);
    public native long getPulsePeriod();

    public native void setPulseDuration(long pulseDuration);
    public native long getPulseDuration();

    public native void setNumPulses(long numPulses);
    public native long getNumPulses();

    @Override
    protected native void createNativeObject();

    @Override
    protected native void destroyNativeObject();
}