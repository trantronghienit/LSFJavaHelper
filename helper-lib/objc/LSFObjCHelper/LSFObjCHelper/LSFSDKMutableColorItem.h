/******************************************************************************
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
******************************************************************************/

#import <Foundation/Foundation.h>
#import "LSFSDKLampState.h"
#import "LSFSDKColorItem.h"

/**
 * Base class for Lighting items that support modification of power and color state.
 *
 * @warning Client software should not instantiate the LSFSDKMutableColorItem directly.
 */
@interface LSFSDKMutableColorItem : LSFSDKColorItem <LSFSDKLampState>

/**
 * Sets the Power of the Lighting item to on.
 */
-(void)turnOn;

/**
 * Sets the Power of the Lighting item to off.
 */
-(void)turnOff;

/**
 * Sets the Power state of the Lighting item.
 *
 * @param power The desired Power state
 */
-(void)setPower: (Power)power;

/**
 * Toggles the power state of the Lighting item.
 */
-(void)togglePower;

/**
 * Sets the color of the Lighting item to the provided HSVT color.
 *
 * @param color The desired LSFSDKColor
 */
-(void)setColor: (LSFSDKColor *)color;

/**
 * Sets the color of the Lighting item to the provided HSVT color.
 *
 * @warning If the provided HSVT values are outside the expected range, they will be normalized to the
 * expected range.
 *
 * @param hsvt  Array of HSVT values
 */
-(void)setColorHsvt: (NSArray *)hsvt;

/**
 * Changes the color state of the current Item to the provided hue.
 *
 * @param hueDegrees The hue component of the desired color (0-360)
 */
-(void)setHue: (unsigned int)hueDegrees;

/**
 * Changes the color state of the current Item to the provide saturation.
 *
 * @param saturationPercent The saturation component of the desired color (0-100)
 */
-(void)setSaturation: (unsigned int)saturationPercent;

/**
 * Changes the color state of the current Item to the provided brightness.
 *
 * @param brightnessPercent The brightness component of the desired color (0-100)
 */
-(void)setBrightness: (unsigned int)brightnessPercent;

/**
 * Changes the color state of the current Item to the provided color temperature.
 *
 * @param colorTempDegrees The color temperature component of the desired color (1000-20000)
 */
-(void)setColorTemp: (unsigned int)colorTempDegrees;

@end