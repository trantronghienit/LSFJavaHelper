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

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.allseen.lsf.sdk.ResponseCode;
import org.allseen.lsf.sdk.TrackingID;
import org.allseen.lsf.sdk.factory.LightingItemFactory;
import org.allseen.lsf.sdk.model.LightingItemFilter;

/**
 * <b>WARNING: This class is not intended to be used by clients, and its interface may change
 * in subsequent releases of the SDK</b>.
 */
public abstract class LightingItemCollectionManager<ADAPTER, LISTENER, MODEL, ERROR> extends LightingItemListenerManager<LISTENER> {

    protected final LightingItemFactory<ERROR> factory;
    protected final Map<String, ADAPTER> itemAdapters = new HashMap<String, ADAPTER>();

    public LightingItemCollectionManager(LightingSystemManager<?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?> manager, LightingItemFactory<ERROR> factory) {
        super(manager);

        this.factory = factory;
    }

    public boolean hasID(String itemID) {
        return itemAdapters.containsKey(itemID);
    }

    protected Collection<String> getIDCollection() {
        return itemAdapters.keySet();
    }

    public Iterator<String> getIDIterator() {
        return getIDCollection().iterator();
    }

    public String[] getIDArray() {
        return getIDArray(new String[size()]);
    }

    public String[] getIDArray(String[] itemIDs) {
        return getIDCollection().toArray(itemIDs);
    }

    public int size() {
        return itemAdapters.size();
    }

    protected ADAPTER getAdapter(String itemID) {
        return itemAdapters.get(itemID);
    }

    protected Collection<ADAPTER> removeAllAdapters() {
        List<ADAPTER> list = new ArrayList<ADAPTER>(size());
        Iterator<ADAPTER> i = getAdapters().iterator();

        while (i.hasNext()) {
            ADAPTER item = i.next();

            i.remove();
            list.add(item);

            sendRemovedEvent(item);
        }

        return list;
    }

    protected ADAPTER removeAdapter(String itemID) {
        ADAPTER item = itemAdapters.remove(itemID);

        sendRemovedEvent(item);

        return item;
    }

    protected Collection<ADAPTER> getAdapters(LightingItemFilter<ADAPTER> filter) {
        Collection<ADAPTER> filteredCollection;

        if (filter != null) {
            filteredCollection = new ArrayList<ADAPTER>();

            for (ADAPTER adapter : itemAdapters.values()) {
                if (filter.passes(adapter)) {
                    filteredCollection.add(adapter);
                }
            }
        } else {
            filteredCollection = getAdapters();
        }

        return filteredCollection;
    }

    protected Collection<ADAPTER> getAdapters() {
        return itemAdapters.values();
    }

    public void sendInitializedEvent(String itemID) {
        sendInitializedEvent(itemID, null);
    }

    public void sendInitializedEvent(String itemID, TrackingID trackingID) {
        sendInitializedEvent(getAdapter(itemID), trackingID);
    }

    public void sendInitializedEvent(ADAPTER item, TrackingID trackingID) {
        processAddedListeners();

        if (item != null) {
            Iterator<LISTENER> i = currentListeners.iterator();
            LISTENER listener = getNext(i);

            while (listener != null) {
                sendInitializedEvent(listener, item, trackingID);
                listener = getNext(i);
            }
        }
    }

    public void sendChangedEvent(String itemID) {
        sendChangedEvent(getAdapter(itemID));
    }

    public void sendChangedEvent(ADAPTER item) {
        processAddedListeners();

        if (item != null) {
            Iterator<LISTENER> i = currentListeners.iterator();
            LISTENER listener = getNext(i);

            while (listener != null) {
                sendChangedEvent(listener, item);
                listener = getNext(i);
            }
        }
    }

    public void sendRemovedEvent(ADAPTER item) {
        processAddedListeners();

        if (item != null) {
            Iterator<LISTENER> i = currentListeners.iterator();
            LISTENER listener = getNext(i);

            while (listener != null) {
                sendRemovedEvent(listener, item);
                listener = getNext(i);
            }
        }
    }

    public void sendErrorEvent(String name, ResponseCode responseCode) {
        sendErrorEvent(name, responseCode, null);
    }

    public void sendErrorEvent(String name, ResponseCode responseCode, String itemID) {
        sendErrorEvent(name, responseCode, itemID, null);
    }

    public void sendErrorEvent(String name, ResponseCode responseCode, String itemID, TrackingID trackingID) {
        sendErrorEvent(factory.createError(name, responseCode, itemID, trackingID, null));
    }

    public void sendErrorEvent(ERROR errorEvent) {
        processAddedListeners();

        Iterator<LISTENER> i = currentListeners.iterator();
        LISTENER listener = getNext(i);

        while (listener != null) {
            postSendErrorEvent(listener, errorEvent);
            listener = getNext(i);
        }
    }

    public void postSendErrorEvent(final LISTENER listener, final ERROR error) {
        manager.getQueue().post(new Runnable() {
            @Override
            public void run() {
                sendErrorEvent(listener, error);
            }
        });
    }

    protected abstract void sendInitializedEvent(LISTENER listener, ADAPTER item, TrackingID trackingID);
    protected abstract void sendChangedEvent(LISTENER listener, ADAPTER item);
    protected abstract void sendRemovedEvent(LISTENER listener, ADAPTER item);
    protected abstract void sendErrorEvent(LISTENER listener, ERROR error);

    public abstract MODEL getModel(String itemID);
    public abstract MODEL getModel(ADAPTER item);
}