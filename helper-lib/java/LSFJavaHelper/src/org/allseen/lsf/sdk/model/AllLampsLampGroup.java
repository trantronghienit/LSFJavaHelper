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

import org.allseen.lsf.LampGroup;
import org.allseen.lsf.sdk.ResponseCode;
import org.allseen.lsf.sdk.manager.LightingSystemManager;

/**
 * <b>WARNING: This class is not intended to be used by clients, and its interface may change
 * in subsequent releases of the SDK</b>.
 */
public class AllLampsLampGroup extends LampGroup {
    public static final AllLampsLampGroup instance = new AllLampsLampGroup();

    protected LightingSystemManager<?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?> manager;

    protected AllLampsLampGroup() {
        super();

        this.manager = null;
    }

    public void setLightingSystemManager(LightingSystemManager<?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?> manager) {
        this.manager = manager;
    }

    @Override
    public void setLamps(String[] lampIDs) {
        // Invalid attempt to set the lamp members of the all-lamp lamp group
        manager.getGroupCollectionManager().sendErrorEvent("AllLampsLampGroup.setLamps", ResponseCode.ERR_INVALID);
    }

    @Override
    public String[] getLamps() {
        return manager != null ? manager.getLampCollectionManager().getIDArray() : LightingItemUtil.NO_ITEM_IDS;
    }

    @Override
    public void setLampGroups(String[] lampGroupIDs) {
        // Invalid attempt to set the group members of the all-lamp lamp group
        manager.getGroupCollectionManager().sendErrorEvent("AllLampsLampGroup.setLampGroups", ResponseCode.ERR_INVALID);
    }

    @Override
    public String[] getLampGroups() {
        return new String[] {};
    }

    @Override
    public ResponseCode isDependentLampGroup(String lampGroupID) {
        // This lamp group does not contain any other group
        return ResponseCode.OK;
    }

}