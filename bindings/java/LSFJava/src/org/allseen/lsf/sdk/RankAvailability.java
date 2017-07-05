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
 * Enum describing the average uptime for the device running the controller service
 * over a 24 hour period.
 */
public enum RankAvailability {
    /**
     * Indicates uptime of 0-3 hours.
     */
    ZERO_TO_THREE_HOURS,

    /**
     * Indicates uptime of 3-6 hours.
     */
    THREE_TO_SIX_HOURS,

    /**
     * Indicates uptime of 6-9 hours.
     */
    SIX_TO_NINE_HOURS,

    /**
     * Indicates uptime of 9-12 hours.
     */
    NINE_TO_TWELVE_HOURS,

    /**
     * Indicates uptime of 12-15 hours.
     */
    TWELVE_TO_FIFTEEN_HOURS,

    /**
     * Indicates uptime of 15-18 hours.
     */
    FIFTEEN_TO_EIGHTEEN_HOURS,

    /**
     * Indicates uptime of 18-21 hours.
     */
    EIGHTEEN_TO_TWENTY_ONE_HOURS,

    /**
     * Indicates uptime of 24 hours.
     */
    TWENTY_ONE_TO_TWENTY_FOUR_HOURS,

    /**
     * If OEMs return this value, the Controller Service will use ZERO_TO_THREE_HOURS as this is not a valid value.
     */
    OEM_CS_RANKPARAM_AVAILABILITY_LAST_VALUE;

    /** Static lookup, used by the native code */
    @SuppressWarnings("unused")
    private static RankAvailability fromValue(int value) {
        for (RankAvailability r : RankAvailability.values()) {
            if (r.getValue() == value) {
                return r;
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
