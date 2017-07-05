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

#import "LSFSDKLightingControllerConfiguration.h"
#import "LSFSDKLightingControllerStatus.h"
#import "model/LSFSDKBasicControllerService.h"

/**
 * The LSFSDKLightingController provides access to the bundled controller and connects to all
 * lamps on the network as well as the LSFSDKLightingDirector. The LSFSDKLightingController also
 * stores all group, preset, effect, and scene definitions.
 *
 * Please see the LSFTutorial Project for an example of how to use the LightingController class.
 *
 * @warning The LSFSDKLightingController requires a WiFi or Ethernet network connection.
 */
@interface LSFSDKLightingController : NSObject
{
    @private BOOL controllerRunning;
    @private LSFSDKBasicControllerService *controllerService;
}

/** @name Class Properties */

/**
 * Returns a boolean representing whether the Controller is running.
 *
 * @return True if the LSFSDKLightingController is running, false otherwise
 */
@property (nonatomic, readonly) BOOL isRunning;

/*
 * Returns a boolean representing whether the Controller is the leader.
 *
 * @return True if the LSFSDKLightingController is the leader, false otherwise
 */
@property (nonatomic, readonly) BOOL isLeader;

/*
 * Returns the device name of the Controller.
 *
 * @return The name of the LSFSDKLightingController if running, otherwise nil
 */
@property (nonatomic, strong, readonly) NSString *name;

/** @name Getting the Shared LSFSDKLightingController Instance */

/**
 * Returns a LSFSDKLightingController instance.
 *
 * @warning The start method must be called at some point when you're ready
 * to begin working with the LSFSDKLightingController.
 *
 * @return Reference to LSFSDKLightingController
 */
+(LSFSDKLightingController *)getLightingController;

/** @name Initializing the Shared LSFSDKLightingController Instance */

/**
 * Initializes the LSFSDKLightingController using the provided controller configuration.
 *
 * @warning This method call should follow the getLightingController method call and precede the start method call.
 *
 * @param configuration The desired configuration for the LSFSDKLightingController
 *
 * @return OK if the controller was initialized correctly, ERROR_INIT otherwise
 */
-(LightingControllerStatus)initializeWithControllerConfiguration: (id<LSFSDKLightingControllerConfiguration>)configuration;

/** @name Controller Operations */

/**
 * Starts the bundled Lighting Controller. Once started, the controller will connect to
 * any lamps that are on the network.
 *
 * @warning This method should be called before interacting with the LSFSDKLightingController. Subsequent
 * calls to this method must each be preceded by a call to stop.
 *
 * @warning You should ensure WiFi or some other network connection is available before
 * calling this method.
 *
 * @return OK if LightingController successfully started
 */
-(LightingControllerStatus)start;

/**
 * Stops the Lighting Controller. All group, preset, effect, and scene definitions will
 * be preserved for when the controller is started again.
 *
 * @warning This method must be preceded by a call to start.
 *
 * @return OK if the LSFSDKLightingController successfully stopped
 */
-(LightingControllerStatus)stop;

/**
 * Causes the Lighting Controller to factory reset. Factory reset will return the
 * controller to its default factory settings. All group, preset, effect, and scene
 * definitions will be deleted.
 *
 * @warning This method must be preceded by a call to start.
 *
 * @return OK if the LSFSDKLightingController successfully factory reset
 */
-(LightingControllerStatus)factoryReset;

/**
 * Causes the Lighting Controller to reset. All group, preset, effect, and scene
 * definitions will be preserved.
 *
 * @warning This method must be preceded by a call to start.
 *
 * @return OK if the LSFSDKLightingController successfully reset
 */
-(LightingControllerStatus)lightingReset;

/**
 * Notifies the LSFSDKLightingController that a WiFi or Ethernet connection is available. Once
 * a WiFi or Ethernet connection is established, the controller will begin connecting to lamps on
 * the network.
 *
 * @warning This method must be preceded by a call to start and should be called any time
 * there is a change to the WiFi or Ethernet connection.
 */
-(void)sendNetworkConnected;

/**
 * Notifies the LSFSDKLightingController that the WiFi or Ethernet connection has been lost.
 * When a network connection is the lost the controller will enter a suspended state until a
 * valid network connection is reestablished.
 *
 * @warning This method must be preceded by a call to start and should be called any time
 * there is a change to the WiFi or Ethernet connection.
 */
-(void)sendNetworkDisconnected;

@end