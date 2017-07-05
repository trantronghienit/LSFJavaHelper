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

#import "LSFSDKSceneElement.h"
#import "LSFSDKLamp.h"
#import "LSFSDKGroup.h"
#import "LSFSDKLightingItemUtil.h"
#import "LSFSDKLightingDirector.h"
#import "manager/LSFSDKAllJoynManager.h"
#import "model/LSFSDKLightingItemHasComponentFilter.h"

@implementation LSFSDKSceneElement

-(void)apply
{
    NSString *errorContext = @"LSFSDKSceneElement apply: error";

    [self postErrorIfFailure: errorContext status: [[LSFSDKAllJoynManager getSceneElementManager] applySceneElementWithID: sceneElementModel.theID]];
}

-(void)modifyWithEffect: (id<LSFSDKEffect>)effect groupMembers: (NSArray *)members
{
    NSString *errorContext = @"LSFSDKSceneElement modify: error";

    if ([self postInvalidArgIfNull: errorContext object: effect] && [self postInvalidArgIfNull: errorContext object: members])
    {
        [self postErrorIfFailure: errorContext status: [[LSFSDKAllJoynManager getSceneElementManager] updateSceneElementWithID: sceneElementModel.theID withSceneElement: [LSFSDKLightingItemUtil createSceneElementWithEffectID: [effect theID] groupMembers: members]]];
    }
}

-(void)addMember: (LSFSDKGroupMember *)member
{
    NSString *errorContext = @"LSFSDKSceneElement addMember: error";

    if ([self postInvalidArgIfNull: errorContext object: member])
    {
        NSMutableSet *lamps = [[NSMutableSet alloc] initWithSet: sceneElementModel.lamps];
        NSMutableSet *groups = [[NSMutableSet alloc] initWithSet: sceneElementModel.groups];

        if ([member isKindOfClass: [LSFSDKLamp class]])
        {
            [lamps addObject: member.theID];
        }
        else if ([member isKindOfClass: [LSFSDKGroup class]])
        {
            [groups addObject: member.theID];
        }

        [self postErrorIfFailure: errorContext status: [[LSFSDKAllJoynManager getSceneElementManager] updateSceneElementWithID: sceneElementModel.theID withSceneElement: [LSFSDKLightingItemUtil createSceneElementWithEffectID: sceneElementModel.effectID lampIDs: [lamps allObjects] groupIDs: [groups allObjects]]]];
    }
}

-(void)removeMember: (LSFSDKGroupMember *)member
{
    NSString *errorContext = @"LSFSDKSceneElement removeMember: error";

    if ([self postInvalidArgIfNull: errorContext object: member])
    {
        NSMutableSet *lamps = [[NSMutableSet alloc] initWithSet: sceneElementModel.lamps];
        NSMutableSet *groups = [[NSMutableSet alloc] initWithSet: sceneElementModel.groups];

        if ([member isKindOfClass: [LSFSDKLamp class]])
        {
            [lamps removeObject: member.theID];
        }
        else if ([member isKindOfClass: [LSFSDKGroup class]])
        {
            [groups removeObject: member.theID];
        }

        [self postErrorIfFailure: errorContext status: [[LSFSDKAllJoynManager getSceneElementManager] updateSceneElementWithID: sceneElementModel.theID withSceneElement: [LSFSDKLightingItemUtil createSceneElementWithEffectID: sceneElementModel.effectID lampIDs: [lamps allObjects] groupIDs: [groups allObjects]]]];
    }
}

-(void)deleteItem
{
    NSString *errorContext = @"LSFSDKSceneElement deleteSceneElement: error";

    [self postErrorIfFailure: errorContext status: [[LSFSDKAllJoynManager getSceneElementManager] deleteSceneElementWithID: sceneElementModel.theID]];
}

/*
 * Override base class functions
 */
-(void)rename:(NSString *)name
{
    NSString *errorContext = @"LSFSDKSceneElement rename: error";

    if ([self postInvalidArgIfNull: errorContext object: name])
    {
        [self postErrorIfFailure: errorContext status: [[LSFSDKAllJoynManager getSceneElementManager] setSceneElementNameWithID: sceneElementModel.theID andSceneElementName: name]];
    }
}

-(LSFModel *)getItemDataModel
{
    return [self getSceneElementDataModel];
}

