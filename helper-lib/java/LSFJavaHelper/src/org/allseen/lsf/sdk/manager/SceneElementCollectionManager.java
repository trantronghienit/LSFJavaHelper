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
import org.allseen.lsf.sdk.factory.SceneElementFactory;
import org.allseen.lsf.sdk.listener.SceneElementCollectionListener;
import org.allseen.lsf.sdk.model.LightingItemFilter;
import org.allseen.lsf.sdk.model.SceneElementDataModelV2;

/**
 * <b>WARNING: This class is not intended to be used by clients, and its interface may change
 * in subsequent releases of the SDK</b>.
 */
public class SceneElementCollectionManager<SCENEELEMENT, ERROR> extends LightingItemCollectionManager<SCENEELEMENT, SceneElementCollectionListener<? super SCENEELEMENT, ? super ERROR>, SceneElementDataModelV2, ERROR> {

    protected final SceneElementFactory<SCENEELEMENT, ERROR> factory;

    public SceneElementCollectionManager(LightingSystemManager<?, ?, ?, ?, ?, SCENEELEMENT, ?, ?, ?, ?, ?> manager, SceneElementFactory<SCENEELEMENT, ERROR> factory) {
        super(manager, factory);

        this.factory = factory;
    }

    public SCENEELEMENT addSceneElement(String sceneElementID) {
        return addSceneElement(sceneElementID, factory.createSceneElement(sceneElementID));
    }

    public SCENEELEMENT addSceneElement(String sceneElementID, SCENEELEMENT sceneElement) {
        return itemAdapters.put(sceneElementID, sceneElement);
    }

    public SCENEELEMENT getSceneElement(String sceneElementID) {
        return getAdapter(sceneElementID);
    }

    public SCENEELEMENT[] getSceneElements() {
        return getAdapters().toArray(factory.createSceneElements(size()));
    }

    public SCENEELEMENT[] getSceneElements(LightingItemFilter<SCENEELEMENT> filter) {
        Collection<SCENEELEMENT> filteredSceneElements = getSceneElementsCollection(filter);
        return filteredSceneElements.toArray(factory.createSceneElements(filteredSceneElements.size()));
    }

    public Collection<SCENEELEMENT> getSceneElementsCollection(LightingItemFilter<SCENEELEMENT> filter) {
        return getAdapters(filter);
    }

    public Iterator<SCENEELEMENT> getSceneElementIterator() {
        return getAdapters().iterator();
    }

    public Collection<SCENEELEMENT> removeSceneElements() {
        return removeAllAdapters();
    }

    public SCENEELEMENT removeSceneElement(String sceneElementId) {
        return removeAdapter(sceneElementId);
    }

    @Override
    protected void sendInitializedEvent(SceneElementCollectionListener<? super SCENEELEMENT, ? super ERROR> listener, SCENEELEMENT sceneElement, TrackingID trackingID) {
        listener.onSceneElementInitialized(trackingID, sceneElement);
    }

    @Override
    protected void sendChangedEvent(SceneElementCollectionListener<? super SCENEELEMENT, ? super ERROR> listener, SCENEELEMENT sceneElement) {
        listener.onSceneElementChanged(sceneElement);
    }

    @Override
    protected void sendRemovedEvent(SceneElementCollectionListener<? super SCENEELEMENT, ? super ERROR> listener, SCENEELEMENT sceneElement) {
        listener.onSceneElementRemoved(sceneElement);
    }

    @Override
    protected void sendErrorEvent(SceneElementCollectionListener<? super SCENEELEMENT, ? super ERROR> listener, ERROR error) {
        listener.onSceneElementError(error);
    }

    @Override
    public SceneElementDataModelV2 getModel(String sceneElementId) {
        return getModel(getAdapter(sceneElementId));
    }

    @Override
    public SceneElementDataModelV2 getModel(SCENEELEMENT sceneElement) {
        return sceneElement != null ? factory.findSceneElementDataModel(sceneElement) : null;
    }
}