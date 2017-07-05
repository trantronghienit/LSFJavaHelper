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
#import <LSFSDKLampDetails.h>
#import "LSFSDKGroupMember.h"
#import "LSFSDKEffect.h"
#import "LSFSDKLampAbout.h"
#import "model/LSFLampModel.h"

@class LSFSDKPreset;

/**
 * This class represents a Lamp that is connected to the lighting controller. This class
 * provides an interface to perform Lamp operations. Supported operations include changing
 * color and power state, renaming, applying effects and presets, and retrieving lamp data.
 * Lamps are considered fully initialized when the name, power and color state, and lamp
 * details have been received. Lamps are still operational even in the uninitialized state.
 *
 * @warning This class is not meant to be instantiated directly. Lamps should be retrieved
 * from the LSFSDKLightingDirector using the [LSFSDKLightingDirector lamps] method.
 */
@interface LSFSDKLamp : LSFSDKGroupMember
{
    @protected LSFLampModel *lampModel;
}

/** @name Class Properties */

/**
 * Returns the Lamp AllJoyn about data for the current LSFSDKLamp.
 *
 * @return Reference to LSFSDKLampAbout object
 */
@property (strong, nonatomic, readonly) LSFSDKLampAbout *about;

/**
 * Returns the LSFSDKLampDetails for the current LSFSDKLamp.
 *
 * @return Reference to LSFSDKLampDetails object
 */
@property (strong, nonatomic, readonly) LSFSDKLampDetails *details;

/**
 * Returns the LSFSDKLampParameters for the current LSFSDKLamp.
 *
 * @return Reference to LSFSDKLampParameters object
 */
@property (strong, nonatomic, readonly) LSFSDKLampParameters *parameters;

/**
 * Returns the minimum color temperature value supported by the current LSFSDKLamp.
 *
 * @return The Lamps minimum color temperature value
 */
@property (nonatomic, readonly) int colorTempMin;

/**
 * Returns the maximum color temperature value supported by the current LSFSDKLamp.
 *
 * @return The Lamps maximum color temperature value
 */
@property (nonatomic, readonly) int colorTempMax;

/** @name Initializing an LSFSDKGroup Object */

/**
 * @warning This method is not intended to be used by clients, and may change or be
 * removed in subsequent releases of the SDK.
 */
-(instancetype)init NS_UNAVAILABLE;

/** @name Protected methods */

/**
 * @warning This method is not intended to be used by clients, and may change or be
 * removed in subsequent releases of the SDK.
 */
-(LSFLampModel *)getLampDataModel;

@end