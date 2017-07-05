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
#import "LSFSDKPreset.h"
#import "LSFSDKGroup.h"
#import "model/LSFSceneDataModel.h"

/**
 * This class represents a SceneV1 definition in the lighting controller. SceneV1s are only
 * relevant in pre 15.04 controllers. This class provides an interface to perform SceneV1
 * operations. Supported operations include renaming, applying, and deleting the SceneV1.
 *
 * @warning This class is not meant to be instantiated directly. SceneV1s should be retrieved
 * from the LightingDirector using the [LSFSDKLightingDirector scenes] method.
 *
 * @warning This class does not support SceneV1 creation. SceneV1s must already exist on the
 * lighting controller.
 */
@interface LSFSDKSceneV1 : LSFSDKScene
{
    @protected LSFSceneDataModel *sceneModel;
}

/** @name Initializing an LSFSDKSceneV1 Object */

/**
 * @warning This method is not intended to be used by clients, and may change or be
 * removed in subsequent releases of the SDK.
 */
-(instancetype)init NS_UNAVAILABLE;

/** @name Finding Objects in a SceneV1 */

/**
 * Tests to see if the current LSFSDKSceneV1 contains the provided LSFSDKPreset.
 *
 * @param preset The LSFSDKPreset to be confirmed a component of the LSFSDKSceneV1
 *
 * @return Returns true if the LSFSDKSceneV1 contains the LSFSDKPreset, false otherwise
 */
-(BOOL)hasPreset: (LSFSDKPreset *)preset;

/**
 * Tests to see if the current LSFSDKSceneV1 contains the provided LSFSDKGroup.
 *
 * @param group The LSFSDKGroup to be confirmed a component of the LSFSDKSceneV1
 *
 * @return Returns true if the LSFSDKSceneV1 contains the LSFSDKGroup, false otherwise
 */
-(BOOL)hasGroup: (LSFSDKGroup *)group;

/** @name Protected methods */

/**
 * @warning This method is not intended to be used by clients, and may change or be
 * removed in subsequent releases of the SDK.
 *
 * @param presetID  The ID of the preset
 */
-(BOOL)hasPresetWithID: (NSString *)presetID;

/**
 * @warning This method is not intended to be used by clients, and may change or be
 * removed in subsequent releases of the SDK.
 *
 * @param groupID  The ID of the group
 */
-(BOOL)hasGroupWithID: (NSString *)groupID;

/**
 * @warning This method is not intended to be used by clients, and may change or be
 * removed in subsequent releases of the SDK.
 */
-(LSFSceneDataModel *)getSceneDataModel;

@end