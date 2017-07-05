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

import org.allseen.lsf.LampState;
import org.allseen.lsf.NativeLibraryLoader;

public class LampStateTest extends TestCase {
    private static final NativeLibraryLoader LIBS = NativeLibraryLoader.LIBS;

    private LampState lampState;

    @Override
    protected void setUp() throws Exception
    {
        lampState = new LampState();

        super.setUp();
    }

    @Override
    protected void tearDown() throws Exception
    {
        lampState = null;

        super.tearDown();
    }

    public void testLowValues()
    {
        boolean onOff = true;
        long hue = 500;
        long saturation = 600;
        long brightness = 700;
        long colorTemp = 800;

        lampState.setOnOff(onOff);
        lampState.setHue(hue);
        lampState.setSaturation(saturation);
        lampState.setBrightness(brightness);
        lampState.setColorTemp(colorTemp);

        assertTrue(lampState.getOnOff() == onOff);
        assertTrue(lampState.getHue() == hue);
        assertTrue(lampState.getSaturation() == saturation);
        assertTrue(lampState.getBrightness() == brightness);
        assertTrue(lampState.getColorTemp() == colorTemp);
    }

    public void testHighValues()
    {
        boolean onOff = true;
        long hue = 2200000500L;
        long saturation = 2200000600L;
        long brightness = 2200000700L;
        long colorTemp = 2200000800L;

        lampState.setOnOff(onOff);
        lampState.setHue(hue);
        lampState.setSaturation(saturation);
        lampState.setBrightness(brightness);
        lampState.setColorTemp(colorTemp);

        assertTrue(lampState.getOnOff() == onOff);
        assertTrue(lampState.getHue() == hue);
        assertTrue(lampState.getSaturation() == saturation);
        assertTrue(lampState.getBrightness() == brightness);
        assertTrue(lampState.getColorTemp() == colorTemp);
    }
}