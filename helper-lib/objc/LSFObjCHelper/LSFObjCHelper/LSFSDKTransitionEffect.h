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
#import "model/LSFTransitionEffectDataModelV2.h"

/**
 * This class represents a TransitionEffect definition in the lighting controller. This class
 * provides an interface to perform TransitionEffect operations. Supported operations include
 * modifying the transition effect properties, applying the transition effect to a lamp or group,
 * renaming, and deleting the transition effect. Transition effects gradually changes the color
 * state of a lamp or group over a given duration. Specifying a duration zero will change the
 * lamp or group state immediately. Transition effects are considered fully initialized if the
 * name, color state, and duration have been received. Transition effects are still operational
 * and can be applied to lamps or groups even in the uninitialized state.
 *
 * @warning This class is not meant to be instantiated directly. TransitionEffects should be retrieved
 * from the LSFSDKLightingDirector using the [LSFSDKLightingDirector transitionEffects] method.
 *
 * @warning This class does not support TransitionEffect creation. See [LSFSDKLightingDirector createTransitionEffectWithLampState:duration:name:].
 */
@interface LSFSDKTransitionEffect : LSFSDKColorItem <LSFSDKEffect>
{
    @protected LSFTransitionEffectDataModelV2 *transitionEffectDataModel;
}

/** @name Class Properties */

/**
 * Specifies the Preset associated with the current LSFSDKTransitionEffect.
 *
 * @warning This method may return null if the LSFSDKTransitionEffect was not created
 * using an existing LSFSDKPreset.
 *
 * @return The LSFSDKPreset associated with the LSFSDKTransitionEffect
 */
@property (nonatomic, strong) LSFSDKPreset *preset;

/**
 * Specifies the preset ID associated with the current LSFSDKTransitionEffect.
 *
 * @warning This method may return null if the LSFSDKTransitionEffect was not created
 * using an existing LSFSDKPreset.
 *
 * @return The preset ID associated with the LSFSDKTransitionEffect
 */
@property (nonatomic, strong) NSString *presetID;

/**
 * Specifies the duration of the current LSFSDKTransitionEffect.
 */
@property (nonatomic) unsigned int duration;

/** @name Initializing an LSFSDKTransitionEffect Object */

/**
 * @warning This method is not intended to be used by clients, and may change or be
 * removed in subsequent releases of the SDK.
 */
-(instancetype)init NS_UNAVAILABLE;

/** @name Transition Effect Operations */

/**
 * Modifies the current LSFSDKTransitionEffect using the provided lamp state and duration.
 *
 * @param state The new LampState
 * @param duration The new duration, in milliseconds
 */
-(void)modify: (id<LSFSDKLampState>)state duration: (unsigned int)duration;

/**
 * Permanently deletes the current LSFSDKTransitionEffect from the lighting controller.
 *
 * @warning You cannot delete a transition effect that is used by a scene element. The dependency
 * must be deleted first.
 */
-(void)deleteItem;

/** @name Finding Objects in a Transition Effect */

/**
 * Tests to see if the current LSFSDKTransitionEffect contains the provided Preset.
 *
 * @param preset The Preset to be confirmed a component of the LSFSDKTransitionEffect
 *
 * @return Returns true if the LSFSDKTransitionEffect contains the Preset, false otherwise
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
-(LSFTransitionEffectDataModelV2 *)getTransitionEffectDataModel;

@end