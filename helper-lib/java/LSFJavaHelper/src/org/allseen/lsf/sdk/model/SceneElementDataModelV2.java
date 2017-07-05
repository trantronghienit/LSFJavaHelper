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

import java.util.HashSet;
import java.util.Set;

public class SceneElementDataModelV2 extends LightingItemDataModel {
    public static final char TAG_PREFIX_SCENE_ELEMENT = 'S';

    public static String defaultName = "<Loading scene element info...>";

    protected boolean lampsInitialized;
    protected boolean groupsInitialized;
    protected boolean effectIdInitialized;

    private Set<String> lamps;
    private Set<String> groups;
    private String effectId;

    public SceneElementDataModelV2() {
        this((String)null);
    }

    public SceneElementDataModelV2(String sceneElementID) {
        this(sceneElementID, null);
    }

    public SceneElementDataModelV2(String sceneElementID, String sceneElementName) {
        super(sceneElementID, TAG_PREFIX_SCENE_ELEMENT, sceneElementName != null ? sceneElementName : defaultName);

        lamps = new HashSet<String>();
        groups = new HashSet<String>();
        effectId = null;

        lampsInitialized = false;
        groupsInitialized = false;
        effectIdInitialized = false;
    }

    public SceneElementDataModelV2(SceneElementDataModelV2 other) {
        super(other);

        lamps = new HashSet<String>(other.getLamps());
        groups = new HashSet<String>(other.getGroups());
        effectId = other.getEffectId();

        lampsInitialized = other.lampsInitialized;
        groupsInitialized = other.groupsInitialized;
        effectIdInitialized = other.effectIdInitialized;
    }

    public Set<String> getLamps() {
        return lamps;
    }

    public void setLamps(Set<String> lamps) {
        this.lamps = lamps;
        lampsInitialized = true;
    }

    public Set<String> getGroups() {
        return groups;
    }

    public void setGroups(Set<String> groups) {
        this.groups = groups;
        groupsInitialized = true;
    }

    public String getEffectId() {
        return effectId;
    }

    public void setEffectId(String effectId) {
        this.effectId = effectId;
        effectIdInitialized = true;
    }

    // Only checks immediate child lamps.
    public boolean containsLamp(String lampID) {
        return lamps.contains(lampID);
    }

    // Only checks immediate child groups.
    public boolean containsGroup(String groupID) {
        return groups.contains(groupID);
    }

    public boolean containsEffect(String effectID) {
        return effectID.equals(this.effectId);
    }

    @Override
    public boolean isInitialized() {
        return super.isInitialized() && lampsInitialized && groupsInitialized && effectIdInitialized;
    }
}