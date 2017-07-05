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

import org.allseen.lsf.sdk.listener.ControllerCollectionListener;
import org.allseen.lsf.sdk.listener.LightingListener;

/**
 * Provides an interface for developers to implement and receive all controller related events in the
 * Lighting system. Developers will be notified when a new controller becomes the leader on the network
 * and when there are any controller related errors.
 * <p>
 * <b>Note: Once implemented, the listener must be registered with the LightingDirector in order
 * to receive Controller callbacks. See {@link LightingDirector#addControllerListener(ControllerListener) addControllerListener}
 * for more information.</b>
 */
public interface ControllerListener extends ControllerCollectionListener<Controller, LightingItemErrorEvent>, LightingListener {

    /**
     * Triggered when a new controller becomes the leader in the Lighting system.
     *
     * @param leader Reference to new lead controller
     */
    @Override
    public void onLeaderChange(Controller leader);

    /**
     * Triggered when an error occurs on a controller operation.
     *
     * @param error Reference to LightingItemErrorEvent
     */
    @Override
    public void onControllerErrors(LightingItemErrorEvent error);
}