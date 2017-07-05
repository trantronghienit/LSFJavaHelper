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

#import "LSFSDKLamp.h"
#import "LSFSDKGroup.h"
#import "LSFSDKLightingItemUtil.h"
#import "LSFSDKLightingDirector.h"
#import "manager/LSFSDKAllJoynManager.h"
#import "model/LSFGroupModel.h"
#import "model/LSFConstants.h"
#import "model/LSFSDKLightingItemHasComponentFilter.h"
#import "model/LSFConstants.h"
#import "model/LSFSDKAllLampsDataModel.h"

@implementation LSFSDKGroup

@synthesize colorTempMin = _colorTempMin;
@synthesize colorTempMax = _colorTempMax;
@synthesize isAllLampsGroup = _isAllLampsGroup;

-(int)colorTempMin
{
    return [[self getLampGroupDataModel] groupColorTempMin];
}

-(int)colorTempMax
{
    return [[self getLampGroupDataModel] groupColorTempMax];
}

-(BOOL)isAllLampsGroup
{
    return [[LSFSDKAllLampsDataModel getAllLampsGroupID] isEqualToString: [[self getLampGroupDataModel] theID]];
}

-(void)add: (LSFSDKGroupMember *)member
{
    NSString *errorContext = @"LSFSDKGroup add: error";

    if ([self postInvalidArgIfNull: errorContext object: member])
    {
        NSMutableSet *lamps = [[NSMutableSet alloc] initWithArray: [[groupModel members] lamps]];
        NSMutableSet *groups = [[NSMutableSet alloc] initWithArray: [[groupModel members] lampGroups]];

        if ([member isKindOfClass: [LSFSDKLamp class]])
        {
            LSFSDKLamp *lamp = (LSFSDKLamp *)member;
            [lamps addObject: [[lamp getLampDataModel] theID]];
        }
        else if ([member isKindOfClass: [LSFSDKGroup class]])
        {
            LSFSDKGroup *group = (LSFSDKGroup *)member;
            [groups addObject: [[group getLampGroupDataModel] theID]];
        }

        [self postErrorIfFailure: errorContext status: [[LSFSDKAllJoynManager getGroupManager] updateLampGroupWithID: groupModel.theID andLampGroup: [LSFSDKLightingItemUtil createLampGroupFromLampIDs: [lamps allObjects] groupIDs: [groups allObjects]]]];
    }
}

-(void)remove: (LSFSDKGroupMember *)member
{
    NSString *errorContext = @"LSFSDKGroup remove: error";

    if ([self postInvalidArgIfNull: errorContext object: member])
    {
        NSMutableSet *lamps = [[NSMutableSet alloc] initWithArray: [[groupModel members] lamps]];
        NSMutableSet *groups = [[NSMutableSet alloc] initWithArray: [[groupModel members] lampGroups]];

        if ([member isKindOfClass: [LSFSDKLamp class]])
        {
            LSFSDKLamp *lamp = (LSFSDKLamp *)member;
            [lamps removeObject: [[lamp getLampDataModel] theID]];
        }
        else if ([member isKindOfClass: [LSFSDKGroup class]])
        {
            LSFSDKGroup *group = (LSFSDKGroup *)member;
            [groups removeObject: [[group getLampGroupDataModel] theID]];
        }

        [self postErrorIfFailure: errorContext status: [[LSFSDKAllJoynManager getGroupManager] updateLampGroupWithID: groupModel.theID andLampGroup: [LSFSDKLightingItemUtil createLampGroupFromLampIDs: [lamps allObjects] groupIDs: [groups allObjects]]]];
    }
}

-(void)modify: (NSArray *)members
{
    NSString *errorContext = @"LSFSDKGroup modify: error";

    if ([self postInvalidArgIfNull: errorContext object: members])
    {
        [self postErrorIfFailure: errorContext status: [[LSFSDKAllJoynManager getGroupManager] updateLampGroupWithID: groupModel.theID andLampGroup: [LSFSDKLightingItemUtil createLampGroupFromGroupMembers: members]]];
    }
}

-(void)deleteItem
{
    NSString *errorContext = @"LSFSDKGroup deleteGroup: error";
    [self postErrorIfFailure: errorContext status: [[LSFSDKAllJoynManager getGroupManager] deleteLampGroupWithID: groupModel.theID]];
}

-(BOOL)hasLamp: (LSFSDKLamp *)lamp
{
    NSString *errorContext = @"LSFSDKGroup hasLamp: error";
    return ([self postInvalidArgIfNull:(errorContext) object:lamp]) ? [self hasLampWithID: lamp.theID] : NO;
}

