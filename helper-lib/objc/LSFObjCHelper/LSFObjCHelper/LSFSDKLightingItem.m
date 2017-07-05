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

@implementation LSFSDKLightingItem

@synthesize name = _name;

-(NSString *)theID
{
    return [[self getItemDataModel] theID];
}

-(NSString *)name
{
    return [[self getItemDataModel] name];
}

-(NSArray *)dependents
{
    return [[NSArray alloc] initWithArray: [self getDependentCollection]];
}

-(NSArray *)components
{
    return [[NSArray alloc] initWithArray: [self getComponentCollection]];
}

-(BOOL)isInitialized
{
    return [[self getItemDataModel] isInitialized];
}

-(BOOL)hasComponent: (LSFSDKLightingItem *) item
{
    return [[self getComponentCollection] containsObject: item];
}

-(NSArray *)getDependentCollection
{
    // Default implementation is an empty list -- subclass must override if they can be a component of another item
    return [[NSArray alloc] init];
}

-(NSArray *)getComponentCollection
{
    // Default implementation is an empty list -- sublass must override if they can be a component of another item
    return [[NSArray alloc] init];
}

-(BOOL)postInvalidArgIfNull: (NSString *)name object: (id)object
{
    if (object == nil)
    {
        [self postError: name status: LSF_ERR_INVALID_ARGS];
        return NO;
    }

    return YES;
}

-(BOOL)postErrorIfFailure: (NSString *)name status: (ControllerClientStatus)status
{
    if (status != CONTROLLER_CLIENT_OK)
    {
        [self postError: name status: LSF_ERR_FAILURE];
        return NO;
    }

    return YES;
}

-(void)rename: (NSString *)name
{
    @throw [NSException exceptionWithName: NSInternalInconsistencyException reason: [NSString stringWithFormat:@"You must override %@ in a subclass", NSStringFromSelector(_cmd)] userInfo: nil];
}

-(LSFModel *)getItemDataModel
{
    @throw [NSException exceptionWithName: NSInternalInconsistencyException reason: [NSString stringWithFormat:@"You must override %@ in a subclass", NSStringFromSelector(_cmd)] userInfo: nil];
}

-(void)postError: (NSString *)name status: (LSFResponseCode)status
{
    @throw [NSException exceptionWithName: NSInternalInconsistencyException reason: [NSString stringWithFormat:@"You must override %@ in a subclass", NSStringFromSelector(_cmd)] userInfo: nil];
}

@end