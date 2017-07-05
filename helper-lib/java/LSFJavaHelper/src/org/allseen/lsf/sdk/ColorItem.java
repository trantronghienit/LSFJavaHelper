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
package org.allseen.lsf.sdk;

import org.allseen.lsf.sdk.model.ColorItemDataModel;
import org.allseen.lsf.sdk.model.ColorStateConverter;
import org.allseen.lsf.sdk.model.LightingItemDataModel;
import org.allseen.lsf.LampState;

/**
 * Abstract base class for Lighting Items that support Color in the Lighting system.
 */
public abstract class ColorItem extends LightingItem {

    /**
     * Returns the HSVT color state of the Lighting item
     *
     * @return Integer array containing the HSVT color of the Lighting item
     */
    public int[] getColorHsvt() {
        return ColorStateConverter.convertModelToView(getColorDataModel().getState());
    }
    
    public long[] getColorDefault() {
    	LampState lampState = getColorDataModel().getState();
    	 long viewHue = lampState.getHue();
    	 long viewSaturation = lampState.getSaturation();
    	 long viewBrightness = lampState.getBrightness();
    	 long viewColorTemp = lampState.getColorTemp();
         return new long[]{viewHue, viewSaturation, viewBrightness, viewColorTemp};
    }

    /**
     * Returns the power state of the Lighting item.
     *
     * @return Returns true if the Lighting item power state is on, false otherwise
     */
    public boolean getPowerOn() {
        return getColorDataModel().getState().getOnOff();
    }

    /**
     * Returns the power state of the Lighting item.
     *
     * @return Returns true if the Lighting item power state is on, false otherwise
     */
    public boolean isOn() {
        return getPowerOn();
    }

    /**
     * Returns the power state of the Lighting item.
     *
     * @return Returns true if the Lighting item power state is off, false otherwise
     */
    public boolean isOff() {
        return !isOn();
    }

    /**
     * Returns the power state of the Lighting item.
     *
     * @return Returns ON if the Lighting item power state is on, OFF otherwise
     */
    public Power getPower() {
        return (getPowerOn())? Power.ON : Power.OFF;
    }

    /**
     * Returns the HSVT color state of the Lighting item
     *
     * @return Reference to Color object
     */
    public Color getColor() {
        return new Color(getColorHsvt());
    }

    /**
     * Returns the power and color state of the Lighting item
     *
     * @return Reference to MyLampState object
     */
    public MyLampState getState() {
        return new MyLampState(getPower(), getColor());
    }

    /**
     * Returns the uniformity information of the Lighting item.
     *
     * @return Reference to LampStateUniformity object
     */
    public LampStateUniformity getUniformity() {
        return new LampStateUniformity(getColorDataModel().uniformity);
    }

    /**
     * Returns the capabilities of the Lighting item.
     *
     * @return Reference to LampCapabilities object
     */
    public LampCapabilities getCapability() {
        return new LampCapabilities(getColorDataModel().getCapability());
    }

    /**
     * Sets the capabilities of the Lighting item.
     *
     * @param capability Reference to LampCapabilities object
     */
    public void setCapability(LampCapabilities capability) {
        getColorDataModel().setCapability(capability);
    }

    /**
     * <b>WARNING: This method is not intended to be used by clients, and may change or be
     * removed in subsequent releases of the SDK.</b>
     */
    @Override
    protected LightingItemDataModel getItemDataModel() {
        return getColorDataModel();
    }

    /**
     * <b>WARNING: This method is not intended to be used by clients, and may change or be
     * removed in subsequent releases of the SDK.</b>
     */
    protected abstract ColorItemDataModel getColorDataModel();
}