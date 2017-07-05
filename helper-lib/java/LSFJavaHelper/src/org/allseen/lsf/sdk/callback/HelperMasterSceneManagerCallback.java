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

import java.util.HashMap;
import java.util.Map;

import org.allseen.lsf.MasterScene;
import org.allseen.lsf.MasterSceneManagerCallback;
import org.allseen.lsf.sdk.ResponseCode;
import org.allseen.lsf.sdk.TrackingID;
import org.allseen.lsf.sdk.manager.AllJoynManager;
import org.allseen.lsf.sdk.manager.LightingSystemManager;
import org.allseen.lsf.sdk.model.MasterSceneDataModel;

/**
 * <b>WARNING: This class is not intended to be used by clients, and its interface may change
 * in subsequent releases of the SDK</b>.
 */
public class HelperMasterSceneManagerCallback<MASTERSCENE> extends MasterSceneManagerCallback {
    protected LightingSystemManager<?, ?, ?, ?, ?, ?, ?, ?, MASTERSCENE, ?, ?> manager;
    protected Map<String, TrackingID> creationTrackingIDs;

    public HelperMasterSceneManagerCallback(LightingSystemManager<?, ?, ?, ?, ?, ?, ?, ?, MASTERSCENE, ?, ?> manager) {
        super();

        this.manager = manager;
        this.creationTrackingIDs = new HashMap<String, TrackingID>();
    }

    @Override
    public void getAllMasterSceneIDsReplyCB(ResponseCode responseCode, String[] masterSceneIDs) {
        if (!responseCode.equals(ResponseCode.OK)) {
            manager.getMasterSceneCollectionManager().sendErrorEvent("getAllMasterSceneIDsReplyCB", responseCode);
        }

        for (final String masterSceneID : masterSceneIDs) {
            postProcessMasterSceneID(masterSceneID);
        }
    }

    @Override
    public void getMasterSceneNameReplyCB(ResponseCode responseCode, String masterSceneID, String language, String masterSceneName) {
        if (!responseCode.equals(ResponseCode.OK)) {
            manager.getMasterSceneCollectionManager().sendErrorEvent("getMasterSceneNameReplyCB", responseCode, masterSceneID);
        }

        postUpdateMasterSceneName(masterSceneID, masterSceneName);
    }

    @Override
    public void setMasterSceneNameReplyCB(ResponseCode responseCode, String masterSceneID, String language) {
        if (!responseCode.equals(ResponseCode.OK)) {
            manager.getMasterSceneCollectionManager().sendErrorEvent("setMasterSceneNameReplyCB", responseCode, masterSceneID);
        }

        AllJoynManager.masterSceneManager.getMasterSceneName(masterSceneID, LightingSystemManager.LANGUAGE);
    }

    @Override
    public void masterScenesNameChangedCB(final String[] masterSceneIDs) {
        manager.getQueue().post(new Runnable() {
            @Override
            public void run() {
                boolean containsNewIDs = false;

                for (final String masterSceneID : masterSceneIDs) {
                    if (manager.getMasterSceneCollectionManager().hasID(masterSceneID)) {
                        AllJoynManager.masterSceneManager.getMasterSceneName(masterSceneID, LightingSystemManager.LANGUAGE);
                    } else {
                        containsNewIDs = true;
                    }
                }

                if (containsNewIDs) {
                    AllJoynManager.masterSceneManager.getAllMasterSceneIDs();
                }
            }
        });
    }

    @Override
    public void createMasterSceneReplyCB(ResponseCode responseCode, String masterSceneID) {
        if (!responseCode.equals(ResponseCode.OK)) {
            manager.getMasterSceneCollectionManager().sendErrorEvent("createMasterSceneReplyCB", responseCode, masterSceneID);
        }
    }

    @Override
    public void createMasterSceneWithTrackingReplyCB(ResponseCode responseCode, String masterSceneID, long trackingID) {
        if (!responseCode.equals(ResponseCode.OK)) {
            manager.getMasterSceneCollectionManager().sendErrorEvent("createMasterSceneWithTrackingReplyCB", responseCode, masterSceneID, new TrackingID(trackingID));
        } else {
            creationTrackingIDs.put(masterSceneID, new TrackingID(trackingID));
        }
    }

    @Override
    public void masterScenesCreatedCB(String[] masterSceneIDs) {
        AllJoynManager.masterSceneManager.getAllMasterSceneIDs();
    }

    @Override
    public void updateMasterSceneReplyCB(ResponseCode responseCode, String masterSceneID) {
        if (!responseCode.equals(ResponseCode.OK)) {
            manager.getMasterSceneCollectionManager().sendErrorEvent("updateMasterSceneReplyCB", responseCode, masterSceneID);
        }
    }

