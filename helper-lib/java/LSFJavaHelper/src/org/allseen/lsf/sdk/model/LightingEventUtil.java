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
package org.allseen.lsf.sdk.model;

import org.allseen.lsf.sdk.Controller;
import org.allseen.lsf.sdk.Group;
import org.allseen.lsf.sdk.Lamp;
import org.allseen.lsf.sdk.LightingDirector;
import org.allseen.lsf.sdk.LightingItem;
import org.allseen.lsf.sdk.LightingItemErrorEvent;
import org.allseen.lsf.sdk.MasterScene;
import org.allseen.lsf.sdk.Preset;
import org.allseen.lsf.sdk.PulseEffect;
import org.allseen.lsf.sdk.Scene;
import org.allseen.lsf.sdk.SceneElement;
import org.allseen.lsf.sdk.TrackingID;
import org.allseen.lsf.sdk.TransitionEffect;
import org.allseen.lsf.sdk.listener.AnyCollectionListenerBase;
import org.allseen.lsf.sdk.listener.TrackingIDListener;

// TODO-REN: This class should probably be called ListenerUtil and moved to the listener package
public class LightingEventUtil {

    public static void listenFor(final TrackingID trackingId, final TrackingIDListener<LightingItem, LightingItemErrorEvent> listener) {
        LightingDirector.get().addListener(new AnyCollectionListenerBase<LightingItem, Lamp, Group, Preset, TransitionEffect, PulseEffect, SceneElement, Scene, MasterScene, Controller, LightingItemErrorEvent>() {

            @Override
            public void onAnyInitialized(TrackingID tid, LightingItem item) {
                if (tid != null && tid.value == trackingId.value) {
                    LightingDirector.get().removeListener(this);
                    listener.onTrackingIDReceived(tid, item);
                }
            }

            @Override
            public void onAnyError(LightingItemErrorEvent error) {
                if (error.trackingID != null && error.trackingID.value == trackingId.value) {
                    LightingDirector.get().removeListener(this);
                    listener.onTrackingIDError(error);
                }
            }
        });
    }
}