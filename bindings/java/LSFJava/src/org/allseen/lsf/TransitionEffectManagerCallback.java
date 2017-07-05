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
package org.allseen.lsf;

import org.allseen.lsf.sdk.ResponseCode;

public class TransitionEffectManagerCallback extends DefaultNativeClassWrapper {

    public TransitionEffectManagerCallback() {
        createNativeObject();
    }

    public void getTransitionEffectReplyCB(ResponseCode responseCode, String transitionEffectID, TransitionEffectV2 transitionEffect)                 { }
    public void applyTransitionEffectOnLampsReplyCB(ResponseCode responseCode, String transitionEffectID, String[] lampIDs)                         { }
    public void applyTransitionEffectOnLampGroupsReplyCB(ResponseCode responseCode, String transitionEffectID, String[] lampGroupIDs)               { }
    public void getAllTransitionEffectIDsReplyCB(ResponseCode responseCode, String[] transitionEffectIDs)                                           { }
    public void getTransitionEffectNameReplyCB(ResponseCode responseCode, String transitionEffectID, String language, String transitionEffectName)  { }
    public void setTransitionEffectNameReplyCB(ResponseCode responseCode, String transitionEffectID, String language)                               { }
    public void transitionEffectsNameChangedCB(String[] transitionEffectIDs)                                                                        { }
    public void createTransitionEffectReplyCB(ResponseCode responseCode, String transitionEffectID, long trackingID)                                { }
    public void transitionEffectsCreatedCB(String[] transitionEffectIDs)                                                                            { }
    public void updateTransitionEffectReplyCB(ResponseCode responseCode, String transitionEffectID)                                                 { }
    public void transitionEffectsUpdatedCB(String[] transitionEffectIDs)                                                                            { }
    public void deleteTransitionEffectReplyCB(ResponseCode responseCode, String transitionEffectID)                                                 { }
    public void transitionEffectsDeletedCB(String[] transitionEffectIDs)                                                                            { }

    @Override
    protected native void createNativeObject();

    @Override
    protected native void destroyNativeObject();
}