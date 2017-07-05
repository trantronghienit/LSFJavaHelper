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
#import "listener/LSFSDKLightingDelegate.h"
#import "LSFSDKTrackingID.h"

@class LSFSDKLamp;
@class LSFSDKLightingItemErrorEvent;

/**
 * Provides an interface for developers to implement and receive all lamp related events in the
 * Lighting system. Developers will be notified when lamps are found, modified, initialized, and
 * lost from the lighting controller. Lamps are considered initialized when all lamp data has
 * been received from the lighting controller.
 *
 * **Note:** Once implemented, the delegate must be registered with the LSFSDKLightingDirector in order
 * to receive Lamp callbacks. See [LSFSDKLightingDirector addLampDelegate:] for more information.
 */
@protocol LSFSDKLampDelegate <LSFSDKLightingDelegate>

/**
 * Triggered when all data has been received from the lighting controller for a
 * particular LSFSDKLamp.
 *
 * @warning This callback will fire only once for each LSFSDKLamp when all data has been received from
 * the lighting controller.
 *
 * @param lamp Reference to LSFSDKLamp
 */
-(void)onLampInitialized: (LSFSDKLamp *)lamp;

/**
 * Triggered every time new data is received from the lighting controller for a
 * particular LSFSDKLamp.
 *
 * @param lamp Reference to LSFSDKLamp
 */
-(void)onLampChanged: (LSFSDKLamp *)lamp;

/**
 * Triggered when a particular LSFSDKLamp has been lost from the lighting controller.
 *
 * @warning This callback will fire only once for each LSFSDKLamp when it is lost from
 * the lighting controller.
 *
 * @param lamp Reference to LSFSDKLamp
 */
-(void)onLampRemoved: (LSFSDKLamp *)lamp;

/**
 * Triggered when an error occurs on a LSFSDKLamp operation.
 *
 * @param error Reference to LSFSDKLightingItemErrorEvent
 */
-(void)onLampError: (LSFSDKLightingItemErrorEvent *)error;

@end