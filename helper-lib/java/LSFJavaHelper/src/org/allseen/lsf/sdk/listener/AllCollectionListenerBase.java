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
package org.allseen.lsf.sdk.listener;

import org.allseen.lsf.sdk.TrackingID;

/**
 * <b>WARNING: This class is not intended to be used by clients, and its interface may change
 * in subsequent releases of the SDK</b>.
 */
public class AllCollectionListenerBase<LAMP, GROUP, PRESET, TRANSITIONEFFECT, PULSEEFFECT, SCENEELEMENT, SCENE, MASTERSCENE, CONTROLLER, ERROR>
    implements AllCollectionListener<LAMP, GROUP, PRESET, TRANSITIONEFFECT, PULSEEFFECT, SCENEELEMENT, SCENE, MASTERSCENE, CONTROLLER, ERROR> {

    @Override
    public void onLampInitialized(LAMP lamp)                                                        { }

    @Override
    public void onLampChanged(LAMP lamp)                                                            { }

    @Override
    public void onLampRemoved(LAMP lamp)                                                            { }

    @Override
    public void onLampError(ERROR error)                                                            { }

    @Override
    public void onGroupInitialized(TrackingID trackingId, GROUP group)                              { }

    @Override
    public void onGroupChanged(GROUP group)                                                         { }

    @Override
    public void onGroupRemoved(GROUP group)                                                         { }

    @Override
    public void onGroupError(ERROR error)                                                           { }

    @Override
    public void onPresetInitialized(TrackingID trackingId, PRESET preset)                           { }

    @Override
    public void onPresetChanged(PRESET preset)                                                      { }

    @Override
    public void onPresetRemoved(PRESET preset)                                                      { }

    @Override
    public void onPresetError(ERROR error)                                                          { }

    @Override
    public void onTransitionEffectInitialized(TrackingID trackingId, TRANSITIONEFFECT effect)       { }

    @Override
    public void onTransitionEffectChanged(TRANSITIONEFFECT effect)                                  { }

    @Override
    public void onTransitionEffectRemoved(TRANSITIONEFFECT effect)                                  { }

    @Override
    public void onTransitionEffectError(ERROR error)                                                { }

    @Override
    public void onPulseEffectInitialized(TrackingID trackingId, PULSEEFFECT effect)                 { }

    @Override
    public void onPulseEffectChanged(PULSEEFFECT effect)                                            { }

    @Override
    public void onPulseEffectRemoved(PULSEEFFECT effect)                                            { }

    @Override
    public void onPulseEffectError(ERROR error)                                                     { }

    @Override
    public void onSceneElementInitialized(TrackingID trackingId, SCENEELEMENT element)              { }

    @Override
    public void onSceneElementChanged(SCENEELEMENT element)                                         { }

    @Override
    public void onSceneElementRemoved(SCENEELEMENT element)                                         { }

    @Override
    public void onSceneElementError(ERROR error)                                                    { }

    @Override
    public void onSceneInitialized(TrackingID trackingId, SCENE scene)                              { }

    @Override
    public void onSceneChanged(SCENE scene)                                                         { }

    @Override
    public void onSceneRemoved(SCENE scene)                                                         { }

    @Override
    public void onSceneError(ERROR error)                                                           { }

    @Override
    public void onMasterSceneInitialized(TrackingID trackingId, MASTERSCENE masterScene)            { }

    @Override
    public void onMasterSceneChanged(MASTERSCENE masterScene)                                       { }

    @Override
    public void onMasterSceneRemoved(MASTERSCENE masterScene)                                       { }

    @Override
    public void onMasterSceneError(ERROR error)                                                     { }

    @Override
    public void onLeaderChange(CONTROLLER leader)                                                   { }

    @Override
    public void onControllerErrors(ERROR errorEvent)                                                { }
}