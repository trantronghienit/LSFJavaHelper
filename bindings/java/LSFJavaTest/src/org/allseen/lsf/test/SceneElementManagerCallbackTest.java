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

import org.allseen.lsf.SceneElement;
import org.allseen.lsf.SceneElementManagerCallback;
import org.allseen.lsf.sdk.ResponseCode;

public class SceneElementManagerCallbackTest extends TestCase {
    private List<Object> results;
    private SceneElementManagerCallback callback;

    @Override
    protected void setUp() throws Exception
    {
        results = new ArrayList<Object>();
        callback = new SceneElementManagerCallback() {
            @Override
            public void getAllSceneElementIDsReplyCB(ResponseCode responseCode, String[] sceneElementIDs)
            {
                results.add((new Object() { public String getName() { return getClass().getEnclosingMethod().getName(); }}).getName());
                results.add(responseCode);
                results.add(sceneElementIDs);
            }

            @Override
            public void getSceneElementNameReplyCB(ResponseCode responseCode, String sceneElementID, String language, String sceneElementName)
            {
                results.add((new Object() { public String getName() { return getClass().getEnclosingMethod().getName(); }}).getName());
                results.add(responseCode);
                results.add(sceneElementID);
                results.add(language);
                results.add(sceneElementName);

            }

            @Override
            public void setSceneElementNameReplyCB(ResponseCode responseCode, String sceneElementID, String language)
            {
                results.add((new Object() { public String getName() { return getClass().getEnclosingMethod().getName(); }}).getName());
                results.add(responseCode);
                results.add(sceneElementID);
                results.add(language);
            }

            @Override
            public void getSceneElementReplyCB(ResponseCode responseCode, String sceneElementID, SceneElement sceneElement)
            {
                results.add((new Object() { public String getName() { return getClass().getEnclosingMethod().getName(); }}).getName());
                results.add(responseCode);
                results.add(sceneElementID);
                results.add(sceneElement.getEffectID());
            }

            @Override
            public void updateSceneElementReplyCB(ResponseCode responseCode, String sceneElementID)
            {
                results.add((new Object() { public String getName() { return getClass().getEnclosingMethod().getName(); }}).getName());
                results.add(responseCode);
                results.add(sceneElementID);

            }

            @Override
            public void deleteSceneElementReplyCB(ResponseCode responseCode, String sceneElementID)
            {
                results.add((new Object() { public String getName() { return getClass().getEnclosingMethod().getName(); }}).getName());
                results.add(responseCode);
                results.add(sceneElementID);
            }

            @Override
            public void applySceneElementReplyCB(ResponseCode responseCode, String sceneElementID)
            {
                results.add((new Object() { public String getName() { return getClass().getEnclosingMethod().getName(); }}).getName());
                results.add(responseCode);
                results.add(sceneElementID);
            }

            @Override
            public void sceneElementsNameChangedCB(String[] sceneElementIDs)
            {
                results.add((new Object() { public String getName() { return getClass().getEnclosingMethod().getName(); }}).getName());
                results.add(sceneElementIDs);
            }

            @Override
            public void sceneElementsCreatedCB(String[] sceneElementIDs)
            {
                results.add((new Object() { public String getName() { return getClass().getEnclosingMethod().getName(); }}).getName());
                results.add(sceneElementIDs);
            }

            @Override
            public void sceneElementsUpdatedCB(String[] sceneElementIDs)
            {
                results.add((new Object() { public String getName() { return getClass().getEnclosingMethod().getName(); }}).getName());
                results.add(sceneElementIDs);
            }

            @Override
            public void sceneElementsDeletedCB(String[] sceneElementIDs)
            {
                results.add((new Object() { public String getName() { return getClass().getEnclosingMethod().getName(); }}).getName());
                results.add(sceneElementIDs);
            }

            @Override
            public void sceneElementsAppliedCB(String[] sceneElementIDs)
            {
                results.add((new Object() { public String getName() { return getClass().getEnclosingMethod().getName(); }}).getName());
                results.add(sceneElementIDs);
            }

            @Override
            public void createSceneElementReplyCB(ResponseCode responseCode, String sceneElementID, long trackingID)
            {
                results.add((new Object() { public String getName() { return getClass().getEnclosingMethod().getName(); }}).getName());
                results.add(responseCode);
                results.add(sceneElementID);
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

    private native String getAllSceneElementIDsReplyCB(SceneElementManagerCallback lmcb, ResponseCode responseCode, String[] sceneElementIDs);
    public void testGetAllSceneElementIDsReplyCB()
    {
        ResponseCode rc = ResponseCode.ERR_REJECTED;
        String[] SceneElementIDs = new String[] { "SceneElementID-1", "SceneElementID-2", "SceneElementID-3" };

        String method = getAllSceneElementIDsReplyCB(callback, rc, SceneElementIDs);

        assertTrue(Arrays.deepEquals(new Object[] {method, rc, SceneElementIDs}, results.toArray()));
    }

    private native String getSceneElementNameReplyCB(SceneElementManagerCallback lmcb, ResponseCode responseCode, String sceneElementID, String language, String sceneElementName);
    public void testGetSceneElementNameReplyCB()
    {
        ResponseCode rc = ResponseCode.ERR_REJECTED;
        String sceneElementID = "SCENEELEMENTID-1";
        String language = "LANGUAGE";
        String sceneElementName = "SCENEELEMENTNAME-1";

        String method = getSceneElementNameReplyCB(callback, rc, sceneElementID, language, sceneElementName);

        assertTrue(Arrays.deepEquals(new Object[] {method, rc, sceneElementID, language, sceneElementName}, results.toArray()));
    }

    private native String setSceneElementNameReplyCB(SceneElementManagerCallback lmcb, ResponseCode responseCode, String sceneElementID, String language);
    public void testSetSceneElementNameReplyCB()
    {
        ResponseCode rc = ResponseCode.ERR_REJECTED;
        String sceneElementID = "SCENEELEMENTID-1";
        String language = "LANGUAGE";

        String method = setSceneElementNameReplyCB(callback, rc, sceneElementID, language);

        assertTrue(Arrays.deepEquals(new Object[] {method, rc, sceneElementID, language}, results.toArray()));
    }

    private native String getSceneElementReplyCB(SceneElementManagerCallback lmcb, ResponseCode responseCode, String sceneElementID, SceneElement sceneElement);
    public void testGetSceneElementReplyCB()
    {
        ResponseCode rc = ResponseCode.ERR_REJECTED;
        String sceneElementID = "SCENEELEMENTID-1";
        SceneElement sceneElement = new SceneElement();

        String effectID = "EFFECTID-1";
        sceneElement.setEffectID(effectID);

        String method = getSceneElementReplyCB(callback, rc, sceneElementID, sceneElement);

        assertTrue(Arrays.deepEquals(new Object[] {method, rc, sceneElementID, sceneElement.getEffectID()}, results.toArray()));
    }

    private native String updateSceneElementReplyCB(SceneElementManagerCallback lmcb, ResponseCode responseCode, String sceneElementID);
    public void testUpdateSceneElementReplyCB()
    {
        ResponseCode rc = ResponseCode.ERR_REJECTED;
        String sceneElementID = "SCENEELEMENTID-1";

        String method = updateSceneElementReplyCB(callback, rc, sceneElementID);

        assertTrue(Arrays.deepEquals(new Object[] {method, rc, sceneElementID}, results.toArray()));
    }

    private native String deleteSceneElementReplyCB(SceneElementManagerCallback lmcb, ResponseCode responseCode, String sceneElementID);
    public void testDeleteSceneElementReplyCB()
    {
        ResponseCode rc = ResponseCode.ERR_REJECTED;
        String sceneElementID = "SCENEELEMENTID-1";

        String method = deleteSceneElementReplyCB(callback, rc, sceneElementID);

        assertTrue(Arrays.deepEquals(new Object[] {method, rc, sceneElementID}, results.toArray()));
    }

    private native String applySceneElementReplyCB(SceneElementManagerCallback lmcb, ResponseCode responseCode, String sceneElementID);
    public void testApplySceneElementReplyCB()
    {
        ResponseCode rc = ResponseCode.ERR_REJECTED;
        String sceneElementID = "SCENEELEMENTID-1";

        String method = applySceneElementReplyCB(callback, rc, sceneElementID);

        assertTrue(Arrays.deepEquals(new Object[] {method, rc, sceneElementID}, results.toArray()));
    }


    private native String sceneElementsNameChangedCB(SceneElementManagerCallback lmcb, String[] sceneElementIDs);
    public void testSceneElementsNameChangedCB()
    {
        String[] sceneElementIDs =  {"ID-1", "ID-2", "ID-3"};

        String method = sceneElementsNameChangedCB(callback, sceneElementIDs);

        assertTrue(Arrays.deepEquals(new Object[] {method, sceneElementIDs}, results.toArray()));
    }

    private native String sceneElementsCreatedCB(SceneElementManagerCallback lmcb, String[] sceneElementIDs);
    public void testSceneElementsCreatedCB()
    {
        String[] sceneElementIDs =  {"ID-1", "ID-2", "ID-3"};

        String method = sceneElementsCreatedCB(callback, sceneElementIDs);

        assertTrue(Arrays.deepEquals(new Object[] {method, sceneElementIDs}, results.toArray()));
    }

    private native String sceneElementsUpdatedCB(SceneElementManagerCallback lmcb, String[] sceneElementIDs);
    public void testSceneElementsUpdatedCB()
    {
        String[] sceneElementIDs =  {"ID-1", "ID-2", "ID-3"};

        String method = sceneElementsUpdatedCB(callback, sceneElementIDs);

        assertTrue(Arrays.deepEquals(new Object[] {method, sceneElementIDs}, results.toArray()));
    }

    private native String sceneElementsDeletedCB(SceneElementManagerCallback lmcb, String[] sceneElementIDs);
    public void testSceneElementsDeletedCB()
    {
        String[] sceneElementIDs =  {"ID-1", "ID-2", "ID-3"};

        String method = sceneElementsDeletedCB(callback, sceneElementIDs);

        assertTrue(Arrays.deepEquals(new Object[] {method, sceneElementIDs}, results.toArray()));
    }

    private native String sceneElementsAppliedCB(SceneElementManagerCallback lmcb, String[] sceneElementIDs);
    public void testSceneElementsAppliedCB()
    {
        String[] sceneElementIDs =  {"ID-1", "ID-2", "ID-3"};

        String method = sceneElementsAppliedCB(callback, sceneElementIDs);

        assertTrue(Arrays.deepEquals(new Object[] {method, sceneElementIDs}, results.toArray()));
    }

    private native String createSceneElementReplyCB(SceneElementManagerCallback lmcb, ResponseCode responseCode, String sceneElementID, long trackingID);
    public void testCreateSceneElementReplyCB()
    {
        ResponseCode rc = ResponseCode.ERR_REJECTED;
        String sceneElementID = "SCENEELEMENTID-1";
        long trackingID = 3987432900L;

        String method = createSceneElementReplyCB(callback, rc, sceneElementID, trackingID);

        assertTrue(Arrays.deepEquals(new Object[] {method, rc, sceneElementID, trackingID}, results.toArray()));
    }
}