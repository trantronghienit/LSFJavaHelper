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
 * Provides an interface for developers to implement and provide the Lighting system a thread
 * to process all Lighting events. The lighting system will post all events and invoke all callbacks
 * on the thread associated with the LightingSystemQueue.
 * <p>
 * <b>Note: Once implemented, the queue must be passed to the LightingDirector via the start method.
 * See {@link LightingDirector#start(String, LightingSystemQueue) start} for more information.</b>
 */
public interface LightingSystemQueue extends org.allseen.lsf.sdk.manager.LightingSystemQueue {

    /**
     * Post the provided runnable to the thread associated with the LightingSystemQueue.
     *
     * @param r Runnable to post to the queue
     */
    @Override
    public void post(Runnable r);

    /**
     * Post the provided runnable to the thread associated with the LightingSystemQueue
     * after the specified delay.
     *
     * @param r Runnable to post to the queue
     * @param delay Specifies the delay to wait before posting the runnable
     */
    @Override
    public void postDelayed(Runnable r, int delay);

    /**
     * Stops the thread associated the LightingSystemQueue.
     */
    @Override
    public void stop();
}