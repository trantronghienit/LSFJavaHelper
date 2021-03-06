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

import org.allseen.lsf.sdk.listener.GroupCollectionListener;

/**
 * Provides an interface for developers to implement and receive all group related events in the
 * Lighting system. Developers will be notified when groups are added, modified, initialized, and
 * deleted from the lighting controller. Groups are considered initialized when all group data has
 * been received from the lighting controller.
 * <p>
 * <b>Note: Once implemented, the listener must be registered with the LightingDirector in order
 * to receive Group callbacks. See {@link LightingDirector#addGroupListener(GroupListener) addGroupListener}
 * for more information.</b>
 */
public interface GroupListener extends GroupCollectionListener<Group, LightingItemErrorEvent> {

    /**
     * Triggered when all data has been received from the lighting controller for a
     * particular Group.
     * <p>
     * <b>Note: This callback will fire only once for each Group when all data has been received from
     * the lighting controller.</b>
     * <p>
     * <b>Note: The tracking ID is only valid for groups created within your application.</b>
     *
     * @param trackingId Reference to TrackingID
     * @param group Reference to Group
     */
    @Override
    public void onGroupInitialized(TrackingID trackingId, Group group);

    /**
     * Triggered every time new data is received from the lighting controller for a
     * particular Group.
     *
     * @param group Reference to Group
     */
    @Override
    public void onGroupChanged(Group group);

    /**
     * Triggered when a particular Group has been deleted from the lighting controller.
     * <p>
     * <b>Note: This callback will fire only once for each Group when it is deleted from
     * the lighting controller.</b>
     *
     * @param group Reference to Group
     */
    @Override
    public void onGroupRemoved(Group group);

    /**
     * Triggered when an error occurs on a Group operation.
     *
     * @param error Reference to LightingItemErrorEvent
     */
    @Override
    public void onGroupError(LightingItemErrorEvent error);
}