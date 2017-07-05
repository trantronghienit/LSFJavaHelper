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

/**
 * This class encapsulates the tracking ID that is returned when groups, presets, effect, and scenes are
 * created in the Lighting system. The tracking ID can be matched to objects created by the developer
 * themselves.
 */
public class TrackingID {
    /**
     * Defines a constant for an undefined tracking ID.
     */
    public static final long UNDEFINED = -1;

    /**
     * The value of the tracking ID.
     */
    public long value;

    /**
     * Constructs a TrackingID object.
     * <p>
     * <b>WARNING: This method is intended to be used internally. Client software should not instantiate
     * TrackingID directly.</b>
     */
    public TrackingID() {
        this(UNDEFINED);
    }

    /**
     * Constructs a TrackingID object using the provided value.
     * <p>
     * <b>WARNING: This method is intended to be used internally. Client software should not instantiate
     * TrackingID directly.</b>
     */
    public TrackingID(long value) {
        this.value = value;
    }
}