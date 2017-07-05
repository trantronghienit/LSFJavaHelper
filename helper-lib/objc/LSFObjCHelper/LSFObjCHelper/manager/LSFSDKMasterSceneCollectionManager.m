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

#import "LSFSDKMasterSceneCollectionManager.h"
#import "../initializer/LSFSDKMasterScene+Init.h"

@implementation LSFSDKMasterSceneCollectionManager

-(id)initWithLightingSystemManager: (LSFSDKLightingSystemManager *)lightingSystemManager
{
    self = [super initWithLightingSystemManager: lightingSystemManager];

    if (self)
    {
        //Intentionally left blank
    }

    return self;
}

-(void)addMasterSceneDelegate: (id<LSFSDKMasterSceneDelegate>)masterSceneDelegate
{
    [self addDelegate: masterSceneDelegate];
}

-(void)removeMasterSceneDelegate: (id<LSFSDKMasterSceneDelegate>)masterSceneDelegate
{
    [self removeDelegate: masterSceneDelegate];
}

-(LSFSDKMasterScene *)addMasterSceneWithID: (NSString *)masterSceneID
{
    LSFSDKMasterScene *newMasterScene = [[LSFSDKMasterScene alloc] initWithMasterSceneID: masterSceneID];
    return [self addMasterSceneWithID: masterSceneID masterScene: newMasterScene];
}

-(LSFSDKMasterScene *)addMasterSceneWithID: (NSString *)masterSceneID masterScene: (LSFSDKMasterScene *)masterScene
{
    [itemAdapters setValue: masterScene forKey: masterSceneID];
    return masterScene;
}

-(LSFSDKMasterScene *)getMasterSceneWithID: (NSString *)masterSceneID
{
    return [self getAdapterForID: masterSceneID];
}

-(NSArray *)getMasterScenes
{
    return [self getAdapters];
}

-(NSArray *)getMasterScenesWithFilter: (id<LSFSDKLightingItemFilter>)filter
{
    return [self getMasterScenesCollectionWithFilter:filter];
}

-(NSArray *)getMasterScenesCollectionWithFilter: (id<LSFSDKLightingItemFilter>)filter;
{
    return [self getAdaptersWithFilter: filter];
}

-(NSArray *)removeAllMasterScenes
{
    return [self removeAllAdapters];
}
-(LSFSDKMasterScene *)removeMasterSceneWithID: (NSString *)masterSceneID
{
    return [self removeAdapterWithID: masterSceneID];
}

-(LSFMasterSceneDataModel *)getModelWithID: (NSString *)masterSceneID
{
    LSFSDKMasterScene *masterScene = [self getAdapterForID: masterSceneID];
    return (masterScene != nil ? [masterScene getMasterSceneDataModel] : nil);
}

/*
 * Overriden functions from base class
 */
-(void)sendInitializedEvent: (id<LSFSDKLightingDelegate>)delegate item: (id)item trackingID: (LSFSDKTrackingID *)trackingID
{
    if ([delegate conformsToProtocol: @protocol(LSFSDKMasterSceneDelegate)] && [item isKindOfClass: [LSFSDKMasterScene class]])
    {
        id<LSFSDKMasterSceneDelegate> masterSceneDelegate = (id<LSFSDKMasterSceneDelegate>)delegate;
        LSFSDKMasterScene *masterScene = (LSFSDKMasterScene *)item;
        [masterSceneDelegate onMasterSceneInitializedWithTrackingID: trackingID andMasterScene: masterScene];
    }
}

-(void)sendChangedEvent: (id<LSFSDKLightingDelegate>)delegate item: (id)item
{
    if ([delegate conformsToProtocol: @protocol(LSFSDKMasterSceneDelegate)] && [item isKindOfClass: [LSFSDKMasterScene class]])
    {
        id<LSFSDKMasterSceneDelegate> masterSceneDelegate = (id<LSFSDKMasterSceneDelegate>)delegate;
        LSFSDKMasterScene *masterScene = (LSFSDKMasterScene *)item;
        [masterSceneDelegate onMasterSceneChanged: masterScene];
    }
}

-(void)sendRemovedEvent: (id<LSFSDKLightingDelegate>)delegate item: (id)item
{
    if ([delegate conformsToProtocol: @protocol(LSFSDKMasterSceneDelegate)] && [item isKindOfClass: [LSFSDKMasterScene class]])
    {
        id<LSFSDKMasterSceneDelegate> masterSceneDelegate = (id<LSFSDKMasterSceneDelegate>)delegate;
        LSFSDKMasterScene *masterScene = (LSFSDKMasterScene *)item;
        [masterSceneDelegate onMasterSceneRemoved: masterScene];
    }
}

-(void)sendErrorEvent: (id<LSFSDKLightingDelegate>)delegate errorEvent: (LSFSDKLightingItemErrorEvent *)errorEvent
{
    if ([delegate conformsToProtocol: @protocol(LSFSDKMasterSceneDelegate)])
    {
        id<LSFSDKMasterSceneDelegate> masterSceneDelegate = (id<LSFSDKMasterSceneDelegate>)delegate;
        [masterSceneDelegate onMasterSceneError: errorEvent];
    }
}

@end