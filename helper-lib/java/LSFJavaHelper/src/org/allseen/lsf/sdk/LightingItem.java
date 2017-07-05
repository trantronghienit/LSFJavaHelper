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

import org.allseen.lsf.sdk.model.LightingItemDataModel;
import org.allseen.lsf.sdk.model.LightingItemFilter;
import org.allseen.lsf.sdk.model.LightingItemSortableTag;

/**
 * Abstract base class for items in a Lighting system that implements the
 * LightingItemInterface.
 */
public abstract class LightingItem implements LightingItemInterface {
    /**
     * <b>WARNING: This method is not intended to be used by clients, and may change or be
     * removed in subsequent releases of the SDK.</b>
     */
    protected class HasComponentFilter<ITEM extends LightingItem> implements LightingItemFilter<ITEM> {
        protected LightingItem component;

        public HasComponentFilter(LightingItem component) {
            this.component = component;
        }

        @Override
        public boolean passes(ITEM item) {
            return item.hasComponent(component);
        }
    }

    /**
     * Returns the name of the Lighting item.
     *
     * @return Name of the Lighting item
     */
    @Override
    public String getName() {
        return getItemDataModel().getName();
    }

    /**
     * Returns the ID of the Lighting item.
     *
     * @return ID of the Lighting item
     */
    @Override
    public String getId() {
        return getItemDataModel().id;
    }

    /**
     * Returns the sortable tag of the Lighting item.
     *
     * @return Sortable tag of the Lighting item
     */
    public LightingItemSortableTag getTag() {
        return getItemDataModel().tag;
    }

    /**
     * Returns a boolean indicating whether or not the Lighting item has received all
     * its data from the lighting controller.
     *
     * @return Returns true if the Lighting item is initialized, false otherwise
     */
    public boolean isInitialized() {
        return getItemDataModel().isInitialized();
    }

    /**
     * Returns the hash code of the Lighting item.
     *
     * @return Hash code of the Lighting item
     */
    @Override
    public int hashCode() {
        return getId().hashCode();
    }

    /**
     * Returns a boolean indicating whether or not this Lighting item is equivalent to
     * the provided object.
     *
     * @param other The other Object to compare.
     *
     * @return Returns true if the objects are equivalent, false otherwise
     */
    @Override
    public boolean equals(Object other) {
        boolean equivalent = false;

        if (other != null && other instanceof LightingItem) {
            equivalent = getId().equals(((LightingItem) other).getId());
        }

        return equivalent;
    }

    /**
     * Returns an array of Lighting items that contain this Lighting item as a child.
     *
     * @return Array of parent Lighting items
     */
    @Override
    public LightingItem[] getDependents() {
        Collection<LightingItem> dependents = getDependentCollection();
        return dependents.toArray(new LightingItem[dependents.size()]);
    }

    /**
     * Returns an array of the Lighting items that comprise this Lighting item.
     *
     * @return Array of children Lighting items
     */
    @Override
    public LightingItem[] getComponents() {
        Collection<LightingItem> components = getComponentCollection();
        return components.toArray(new LightingItem[components.size()]);
    }

    /**
     * Returns a boolean that indicates whether this Lighting item is comprised of the
     * provided object.
     *
     * @param item Potential child Lighting item
     *
     * @return Returns true if this Lighting item is comprised of the provided object, false otherwise
     */
    public boolean hasComponent(LightingItem item) {
        // Default implementation -- subclasses may override for efficiency
        return getComponentCollection().contains(item);
    }

    /**
     * <b>WARNING: This method is not intended to be used by clients, and may change or be
     * removed in subsequent releases of the SDK.</b>
     */
    protected Collection<LightingItem> getDependentCollection() {
        // Default implementation is an empty list -- subclasses must override if they can be a component of another item
        return new ArrayList<LightingItem>();
    }

    /**
     * <b>WARNING: This method is not intended to be used by clients, and may change or be
     * removed in subsequent releases of the SDK.</b>
     */
    protected Collection<LightingItem> getComponentCollection() {
        // Default implementation is an empty list -- subclasses must override if they have other items as components
        return new ArrayList<LightingItem>();
    }

    /**
     * <b>WARNING: This method is not intended to be used by clients, and may change or be
     * removed in subsequent releases of the SDK.</b>
     */
    protected boolean postInvalidArgIfNull(String name, Object obj) {
        if (obj == null) {
            postError(new Throwable().getStackTrace()[1].getMethodName(), ResponseCode.ERR_INVALID_ARGS);
            return false;
        }

        return true;
    }

    /**
     * <b>WARNING: This method is not intended to be used by clients, and may change or be
     * removed in subsequent releases of the SDK.</b>
     */
    protected boolean postErrorIfFailure(String name, ControllerClientStatus status) {
        if (status != ControllerClientStatus.OK) {
            postError(new Throwable().getStackTrace()[1].getMethodName(), ResponseCode.ERR_FAILURE);
            return false;
        }

        return true;
    }

    /**
     * <b>WARNING: This method is not intended to be used by clients, and may change or be
     * removed in subsequent releases of the SDK.</b>
     */
    protected void postError(ResponseCode status) {
        postError(new Throwable().getStackTrace()[1].getMethodName(), status);
    }

    /**
     * Renames the current Lighting item using the provided name.
     *
     * @param name The new name for the Lighting item
     */
    public abstract void rename(String name);

    /**
     * <b>WARNING: This method is not intended to be used by clients, and may change or be
     * removed in subsequent releases of the SDK.</b>
     */
    protected abstract LightingItemDataModel getItemDataModel();

    /**
     * <b>WARNING: This method is not intended to be used by clients, and may change or be
     * removed in subsequent releases of the SDK.</b>
     */
    protected abstract void postError(String name, ResponseCode status);
}