    @Override
    public void masterScenesUpdatedCB(String[] masterSceneIDs) {
        for (String masterSceneID : masterSceneIDs) {
            AllJoynManager.masterSceneManager.getMasterScene(masterSceneID);
        }
    }

    @Override
    public void deleteMasterSceneReplyCB(ResponseCode responseCode, String masterSceneID) {
        if (!responseCode.equals(ResponseCode.OK)) {
            manager.getMasterSceneCollectionManager().sendErrorEvent("deleteMasterSceneReplyCB", responseCode, masterSceneID);
        }
    }

    @Override
    public void masterScenesDeletedCB(String[] masterSceneIDs) {
        postDeleteMasterScenes(masterSceneIDs);
    }

    @Override
    public void getMasterSceneReplyCB(ResponseCode responseCode, String masterSceneID, MasterScene masterScene) {
        if (!responseCode.equals(ResponseCode.OK)) {
            manager.getMasterSceneCollectionManager().sendErrorEvent("getMasterSceneReplyCB", responseCode, masterSceneID);
        }

        postUpdateMasterScene(masterSceneID, masterScene);
    }

    @Override
    public void applyMasterSceneReplyCB(ResponseCode responseCode, String masterSceneID) {
        if (!responseCode.equals(ResponseCode.OK)) {
            manager.getMasterSceneCollectionManager().sendErrorEvent("applyMasterSceneReplyCB", responseCode, masterSceneID);
        }
    }

    @Override
    public void masterScenesAppliedCB(String[] masterSceneIDs) {
        // Currently nothing to do
    }

    protected void postProcessMasterSceneID(final String masterSceneID) {
        manager.getQueue().post(new Runnable() {
            @Override
            public void run() {
                if (!manager.getMasterSceneCollectionManager().hasID(masterSceneID)) {
                    postUpdateMasterSceneID(masterSceneID);
                    AllJoynManager.masterSceneManager.getMasterSceneName(masterSceneID, LightingSystemManager.LANGUAGE);
                    AllJoynManager.masterSceneManager.getMasterScene(masterSceneID);
                }
            }
        });
    }

    protected void postUpdateMasterSceneID(final String masterSceneID) {
        manager.getQueue().post(new Runnable() {
            @Override
            public void run() {
                if (!manager.getMasterSceneCollectionManager().hasID(masterSceneID)) {
                    manager.getMasterSceneCollectionManager().addMasterScene(masterSceneID);
                }
            }
        });

        postSendMasterSceneChanged(masterSceneID);
    }

    protected void postUpdateMasterScene(final String masterSceneID, final MasterScene masterScene) {
        manager.getQueue().post(new Runnable() {
            @Override
            public void run() {
                MasterSceneDataModel masterSceneModel = manager.getMasterSceneCollectionManager().getModel(masterSceneID);

                if (masterSceneModel != null) {
                    boolean wasInitialized = masterSceneModel.isInitialized();
                    masterSceneModel.setMasterScene(masterScene);
                    if (wasInitialized != masterSceneModel.isInitialized()) {
                        postSendMasterSceneInitialized(masterSceneID);
                    }
                }
            }
        });

        postSendMasterSceneChanged(masterSceneID);
    }

    protected void postUpdateMasterSceneName(final String masterSceneID, final String masterSceneName) {
        manager.getQueue().post(new Runnable() {
            @Override
            public void run() {
                MasterSceneDataModel masterSceneModel = manager.getMasterSceneCollectionManager().getModel(masterSceneID);

                if (masterSceneModel != null) {
                    boolean wasInitialized = masterSceneModel.isInitialized();
                    masterSceneModel.setName(masterSceneName);
                    if (wasInitialized != masterSceneModel.isInitialized()) {
                        postSendMasterSceneInitialized(masterSceneID);
                    }
                }
            }
        });

        postSendMasterSceneChanged(masterSceneID);
    }

    protected void postDeleteMasterScenes(final String[] masterSceneIDs) {
        manager.getQueue().post(new Runnable() {
            @Override
            public void run() {
                for (String masterSceneID : masterSceneIDs) {
                    manager.getMasterSceneCollectionManager().removeMasterScene(masterSceneID);
                }
            }
        });
    }

    protected void postSendMasterSceneChanged(final String masterSceneID) {
        manager.getQueue().post(new Runnable() {
            @Override
            public void run() {
                manager.getMasterSceneCollectionManager().sendChangedEvent(masterSceneID);
            }
        });
    }

    protected void postSendMasterSceneInitialized(final String masterSceneID) {
        manager.getQueue().post(new Runnable() {
            @Override
            public void run() {
                manager.getMasterSceneCollectionManager().sendInitializedEvent(masterSceneID, creationTrackingIDs.remove(masterSceneID));
            }
        });
    }
}