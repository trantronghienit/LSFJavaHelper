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
package org.allseen.lsf.sdk.manager;

import java.util.Collection;
import java.util.Iterator;

import org.allseen.lsf.sdk.TrackingID;
import org.allseen.lsf.sdk.factory.GroupFactory;
import org.allseen.lsf.sdk.listener.GroupCollectionListener;
import org.allseen.lsf.sdk.model.GroupDataModel;
import org.allseen.lsf.sdk.model.GroupsFlattener;
import org.allseen.lsf.sdk.model.LightingItemFilter;

/**
 * <b>WARNING: This class is not intended to be used by clients, and its interface may change
 * in subsequent releases of the SDK</b>.
 */
public class GroupCollectionManager<GROUP, ERROR> extends LightingItemCollectionManager<GROUP, GroupCollectionListener<? super GROUP, ? super ERROR>, GroupDataModel, ERROR> {

    protected final GroupFactory<GROUP, ERROR> factory;
    protected final GroupsFlattener<GROUP> groupsFlattener;

    public GroupCollectionManager(LightingSystemManager<?, GROUP, ?, ?, ?, ?, ?, ?, ?, ?, ?> manager, GroupFactory<GROUP, ERROR> factory) {
        super(manager, factory);

        this.factory = factory;
        this.groupsFlattener = new GroupsFlattener<GROUP>(factory);
    }

    public GROUP addGroup(String groupID) {
        return addGroup(groupID, factory.createGroup(groupID));
    }

    public GROUP addGroup(String groupID, GROUP group) {
        return itemAdapters.put(groupID, group);
    }

    public GROUP getGroup(String groupID) {
        return getAdapter(groupID);
    }

    public GROUP[] getGroups() {
        return getAdapters().toArray(factory.createGroups(size()));
    }

    public GROUP[] getGroups(LightingItemFilter<GROUP> filter) {
        Collection<GROUP> filteredGroups = getGroupCollection(filter);
        return filteredGroups.toArray(factory.createGroups(filteredGroups.size()));
    }

    public Collection<GROUP> getGroupCollection(LightingItemFilter<GROUP> filter) {
        return getAdapters(filter);
    }

    public Iterator<GROUP> getGroupIterator() {
        return getAdapters().iterator();
    }

    public void flattenGroups() {
        groupsFlattener.flattenGroups(itemAdapters);
    }

    public void flattenGroup(GROUP group) {
        groupsFlattener.flattenGroup(itemAdapters, group);
    }

    public Collection<GROUP> removeGroups() {
        return removeAllAdapters();
    }

    public GROUP removeGroup(String groupID) {
        return removeAdapter(groupID);
    }

    @Override
    protected void sendInitializedEvent(GroupCollectionListener<? super GROUP, ? super ERROR> listener, GROUP group, TrackingID trackingID) {
        listener.onGroupInitialized(trackingID, group);
    }

    @Override
    protected void sendChangedEvent(GroupCollectionListener<? super GROUP, ? super ERROR> listener, GROUP group) {
        listener.onGroupChanged(group);
    }

    @Override
    protected void sendRemovedEvent(GroupCollectionListener<? super GROUP, ? super ERROR> listener, GROUP group) {
        listener.onGroupRemoved(group);
    }

    @Override
    protected void sendErrorEvent(GroupCollectionListener<? super GROUP, ? super ERROR> listener, ERROR error) {
        listener.onGroupError(error);
    }

    @Override
    public GroupDataModel getModel(String groupID) {
        return getModel(getAdapter(groupID));
    }

    @Override
    public GroupDataModel getModel(GROUP group) {
        return group != null ? factory.findGroupDataModel(group) : null;
    }
}