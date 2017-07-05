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

#import "LSFSDKGroupCollectionManager.h"
#import "../initializer/LSFSDKGroup+Init.h"

@implementation LSFSDKGroupCollectionManager

-(id)initWithLightingSystemManager: (LSFSDKLightingSystemManager *)lightingSystemManager
{
    self = [super initWithLightingSystemManager: lightingSystemManager];

    if (self)
    {
        groupFlattener = [[LSFGroupsFlattener alloc] init];
    }

    return self;
}

-(void)addGroupDelegate: (id<LSFSDKGroupDelegate>)groupDelegate
{
    [self addDelegate: groupDelegate];
}

-(void)removeGroupDelegate: (id<LSFSDKGroupDelegate>)groupDelegate
{
    [self removeDelegate: groupDelegate];
}

-(LSFSDKGroup *)addGroupWithID: (NSString *)groupID
{
    LSFSDKGroup *newGroup = [[LSFSDKGroup alloc] initWithGroupID: groupID];
    return [self addGroupWithID: groupID group: newGroup];
}

-(LSFSDKGroup *)addGroupWithID: (NSString *)groupID group: (LSFSDKGroup *)group
{
    [itemAdapters setValue: group forKey: groupID];
    return group;
}

-(LSFSDKGroup *)getGroupWithID: (NSString *)groupID
{
    return [self getAdapterForID: groupID];
}

-(NSArray *)getGroups
{
    return [self getAdapters];
}

-(NSArray *)getGroupsWithFilter: (id<LSFSDKLightingItemFilter>)filter
{
    return [self getGroupsCollectionWithFilter:filter];
}

-(NSArray *)getGroupsCollectionWithFilter: (id<LSFSDKLightingItemFilter>)filter
{
    return [self getAdaptersWithFilter: filter];
}

-(void)flattenGroups
{
    [groupFlattener flattenGroups: itemAdapters];
}

-(void)flattenGroup: (LSFSDKGroup *)group
{
    [groupFlattener flattenGroups: itemAdapters withGroup: group];
}

-(NSArray *)removeAllGroups
{
    return [self removeAllAdapters];
}

-(LSFSDKGroup *)removeGroupWithID: (NSString *)groupID
{
    return [self removeAdapterWithID: groupID];
}

-(LSFGroupModel *)getModelWithID: (NSString *)groupID
{
    LSFSDKGroup *group = [self getAdapterForID: groupID];
    return (group != nil ? [group getLampGroupDataModel] : nil);
}

/*
 * Overriden functions from base class
 */
-(void)sendInitializedEvent: (id<LSFSDKLightingDelegate>)delegate item: (id)item trackingID: (LSFSDKTrackingID *)trackingID
{
    if ([delegate conformsToProtocol: @protocol(LSFSDKGroupDelegate)] && [item isKindOfClass: [LSFSDKGroup class]])
    {
        id<LSFSDKGroupDelegate> groupDelegate = (id<LSFSDKGroupDelegate>)delegate;
        LSFSDKGroup *group = (LSFSDKGroup *)item;
        [groupDelegate onGroupInitializedWithTrackingID: trackingID andGroup: group];
    }
}

-(void)sendChangedEvent: (id<LSFSDKLightingDelegate>)delegate item: (id)item
{
    if ([delegate conformsToProtocol: @protocol(LSFSDKGroupDelegate)] && [item isKindOfClass: [LSFSDKGroup class]])
    {
        id<LSFSDKGroupDelegate> groupDelegate = (id<LSFSDKGroupDelegate>)delegate;
        LSFSDKGroup *group = (LSFSDKGroup *)item;
        [groupDelegate onGroupChanged: group];
    }
}

-(void)sendRemovedEvent: (id<LSFSDKLightingDelegate>)delegate item: (id)item
{
    if ([delegate conformsToProtocol: @protocol(LSFSDKGroupDelegate)] && [item isKindOfClass: [LSFSDKGroup class]])
    {
        id<LSFSDKGroupDelegate> groupDelegate = (id<LSFSDKGroupDelegate>)delegate;
        LSFSDKGroup *group = (LSFSDKGroup *)item;
        [groupDelegate onGroupRemoved: group];
    }
}

-(void)sendErrorEvent: (id<LSFSDKLightingDelegate>)delegate errorEvent: (LSFSDKLightingItemErrorEvent *)errorEvent
{
    if ([delegate conformsToProtocol: @protocol(LSFSDKGroupDelegate)])
    {
        id<LSFSDKGroupDelegate> groupDelegate = (id<LSFSDKGroupDelegate>)delegate;
        [groupDelegate onGroupError: errorEvent];
    }
}

@end