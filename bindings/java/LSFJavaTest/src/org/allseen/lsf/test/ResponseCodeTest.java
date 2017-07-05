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

import org.allseen.lsf.sdk.ResponseCode;

public class ResponseCodeTest extends TestCase {
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
        assertTrue(checkConvertIntToObj(ResponseCode.OK));
        assertTrue(checkConvertIntToObj(ResponseCode.ERR_NULL));
        assertTrue(checkConvertIntToObj(ResponseCode.ERR_UNEXPECTED));
        assertTrue(checkConvertIntToObj(ResponseCode.ERR_INVALID));
        assertTrue(checkConvertIntToObj(ResponseCode.ERR_UNKNOWN));
        assertTrue(checkConvertIntToObj(ResponseCode.ERR_FAILURE));
        assertTrue(checkConvertIntToObj(ResponseCode.ERR_BUSY));
        assertTrue(checkConvertIntToObj(ResponseCode.ERR_REJECTED));
        assertTrue(checkConvertIntToObj(ResponseCode.ERR_RANGE));

        assertNull(convertIntToObj(-1));
    }

    public void testConvertObjToInt()
    {
        assertTrue(checkConvertObjToInt(ResponseCode.OK));
        assertTrue(checkConvertObjToInt(ResponseCode.ERR_NULL));
        assertTrue(checkConvertObjToInt(ResponseCode.ERR_UNEXPECTED));
        assertTrue(checkConvertObjToInt(ResponseCode.ERR_INVALID));
        assertTrue(checkConvertObjToInt(ResponseCode.ERR_UNKNOWN));
        assertTrue(checkConvertObjToInt(ResponseCode.ERR_FAILURE));
        assertTrue(checkConvertObjToInt(ResponseCode.ERR_BUSY));
        assertTrue(checkConvertObjToInt(ResponseCode.ERR_REJECTED));
        assertTrue(checkConvertObjToInt(ResponseCode.ERR_RANGE));
    }

    private boolean checkConvertIntToObj(ResponseCode rc)
    {
        return rc.equals(convertIntToObj(rc.getValue()));
    }

    private boolean checkConvertObjToInt(ResponseCode rc)
    {
        return rc.getValue() == convertObjToInt(rc);
    }

    private native ResponseCode convertIntToObj(int rc);
    private native int convertObjToInt(ResponseCode rc);
}