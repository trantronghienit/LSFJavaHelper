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

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

import org.allseen.lsf.sdk.manager.AllJoynManager;
import org.allseen.lsf.sdk.model.LightingItemDataModel;
import org.allseen.lsf.sdk.model.LightingItemUtil;
import org.allseen.lsf.sdk.model.SceneDataModelV2;

/**
 * This class represents a SceneV2 definition in the lighting controller. This class
 * provides an interface to perform SceneV2 operations. Supported operations include
 * modifying the scene members, applying the scene, renaming, and deleting the scene.
 * SceneV2s are only relevant in 15.04 controllers. Scenes contain an array of scene
 * elements and allows them to be applied simultaneously. Scenes are considered fully
 * initialized when the name and array of scene elements have been received. Scenes are
 * still operational and can still be applied even in the uninitialized state.
 * <p>
 * <b>Note: This class is not meant to be instantiated directly. SceneV2s should be retrieved
 * from the LightingDirector using the {@link LightingDirector#getScenes()} method.</b>
 * <p>
 * <b>Note: This class does not support Scene creation. See {@link LightingDirector#createScene(SceneElement[], String)}.</b>
 */
public class SceneV2 extends Scene {
    /**
     * Specifies the default name of the SceneV2.
     *
     * @param defaultName Default name of the SceneV2
     */
    public static void setDefaultName(String defaultName) {
        if (defaultName != null) {
            SceneDataModelV2.defaultName = defaultName;
        }
    }

    protected SceneDataModelV2 sceneModel;

    /**
     * Constructs a Scene using the specified ID.
     * <p>
     * <b>WARNING: This method is intended to be used internally. Client software should not instantiate
     * Scenes directly, but should instead get them from the {@link LightingDirector} using the
     * {@link LightingDirector#getScenes()} method.</b>
     *
     * @param sceneID The ID of the Scene.
     */
    protected SceneV2(String sceneID) {
        this(new SceneDataModelV2(sceneID));
    }

    /**
     * Constructs a Scene using the specified scene model.
     * <p>
     * <b>WARNING: This method is intended to be used internally. Client software should not instantiate
     * Scenes directly, but should instead get them from the {@link LightingDirector} using the
     * {@link LightingDirector#getScenes()} method.</b>
     *
     * @param sceneModel The Scene model to use.
     */
    protected SceneV2(SceneDataModelV2 sceneModel) {
        super();

        this.sceneModel = sceneModel;
    }

    /**
     * Modifies the current SceneV2 using the provided array of SceneElements.
     *
     * @param elements The new array of SceneElements
     */
    public void modify(SceneElement[] elements) {
        String errorContext = "SceneV2.modify() error";

        if (postInvalidArgIfNull(errorContext, elements)) {

            String[] sceneElementIds = new String[elements.length];
            for (int i = 0; i < elements.length; i++) {
                sceneElementIds[i] = elements[i].getId();
            }

            postErrorIfFailure(errorContext,
                    AllJoynManager.sceneManager.updateSceneWithSceneElements(sceneModel.id, LightingItemUtil.createSceneWithSceneElements(sceneElementIds)));
        }
    }

    /**
     * Adds a SceneElement to the current SceneV2.
     *
     * @param element The SceneElement to be added
     */
    public void add(SceneElement element) {
        String errorContext = "SceneV2.add() error";

        if (postInvalidArgIfNull(errorContext, element)) {
            Set<String> sceneElementIds = new HashSet<String>(Arrays.asList(getSceneElementIDs()));
            // only update this SceneElement if it does not already contain the Scene to add
            if (sceneElementIds.add(element.getId())) {
                postErrorIfFailure(errorContext,
                        AllJoynManager.sceneManager.updateSceneWithSceneElements(sceneModel.id, LightingItemUtil.createSceneWithSceneElements(
                                sceneElementIds.toArray(new String[sceneElementIds.size()]))));
            }
        }
    }

    /**
     * Removes a SceneElement from the current SceneV2.
     *
     * @param element The SceneElement to be removed
     */
    public void remove(SceneElement element) {
        String errorContext = "SceneV2.remove() error";

        if (postInvalidArgIfNull(errorContext, element)) {
            Set<String> sceneElementIds = new HashSet<String>(Arrays.asList(getSceneElementIDs()));
            // only update this SceneElement if it contains the scene to remove
            if (sceneElementIds.remove(element.getId())) {
                postErrorIfFailure(errorContext,
                        AllJoynManager.sceneManager.updateSceneWithSceneElements(sceneModel.id, LightingItemUtil.createSceneWithSceneElements(
                                sceneElementIds.toArray(new String[sceneElementIds.size()]))));
            }
        }
    }

    /**
     * <b>WARNING: This method is not intended to be used by clients, and may change or be
     * removed in subsequent releases of the SDK.</b>
     */
    @Override
    protected LightingItemDataModel getItemDataModel() {
        return getSceneDataModel();
    }

    /**
     * Returns all the scene element IDs associated with the current SceneV2.
     *
     * @return Array of scene element IDs of the SceneElements in the SceneV2
     */
    public String[] getSceneElementIDs() {
        return sceneModel.getSceneElementIDs();
    }

    /**
     * Returns all the scene elements associated with the current SceneV2.
     *
     * @return Array of SceneElements in the SceneV2
     */
    public SceneElement[] getSceneElements() {
        return LightingDirector.get().getSceneElements(Arrays.asList(getSceneElementIDs()));
    }

    /**
     * Tests to see if the current SceneV2 contains the provided Lighting item.
     *
     * @param item Lighting item to be confirmed present in the SceneV2
     *
     * @return Returns true if the SceneV2 contains Lighting item, false otherwise
     */
    @Override
    public boolean hasComponent(LightingItem item) {
        String errorContext = "SceneV2.hasComponent() error";
        return postInvalidArgIfNull(errorContext, item) ? hasSceneElement(item.getId()) : false;
    }

    /**
     * Tests to see if the current SceneV2 contains the provided SceneElement.
     *
     * @param sceneElement SceneElement to be confirmed present in the SceneV2
     *
     * @return Returns true if the SceneV2 contains SceneElement, false otherwise
     */
    public boolean hasSceneElement(SceneElement sceneElement) {
        String errorContext = "SceneV2.hasSceneElement() error";
        return postInvalidArgIfNull(errorContext, sceneElement) ? hasSceneElement(sceneElement.getId()) : false;
    }

    /**
     * <b>WARNING: This method is not intended to be used by clients, and may change or be
     * removed in subsequent releases of the SDK.</b>
     */
    protected boolean hasSceneElement(String sceneElementID) {
        return sceneModel.containsSceneElement(sceneElementID);
    }

    /**
     * <b>WARNING: This method is not intended to be used by clients, and may change or be
     * removed in subsequent releases of the SDK.</b>
     */
    protected SceneDataModelV2 getSceneDataModel() {
        return sceneModel;
    }

    /**
     * <b>WARNING: This method is not intended to be used by clients, and may change or be
     * removed in subsequent releases of the SDK.</b>
     */
    @Override
    protected void postError(final String name, final ResponseCode status) {
        LightingDirector.get().getLightingSystemManager().getQueue().post(new Runnable() {
            @Override
            public void run() {
                LightingDirector.get().getSceneCollectionManagerV2().sendErrorEvent(name, status, getId());
            }
        });
    }
}