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

#import "LSFSDKTransitionEffectCollectionManager.h"
#import "../initializer/LSFSDKTransitionEffect+Init.h"

@implementation LSFSDKTransitionEffectCollectionManager

-(id)initWithLightingSystemManager: (LSFSDKLightingSystemManager *)lightingSystemManager
{
    self = [super initWithLightingSystemManager: lightingSystemManager];

    if (self)
    {
        //Intentionally left blank
    }

    return self;
}

-(void)addTransitionEffectDelegate: (id<LSFSDKTransitionEffectDelegate>)transitionEffectDelegate
{
    [self addDelegate: transitionEffectDelegate];
}

-(void)removeTransitionEffectDelegate: (id<LSFSDKTransitionEffectDelegate>)transitionEffectDelegate
{
    [self removeDelegate: transitionEffectDelegate];
}

-(LSFSDKTransitionEffect *)addTransitionEffectWithID: (NSString *)transitionEffectID
{
    LSFSDKTransitionEffect *newTransitionEffect = [[LSFSDKTransitionEffect alloc] initWithTransitionEffectID: transitionEffectID];
    return [self addTransitionEffectWithID: transitionEffectID transitionEffect: newTransitionEffect];
}

-(LSFSDKTransitionEffect *)addTransitionEffectWithID: (NSString *)transitionEffectID transitionEffect: (LSFSDKTransitionEffect *)transitionEffect
{
    [itemAdapters setValue: transitionEffect forKey: transitionEffectID];
    return transitionEffect;
}

-(LSFSDKTransitionEffect *)getTransitionEffectWithID: (NSString *)transitionEffectID
{
    return [self getAdapterForID: transitionEffectID];
}

-(NSArray *)getTransitionEffects
{
    return [self getAdapters];
}

-(NSArray *)getTransitionEffectsWithFilter: (id<LSFSDKLightingItemFilter>)filter
{
    return [self getTransitionEffectCollectionWithFilter: filter];
}

-(NSArray *)getTransitionEffectCollectionWithFilter: (id<LSFSDKLightingItemFilter>)filter
{
    return [self getAdaptersWithFilter: filter];
}

-(NSArray *)removeAllTransitionEffects
{
    return [self removeAllAdapters];
}

-(LSFSDKTransitionEffect *)removeTransitionEffectWithID: (NSString *)transitionEffectID
{
    return [self removeAdapterWithID: transitionEffectID];
}

-(LSFTransitionEffectDataModelV2 *)getModelWithID: (NSString *)transitionEffectID
{
    LSFSDKTransitionEffect *transitionEffect = [self getAdapterForID: transitionEffectID];
    return (transitionEffect != nil ? [transitionEffect getTransitionEffectDataModel] : nil);
}

/*
 * Overriden functions from base class
 */
-(void)sendInitializedEvent: (id<LSFSDKLightingDelegate>)delegate item: (id)item trackingID: (LSFSDKTrackingID *)trackingID
{
    if ([delegate conformsToProtocol: @protocol(LSFSDKTransitionEffectDelegate)] && [item isKindOfClass: [LSFSDKTransitionEffect class]])
    {
        id<LSFSDKTransitionEffectDelegate> transitionEffectDelegate = (id<LSFSDKTransitionEffectDelegate>)delegate;
        LSFSDKTransitionEffect *transitionEffect = (LSFSDKTransitionEffect *)item;
        [transitionEffectDelegate onTransitionEffectInitializedWithTrackingID: trackingID andTransitionEffect: transitionEffect];
    }
}

-(void)sendChangedEvent: (id<LSFSDKLightingDelegate>)delegate item: (id)item
{
    if ([delegate conformsToProtocol: @protocol(LSFSDKTransitionEffectDelegate)] && [item isKindOfClass: [LSFSDKTransitionEffect class]])
    {
        id<LSFSDKTransitionEffectDelegate> transitionEffectDelegate = (id<LSFSDKTransitionEffectDelegate>)delegate;
        LSFSDKTransitionEffect *transitionEffect = (LSFSDKTransitionEffect *)item;
        [transitionEffectDelegate onTransitionEffectChanged: transitionEffect];
    }
}

-(void)sendRemovedEvent: (id<LSFSDKLightingDelegate>)delegate item: (id)item
{
    if ([delegate conformsToProtocol: @protocol(LSFSDKTransitionEffectDelegate)] && [item isKindOfClass: [LSFSDKTransitionEffect class]])
    {
        id<LSFSDKTransitionEffectDelegate> transitionEffectDelegate = (id<LSFSDKTransitionEffectDelegate>)delegate;
        LSFSDKTransitionEffect *transitionEffect = (LSFSDKTransitionEffect *)item;
        [transitionEffectDelegate onTransitionEffectRemoved: transitionEffect];
    }
}

-(void)sendErrorEvent: (id<LSFSDKLightingDelegate>)delegate errorEvent: (LSFSDKLightingItemErrorEvent *)errorEvent
{
    if ([delegate conformsToProtocol: @protocol(LSFSDKTransitionEffectDelegate)])
    {
        id<LSFSDKTransitionEffectDelegate> transitionEffectDelegate = (id<LSFSDKTransitionEffectDelegate>)delegate;
        [transitionEffectDelegate onTransitionEffectError: errorEvent];
    }
}

@end