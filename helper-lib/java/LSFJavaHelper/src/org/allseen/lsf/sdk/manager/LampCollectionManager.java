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

import java.util.ArrayDeque;
import java.util.Collection;
import java.util.Iterator;
import java.util.Queue;

import org.allseen.lsf.sdk.TrackingID;
import org.allseen.lsf.sdk.factory.LampFactory;
import org.allseen.lsf.sdk.listener.LampCollectionListener;
import org.allseen.lsf.sdk.model.LampDataModel;
import org.allseen.lsf.sdk.model.LightingItemFilter;

/**
 * <b>WARNING: This class is not intended to be used by clients, and its interface may change
 * in subsequent releases of the SDK</b>.
 */
public class LampCollectionManager<LAMP, ERROR> extends LightingItemCollectionManager<LAMP, LampCollectionListener<? super LAMP, ? super ERROR>, LampDataModel, ERROR> {

    protected final LampFactory<LAMP, ERROR> factory;
    protected final Queue<String> lampIDs = new ArrayDeque<String>();

    public LampCollectionManager(LightingSystemManager<LAMP, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?> manager, LampFactory<LAMP, ERROR> factory) {
        super(manager, factory);
        this.factory = factory;
    }

    @Override
    protected Collection<String> getIDCollection() {
        // Overridden for efficiency
        return lampIDs;
    }

    public LAMP addLamp(String lampID) {
        return addLamp(lampID, factory.createLamp(lampID));
    }

    public LAMP addLamp(String lampID, LAMP lamp) {
        itemAdapters.put(lampID, lamp);
        lampIDs.add(lampID);

        return lamp;
    }

    public LAMP getLamp(String lampID) {
        return getAdapter(lampID);
    }

    public LAMP[] getLamps() {
        return getAdapters().toArray(factory.createLamps(size()));
    }

    public LAMP[] getLamps(LightingItemFilter<LAMP> filter) {
        Collection<LAMP> filteredLamps = getLampsCollection(filter);
        return filteredLamps.toArray(factory.createLamps(filteredLamps.size()));
    }

    public Collection<LAMP> getLampsCollection(LightingItemFilter<LAMP> filter) {
        return getAdapters(filter);
    }

    public Iterator<LAMP> getLampIterator() {
        return getAdapters().iterator();
    }

    public Collection<LAMP> removeAllLamps() {
        return removeAllAdapters();
    }

    public LAMP removeLamp(String lampID) {
        lampIDs.remove(lampID);

        return removeAdapter(lampID);
    }

    @Override
    protected void sendInitializedEvent(LampCollectionListener<? super LAMP, ? super ERROR> listener, LAMP lamp, TrackingID trackingID) {
        listener.onLampInitialized(lamp);
    }

    @Override
    protected void sendChangedEvent(LampCollectionListener<? super LAMP, ? super ERROR> listener, LAMP lamp) {
        listener.onLampChanged(lamp);
    }

    @Override
    protected void sendRemovedEvent(LampCollectionListener<? super LAMP, ? super ERROR> listener, LAMP lamp) {
        listener.onLampRemoved(lamp);
    }

    @Override
    protected void sendErrorEvent(LampCollectionListener<? super LAMP, ? super ERROR> listener, ERROR error) {
        listener.onLampError(error);
    }

    @Override
    public LampDataModel getModel(String lampID) {
        return getModel(getAdapter(lampID));
    }

    @Override
    public LampDataModel getModel(LAMP lamp) {
        return lamp != null ? factory.findLampDataModel(lamp) : null;
    }
}