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
import org.allseen.lsf.sdk.factory.SceneV1Factory;
import org.allseen.lsf.sdk.listener.SceneCollectionListener;
import org.allseen.lsf.sdk.model.LightingItemFilter;
import org.allseen.lsf.sdk.model.SceneDataModel;

/**
 * <b>WARNING: This class is not intended to be used by clients, and its interface may change
 * in subsequent releases of the SDK</b>.
 */
public class SceneCollectionManagerV1<SCENEV1, ERROR> extends SceneCollectionManager<SCENEV1, SceneDataModel, ERROR> { //LightingItemCollectionManager<SCENEV1, SceneCollectionListener<? super SCENEV1, ? super ERROR>, SceneDataModel, ERROR> {

    protected final SceneV1Factory<SCENEV1, ERROR> factory;

    public SceneCollectionManagerV1(LightingSystemManager<?, ?, ?, ?, ?, ?, SCENEV1, ?, ?, ?, ?> manager, SceneV1Factory<SCENEV1, ERROR> factory) {
        super(manager, factory);

        this.factory = factory;
    }

    @Override
    public SCENEV1 addScene(String sceneID) {
        return addScene(sceneID, factory.createSceneV1(sceneID));
    }

    public SCENEV1 addScene(String sceneID, SCENEV1 scene) {
        return itemAdapters.put(sceneID, scene);
    }

    public SCENEV1 getScene(String sceneID) {
        return getAdapter(sceneID);
    }

    public SCENEV1[] getScenes() {
        return getAdapters().toArray(factory.createScenesV1(size()));
    }

    public SCENEV1[] getScenes(LightingItemFilter<SCENEV1> filter) {
        Collection<SCENEV1> filteredScenes = getScenesCollection(filter);
        return filteredScenes.toArray(factory.createScenesV1(filteredScenes.size()));
    }

    public Collection<SCENEV1> getScenesCollection(LightingItemFilter<SCENEV1> filter) {
        return getAdapters(filter);
    }

    public Iterator<SCENEV1> getSceneIterator() {
        return getAdapters().iterator();
    }

    public Collection<SCENEV1> removeScenes() {
        return removeAllAdapters();
    }

    @Override
    public SCENEV1 removeScene(String sceneID) {
        return removeAdapter(sceneID);
    }

    @Override
    protected void sendInitializedEvent(SceneCollectionListener<? super SCENEV1, ? super ERROR> listener, SCENEV1 scene, TrackingID trackingID) {
        listener.onSceneInitialized(trackingID, scene);
    }

    @Override
    protected void sendChangedEvent(SceneCollectionListener<? super SCENEV1, ? super ERROR> listener, SCENEV1 scene) {
        listener.onSceneChanged(scene);
    }

    @Override
    protected void sendRemovedEvent(SceneCollectionListener<? super SCENEV1, ? super ERROR> listener, SCENEV1 scene) {
        listener.onSceneRemoved(scene);
    }

    @Override
    protected void sendErrorEvent(SceneCollectionListener<? super SCENEV1, ? super ERROR> listener, ERROR error) {
        listener.onSceneError(error);
    }

    @Override
    public SceneDataModel getModel(String sceneID) {
        return getModel(getAdapter(sceneID));
    }

    @Override
    public SceneDataModel getModel(SCENEV1 scene) {
        return scene != null ? factory.findSceneDataModelV1(scene) : null;
    }
}