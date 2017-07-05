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
package org.allseen.lsf.sdk.callback;

import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;

import org.allseen.lsf.SceneElement;
import org.allseen.lsf.SceneElementManagerCallback;
import org.allseen.lsf.sdk.ResponseCode;
import org.allseen.lsf.sdk.TrackingID;
import org.allseen.lsf.sdk.manager.AllJoynManager;
import org.allseen.lsf.sdk.manager.LightingSystemManager;
import org.allseen.lsf.sdk.model.SceneElementDataModelV2;

/**
 * <b>WARNING: This class is not intended to be used by clients, and its interface may change
 * in subsequent releases of the SDK</b>.
 */
public class HelperSceneElementManagerCallback<SCENEELEMENT> extends SceneElementManagerCallback {
    protected LightingSystemManager<?, ?, ?, ?, ?, SCENEELEMENT, ?, ?, ?, ?, ?> manager;
    protected Map<String, TrackingID> creationTrackingIDs;

    public HelperSceneElementManagerCallback(LightingSystemManager<?, ?, ?, ?, ?, SCENEELEMENT, ?, ?, ?, ?, ?> manager) {
        super();

        this.manager = manager;
        this.creationTrackingIDs = new HashMap<String, TrackingID>();
    }

    @Override
    public void getAllSceneElementIDsReplyCB(ResponseCode responseCode, String[] sceneElementIDs) {
        if (!responseCode.equals(ResponseCode.OK)) {
            manager.getSceneElementCollectionManager().sendErrorEvent("getAllSceneElementIDsReplyCB", responseCode, null);
        }

        for (final String sceneElementID : sceneElementIDs) {
            postProcessSceneElementID(sceneElementID);
        }
    }

    @Override
    public void getSceneElementNameReplyCB(ResponseCode responseCode, String sceneElementID, String language, String sceneElementName) {
        if (!responseCode.equals(ResponseCode.OK)) {
            manager.getSceneElementCollectionManager().sendErrorEvent("", responseCode, sceneElementID);
        }

        postUpdateSceneElementName(sceneElementID, sceneElementName);
    }

    @Override
    public void setSceneElementNameReplyCB(ResponseCode responseCode, String sceneElementID, String language) {
        if (!responseCode.equals(ResponseCode.OK)){
            manager.getSceneElementCollectionManager().sendErrorEvent("", responseCode, sceneElementID);
        }

        AllJoynManager.sceneElementManager.getSceneElementName(sceneElementID, language);
    }

    @Override
    public void sceneElementsNameChangedCB(final String[] sceneElementIDs) {
        manager.getQueue().post(new Runnable() {
            @Override
            public void run() {
                boolean containsNewIDs = false;

                for (final String sceneElementID : sceneElementIDs) {
                    if (manager.getSceneElementCollectionManager().hasID(sceneElementID)) {
                        AllJoynManager.sceneElementManager.getSceneElementName(sceneElementID, LightingSystemManager.LANGUAGE);
                    } else {
                        containsNewIDs = true;
                    }
                }

                if (containsNewIDs) {
                    AllJoynManager.sceneElementManager.getAllSceneElementIDs();
                }
            }
        });
    }

    @Override
    public void createSceneElementReplyCB(ResponseCode responseCode, String sceneElementID, long trackingID) {
        if (!responseCode.equals(ResponseCode.OK)){
            manager.getPulseEffectCollectionManager().sendErrorEvent("createSceneElementReplyCB", responseCode, sceneElementID, new TrackingID(trackingID));
        } else {
            creationTrackingIDs.put(sceneElementID, new TrackingID(trackingID));
        }
    }

    @Override
    public void sceneElementsCreatedCB(String[] sceneElementIDs) {
        AllJoynManager.sceneElementManager.getAllSceneElementIDs();
    }

    @Override
    public void updateSceneElementReplyCB(ResponseCode responseCode, String sceneElementID) {
        if (!responseCode.equals(ResponseCode.OK)) {
            manager.getTransitionEffectCollectionManager().sendErrorEvent("updateTransitionEffectReplyCB", responseCode, sceneElementID);
        }
    }

    @Override
    public void sceneElementsUpdatedCB(String[] sceneElementIDs) {
        for (String sceneElementID : sceneElementIDs) {
            AllJoynManager.sceneElementManager.getSceneElement(sceneElementID);
        }
    }

    @Override
    public void deleteSceneElementReplyCB(ResponseCode responseCode, String sceneElementID) {
        if (!responseCode.equals(ResponseCode.OK)) {
            manager.getSceneElementCollectionManager().sendErrorEvent("deleteSceneElementReplyCB", responseCode, sceneElementID);
        }
    }

