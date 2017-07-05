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
#import <internal/LSFLampState.h>
#import <internal/LSFLampGroup.h>
#import <internal/LSFTransitionEffectV2.h>
#import <internal/LSFPulseEffectV2.h>
#import <internal/LSFSceneElement.h>
#import <internal/LSFMasterScene.h>
#import <internal/LSFSceneWithSceneElements.h>
#import "LSFSDKPreset.h"

@interface LSFSDKLightingItemUtil : NSObject

+(LSFLampState *)createLampStateFromPower: (BOOL)powerOn hue: (unsigned int)hue saturation: (unsigned int)saturation brightness: (unsigned int)brightness colorTemp: (unsigned int)colorTemp;
+(LSFLampGroup *)createLampGroupFromGroupMembers: (NSArray *)groupMembers;
+(LSFLampGroup *)createLampGroupFromLampIDs: (NSArray *)lampIDs groupIDs: (NSArray *)groupIDs;
+(LSFTransitionEffectV2 *)createTransitionEffectFromPower: (BOOL)powerOn hsvt: (NSArray *)hsvt duration: (unsigned int)duration;
+(LSFTransitionEffectV2 *)createTransitionEffectFromLampState: (LSFLampState *)lampState duration: (unsigned int)duration;
+(LSFTransitionEffectV2 *)createTransitionEffectFromPreset: (LSFSDKPreset *)preset duration: (unsigned int)duration;
+(LSFPulseEffectV2 *)createPulseEffectWithFromPowerOn: (BOOL)fromPowerOn fromColorHsvt: (NSArray *)fromColorHsvt toPowerOn: (BOOL)toPowerOn toColorHsvt: (NSArray *)toColorHsvt period: (unsigned int)period duration: (unsigned int)duration count: (unsigned int)count;
+(LSFPulseEffectV2 *)createPulseEffectFromLampState: (LSFLampState *)fromState toLampState: (LSFLampState *)toState period: (unsigned int)period duration: (unsigned int)duration count: (unsigned int)count;
+(LSFPulseEffectV2 *)createPulseeffectFromPreset: (LSFSDKPreset *)fromPreset toPreset: (LSFSDKPreset *)toPreset period: (unsigned int)period duration: (unsigned int)duration count: (unsigned int)count;
+(LSFSceneElement *)createSceneElementWithEffectID: (NSString *)effectID groupMembers: (NSArray *)members;
+(LSFSceneElement *)createSceneElementWithEffectID: (NSString *)effectID lampIDs: (NSArray *)lampIDs groupIDs: (NSArray *)groupIDs;
+(LSFMasterScene *)createMasterSceneFromSceneID: (NSArray *)sceneIDs;
+(LSFSceneWithSceneElements *)createSceneWithSceneElements: (NSArray *)sceneElementIDs;

@end