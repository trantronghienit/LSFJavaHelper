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

import org.allseen.lsf.NativeLibraryLoader;
import org.allseen.lsf.PresetPulseEffect;

public class PresetPulseEffectTest extends TestCase {
    private static final NativeLibraryLoader LIBS = NativeLibraryLoader.LIBS;

    private PresetPulseEffect presetPulseEffect;

    @Override
    protected void setUp() throws Exception
    {
        presetPulseEffect = new PresetPulseEffect();

        super.setUp();
    }

    @Override
    protected void tearDown() throws Exception
    {
        presetPulseEffect = null;

        super.tearDown();
    }

    public void testBasicSet()
    {
        String[] lampIDs = new String[] { "F", "G", "H" };
        String[] lampGroupIDs = new String[] { "A", "B", "C", "D", "E" };
        int pulsePeriod = 1000;
        int pulseDuration = 2000;
        int pulseCount = 3;
        String fromPresetID = "PresetID-1";
        String toPresetID = "PresetID-2";

        presetPulseEffect.setLamps(lampIDs);
        presetPulseEffect.setLampGroups(lampGroupIDs);
        presetPulseEffect.setPulsePeriod(pulsePeriod);
        presetPulseEffect.setPulseDuration(pulseDuration);
        presetPulseEffect.setPulseCount(pulseCount);
        presetPulseEffect.setFromPreset(fromPresetID);
        presetPulseEffect.setToPreset(toPresetID);

        assertTrue(Arrays.deepEquals(presetPulseEffect.getLamps(), lampIDs));
        assertTrue(Arrays.deepEquals(presetPulseEffect.getLampGroups(), lampGroupIDs));
        assertTrue(presetPulseEffect.getPulsePeriod() == pulsePeriod);
        assertTrue(presetPulseEffect.getPulseDuration() == pulseDuration);
        assertTrue(presetPulseEffect.getPulseCount() == pulseCount);
        assertTrue(presetPulseEffect.getFromPreset().equals(fromPresetID));
        assertTrue(presetPulseEffect.getToPreset().equals(toPresetID));
    }
}