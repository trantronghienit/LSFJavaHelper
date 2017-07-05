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

import org.allseen.lsf.LampState;
import org.allseen.lsf.PresetManagerCallback;
import org.allseen.lsf.sdk.ResponseCode;

public class PresetManagerCallbackTest extends TestCase {
    private List<Object> results;
    private PresetManagerCallback callback;

    @Override
    protected void setUp() throws Exception
    {
        results = new ArrayList<Object>();
        callback = new PresetManagerCallback() {
            @Override
            public void getAllPresetIDsReplyCB(ResponseCode responseCode, String[] presetIDs)
            {
                results.add((new Object() { public String getName() { return getClass().getEnclosingMethod().getName(); }}).getName());
                results.add(responseCode);
                results.add(presetIDs);
            }

            @Override
            public void getPresetReplyCB(ResponseCode responseCode, String presetID, LampState state)
            {
                results.add((new Object() { public String getName() { return getClass().getEnclosingMethod().getName(); }}).getName());
                results.add(responseCode);
                results.add(presetID);
                results.add(state.getColorTemp());
            }

            @Override
            public void getPresetNameReplyCB(ResponseCode responseCode, String presetID, String language, String presetName)
            {
                results.add((new Object() { public String getName() { return getClass().getEnclosingMethod().getName(); }}).getName());
                results.add(responseCode);
                results.add(presetID);
                results.add(language);
                results.add(presetName);
            }

            @Override
            public void setPresetNameReplyCB(ResponseCode responseCode, String presetID, String language)
            {
                results.add((new Object() { public String getName() { return getClass().getEnclosingMethod().getName(); }}).getName());
                results.add(responseCode);
                results.add(presetID);
                results.add(language);
            }

            @Override
            public void presetsNameChangedCB(String[] presetIDs)
            {
                results.add((new Object() { public String getName() { return getClass().getEnclosingMethod().getName(); }}).getName());
                results.add(presetIDs);
            }

            @Override
            public void getDefaultLampStateReplyCB(ResponseCode responseCode, LampState defaultLampState)
            {
                results.add((new Object() { public String getName() { return getClass().getEnclosingMethod().getName(); }}).getName());
                results.add(responseCode);
                results.add(defaultLampState.getColorTemp());
            }

            @Override
            public void createPresetReplyCB(ResponseCode responseCode, String presetID)
            {
                results.add((new Object() { public String getName() { return getClass().getEnclosingMethod().getName(); }}).getName());
                results.add(responseCode);
                results.add(presetID);
            }
            @Override
            public void presetsCreatedCB(String[] presetIDs)
            {
                results.add((new Object() { public String getName() { return getClass().getEnclosingMethod().getName(); }}).getName());
                results.add(presetIDs);
            }

            @Override
            public void updatePresetReplyCB(ResponseCode responseCode, String presetID)
            {
                results.add((new Object() { public String getName() { return getClass().getEnclosingMethod().getName(); }}).getName());
                results.add(responseCode);
                results.add(presetID);
            }

            @Override
            public void presetsUpdatedCB(String[] presetIDs)
            {
                results.add((new Object() { public String getName() { return getClass().getEnclosingMethod().getName(); }}).getName());
                results.add(presetIDs);
            }

            @Override
            public void deletePresetReplyCB(ResponseCode responseCode, String presetID)
            {
                results.add((new Object() { public String getName() { return getClass().getEnclosingMethod().getName(); }}).getName());
                results.add(responseCode);
                results.add(presetID);
            }

            @Override
            public void presetsDeletedCB(String[] presetIDs)
            {
                results.add((new Object() { public String getName() { return getClass().getEnclosingMethod().getName(); }}).getName());
                results.add(presetIDs);
            }

            @Override
            public void setDefaultLampStateReplyCB(ResponseCode responseCode)
            {
                results.add((new Object() { public String getName() { return getClass().getEnclosingMethod().getName(); }}).getName());
                results.add(responseCode);
            }

            @Override
            public void defaultLampStateChangedCB()
            {
                results.add((new Object() { public String getName() { return getClass().getEnclosingMethod().getName(); }}).getName());
            }

            @Override
            public void createPresetWithTrackingReplyCB(ResponseCode responseCode, String presetID, long trackingID)
            {
                results.add((new Object() { public String getName() { return getClass().getEnclosingMethod().getName(); }}).getName());
                results.add(responseCode);
                results.add(presetID);
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


    private native String getAllPresetIDsReplyCB(PresetManagerCallback lmcb, ResponseCode rc, String[] presetIDs);
    public void testGetAllPresetIDsReplyCB()
    {
        ResponseCode rc = ResponseCode.ERR_REJECTED;
        String[] presetIDs = new String[] { "PresetID-1", "PresetID-2", "PresetID-3" };

        String method = getAllPresetIDsReplyCB(callback, rc, presetIDs);

        assertTrue(Arrays.deepEquals(new Object[] {method, rc, presetIDs}, results.toArray()));
    }

    private native String getPresetReplyCB(PresetManagerCallback lmcb, ResponseCode rc, String presetID, LampState preset);
    public void testGetPresetReplyCB()
    {
        ResponseCode rc = ResponseCode.ERR_REJECTED;
        String presetID = "PresetID-1";
        LampState preset = new LampState();

        preset.setColorTemp(411);

        String method = getPresetReplyCB(callback, rc, presetID, preset);

        assertTrue(Arrays.deepEquals(new Object[] {method, rc, presetID, preset.getColorTemp()}, results.toArray()));
    }

    private native String getPresetNameReplyCB(PresetManagerCallback lmcb, ResponseCode rc, String presetID, String language, String presetName);
    public void testGetPresetNameReplyCB()
    {
        ResponseCode rc = ResponseCode.ERR_REJECTED;
        String presetID = "PresetID-1";
        String language = "EN";
        String presetName = "PresetName-1";

        String method = getPresetNameReplyCB(callback, rc, presetID, language, presetName);

        assertTrue(Arrays.deepEquals(new Object[] {method, rc, presetID, language, presetName}, results.toArray()));
    }

    private native String setPresetNameReplyCB(PresetManagerCallback lmcb, ResponseCode rc, String presetID, String language);
    public void testSetPresetNameReplyCB()
    {
        ResponseCode rc = ResponseCode.ERR_REJECTED;
        String presetID = "PresetID-1";
        String language = "EN";

        String method = setPresetNameReplyCB(callback, rc, presetID, language);

        assertTrue(Arrays.deepEquals(new Object[] {method, rc, presetID, language}, results.toArray()));
    }

    private native String presetsNameChangedCB(PresetManagerCallback lmcb, String[] presetIDs);
    public void testPresetsNameChangedCB()
    {
        String[] presetIDs = new String[] { "PresetID-1", "PresetID-2", "PresetID-3" };

        String method = presetsNameChangedCB(callback, presetIDs);

        assertTrue(Arrays.deepEquals(new Object[] {method, presetIDs}, results.toArray()));
    }

    private native String createPresetReplyCB(PresetManagerCallback lmcb, ResponseCode responseCode, String presetID);
    public void testCreatePresetReplyCB()
    {
        ResponseCode rc = ResponseCode.ERR_REJECTED;
        String presetID = "PresetID-1";

        String method = createPresetReplyCB(callback, rc, presetID);

        assertTrue(Arrays.deepEquals(new Object[] {method, rc, presetID}, results.toArray()));
    }

    private native String presetsCreatedCB(PresetManagerCallback lmcb, String[] presetIDs);
    public void testPresetsCreatedCB()
    {
        String[] presetIDs = new String[] { "PresetID-1", "PresetID-2", "PresetID-3" };

        String method = presetsCreatedCB(callback, presetIDs);

        assertTrue(Arrays.deepEquals(new Object[] {method, presetIDs}, results.toArray()));
    }

    private native String updatePresetReplyCB(PresetManagerCallback lmcb, ResponseCode responseCode, String presetID);
    public void testUpdatePresetReplyCB()
    {
        ResponseCode rc = ResponseCode.ERR_REJECTED;
        String presetID = "PresetID-1";

        String method = updatePresetReplyCB(callback, rc, presetID);

        assertTrue(Arrays.deepEquals(new Object[] {method, rc, presetID}, results.toArray()));
    }


    private native String presetsUpdatedCB(PresetManagerCallback lmcb, String[] presetIDs);
    public void testPresetsUpdatedCB()
    {
        String[] presetIDs = new String[] { "PresetID-1", "PresetID-2", "PresetID-3" };

        String method = presetsUpdatedCB(callback, presetIDs);

        assertTrue(Arrays.deepEquals(new Object[] {method, presetIDs}, results.toArray()));
    }


    private native String deletePresetReplyCB(PresetManagerCallback lmcb, ResponseCode responseCode, String presetID);
    public void testDeletePresetReplyCB()
    {
        ResponseCode rc = ResponseCode.ERR_REJECTED;
        String presetID = "PresetID-1";

        String method = deletePresetReplyCB(callback, rc, presetID);

        assertTrue(Arrays.deepEquals(new Object[] {method, rc, presetID}, results.toArray()));
    }

    private native String presetsDeletedCB(PresetManagerCallback lmcb, String[] presetIDs);
    public void testPresetsDeletedCB()
    {
        String[] presetIDs = new String[] { "PresetID-1", "PresetID-2", "PresetID-3" };

        String method = presetsDeletedCB(callback, presetIDs);

        assertTrue(Arrays.deepEquals(new Object[] {method, presetIDs}, results.toArray()));
    }

    private native String createPresetWithTrackingReplyCB(PresetManagerCallback lmcb, ResponseCode responseCode, String presetID, long trackingID);
    public void testCreatePresetWithTrackingReplyCB()
    {
        ResponseCode rc = ResponseCode.ERR_REJECTED;
        String presetID = "PresetID-1";
        long trackingID = 3485993300L;

        String method = createPresetWithTrackingReplyCB(callback, rc, presetID, trackingID);

        assertTrue(Arrays.deepEquals(new Object[] {method, rc, presetID, trackingID}, results.toArray()));
    }



/*
    private native String defaultLampStateChangedCB(PresetManagerCallback lmcb);
    public void testDefaultLampStateChangedCB()
    {
        String method = defaultLampStateChangedCB(callback);


        assertTrue(Arrays.deepEquals(new Object[] {method}, results.toArray()));
    }

    private native String getDefaultLampStateReplyCB(PresetManagerCallback lmcb, ResponseCode responseCode, LampState defaultLampState);
    public void testGetDefaultLampStateReplyCB()
    {
        ResponseCode rc = ResponseCode.ERR_REJECTED;
        LampState defaultLampState = new LampState();
        long colorTemp = 3993300L;
        defaultLampState.setColorTemp(colorTemp);

        String method = getDefaultLampStateReplyCB(callback, rc, defaultLampState);

        assertTrue(Arrays.deepEquals(new Object[] {method, rc, defaultLampState.getColorTemp()}, results.toArray()));
    }


    private native String setDefaultLampStateReplyCB(PresetManagerCallback lmcb, ResponseCode responseCode);
    public void testSetDefaultLampStateReplyCB()
    {
        ResponseCode rc = ResponseCode.ERR_REJECTED;

        String method = setDefaultLampStateReplyCB(callback, rc);

        assertTrue(Arrays.deepEquals(new Object[] {method, rc}, results.toArray()));
    }


*/



}