/*
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
*/
package org.allseen.lsf.sdk;

import org.allseen.lsf.sdk.RankAvailability;
import org.allseen.lsf.sdk.RankMobility;
import org.allseen.lsf.sdk.RankNodeType;
import org.allseen.lsf.sdk.RankPower;

/**
 * Provides an interface for developers to implement and define the application and device
 * specific properties for the lighting controller. This includes AllJoyn keystore file path,
 * AllJoyn about properties, device MAC address, and other device specific properties.
 * <p>
 * <b>Note: Once implemented, the configuration must be registered with the LightingController
 * using the {@link LightingController#init(LightingControllerConfiguration) init} method.</b>
 */
public interface LightingControllerConfiguration {

    /**
     * Returns the file location to save LighingController specific files.
     *
     * @return Absolute directory path to be used for file storage.
     */
    public String getKeystorePath();

    /**
     * Populate the AllJoyn AboutData parameter with device specific about data. The about
     * data will be used by the LighingController.
     *
     * @param aboutData Reference to AllJoyn AboutData
     */
    public void populateDefaultProperties(AboutData aboutData);

    /**
     * Returns the MAC address of the device running the LightingController.
     * <p>
     * <b>Note: The MAC address is expected to be a 12-digit hex string (i.e. "XXXXXXXXXXXX").</b>
     *
     * @param generatedMacAddress
     *             Random hex string which can be used as the MAC address on devices where it cannot
     *             be queried.
     *
     * @return The 12-digit HEX string MAC address of the device
     */
    public String getMacAddress(String generatedMacAddress);

    /**
     * Determines if their is a network available for LightingController to connect.
     *
     * @return Return true their is a network available for the controller, false otherwise.
     */
    public boolean isNetworkConnected();

    /**
     * Returns the mobility capability of the device running the LightingController. The mobility
     * capability is used to determine the controllers rank.
     *
     * @return LightingController RankMobility
     */
    public RankMobility getRankMobility();

    /**
     * Returns the power capability of the device running the LightingController. The power
     * capability is used to determine the controllers rank.
     *
     * @return LightingController RankPower
     */
    public RankPower getRankPower();

    /**
     * Returns the availability capability of the device running the LightingController. The availability
     * capability is used to determine the controllers rank.
     *
     * @return LightingController RankAvailability
     */
    public RankAvailability getRankAvailability();

    /**
     * Returns the type of the device running the LightingController. The node type is used to determine
     * the controllers rank.
     *
     * @return LightingController RankNodeType
     */
    public RankNodeType getRankNodeType();
}