    @Override
    public void sceneElementsDeletedCB(String[] sceneElementIDs) {
        postDeleteSceneElements(sceneElementIDs);
    }

    @Override
    public void getSceneElementReplyCB(ResponseCode responseCode, String sceneElementID, SceneElement sceneElement) {
        if (!responseCode.equals(ResponseCode.OK)) {
            manager.getSceneElementCollectionManager().sendErrorEvent("getSceneElementReplyCB", responseCode, sceneElementID);
        }

        postUpdateSceneElement(sceneElementID, sceneElement);
    }

    @Override
    public void applySceneElementReplyCB(ResponseCode responseCode, String sceneElementID) {
        if (!responseCode.equals(ResponseCode.OK)) {
            manager.getSceneElementCollectionManager().sendErrorEvent("applySceneElementReplyCB", responseCode, sceneElementID);
        }
    }

    @Override
    public void sceneElementsAppliedCB(String[] sceneElementIDs) {
        // nothing to do; intentionally left empty
    }

    protected void postProcessSceneElementID(final String sceneElementID) {
        manager.getQueue().post(new Runnable() {
            @Override
            public void run() {
                if (!manager.getSceneElementCollectionManager().hasID(sceneElementID)) {
                    postUpdateSceneElementID(sceneElementID);
                    AllJoynManager.sceneElementManager.getSceneElementName(sceneElementID, LightingSystemManager.LANGUAGE);
                    AllJoynManager.sceneElementManager.getSceneElement(sceneElementID);
                }
            }
        });
    }

    protected void postUpdateSceneElementID(final String sceneElementID) {
        manager.getQueue().post(new Runnable() {
            @Override
            public void run() {
                if (!manager.getSceneElementCollectionManager().hasID(sceneElementID)) {
                    manager.getSceneElementCollectionManager().addSceneElement(sceneElementID);
                }
            }
        });

        postSendSceneElementChanged(sceneElementID);
    }

    protected void postUpdateSceneElementName(final String sceneElementID, final String sceneElementName) {
        manager.getQueue().post(new Runnable() {
            @Override
            public void run() {
                SceneElementDataModelV2 sceneElementModel = manager.getSceneElementCollectionManager().getModel(sceneElementID);

                if (sceneElementModel != null) {
                    boolean wasInitialized = sceneElementModel.isInitialized();
                    sceneElementModel.setName(sceneElementName);
                    if (wasInitialized != sceneElementModel.isInitialized()) {
                        postSendSceneElementInitialized(sceneElementID);
                    }
                }
            }
        });

        postSendSceneElementChanged(sceneElementID);
    }

    protected void postUpdateSceneElement(final String sceneElementID, final SceneElement sceneElement) {
        manager.getQueue().post(new Runnable() {
            @Override
            public void run() {
                SceneElementDataModelV2 basicSceneElementModel = manager.getSceneElementCollectionManager().getModel(sceneElementID);

                if (basicSceneElementModel != null) {
                    boolean wasInitialized = basicSceneElementModel.isInitialized();

                    basicSceneElementModel.setEffectId(sceneElement.getEffectID());
                    basicSceneElementModel.setLamps(new HashSet<String>(Arrays.asList(sceneElement.getLamps())));
                    basicSceneElementModel.setGroups(new HashSet<String>(Arrays.asList(sceneElement.getLampGroups())));

                    if (wasInitialized != basicSceneElementModel.isInitialized()) {
                        postSendSceneElementInitialized(sceneElementID);
                    }
                }
            }
        });

        postSendSceneElementChanged(sceneElementID);
    }

    protected void postDeleteSceneElements(final String[] sceneElementIDs) {
        manager.getQueue().post(new Runnable() {
            @Override
            public void run() {
                for (String sceneElementID : sceneElementIDs) {
                    manager.getSceneElementCollectionManager().removeSceneElement(sceneElementID);
                }
            }
        });
    }

    protected void postSendSceneElementChanged(final String sceneElementID) {
        manager.getQueue().post(new Runnable() {
            @Override
            public void run() {
                manager.getSceneElementCollectionManager().sendChangedEvent(sceneElementID);
            }
        });
    }

    protected void postSendSceneElementInitialized(final String sceneElementID) {
        manager.getQueue().post(new Runnable() {
            @Override
            public void run() {
                manager.getSceneElementCollectionManager().sendInitializedEvent(sceneElementID, creationTrackingIDs.remove(sceneElementID));
            }
        });
    }
}