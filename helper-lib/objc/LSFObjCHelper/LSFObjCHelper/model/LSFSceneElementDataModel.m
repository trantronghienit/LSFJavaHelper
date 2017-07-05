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

#import "LSFSceneElementDataModel.h"

static int nextID = 1;

@implementation LSFSceneElementDataModel

@synthesize type = _type;
@synthesize members = _members;
@synthesize colorTempMin = _colorTempMin;
@synthesize colorTempMax = _colorTempMax;

-(id)initWithEffectType: (EffectType)type andName: (NSString *)name
{
    self = [super initWithID: [NSString stringWithFormat: @"%i", nextID++] andName: name];

    if (self)
    {
        self.type = type;
        self.members = [[LSFLampGroup alloc] init];

        // Lamp state should always be set to "ON". Turning the lamp off as part
        // of an effect requires setting the brightness to zero.
        self.state.onOff = YES;

        self.capability.dimmable = ALL;
        self.capability.color = ALL;
        self.capability.temp = ALL;

        self.colorTempMin = -1;
        self.colorTempMax = -1;
    }

    return self;
}

-(BOOL)containsGroup: (NSString *)groupID
{
    NSArray *childIDs = [self.members lampGroups];

    for (NSString *childID in childIDs)
    {
        if ([childID isEqualToString: groupID])
        {
            return YES;
        }
    }

    return NO;
}

-(BOOL) containsPreset:(NSString *)presetID
{
    return NO;
}

@end