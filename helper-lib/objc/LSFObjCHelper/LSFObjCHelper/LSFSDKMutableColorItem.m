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

#import "LSFSDKMutableColorItem.h"
#import "model/LSFConstants.h"

@implementation LSFSDKMutableColorItem

-(void)turnOn
{
    [self setPowerOn: YES];
}

-(void)turnOff
{
    [self setPowerOn: NO];
}

-(void)setPower: (Power)power
{
    [self setPowerOn: (power == ON)];
}

-(void)togglePower
{
    [self setPowerOn: [self isOff]];
}

-(void)setColor: (LSFSDKColor *)color
{
    NSString *errorContext = @"LSFSDKMutableColorItem setColor: error";

    if ([self postInvalidArgIfNull: errorContext object: color])
    {
        [self setColorHsvtWithHue: color.hue saturation: color.saturation brightness: color.brightness colorTemp: color.colorTemp];
    }
}

-(void)setColorHsvt: (NSArray *)hsvt
{
    NSString *errorContext = @"LSFSDKMutableColorItem setColorHsvt: error";

    if ([self postInvalidArgIfNull: errorContext object: hsvt])
    {
        if ([hsvt count] != 4)
        {
            [self postInvalidArgIfNull: errorContext object: nil];
        }
        else
        {
            unsigned int hue = [(NSNumber *)[hsvt objectAtIndex: 0] unsignedIntValue];
            unsigned int saturation = [(NSNumber *)[hsvt objectAtIndex: 1] unsignedIntValue];
            unsigned int brightness = [(NSNumber *)[hsvt objectAtIndex: 2] unsignedIntValue];
            unsigned int colorTemp = [(NSNumber *)[hsvt objectAtIndex: 3] unsignedIntValue];

            [self setColorHsvtWithHue: hue saturation: saturation brightness: brightness colorTemp: colorTemp];
        }
    }
}

/*
 * LSFSDKLampState implementation
 */

-(void)setPowerOn: (BOOL)powerOn
{
    @throw [NSException exceptionWithName: NSInternalInconsistencyException reason: [NSString stringWithFormat:@"You must override %@ in a subclass", NSStringFromSelector(_cmd)] userInfo: nil];
}

-(void)setColorHsvtWithHue: (unsigned int)hueDegress saturation: (unsigned int)saturationPercent brightness: (unsigned int)brightnessPercent colorTemp: (unsigned int)colorTempDegrees
{
    @throw [NSException exceptionWithName: NSInternalInconsistencyException reason: [NSString stringWithFormat:@"You must override %@ in a subclass", NSStringFromSelector(_cmd)] userInfo: nil];
}

-(void)setHue:(unsigned int)hueDegrees
{
    @throw [NSException exceptionWithName: NSInternalInconsistencyException reason: [NSString stringWithFormat:@"You must override %@ in a subclass", NSStringFromSelector(_cmd)] userInfo: nil];
}

-(void)setSaturation:(unsigned int)saturationPercent
{
    @throw [NSException exceptionWithName: NSInternalInconsistencyException reason: [NSString stringWithFormat:@"You must override %@ in a subclass", NSStringFromSelector(_cmd)] userInfo: nil];
}

-(void)setBrightness:(unsigned int)brightnessPercent
{
    @throw [NSException exceptionWithName: NSInternalInconsistencyException reason: [NSString stringWithFormat:@"You must override %@ in a subclass", NSStringFromSelector(_cmd)] userInfo: nil];
}

-(void)setColorTemp:(unsigned int)colorTempDegrees
{
    @throw [NSException exceptionWithName: NSInternalInconsistencyException reason: [NSString stringWithFormat:@"You must override %@ in a subclass", NSStringFromSelector(_cmd)] userInfo: nil];
}

@end