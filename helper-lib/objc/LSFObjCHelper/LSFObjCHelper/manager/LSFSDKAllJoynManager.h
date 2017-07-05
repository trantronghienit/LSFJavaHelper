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

#import "../callback/LSFSDKHelperControllerClientCallback.h"
#import "../callback/LSFSDKHelperControllerServiceManagerCallback.h"
#import "../callback/LSFSDKHelperGroupManagerCallback.h"
#import "../callback/LSFSDKHelperLampManagerCallback.h"
#import "../callback/LSFSDKHelperPresetManagerCallback.h"
#import "../callback/LSFSDKHelperTransitionEffectManagerCallback.h"
#import "../callback/LSFSDKHelperPulseEffectManagerCallback.h"
#import "../callback/LSFSDKHelperSceneElementManagerCallback.h"
#import "../callback/LSFSDKHelperSceneManagerCallback.h"
#import "../callback/LSFSDKHelperMasterSceneManagerCallback.h"
#import "../model/LSFLampAnnouncementData.h"
#import <alljoyn/BusAttachment.h>
#import <internal/LSFControllerClient.h>
#import <internal/LSFControllerServiceManager.h>
#import <internal/LSFLampManager.h>
#import <internal/LSFPresetManager.h>
#import <internal/LSFLampGroupManager.h>
#import <internal/LSFSceneElementManager.h>
#import <internal/LSFSceneManager.h>
#import <internal/LSFMasterSceneManager.h>
#import <internal/LSFTransitionEffectManager.h>
#import <internal/LSFPulseEffectManager.h>

@interface LSFSDKAllJoynManager : NSObject

+(void)initializeWithApplicationName: (NSString *)applicationName controllerClientCallback: (LSFSDKHelperControllerClientCallback *)ccc controllerServiceManagerCallback: (LSFSDKHelperControllerServiceManagerCallback *)csmc lampManagerCallback: (LSFSDKHelperLampManagerCallback *)lmc groupManagerCallback: (LSFSDKHelperGroupManagerCallback *)gmc presetManagerCallback: (LSFSDKHelperPresetManagerCallback *)pmc transitionEffectManagerCallback: (LSFSDKHelperTransitionEffectManagerCallback *)temc pulseEffectManagerCallback: (LSFSDKHelperPulseEffectManagerCallback *)pemc sceneElementManagerCallback: (LSFSDKHelperSceneElementManagerCallback *)semc sceneManagerCallback: (LSFSDKHelperSceneManagerCallback *)smc masterSceneManagerCallback: (LSFSDKHelperMasterSceneManagerCallback *)msmc;
+(void)initializeWithBusAttachment: (ajn::BusAttachment *)busAttachment controllerClientCallback: (LSFSDKHelperControllerClientCallback *)ccc controllerServiceManagerCallback: (LSFSDKHelperControllerServiceManagerCallback *)csmc lampManagerCallback: (LSFSDKHelperLampManagerCallback *)lmc groupManagerCallback: (LSFSDKHelperGroupManagerCallback *)gmc presetManagerCallback: (LSFSDKHelperPresetManagerCallback *)pmc transitionEffectManagerCallback: (LSFSDKHelperTransitionEffectManagerCallback *)temc pulseEffectManagerCallback: (LSFSDKHelperPulseEffectManagerCallback *)pemc sceneElementManagerCallback: (LSFSDKHelperSceneElementManagerCallback *)semc sceneManagerCallback: (LSFSDKHelperSceneManagerCallback *)smc masterSceneManagerCallback: (LSFSDKHelperMasterSceneManagerCallback *)msmc;
+(void)startWithQueue: (dispatch_queue_t)queue;
+(void)stop;
+(void)addNewLamp: (NSString*)lampID lampAnnouncementData:(LSFLampAnnouncementData *)lampAnnData;
+(void)getAboutDataForLampID: (NSString*)lampID;
+(void)setControllerConnected: (BOOL)isConnected;
+(BOOL)getControllerConnected;
+(void)setControllerServiceLeaderVersion: (unsigned int)version;
+(unsigned int)getControllerServiceLeaderVersion;
+(BOOL)isControllerServiceLeaderV1;
+(LSFControllerClient *)getControllerClient;
+(LSFControllerServiceManager *)getControllerServiceManager;
+(LSFLampManager *)getLampManager;
+(LSFLampGroupManager *)getGroupManager;
+(LSFPresetManager *)getPresetManager;
+(LSFSceneElementManager *)getSceneElementManager;
+(LSFSceneManager *)getSceneManager;
+(LSFMasterSceneManager *)getMasterSceneManager;
+(LSFTransitionEffectManager *)getTransitionEffectManager;
+(LSFPulseEffectManager *)getPulseEffectManager;
+(LSFSDKHelperControllerClientCallback *)getControllerClientCallback;
+(LSFSDKHelperControllerServiceManagerCallback *)getControllerServiceManagerCallback;
+(LSFSDKHelperLampManagerCallback *)getLampManagerCallback;
+(LSFSDKHelperGroupManagerCallback *)getGroupManagerCallback;
+(LSFSDKHelperPresetManagerCallback *)getPresetManagerCallback;
+(LSFSDKHelperSceneElementManagerCallback *)getSceneElementManagerCallback;
+(LSFSDKHelperSceneManagerCallback *)getSceneManagerCallback;
+(LSFSDKHelperMasterSceneManagerCallback *)getMasterSceneManagerCallback;
+(LSFSDKHelperTransitionEffectManagerCallback *)getTransitionEffectManagerCallback;
+(LSFSDKHelperPulseEffectManagerCallback *)getPulseEffectManagerCallback;
+(ajn::BusAttachment *)getBusAttachment;

@end