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

#import <Foundation/Foundation.h>
#import "LSFDataModel.h"

extern const NSString *PULSE_EFFECT_DEFAULT_NAME;
extern const unsigned int PULSE_EFFECT_DEFAULT_PERIOD;
extern const unsigned int PULSE_EFFECT_DEFAULT_DURATION;
extern const unsigned int PULSE_EFFECT_DEFAULT_COUNT;

@interface LSFPulseEffectDataModelV2 : LSFDataModel
{
    @protected BOOL endStateInitialized;
    @protected BOOL periodInitialized;
    @protected BOOL durationInitialized;
    @protected BOOL countInitialized;
}

@property (nonatomic, strong) LSFLampState *endState;
@property (nonatomic, strong) NSString *startPresetID;
@property (nonatomic, strong) NSString *endPresetID;
@property (nonatomic) unsigned int period;
@property (nonatomic) unsigned int duration;
@property (nonatomic) unsigned int count;
@property (nonatomic) BOOL startWithCurrent;

-(id)init;
-(id)initWithPulseEffectID: (NSString *)pulseEffectID;
-(id)initWithPulseEffectID: (NSString *)pulseEffectID andPulseEffectName: (NSString *)pulseEffectName;
-(BOOL)containsPreset: (NSString *) presetID;

@end