/******************************************************************************
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
******************************************************************************/

#import "LSFSDKLightingItem.h"
#import "model/LSFControllerModel.h"

/**
 * This class provides an interface to retrieve information about a given controller
 * in the Lighting system.
 *
 * @warning Client software should not instantiate the LSFSDKController directly, but should
 * instead get it from the LSFSDKLightingDirector using the [LSFSDKLightingDirector leadController]
 * method.
 */
@interface LSFSDKController : LSFSDKLightingItem
{
    @protected LSFControllerModel *controllerModel;
}

/** @name Class Properties */

/**
 * Returns a boolean that indicates if it is the lead controller in the Lighting system.
 *
 * @return Return true if this is the lead controller, false otherwise
 */
@property (nonatomic, readonly) BOOL connected;

/**
 * Returns the version of the controller.
 *
 * @return Version of the controller.
 */
@property (nonatomic, readonly) unsigned int version;

/** @name Protected methods */

/**
 * @warning This method is not intended to be used by clients, and may change or be
 * removed in subsequent releases of the SDK.
 */
-(LSFControllerModel *) getControllerDataModel;

@end