-(BOOL)hasGroup: (LSFSDKGroup *)group
{
    NSString *errorContext = @"LSFSDKGroup hasGroup: error";
    return ([self postInvalidArgIfNull:(errorContext) object:group]) ? [self hasGroupWithID: group.theID] : NO;

}

-(BOOL)hasComponent:(LSFSDKLightingItem *)item
{
    NSString *errorContext = @"LSFSDKGroup hasComponent: error";
    return ([self postInvalidArgIfNull: errorContext object: item]) ? ([self hasLampWithID: item.theID] || [self hasGroupWithID: item.theID]): NO;
}

-(BOOL)hasLampWithID: (NSString *)lampID
{
    return [groupModel containsLamp: lampID];
}

-(BOOL)hasGroupWithID: (NSString *)groupID
{
    return [groupModel containsGroup: groupID];
}

-(NSArray *)getGroups
{
    NSMutableArray *groups = [[NSMutableArray alloc] init];
    NSSet *groupIDs = [self getGroupIDs];
    LSFSDKLightingDirector *lightingDirector = [LSFSDKLightingDirector getLightingDirector];

    for (NSString *groupID in groupIDs)
    {
        [groups addObject: [lightingDirector getGroupWithID: groupID]];
    }

    return groups;
}

-(NSArray *)getLamps
{
    NSMutableArray *lamps = [[NSMutableArray alloc] init];
    NSSet *lampIDs = [self getLampIDs];
    LSFSDKLightingDirector *lightingDirector = [LSFSDKLightingDirector getLightingDirector];

    for (NSString *lampID in lampIDs)
    {
        LSFSDKLamp *lamp = [lightingDirector getLampWithID: lampID];
        if (lamp != nil)
        {
            [lamps addObject: lamp];
        }
    }

    return lamps;
}

-(NSSet *)getLampIDs
{
    return groupModel.lamps;
}

-(NSSet *)getGroupIDs
{
    return groupModel.groups;
}

/*
 * Override base class functions
 */
-(void)setPowerOn: (BOOL)powerOn
{
    NSString *errorContext = @"LSFSDKGroup setPowerOn: error";
    [self postErrorIfFailure: errorContext status: [[LSFSDKAllJoynManager getGroupManager] transitionLampGroupID: groupModel.theID onOffField: powerOn]];
}

-(void)setColorHsvtWithHue:(unsigned int)hueDegrees saturation:(unsigned int)saturationPercent brightness:(unsigned int)brightnessPercent colorTemp:(unsigned int)colorTempDegrees
{
    NSString *errorContext = @"LSFSDKGroup setColorHsvt: error";
    LSFConstants *constants = [LSFConstants getConstants];

    unsigned int scaledBrightness = [constants scaleLampStateValue: brightnessPercent withMax: 100];
    unsigned int scaledHue = [constants scaleLampStateValue: hueDegrees withMax: 360];
    unsigned int scaledSaturation = [constants scaleLampStateValue: saturationPercent withMax: 100];
    unsigned int scaledColorTemp = [constants scaleColorTemp: colorTempDegrees];

    LSFLampState *lampState = [[LSFLampState alloc] initWithOnOff: self.getPowerOn brightness: scaledBrightness hue: scaledHue saturation: scaledSaturation colorTemp: scaledColorTemp];

    [self postErrorIfFailure: errorContext status: [[LSFSDKAllJoynManager getGroupManager] transitionLampGroupID: groupModel.theID toState: lampState]];
}

-(void)setHue:(unsigned int)hueDegrees
{
    NSString *errorContext = @"LSFSDKGroup setHue: error";

    unsigned int scaledHue = [[LSFConstants getConstants] scaleLampStateValue: hueDegrees withMax: 360];

    [self postErrorIfFailure: errorContext status: [[LSFSDKAllJoynManager getGroupManager] transitionLampGroupID: groupModel.theID hueField: scaledHue]];
}

-(void)setSaturation:(unsigned int)saturationPercent
{
    NSString *errorContext = @"LSFSDKGroup setSaturation: error";

    unsigned int scaledSaturation = [[LSFConstants getConstants] scaleLampStateValue: saturationPercent withMax: 100];

    [self postErrorIfFailure: errorContext status: [[LSFSDKAllJoynManager getGroupManager] transitionLampGroupID: groupModel.theID saturationField: scaledSaturation]];
}

