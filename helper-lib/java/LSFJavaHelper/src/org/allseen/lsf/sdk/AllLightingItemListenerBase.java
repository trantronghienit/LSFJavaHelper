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
 * Provides a base class for developers to extend and implement to receive all Lighting related events in the
 * Lighting system. Any method of the AllLightingItemListener interface that is not overridden in the subclass will
 * be treated as a no-op.
 * <p>
 * <b>Note: Once implemented, the callback must be registered with the LightingDirector in order
 * to receive all Lighting callbacks. See {@link LightingDirector#addListener(LightingListener) addListener}
 * for more information.</b>
 */
public class AllLightingItemListenerBase implements AllLightingItemListener {

    /**
     * Triggered when all data has been received from the lighting controller for a
     * particular Lamp.
     * <p>
     * <b>Note: This callback will fire only once for each Lamp when it is initialized.</b>
     *
     * @param lamp Reference to Lamp
     */
    @Override
    public void onLampInitialized(Lamp lamp)                                                    { }

    /**
     * Triggered every time new data is received from the lighting controller for a
     * particular Lamp.
     *
     * @param lamp Reference to Lamp
     */
    @Override
    public void onLampChanged(Lamp lamp)                                                        { }

    /**
     * Triggered when a particular Lamp has been removed from the Lighting system.
     * <p>
     * <b>Note: This callback will fire only once for each Lamp when it is removed from
     * the Lighting system.</b>
     *
     * @param lamp Reference to Lamp
     */
    @Override
    public void onLampRemoved(Lamp lamp)                                                        { }

    /**
     * Triggered when an error occurs on a Lamp operation.
     *
     * @param error Reference to LightingItemErrorEvent
     */
    @Override
    public void onLampError(LightingItemErrorEvent error)                                       { }

    /**
     * Triggered when all data has been received from the lighting controller for a
     * particular Group.
     * <p>
     * <b>Note: This callback will fire only once for each Group when it is initialized.</b>
     *
     * @param trackingId Reference to TrackingID
     * @param group Reference to Group
     */
    @Override
    public void onGroupInitialized(TrackingID trackingId, Group group)                          { }

    /**
     * Triggered every time new data is received from the lighting controller for a
     * particular Group.
     *
     * @param group Reference to Group
     */
    @Override
    public void onGroupChanged(Group group)                                                     { }

    /**
     * Triggered when a particular Group has been removed from the Lighting system.
     * <p>
     * <b>Note: This callback will fire only once for each Group when it is removed from
     * the Lighting system.</b>
     *
     * @param group Reference to Group
     */
    @Override
    public void onGroupRemoved(Group group)                                                     { }

    /**
     * Triggered when an error occurs on a Group operation.
     *
     * @param error Reference to LightingItemErrorEvent
     */
    @Override
    public void onGroupError(LightingItemErrorEvent error)                                      { }

    /**
     * Triggered when all data has been received from the lighting controller for a
     * particular Preset.
     * <p>
     * <b>Note: This callback will fire only once for each Preset when it is initialized.</b>
     *
     * @param trackingId Reference to TrackingID
     * @param preset Reference to Preset
     */
    @Override
    public void onPresetInitialized(TrackingID trackingId, Preset preset)                       { }

    /**
     * Triggered every time new data is received from the lighting controller for a
     * particular Preset.
     *
     * @param preset Reference to Preset
     */
    @Override
    public void onPresetChanged(Preset preset)                                                  { }

    /**
     * Triggered when a particular Preset has been removed from the Lighting system.
     * <p>
     * <b>Note: This callback will fire only once for each Preset when it is removed from
     * the Lighting system.</b>
     *
     * @param preset Reference to Preset
     */
    @Override
    public void onPresetRemoved(Preset preset)                                                  { }

    /**
     * Triggered when an error occurs on a Preset operation.
     *
     * @param error Reference to LightingItemErrorEvent
     */
    @Override
    public void onPresetError(LightingItemErrorEvent error)                                     { }

    /**
     * Triggered when all data has been received from the lighting controller for a
     * particular TransitionEffect.
     * <p>
     * <b>Note: This callback will fire only once for each TransitionEffect when it
     * is initialized.</b>
     *
     * @param trackingId Reference to TrackingID
     * @param effect Reference to TransitionEffect
     */
    @Override
    public void onTransitionEffectInitialized(TrackingID trackingId, TransitionEffect effect)   { }

    /**
     * Triggered every time new data is received from the lighting controller for a
     * particular TransitionEffect.
     *
     * @param effect Reference to TransitionEffect
     */
    @Override
    public void onTransitionEffectChanged(TransitionEffect effect)                              { }

    /**
     * Triggered when a particular TransitionEffect has been removed from the Lighting system.
     * <p>
     * <b>Note: This callback will fire only once for each TransitionEffect when it is removed from
     * the Lighting system.</b>
     *
     * @param effect Reference to TransitionEffect
     */
    @Override
    public void onTransitionEffectRemoved(TransitionEffect effect)                              { }

    /**
     * Triggered when an error occurs on a TransitionEffect operation.
     *
     * @param error Reference to LightingItemErrorEvent
     */
    @Override
    public void onTransitionEffectError(LightingItemErrorEvent error)                           { }

    /**
     * Triggered when all data has been received from the lighting controller for a
     * particular PulseEffect.
     * <p>
     * <b>Note: This callback will fire only once for each PulseEffect when it
     * is initialized.</b>
     *
     * @param trackingId Reference to TrackingID
     * @param effect Reference to PulseEffect
     */
    @Override
    public void onPulseEffectInitialized(TrackingID trackingId, PulseEffect effect)             { }

    /**
     * Triggered every time new data is received from the lighting controller for a
     * particular PulseEffect.
     *
     * @param effect Reference to PulseEffect
     */
    @Override
    public void onPulseEffectChanged(PulseEffect effect)                                        { }

    /**
     * Triggered when a particular PulseEffect has been removed from the Lighting system.
     * <p>
     * <b>Note: This callback will fire only once for each PulseEffect when it is removed from
     * the Lighting system.</b>
     *
     * @param effect Reference to PulseEffect
     */
    @Override
    public void onPulseEffectRemoved(PulseEffect effect)                                        { }

    /**
     * Triggered when an error occurs on a PulseEffect operation.
     *
     * @param error Reference to LightingItemErrorEvent
     */
    @Override
    public void onPulseEffectError(LightingItemErrorEvent error)                                { }

    /**
     * Triggered when all data has been received from the lighting controller for a
     * particular SceneElement.
     * <p>
     * <b>Note: This callback will fire only once for each SceneElement when it is initialized.</b>
     *
     * @param trackingId Reference to TrackingID
     * @param element Reference to SceneElement
     */
    @Override
    public void onSceneElementInitialized(TrackingID trackingId, SceneElement element)          { }

    /**
     * Triggered every time new data is received from the lighting controller for a
     * particular SceneElement.
     *
     * @param element Reference to SceneElement
     */
    @Override
    public void onSceneElementChanged(SceneElement element)                                     { }

    /**
     * Triggered when a particular SceneElement has been removed from the Lighting system.
     * <p>
     * <b>Note: This callback will fire only once for each SceneElement when it is removed from
     * the Lighting system.</b>
     *
     * @param element Reference to SceneElement
     */
    @Override
    public void onSceneElementRemoved(SceneElement element)                                     { }

    /**
     * Triggered when an error occurs on a SceneElement operation.
     *
     * @param error Reference to LightingItemErrorEvent
     */
    @Override
    public void onSceneElementError(LightingItemErrorEvent error)                               { }

    /**
     * Triggered when all data has been received from the lighting controller for a
     * particular Scene.
     * <p>
     * <b>Note: This callback will fire only once for each Scene when it is initialized.</b>
     *
     * @param trackingId Reference to TrackingID
     * @param scene Reference to Scene
     */
    @Override
    public void onSceneInitialized(TrackingID trackingId, Scene scene)                          { }

    /**
     * Triggered every time new data is received from the lighting controller for a
     * particular Scene.
     *
     * @param scene Reference to Scene
     */
    @Override
    public void onSceneChanged(Scene scene)                                                     { }

    /**
     * Triggered when a particular Scene has been removed from the Lighting system.
     * <p>
     * <b>Note: This callback will fire only once for each Scene when it is removed from
     * the Lighting system.</b>
     *
     * @param scene Reference to Scene
     */
    @Override
    public void onSceneRemoved(Scene scene)                                                     { }

    /**
     * Triggered when an error occurs on a Scene operation.
     *
     * @param error Reference to LightingItemErrorEvent
     */
    @Override
    public void onSceneError(LightingItemErrorEvent error)                                      { }

    /**
     * Triggered when all data has been received from the lighting controller for a
     * particular MasterScene.
     * <p>
     * <b>Note: This callback will fire only once for each MasterScene when it is initialized.</b>
     *
     * @param trackingId Reference to TrackingID
     * @param masterScene Reference to MasterScene
     */
    @Override
    public void onMasterSceneInitialized(TrackingID trackingId, MasterScene masterScene)        { }

    /**
     * Triggered every time new data is received from the lighting controller for a
     * particular MasterScene.
     *
     * @param masterScene Reference to MasterScene
     */
    @Override
    public void onMasterSceneChanged(MasterScene masterScene)                                   { }

    /**
     * Triggered when a particular MasterScene has been removed from the Lighting system.
     * <p>
     * <b>Note: This callback will fire only once for each MasterScene when it is removed from
     * the Lighting system.</b>
     *
     * @param masterScene Reference to MasterScene
     */
    @Override
    public void onMasterSceneRemoved(MasterScene masterScene)                                   { }

    /**
     * Triggered when an error occurs on a MasterScene operation.
     *
     * @param error Reference to LightingItemErrorEvent
     */
    @Override
    public void onMasterSceneError(LightingItemErrorEvent error)                                { }

    /**
     * Triggered when a new Controller becomes the leader in the Lighting system.
     *
     * @param leader Reference to new lead Controller
     */
    @Override
    public void onLeaderChange(Controller leader)                                               { }

    /**
     * Triggered when an error occurs on a Controller operation.
     *
     * @param error Reference to LightingItemErrorEvent
     */
    @Override
    public void onControllerErrors(LightingItemErrorEvent error)                                { }
}