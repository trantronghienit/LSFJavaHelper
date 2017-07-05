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

import org.allseen.lsf.sdk.model.LightingItemDataModel;
import org.allseen.lsf.sdk.model.SceneDataModel;

/**
 * This class represents a SceneV1 definition in the lighting controller. SceneV1s are only
 * relevant in pre 15.04 controllers. This class provides an interface to perform SceneV1
 * operations. Supported operations include renaming, applying, and deleting the SceneV1.
 * <p>
 * <b>Note: This class is not meant to be instantiated directly. SceneV1s should be retrieved
 * from the LightingDirector using the {@link LightingDirector#getScenes()} method.</b>
 * <p>
 * <b>Note: This class does not support SceneV1 creation. SceneV1s must already exist on the
 * lighting controller.</b>
 */
public class SceneV1 extends Scene {
    /**
     * Specifies the default name of the SceneV1.
     *
     * @param defaultName Default name of the SceneV1
     */
    public static void setDefaultName(String defaultName) {
        if (defaultName != null) {
            SceneDataModel.defaultName = defaultName;
        }
    }

    protected SceneDataModel sceneModel;

    /**
     * Constructs a Scene using the specified ID.
     * <p>
     * <b>WARNING: This method is intended to be used internally. Client software should not instantiate
     * Scenes directly, but should instead get them from the {@link LightingDirector} using the
     * {@link LightingDirector#getScenes()} method.</b>
     *
     * @param sceneID The ID of the Scene.
     */
    protected SceneV1(String sceneID) {
        this(new SceneDataModel(sceneID));
    }

    /**
     * Constructs a Scene using the specified scene.
     * <p>
     * <b>WARNING: This method is intended to be used internally. Client software should not instantiate
     * Scenes directly, but should instead get them from the {@link LightingDirector} using the
     * {@link LightingDirector#getScenes()} method.</b>
     *
     * @param scene The Scene to use.
     */
    protected SceneV1(SceneV1 scene) {
        this(new SceneDataModel(scene.getSceneDataModel()));
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
    protected SceneV1(SceneDataModel sceneModel) {
        super();

        this.sceneModel = sceneModel;
    }

    /**
     * Tests to see if the current SceneV1 contains the provided Lighting item.
     *
     * @param item Lighting item to be confirmed present in the SceneV1
     *
     * @return Returns true if the SceneV1 contains Lighting item, false otherwise
     */
    @Override
    public boolean hasComponent(LightingItem item) {
        String errorContext = "SceneV1.hasComponent() error";
        return postInvalidArgIfNull(errorContext, item) ? hasPreset(item.getId()) || hasGroup(item.getId()) : false;
    }

    /**
     * Tests to see if the current SceneV1 contains the provided Preset.
     *
     * @param preset The Preset to be confirmed a component of the SceneV1
     *
     * @return Returns true if the SceneV1 contains the Preset, false otherwise
     */
    public boolean hasPreset(Preset preset) {
        String errorContext = "SceneV1.hasPreset() error";
        return postInvalidArgIfNull(errorContext, preset) ? hasPreset(preset.getId()) : false;
    }

    /**
     * Tests to see if the current SceneV1 contains the provided Group.
     *
     * @param group The Group to be confirmed a component of the SceneV1
     *
     * @return Returns true if the SceneV1 contains the Group, false otherwise
     */
    public boolean hasGroup(Group group) {
        String errorContext = "SceneV1.hasGroup() error";
        return postInvalidArgIfNull(errorContext, group) ? hasGroup(group.getId()) : false;
    }

    /**
     * <b>WARNING: This method is not intended to be used by clients, and may change or be
     * removed in subsequent releases of the SDK.</b>
     */
    protected boolean hasPreset(String presetID) {
        return sceneModel.containsPreset(presetID);
    }

    /**
     * <b>WARNING: This method is not intended to be used by clients, and may change or be
     * removed in subsequent releases of the SDK.</b>
     */
    protected boolean hasGroup(String groupID) {
        return sceneModel.containsGroup(groupID);
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
     * <b>WARNING: This method is not intended to be used by clients, and may change or be
     * removed in subsequent releases of the SDK.</b>
     */
    protected SceneDataModel getSceneDataModel() {
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
                LightingDirector.get().getSceneCollectionManager().sendErrorEvent(name, status, getId());
            }
        });
    }
}