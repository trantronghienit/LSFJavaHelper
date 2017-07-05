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

import org.allseen.lsf.ControllerClientCallback;
import org.allseen.lsf.sdk.ErrorCode;

public class ControllerClientCallbackTest extends TestCase {
    // Load the native library on application start
    static {
        System.loadLibrary("gnustl_shared");
        System.loadLibrary("alljoyn_java");
        System.loadLibrary("alljoyn_lsf_java");
        System.loadLibrary("alljoyn_lsf_java_test");
    }

    private static String lampStateFieldKey = "lampStateFieldKey";
    private List<Object> results;
    private ControllerClientCallback callback;

    @Override
    protected void setUp() throws Exception
    {
        results = new ArrayList<Object>();
        callback = new ControllerClientCallback() {
            @Override
            public void connectedToControllerServiceCB(String controllerServiceDeviceID, String controllerServiceName) {
                results.add((new Object() { public String getName() { return getClass().getEnclosingMethod().getName(); }}).getName());
                results.add(controllerServiceDeviceID);
                results.add(controllerServiceName);
            }

            @Override
            public void connectToControllerServiceFailedCB(String controllerServiceDeviceID, String controllerServiceName) {
                results.add((new Object() { public String getName() { return getClass().getEnclosingMethod().getName(); }}).getName());
                results.add(controllerServiceDeviceID);
                results.add(controllerServiceName);
            }

            @Override
            public void disconnectedFromControllerServiceCB(String controllerServiceDeviceID, String controllerServiceName) {
                results.add((new Object() { public String getName() { return getClass().getEnclosingMethod().getName(); }}).getName());
                results.add(controllerServiceDeviceID);
                results.add(controllerServiceName);
            }

            @Override
            public void controllerClientErrorCB(ErrorCode[] errorCodeList) {
                results.add((new Object() { public String getName() { return getClass().getEnclosingMethod().getName(); }}).getName());
                results.add(errorCodeList);
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

    private native String connectedToControllerServiceCB(ControllerClientCallback cc, String deviceID, String serviceName);
    public void testConnectedToControllerServiceCB()
    {
        String deviceID = "ID-1";
        String serviceName = "Name-1";
        String method = connectedToControllerServiceCB(callback, deviceID, serviceName);

        assertTrue(Arrays.equals(new Object[] {method, deviceID, serviceName}, results.toArray()));
    }

    private native String connectToControllerServiceFailedCB(ControllerClientCallback cc, String deviceID, String serviceName);
    public void testconnectToControllerServiceFailedCB()
    {
        String deviceID = "ID-2";
        String serviceName = "Name-2";
        String method = connectToControllerServiceFailedCB(callback, deviceID, serviceName);

        assertTrue(Arrays.equals(new Object[] {method, deviceID, serviceName}, results.toArray()));
    }

    private native String disconnectedFromControllerServiceCB(ControllerClientCallback cc, String deviceID, String serviceName);
    public void testDisconnectedFromControllerServiceCB()
    {
        String deviceID = "ID-3";
        String serviceName = "Name-3";
        String method = disconnectedFromControllerServiceCB(callback, deviceID, serviceName);

        assertTrue(Arrays.equals(new Object[] {method, deviceID, serviceName}, results.toArray()));
    }

    private native String controllerClientErrorCB(ControllerClientCallback cc, ErrorCode[] ecl);
    public void testControllerClientErrorCB()
    {
        ErrorCode[] ecl = new ErrorCode[] {ErrorCode.NONE, ErrorCode.ERR_REGISTERING_SIGNAL_HANDLERS};
        String method = controllerClientErrorCB(callback, ecl);
        assertTrue(Arrays.deepEquals(new Object[] {method, ecl}, results.toArray()));
    }
}