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

import java.util.Iterator;

import org.allseen.lsf.sdk.ErrorCode;
import org.allseen.lsf.sdk.ResponseCode;
import org.allseen.lsf.sdk.factory.ControllerFactory;
import org.allseen.lsf.sdk.listener.ControllerCollectionListener;
import org.allseen.lsf.sdk.model.ControllerDataModel;

/**
 * <b>WARNING: This class is not intended to be used by clients, and its interface may change
 * in subsequent releases of the SDK</b>.
 */
public class ControllerCollectionManager<CONTROLLER, ERROR> extends LightingItemListenerManager<ControllerCollectionListener<? super CONTROLLER, ? super ERROR>> {

    protected final ControllerFactory<CONTROLLER, ERROR> factory;
    protected final CONTROLLER leader;

    public ControllerCollectionManager(LightingSystemManager<?, ?, ?, ?, ?, ?, ?, ?, ?, CONTROLLER, ?> manager, ControllerFactory<CONTROLLER, ERROR> factory) {
        super(manager);

        this.factory = factory;
        this.leader = factory.createController("");
    }

    public CONTROLLER getLeader() {
        return leader;
    }

    public ControllerDataModel getLeaderModel() {
        return factory.findControllerDataModel(leader);
    }

    public void sendLeaderStateChangeEvent() {
        processAddedListeners();

        Iterator<ControllerCollectionListener<? super CONTROLLER, ? super ERROR>> i = currentListeners.iterator();
        ControllerCollectionListener<? super CONTROLLER, ? super ERROR> listener = getNext(i);

        while (listener != null) {
            listener.onLeaderChange(leader);
            listener = getNext(i);
        }
    }

    public void sendErrorEvent(String name, ResponseCode responseCode) {
        sendErrorEvent(factory.createError(name, responseCode, null, null, null));
    }

    public void sendErrorEvent(String name, ErrorCode[] errorCodes) {
        sendErrorEvent(factory.createError(name, null, null, null, errorCodes));
    }

    public void sendErrorEvent(ERROR errorEvent) {
        processAddedListeners();

        Iterator<ControllerCollectionListener<? super CONTROLLER, ? super ERROR>> i = currentListeners.iterator();
        ControllerCollectionListener<? super CONTROLLER, ? super ERROR> listener = getNext(i);

        while (listener != null) {
            sendErrorEvent(listener, errorEvent);
            listener = getNext(i);
        }
    }

    protected void sendErrorEvent(ControllerCollectionListener<? super CONTROLLER, ? super ERROR> listener, ERROR error) {
        listener.onControllerErrors(error);
    }
}