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

#import "LSFSDKScene.h"
#import "LSFSDKSceneElement.h"
#import "model/LSFSceneDataModelV2.h"

/**
 * This class represents a SceneV2 definition in the lighting controller. This class
 * provides an interface to perform SceneV2 operations. Supported operations include
 * modifying the scene members, applying the scene, renaming, and deleting the scene.
 * SceneV2s are only relevant in 15.04 controllers. Scenes contain an array of scene
 * elements and allows them to be applied simultaneously. Scenes are considered fully
 * initialized when the name and array of scene elements have been received. Scenes are
 * still operational and can still be applied even in the uninitialized state.
 *
 * @warning This class is not meant to be instantiated directly. SceneV2s should be retrieved
 * from the LightingDirector using the [LSFSDKLightingDirector scenes] method.
 *
 * @warning This class does not support Scene creation. See [LSFSDKLightingDirector createSceneWithSceneElements:name:].
 */
@interface LSFSDKSceneV2 : LSFSDKScene
{
    @protected LSFSceneDataModelV2 *sceneModel;
}

/** @name Initializing an LSFSDKSceneV2 Object */

/**
 * @warning This method is not intended to be used by clients, and may change or be
 * removed in subsequent releases of the SDK.
 */
-(instancetype)init NS_UNAVAILABLE;

/** @name SceneV2 Operations */

/**
 * Modifies the current LSFSDKSceneV2 using the provided array of SceneElements.
 *
 * @param sceneElements The new array of SceneElements
 */
-(void)modify: (NSArray *)sceneElements;

/**
 * Adds a LSFSDKSceneElement to the current LSFSDKSceneV2.
 *
 * @param sceneElement The LSFSDKSceneElement to be added
 */
-(void)add: (LSFSDKSceneElement *)sceneElement;

/**
 * Removes a LSFSDKSceneElement from the current LSFSDKSceneV2.
 *
 * @param sceneElement The LSFSDKSceneElement to be added
 */
-(void)remove: (LSFSDKSceneElement *)sceneElement;

/** @name Querying a SceneV2 */

/**
 * Returns all the scene element IDs associated with the current LSFSDKSceneV2.
 *
 * @return Array of scene element IDs of the SceneElements in the LSFSDKSceneV2
 */
-(NSArray *)getSceneElementIDs;

/**
 * Returns all the scene elements associated with the current LSFSDKSceneV2.
 *
 * @return Array of SceneElements in the LSFSDKSceneV2
 */
-(NSArray *)getSceneElements;

/** Finding Objects in a SceneV2 */

/**
 * Tests to see if the current LSFSDKSceneV2 contains the provided LSFSDKSceneElement.
 *
 * @param sceneElement LSFSDKSceneElement to be confirmed present in the LSFSDKSceneV2
 *
 * @return Returns true if the LSFSDKSceneV2 contains LSFSDKSceneElement, false otherwise
 */
-(BOOL)hasSceneElement: (LSFSDKSceneElement *)sceneElement;

/** @name Protected methods */

/**
 * @warning This method is not intended to be used by clients, and may change or be
 * removed in subsequent releases of the SDK.
 *
 * @param sceneElementID  The ID of the scene element
 */
-(BOOL)hasSceneElementWithID: (NSString *)sceneElementID;

/**
 * @warning This method is not intended to be used by clients, and may change or be
 * removed in subsequent releases of the SDK.
 */
-(LSFSceneDataModelV2 *)getSceneDataModel;

@end