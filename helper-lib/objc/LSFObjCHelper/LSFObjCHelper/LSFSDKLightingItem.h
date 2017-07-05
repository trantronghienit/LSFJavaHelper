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

#import <Foundation/Foundation.h>
#import "LSFSDKLightingItemProtocol.h"
#import "LSFSDKControllerClientDefs.h"
#import "model/LSFModel.h"

using namespace lsf;

/**
 * Base class for items in a Lighting system that implements the LSFSDKLightingItemProtocol.
 *
 * @warning Client software should not instantiate the LSFSDKLightingItem directly.
 */
@interface LSFSDKLightingItem : NSObject <LSFSDKLightingItemProtocol>

/**
 * Renames the current Lighting item using the provided name.
 *
 * @param name The new name for the Lighting item
 */
-(void)rename: (NSString *)name;

/**
 * Returns a boolean indicating whether or not the Lighting item has received all
 * its data from the lighting controller.
 *
 * @return Returns true if the Lighting item is initialized, false otherwise
 */
-(BOOL)isInitialized;

/**
 * Returns a boolean that indicates whether this Lighting item is comprised of the
 * provided object.
 *
 * @param item  Potential child Lighting item
 *
 * @return Returns true if this Lighting item is comprised of the provided object, false otherwise
 */
-(BOOL)hasComponent: (LSFSDKLightingItem *) item;

/** @name Protected Methods */

/**
 * @warning This method is not intended to be used by clients, and may change or be
 * removed in subsequent releases of the SDK.
 */
-(NSArray *)getDependentCollection;

/**
 * @warning This method is not intended to be used by clients, and may change or be
 * removed in subsequent releases of the SDK.
 */
-(NSArray *)getComponentCollection;

/**
 * @warning This method is not intended to be used by clients, and may change or be
 * removed in subsequent releases of the SDK.
 *
 * @param name  The name of the Lighting item
 * @param object  The Lighting item
 */
-(BOOL)postInvalidArgIfNull: (NSString *)name object: (id)object;

/**
 * @warning This method is not intended to be used by clients, and may change or be
 * removed in subsequent releases of the SDK.
 *
 * @param name  The name of the Lighting item
 * @param status  The status code retured
 */
-(BOOL)postErrorIfFailure: (NSString *)name status: (ControllerClientStatus)status;

/**
 * @warning This method is not intended to be used by clients, and may change or be
 * removed in subsequent releases of the SDK.
 *
 * @param name  The name of the Lighting item
 * @param status  The status code retured
 */
-(void)postError: (NSString *)name status: (LSFResponseCode)status;

/**
 * @warning This method is not intended to be used by clients, and may change or be
 * removed in subsequent releases of the SDK.
 */
-(LSFModel *)getItemDataModel;

@end