-(void)postError:(NSString *)name status:(LSFResponseCode)status
{
    dispatch_async([[[LSFSDKLightingDirector getLightingDirector] lightingManager] dispatchQueue], ^{
        [[[[LSFSDKLightingDirector getLightingDirector] lightingManager] sceneElementCollectionManager] sendErrorEvent: name statusCode: status itemID: sceneElementModel.theID];
    });
}

/**
 * <b>WARNING: This method is not intended to be used by clients, and may change or be
 * removed in subsequent releases of the SDK.</b>
 */
-(LSFSceneElementDataModelV2 *)getSceneElementDataModel
{
    return sceneElementModel;
}

-(NSArray *)getLamps
{
    NSMutableArray *lamps = [[NSMutableArray alloc] init];
    NSSet *lampIDs = [[self getSceneElementDataModel] lamps];
    for (NSString *lampID in lampIDs)
    {
        LSFSDKLamp *lamp = [[LSFSDKLightingDirector getLightingDirector] getLampWithID: lampID];
        if (lamp != nil)
        {
            [lamps addObject: lamp];
        }
    }

    return lamps;
}

-(NSArray *)getGroups
{
    NSMutableArray *groups = [[NSMutableArray alloc] init];
    NSSet *groupIDs = [[self getSceneElementDataModel] groups];
    for (NSString *groupID in groupIDs)
    {
        LSFSDKGroup *group = [[LSFSDKLightingDirector getLightingDirector] getGroupWithID: groupID];
        if (group != nil)
        {
            [groups addObject: group];
        }
    }

    return groups;
}

-(id<LSFSDKEffect>)getEffect
{
    return [[LSFSDKLightingDirector getLightingDirector] getEffectWithID: [[self getSceneElementDataModel] effectID]];
}

-(BOOL)hasLampWithID: (NSString *)lampID
{
    return [sceneElementModel containsLamp: lampID];
}

-(BOOL)hasGroupWithID: (NSString *)groupID
{
    return [sceneElementModel containsGroup: groupID];
}

-(BOOL)hasEffectWithID: (NSString *)effectID
{
    return [sceneElementModel contaisnEffect: effectID];
}

-(BOOL)hasLamp: (LSFSDKLamp *)lamp
{
    NSString *errorContext = @"LSFSDKSceneElement hasLamp: error";
    return ([self postInvalidArgIfNull:(errorContext) object:lamp]) ? [self hasLampWithID: lamp.theID] : NO;
}

-(BOOL)hasGroup: (LSFSDKGroup *)group
{
    NSString *errorContext = @"LSFSDKSceneElement hasGroup: error";
    return ([self postInvalidArgIfNull:(errorContext) object:group]) ? [self hasGroupWithID: group.theID] : NO;
}

-(BOOL)hasEffect: (id<LSFSDKEffect>)effect
{
    NSString *errorContext = @"LSFSDKSceneElement hasEffect: error";
    return ([self postInvalidArgIfNull:(errorContext) object:effect]) ? [self hasEffectWithID: effect.theID] : NO;
}

-(BOOL)hasComponent:(LSFSDKLightingItem *)item
{
    NSString *errorContext = @"LSFSDKSceneElement hasComponent: error";
    return ([self postInvalidArgIfNull:errorContext object:item]) ? ([self hasLampWithID: item.theID] || [self hasGroupWithID: item.theID] || [self hasEffectWithID: item.theID]) : NO;
}

-(NSArray *)getDependentCollection
{
    LSFSDKLightingDirector *director = [LSFSDKLightingDirector getLightingDirector];

    NSMutableArray *dependents = [[NSMutableArray alloc] init];
    [dependents addObjectsFromArray: [[[director lightingManager] sceneCollectionManager] getScenesCollectionWithFilter: [[LSFSDKLightingItemHasComponentFilter alloc] initWithComponent: self]]];

    return [NSArray arrayWithArray: dependents];
}

-(NSArray *)getComponentCollection
{
    NSMutableArray *collection = [[NSMutableArray alloc] init];
    if ([self getEffect])
    {
        [collection addObject: [self getEffect]];
    }
    [collection addObjectsFromArray: [self getGroups]];
    [collection addObjectsFromArray: [self getLamps]];
    return collection;
}

@end