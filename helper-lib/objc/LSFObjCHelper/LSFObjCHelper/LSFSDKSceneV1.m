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

#import "LSFSDKSceneV1.h"
#import "LSFSDKLightingDirector.h"

@implementation LSFSDKSceneV1

-(BOOL)hasPreset: (LSFSDKPreset *)preset
{
    NSString *errorContext = @"LSFSDKSceneV1 hasPreset: error";
    return ([self postInvalidArgIfNull: errorContext object: preset]) ? [self hasPresetWithID: preset.theID] : NO;
}

-(BOOL)hasGroup: (LSFSDKGroup *)group;
{
    NSString *errorContext = @"LSFSDKSceneV1 hasGroup: error";
    return ([self postInvalidArgIfNull: errorContext object: group]) ? [self hasPresetWithID: group.theID] : NO;
}

-(BOOL)hasPresetWithID: (NSString *)presetID;
{
    return [sceneModel containsPreset: presetID];
}

-(BOOL)hasGroupWithID: (NSString *)groupID
{
    return [sceneModel containsGroup: groupID];
}

/*
 * Override base class functions
 */
-(LSFModel *)getItemDataModel
{
    return [self getSceneDataModel];
}

-(BOOL)hasComponent:(LSFSDKLightingItem *)item
{
    NSString *errorContext = @"LSFSDKSceneV1 hasComponent: error";
    return ([self postInvalidArgIfNull: errorContext object: item]) ? ([self hasPresetWithID: item.theID] || [self hasGroupWithID: item.theID]): NO;
}

-(void)postError:(NSString *)name status:(LSFResponseCode)status
{
    dispatch_async([[[LSFSDKLightingDirector getLightingDirector] lightingManager] dispatchQueue], ^{
        [[[[LSFSDKLightingDirector getLightingDirector] lightingManager] sceneCollectionManagerV1] sendErrorEvent: name statusCode: status itemID: sceneModel.theID];
    });
}

/*
 * Note: This method is not intended to be used by clients, and may change or be
 * removed in subsequent releases of the SDK.
 */
-(LSFSceneDataModel *)getSceneDataModel
{
    return sceneModel;
}

@end