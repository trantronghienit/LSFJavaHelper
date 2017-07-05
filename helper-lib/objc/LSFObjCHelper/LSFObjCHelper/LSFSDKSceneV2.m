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

#import "LSFSDKSceneV2.h"
#import "LSFSDKLightingItemUtil.h"
#import "LSFSDKLightingDirector.h"
#import "manager/LSFSDKAllJoynManager.h"
#import "model/LSFSDKLightingItemHasComponentFilter.h"

@implementation LSFSDKSceneV2

-(void)modify: (NSArray *)sceneElements
{
    NSString *errorContext = @"LSFSDKSceneV2 modify: error";

    if ([self postInvalidArgIfNull: errorContext object: sceneElements])
    {
        NSMutableArray *sceneElementIDs = [[NSMutableArray alloc] init];
        for (LSFSDKSceneElement *sceneElement in sceneElements)
        {
            [sceneElementIDs addObject: [sceneElement theID]];
        }

        [self postErrorIfFailure: errorContext status: [[LSFSDKAllJoynManager getSceneManager] updateSceneWithSceneElementsWithID: sceneModel.theID withSceneWithSceneElements: [LSFSDKLightingItemUtil createSceneWithSceneElements: sceneElementIDs]]];
    }
}

-(void)add: (LSFSDKSceneElement *)sceneElement
{
    NSString *errorContext = @"LSFSDKSceneV2 add: error";

    if ([self postInvalidArgIfNull: errorContext object: sceneElement])
    {
        NSMutableSet *sceneElementIDs = [[NSMutableSet alloc] initWithArray: [[sceneModel sceneWithSceneElements] sceneElements]];
        [sceneElementIDs addObject: [sceneElement theID]];

        [self postErrorIfFailure: errorContext status: [[LSFSDKAllJoynManager getSceneManager] updateSceneWithSceneElementsWithID: sceneModel.theID withSceneWithSceneElements: [LSFSDKLightingItemUtil createSceneWithSceneElements: [sceneElementIDs allObjects]]]];
    }
}

-(void)remove: (LSFSDKSceneElement *)sceneElement
{
    NSString *errorContext = @"LSFSDKSceneV2 remove: error";

    if ([self postInvalidArgIfNull: errorContext object: sceneElement])
    {
        NSMutableSet *sceneElementIDs = [[NSMutableSet alloc] initWithArray: [[sceneModel sceneWithSceneElements] sceneElements]];
        [sceneElementIDs removeObject: [sceneElement theID]];

        [self postErrorIfFailure: errorContext status: [[LSFSDKAllJoynManager getSceneManager] updateSceneWithSceneElementsWithID: sceneModel.theID withSceneWithSceneElements: [LSFSDKLightingItemUtil createSceneWithSceneElements: [sceneElementIDs allObjects]]]];
    }
}

-(BOOL)hasSceneElement:(LSFSDKSceneElement *)sceneElement
{
    NSString *errorContext = @"LSFSDKSceneV2 hasSceneElement: error";
    return ([self postInvalidArgIfNull: errorContext object: sceneElement]) ? [self hasSceneElementWithID: sceneElement.theID] : NO;
}

-(BOOL)hasSceneElementWithID:(NSString *)sceneElementID
{
    return [sceneModel containsSceneElement: sceneElementID];
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
    NSString *errorContext = @"LSFSDKSceneV2 hasComponent: error";
    return ([self postInvalidArgIfNull: errorContext object: item]) ? [self hasSceneElementWithID: item.theID] : NO;
}

-(NSArray *)getSceneElementIDs
{
    return sceneModel.sceneWithSceneElements.sceneElements;
}

-(NSArray *)getSceneElements
{
    NSMutableArray *collection = [[NSMutableArray alloc] init];

    NSArray *sceneElementIDs = [self getSceneElementIDs];
    for (NSString *sceneElementID in sceneElementIDs)
    {
        LSFSDKSceneElement *sceneElement = [[LSFSDKLightingDirector getLightingDirector] getSceneElementWithID: sceneElementID];
        if (sceneElement != nil)
        {
            [collection addObject: sceneElement];
        }
    }

    return collection;
}

-(NSArray *)getComponentCollection
{
    return [self getSceneElements];
}

-(void)postError:(NSString *)name status:(LSFResponseCode)status
{
    dispatch_async([[[LSFSDKLightingDirector getLightingDirector] lightingManager] dispatchQueue], ^{
        [[[[LSFSDKLightingDirector getLightingDirector] lightingManager] sceneCollectionManager] sendErrorEvent: name statusCode: status itemID: sceneModel.theID];
    });
}

/*
 * Note: This method is not intended to be used by clients, and may change or be
 * removed in subsequent releases of the SDK.
 */
-(LSFSceneDataModelV2 *)getSceneDataModel
{
    return sceneModel;
}

@end