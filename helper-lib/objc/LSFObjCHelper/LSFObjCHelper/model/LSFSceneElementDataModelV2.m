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

#import "LSFSceneElementDataModelV2.h"

const NSString *SCENE_ELEMENT_DEFAULT_NAME = @"<Loading scene element info...>";

@implementation LSFSceneElementDataModelV2

@synthesize lamps = _lamps;
@synthesize groups = _groups;
@synthesize effectID = _effectID;

-(id)init
{
    return [self initWithSceneElementID: nil];
}

-(id)initWithSceneElementID: (NSString *)sceneElementID
{
    return [self initWithSceneElementID: sceneElementID andSceneElementName: nil];
}

-(id)initWithSceneElementID: (NSString *)sceneElementID andSceneElementName: (NSString *)sceneElementName
{
    self = [super initWithID: sceneElementID andName: (sceneElementName != nil ? sceneElementName : SCENE_ELEMENT_DEFAULT_NAME)];

    if (self)
    {
        self.lamps = [[NSSet alloc] init];
        self.groups = [[NSSet alloc] init];
        self.effectID = nil;

        lampsInitialized = NO;
        groupsInitialized = NO;
        effectIDInitialized = NO;
    }

    return self;
}

-(BOOL)containsLamp: (NSString *)lampID
{
    return [self.lamps containsObject: lampID];
}

-(BOOL)containsGroup: (NSString *)groupID
{
    return [self.groups containsObject: groupID];
}

-(BOOL)contaisnEffect: (NSString *)effectID
{
    return [effectID isEqualToString: self.effectID];
}

-(void)setLamps: (NSSet *)lamps
{
    _lamps = lamps;
    lampsInitialized = YES;
}

-(NSSet *)lamps
{
    return _lamps;
}

-(void)setGroups: (NSSet *)groups
{
    _groups = groups;
    groupsInitialized = YES;
}

-(NSSet *)groups
{
    return _groups;
}

-(void)setEffectID: (NSString *)effectID
{
    _effectID = effectID;
    effectIDInitialized = YES;
}

-(NSString *)effectID
{
    return _effectID;
}

-(BOOL)isInitialized
{
    return ([super isInitialized] && lampsInitialized && groupsInitialized && effectIDInitialized);
}

@end