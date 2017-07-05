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

import java.util.Collections;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import org.allseen.lsf.sdk.factory.GroupFactory;

/**
 * <b>WARNING: This class is not intended to be used by clients, and its interface may change
 * in subsequent releases of the SDK</b>.
 */
public class GroupsFlattener<GROUP> {
    protected final GroupFactory<GROUP, ?> factory;

    protected Set<String> groupIDSet;
    protected Set<String> lampIDSet;
    protected int duplicates;

    public GroupsFlattener(GroupFactory<GROUP, ?> factory) {
        super();

        this.factory = factory;
    }

    public void flattenGroups(Map<String, GROUP> groups) {
        for (GROUP group : groups.values()) {
            flattenGroup(groups, group);
        }
    }

    public void flattenGroup(Map<String, GROUP> groups, GROUP group) {
        GroupDataModel groupModel = factory.findGroupDataModel(group);

        groupIDSet = new HashSet<String>();
        lampIDSet = new HashSet<String>();
        duplicates = 0;

        walkGroups(groups, groupModel);
        walkLamps(groups);

        groupModel.setGroups(groupIDSet);
        groupModel.setLamps(lampIDSet);
        groupModel.duplicates = duplicates;
    }

    protected void walkGroups(Map<String, GROUP> groups, GroupDataModel parentModel) {
        if (!groupIDSet.contains(parentModel.id)) {
            groupIDSet.add(parentModel.id);

            for (String childGroupID : parentModel.members.getLampGroups()) {
                GROUP childGroup = groups.get(childGroupID);
                GroupDataModel childModel = childGroup != null ? factory.findGroupDataModel(childGroup) : null;

                if (childModel != null) {
                    walkGroups(groups, childModel);
                }
            }
        } else {
            duplicates++;
        }
    }

    protected void walkLamps(Map<String, GROUP> groups) {
        for (String groupID : groupIDSet) {
            GROUP group = groups.get(groupID);
            GroupDataModel groupModel = group != null ? factory.findGroupDataModel(group) : null;

            if (groupModel != null) {
                Collections.addAll(lampIDSet, groupModel.members.getLamps());
            }
        }
    }
}