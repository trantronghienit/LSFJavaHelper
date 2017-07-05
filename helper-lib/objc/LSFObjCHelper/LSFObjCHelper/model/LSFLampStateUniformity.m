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

#import "LSFLampStateUniformity.h"

@implementation LSFLampStateUniformity

@synthesize power = _power;
@synthesize brightness = _brightness;
@synthesize hue = _hue;
@synthesize saturation = _saturation;
@synthesize colorTemp = _colorTemp;

-(id)init
{
    return [self initWithPower: YES brightness: YES hue: YES saturation: YES andColorTemp: YES];
}

-(id)initWithLampStateUniformity: (LSFLampStateUniformity *)other
{
    return [self initWithPower: other.power brightness:other.brightness hue:other.hue saturation:other.saturation andColorTemp:other.colorTemp];
}

-(id)initWithPower: (BOOL)power brightness: (BOOL)brightness hue: (BOOL)hue saturation: (BOOL)saturation andColorTemp: (BOOL)colorTemp
{
    self = [super init];

    if (self)
    {
        self.power = power;
        self.brightness = brightness;
        self.hue = hue;
        self.saturation = saturation;
        self.colorTemp = colorTemp;
    }

    return self;
}

@end