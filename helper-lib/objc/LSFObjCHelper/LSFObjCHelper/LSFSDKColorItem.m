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

#import "LSFSDKColorItem.h"

@implementation LSFSDKColorItem

-(NSArray *)getColorHsvt
{
    return [NSArray arrayWithObjects: [NSNumber numberWithUnsignedInt: [[[self getColorDataModel] state] hue]], [NSNumber numberWithUnsignedInt: [[[self getColorDataModel] state] saturation]], [NSNumber numberWithUnsignedInt: [[[self getColorDataModel] state] brightness]], [NSNumber numberWithUnsignedInt: [[[self getColorDataModel] state] colorTemp]], nil];
}

-(BOOL)getPowerOn
{
    return [[[self getColorDataModel] state] onOff];
}

-(BOOL)isOn
{
    return [self getPowerOn];
}

-(BOOL)isOff
{
    return ![self isOn];
}

-(Power)getPower
{
    return (([self getPowerOn]) ? ON : OFF);
}

-(LSFSDKColor *)getColor
{
    return [[LSFSDKColor alloc] initWithHsvt: [self getColorHsvt]];
}

-(LSFSDKMyLampState *)getState
{
    return [[LSFSDKMyLampState alloc] initWithPower: [self getPower] color: [self getColor]];
}

-(LSFSDKLampStateUniformity *)getUniformity
{
    return [[LSFSDKLampStateUniformity alloc] initWithLampStateUniformity: [[self getColorDataModel] uniformity]];
}

-(LSFSDKCapabilityData *)getCapabilities
{
    return [[LSFSDKCapabilityData alloc] initWithCapabilityData: [[self getColorDataModel] capability]];
}

-(void)setCapabilities: (LSFSDKCapabilityData *)capabilityData
{
    [self getColorDataModel].capability = capabilityData;
}

-(LSFDataModel *)getColorDataModel;
{
    @throw [NSException exceptionWithName: NSInternalInconsistencyException reason: [NSString stringWithFormat:@"You must override %@ in a subclass", NSStringFromSelector(_cmd)] userInfo: nil];
}

/*
 * Overriden functions from base class
 */
-(LSFModel *)getItemDataModel
{
    return [self getColorDataModel];
}

@end