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

#import "LSFSDKSceneElementCollectionManager.h"
#import "../initializer/LSFSDKSceneElement+Init.h"

@implementation LSFSDKSceneElementCollectionManager

-(id)initWithLightingSystemManager: (LSFSDKLightingSystemManager *)lightingSystemManager
{
    self = [super initWithLightingSystemManager: lightingSystemManager];

    if (self)
    {
        //Intentionally left blank
    }

    return self;
}

-(void)addSceneElementDelegate: (id<LSFSDKSceneElementDelegate>)sceneElementDelegate
{
    [self addDelegate: sceneElementDelegate];
}

-(void)removeSceneElementDelegate: (id<LSFSDKSceneElementDelegate>)sceneElementDelegate
{
    [self removeDelegate: sceneElementDelegate];
}

-(LSFSDKSceneElement *)addSceneElementWithID: (NSString *)sceneElementID
{
    LSFSDKSceneElement *sceneElement = [[LSFSDKSceneElement alloc] initWithSceneElementID: sceneElementID];
    return [self addSceneElementWithID: sceneElementID sceneElement: sceneElement];
}

-(LSFSDKSceneElement *)addSceneElementWithID: (NSString *)sceneElementID sceneElement: (LSFSDKSceneElement *)sceneElement
{
    [itemAdapters setValue: sceneElement forKey: sceneElementID];
    return sceneElement;
}

-(LSFSDKSceneElement *)getSceneElementWithID: (NSString *)sceneElementID
{
    return [self getAdapterForID: sceneElementID];
}

-(NSArray *)getSceneElements
{
    return [self getAdapters];
}

-(NSArray *)getSceneElementsWithFilter: (id<LSFSDKLightingItemFilter>)filter
{
    return [self getSceneElementsCollectionWithFilter: filter];
}

-(NSArray *)getSceneElementsCollectionWithFilter: (id<LSFSDKLightingItemFilter>)filter
{
    return [self getAdaptersWithFilter: filter];
}

-(NSArray *)removeAllSceneElements
{
    return [self removeAllAdapters];
}

-(LSFSDKSceneElement *)removeSceneElementWithID: (NSString *)sceneElementID
{
    return [self removeAdapterWithID: sceneElementID];
}

-(LSFSceneElementDataModelV2 *)getModelWithID: (NSString *)sceneElementID
{
    LSFSDKSceneElement *sceneElement = [self getAdapterForID: sceneElementID];
    return (sceneElement != nil ? [sceneElement getSceneElementDataModel] : nil);
}

/*
 * Overriden functions from base class
 */
-(void)sendInitializedEvent: (id<LSFSDKLightingDelegate>)delegate item: (id)item trackingID: (LSFSDKTrackingID *)trackingID
{
    if ([delegate conformsToProtocol: @protocol(LSFSDKSceneElementDelegate)] && [item isKindOfClass: [LSFSDKSceneElement class]])
    {
        id<LSFSDKSceneElementDelegate> sceneElementDelegate = (id<LSFSDKSceneElementDelegate>)delegate;
        LSFSDKSceneElement *sceneElement = (LSFSDKSceneElement *)item;
        [sceneElementDelegate onSceneElementInitializedWithTrackingID: trackingID andSceneElement: sceneElement];
    }
}

-(void)sendChangedEvent: (id<LSFSDKLightingDelegate>)delegate item: (id)item
{
    if ([delegate conformsToProtocol: @protocol(LSFSDKSceneElementDelegate)] && [item isKindOfClass: [LSFSDKSceneElement class]])
    {
        id<LSFSDKSceneElementDelegate> sceneElementDelegate = (id<LSFSDKSceneElementDelegate>)delegate;
        LSFSDKSceneElement *sceneElement = (LSFSDKSceneElement *)item;
        [sceneElementDelegate onSceneElementChanged: sceneElement];
    }
}

-(void)sendRemovedEvent: (id<LSFSDKLightingDelegate>)delegate item: (id)item
{
    if ([delegate conformsToProtocol: @protocol(LSFSDKSceneElementDelegate)] && [item isKindOfClass: [LSFSDKSceneElement class]])
    {
        id<LSFSDKSceneElementDelegate> sceneElementDelegate = (id<LSFSDKSceneElementDelegate>)delegate;
        LSFSDKSceneElement *sceneElement = (LSFSDKSceneElement *)item;
        [sceneElementDelegate onSceneElementRemoved: sceneElement];
    }
}

-(void)sendErrorEvent: (id<LSFSDKLightingDelegate>)delegate errorEvent: (LSFSDKLightingItemErrorEvent *)errorEvent
{
    if ([delegate conformsToProtocol: @protocol(LSFSDKSceneElementDelegate)])
    {
        id<LSFSDKSceneElementDelegate> sceneElementDelegate = (id<LSFSDKSceneElementDelegate>)delegate;
        [sceneElementDelegate onSceneElementError: errorEvent];
    }
}

@end