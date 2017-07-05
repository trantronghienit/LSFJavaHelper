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
#import "LSFSDKPower.h"
#import "LSFSDKColor.h"
#import <internal/LSFLampState.h>

/**
 * Default implementation of the LSFSDKLampState interface and represents the power and
 * color state of a Lighting item.
 */
@interface LSFSDKMyLampState : NSObject <LSFSDKLampState>

/** @name Class Properties */

/**
 * The power state.
 */
@property (nonatomic) Power power;

/**
 * The color state.
 */
@property (nonatomic, strong) LSFSDKColor *color;

/** @name Initializing an LSFSDKMyLampState Object */

/**
 * Constructs a LSFSDKMyLampState object.
 *
 * @param state  An existing LSFLampState.
 *
 * @return An LSFSDKMyLampState object
 */
-(id)initWithLSFLampState: (LSFLampState *)state;

/**
 * Constructs a LSFSDKMyLampState object.
 *
 * @warning If the provided HSVT values are outside the expected range, they will be normalized to the
 * expected range.
 *
 * @param power  The Power state.
 * @param hue  The hue component of the Color (0-360)
 * @param sat  The saturation component of a Color (0-100)
 * @param brightness  The brightness component of a Color (0-100)
 * @param colorTemp  The color temperature component of a Color (1000-20000)
 *
 * @return An LSFSDKMyLampState object
 */
-(id)initWithPower: (Power)power hue: (unsigned int)hue saturation: (unsigned int)sat brightness: (unsigned int)brightness colorTemp: (unsigned int)colorTemp;

/**
 * Constructs a LSFSDKMyLampState object.
 *
 * @param power  The Power state
 * @param colorState  The Color state
 *
 * @return An LSFSDKMyLampState object
 */
-(id)initWithPower: (Power)power color: (LSFSDKColor *)colorState;

@end