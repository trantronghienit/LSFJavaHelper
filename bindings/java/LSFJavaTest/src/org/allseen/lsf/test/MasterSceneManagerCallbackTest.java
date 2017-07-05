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


package org.allseen.lsf.test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import junit.framework.TestCase;

import org.allseen.lsf.MasterScene;
import org.allseen.lsf.MasterSceneManagerCallback;
import org.allseen.lsf.sdk.ResponseCode;

public class MasterSceneManagerCallbackTest extends TestCase {
    private List<Object> results;
    private MasterSceneManagerCallback callback;

    @Override
    protected void setUp() throws Exception
    {
        results = new ArrayList<Object>();
        callback = new MasterSceneManagerCallback() {

            @Override
            public void getAllMasterSceneIDsReplyCB(ResponseCode responseCode, String[] masterSceneIDs)
            {
                results.add((new Object() { public String getName() { return getClass().getEnclosingMethod().getName(); }}).getName());
                results.add(responseCode);
                results.add(masterSceneIDs);
            }

            @Override
            public void getMasterSceneNameReplyCB(ResponseCode responseCode, String masterSceneID, String language, String masterSceneName)
            {
                results.add((new Object() { public String getName() { return getClass().getEnclosingMethod().getName(); }}).getName());
                results.add(responseCode);
                results.add(masterSceneID);
                results.add(language);
                results.add(masterSceneName);
            }

            @Override
            public void setMasterSceneNameReplyCB(ResponseCode responseCode, String masterSceneID, String language)
            {
                results.add((new Object() { public String getName() { return getClass().getEnclosingMethod().getName(); }}).getName());
                results.add(responseCode);
                results.add(masterSceneID);
                results.add(language);
            }

            @Override
            public void masterScenesNameChangedCB(String[] masterSceneIDs)
            {
                results.add((new Object() { public String getName() { return getClass().getEnclosingMethod().getName(); }}).getName());
                results.add(masterSceneIDs);
            }

            @Override
            public void createMasterSceneReplyCB(ResponseCode responseCode, String masterSceneID)
            {
                results.add((new Object() { public String getName() { return getClass().getEnclosingMethod().getName(); }}).getName());
                results.add(responseCode);
                results.add(masterSceneID);
            }

            @Override
            public void masterScenesCreatedCB(String[] masterSceneIDs)
            {
                results.add((new Object() { public String getName() { return getClass().getEnclosingMethod().getName(); }}).getName());
                results.add(masterSceneIDs);
            }

            @Override
            public void getMasterSceneReplyCB(ResponseCode responseCode, String masterSceneID, MasterScene masterScene)
            {
                results.add((new Object() { public String getName() { return getClass().getEnclosingMethod().getName(); }}).getName());
                results.add(responseCode);
                results.add(masterSceneID);
                results.add(masterScene.getScenes());
            }

            @Override
            public void deleteMasterSceneReplyCB(ResponseCode responseCode, String masterSceneID)
            {
                results.add((new Object() { public String getName() { return getClass().getEnclosingMethod().getName(); }}).getName());
                results.add(responseCode);
                results.add(masterSceneID);
            }

            @Override
            public void masterScenesDeletedCB(String[] masterSceneIDs)
            {
                results.add((new Object() { public String getName() { return getClass().getEnclosingMethod().getName(); }}).getName());
                results.add(masterSceneIDs);
            }

            @Override
            public void updateMasterSceneReplyCB(ResponseCode responseCode, String masterSceneID)
            {
                results.add((new Object() { public String getName() { return getClass().getEnclosingMethod().getName(); }}).getName());
                results.add(responseCode);
                results.add(masterSceneID);
            }

            @Override
            public void masterScenesUpdatedCB(String[] masterSceneIDs)
            {
                results.add((new Object() { public String getName() { return getClass().getEnclosingMethod().getName(); }}).getName());
                results.add(masterSceneIDs);
            }

            @Override
            public void applyMasterSceneReplyCB(ResponseCode responseCode, String masterSceneID)
            {
                results.add((new Object() { public String getName() { return getClass().getEnclosingMethod().getName(); }}).getName());
                results.add(responseCode);
                results.add(masterSceneID);
            }

            @Override
            public void masterScenesAppliedCB(String[] masterSceneIDs)
            {
                results.add((new Object() { public String getName() { return getClass().getEnclosingMethod().getName(); }}).getName());
                results.add(masterSceneIDs);
            }

            @Override
            public void createMasterSceneWithTrackingReplyCB(ResponseCode responseCode, String masterSceneID, long trackingID)
            {
                results.add((new Object() { public String getName() { return getClass().getEnclosingMethod().getName(); }}).getName());
                results.add(responseCode);
                results.add(masterSceneID);
                results.add(trackingID);
            }
        };

        super.setUp();
    }

