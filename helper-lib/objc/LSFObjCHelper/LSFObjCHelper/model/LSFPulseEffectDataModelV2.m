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

#import "LSFPulseEffectDataModelV2.h"

const NSString *PULSE_EFFECT_DEFAULT_NAME = @"<Loading pulse effect info...>";
const unsigned int PULSE_EFFECT_DEFAULT_PERIOD = 1000;
const unsigned int PULSE_EFFECT_DEFAULT_DURATION = 500;
const unsigned int PULSE_EFFECT_DEFAULT_COUNT = 10;

@implementation LSFPulseEffectDataModelV2

@synthesize endState = _endState;
@synthesize startPresetID = _startPresetID;
@synthesize endPresetID = _endPresetID;
@synthesize period = _period;
@synthesize duration = _duration;
@synthesize count = _count;
@synthesize startWithCurrent = _startWithCurrent;

-(id)init
{
    return [self initWithPulseEffectID: nil];
}

-(id)initWithPulseEffectID: (NSString *)pulseEffectID
{
    return [self initWithPulseEffectID: pulseEffectID andPulseEffectName: nil];
}

-(id)initWithPulseEffectID: (NSString *)pulseEffectID andPulseEffectName: (NSString *)pulseEffectName
{
    self = [super initWithID: pulseEffectID andName: (pulseEffectID != nil ? pulseEffectName : PULSE_EFFECT_DEFAULT_NAME)];

    if (self)
    {
        self.endState = [[LSFLampState alloc] init];
        self.startPresetID = nil;
        self.endPresetID = nil;
        self.period = PULSE_EFFECT_DEFAULT_PERIOD;
        self.duration = PULSE_EFFECT_DEFAULT_DURATION;
        self.count = PULSE_EFFECT_DEFAULT_COUNT;
        self.startWithCurrent = NO;

        endStateInitialized = NO;
        periodInitialized = NO;
        durationInitialized = NO;
        countInitialized = NO;
    }

    return self;
}

-(BOOL)containsPreset: (NSString *) presetID;
{
    return [presetID isEqualToString: [self startPresetID]] || [presetID isEqualToString: [self endPresetID]];
}

-(void)setEndState: (LSFLampState *)endState
{
    _endState = endState;
    endStateInitialized = YES;
}

-(LSFLampState *)endState
{
    return _endState;
}

-(void)setStartPresetID: (NSString *)startPresetID
{
    _startPresetID = startPresetID;
    stateInitialized = YES;
}

-(NSString *)startPresetID
{
    return _startPresetID;
}

-(void)setEndPresetID: (NSString *)endPresetID
{
    _endPresetID = endPresetID;
    endStateInitialized = YES;
}

-(NSString *)endPresetID
{
    return _endPresetID;
}

-(void)setPeriod: (unsigned int)period
{
    _period = period;
    periodInitialized = YES;
}

-(unsigned int)period
{
    return _period;
}

-(void)setDuration: (unsigned int)duration
{
    _duration = duration;
    durationInitialized = YES;
}

-(unsigned int)duration
{
    return _duration;
}

-(void)setCount: (unsigned int)count
{
    _count = count;
    countInitialized = YES;
}

-(unsigned int)count
{
    return _count;
}

-(BOOL)isInitialized
{
    return ([super isInitialized] && stateInitialized && endStateInitialized && periodInitialized && durationInitialized && countInitialized);
}

@end