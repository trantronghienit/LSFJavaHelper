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

#import <LSFSDKRankParam.h>
#import <LSFSDKAboutData.h>

using namespace lsf;

#ifdef LSF_BINDINGS
using namespace controllerservice;
#endif

/**
 * Provides an interface for developers to implement and define the application and device
 * specific properties for the lighting controller. This includes AllJoyn keystore file path,
 * AllJoyn about properties, device MAC address, and other device specific properties.
 *
 * **Note:** Once implemented, the configuration must be registered with the LSFSDKLightingController
 * using the [LSFSDKLightingController initializeWithControllerConfiguration:] method.
 */
@protocol LSFSDKLightingControllerConfiguration <NSObject>

/**
 * Returns the file location to save LSFSDKLightingController specific files.
 *
 * @return Absolute directory path to be used for file storage.
 */
-(NSString *)getKeystorePath;

/**
 * Returns the MAC address of the device running the LSFSDKLightingController.
 *
 * **Note:** The MAC address is expected to be a 12-digit hex string (i.e. "XXXXXXXXXXXX").
 *
 * @param generatedMacAddress  Random hex string which can be used as the MAC address on devices where it cannot be queried
 *
 * @return The 12-digit HEX string MAC address of the device
 */
-(NSString *)getMacAddress: (NSString *)generatedMacAddress;

/**
 * Determines if their is a network available for LSFSDKLightingController to connect.
 *
 * @return Return true their is a network available for the controller, false otherwise.
 */
-(BOOL)isNetworkConnected;

/**
 * Returns the mobility capability of the device running the LSFSDKLightingController. The mobility
 * capability is used to determine the controllers rank.
 *
 * @return LSFSDKLightingController RankMobility
 */
-(OEM_CS_RankParam_Mobility)getRankMobility;

/**
 * Returns the power capability of the device running the LSFSDKLightingController. The power
 * capability is used to determine the controllers rank.
 *
 * @return LSFSDKLightingController RankPower
 */
-(OEM_CS_RankParam_Power)getRankPower;

/**
 * Returns the availability capability of the device running the LSFSDKLightingController. The availability
 * capability is used to determine the controllers rank.
 *
 * @return LSFSDKLightingController RankAvailability
 */
-(OEM_CS_RankParam_Availability)getRankAvailability;

/**
 * Returns the type of the device running the LSFSDKLightingController. The node type is used to determine
 * the controllers rank.
 *
 * @return LSFSDKLightingController RankNodeType
 */
-(OEM_CS_RankParam_NodeType)getRankNodeType;

/**
 * Populate the AllJoyn AboutData parameter with device specific about data. The about
 * data will be used by the LSFSDKLightingController.
 *
 * @param aboutData Reference to LSFSDKAboutData
 */
-(void)populateDefaultProperties:(LSFSDKAboutData *)aboutData;

@end