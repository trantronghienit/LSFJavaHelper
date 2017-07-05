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

import org.allseen.lsf.sdk.AboutData;
import org.allseen.lsf.sdk.RankAvailability;
import org.allseen.lsf.sdk.RankMobility;
import org.allseen.lsf.sdk.RankNodeType;
import org.allseen.lsf.sdk.RankPower;

public abstract class ControllerService extends DefaultNativeClassWrapper {
    private static final NativeLibraryLoader LIBS = NativeLibraryLoader.LIBS;

    public ControllerService() {
        createNativeObject();
    }

    public abstract void populateDefaultProperties(AboutData aboutData);
    public abstract String getMacAddress(String generatedMacAddress);
    public abstract boolean isNetworkConnected();
    public abstract RankPower getRankPower();
    public abstract RankMobility getRankMobility();
    public abstract RankAvailability getRankAvailability();
    public abstract RankNodeType getRankNodeType();

    public native void start(String keyStorePath);
    public native void stop();
    public native void lightingReset();
    public native void factoryReset();
    public native void sendNetworkConnected();
    public native void sendNetworkDisconnected();
    public native String getName();
    public native boolean isLeader();

    protected void populateDefaultProperties(long nativeAboutDataHandle) {
        populateDefaultProperties(new AboutData(nativeAboutDataHandle));
    }

    @Override
    protected native void createNativeObject();

    @Override
    protected native void destroyNativeObject();
}
