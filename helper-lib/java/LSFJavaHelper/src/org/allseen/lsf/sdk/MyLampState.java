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

import org.allseen.lsf.sdk.model.ColorStateConverter;

/**
 * Default implementation of the LampState interface and represents the power and
 * color state of a Lighting item.
 */
public class MyLampState implements LampState {

    private static final Color DEFAULT_COLOR = Color.white();
    private static final Power DEFAULT_POWER = Power.ON;

    private Power power;
    private Color color;

    /**
     * Constructs a MyLampState object.
     *
     * @param lampState An existing LampState.
     */
    public MyLampState(org.allseen.lsf.LampState lampState) {
        this(
                lampState.getOnOff() ? Power.ON : Power.OFF,
                        ColorStateConverter.convertHueModelToView(lampState.getHue()),
                        ColorStateConverter.convertSaturationModelToView(lampState.getHue()),
                        ColorStateConverter.convertBrightnessModelToView(lampState.getHue()),
                        ColorStateConverter.convertColorTempModelToView(lampState.getHue()));
    }

    /**
     * Constructs a MyLampState object.
     * <p>
     * <b>Note: If the provided HSVT values are outside the expected range, they will be normalized to the
     * expected range</b>
     *
     * @param lampPower The Power state.
     * @param hue The hue component of the Color (0-360)
     * @param sat The saturation component of a Color (0-100)
     * @param brightness The brightness component of a Color (0-100)
     * @param colorTemp The color temperature component of a Color (1000-20000)
     */
    public MyLampState(Power lampPower, int hue, int sat, int brightness, int colorTemp) {
        this(lampPower, new Color(hue, sat, brightness, colorTemp));
    }

    /**
     * Contructs a MyLampState object.
     * <p>
     * <b>Note: The lamp state power will be on and the color will be white if the constructor
     * parameters are null.</b>
     *
     * @param lampPower The Power state
     * @param colorState The Color state
     */
    public MyLampState(Power lampPower, Color colorState) {
        power = (lampPower != null)? lampPower : DEFAULT_POWER;
        color = (colorState != null)? colorState : DEFAULT_COLOR;
    }

    /**
     * Constructs a MyLampState object using the provided MyLampState object.
     *
     * @param other The other MyLampState object
     */
    public MyLampState(MyLampState other) {
        this((other == null)? null : other.power,
                (other == null)? null : new Color(other.color));
    }

    /**
     * Returns the power state.
     *
     * @return Returns on if the power is on, off otherwise
     */
    public Power getPower() {
        return power;
    }

    /**
     * Returns the color state.
     *
     * @return Reference to Color object
     */
    public Color getColor() {
        return color;
    }

    /**
     * Returns the power state.
     *
     * @return Returns true if the Power state is on, false otherwise
     */
    @Override
    public boolean getPowerOn() {
        return power == Power.ON;
    }

    /**
     * Sets the power state.
     *
     * @param powerOn Boolean that represents the power state
     */
    @Override
    public void setPowerOn(boolean powerOn) {
        power = (powerOn)? Power.ON : Power.OFF;
    }

    /**
     * Returns the power state.
     *
     * @return Returns true if the power state is on, false otherwise
     */
    public boolean isOn() {
        return getPowerOn();
    }

    /**
     * Returns the power state.
     *
     * @return Returns true if the power state is off, false otherwise
     */
    public boolean isOff() {
        return !isOn();
    }

    /**
     * Returns the HSVT color state of the Lighting item
     *
     * @return Integer array containing the HSVT color of the Lighting item
     */
    @Override
    public int[] getColorHsvt() {
        return new int[] { color.getHue(), color.getSaturation(), color.getBrightness(), color.getColorTemperature() };
    }

    /**
     * Sets the color state.
     *
     * @param hueDegrees The hue component of the desired color (0-360)
     * @param saturationPercent The saturation component of the desired color (0-100)
     * @param brightnessPercent The brightness component of the desired color (0-100)
     * @param colorTempDegrees The color temperature component of the desired color (1000-20000)
     */
    @Override
    public void setColorHsvt(int hueDegrees, int saturationPercent, int brightnessPercent, int colorTempDegrees) {
        color = new Color(hueDegrees, saturationPercent, brightnessPercent, colorTempDegrees);
    }

    /**
     * Returns the hash code of the MyLampState object.
     *
     * @return Hash code of the MyLampState object.
     */
    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((color == null) ? 0 : color.hashCode());
        result = prime * result + ((power == null) ? 0 : power.hashCode());
        return result;
    }

    /**
     * Compares the MyLampState object to another object for equivalence.
     *
     * @return Returns true if the lamp state objects are equivalent, false otherwise
     */
    @Override
    public boolean equals(Object other) {
        if (other != null && other instanceof MyLampState) {
            MyLampState otherMyLampState = (MyLampState) other;

            return this.getColor().equals(otherMyLampState.getColor()) &&
                    this.getPower().equals(otherMyLampState.getPower());
        }

        return false;
    }
}