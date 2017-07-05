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
#import "LSFSDKNextControllerConnectionDelegate.h"
#import "../listener/LSFSDKAllJoynDelegate.h"
#import "../LSFSDKControllerDelegate.h"
#import "../callback/LSFSDKHelperControllerClientCallback.h"
#import "../callback/LSFSDKHelperControllerServiceManagerCallback.h"
#import "../callback/LSFSDKHelperGroupManagerCallback.h"
#import "../callback/LSFSDKHelperLampManagerCallback.h"
#import "../callback/LSFSDKHelperMasterSceneManagerCallback.h"
#import "../callback/LSFSDKHelperPresetManagerCallback.h"
#import "../callback/LSFSDKHelperPulseEffectManagerCallback.h"
#import "../callback/LSFSDKHelperSceneElementManagerCallback.h"
#import "../callback/LSFSDKHelperSceneManagerCallback.h"
#import "../callback/LSFSDKHelperTransitionEffectManagerCallback.h"
#import "LSFSDKLampCollectionManager.h"
#import "LSFSDKPresetCollectionManager.h"
#import "LSFSDKGroupCollectionManager.h"
#import "LSFSDKTransitionEffectCollectionManager.h"
#import "LSFSDKPulseEffectCollectionManager.h"
#import "LSFSDKSceneElementCollectionManager.h"
#import "LSFSDKSceneCollectionManagerV1.h"
#import "LSFSDKSceneCollectionManagerV2.h"
#import "LSFSDKMasterSceneCollectionManager.h"
#import "LSFControllerManager.h"
#import <internal/LSFLampManager.h>
#import <internal/LSFLampGroupManager.h>
#import <internal/LSFPresetManager.h>
#import <internal/LSFSceneManager.h>
#import <internal/LSFMasterSceneManager.h>
#import <internal/LSFTransitionEffectManager.h>
#import <internal/LSFSceneElementManager.h>
#import <internal/LSFPulseEffectManager.h>

/**
 * @warning *Note:* This class is not intended to be used by clients, and its interface may change
 * in subsequent releases of the SDK.
 */
@interface LSFSDKLightingSystemManager : NSObject <LSFSDKControllerDelegate, LSFSDKLampDelegate>

@property (nonatomic, strong) NSString *defaultLanguage;
@property (nonatomic, strong) dispatch_queue_t dispatchQueue;
@property (nonatomic, strong, readonly) LSFSDKHelperControllerClientCallback *controllerClientCB;
@property (nonatomic, strong, readonly) LSFSDKHelperControllerServiceManagerCallback *controllerServiceManagerCB;
@property (nonatomic, strong, readonly) LSFSDKHelperGroupManagerCallback *groupManagerCB;
@property (nonatomic, strong, readonly) LSFSDKHelperLampManagerCallback *lampManagerCB;
@property (nonatomic, strong, readonly) LSFSDKHelperPresetManagerCallback *presetManagerCB;
@property (nonatomic, strong, readonly) LSFSDKHelperTransitionEffectManagerCallback *transitionEffectManagerCB;
@property (nonatomic, strong, readonly) LSFSDKHelperPulseEffectManagerCallback *pulseEffectManagerCB;
@property (nonatomic, strong, readonly) LSFSDKHelperSceneElementManagerCallback *sceneElementManagerCB;
@property (nonatomic, strong, readonly) LSFSDKHelperSceneManagerCallback *sceneManagerCB;
@property (nonatomic, strong, readonly) LSFSDKHelperMasterSceneManagerCallback *masterSceneManagerCB;
@property (nonatomic, strong, readonly) LSFSDKLampCollectionManager *lampCollectionManager;
@property (nonatomic, strong, readonly) LSFSDKPresetCollectionManager *presetCollectionManager;
@property (nonatomic, strong, readonly) LSFSDKGroupCollectionManager *groupCollectionManager;
@property (nonatomic, strong, readonly) LSFSDKTransitionEffectCollectionManager *transitionEffectCollectionManager;
@property (nonatomic, strong, readonly) LSFSDKPulseEffectCollectionManager *pulseEffectCollectionManager;
@property (nonatomic, strong, readonly) LSFSDKSceneElementCollectionManager *sceneElementCollectionManager;
@property (nonatomic, strong, readonly) LSFSDKSceneCollectionManagerV1 *sceneCollectionManagerV1;
@property (nonatomic, strong, readonly) LSFSDKSceneCollectionManagerV2 *sceneCollectionManager;
@property (nonatomic, strong, readonly) LSFSDKMasterSceneCollectionManager *masterSceneCollectionManager;
@property (nonatomic, strong, readonly) LSFLampManager *lampManager;
@property (nonatomic, strong, readonly) LSFLampGroupManager *groupManager;
@property (nonatomic, strong, readonly) LSFPresetManager *presetManager;
@property (nonatomic, strong, readonly) LSFTransitionEffectManager *transitionEffectManager;
@property (nonatomic, strong, readonly) LSFPulseEffectManager *pulseEffectManager;
@property (nonatomic, strong, readonly) LSFSceneElementManager *sceneElementManager;
@property (nonatomic, strong, readonly) LSFSceneManager *sceneManager;
@property (nonatomic, strong, readonly) LSFMasterSceneManager *masterSceneManager;
@property (nonatomic, strong, readonly) LSFControllerManager *controllerManager;

-(id)init;
-(void)initializeWithApplicationName: (NSString *)applicationName dispatchQueue: (dispatch_queue_t)queue andAllJoynDelegate: (id<LSFSDKAllJoynDelegate>)alljoynDelegate;
-(void)initializeWithBusAttachment: (ajn::BusAttachment *)busAttachment dispatchQueue: (dispatch_queue_t)queue andAllJoynDelegate: (id<LSFSDKAllJoynDelegate>)alljoynDelegate;
-(void)start;
-(void)stop;
-(void)postOnNextControllerConnection: (id<LSFSDKNextControllerConnectionDelegate>)delegate withDelay: (unsigned int)delay;

@end