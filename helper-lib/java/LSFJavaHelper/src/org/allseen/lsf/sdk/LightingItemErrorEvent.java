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
 * This class contains all pertinent information for errors that occur in the Lighting
 * system. This class provides an interface to retrieve the following data: the name of
 * the error, the response code returned by the Lighting operation, the ID of the Lighting
 * item for which the error occured, the tracking ID only if the error occurred during
 * creation, and an array of error codes if the error occurred in the controller.
 */
public class LightingItemErrorEvent {
    /**
     * The name of the error.
     */
    public String name;

    /**
     * The response code of the error.
     */
    public ResponseCode responseCode;

    /**
     * The ID of the Lighting item.
     */
    public String itemID;

    /**
     * The tracking ID of the Lighting item.
     */
    public TrackingID trackingID;

    /**
     * Array of error codes.
     */
    public ErrorCode[] errorCodes;

    /**
     * Constructs a LightingItemErrorEvent object.
     *
     * @param name The name of the error.
     * @param responseCode The response code of the error.
     * @param itemID The ID of the Lighting item.
     * @param trackingID The tracking ID of the Lighting Object.
     * @param errorCodes Array of ErrorCodes.
     */
    public LightingItemErrorEvent(String name, ResponseCode responseCode, String itemID, TrackingID trackingID, ErrorCode[] errorCodes) {
        this.name = name;
        this.responseCode = responseCode;
        this.itemID = itemID;
        this.trackingID = trackingID;
        this.errorCodes = errorCodes;
    }
}