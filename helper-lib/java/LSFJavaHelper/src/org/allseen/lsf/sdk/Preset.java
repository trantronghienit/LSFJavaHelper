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

import java.util.ArrayList;
import java.util.Collection;

import org.allseen.lsf.sdk.manager.AllJoynManager;
import org.allseen.lsf.sdk.model.ColorItemDataModel;
import org.allseen.lsf.sdk.model.ColorStateConverter;
import org.allseen.lsf.sdk.model.LightingItemUtil;
import org.allseen.lsf.sdk.model.PresetDataModel;

/**
 * This class represents a Preset definition in the lighting controller. This class
 * provides an interface to perform Preset operations. Supported operations include
 * modifying the preset power and color properties, applying the preset to a lamp or
 * group, renaming, and deleting the preset. Presets are considered fully initialized
 * when the name and the power and color state have been received. Groups are still
 * operational and can be applied to lamps and groups even in the uninitialized state.
 * In general, presets are transitions effects that occur instantly.
 * <p>
 * <b>Note: This class is not meant to be instantiated directly. Presets should be retrieved
 * from the LightingDirector using the {@link LightingDirector#getPresets()} method.</b>
 * <p>
 * <b>Note: This class does not support Preset creation. See {@link LightingDirector#createPreset(Power, Color, String)}.</b>
 */
public class Preset extends MutableColorItem implements Effect {
    /**
     * Specifies the default name of the Preset.
     *
     * @param defaultName Default name of the Preset
     */
    public static void setDefaultName(String defaultName) {
        if (defaultName != null) {
            PresetDataModel.defaultName = defaultName;
        }
    }

    protected PresetDataModel presetModel;

    /**
     * Constructs a Preset using the specified ID.
     * <p>
     * <b>WARNING: This method is intended to be used internally. Client software should not instantiate
     * Presets directly, but should instead get them from the {@link LightingDirector} using the
     * {@link LightingDirector#getPresets()} method.</b>
     *
     * @param presetID The ID of the preset
     */
    protected Preset(String presetID) {
        this(presetID, null);
    }

    /**
     * Constructs a Preset using the specified ID and name.
     * <p>
     * <b>WARNING: This method is intended to be used internally. Client software should not instantiate
     * Presets directly, but should instead get them from the {@link LightingDirector} using the
     * {@link LightingDirector#getPresets()} method.</b>
     *
     * @param presetID The ID of the preset
     * @param presetName The name of the preset
     */
    protected Preset(String presetID, String presetName) {
        super();

        presetModel = new PresetDataModel(presetID, presetName);
    }

    /**
     * Applies the current Preset to the provided GroupMember.
     *
     * @param member The GroupMember the Preset will be applied to.
     */
    @Override
    public void applyTo(GroupMember member) {
        String errorContext = "Preset.applyTo() error";

        if (postInvalidArgIfNull(errorContext, member)) {
            member.applyPreset(this);
        }
    }

    /**
     * Modifies the current Preset using the provided power and color state.
     *
     * @param power New Preset power state.
     * @param color New Preset color state.
     */
    public void modify(Power power, Color color) {
        String errorContext = "Preset.modify() error";

        if (postInvalidArgIfNull(errorContext, power) && postInvalidArgIfNull(errorContext, color)) {
            postErrorIfFailure(errorContext,
                    AllJoynManager.presetManager.updatePreset(presetModel.id, LightingItemUtil.createLampStateFromView(
                            power == Power.ON, color.getHue(), color.getSaturation(), color.getBrightness(),
                            color.getColorTemperature())));
        }
    }

    /**
     * Renames the current Preset using the provided name.
     *
     * @param presetName The new name for the Preset
     */
    @Override
    public void rename(String presetName) {
        String errorContext = "Preset.rename() error";

        if (postInvalidArgIfNull(errorContext, presetName)) {
            postErrorIfFailure(errorContext,
                    AllJoynManager.presetManager.setPresetName(presetModel.id, presetName, LightingDirector.get().getDefaultLanguage()));
        }
    }

    /**
     * Permanently deletes the current Preset from the lighting controller.
     * <p>
     * <b>Note: You cannot delete a preset that is a member of a transition effect, pulse
     * effect, or scene element. The dependency must be deleted first.</b>
     */
    @Override
    public void delete() {
        String errorContext = "Preset.delete() error";

        postErrorIfFailure(errorContext,
                AllJoynManager.presetManager.deletePreset(presetModel.id));
    }

    /**
     * Modifies the power state of the current Preset.
     *
     * @param powerOn True for on, false for off
     */
    @Override
    public void setPowerOn(boolean powerOn) {
        modify((powerOn)? Power.ON : Power.OFF, getColor());
    }

    /**
     * Modifies the color state of the current Preset to the provided HSVT color.
     * <p>
     * <b>Note: If the provided HSVT values are outside the expected range, they will be normalized to the
     * expected range</b>
     *
     * @param hueDegrees The hue component of the desired color (0-360)
     * @param saturationPercent The saturation component of the desired color (0-100)
     * @param brightnessPercent The brightness component of the desired color (0-100)
     * @param colorTempDegrees The color temperature component of the desired color (1000-20000)
     */
    @Override
    public void setColorHsvt(int hueDegrees, int saturationPercent, int brightnessPercent, int colorTempDegrees) {
        modify(getPower(), new Color(hueDegrees, saturationPercent, brightnessPercent, colorTempDegrees));
    }

