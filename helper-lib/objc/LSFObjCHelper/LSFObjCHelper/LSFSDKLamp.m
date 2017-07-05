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

#import "LSFSDKLightingDirector.h"
#import "LSFSDKLamp.h"
#import "model/LSFConstants.h"
#import "manager/LSFSDKAllJoynManager.h"

@implementation LSFSDKLamp

@synthesize about = _about;
@synthesize details = _details;
@synthesize parameters = _parameters;
@synthesize colorTempMin = _colorTempMin;
@synthesize colorTempMax = _colorTempMax;

-(LSFSDKLampAbout *)about
{
    return [[LSFSDKLampAbout alloc] initWithAboutData: [[self getLampDataModel] aboutData]];
}

-(LSFSDKLampDetails *)details
{
    return [[self getLampDataModel] lampDetails];
}

-(LSFSDKLampParameters *)parameters
{
    return [[self getLampDataModel] lampParameters];
}

-(int)colorTempMin
{
    return [[self details] minTemperature];
}

-(int)colorTempMax
{
    return [[self details] maxTemperature];
}

/*
 * Override base class functions
 */
-(void)setPowerOn: (BOOL)powerOn
{
    NSString *errorContext = @"LSFSDKLamp setPowerOn: error";

    [self postErrorIfFailure: errorContext status: [[LSFSDKAllJoynManager getLampManager] transitionLampID: lampModel.theID onOffField: powerOn]];
}

-(void)setColorHsvtWithHue:(unsigned int)hueDegrees saturation:(unsigned int)saturationPercent brightness:(unsigned int)brightnessPercent colorTemp:(unsigned int)colorTempDegrees
{
    NSString *errorContext = @"LSFSDKLamp setColorHsvt: error";
    LSFConstants *constants = [LSFConstants getConstants];

    unsigned int scaledBrightness = [constants scaleLampStateValue: brightnessPercent withMax: 100];
    unsigned int scaledHue = [constants scaleLampStateValue: hueDegrees withMax: 360];
    unsigned int scaledSaturation = [constants scaleLampStateValue: saturationPercent withMax: 100];
    unsigned int scaledColorTemp = [constants scaleColorTemp: colorTempDegrees];

    LSFLampState *lampState = [[LSFLampState alloc] initWithOnOff: self.getPowerOn brightness: scaledBrightness hue: scaledHue saturation: scaledSaturation colorTemp: scaledColorTemp];

    [self postErrorIfFailure: errorContext status: [[LSFSDKAllJoynManager getLampManager] transitionLampID: lampModel.theID toLampState: lampState]];
}

-(void)setHue:(unsigned int)hueDegrees
{
    NSString *errorContext = @"LSFSDKLamp setHue: error";

    unsigned int scaledHue = [[LSFConstants getConstants] scaleLampStateValue: hueDegrees withMax: 360];

    [self postErrorIfFailure: errorContext status: [[LSFSDKAllJoynManager getLampManager] transitionLampID: lampModel.theID hueField: scaledHue]];
}

-(void)setSaturation:(unsigned int)saturationPercent
{
    NSString *errorContext = @"LSFSDKLamp setSaturation: error";

    unsigned int scaledSaturation = [[LSFConstants getConstants] scaleLampStateValue: saturationPercent withMax: 100];

    [self postErrorIfFailure: errorContext status: [[LSFSDKAllJoynManager getLampManager] transitionLampID: lampModel.theID saturationField: scaledSaturation]];
}

-(void)setBrightness:(unsigned int)brightnessPercent
{
    NSString *errorContext = @"LSFSDKLamp setBrightness: error";

    unsigned int scaledBrightness = [[LSFConstants getConstants] scaleLampStateValue: brightnessPercent withMax: 100];

    [self postErrorIfFailure: errorContext status: [[LSFSDKAllJoynManager getLampManager] transitionLampID: lampModel.theID brightnessField: scaledBrightness]];
}

-(void)setColorTemp:(unsigned int)colorTempDegrees
{
    NSString *errorContext = @"LSFSDKLamp setColorTemperature: error";

    unsigned int scaledColorTemp = [[LSFConstants getConstants] scaleColorTemp: colorTempDegrees];

    [self postErrorIfFailure: errorContext status: [[LSFSDKAllJoynManager getLampManager] transitionLampID: lampModel.theID colorTempField:scaledColorTemp]];
}

-(void)rename: (NSString *)name
{
    NSString *errorContext = @"LSFSDKLamp rename: error";

    if ([self postInvalidArgIfNull: errorContext object: name])
    {
        [self postErrorIfFailure: errorContext status: [[LSFSDKAllJoynManager getLampManager] setLampNameWithID: lampModel.theID name: name andLanguage: [[LSFSDKLightingDirector getLightingDirector] defaultLanguage]]];
    }
}

-(void)applyPreset: (LSFSDKPreset *)preset
{
    NSString *errorContext = @"LSFSDKLamp applyPreset: error";

    if ([self postInvalidArgIfNull: errorContext object: preset])
    {
        [self postErrorIfFailure: errorContext status: [[LSFSDKAllJoynManager getLampManager] transitionLampID: lampModel.theID toPresetID: [[preset getPresetDataModel] theID]]];
    }
}

-(void)applyEffect: (id<LSFSDKEffect>)effect
{
    NSString *errorContext = @"LSFSDKLamp applyEffect: error";

    if ([self postInvalidArgIfNull: errorContext object: effect])
    {
        if ([effect isKindOfClass: [LSFSDKPreset class]])
        {
            [self applyPreset: (LSFSDKPreset *)effect];
        }
        else if ([effect isKindOfClass: [LSFSDKTransitionEffect class]])
        {
            [self postErrorIfFailure: errorContext status: [[LSFSDKAllJoynManager getTransitionEffectManager] applyTranstionEffectWithID: [effect theID] onLamps: [NSArray arrayWithObjects: lampModel.theID, nil]]];
        }
        else if ([effect isKindOfClass: [LSFSDKPulseEffect class]])
        {
            [self postErrorIfFailure: errorContext status: [[LSFSDKAllJoynManager getPulseEffectManager] applyPulseEffectWithID: [effect theID] onLamps: [NSArray arrayWithObjects: lampModel.theID, nil]]];
        }
    }
}

-(LSFDataModel *)getColorDataModel
{
    return [self getLampDataModel];
}

-(void)postError:(NSString *)name status:(LSFResponseCode)status
{
    dispatch_async([[[LSFSDKLightingDirector getLightingDirector] lightingManager] dispatchQueue], ^{
        [[[[LSFSDKLightingDirector getLightingDirector] lightingManager] lampCollectionManager] sendErrorEvent: name statusCode: status itemID: lampModel.theID];
    });
}

/**
 * <b>WARNING: This method is not intended to be used by clients, and may change or be
 * removed in subsequent releases of the SDK.</b>
 */
-(LSFLampModel *)getLampDataModel
{
    return lampModel;
}

@end