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

#import "LSFSDKPulseEffectCollectionManager.h"
#import "../initializer/LSFSDKPulseEffect+Init.h"

@implementation LSFSDKPulseEffectCollectionManager

-(id)initWithLightingSystemManager: (LSFSDKLightingSystemManager *)lightingSystemManager
{
    self = [super initWithLightingSystemManager: lightingSystemManager];

    if (self)
    {
        //Intentionally left blank
    }

    return self;
}

-(void)addPulseEffectDelegate: (id<LSFSDKPulseEffectDelegate>)pulseEffectDelegate
{
    [self addDelegate: pulseEffectDelegate];
}

-(void)removePulseEffectDelegate: (id<LSFSDKPulseEffectDelegate>)pulseEffectDelegate
{
    [self removeDelegate: pulseEffectDelegate];
}

-(LSFSDKPulseEffect *)addPulseEffectWithID: (NSString *)pulseEffectID
{
    LSFSDKPulseEffect *newPulseEffect = [[LSFSDKPulseEffect alloc] initWithPulseEffectID: pulseEffectID];
    return [self addPulseEffectWithID: pulseEffectID pulseEffect: newPulseEffect];
}

-(LSFSDKPulseEffect *)addPulseEffectWithID: (NSString *)pulseEffectID pulseEffect: (LSFSDKPulseEffect *)pulseEffect
{
    [itemAdapters setValue: pulseEffect forKey: pulseEffectID];
    return pulseEffect;
}

-(LSFSDKPulseEffect *)getPulseEffectWithID: (NSString *)pulseEffectID
{
    return [self getAdapterForID: pulseEffectID];
}

-(NSArray *)getPulseEffects
{
    return [self getAdapters];
}

-(NSArray *)getPulseEffectsWithFilter: (id<LSFSDKLightingItemFilter>)filter
{
    return [self getPulseEffectsCollectionWithFilter: filter];
}

-(NSArray *)getPulseEffectsCollectionWithFilter: (id<LSFSDKLightingItemFilter>)filter
{
    return [self getAdaptersWithFilter: filter];
}

-(NSArray *)removeAllPulseEffects
{
    return [self removeAllAdapters];
}

-(LSFSDKPulseEffect *)removePulseEffectWithID: (NSString *)pulseEffectID
{
    return [self removeAdapterWithID: pulseEffectID];
}

-(LSFPulseEffectDataModelV2 *)getModelWithID: (NSString *)pulseEffectID
{
    LSFSDKPulseEffect *pulseEffect = [self getAdapterForID: pulseEffectID];
    return (pulseEffect != nil ? [pulseEffect getPulseEffectDataModel] : nil);
}

/*
 * Overriden functions from base class
 */
-(void)sendInitializedEvent: (id<LSFSDKLightingDelegate>)delegate item: (id)item trackingID: (LSFSDKTrackingID *)trackingID
{
    if ([delegate conformsToProtocol: @protocol(LSFSDKPulseEffectDelegate)] && [item isKindOfClass: [LSFSDKPulseEffect class]])
    {
        id<LSFSDKPulseEffectDelegate> pulseEffectDelegate = (id<LSFSDKPulseEffectDelegate>)delegate;
        LSFSDKPulseEffect *pulseEffect = (LSFSDKPulseEffect *)item;
        [pulseEffectDelegate onPulseEffectInitializedWithTrackingID: trackingID andPulseEffect: pulseEffect];
    }
}

-(void)sendChangedEvent: (id<LSFSDKLightingDelegate>)delegate item: (id)item
{
    if ([delegate conformsToProtocol: @protocol(LSFSDKPulseEffectDelegate)] && [item isKindOfClass: [LSFSDKPulseEffect class]])
    {
        id<LSFSDKPulseEffectDelegate> pulseEffectDelegate = (id<LSFSDKPulseEffectDelegate>)delegate;
        LSFSDKPulseEffect *pulseEffect = (LSFSDKPulseEffect *)item;
        [pulseEffectDelegate onPulseEffectChanged: pulseEffect];
    }
}

-(void)sendRemovedEvent: (id<LSFSDKLightingDelegate>)delegate item: (id)item
{
    if ([delegate conformsToProtocol: @protocol(LSFSDKPulseEffectDelegate)] && [item isKindOfClass: [LSFSDKPulseEffect class]])
    {
        id<LSFSDKPulseEffectDelegate> pulseEffectDelegate = (id<LSFSDKPulseEffectDelegate>)delegate;
        LSFSDKPulseEffect *pulseEffect = (LSFSDKPulseEffect *)item;
        [pulseEffectDelegate onPulseEffectRemoved: pulseEffect];
    }
}

-(void)sendErrorEvent: (id<LSFSDKLightingDelegate>)delegate errorEvent: (LSFSDKLightingItemErrorEvent *)errorEvent
{
    if ([delegate conformsToProtocol: @protocol(LSFSDKPulseEffectDelegate)])
    {
        id<LSFSDKPulseEffectDelegate> pulseEffectDelegate = (id<LSFSDKPulseEffectDelegate>)delegate;
        [pulseEffectDelegate onPulseEffectError: errorEvent];
    }
}

@end