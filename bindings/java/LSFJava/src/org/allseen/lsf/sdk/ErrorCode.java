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
 * Enum describing Error Codes that represent any internal errors in the Lighting
 * controller client. The controller client will invoke the ControllerClientErrorCB
 * with this error code to inform the user about an internal error.
 */
public enum ErrorCode {
    /**
     * Indicates no error in the controller client.
     */
    NONE,

    /**
     * Indicates an error registering the AllJoyn signal handlers.
     */
    ERR_REGISTERING_SIGNAL_HANDLERS,

    /**
     * Indicates that no controller service was found.
     */
    ERR_NO_ACTIVE_CONTROLLER_SERVICE_FOUND,

    /**
     * Indicates that an AllJoyn method call timed out.
     */
    ERR_ALLJOYN_METHOD_CALL_TIMEOUT,

    /**
     * Indicates an irrecoverable error.
     */
    ERR_IRRECOVERABLE,

    /**
     * Indicates that the AllJoyn bus attachment is not connected.
     */
    ERR_DISCONNECTED_FROM_BUS,

    /**
     * Indicates an error occurred while the controller client is shutting down.
     */
    ERR_CONTROLLER_CLIENT_EXITING,

    /**
     * Indicates that an AllJoyn message was sent to the controller service with invalid args.
     */
    ERR_MESSAGE_WITH_INVALID_ARGS,

    /**
     * Indicates that the same error occurred.
     */
    ERROR_CODE_LAST;

    /** Static lookup, used by the native code */
    @SuppressWarnings("unused")
    private static ErrorCode fromValue(int value) {
        for (ErrorCode c : ErrorCode.values()) {
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