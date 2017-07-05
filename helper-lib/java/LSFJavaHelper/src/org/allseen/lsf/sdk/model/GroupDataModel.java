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

import java.util.HashSet;
import java.util.Set;

import org.allseen.lsf.LampGroup;

/**
 * <b>WARNING: This class is not intended to be used by clients, and its interface may change
 * in subsequent releases of the SDK</b>.
 */
public class GroupDataModel extends ColorItemDataModel {
    public static final char TAG_PREFIX_GROUP = 'G';

    public static String defaultName = "<Loading group info...>";

    public LampGroup members;
    private Set<String> lamps;
    private Set<String> groups;
    public int duplicates;
    public int viewColorTempMin;
    public int viewColorTempMax;

    public GroupDataModel() {
        this((String)null);
    }

    public GroupDataModel(String groupID) {
        this(groupID, null);
    }

    public GroupDataModel(String groupID, String groupName) {
        this(groupID, TAG_PREFIX_GROUP, groupName);
    }

    public GroupDataModel(String groupID, char prefix, String groupName) {
        super(groupID, prefix, groupName != null ? groupName : defaultName);

        members = new LampGroup();

        viewColorTempMin = ColorStateConverter.VIEW_COLORTEMP_MIN;
        viewColorTempMax = ColorStateConverter.VIEW_COLORTEMP_MAX;
    }

    public GroupDataModel(GroupDataModel other) {
        super(other);

        this.members = new LampGroup(other.members);
        this.lamps = new HashSet<String>(other.lamps);
        this.groups = new HashSet<String>(other.groups);
        this.duplicates = other.duplicates;
        this.viewColorTempMax = other.viewColorTempMax;
        this.viewColorTempMin = other.viewColorTempMin;
    }

    public void setLamps(Set<String> lamps) {
        this.lamps = lamps;
        // capability data is now dirty
        capability = new LampCapabilities();
    }

    public void setGroups(Set<String> groups) {
        this.groups = groups;
        // capability data is now dirty
        capability = new LampCapabilities();
    }

    public void setMembers(LampGroup members) {
        this.members = members;
        // a group's state is initialized when its LampGroup container is set
        stateInitialized = true;
    }

    public Set<String> getLamps() {
        return lamps;
    }

    public Set<String> getGroups() {
        return groups;
    }

    public LampGroup getMembers() {
        return this.members;
    }

    // Only checks immediate child lamps. To see if the lamp is in a descendent (child, grandchild,
    // great-grandchild, etc.) group, you can use getLamps().contains(lampID);
    public boolean containsLamp(String lampID) {
        String[] childIDs = members.getLamps();

        for (String childID : childIDs) {
            if (childID.equals(lampID)) {
                return true;
            }
        }

        return false;
    }

    // Only checks immediate child groups. To see if the group is a descendent (child, grandchild,
    // great-grandchild, etc.) you can use getGroups().contains(groupID);
    public boolean containsGroup(String groupID) {
        String[] childIDs = members.getLampGroups();

        for (String childID : childIDs) {
            if (childID.equals(groupID)) {
                return true;
            }
        }

        return false;
    }

    @Override
    public boolean isInitialized() {
        return super.isInitialized();
    }
}