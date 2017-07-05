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

import org.allseen.lsf.SceneWithSceneElements;

public class SceneDataModelV2 extends LightingItemDataModel {
    public static final char TAG_PREFIX_SCENE_WITH_ELEMENTS = 'S';

    public static String defaultName = "<Loading scene info ...>";

    private SceneWithSceneElements sceneWithSceneElements;

    protected boolean sceneWithSceneElementsInitialized;

    public SceneDataModelV2() {
        this((String)null);
    }

    public SceneDataModelV2(String sceneID) {
        this(sceneID, null);
    }

    public SceneDataModelV2(String sceneID, String sceneName) {
        super(sceneID, TAG_PREFIX_SCENE_WITH_ELEMENTS, sceneName != null ? sceneName : defaultName);

        sceneWithSceneElements = null;
        sceneWithSceneElementsInitialized = false;
    }

    public SceneDataModelV2(SceneDataModelV2 other) {
        super(other);

        sceneWithSceneElements = other.sceneWithSceneElements;

        sceneWithSceneElementsInitialized = other.sceneWithSceneElementsInitialized;
    }

    public String[] getSceneElementIDs() {
        return sceneWithSceneElements != null ? sceneWithSceneElements.getSceneElements() : LightingItemUtil.NO_ITEM_IDS;
    }

    public SceneWithSceneElements getSceneWithSceneElements() {
        return sceneWithSceneElements;
    }

    public void setSceneWithSceneElements(SceneWithSceneElements scene) {
        sceneWithSceneElements = scene;
        sceneWithSceneElementsInitialized = true;
    }

    public boolean containsSceneElement(String sceneElementID) {
        String[] childIDs = sceneWithSceneElements.getSceneElements();

        for (String childID : childIDs) {
            if (childID.equals(sceneElementID)) {
                return true;
            }
        }

        return false;
    }

    @Override
    public boolean isInitialized() {
        return super.isInitialized() && sceneWithSceneElementsInitialized;
    }
}