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

#import "LSFSDKLightingItem.h"
#import "LSFSDKPower.h"
#import "LSFSDKColor.h"
#import "LSFSDKMyLampState.h"
#import "LSFSDKLampStateUniformity.h"
#import "LSFSDKCapabilityData.h"
#import "model/LSFDataModel.h"

/**
 * Base class for Lighting Items that support Color in the Lighting system.
 *
 * @warning Client software should not instantiate the LSFSDKColorItem directly.
 */
@interface LSFSDKColorItem : LSFSDKLightingItem

/** @name Public methods */

/**
 * Returns the HSVT color state of the Lighting item
 *
 * @return Integer array containing the HSVT color of the Lighting item
 */
-(NSArray *)getColorHsvt;

/**
 * Returns the power state of the Lighting item.
 *
 * @return Returns true if the Lighting item power state is on, false otherwise
 */
-(BOOL)getPowerOn;

/**
 * Returns the power state of the Lighting item.
 *
 * @return Returns true if the Lighting item power state is on, false otherwise
 */
-(BOOL)isOn;

/**
 * Returns the power state of the Lighting item.
 *
 * @return Returns true if the Lighting item power state is off, false otherwise
 */
-(BOOL)isOff;

/**
 * Returns the power state of the Lighting item.
 *
 * @return Returns ON if the Lighting item power state is on, OFF otherwise
 */
-(Power)getPower;

/**
 * Returns the HSVT color state of the Lighting item
 *
 * @return Reference to Color object
 */
-(LSFSDKColor *)getColor;

/**
 * Returns the power and color state of the Lighting item
 *
 * @return Reference to LSFSDKMyLampState object
 */
-(LSFSDKMyLampState *)getState;

/**
 * Returns the uniformity information of the Lighting item.
 *
 * @return Reference to LSFSDKLampStateUniformity object
 */
-(LSFSDKLampStateUniformity *)getUniformity;

/**
 * Returns the capabilities of the Lighting item.
 *
 * @return Reference to LSFSDKCapabilityData object
 */
-(LSFSDKCapabilityData *)getCapabilities;

/**
 * Sets the capabilities of the Lighting item.
 *
 * @param capabilityData  Reference to LSFSDKCapabilityData object
 */
-(void)setCapabilities: (LSFSDKCapabilityData *)capabilityData;

/** @name Protected methods */

/**
 * @warning This method is not intended to be used by clients, and may change or be
 * removed in subsequent releases of the SDK.
 */
-(LSFDataModel *)getColorDataModel;

@end