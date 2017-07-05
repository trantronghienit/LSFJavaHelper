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

#import "LSFSDKLampCollectionManager.h"
#import "../initializer/LSFSDKLamp+Init.h"

@implementation LSFSDKLampCollectionManager

-(id)initWithLightingSystemManager: (LSFSDKLightingSystemManager *)lightingSystemManager
{
    self = [super initWithLightingSystemManager: lightingSystemManager];

    if (self)
    {
        lampIDs = [[NSMutableArray alloc] init];
    }

    return self;
}

-(void)addLampDelegate: (id<LSFSDKLampDelegate>)lampDelegate
{
    [self addDelegate: lampDelegate];
}

-(void)removeLampDelegate: (id<LSFSDKLampDelegate>)lampDelegate
{
    [self removeDelegate: lampDelegate];
}

-(LSFSDKLamp *)addLampWithID: (NSString *)lampID
{
    LSFSDKLamp *newLamp = [[LSFSDKLamp alloc] initWithLampID: lampID];
    return [self addLampWithID: lampID lamp: newLamp];
}

-(LSFSDKLamp *)addLampWithID: (NSString *)lampID lamp: (LSFSDKLamp *)lamp
{
    [itemAdapters setValue: lamp forKey: lampID];
    [lampIDs addObject: lampID];
    return lamp;
}

-(LSFSDKLamp *)getLampWithID: (NSString *)lampID
{
    return [self getAdapterForID: lampID];
}

-(NSArray *)getLamps
{
    return [self getAdapters];
}

-(NSArray *)getLampsWithFilter: (id<LSFSDKLightingItemFilter>)filter
{
    return [self getLampsCollectionWithFilter: filter];
}

-(NSArray *)getLampsCollectionWithFilter: (id<LSFSDKLightingItemFilter>)filter
{
    return [self getAdaptersWithFilter: filter];
}

-(NSArray *)removeAllLamps
{
    return [self removeAllAdapters];
}

-(LSFSDKLamp *)removeLampWithID: (NSString *)lampID
{
    [lampIDs removeObject: lampID];
    return [self removeAdapterWithID: lampID];
}

-(LSFLampModel *)getModelWithID: (NSString *)lampID
{
    LSFSDKLamp *lamp = [self getAdapterForID: lampID];
    return (lamp != nil ? [lamp getLampDataModel] : nil);
}

/*
 * Overriden functions from base class
 */
-(void)sendInitializedEvent: (id<LSFSDKLightingDelegate>)delegate item: (id)item trackingID: (LSFSDKTrackingID *)trackingID
{
    if ([delegate conformsToProtocol: @protocol(LSFSDKLampDelegate)] && [item isKindOfClass: [LSFSDKLamp class]])
    {
        id<LSFSDKLampDelegate> lampDelegate = (id<LSFSDKLampDelegate>)delegate;
        LSFSDKLamp *lamp = (LSFSDKLamp *)item;
        [lampDelegate onLampInitialized: lamp];
    }
}

-(void)sendChangedEvent: (id<LSFSDKLightingDelegate>)delegate item: (id)item
{
    if ([delegate conformsToProtocol: @protocol(LSFSDKLampDelegate)] && [item isKindOfClass: [LSFSDKLamp class]])
    {
        id<LSFSDKLampDelegate> lampDelegate = (id<LSFSDKLampDelegate>)delegate;
        LSFSDKLamp *lamp = (LSFSDKLamp *)item;
        [lampDelegate onLampChanged: lamp];
    }
}

-(void)sendRemovedEvent: (id<LSFSDKLightingDelegate>)delegate item: (id)item
{
    if ([delegate conformsToProtocol: @protocol(LSFSDKLampDelegate)] && [item isKindOfClass: [LSFSDKLamp class]])
    {
        id<LSFSDKLampDelegate> lampDelegate = (id<LSFSDKLampDelegate>)delegate;
        LSFSDKLamp *lamp = (LSFSDKLamp *)item;
        [lampDelegate onLampRemoved: lamp];
    }
}

-(void)sendErrorEvent: (id<LSFSDKLightingDelegate>)delegate errorEvent: (LSFSDKLightingItemErrorEvent *)errorEvent
{
    if ([delegate conformsToProtocol: @protocol(LSFSDKLampDelegate)])
    {
        id<LSFSDKLampDelegate> lampDelegate = (id<LSFSDKLampDelegate>)delegate;
        [lampDelegate onLampError: errorEvent];
    }
}

@end