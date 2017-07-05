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

#import "LSFSDKSceneCollectionManagerV1.h"
#import "../initializer/LSFSDKSceneV1+Init.h"

@implementation LSFSDKSceneCollectionManagerV1

-(id)initWithLightingSystemManager: (LSFSDKLightingSystemManager *)lightingSystemManager
{
    self = [super initWithLightingSystemManager: lightingSystemManager];

    if (self)
    {
        //Intentionally left blank
    }

    return self;
}

-(void)addSceneDelegate: (id<LSFSDKSceneDelegate>)sceneDelegate
{
    [self addDelegate: sceneDelegate];
}

-(void)removeSceneDelegate: (id<LSFSDKSceneDelegate>)sceneDelegate
{
    [self removeDelegate: sceneDelegate];
}

-(LSFSDKSceneV1 *)addSceneWithID: (NSString *)sceneID
{
    LSFSDKSceneV1 *scene = [[LSFSDKSceneV1 alloc] initWithSceneID: sceneID];
    return [self addSceneWithID: sceneID scene: scene];
}

-(LSFSDKSceneV1 *)addSceneWithModel: (LSFSceneDataModel *)sceneModel
{
    LSFSDKSceneV1 *scene = [[LSFSDKSceneV1 alloc] initWithSceneDataModel: sceneModel];
    return [self addSceneWithID: sceneModel.theID scene: scene];
}

-(LSFSDKSceneV1 *)addSceneWithID: (NSString *)sceneID scene: (LSFSDKSceneV1 *)scene
{
    [itemAdapters setValue: scene forKey: sceneID];
    return scene;
}

-(LSFSDKSceneV1 *)getSceneWithID: (NSString *)sceneID
{
    return [self getAdapterForID: sceneID];
}

-(NSArray *)getScenes
{
    return [self getAdapters];
}

-(NSArray *)getScenesWithFilter: (id<LSFSDKLightingItemFilter>)filter
{
    return [self getScenesCollectionWithFilter: filter];
}

-(NSArray *)getScenesCollectionWithFilter: (id<LSFSDKLightingItemFilter>)filter
{
    return [self getAdaptersWithFilter: filter];
}

-(NSArray *)removeAllScenes
{
    return [self removeAllAdapters];
}

-(LSFSDKSceneV1 *)removeSceneWithID: (NSString *)sceneID
{
    return [self removeAdapterWithID: sceneID];
}

-(LSFSceneDataModel *)getModelWithID: (NSString *)sceneID
{
    LSFSDKSceneV1 *scene = [self getAdapterForID: sceneID];
    return (scene != nil ? [scene getSceneDataModel] : nil);
}

/*
 * Overriden functions from base class
 */
-(void)sendInitializedEvent: (id<LSFSDKLightingDelegate>)delegate item: (id)item trackingID: (LSFSDKTrackingID *)trackingID
{
    if ([delegate conformsToProtocol: @protocol(LSFSDKSceneDelegate)] && [item isKindOfClass: [LSFSDKSceneV1 class]])
    {
        id<LSFSDKSceneDelegate> sceneDelegate = (id<LSFSDKSceneDelegate>)delegate;
        LSFSDKSceneV1 *scene = (LSFSDKSceneV1 *)item;
        [sceneDelegate onSceneInitializedWithTrackingID: trackingID andScene: scene];
    }
}

-(void)sendChangedEvent: (id<LSFSDKLightingDelegate>)delegate item: (id)item
{
    if ([delegate conformsToProtocol: @protocol(LSFSDKSceneDelegate)] && [item isKindOfClass: [LSFSDKSceneV1 class]])
    {
        id<LSFSDKSceneDelegate> sceneDelegate = (id<LSFSDKSceneDelegate>)delegate;
        LSFSDKSceneV1 *scene = (LSFSDKSceneV1 *)item;
        [sceneDelegate onSceneChanged: scene];
    }
}

-(void)sendRemovedEvent: (id<LSFSDKLightingDelegate>)delegate item: (id)item
{
    if ([delegate conformsToProtocol: @protocol(LSFSDKSceneDelegate)] && [item isKindOfClass: [LSFSDKSceneV1 class]])
    {
        id<LSFSDKSceneDelegate> sceneDelegate = (id<LSFSDKSceneDelegate>)delegate;
        LSFSDKSceneV1 *scene = (LSFSDKSceneV1 *)item;
        [sceneDelegate onSceneRemoved: scene];
    }
}

-(void)sendErrorEvent: (id<LSFSDKLightingDelegate>)delegate errorEvent: (LSFSDKLightingItemErrorEvent *)errorEvent
{
    if ([delegate conformsToProtocol: @protocol(LSFSDKSceneDelegate)])
    {
        id<LSFSDKSceneDelegate> sceneDelegate = (id<LSFSDKSceneDelegate>)delegate;
        [sceneDelegate onSceneError: errorEvent];
    }
}

@end