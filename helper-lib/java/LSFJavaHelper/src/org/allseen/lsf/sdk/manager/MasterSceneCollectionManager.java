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
import org.allseen.lsf.sdk.factory.MasterSceneFactory;
import org.allseen.lsf.sdk.listener.MasterSceneCollectionListener;
import org.allseen.lsf.sdk.model.LightingItemFilter;
import org.allseen.lsf.sdk.model.MasterSceneDataModel;

/**
 * <b>WARNING: This class is not intended to be used by clients, and its interface may change
 * in subsequent releases of the SDK</b>.
 */
public class MasterSceneCollectionManager<MASTERSCENE, ERROR> extends LightingItemCollectionManager<MASTERSCENE, MasterSceneCollectionListener<? super MASTERSCENE, ? super ERROR>, MasterSceneDataModel, ERROR> {

    protected final MasterSceneFactory<MASTERSCENE, ERROR> factory;

    public MasterSceneCollectionManager(LightingSystemManager<?, ?, ?, ?, ?, ?, ?, ?, MASTERSCENE, ?, ?> manager, MasterSceneFactory<MASTERSCENE, ERROR> factory) {
        super(manager, factory);

        this.factory = factory;
    }

    public MASTERSCENE addMasterScene(String masterSceneID) {
        return addMasterScene(masterSceneID, factory.createMasterScene(masterSceneID));
    }

    public MASTERSCENE addMasterScene(String masterSceneID, MASTERSCENE scene) {
        return itemAdapters.put(masterSceneID, scene);
    }

    public MASTERSCENE getMasterScene(String masterSceneID) {
        return getAdapter(masterSceneID);
    }

    public MASTERSCENE[] getMasterScenes() {
        return getAdapters().toArray(factory.createMasterScenes(size()));
    }

    public MASTERSCENE[] getMasterScenes(LightingItemFilter<MASTERSCENE> filter) {
        Collection<MASTERSCENE> filteredMasterScenes = getMasterScenesCollection(filter);
        return filteredMasterScenes.toArray(factory.createMasterScenes(filteredMasterScenes.size()));
    }

    public Collection<MASTERSCENE> getMasterScenesCollection(LightingItemFilter<MASTERSCENE> filter) {
        return getAdapters(filter);
    }

    public Iterator<MASTERSCENE> getMasterSceneIterator() {
        return getAdapters().iterator();
    }

    public Collection<MASTERSCENE> removeMasterScenes() {
        return removeAllAdapters();
    }

    public MASTERSCENE removeMasterScene(String masterSceneID) {
        return removeAdapter(masterSceneID);
    }

    @Override
    protected void sendInitializedEvent(MasterSceneCollectionListener<? super MASTERSCENE, ? super ERROR> listener, MASTERSCENE masterScene, TrackingID trackingID) {
        listener.onMasterSceneInitialized(trackingID, masterScene);
    }

    @Override
    protected void sendChangedEvent(MasterSceneCollectionListener<? super MASTERSCENE, ? super ERROR> listener, MASTERSCENE masterScene) {
        listener.onMasterSceneChanged(masterScene);
    }

    @Override
    protected void sendRemovedEvent(MasterSceneCollectionListener<? super MASTERSCENE, ? super ERROR> listener, MASTERSCENE masterScene) {
        listener.onMasterSceneRemoved(masterScene);
    }

    @Override
    protected void sendErrorEvent(MasterSceneCollectionListener<? super MASTERSCENE, ? super ERROR> listener, ERROR error) {
        listener.onMasterSceneError(error);
    }

    @Override
    public MasterSceneDataModel getModel(String masterSceneID) {
        return getModel(getAdapter(masterSceneID));
    }

    @Override
    public MasterSceneDataModel getModel(MASTERSCENE masterScene) {
        return masterScene != null ? factory.findMasterSceneDataModel(masterScene) : null;
    }
}