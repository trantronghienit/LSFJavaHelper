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

#import "LSFSDKPreset.h"
#import "LSFSDKAllJoynManager.h"
#import "LSFSDKLightingItemUtil.h"
#import "LSFSDKLightingDirector.h"
#import "LSFSDKLightingItemHasComponentFilter.h"
#import "LSFConstants.h"

@implementation LSFSDKPreset

-(void)modifyWithPower: (Power)power color: (LSFSDKColor *)color
{
    NSString *errorContext = @"LSFSDKPreset modify: error";

    if ([self postInvalidArgIfNull: errorContext object: color])
    {
        [self postErrorIfFailure: errorContext status: [[LSFSDKAllJoynManager getPresetManager] updatePresetWithID: presetModel.theID andState: [LSFSDKLightingItemUtil createLampStateFromPower: power hue: color.hue saturation: color.saturation brightness: color.brightness colorTemp: color.colorTemp]]];
    }
}

-(void)deleteItem
{
    NSString *errorContext = @"LSFSDKPreset rename: error";

    [self postErrorIfFailure: errorContext status: [[LSFSDKAllJoynManager getPresetManager] deletePresetWithID: presetModel.theID]];
}

-(BOOL)stateEquals: (LSFSDKPreset *)preset
{
    return [[self getPresetDataModel] isStateEqualToModelState: [preset getPresetDataModel]];
}

-(BOOL)stateEqualsMyLampState: (LSFSDKMyLampState *)state
{
    return [self stateEqualsPower: state.power andColor: state.color];
}

-(BOOL)stateEqualsPower: (Power)power andColor: (LSFSDKColor *)color
{
    LSFConstants *constants = [LSFConstants getConstants];
    unsigned int hue = [constants unscaleLampStateValue: color.hue withMax: 100];
    unsigned int saturation = [constants unscaleLampStateValue: color.saturation withMax: 360];
    unsigned int brightness = [constants unscaleLampStateValue: color.brightness withMax: 100];
    unsigned int colorTemp = [constants unscaleColorTemp: color.colorTemp];
    return [[self getColorDataModel] isStateEqualToPowerOn: (power == ON ? YES : NO) hue: hue saturation: saturation brightness: brightness colorTemp: colorTemp];
}

/*
 * LSFSDKEffect implementation
 */
-(void)applyToGroupMember: (LSFSDKGroupMember *)member
{
    NSString *errorContext = @"LSFSDKPreset applyToGroupMember: error";

    if ([self postInvalidArgIfNull: errorContext object: member])
    {
        [member applyPreset: self];
    }
}

/*
 * Override base class functions
 */
-(void)setPowerOn: (BOOL)powerOn
{
    [self modifyWithPower: (powerOn ? ON : OFF) color: [self getColor]];
}

-(void)setColorHsvtWithHue: (unsigned int)hueDegrees saturation: (unsigned int)saturationPercent brightness: (unsigned int)brightnessPercent colorTemp: (unsigned int)colorTempDegrees
{
    LSFSDKColor *color = [[LSFSDKColor alloc] initWithHue: hueDegrees saturation: saturationPercent brightness: brightnessPercent colorTemp: colorTempDegrees];
    [self modifyWithPower: [self getPower] color: color];
}

-(void)setHue:(unsigned int)hueDegrees
{
    LSFSDKColor *currentColor = [self getColor];
    [currentColor setHue: hueDegrees];
    [self modifyWithPower: [self getPower] color: currentColor];
}

-(void)setSaturation:(unsigned int)saturationPercent
{
    LSFSDKColor *currentColor = [self getColor];
    [currentColor setSaturation: saturationPercent];
    [self modifyWithPower: [self getPower] color: currentColor];
}

-(void)setBrightness:(unsigned int)brightnessPercent
{
    LSFSDKColor *currentColor = [self getColor];
    [currentColor setBrightness: brightnessPercent];
    [self modifyWithPower: [self getPower] color: currentColor];
}

-(void)setColorTemp:(unsigned int)colorTempDegrees
{
    LSFSDKColor *currentColor = [self getColor];
    [currentColor setColorTemp: colorTempDegrees];
    [self modifyWithPower: [self getPower] color: currentColor];
}

-(void)rename:(NSString *)name
{
    NSString *errorContext = @"LSFSDKPreset rename: error";

    if ([self postInvalidArgIfNull: errorContext object: name])
    {
        [self postErrorIfFailure: errorContext status: [[LSFSDKAllJoynManager getPresetManager] setPresetNameWithID: presetModel.theID andPresetName: name]];
    }
}

-(LSFDataModel *)getColorDataModel
{
    return [self getPresetDataModel];
}

-(NSArray *)getDependentCollection
{
    LSFSDKLightingDirector *director = [LSFSDKLightingDirector getLightingDirector];
    NSMutableArray *dependents = [[NSMutableArray alloc] init];

    [dependents addObjectsFromArray: [[[director lightingManager] sceneCollectionManagerV1] getScenesCollectionWithFilter: [[LSFSDKLightingItemHasComponentFilter alloc] initWithComponent:self]]];

    [dependents addObjectsFromArray: [[[director lightingManager] transitionEffectCollectionManager] getTransitionEffectCollectionWithFilter: [[LSFSDKLightingItemHasComponentFilter alloc] initWithComponent:self]]];

    [dependents addObjectsFromArray: [[[director lightingManager] pulseEffectCollectionManager] getPulseEffectsCollectionWithFilter: [[LSFSDKLightingItemHasComponentFilter alloc] initWithComponent:self]]];


    return [NSArray arrayWithArray: dependents];
}

-(void)postError:(NSString *)name status:(LSFResponseCode)status
{
    dispatch_async([[[LSFSDKLightingDirector getLightingDirector] lightingManager] dispatchQueue], ^{
        [[[[LSFSDKLightingDirector getLightingDirector] lightingManager] presetCollectionManager] sendErrorEvent: name statusCode: status itemID: presetModel.theID];
    });
}

/**
 * <b>WARNING: This method is not intended to be used by clients, and may change or be
 * removed in subsequent releases of the SDK.</b>
 */
-(LSFPresetModel *)getPresetDataModel
{
    return presetModel;
}

@end