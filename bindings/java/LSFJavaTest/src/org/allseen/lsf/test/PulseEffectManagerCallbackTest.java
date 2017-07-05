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

import org.allseen.lsf.PulseEffectManagerCallback;
import org.allseen.lsf.PulseEffectV2;
import org.allseen.lsf.sdk.ResponseCode;

public class PulseEffectManagerCallbackTest extends TestCase {
    private List<Object> results;
    private PulseEffectManagerCallback callback;

    @Override
    protected void setUp() throws Exception
    {
        results = new ArrayList<Object>();
        callback = new PulseEffectManagerCallback() {

            @Override
            public void getPulseEffectReplyCB(ResponseCode responseCode, String pulseEffectID, PulseEffectV2 pulseEffect)
            {
                results.add((new Object() { public String getName() { return getClass().getEnclosingMethod().getName(); }}).getName());
                results.add(responseCode);
                results.add(pulseEffectID);
                results.add(pulseEffect.getNumPulses());
            }

            @Override
            public void applyPulseEffectOnLampsReplyCB(ResponseCode responseCode, String pulseEffectID, String[] lampIDs)
            {
                results.add((new Object() { public String getName() { return getClass().getEnclosingMethod().getName(); }}).getName());
                results.add(responseCode);
                results.add(pulseEffectID);
                results.add(lampIDs);
            }
            @Override
            public void applyPulseEffectOnLampGroupsReplyCB(ResponseCode responseCode, String pulseEffectID, String[] lampGroupIDs)
            {
                results.add((new Object() { public String getName() { return getClass().getEnclosingMethod().getName(); }}).getName());
                results.add(responseCode);
                results.add(pulseEffectID);
                results.add(lampGroupIDs);
            }

            @Override
            public void getAllPulseEffectIDsReplyCB(ResponseCode responseCode, String[] pulseEffectIDs)
            {
                results.add((new Object() { public String getName() { return getClass().getEnclosingMethod().getName(); }}).getName());
                results.add(responseCode);
                results.add(pulseEffectIDs);
            }

            @Override
            public void getPulseEffectNameReplyCB(ResponseCode responseCode, String pulseEffectID, String language, String pulseEffectName)
            {
                results.add((new Object() { public String getName() { return getClass().getEnclosingMethod().getName(); }}).getName());
                results.add(responseCode);
                results.add(pulseEffectID);
                results.add(language);
                results.add(pulseEffectName);
            }

            @Override
            public void setPulseEffectNameReplyCB(ResponseCode responseCode, String pulseEffectID, String language)
            {
                results.add((new Object() { public String getName() { return getClass().getEnclosingMethod().getName(); }}).getName());
                results.add(responseCode);
                results.add(pulseEffectID);
                results.add(language);
            }

            @Override
            public void pulseEffectsNameChangedCB(String[] pulseEffectIDs)
            {
                results.add((new Object() { public String getName() { return getClass().getEnclosingMethod().getName(); }}).getName());
                results.add(pulseEffectIDs);
            }

            @Override
            public void pulseEffectsCreatedCB(String[] pulseEffectIDs)
            {
                results.add((new Object() { public String getName() { return getClass().getEnclosingMethod().getName(); }}).getName());
                results.add(pulseEffectIDs);
            }

            @Override
            public void pulseEffectsUpdatedCB(String[] pulseEffectIDs)
            {
                results.add((new Object() { public String getName() { return getClass().getEnclosingMethod().getName(); }}).getName());
                results.add(pulseEffectIDs);
            }

            @Override
            public void pulseEffectsDeletedCB(String[] pulseEffectIDs)
            {
                results.add((new Object() { public String getName() { return getClass().getEnclosingMethod().getName(); }}).getName());
                results.add(pulseEffectIDs);
            }

            @Override
            public void updatePulseEffectReplyCB(ResponseCode responseCode, String pulseEffectID)
            {
                results.add((new Object() { public String getName() { return getClass().getEnclosingMethod().getName(); }}).getName());
                results.add(responseCode);
                results.add(pulseEffectID);
            }

            @Override
            public void deletePulseEffectReplyCB(ResponseCode responseCode, String pulseEffectID)
            {
                results.add((new Object() { public String getName() { return getClass().getEnclosingMethod().getName(); }}).getName());
                results.add(responseCode);
                results.add(pulseEffectID);
            }

            @Override
            public void createPulseEffectReplyCB(ResponseCode responseCode, String pulseEffectID, long trackingID)
            {
                results.add((new Object() { public String getName() { return getClass().getEnclosingMethod().getName(); }}).getName());
                results.add(responseCode);
                results.add(pulseEffectID);
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

    private native String getPulseEffectReplyCB(PulseEffectManagerCallback cb, ResponseCode responseCode, String pulseEffectID, PulseEffectV2 pulseEffect);
    public void testGetPulseEffectReplyCB()
    {
        ResponseCode rc = ResponseCode.ERR_REJECTED;
        String pulseEffectID = "PulseEffect-1";
        PulseEffectV2 pulseEffect = new PulseEffectV2();

        long numPulses = 125843;
        pulseEffect.setNumPulses(numPulses);

        String method = getPulseEffectReplyCB(callback, rc, pulseEffectID, pulseEffect);

        assertTrue(Arrays.deepEquals(new Object[] {method, rc, pulseEffectID, pulseEffect.getNumPulses()}, results.toArray()));
    }


    private native String applyPulseEffectOnLampsReplyCB(PulseEffectManagerCallback cb, ResponseCode responseCode, String pulseEffectID, String[] lampIDs);
    public void testApplyPulseEffectOnLampsReplyCB()
    {
        ResponseCode rc = ResponseCode.ERR_REJECTED;
        String pulseEffectID = "PulseEffect-1";
        String[] lampIDs = {"LOOK", "AT", "THESE", "LAMP", "IDS"};


        String method = applyPulseEffectOnLampsReplyCB(callback, rc, pulseEffectID, lampIDs);

        assertTrue(Arrays.deepEquals(new Object[] {method, rc, pulseEffectID, lampIDs}, results.toArray()));
    }

    private native String applyPulseEffectOnLampGroupsReplyCB(PulseEffectManagerCallback cb, ResponseCode responseCode, String pulseEffectID, String[] lampGroupIDs);
    public void testApplyPulseEffectOnLampGroupsReplyCB()
    {
        ResponseCode rc = ResponseCode.ERR_REJECTED;
        String pulseEffectID = "PulseEffect-1";
        String[] lampGroupIDs = {"LOOK", "AT", "THESE", "LAMPGROUP", "IDS"};


        String method = applyPulseEffectOnLampGroupsReplyCB(callback, rc, pulseEffectID, lampGroupIDs);

        assertTrue(Arrays.deepEquals(new Object[] {method, rc, pulseEffectID, lampGroupIDs}, results.toArray()));
    }

    private native String getAllPulseEffectIDsReplyCB(PulseEffectManagerCallback cb, ResponseCode responseCode, String[] pulseEffectIDs);
    public void testGetAllPulseEffectIDsReplyCB()
    {
        ResponseCode rc = ResponseCode.ERR_REJECTED;
        String[] pulseEffectIDs = {"LOOK", "AT", "THESE", "PULSEEFFECT", "IDS"};


        String method = getAllPulseEffectIDsReplyCB(callback, rc, pulseEffectIDs);

        assertTrue(Arrays.deepEquals(new Object[] {method, rc, pulseEffectIDs}, results.toArray()));
    }

    private native String getPulseEffectNameReplyCB(PulseEffectManagerCallback cb, ResponseCode responseCode, String pulseEffectID, String language, String pulseEffectName);
    public void testGetPulseEffectNameReplyCB()
    {
        ResponseCode rc = ResponseCode.ERR_REJECTED;
        String pulseEffectID = "pulseEffectID-1";
        String language = "LANGUAGE-1";
        String pulseEffectName = "pulseEffectName-1";


        String method = getPulseEffectNameReplyCB(callback, rc, pulseEffectID, language, pulseEffectName);

        assertTrue(Arrays.deepEquals(new Object[] {method, rc, pulseEffectID, language, pulseEffectName}, results.toArray()));
    }

    private native String setPulseEffectNameReplyCB(PulseEffectManagerCallback cb, ResponseCode responseCode, String pulseEffectID, String language);
    public void testSetPulseEffectNameReplyCB()
    {
        ResponseCode rc = ResponseCode.ERR_REJECTED;
        String pulseEffectID = "pulseEffectID-1";
        String language = "LANGUAGE-1";


        String method = setPulseEffectNameReplyCB(callback, rc, pulseEffectID, language);

        assertTrue(Arrays.deepEquals(new Object[] {method, rc, pulseEffectID, language}, results.toArray()));
    }

    private native String createPulseEffectReplyCB(PulseEffectManagerCallback cb, ResponseCode responseCode, String pulseEffectID, long trackingID);
    public void testCreatePulseEffectReplyCB()
    {
        ResponseCode rc = ResponseCode.ERR_REJECTED;
        String pulseEffectID = "pulseEffectID-1";
        long trackingID = 129493;


        String method = createPulseEffectReplyCB(callback, rc, pulseEffectID, trackingID);

        assertTrue(Arrays.deepEquals(new Object[] {method, rc, pulseEffectID, trackingID}, results.toArray()));
    }

    private native String pulseEffectsNameChangedCB(PulseEffectManagerCallback cb, String[] pulseEffectIDs);
    public void testPulseEffectsNameChangedCB()
    {
        String[] pulseEffectIDs = {"LOOK", "AT", "THESE", "PULSEEFFECT", "IDS"};


        String method = pulseEffectsNameChangedCB(callback, pulseEffectIDs);

        assertTrue(Arrays.deepEquals(new Object[] {method, pulseEffectIDs}, results.toArray()));
    }

    private native String pulseEffectsCreatedCB(PulseEffectManagerCallback cb, String[] pulseEffectIDs);
    public void testPulseEffectsCreatedCB()
    {
        String[] pulseEffectIDs = {"LOOK", "AT", "THESE", "PULSEEFFECT", "IDS"};


        String method = pulseEffectsCreatedCB(callback, pulseEffectIDs);

        assertTrue(Arrays.deepEquals(new Object[] {method, pulseEffectIDs}, results.toArray()));
    }

    private native String pulseEffectsUpdatedCB(PulseEffectManagerCallback cb, String[] pulseEffectIDs);
    public void testPulseEffectsUpdatedCB()
    {
        String[] pulseEffectIDs = {"LOOK", "AT", "THESE", "PULSEEFFECT", "IDS"};


        String method = pulseEffectsUpdatedCB(callback, pulseEffectIDs);

        assertTrue(Arrays.deepEquals(new Object[] {method, pulseEffectIDs}, results.toArray()));
    }

    private native String pulseEffectsDeletedCB(PulseEffectManagerCallback cb, String[] pulseEffectIDs);
    public void testPulseEffectsDeletedCB()
    {
        String[] pulseEffectIDs = {"LOOK", "AT", "THESE", "PULSEEFFECT", "IDS"};


        String method = pulseEffectsDeletedCB(callback, pulseEffectIDs);

        assertTrue(Arrays.deepEquals(new Object[] {method, pulseEffectIDs}, results.toArray()));
    }

    private native String updatePulseEffectReplyCB(PulseEffectManagerCallback cb, ResponseCode responseCode, String pulseEffectID);
    public void testUpdatePulseEffectReplyCB()
    {
        ResponseCode rc = ResponseCode.ERR_REJECTED;
        String pulseEffectID = "pulseEffectID-1";

        String method = updatePulseEffectReplyCB(callback, rc, pulseEffectID);

        assertTrue(Arrays.deepEquals(new Object[] {method, rc, pulseEffectID}, results.toArray()));
    }

    private native String deletePulseEffectReplyCB(PulseEffectManagerCallback cb, ResponseCode responseCode, String pulseEffectID);
    public void testDeletePulseEffectReplyCB()
    {
        ResponseCode rc = ResponseCode.ERR_REJECTED;
        String pulseEffectID = "pulseEffectID-1";

        String method = deletePulseEffectReplyCB(callback, rc, pulseEffectID);

        assertTrue(Arrays.deepEquals(new Object[] {method, rc, pulseEffectID}, results.toArray()));
    }

}