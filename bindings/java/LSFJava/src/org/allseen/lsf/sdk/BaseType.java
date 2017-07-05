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
 * Enum describing the type of the lamp base.
 */
public enum BaseType {
    /**
     * Specifies an invalid BaseType.
     */
    INVALID,

    /**
     * Specifies a lamp base type of E5.
     */
    E5,

    /**
     * Specifies a lamp base type of E10.
     */
    E10,

    /**
     * Specifies a lamp base type of E11.
     */
    E11,

    /**
     * Specifies a lamp base type of E12.
     */
    E12,

    /**
     * Specifies a lamp base type of E14.
     */
    E14,

    /**
     * Specifies a lamp base type of E17.
     */
    E17,

    /**
     * Specifies a lamp base type of E26.
     */
    E26,

    /**
     * Specifies a lamp base type of E27.
     */
    E27,

    /**
     * Specifies a lamp base type of E29.
     */
    E29,

    /**
     * Specifies a lamp base type of E39.
     */
    E39,

    /**
     * Specifies a lamp base type of B22.
     */
    B22,

    /**
     * Specifies a lamp base type of GU10.
     */
    GU10,

    /**
     * Specifies that the BaseType is unchanged and to use the last value
     * that was received.
     */
    LASTVALUE;

    /** Static lookup, used by the native code */
    @SuppressWarnings("unused")
    private static BaseType fromValue(int value) {
        for (BaseType c : BaseType.values()) {
            if (c.getValue() == value) {
                return c;
            }
        }

        return null;
    }

    /**
     * Gets the integer value of the enum.
     *
     * @return the integer value
     */
    public int getValue() { return ordinal(); }
}