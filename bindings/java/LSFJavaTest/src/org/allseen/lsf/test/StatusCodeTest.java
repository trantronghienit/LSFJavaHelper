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

import junit.framework.TestCase;

import org.alljoyn.bus.Status;

public class StatusCodeTest extends TestCase {
    @Override
    protected void setUp() throws Exception
    {
        super.setUp();
    }

    @Override
    protected void tearDown() throws Exception
    {
        super.tearDown();
    }

    public void testConvertIntToObj()
    {
        assertTrue(checkConvertIntToObj(Status.OK));
        assertTrue(checkConvertIntToObj(Status.FAIL));
        assertTrue(checkConvertIntToObj(Status.WARNING));
        assertTrue(checkConvertIntToObj(Status.BUS_ALREADY_CONNECTED));
    }

    public void testConvertObjToInt()
    {
        assertTrue(checkConvertObjToInt(Status.OK));
        assertTrue(checkConvertObjToInt(Status.FAIL));
        assertTrue(checkConvertObjToInt(Status.WARNING));
        assertTrue(checkConvertObjToInt(Status.BUS_ALREADY_CONNECTED));
    }

    private boolean checkConvertIntToObj(Status sc)
    {
        return sc.equals(convertIntToObj(sc.getErrorCode()));
    }

    private boolean checkConvertObjToInt(Status sc)
    {
        return sc.getErrorCode() == convertObjToInt(sc);
    }

    private native Status convertIntToObj(int sc);
    private native int convertObjToInt(Status sc);
}