    /**
     * Changes the color state of the current Lamp to the provided hue.
     * <p>
     * <b>Note: If the provided hue values are outside the expected range, they will be normalized to the
     * expected range</b>
     *
     * @param hueDegrees The hue component of the desired color (0-360)
     */
    @Override
    public void setHue(int hueDegrees) {
        Color currentColor = getColor();
        currentColor.setHue(hueDegrees);
        modify(getPower(), currentColor);
    }

    /**
     * Changes the color state of the current Lamp to the provided saturation.
     * <p>
     * <b>Note: If the provided saturation values are outside the expected range, they will be normalized to the
     * expected range</b>
     *
     * @param saturationPercent The saturation component of the desired color (0-100)
     */
    @Override
    public  void setSaturation(int saturationPercent) {
        Color currentColor = getColor();
        currentColor.setSaturation(saturationPercent);
        modify(getPower(), currentColor);
    }

    /**
     * Changes the color state of the current Lamp to the provided brightness.
     * <p>
     * <b>Note: If the provided brightness values are outside the expected range, they will be normalized to the
     * expected range</b>
     *
     * @param brightnessPercent The brightness component of the desired color (0-100)
     */
    @Override
    public  void setBrightness(int brightnessPercent) {
        Color currentColor = getColor();
        currentColor.setBrightness(brightnessPercent);
        modify(getPower(), currentColor);
    }

    /**
     * Changes the color state of the current Lamp to the provided color temperature.
     * <p>
     * <b>Note: If the provided color temperature values are outside the expected range, they will be normalized to the
     * expected range</b>
     *
     * @param colorTempDegrees The color temperature component of the desired color (1000-20000)
     */
    @Override
    public  void setColorTemperature(int colorTempDegrees) {
        Color currentColor = getColor();
        currentColor.setColorTemperature(colorTempDegrees);
        modify(getPower(), currentColor);
    }

    /**
     * Tests to see if the current Preset lamp state matches the provided Preset.
     *
     * @param that The Preset for comparison.
     *
     * @return Returns true if the lamp states are equal, false otherwise
     */
    public boolean stateEquals(Preset that) {
        return getColorDataModel().stateEquals(that.getColorDataModel());
    }

    /**
     * Tests to see if the current Preset lamp state matches the provided lamp state.
     *
     * @param state The MyLampState for comparison.
     *
     * @return Returns true if the lamp states are equal, false otherwise
     */
    public boolean stateEquals(MyLampState state) {
        return stateEquals(state.getPower(), state.getColor());
    }

    /**
     * Tests to see if the current Preset power and color state matches the provided power and
     * color state.
     *
     * @param power The Power state to be compared
     * @param color The Color state to be compared
     *
     * @return Returns true if the power and color states are equal, false otherwise
     */
    public boolean stateEquals(Power power, Color color) {
        return getColorDataModel().stateEquals(
                power == Power.ON,
                ColorStateConverter.convertHueViewToModel(color.getHue()),
                ColorStateConverter.convertSaturationViewToModel(color.getSaturation()),
                ColorStateConverter.convertBrightnessViewToModel(color.getBrightness()),
                ColorStateConverter.convertColorTempViewToModel(color.getColorTemperature()));
    }

    /**
     * <b>WARNING: This method is not intended to be used by clients, and may change or be
     * removed in subsequent releases of the SDK.</b>
     */
    @Override
    protected Collection<LightingItem> getDependentCollection() {
        LightingDirector director = LightingDirector.get();
        Collection<LightingItem> dependents = new ArrayList<LightingItem>();

        dependents.addAll(director.getSceneCollectionManager().getScenesCollection(new HasComponentFilter<SceneV1>(Preset.this)));
        dependents.addAll(director.getTransitionEffectCollectionManager().getTransitionEffectsCollection(new HasComponentFilter<TransitionEffect>(Preset.this)));
        dependents.addAll(director.getPulseEffectCollectionManager().getPulseEffectsCollection(new HasComponentFilter<PulseEffect>(Preset.this)));

        return dependents;
    }

    /**
     * <b>WARNING: This method is not intended to be used by clients, and may change or be
     * removed in subsequent releases of the SDK.</b>
     */
    protected PresetDataModel getPresetDataModel() {
        return presetModel;
    }

    /**
     * <b>WARNING: This method is not intended to be used by clients, and may change or be
     * removed in subsequent releases of the SDK.</b>
     */
    @Override
    protected ColorItemDataModel getColorDataModel() {
        return getPresetDataModel();
    }

    /**
     * <b>WARNING: This method is not intended to be used by clients, and may change or be
     * removed in subsequent releases of the SDK.</b>
     */
    @Override
    protected void postError(final String name, final ResponseCode status) {
        LightingDirector.get().getLightingSystemManager().getQueue().post(new Runnable() {
            @Override
            public void run() {
                LightingDirector.get().getPresetCollectionManager().sendErrorEvent(name, status, getId());
            }
        });
    }
}