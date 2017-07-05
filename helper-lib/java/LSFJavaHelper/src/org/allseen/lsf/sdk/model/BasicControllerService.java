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
package org.allseen.lsf.sdk.model;

import org.allseen.lsf.ControllerService;
import org.allseen.lsf.sdk.AboutData;
import org.allseen.lsf.sdk.LightingControllerConfiguration;
import org.allseen.lsf.sdk.RankAvailability;
import org.allseen.lsf.sdk.RankMobility;
import org.allseen.lsf.sdk.RankNodeType;
import org.allseen.lsf.sdk.RankPower;

public class BasicControllerService extends ControllerService {

    protected LightingControllerConfiguration controllerConfiguration;

    public BasicControllerService(LightingControllerConfiguration configuration) {
        controllerConfiguration = configuration;
    }

    public LightingControllerConfiguration getLightingControllerConfiguration() {
        return controllerConfiguration;
    }

    @Override
    public void populateDefaultProperties(AboutData aboutData) {
        controllerConfiguration.populateDefaultProperties(aboutData);
    }

    @Override
    public String getMacAddress(String generatedMacAddress) {
        return controllerConfiguration.getMacAddress(generatedMacAddress);
    }

    @Override
    public boolean isNetworkConnected() {
        return controllerConfiguration.isNetworkConnected();
    }

    @Override
    public RankMobility getRankMobility() {
        return controllerConfiguration.getRankMobility();
    }

    @Override
    public RankPower getRankPower() {
        return controllerConfiguration.getRankPower();
    }

    @Override
    public RankAvailability getRankAvailability() {
        return controllerConfiguration.getRankAvailability();
    }

    @Override
    public RankNodeType getRankNodeType() {
        return controllerConfiguration.getRankNodeType();
    }
}
