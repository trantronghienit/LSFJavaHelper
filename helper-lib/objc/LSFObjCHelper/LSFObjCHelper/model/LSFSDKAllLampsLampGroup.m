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

#import "LSFSDKAllLampsLampGroup.h"

@implementation LSFSDKAllLampsLampGroup

+(LSFSDKAllLampsLampGroup *)getInstance
{
    static LSFSDKAllLampsLampGroup *instance = nil;
    static dispatch_once_t onceToken;

    dispatch_once(&onceToken, ^{
        instance = [[self alloc] init];
    });

    return instance;
}

-(id)init
{
    self = [super init];

    if (self)
    {
        manager = nil;
        emptyArray = [[NSArray alloc] init];
    }

    return self;
}

/*
 *
 */
-(void)setLightingSystemManager: (LSFSDKLightingSystemManager *)lsm
{
    manager = lsm;
}

/*
 * Override base class functions
 */
-(void)setLamps: (NSArray *)lamps
{
    NSLog(@"LSFAllLampsLampGroup - Invalid attempt to set lamp members of the all-lamps lamp group");
}

-(NSArray *)lamps
{
    if (manager != nil)
    {
        NSArray *lamps = [manager.lampCollectionManager getLamps];
        NSMutableArray *lampIDs = [[NSMutableArray alloc] init];

        for (LSFSDKLamp *lamp in lamps)
        {
            [lampIDs addObject: [lamp theID]];
        }

        return lampIDs;
    }

    return emptyArray;
}

-(void)setLampGroups: (NSArray *)lampGroups
{
    NSLog(@"LSFAllLampsLampGroup - Invalid attempt to set group members of the all-lamps lamp group");
}

-(NSArray *)lampGroups
{
    return emptyArray;
}

-(LSFResponseCode)isDependentLampGroup: (NSString *)groupID
{
    return [super isDependentLampGroup: groupID];
}

@end