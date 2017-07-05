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
public class ColorItemDataModel extends LightingItemDataModel {
    public static final char TAG_PREFIX_UNKNOWN = 'X';

    public LampState state;
    public LampStateUniformity uniformity;

    protected LampCapabilities capability;
    protected boolean stateInitialized;

    public ColorItemDataModel() {
        this(null, TAG_PREFIX_UNKNOWN, "");
    }

    public ColorItemDataModel(String itemID, char prefix, String itemName) {
        super(itemID, prefix, itemName);

        state = new LampState();
        uniformity = new LampStateUniformity();

        capability = new LampCapabilities();

        state.setOnOff(false);
        state.setBrightness(0);
        state.setHue(0);
        state.setSaturation(0);
        state.setColorTemp(0);

        stateInitialized = false;
    }

    public ColorItemDataModel(ColorItemDataModel other) {
        super(other);

        this.state = new LampState(other.state);
        this.uniformity = new LampStateUniformity(other.uniformity);
        this.capability = other.getCapability();
        this.stateInitialized = other.stateInitialized;
    }

    public boolean stateEquals(ColorItemDataModel that) {
        return that != null ? stateEquals(that.state) : false;
    }

    public boolean stateEquals(LampState that) {
        return that != null ? stateEquals(that.getOnOff(), that.getHue(), that.getSaturation(), that.getBrightness(), that.getColorTemp()) : false;
    }

    public boolean stateEquals(boolean onOff, long hue, long saturation, long brightness, long colorTemp) {
        return
            this.state.getHue()         == hue          &&
            this.state.getSaturation()  == saturation   &&
            this.state.getBrightness()  == brightness   &&
            this.state.getColorTemp()   == colorTemp    &&
            this.state.getOnOff()       == onOff;
    }

    public void setCapability(LampCapabilities capability) {
        this.capability = capability;
    }

    public LampCapabilities getCapability() {
        return capability;
    }

    public LampState getState() {
        return state;
    }

    public void setState(LampState state) {
        this.state = state;
        stateInitialized = true;
    }

    @Override
    public boolean isInitialized() {
        return super.isInitialized() && stateInitialized;
    }
}