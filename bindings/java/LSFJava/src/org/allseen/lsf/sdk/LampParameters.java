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

package org.allseen.lsf.sdk;

import org.allseen.lsf.DefaultNativeClassWrapper;

/**
 * This class encapsulates all of the parameters for lamps and illustrates the current energy usage and
 * brightness output of the lamp.
 */
public class LampParameters extends DefaultNativeClassWrapper {
    /**
     * Constructs a LampParameters object.
     * <p>
     * <b>WARNING: This method is intended to be used internally. Client software should not instantiate
     * LampParameters directly, but should instead get them from the {@link Lamp} using the {@link Lamp#getParameters()}
     * method.</b>
     */
    public LampParameters() {
        createNativeObject();
    }

    /**
     * Constructs a LampParameters object using the provided LampParameters.
     * <p>
     * <b>WARNING: This method is intended to be used internally. Client software should not instantiate
     * LampParameters directly, but should instead get them from the {@link Lamp} using the {@link Lamp#getParameters()}
     * method.</b>
     *
     * @param other The LampParameters object to use
     */
    public LampParameters(LampParameters other) {
        //TODO-IMPL
    }

    //TODO-FIX The methods returning primitives should return their
    //         Object equivalent so that we can return NULL on failure

    /**
     * Returns the current energy usage of the lamp, in milliwatts.
     *
     * @return Energy usage of the lamp, in milliwatts
     */
    public native int getEnergyUsageMilliwatts();

    /**
     * Returns the current brightness of the lamp, in lumens.
     *
     * @return Brightness of the lamp, in lumens
     */
    public native int getLumens();

    /**
     * <b>WARNING: This method is not intended to be used by clients, and may change or be
     * removed in subsequent releases of the SDK.</b>
     */
    @Override
    protected native void createNativeObject();

    /**
     * <b>WARNING: This method is not intended to be used by clients, and may change or be
     * removed in subsequent releases of the SDK.</b>
     */
    @Override
    protected native void destroyNativeObject();
}