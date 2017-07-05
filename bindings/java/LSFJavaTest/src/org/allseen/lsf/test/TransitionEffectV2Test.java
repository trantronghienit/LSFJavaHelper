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
import org.allseen.lsf.TransitionEffectV2;

public class TransitionEffectV2Test extends TestCase {
    private static final NativeLibraryLoader LIBS = NativeLibraryLoader.LIBS;

    private TransitionEffectV2 TransitionEffectV2;

    @Override
    protected void setUp() throws Exception
    {
        TransitionEffectV2 = new TransitionEffectV2();

        super.setUp();
    }

    @Override
    protected void tearDown() throws Exception
    {
        TransitionEffectV2 = null;

        super.tearDown();
    }

    public void testBasicSet()
    {
        int transitionPeriod = 100;
        LampState lampState = new LampState();
        String presetID = "PresetID-1";
        int brightness = 3;

        lampState.setBrightness(brightness);

        TransitionEffectV2.setLampState(lampState);
        TransitionEffectV2.setPresetID(presetID);
        TransitionEffectV2.setTransitionPeriod(transitionPeriod);

        assertTrue(TransitionEffectV2.getLampState().getBrightness() == brightness);
        assertTrue(TransitionEffectV2.getPresetID().equals(presetID));
        assertTrue(TransitionEffectV2.getTransitionPeriod()== transitionPeriod);

    }
}