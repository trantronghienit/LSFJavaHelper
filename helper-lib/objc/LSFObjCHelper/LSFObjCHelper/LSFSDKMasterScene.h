/******************************************************************************
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
******************************************************************************/

#import "LSFSDKLightingItem.h"
#import "LSFSDKScene.h"
#import "model/LSFMasterSceneDataModel.h"

/**
 * This class represents an LSFSDKMasterScene definition in the lighting controller. This class
 * provides an interface to perform LSFSDKMasterScene operations. Supported operations include
 * adding and removing scene members, renaming, applying and deleting the LSFSDKMasterScene.
 * Master scenes contain an array of scenes and allow them to be applied simultaneously.
 * Master scenes are considered fully initialized when the name and array of scenes have
 * been received. Master scenes are still operational and can still be applied even in the
 * uninitialized state.
 *
 * @warning This class is not meant to be instantiated directly. MasterScenes should be retrieved
 * from the LSFSDKLightingDirector using the [LSFSDKLightingDirector masterScenes] method.
 *
 * @warning This class does not support MasterScene creation. See [LSFSDKLightingDirector createMasterSceneWithScenes:name:].
 */
@interface LSFSDKMasterScene : LSFSDKLightingItem
{
    @protected LSFMasterSceneDataModel *masterSceneDataModel;
}

/** @name Initializing an LSFSDKMasterScene Object */

/**
 * @warning This method is not intended to be used by clients, and may change or be
 * removed in subsequent releases of the SDK.
 */
-(instancetype)init NS_UNAVAILABLE;

/** @name Master Scene Operations */

/**
 * Applies the current LSFSDKMasterScene in the Lighting system.
 */
-(void)apply;

/**
 * Modifies the current LSFSDKMasterScene with the provided array of Scenes.
 *
 * @param scenes  The array of new Scenes
 */
-(void)modify: (NSArray *)scenes;

/**
 * Adds a Scene to the current LSFSDKMasterScene.
 *
 * @param scene  The Scene to be added
 */
-(void)add: (LSFSDKScene *)scene;

/**
 * Removes a Scene from the current LSFSDKMasterScene
 *
 * @param scene  The Scene to be removed
 */
-(void)remove: (LSFSDKScene *)scene;

/**
 * Permanently deletes the current LSFSDKMasterScene from the lighting controller.
 */
-(void)deleteItem;

/** @name Querying a Master Scene */

/**
 * Returns all the Scenes associated with the LSFSDKMasterScene.
 *
 * @return Array of Scenes in the Master Scene
 */
-(NSArray *)getScenes;

/** Finding Objects in a Master Scene */

/**
 * Tests to see if the current LSFSDKMasterScene contains the provided LSFSDKScene.
 *
 * @param scene  LSFSDKScene to be confirmed present in the LSFSDKMasterScene
 *
 * @return Returns true if the LSFSDKMasterScene contains LSFSDKScene, false otherwise
 */
-(BOOL)hasScene: (LSFSDKScene *)scene;

/** @name Protected methods */

/**
 * @warning This method is not intended to be used by clients, and may change or be
 * removed in subsequent releases of the SDK.
 *
 * @param sceneID  The ID of the Scene
 */
-(BOOL)hasSceneWithID: (NSString *)sceneID;

/**
 * @warning This method is not intended to be used by clients, and may change or be
 * removed in subsequent releases of the SDK.
 */
-(LSFMasterSceneDataModel *)getMasterSceneDataModel;

@end