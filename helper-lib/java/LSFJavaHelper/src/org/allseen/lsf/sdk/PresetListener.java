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

import org.allseen.lsf.sdk.listener.PresetCollectionListener;

/**
 * Provides an interface for developers to implement and receive all preset related events in the
 * Lighting system. Developers will be notified when presets are added, modified, initialized, and
 * removed from the lighting controller. Presets are considered initialized when all preset data has
 * been received from the lighting controller.
 * <p>
 * <b>Note: Once implemented, the listener must be registered with the LightingDirector in order
 * to receive Preset callbacks. See {@link LightingDirector#addPresetListener(PresetListener) addPresetListener}
 * for more information.</b>
 */
public interface PresetListener extends PresetCollectionListener<Preset, LightingItemErrorEvent> {

    /**
     * Triggered when all data has been received from the lighting controller for a
     * particular Preset.
     * <p>
     * <b>Note: This callback will fire only once for each Preset when all data has been received from
     * the lighting controller.</b>
     * <p>
     * <b>Note: The tracking ID is only valid for presets created within your application.</b>
     *
     * @param trackingId Reference to TrackingID
     * @param preset Reference to Preset
     */
    @Override
    public void onPresetInitialized(TrackingID trackingId, Preset preset);

    /**
     * Triggered every time new data is received from the lighting controller for a
     * particular Preset.
     *
     * @param preset Reference to Preset
     */
    @Override
    public void onPresetChanged(Preset preset);

    /**
     * Triggered when a particular Preset has been deleted from the Lighting system.
     * <p>
     * <b>Note: This callback will fire only once for each Preset when it is deleted from
     * the Lighting system.</b>
     *
     * @param preset Reference to Preset
     */
    @Override
    public void onPresetRemoved(Preset preset);

    /**
     * Triggered when an error occurs on a Preset operation.
     *
     * @param error Reference to LightingItemErrorEvent
     */
    @Override
    public void onPresetError(LightingItemErrorEvent error);
}