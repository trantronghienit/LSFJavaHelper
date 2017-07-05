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

package org.allseen.lsf;

import org.allseen.lsf.sdk.ResponseCode;

public class SceneManagerCallback extends DefaultNativeClassWrapper {

    public SceneManagerCallback() {
        createNativeObject();
    }

    public void getAllSceneIDsReplyCB(ResponseCode responseCode, String[] sceneIDs)                                         { }
    public void getSceneNameReplyCB(ResponseCode responseCode, String sceneID, String language, String sceneName)           { }
    public void setSceneNameReplyCB(ResponseCode responseCode, String sceneID, String language)                             { }
    public void scenesNameChangedCB(String[] sceneIDs)                                                                      { }
    public void createSceneReplyCB(ResponseCode responseCode, String sceneID)                                               { }
    public void scenesCreatedCB(String[] sceneIDs)                                                                          { }
    public void updateSceneReplyCB(ResponseCode responseCode, String sceneID)                                               { }
    public void scenesUpdatedCB(String[] sceneIDs)                                                                          { }
    public void deleteSceneReplyCB(ResponseCode responseCode, String sceneID)                                               { }
    public void scenesDeletedCB(String[] sceneIDs)                                                                          { }
    public void getSceneReplyCB(ResponseCode responseCode, String sceneID, Scene scene)                                     { }
    public void applySceneReplyCB(ResponseCode responseCode, String sceneID)                                                { }
    public void scenesAppliedCB(String[] sceneIDs)                                                                          { }
    public void createSceneWithTrackingReplyCB(ResponseCode responseCode, String sceneID, long trackingID)                  { }
    public void createSceneWithSceneElementsReplyCB(ResponseCode responseCode, String sceneID, long trackingID)             { }
    public void updateSceneWithSceneElementsReplyCB(ResponseCode responseCode, String sceneID)                              { }
    public void getSceneWithSceneElementsReplyCB(ResponseCode responseCode, String sceneID, SceneWithSceneElements scene)   { }

    // @Override
    @Override
    protected native void createNativeObject();

    // @Override
    @Override
    protected native void destroyNativeObject();
}