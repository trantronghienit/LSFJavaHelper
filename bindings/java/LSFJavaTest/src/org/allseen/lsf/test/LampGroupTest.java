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

import java.util.Arrays;

import junit.framework.TestCase;

import org.allseen.lsf.LampGroup;
import org.allseen.lsf.NativeLibraryLoader;

public class LampGroupTest extends TestCase {
    private static final NativeLibraryLoader LIBS = NativeLibraryLoader.LIBS;

    private LampGroup lampGroup;

    @Override
    protected void setUp() throws Exception
    {
        lampGroup = new LampGroup();

        super.setUp();
    }

    @Override
    protected void tearDown() throws Exception
    {
        lampGroup = null;

        super.tearDown();
    }

    public void testBasicSet()
    {
        String[] lampIDs = new String[] { "A", "B", "C", "D", "E" };
        String[] lampGroupIDs = new String[] { "F", "G", "H" };

        lampGroup.setLamps(lampIDs);
        lampGroup.setLampGroups(lampGroupIDs);

        assertTrue(Arrays.deepEquals(lampGroup.getLamps(), lampIDs));
        assertTrue(Arrays.deepEquals(lampGroup.getLampGroups(), lampGroupIDs));
    }

    public void testDoubleSet()
    {
        String[] lampIDs = new String[] { "A", "B", "C", "D", "E" };
        String[] lampGroupIDs = new String[] { "F", "G", "H" };

        lampGroup.setLamps(lampIDs);
        lampGroup.setLampGroups(lampGroupIDs);

        lampGroup.setLamps(lampIDs);
        lampGroup.setLampGroups(lampGroupIDs);

        assertTrue(Arrays.deepEquals(lampGroup.getLamps(), lampIDs));
        assertTrue(Arrays.deepEquals(lampGroup.getLampGroups(), lampGroupIDs));
    }
}