    @Override
    protected void tearDown() throws Exception
    {
        results = null;
        callback = null;

        super.tearDown();
    }



    private native String getAllMasterSceneIDsReplyCB(MasterSceneManagerCallback mcmcb, ResponseCode responseCode, String[] masterSceneIDs);
    public void testGetAllMasterSceneIDsReplyCB()
    {
        ResponseCode rc = ResponseCode.ERR_REJECTED;
        String[] MasterSceneIDs ={ "MasterSceneID-1", "masterSceneID-2", "MasterSceneID-3"};

        String method = getAllMasterSceneIDsReplyCB(callback, rc, MasterSceneIDs);

        assertTrue(Arrays.deepEquals(new Object[] {method, rc, MasterSceneIDs}, results.toArray()));
    }

    private native String getMasterSceneNameReplyCB(MasterSceneManagerCallback mcmcb, ResponseCode responseCode, String masterSceneID, String language, String masterSceneName);
    public void testGetMasterSceneNameReplyCB()
    {
        ResponseCode rc = ResponseCode.ERR_REJECTED;
        String MasterSceneID = "MasterSceneID-1";
        String language = "language";
        String masterSceneName = "masterSceneName";

        String method = getMasterSceneNameReplyCB(callback, rc, MasterSceneID, language, masterSceneName);

        assertTrue(Arrays.deepEquals(new Object[] {method, rc, MasterSceneID, language, masterSceneName}, results.toArray()));
    }

    private native String setMasterSceneNameReplyCB(MasterSceneManagerCallback mcmcb, ResponseCode responseCode, String masterSceneID, String language);
    public void testSetMasterSceneNameReplyCB()
    {
        ResponseCode rc = ResponseCode.ERR_REJECTED;
        String MasterSceneID = "MasterSceneID-1";
        String language = "language";

        String method = setMasterSceneNameReplyCB(callback, rc, MasterSceneID, language);

        assertTrue(Arrays.deepEquals(new Object[] {method, rc, MasterSceneID, language}, results.toArray()));
    }

    private native String masterScenesNameChangedCB(MasterSceneManagerCallback mcmcb, String[] masterSceneIDs);
    public void testMasterScenesNameChangedCB()
    {
        String[] MasterSceneIDs = {"MasterSceneID-1", "MasterSceneID-2", "MasterSceneID-3"};

        String method = masterScenesNameChangedCB(callback, MasterSceneIDs);

        assertTrue(Arrays.deepEquals(new Object[] {method, MasterSceneIDs}, results.toArray()));
    }

    private native String masterScenesCreatedCB(MasterSceneManagerCallback mcmcb, String[] masterSceneIDs);
    public void testMasterScenesCreatedCB()
    {
        String[] MasterSceneIDs = {"MasterSceneID-1", "MasterSceneID-2", "MasterSceneID-3"};

        String method = masterScenesCreatedCB(callback, MasterSceneIDs);

        assertTrue(Arrays.deepEquals(new Object[] {method, MasterSceneIDs}, results.toArray()));
    }

    private native String masterScenesDeletedCB(MasterSceneManagerCallback mcmcb, String[] masterSceneIDs);
    public void testMasterScenesDeletedCB()
    {
        String[] MasterSceneIDs = {"MasterSceneID-1", "MasterSceneID-2", "MasterSceneID-3"};

        String method = masterScenesDeletedCB(callback, MasterSceneIDs);

        assertTrue(Arrays.deepEquals(new Object[] {method, MasterSceneIDs}, results.toArray()));
    }

    private native String masterScenesUpdatedCB(MasterSceneManagerCallback mcmcb, String[] masterSceneIDs);
    public void testMasterScenesUpdatedCB()
    {
        String[] MasterSceneIDs = {"MasterSceneID-1", "MasterSceneID-2", "MasterSceneID-3"};

        String method = masterScenesUpdatedCB(callback, MasterSceneIDs);

        assertTrue(Arrays.deepEquals(new Object[] {method, MasterSceneIDs}, results.toArray()));
    }

