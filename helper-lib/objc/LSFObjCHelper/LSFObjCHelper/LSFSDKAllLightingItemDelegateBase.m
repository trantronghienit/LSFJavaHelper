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

#import "LSFSDKAllLightingItemDelegateBase.h"

@implementation LSFSDKAllLightingItemDelegateBase

-(void)onLampInitialized: (LSFSDKLamp *)lamp
{
    //Intentionally left blank
}

-(void)onLampChanged: (LSFSDKLamp *)lamp
{
    //Intentionally left blank
}

-(void)onLampRemoved: (LSFSDKLamp *)lamp
{
    //Intentionally left blank
}

-(void)onLampError: (LSFSDKLightingItemErrorEvent *)error
{
    //Intentionally left blank
}

-(void)onGroupInitializedWithTrackingID: (LSFSDKTrackingID *)trackingID andGroup: (LSFSDKGroup *)group
{
    //Intentionally left blank
}

-(void)onGroupChanged: (LSFSDKGroup *)group
{
    //Intentionally left blank
}
-(void)onGroupRemoved: (LSFSDKGroup *)group
{
    //Intentionally left blank
}

-(void)onGroupError: (LSFSDKLightingItemErrorEvent *)error
{
    //Intentionally left blank
}

-(void)onPresetInitializedWithTrackingID: (LSFSDKTrackingID *)trackingID andPreset: (LSFSDKPreset *)preset
{
    //Intentionally left blank
}

-(void)onPresetChanged: (LSFSDKPreset *)preset
{
    //Intentionally left blank
}
-(void)onPresetRemoved: (LSFSDKPreset *)preset
{
    //Intentionally left blank
}

-(void)onPresetError: (LSFSDKLightingItemErrorEvent *)error
{
    //Intentionally left blank
}

-(void)onTransitionEffectInitializedWithTrackingID: (LSFSDKTrackingID *)trackingID andTransitionEffect: (LSFSDKTransitionEffect *)transitionEffect;
{
    //Intentionally left blank
}

-(void)onTransitionEffectChanged: (LSFSDKTransitionEffect *)transitionEffect
{
    //Intentionally left blank
}

-(void)onTransitionEffectRemoved: (LSFSDKTransitionEffect *)transitionEffect
{
    //Intentionally left blank
}

-(void)onTransitionEffectError: (LSFSDKLightingItemErrorEvent *)error
{
    //Intentionally left blank
}

-(void)onPulseEffectInitializedWithTrackingID: (LSFSDKTrackingID *)trackingID andPulseEffect: (LSFSDKPulseEffect *)pulseEffect;
{
    //Intentionally left blank
}

-(void)onPulseEffectChanged: (LSFSDKPulseEffect *)pulseEffect
{
    //Intentionally left blank
}

-(void)onPulseEffectRemoved: (LSFSDKPulseEffect *)pulseEffect
{
    //Intentionally left blank
}

-(void)onPulseEffectError: (LSFSDKLightingItemErrorEvent *)error
{
    //Intentionally left blank
}

-(void)onSceneElementInitializedWithTrackingID: (LSFSDKTrackingID *)trackingID andSceneElement: (LSFSDKSceneElement *)sceneElement
{
    //Intentionally left blank
}

-(void)onSceneElementChanged: (LSFSDKSceneElement *)sceneElement
{
    //Intentionally left blank
}

-(void)onSceneElementRemoved: (LSFSDKSceneElement *)sceneElement
{
    //Intentionally left blank
}

-(void)onSceneElementError: (LSFSDKLightingItemErrorEvent *)error
{
    //Intentionally left blank
}

-(void)onSceneInitializedWithTrackingID: (LSFSDKTrackingID *)trackingID andScene: (LSFSDKScene *)scene
{
    //Intentionally left blank
}

-(void)onSceneChanged: (LSFSDKScene *)scene
{
    //Intentionally left blank
}

-(void)onSceneRemoved: (LSFSDKScene *)scene
{
    //Intentionally left blank
}

-(void)onSceneError: (LSFSDKLightingItemErrorEvent *)error
{
    //Intentionally left blank
}

-(void)onMasterSceneInitializedWithTrackingID: (LSFSDKTrackingID *)trackingID andMasterScene: (LSFSDKMasterScene *)masterScene
{
    //Intentionally left blank
}

-(void)onMasterSceneChanged: (LSFSDKMasterScene *)masterScene
{
    //Intentionally left blank
}

-(void)onMasterSceneRemoved: (LSFSDKMasterScene *)masterScene
{
    //Intentionally left blank
}

-(void)onMasterSceneError: (LSFSDKLightingItemErrorEvent *)error
{
    //Intentionally left blank
}

-(void)onLeaderChange: (LSFSDKController *)leader
{
    //Intentionally left blank
}

-(void)onControllerError: (LSFSDKControllerErrorEvent *)errorEvent
{
    //Intentionally left blank
}

@end