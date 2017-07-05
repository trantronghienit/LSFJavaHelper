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

#import "LSFSDKTransitionEffect.h"
#import "LSFSDKLamp.h"
#import "LSFSDKGroup.h"
#import "LSFSDKLightingItemUtil.h"
#import "LSFSDKLightingDirector.h"
#import "manager/LSFSDKAllJoynManager.h"
#import "model/LSFSDKLightingItemHasComponentFilter.h"

@implementation LSFSDKTransitionEffect

@synthesize preset = _preset;
@synthesize presetID = _presetID;
@synthesize duration = _duration;

-(void)modify: (id<LSFSDKLampState>)state duration: (unsigned int)duration
{
    NSString *errorContext = @"LSFSDKTransitionEffect modify: error";

    if ([self postInvalidArgIfNull: errorContext object: state])
    {
        if ([state isKindOfClass: [LSFSDKPreset class]])
        {
            [self postErrorIfFailure: errorContext status: [[LSFSDKAllJoynManager getTransitionEffectManager] updateTransitionEffectWithID: transitionEffectDataModel.theID andTransitionEffect: [LSFSDKLightingItemUtil createTransitionEffectFromPreset:(LSFSDKPreset *)state duration: duration]]];
        }
        else
        {
            [self postErrorIfFailure: errorContext status: [[LSFSDKAllJoynManager getTransitionEffectManager] updateTransitionEffectWithID: transitionEffectDataModel.theID andTransitionEffect: [LSFSDKLightingItemUtil createTransitionEffectFromPower: [state getPowerOn] hsvt: [state getColorHsvt] duration: duration]]];
        }
    }
}

-(void)deleteItem
{
    NSString *errorContext = @"LSFSDKTransitionEffect deleteTransitionEffect: error";

    [self postErrorIfFailure: errorContext status: [[LSFSDKAllJoynManager getTransitionEffectManager] deleteTransitionEffectWithID: transitionEffectDataModel.theID]];
}

-(BOOL)hasPreset: (LSFSDKPreset *)preset
{
    NSString *errorContext = @"LSFSDKTransitionEffect hasPreset: error";
    return ([self postInvalidArgIfNull: errorContext object: preset]) ? [self hasPresetWithID: preset.theID] : NO;
}

-(BOOL)hasPresetWithID:(NSString *)presetID
{
    return [transitionEffectDataModel containsPreset: presetID];
}

/*
 * LSFSDKEffect implementation
 */
-(void)applyToGroupMember: (LSFSDKGroupMember *)member
{
    NSString *errorContext = @"LSFSDKTransitionEffect applyToGroupMember: error";

    if ([self postInvalidArgIfNull: errorContext object: member])
    {
        [member applyEffect: self];
    }
}

/*
 * Override base class functions
 */
-(void)rename:(NSString *)name
{
    NSString *errorContext = @"LSFSDKTransitionEffect rename: error";

    if ([self postInvalidArgIfNull: errorContext object: name])
    {
        [self postErrorIfFailure: errorContext status: [[LSFSDKAllJoynManager getTransitionEffectManager] setTransitionEffectNameWithID: transitionEffectDataModel.theID transitionEffectName: name]];
    }
}

-(BOOL)hasComponent:(LSFSDKLightingItem *)item
{
    NSString *errorContext = @"LSFSDKTransitionEffect hasComponent: error";
    return ([self postInvalidArgIfNull: errorContext object: item]) ? [self hasPresetWithID: item.theID] : NO;
}

-(NSArray *)getDependentCollection
{
    NSMutableArray *dependents = [[NSMutableArray alloc] init];

    LSFSDKLightingDirector *director = [LSFSDKLightingDirector getLightingDirector];
    [dependents addObjectsFromArray: [[[director lightingManager] sceneElementCollectionManager] getSceneElementsCollectionWithFilter:[[LSFSDKLightingItemHasComponentFilter alloc] initWithComponent: self]]];

    return [NSArray arrayWithArray: dependents];
}

-(LSFDataModel *)getColorDataModel
{
    return [self getTransitionEffectDataModel];
}

-(void)postError:(NSString *)name status:(LSFResponseCode)status
{
    dispatch_async([[[LSFSDKLightingDirector getLightingDirector] lightingManager] dispatchQueue], ^{
        [[[[LSFSDKLightingDirector getLightingDirector] lightingManager] transitionEffectCollectionManager] sendErrorEvent: name statusCode: status itemID: transitionEffectDataModel.theID];
    });
}

/**
 * <b>WARNING: This method is not intended to be used by clients, and may change or be
 * removed in subsequent releases of the SDK.</b>
 */
-(LSFTransitionEffectDataModelV2 *)getTransitionEffectDataModel
{
    return transitionEffectDataModel;
}

-(NSString *)presetID
{
    return transitionEffectDataModel.presetID;
}

-(LSFSDKPreset *)preset
{
    return [[LSFSDKLightingDirector getLightingDirector] getPresetWithID: self.presetID];
}

-(unsigned int)duration
{
    return transitionEffectDataModel.duration;
}

@end