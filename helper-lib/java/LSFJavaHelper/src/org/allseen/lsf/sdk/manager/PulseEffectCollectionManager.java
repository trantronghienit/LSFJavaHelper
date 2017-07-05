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
package org.allseen.lsf.sdk.manager;

import java.util.Collection;
import java.util.Iterator;

import org.allseen.lsf.sdk.TrackingID;
import org.allseen.lsf.sdk.factory.PulseEffectFactory;
import org.allseen.lsf.sdk.listener.PulseEffectCollectionListener;
import org.allseen.lsf.sdk.model.LightingItemFilter;
import org.allseen.lsf.sdk.model.PulseEffectDataModelV2;

/**
 * <b>WARNING: This class is not intended to be used by clients, and its interface may change
 * in subsequent releases of the SDK</b>.
 */
public class PulseEffectCollectionManager<PULSEEFFECT, ERROR> extends LightingItemCollectionManager<PULSEEFFECT, PulseEffectCollectionListener<? super PULSEEFFECT, ? super ERROR>, PulseEffectDataModelV2, ERROR> {

    protected final PulseEffectFactory<PULSEEFFECT, ERROR> factory;

    public PulseEffectCollectionManager(LightingSystemManager<?, ?, ?, ?, PULSEEFFECT, ?, ?, ?, ?, ?, ?> manager, PulseEffectFactory<PULSEEFFECT, ERROR> factory) {
        super(manager, factory);

        this.factory = factory;
    }

    public PULSEEFFECT addPulseEffect(String pulseEffectID) {
        return addPulseEffect(pulseEffectID, factory.createPulseEffect(pulseEffectID));
    }

    public PULSEEFFECT addPulseEffect(String pulseEffectID, PULSEEFFECT pulseEffect) {
        return itemAdapters.put(pulseEffectID, pulseEffect);
    }

    public PULSEEFFECT getPulseEffect(String pulseEffectID) {
        return getAdapter(pulseEffectID);
    }

    public PULSEEFFECT[] getPulseEffects() {
        return getAdapters().toArray(factory.createPulseEffects(size()));
    }

    public PULSEEFFECT[] getPulseEffects(LightingItemFilter<PULSEEFFECT> filter) {
        Collection<PULSEEFFECT> filteredPulseEffects = getPulseEffectsCollection(filter);
        return filteredPulseEffects.toArray(factory.createPulseEffects(filteredPulseEffects.size()));
    }

    public Collection<PULSEEFFECT> getPulseEffectsCollection(LightingItemFilter<PULSEEFFECT> filter) {
        return getAdapters(filter);
    }

    public Iterator<PULSEEFFECT> getPulseEffectIterator() {
        return getAdapters().iterator();
    }

    public Collection<PULSEEFFECT> removePulseEffect() {
        return removeAllAdapters();
    }

    public PULSEEFFECT removePulseEffect(String pulseEffectId) {
        return removeAdapter(pulseEffectId);
    }

    @Override
    protected void sendInitializedEvent(PulseEffectCollectionListener<? super PULSEEFFECT, ? super ERROR> listener, PULSEEFFECT pulseEffect, TrackingID trackingID) {
        listener.onPulseEffectInitialized(trackingID, pulseEffect);
    }

    @Override
    protected void sendChangedEvent(PulseEffectCollectionListener<? super PULSEEFFECT, ? super ERROR> listener, PULSEEFFECT pulseEffect) {
        listener.onPulseEffectChanged(pulseEffect);
    }

    @Override
    protected void sendRemovedEvent(PulseEffectCollectionListener<? super PULSEEFFECT, ? super ERROR> listener, PULSEEFFECT pulseEffect) {
        listener.onPulseEffectRemoved(pulseEffect);
    }

    @Override
    protected void sendErrorEvent(PulseEffectCollectionListener<? super PULSEEFFECT, ? super ERROR> listener, ERROR error) {
        listener.onPulseEffectError(error);
    }

    @Override
    public PulseEffectDataModelV2 getModel(String pulseEffectID) {
        return getModel(getAdapter(pulseEffectID));
    }

    @Override
    public PulseEffectDataModelV2 getModel(PULSEEFFECT pulseEffect) {
        return pulseEffect != null ? factory.findPulseEffectDataModel(pulseEffect) : null;
    }

}