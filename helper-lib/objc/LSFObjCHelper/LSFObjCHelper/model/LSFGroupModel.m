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

#import "LSFGroupModel.h"
#import "LSFConstants.h"

@interface LSFGroupModel()

@end

@implementation LSFGroupModel

@synthesize members = _members;
@synthesize lamps = _lamps;
@synthesize groups = _groups;
@synthesize duplicates = _duplicates;
@synthesize delay = _delay;
@synthesize groupColorTempMin = _groupColorTempMin;
@synthesize groupColorTempMax = _groupColorTempMax;

-(id)initWithGroupID: (NSString *)groupID
{
    return [self initWithGroupID: groupID andGroupName: @"[Loading group info...]"];
}

-(id)initWithGroupID: (NSString *)groupID andGroupName:(NSString *)groupName
{
    self = [super initWithID: groupID andName: groupName];
    
    if (self)
    {
        self.members = [[LSFLampGroup alloc] init];
        self.groupColorTempMin = [[LSFConstants getConstants] MIN_COLOR_TEMP];
        self.groupColorTempMax = [[LSFConstants getConstants] MAX_COLOR_TEMP];
    }
    
    return self;
}

-(BOOL)containsLamp:(NSString *)lampID
{
    NSArray *childIDs = [[self members] lamps];

    for (NSString* childID : childIDs)
    {
        if ([childID isEqualToString: lampID])
        {
            return true;
        }
    }

    return false;
}

-(BOOL)containsGroup: (NSString *)groupID
{
    NSArray *childIDs = [[self members] lampGroups];

    for (NSString* childID : childIDs)
    {
        if ([childID isEqualToString: groupID])
        {
            return true;
        }
    }

    return false;
}

-(void)setLamps: (NSSet *)lamps
{
    _lamps = lamps;
    self.capability = [[LSFCapabilityData alloc] init];
}

-(NSSet *)lamps
{
    return _lamps;
}

-(void)setGroups: (NSSet *)groups
{
    _groups = groups;
    self.capability = [[LSFCapabilityData alloc] init];
}

-(NSSet *)groups
{
    return _groups;
}

-(void)setMembers:  (LSFLampGroup *)members
{
    _members = members;
    stateInitialized = YES;
}

-(LSFLampGroup *)members
{
    return _members;
}

-(BOOL)isInitialized
{
    return [super isInitialized];
}

@end