-(void)setBrightness:(unsigned int)brightnessPercent
{
    NSString *errorContext = @"LSFSDKGroup setBrightness: error";

    unsigned int scaledBrightness = [[LSFConstants getConstants] scaleLampStateValue: brightnessPercent withMax: 100];

    [self postErrorIfFailure: errorContext status: [[LSFSDKAllJoynManager getGroupManager] transitionLampGroupID: groupModel.theID brightnessField: scaledBrightness]];
}

-(void)setColorTemp:(unsigned int)colorTempDegrees
{
    NSString *errorContext = @"LSFSDKGroup setColorTemperature: error";

    unsigned int scaledColorTemp = [[LSFConstants getConstants] scaleColorTemp: colorTempDegrees];

    [self postErrorIfFailure: errorContext status: [[LSFSDKAllJoynManager getGroupManager] transitionLampGroupID: groupModel.theID colorTempField:scaledColorTemp]];
}

-(void)rename: (NSString *)name
{
    NSString *errorContext = @"LSFSDKGroup rename: error";

    if ([self postInvalidArgIfNull: errorContext object: name])
    {
        [self postErrorIfFailure: errorContext status: [[LSFSDKAllJoynManager getGroupManager] setLampGroupNameForID: groupModel.theID andName: name]];
    }
}

-(void)applyPreset: (LSFSDKPreset *)preset
{
    NSString *errorContext = @"LSFSDKGroup applyPreset: error";

    if ([self postInvalidArgIfNull: errorContext object: preset])
    {
        [self postErrorIfFailure: errorContext status: [[LSFSDKAllJoynManager getGroupManager] transitionLampGroupID: groupModel.theID toPreset: [[preset getPresetDataModel] theID]]];
    }
}

-(void)applyEffect: (id<LSFSDKEffect>)effect
{
    NSString *errorContext = @"LSFSDKGroup applyEffect: error";

    if ([self postInvalidArgIfNull: errorContext object: effect])
    {
        if ([effect isKindOfClass: [LSFSDKPreset class]])
        {
            [self applyPreset: (LSFSDKPreset *)effect];
        }
        else if ([effect isKindOfClass: [LSFSDKTransitionEffect class]])
        {
            [self postErrorIfFailure: errorContext status: [[LSFSDKAllJoynManager getTransitionEffectManager] applyTranstionEffectWithID: [effect theID] onLampGroups: [NSArray arrayWithObjects: groupModel.theID, nil]]];
        }
        else if ([effect isKindOfClass: [LSFSDKPulseEffect class]])
        {
            [self postErrorIfFailure: errorContext status: [[LSFSDKAllJoynManager getPulseEffectManager] applyPulseEffectWithID: [effect theID] onLampGroups: [NSArray arrayWithObjects: groupModel.theID, nil]]];
        }
    }
}

-(LSFDataModel *)getColorDataModel
{
    return [self getLampGroupDataModel];
}

-(NSArray *)getDependentCollection
{
    LSFSDKLightingDirector *director = [LSFSDKLightingDirector getLightingDirector];

    NSMutableArray *dependents = [[NSMutableArray alloc] init];
    [dependents addObjectsFromArray: [[[director lightingManager] groupCollectionManager] getGroupsCollectionWithFilter: [[LSFSDKLightingItemHasComponentFilter alloc] initWithComponent: self]]];
    [dependents addObjectsFromArray: [[[director lightingManager] sceneCollectionManagerV1] getScenesCollectionWithFilter: [[LSFSDKLightingItemHasComponentFilter alloc] initWithComponent: self]]];
    [dependents addObjectsFromArray: [[[director lightingManager] sceneElementCollectionManager] getSceneElementsCollectionWithFilter: [[LSFSDKLightingItemHasComponentFilter alloc] initWithComponent: self]]];

    return [NSArray arrayWithArray: dependents];

}

-(void)postError:(NSString *)name status:(LSFResponseCode)status
{
    dispatch_async([[[LSFSDKLightingDirector getLightingDirector] lightingManager] dispatchQueue], ^{
        [[[[LSFSDKLightingDirector getLightingDirector] lightingManager] groupCollectionManager] sendErrorEvent: name statusCode: status itemID: groupModel.theID];
    });
}

/**
 * <b>WARNING: This method is not intended to be used by clients, and may change or be
 * removed in subsequent releases of the SDK.</b>
 */
-(LSFGroupModel *)getLampGroupDataModel
{
    return groupModel;
}

@end