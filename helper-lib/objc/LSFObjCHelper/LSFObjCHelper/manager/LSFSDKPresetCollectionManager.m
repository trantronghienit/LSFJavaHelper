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

#import "LSFSDKPresetCollectionManager.h"
#import "../initializer/LSFSDKPreset+Init.h"

@implementation LSFSDKPresetCollectionManager

-(id)initWithLightingSystemManager: (LSFSDKLightingSystemManager *)lightingSystemManager
{
    self = [super initWithLightingSystemManager: lightingSystemManager];

    if (self)
    {
        //Intentionally left blank
    }

    return self;
}

-(void)addPresetDelegate: (id<LSFSDKPresetDelegate>)presetDelegate
{
    [self addDelegate: presetDelegate];
}

-(void)removePresetDelegate: (id<LSFSDKPresetDelegate>)presetDelegate
{
    [self removeDelegate: presetDelegate];
}

-(LSFSDKPreset *)addPresetWithID: (NSString *)presetID
{
    LSFSDKPreset *newPreset = [[LSFSDKPreset alloc] initWithPresetID: presetID];
    return [self addPresetWithID: presetID preset: newPreset];
}

-(LSFSDKPreset *)addPresetWithID: (NSString *)presetID preset: (LSFSDKPreset *)preset
{
    [itemAdapters setValue: preset forKey: presetID];
    return preset;
}

-(LSFSDKPreset *)getPresetWithID: (NSString *)presetID
{
    return [self getAdapterForID: presetID];
}

-(NSArray *)getPresets
{
    return [self getAdapters];
}

-(NSArray *)getPresetsWithFilter: (id<LSFSDKLightingItemFilter>)filter
{
    return [self getPresetsCollectionWithFilter: filter];
}

-(NSArray *)getPresetsCollectionWithFilter: (id<LSFSDKLightingItemFilter>)filter
{
    return [self getAdaptersWithFilter: filter];
}

-(NSArray *)removeAllPresets
{
    return [self removeAllAdapters];
}

-(LSFSDKPreset *)removePresetWithID: (NSString *)presetID
{
    return [self removeAdapterWithID: presetID];
}

-(LSFPresetModel *)getModelWithID: (NSString *)presetID
{
    LSFSDKPreset *preset = [self getAdapterForID: presetID];
    return (preset != nil ? [preset getPresetDataModel] : nil);
}

/*
 * Overriden functions from base class
 */
-(void)sendInitializedEvent: (id<LSFSDKLightingDelegate>)delegate item: (id)item trackingID: (LSFSDKTrackingID *)trackingID
{
    if ([delegate conformsToProtocol: @protocol(LSFSDKPresetDelegate)] && [item isKindOfClass: [LSFSDKPreset class]])
    {
        id<LSFSDKPresetDelegate> presetDelegate = (id<LSFSDKPresetDelegate>)delegate;
        LSFSDKPreset *preset = (LSFSDKPreset *)item;
        [presetDelegate onPresetInitializedWithTrackingID: trackingID andPreset: preset];
    }
}

-(void)sendChangedEvent: (id<LSFSDKLightingDelegate>)delegate item: (id)item
{
    if ([delegate conformsToProtocol: @protocol(LSFSDKPresetDelegate)] && [item isKindOfClass: [LSFSDKPreset class]])
    {
        id<LSFSDKPresetDelegate> presetDelegate = (id<LSFSDKPresetDelegate>)delegate;
        LSFSDKPreset *preset = (LSFSDKPreset *)item;
        [presetDelegate onPresetChanged: preset];
    }
}

-(void)sendRemovedEvent: (id<LSFSDKLightingDelegate>)delegate item: (id)item
{
    if ([delegate conformsToProtocol: @protocol(LSFSDKPresetDelegate)] && [item isKindOfClass: [LSFSDKPreset class]])
    {
        id<LSFSDKPresetDelegate> presetDelegate = (id<LSFSDKPresetDelegate>)delegate;
        LSFSDKPreset *preset = (LSFSDKPreset *)item;
        [presetDelegate onPresetRemoved: preset];
    }
}

-(void)sendErrorEvent: (id<LSFSDKLightingDelegate>)delegate errorEvent: (LSFSDKLightingItemErrorEvent *)errorEvent
{
    if ([delegate conformsToProtocol: @protocol(LSFSDKPresetDelegate)])
    {
        id<LSFSDKPresetDelegate> presetDelegate = (id<LSFSDKPresetDelegate>)delegate;
        [presetDelegate onPresetError: errorEvent];
    }
}

@end