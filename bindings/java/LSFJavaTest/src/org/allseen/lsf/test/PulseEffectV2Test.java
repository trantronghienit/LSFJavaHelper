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
import org.allseen.lsf.PulseEffectV2;

public class PulseEffectV2Test extends TestCase {
    private static final NativeLibraryLoader LIBS = NativeLibraryLoader.LIBS;

    private PulseEffectV2 PulseEffectV2;

    @Override
    protected void setUp() throws Exception
    {
        PulseEffectV2 = new PulseEffectV2();

        super.setUp();
    }

    @Override
    protected void tearDown() throws Exception
    {
        PulseEffectV2 = null;

        super.tearDown();
    }

    public void testBasicSet()
    {
        int pulsePeriod = 1000;
        int pulseDuration = 2000;
        int numPulses = 5;
        int fromBrightness = 1;
        int toBrightness = 2;

        LampState toLampState = new LampState();
        LampState fromLampState = new LampState();

        fromLampState.setBrightness(fromBrightness);
        toLampState.setBrightness(toBrightness);

        String fromPresetID = "PresetID-1";
        String toPresetID = "PresetID-2";

        PulseEffectV2.setToLampState(toLampState);
        PulseEffectV2.setFromLampState(fromLampState);
        PulseEffectV2.setToPresetID(toPresetID);
        PulseEffectV2.setFromPresetID(fromPresetID);
        PulseEffectV2.setPulsePeriod(pulsePeriod);
        PulseEffectV2.setPulseDuration(pulseDuration);
        PulseEffectV2.setNumPulses(numPulses);

        assertTrue(PulseEffectV2.getFromLampState().getBrightness() == fromBrightness);
        assertTrue(PulseEffectV2.getToLampState().getBrightness() == toBrightness);
        assertTrue(PulseEffectV2.getToPresetID().equals(toPresetID));
        assertTrue(PulseEffectV2.getFromPresetID().equals(fromPresetID));
        assertTrue(PulseEffectV2.getPulsePeriod() == pulsePeriod);
        assertTrue(PulseEffectV2.getPulseDuration() == pulseDuration);
        assertTrue(PulseEffectV2.getNumPulses() == numPulses);

    }
}