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

#import "LSFSceneDataModelV2.h"

const NSString *SCENE_DEFAULT_NAME = @"<Loading scene info...>";

@implementation LSFSceneDataModelV2

@synthesize sceneWithSceneElements = _sceneWithSceneElements;

-(id)init
{
    return [self initWithSceneID: nil];
}

-(id)initWithSceneID: (NSString *)sceneID
{
    return [self initWithSceneID: sceneID sceneName: nil];
}

-(id)initWithSceneID: (NSString *)sceneID sceneName: (NSString *)sceneName
{
    self = [super initWithID: sceneID andName: (sceneName != nil ? sceneName : SCENE_DEFAULT_NAME)];

    if (self)
    {
        self.sceneWithSceneElements = nil;
        sceneWithSceneElementsInitialized = NO;
    }

    return self;
}

-(BOOL)containsSceneElement: (NSString *)sceneElementID
{
    NSArray *childIDs = [self.sceneWithSceneElements sceneElements];

    for (NSString *childID in childIDs)
    {
        if ([childID isEqualToString: sceneElementID])
        {
            return true;
        }
    }

    return false;
}

-(void)setSceneWithSceneElements: (LSFSceneWithSceneElements *)sceneWithSceneElements
{
    _sceneWithSceneElements = sceneWithSceneElements;
    sceneWithSceneElementsInitialized = YES;
}

-(LSFSceneWithSceneElements *)sceneWithSceneElements
{
    return _sceneWithSceneElements;
}

-(BOOL)isInitialized
{
    return ([super isInitialized] && sceneWithSceneElementsInitialized);
}

@end