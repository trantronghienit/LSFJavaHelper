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
import org.allseen.lsf.sdk.factory.PresetFactory;
import org.allseen.lsf.sdk.listener.PresetCollectionListener;
import org.allseen.lsf.sdk.model.LightingItemFilter;
import org.allseen.lsf.sdk.model.PresetDataModel;

/**
 * <b>WARNING: This class is not intended to be used by clients, and its interface may change
 * in subsequent releases of the SDK</b>.
 */
public class PresetCollectionManager<PRESET, ERROR> extends LightingItemCollectionManager<PRESET, PresetCollectionListener<? super PRESET, ? super ERROR>, PresetDataModel, ERROR> {

    private final PresetFactory<PRESET, ERROR> factory;

    public PresetCollectionManager(LightingSystemManager<?, ?, PRESET, ?, ?, ?, ?, ?, ?, ?, ?> manager, PresetFactory<PRESET, ERROR> factory) {
        super(manager, factory);

        this.factory = factory;
    }

    public PRESET addPreset(String presetID) {
        return addPreset(presetID, factory.createPreset(presetID));
    }

    public PRESET addPreset(String presetID, PRESET preset) {
        return itemAdapters.put(presetID, preset);
    }

    public PRESET getPreset(String presetID) {
        return getAdapter(presetID);
    }

    public PRESET[] getPresets() {
        return getAdapters().toArray(factory.createPresets(size()));
    }

    public PRESET[] getPresets(LightingItemFilter<PRESET> filter) {
        Collection<PRESET> filteredPresets = getPresetsCollection(filter);
        return filteredPresets.toArray(factory.createPresets(filteredPresets.size()));
    }

    public Collection<PRESET> getPresetsCollection(LightingItemFilter<PRESET> filter) {
        return getAdapters(filter);
    }

    public Iterator<PRESET> getPresetIterator() {
        return getAdapters().iterator();
    }

    public Collection<PRESET> removePresets() {
        return removeAllAdapters();
    }

    public PRESET removePreset(String presetID) {
        return removeAdapter(presetID);
    }

    @Override
    protected void sendInitializedEvent(PresetCollectionListener<? super PRESET, ? super ERROR> listener, PRESET preset, TrackingID trackingID) {
        listener.onPresetInitialized(trackingID, preset);
    }

    @Override
    protected void sendChangedEvent(PresetCollectionListener<? super PRESET, ? super ERROR> listener, PRESET preset) {
        listener.onPresetChanged(preset);
    }

    @Override
    protected void sendRemovedEvent(PresetCollectionListener<? super PRESET, ? super ERROR> listener, PRESET preset) {
        listener.onPresetRemoved(preset);
    }

    @Override
    protected void sendErrorEvent(PresetCollectionListener<? super PRESET, ? super ERROR> listener, ERROR error) {
        listener.onPresetError(error);
    }

    @Override
    public PresetDataModel getModel(String presetID) {
        return getModel(getAdapter(presetID));
    }

    @Override
    public PresetDataModel getModel(PRESET preset) {
        return preset != null ? factory.findPresetDataModel(preset) : null;
    }
}