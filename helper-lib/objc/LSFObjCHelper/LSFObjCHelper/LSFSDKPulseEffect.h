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
#import "LSFSDKColorItem.h"
#import "LSFSDKEffect.h"
#import "LSFSDKLampState.h"
#import "LSFSDKPreset.h"
#import "model/LSFPulseEffectDataModelV2.h"

/**
 * This class represents a PulseEffect definition in the lighting controller. This class
 * provides an interface to perform PulseEffect operations. Supported operations include
 * modifying the pulse effect properties, applying the pulse effect to a lamp or group,
 * renaming, and deleting the pulse effect. Pulse effects alternate between two color states
 * for a given duration and then return to their original state. Pulse effects are considered
 * fully initialized when their name, start and end color state, duration, period, and number
 * of pulses have been received. Pulse effects are still operational and can be applied to
 * lamps or groups even in the uninitialized state.
 *
 * @warning This class is not meant to be instantiated directly. PulseEffects should be retrieved
 * from the LightingDirector using the [LSFSDKLightingDirector pulseEffects] method.
 *
 * @warning This class does not support PulseEffect creation. See [LSFSDKLightingDirector createPulseEffectWithFromState:toState:period:duration:count:name:].
 */
@interface LSFSDKPulseEffect : LSFSDKColorItem <LSFSDKEffect>
{
    @protected LSFPulseEffectDataModelV2 *pulseEffectDataModel;
}

/** @name Class Properties */

/**
 * Specifies whether the current PulseEffect is using the current state as its starting
 * state.
 */
@property (nonatomic) BOOL isStartWithCurrent;

/**
 * Specifies the LSFSDKPulseEffect starting lamp state.
 */
@property (nonatomic, strong) LSFSDKMyLampState * startState;

/**
 * Specifies the LSFSDKPulseEffect ending lamp state.
 */
@property (nonatomic, strong) LSFSDKMyLampState * endState;

/**
 * Specifies the LSFSDKPreset associated with the LSFSDKPulseEffect start state.
 */
@property (nonatomic, strong) LSFSDKPreset * startPreset;

/**
 * Specifies the LSFSDKPreset associated with the LSFSDKPulseEffect end state.
 */
@property (nonatomic, strong) LSFSDKPreset * endPreset;

/**
 * Specifies the preset ID associated with the LSFSDKPulseEffect start state.
 */
@property (nonatomic, strong) NSString * startPresetID;

/**
 * Specifies the preset ID associated with the LSFSDKPulseEffect end state.
 */
@property (nonatomic, strong) NSString * endPresetID;

/**
 * Specifies the period of the LSFSDKPulseEffect, in milliseconds.
 */
@property (nonatomic) unsigned int period;

/**
 * Specifies the duration of the LSFSDKPulseEffect, in milliseconds.
 */
@property (nonatomic) unsigned int duration;

/**
 * Specifies the number of pulses in the LSFSDKPulseEffect.
 */
@property (nonatomic) unsigned int count;

/** @name Initializing an LSFSDKPulseEffect Object */

/**
 * @warning This method is not intended to be used by clients, and may change or be
 * removed in subsequent releases of the SDK.
 */
-(instancetype)init NS_UNAVAILABLE;

/** @name Pulse Effect Operations */

/**
 * Modifies the current PulseEffect using the provided parameters.
 *
 * @param fromState The new fromState
 * @param toState The new toState
 * @param period The new period, in milliseconds
 * @param duration The new duration, in milliseconds
 * @param count The new count
 */
-(void)modifyFromState: (id<LSFSDKLampState>)fromState toState: (id<LSFSDKLampState>)toState period: (unsigned int)period duration: (unsigned int)duration count: (unsigned int)count;

/**
 * Permanently deletes the current LSFSDKPulseEffect from the lighting controller.
 *
 * @warning You cannot delete a pulse effect that is used by a scene element. The dependency
 * must be deleted first.
 */
-(void)deleteItem;

/** @name Finding Objects in a Pulse Effect */

/**
 * Tests to see if the current LSFSDKPulseEffect contains the provided LSFSDKPreset.
 *
 * @param preset The LSFSDKPreset to be confirmed a component of the LSFSDKPulseEffect
 *
 * @return Returns true if the LSFSDKPulseEffect contains the LSFSDKPreset, false otherwise
 */
-(BOOL)hasPreset: (LSFSDKPreset *)preset;

/** @name Protected methods */

/**
 * @warning This method is not intended to be used by clients, and may change or be
 * removed in subsequent releases of the SDK.
 *
 * @param presetID  The ID of the preset
 */
-(BOOL)hasPresetWithID: (NSString *)presetID;

/**
 * @warning This method is not intended to be used by clients, and may change or be
 * removed in subsequent releases of the SDK.
 */
-(LSFPulseEffectDataModelV2 *)getPulseEffectDataModel;

@end