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
 * This class encapsulates colors in the HSVT color space. Every color contains brightness,
 * hue, saturation, and color temperature. Brightness must be between 0-100, hue must be
 * between 0-360, saturation must be between 0-100, and color temperature must be between
 * 1000-20000.
 */
public class Color {
    /**
     * Returns a default color with a hue, saturation, brightness, and color temperature
     * set to minimum values.
     */
    public static Color defaultColor() {
        return new Color(LightingDirector.HUE_MIN, LightingDirector.SATURATION_MIN, LightingDirector.BRIGHTNESS_MIN, LightingDirector.COLORTEMP_MIN);
    }

    /**
     * Returns the color red.
     */
    public static Color red() {
        return new Color(0, 100, 100, 3500);
    }

    /**
     * Returns the color green.
     */
    public static Color green() {
        return new Color(120, 100, 50, 3500);
    }

    /**
     * Returns the color blue.
     */
    public static Color blue() {
        return new Color(240, 100, 100, 3500);
    }

    /**
     * Returns the color white.
     */
    public static Color white() {
        return new Color(0,0,100,3500);
    }

    private int hue;
    private int saturation;
    private int brightness;
    private int colorTemperature;

    /**
     * Constructs a Color object using the provided HSVT values.
     * <p>
     * <b>Note: If the provided HSVT values are outside the expected range, they will be normalized to the
     * expected range</b>
     *
     * @param hsvt Array of HSVT values
     */
    public Color(int[] hsvt) {
        this(hsvt[0], hsvt[1], hsvt[2], hsvt[3]);
    }

    /**
     * Constructs a Color object using the provided Color object.
     *
     * @param other The other Color object
     */
    public Color(Color other) {
        this(other.hue, other.saturation, other.brightness, other.colorTemperature);
    }

    /**
     * Constructs a Color object using the provided HSVT values.
     * <p>
     * <b>Note: If the provided HSVT values are outside the expected range, they will be normalized to the
     * expected range</b>
     *
     * @param hueDegrees The hue component of the desired color (0-360)
     * @param saturationPercent The saturation component of the desired color (0-100)
     * @param brightnessPercent The brightness component of the desired color (0-100)
     * @param colorTempDegrees The color temperature component of the desired color (1000-20000)
     */
    public Color(int hueDegrees, int saturationPercent, int brightnessPercent, int colorTempDegrees) {
        setHue(hueDegrees);
        setSaturation(saturationPercent);
        setBrightness(brightnessPercent);
        setColorTemperature(colorTempDegrees);
    }

    /**
     * Sets the hue component of the Color object.
     * <p>
     * <b>Note: If the provided HSVT values are outside the expected range, they will be normalized to the
     * expected range</b>
     *
     * @param hueDegrees The hue component of the desired color (0-360)
     */
    public void setHue(int hueDegrees) {
        hue = ColorStateConverter.boundHueView(hueDegrees);
    }

    /**
     * Returns the hue component of the Color object.
     *
     * @return The hue component of a Color object (0-360)
     */
    public int getHue() {
        return hue;
    }

    /**
     * Sets the saturation component of the Color object.
     * <p>
     * <b>Note: If the provided HSVT values are outside the expected range, they will be normalized to the
     * expected range</b>
     *
     * @param saturationPercent The saturation component of the desired color (0-100)
     */
    public void setSaturation(int saturationPercent) {
        saturation = ColorStateConverter.boundSaturationView(saturationPercent);
    }

    /**
     * Returns the saturation component of the Color object.
     *
     * @return The saturation component of a Color object (0-100)
     */
    public int getSaturation() {
        return saturation;
    }

    /**
     * Sets the brightness component of the Color object.
     * <p>
     * <b>Note: If the provided HSVT values are outside the expected range, they will be normalized to the
     * expected range</b>
     *
     * @param brightnessPercent The brightness component of the desired color (0-100)
     */
    public void setBrightness(int brightnessPercent) {
        brightness = ColorStateConverter.boundBrightnessView(brightnessPercent);
    }

    /**
     * Returns the brightness component of the Color object.
     *
     * @return The brightness component of the Color object (0-100)
     */
    public int getBrightness() {
        return brightness;
    }

    /**
     * Sets the color temperature component of the Color object.
     * <p>
     * <b>Note: If the provided HSVT values are outside the expected range, they will be normalized to the
     * expected range</b>
     *
     * @param colorTempDegrees The color temperature component of the desired color (1000-20000)
     */
    public void setColorTemperature(int colorTempDegrees) {
        colorTemperature = ColorStateConverter.boundColorTempView(colorTempDegrees);
    }

    /**
     * Returns the color temperature component of the Color object.
     *
     * @return The color temperature component of the Color object (1000-20000)
     */
    public int getColorTemperature() {
        return colorTemperature;
    }

    /**
     * Returns the hashCode for the Color object.
     *
     * @return The hashcode for the Color object
     */
    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + brightness;
        result = prime * result + colorTemperature;
        result = prime * result + hue;
        result = prime * result + saturation;
        return result;
    }

    /**
     * Compares the Color object to another object for equivalence.
     *
     * @return Returns true if the color objects are equivalent, false otherwise
     */
    @Override
    public boolean equals(Object other) {
        if (other != null && other instanceof Color) {
            Color otherColor = (Color) other;

            return this.getBrightness() == otherColor.getBrightness() &&
                    this.getColorTemperature() == otherColor.getColorTemperature() &&
                    this.getHue() == otherColor.getHue() &&
                    this.getSaturation() == otherColor.getSaturation();
        }

        return false;
    }
}