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

import org.allseen.lsf.PresetPulseEffect;
import org.allseen.lsf.sdk.model.EffectType;

/**
 * Base interface implemented by all Lighting items that are effects.
 */
public interface Effect extends LightingItemInterface, DeletableItem {

    /**
     * Constant specifying preset effect type.
     */
    public static final String EFFECT_TYPE_PRESET = EffectType.None.toString();

    /**
     * Constant specifying transition effect type.
     */
    public static final String EFFECT_TYPE_TRANSITION = EffectType.Transition.toString();

    /**
     * Constant specifying pulse effect type.
     */
    public static final String EFFECT_TYPE_PULSE = EffectType.Pulse.toString();

    /**
     * Constant specifying preset to use current state of GroupMember as starting state.
     */
    public static final String PRESET_ID_USE_CURRENT_STATE = PresetPulseEffect.PRESET_ID_CURRENT_STATE;

    /**
     * Applies the current effect to the provided Lighting item.
     *
     * @param member Lighting item to apply the effect
     */
    public void applyTo(GroupMember member);
}