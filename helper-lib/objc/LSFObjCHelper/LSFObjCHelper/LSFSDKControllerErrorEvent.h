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

#import <LSFSDKResponseCodes.h>

/**
 * This class contains all pertinent information for errors that occur in the Lighting
 * controller. This class provides an interface to retrieve the following data: the name of
 * the error, the response code returned by the Lighting controller operation, and an array
 * of error codes.
 */
@interface LSFSDKControllerErrorEvent : NSObject

/**
 * The name of the error.
 */
@property (nonatomic, strong) NSString *name;

/**
 * Array of error codes.
 */
@property (nonatomic, strong) NSArray *errorCodes;

/**
 * The response code of the error.
 */
@property (nonatomic) lsf::LSFResponseCode responseCode;

/** @name Initializing an LSFSDKControllerErrorEvent Object */

/**
 * Constructs a LSFSDKControllerErrorEvent object.
 *
 * @param name The name of the error.
 * @param code The response code of the error.
 */
-(id)initWithName: (NSString *)name andResponseCode: (lsf::LSFResponseCode)code;

/**
 * Constructs a LSFSDKControllerErrorEvent object.
 *
 * @param name The name of the error.
 * @param errorCodes Array of ErrorCodes.
 */
-(id)initWithName: (NSString *)name andErrorCodes: (NSArray *)errorCodes;

@end