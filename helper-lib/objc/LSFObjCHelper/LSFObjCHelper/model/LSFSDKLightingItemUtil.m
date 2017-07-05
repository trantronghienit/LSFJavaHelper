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

#import "LSFSDKLightingItemUtil.h"
#import "LSFConstants.h"
#import "LSFSDKLamp.h"
#import "LSFSDKGroup.h"

@implementation LSFSDKLightingItemUtil

+(LSFLampState *)createLampStateFromPower: (BOOL)powerOn hue: (unsigned int)hue saturation: (unsigned int)saturation brightness: (unsigned int)brightness colorTemp: (unsigned int)colorTemp
{
    LSFConstants *constants = [LSFConstants getConstants];

    unsigned int scaledBrightness = [constants scaleLampStateValue: brightness withMax: 100];
    unsigned int scaledHue = [constants scaleLampStateValue: hue withMax: 360];
    unsigned int scaledSaturation = [constants scaleLampStateValue: saturation withMax: 100];
    unsigned int scaledColorTemp = [constants scaleColorTemp: colorTemp];

    LSFLampState *lampState = [[LSFLampState alloc] initWithOnOff: powerOn brightness: scaledBrightness hue: scaledHue saturation:scaledSaturation colorTemp: scaledColorTemp];
    return lampState;
}

+(LSFLampGroup *)createLampGroupFromGroupMembers: (NSArray *)groupMembers
{
    if (groupMembers == nil)
    {
        return nil;
    }

    NSMutableSet *lamps = [[NSMutableSet alloc] init];
    NSMutableSet *groups = [[NSMutableSet alloc] init];

    for (LSFSDKGroupMember *member in groupMembers)
    {
        if ([member isKindOfClass: [LSFSDKLamp class]])
        {
            LSFSDKLamp *lamp = (LSFSDKLamp *)member;
            [lamps addObject: [[lamp getLampDataModel] theID]];
        }
        else if ([member isKindOfClass: [LSFSDKGroup class]])
        {
            LSFSDKGroup *group = (LSFSDKGroup *)member;
            [groups addObject: [[group getLampGroupDataModel] theID]];
        }
    }

    return [self createLampGroupFromLampIDs:[lamps allObjects]groupIDs: [groups allObjects]];
}

+(LSFLampGroup *)createLampGroupFromLampIDs: (NSArray *)lampIDs groupIDs: (NSArray *)groupIDs
{
    LSFLampGroup *lampGroup = nil;

    if ((lampIDs != nil) &&(groupIDs != nil))
    {
        lampGroup = [[LSFLampGroup alloc] init];
        [lampGroup setLamps: lampIDs];
        [lampGroup setLampGroups: groupIDs];
    }

    return lampGroup;
}

+(LSFTransitionEffectV2 *)createTransitionEffectFromPower: (BOOL)powerOn hsvt: (NSArray *)hsvt duration: (unsigned int)duration
{
    NSNumber *hueNumber = [hsvt objectAtIndex: 0];
    NSNumber *saturationNumber = [hsvt objectAtIndex: 1];
    NSNumber *brightnessNumber = [hsvt objectAtIndex: 2];
    NSNumber *colorTempNumber = [hsvt objectAtIndex: 3];

    return [self createTransitionEffectFromLampState: [self createLampStateFromPower: powerOn hue: [hueNumber unsignedIntValue] saturation: [saturationNumber unsignedIntValue] brightness: [brightnessNumber unsignedIntValue] colorTemp: [colorTempNumber unsignedIntValue]] duration: duration];
}

+(LSFTransitionEffectV2 *)createTransitionEffectFromLampState: (LSFLampState *)lampState duration: (unsigned int)duration
{
    LSFTransitionEffectV2 *transitionEffect = nil;

    if (lampState != nil)
    {
        transitionEffect = [[LSFTransitionEffectV2 alloc] initWithLampState: lampState transitionPeriod: duration];
    }

    return transitionEffect;
}

+(LSFTransitionEffectV2 *)createTransitionEffectFromPreset: (LSFSDKPreset *)preset duration: (unsigned int)duration
{
    LSFTransitionEffectV2 *transitionEffect = nil;

    if (preset != nil)
    {
        transitionEffect = [[LSFTransitionEffectV2 alloc] initWithPresetID: [[preset getPresetDataModel] theID] transitionPeriod: duration];
    }

    return transitionEffect;
}