    private native String masterScenesAppliedCB(MasterSceneManagerCallback mcmcb, String[] masterSceneIDs);
    public void testMasterScenesAppliedCB()
    {
        String[] MasterSceneIDs = {"MasterSceneID-1", "MasterSceneID-2", "MasterSceneID-3"};

        String method = masterScenesAppliedCB(callback, MasterSceneIDs);

        assertTrue(Arrays.deepEquals(new Object[] {method, MasterSceneIDs}, results.toArray()));
    }

    private native String getMasterSceneReplyCB(MasterSceneManagerCallback mcmcb, ResponseCode responseCode, String masterSceneID, MasterScene masterScene);
    public void testGetMasterSceneReplyCB()
    {
        ResponseCode rc = ResponseCode.ERR_REJECTED;
        String MasterSceneID = "MasterSceneID-1";
        MasterScene masterScene = new MasterScene();

        String[] lampIDs = {"lampID-1", "lampID-2", "lampID-3"};
        masterScene.setScenes(lampIDs);

        String method = getMasterSceneReplyCB(callback, rc, MasterSceneID, masterScene);

        assertTrue(Arrays.deepEquals(new Object[] {method, rc, MasterSceneID, masterScene.getScenes()}, results.toArray()));
    }

    private native String createMasterSceneReplyCB(MasterSceneManagerCallback mcmcb, ResponseCode responseCode, String masterSceneID);
    public void testCreateMasterSceneReplyCB()
    {
        ResponseCode rc = ResponseCode.ERR_REJECTED;
        String MasterSceneID = "MasterSceneID-1";

        String method = createMasterSceneReplyCB(callback, rc, MasterSceneID);

        assertTrue(Arrays.deepEquals(new Object[] {method, rc, MasterSceneID}, results.toArray()));
    }

    private native String deleteMasterSceneReplyCB(MasterSceneManagerCallback mcmcb, ResponseCode responseCode, String masterSceneID);
    public void testDeleteMasterSceneReplyCB()
    {
        ResponseCode rc = ResponseCode.ERR_REJECTED;
        String MasterSceneID = "MasterSceneID-1";

        String method = deleteMasterSceneReplyCB(callback, rc, MasterSceneID);

        assertTrue(Arrays.deepEquals(new Object[] {method, rc, MasterSceneID}, results.toArray()));
    }

    private native String updateMasterSceneReplyCB(MasterSceneManagerCallback mcmcb, ResponseCode responseCode, String masterSceneID);
    public void testUpdateMasterSceneReplyCB()
    {
        ResponseCode rc = ResponseCode.ERR_REJECTED;
        String MasterSceneID = "MasterSceneID-1";

        String method = updateMasterSceneReplyCB(callback, rc, MasterSceneID);

        assertTrue(Arrays.deepEquals(new Object[] {method, rc, MasterSceneID}, results.toArray()));
    }

    private native String applyMasterSceneReplyCB(MasterSceneManagerCallback mcmcb, ResponseCode responseCode, String masterSceneID);
    public void testApplyMasterSceneReplyCB()
    {
        ResponseCode rc = ResponseCode.ERR_REJECTED;
        String MasterSceneID = "MasterSceneID-1";

        String method = applyMasterSceneReplyCB(callback, rc, MasterSceneID);

        assertTrue(Arrays.deepEquals(new Object[] {method, rc, MasterSceneID}, results.toArray()));
    }

    private native String createMasterSceneWithTrackingReplyCB(MasterSceneManagerCallback mcmcb, ResponseCode responseCode, String masterSceneID, long trackingID);
    public void testCreateMasterSceneWithTrackingReplyCB()
    {
        ResponseCode rc = ResponseCode.ERR_REJECTED;
        String MasterSceneID = "MasterSceneID-1";
        long trackingID = 3485993300L;

        String method = createMasterSceneWithTrackingReplyCB(callback, rc, MasterSceneID, trackingID);

        assertTrue(Arrays.deepEquals(new Object[] {method, rc, MasterSceneID, trackingID}, results.toArray()));
    }
}