+(LSFPulseEffectV2 *)createPulseEffectWithFromPowerOn: (BOOL)fromPowerOn fromColorHsvt: (NSArray *)fromColorHsvt toPowerOn: (BOOL)toPowerOn toColorHsvt: (NSArray *)toColorHsvt period: (unsigned int)period duration: (unsigned int)duration count: (unsigned int)count
{
    NSNumber *fromHueNumber = [fromColorHsvt objectAtIndex: 0];
    NSNumber *fromSaturationNumber = [fromColorHsvt objectAtIndex: 1];
    NSNumber *fromBrightnessNumber = [fromColorHsvt objectAtIndex: 2];
    NSNumber *fromColorTempNumber = [fromColorHsvt objectAtIndex: 3];

    NSNumber *toHueNumber = [toColorHsvt objectAtIndex: 0];
    NSNumber *toSaturationNumber = [toColorHsvt objectAtIndex: 1];
    NSNumber *toBrightnessNumber = [toColorHsvt objectAtIndex: 2];
    NSNumber *toColorTempNumber = [toColorHsvt objectAtIndex: 3];

    return [self createPulseEffectFromLampState: [self createLampStateFromPower: fromPowerOn hue: [fromHueNumber unsignedIntValue] saturation: [fromSaturationNumber unsignedIntValue] brightness: [fromBrightnessNumber unsignedIntValue] colorTemp: [fromColorTempNumber unsignedIntValue]] toLampState: [self createLampStateFromPower: toPowerOn hue: [toHueNumber unsignedIntValue] saturation: [toSaturationNumber unsignedIntValue] brightness: [toBrightnessNumber unsignedIntValue] colorTemp: [toColorTempNumber unsignedIntValue]] period: period duration: duration count: count];
}

+(LSFPulseEffectV2 *)createPulseEffectFromLampState: (LSFLampState *)fromState toLampState: (LSFLampState *)toState period: (unsigned int)period duration: (unsigned int)duration count: (unsigned int)count
{
    LSFPulseEffectV2 *pulseEffect = nil;

    if ((fromState != nil) && (toState != nil))
    {
        pulseEffect = [[LSFPulseEffectV2 alloc] initWithToLampState: toState period: period duration: duration numPulses: count fromLampState: fromState];
    }

    return pulseEffect;
}

+(LSFPulseEffectV2 *)createPulseeffectFromPreset: (LSFSDKPreset *)fromPreset toPreset: (LSFSDKPreset *)toPreset period: (unsigned int)period duration: (unsigned int)duration count: (unsigned int)count
{
    LSFPulseEffectV2 *pulseEffect = nil;

    if ((fromPreset != nil) && (toPreset != nil))
    {
        pulseEffect = [[LSFPulseEffectV2 alloc] initWithToPreset: [[toPreset getPresetDataModel] theID] period: period duration: duration numPulses: count fromPreset: [[fromPreset getPresetDataModel] theID]];
    }

    return pulseEffect;
}

+(LSFSceneElement *)createSceneElementWithEffectID: (NSString *)effectID groupMembers: (NSArray *)members
{
    LSFSceneElement *sceneElement = nil;

    if (effectID != nil && members != nil)
    {
        LSFLampGroup *groupMembers = [self createLampGroupFromGroupMembers: members];
        sceneElement = [[LSFSceneElement alloc] initWithLampIDs: groupMembers.lamps lampGroupIDs: groupMembers.lampGroups andEffectID: effectID];
    }

    return sceneElement;
}

+(LSFSceneElement *)createSceneElementWithEffectID: (NSString *)effectID lampIDs: (NSArray *)lampIDs groupIDs: (NSArray *)groupIDs
{
    LSFSceneElement *sceneElement = nil;

    if (effectID != nil && lampIDs != nil && groupIDs != nil)
    {
        sceneElement = [[LSFSceneElement alloc] initWithLampIDs: lampIDs lampGroupIDs: groupIDs andEffectID: effectID];
    }

    return sceneElement;
}

+(LSFMasterScene *)createMasterSceneFromSceneID: (NSArray *)sceneIDs
{
    LSFMasterScene *masterScene = nil;

    if (sceneIDs != nil)
    {
        masterScene = [[LSFMasterScene alloc] initWithSceneIDs: sceneIDs];
    }

    return masterScene;
}

+(LSFSceneWithSceneElements *)createSceneWithSceneElements: (NSArray *)sceneElementIDs
{
    LSFSceneWithSceneElements *sceneWithSceneElements = nil;

    if (sceneElementIDs != nil)
    {
        sceneWithSceneElements = [[LSFSceneWithSceneElements alloc] initWithSceneElementIDs: sceneElementIDs];
    }

    return sceneWithSceneElements;
}

@end