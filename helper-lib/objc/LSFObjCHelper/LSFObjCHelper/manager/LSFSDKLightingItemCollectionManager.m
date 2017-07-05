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

#import "LSFSDKLightingItemCollectionManager.h"
#import "LSFSDKLightingSystemManager.h"

@implementation LSFSDKLightingItemCollectionManager

-(id)initWithLightingSystemManager: (LSFSDKLightingSystemManager *)lightingSystemManager;
{
    self = [super initWithLightingSystemManager: lightingSystemManager];

    if (self)
    {
        itemAdapters = [[NSMutableDictionary alloc] init];
    }

    return self;
}

-(BOOL)hasID: (NSString *)itemID
{
    return ([itemAdapters objectForKey: itemID] != nil);
}

-(NSArray *)getIDArray
{
    return [itemAdapters allKeys];
}

-(unsigned int)size
{
    return (unsigned int)[itemAdapters count];
}

-(id)getAdapterForID: (NSString *)itemID
{
    return [itemAdapters valueForKey: itemID];
}

-(NSArray *)removeAllAdapters
{
    NSMutableArray *list = [[NSMutableArray alloc] initWithCapacity: [self size]];
    NSMutableArray *keysToDelete = [[NSMutableArray alloc] initWithCapacity: [itemAdapters count]];
    NSEnumerator *valueEnumerator = [itemAdapters objectEnumerator];

    id value;
    while ((value = [valueEnumerator nextObject]))
    {
        [list addObject: value];
        [self sendRemovedEvent: value];
        [keysToDelete addObjectsFromArray: [itemAdapters allKeysForObject: value]];
    }

    [itemAdapters removeObjectsForKeys: keysToDelete];

    return list;
}

-(id)removeAdapterWithID: (NSString *)itemID
{
    id item = [itemAdapters valueForKey: itemID];
    [itemAdapters removeObjectForKey: itemID];
    [self sendRemovedEvent: item];
    return item;
}

-(NSArray *)getAdapters
{
    return [itemAdapters allValues];
}

-(NSArray *)getAdaptersWithFilter: (id<LSFSDKLightingItemFilter>)filter
{
    if (filter != nil)
    {
        NSMutableArray *filteredArray = [[NSMutableArray alloc] init];

        for (id item in [itemAdapters allValues])
        {
            if ([filter passes: item])
            {
                [filteredArray addObject: item];
            }
        }

        return [NSArray arrayWithArray: filteredArray];
    }
    else
    {
        return [self getAdapters];
    }
}

-(void)sendInitializedEvent: (NSString *)itemID
{
    [self sendInitializedEvent: itemID withTrackingID: nil];
}

-(void)sendInitializedEvent: (NSString *)itemID withTrackingID: (LSFSDKTrackingID *)trackingID
{
    [self processPendingAdditions];
    [self processPendingRemovals];

    for (id<LSFSDKLightingDelegate> delegate in delegates)
    {
        [self sendInitializedEvent: delegate item: [self getAdapterForID: itemID] trackingID: trackingID];
    }
}

-(void)sendChangedEvent: (NSString *)itemID
{
    [self processPendingAdditions];
    [self processPendingRemovals];

    for (id<LSFSDKLightingDelegate> delegate in delegates)
    {
        [self sendChangedEvent: delegate item: [self getAdapterForID: itemID]];
    }
}

-(void)sendRemovedEvent: (id)item
{
    [self processPendingAdditions];
    [self processPendingRemovals];

    for (id<LSFSDKLightingDelegate> delegate in delegates)
    {
        [self sendRemovedEvent: delegate item: item];
    }
}

-(void)sendErrorEvent: (NSString *)errorName statusCode: (lsf::LSFResponseCode)responseCode
{
    [self sendErrorEvent: errorName statusCode: responseCode itemID: nil];
}

-(void)sendErrorEvent: (NSString *)errorName statusCode: (lsf::LSFResponseCode)responseCode itemID: (NSString *)itemID
{
    [self sendErrorEvent: [[LSFSDKLightingItemErrorEvent alloc] initWithName: errorName responseCode: responseCode itemID: itemID andTrackingID: nil]];
}

-(void)sendErrorEvent: (NSString *)errorName statusCode: (lsf::LSFResponseCode)responseCode itemID: (NSString *)itemID withTrackingID: (LSFSDKTrackingID *)trackingID
{
    [self sendErrorEvent: [[LSFSDKLightingItemErrorEvent alloc] initWithName: errorName responseCode: responseCode itemID: itemID andTrackingID: trackingID]];
}

-(void)sendErrorEvent: (LSFSDKLightingItemErrorEvent *)errorEvent
{
    [self processPendingAdditions];
    [self processPendingRemovals];

    for (id<LSFSDKLightingDelegate> delegate in delegates)
    {
        [self sendErrorEvent: delegate errorEvent: errorEvent];
    }
}

-(void)postSendErrorEvent: (id<LSFSDKLightingDelegate>)delegate errorEvent: (LSFSDKLightingItemErrorEvent *)errorEvent
{
    dispatch_async(manager.dispatchQueue, ^{
        [self sendErrorEvent: delegate errorEvent: errorEvent];
    });
}

-(void)sendInitializedEvent: (id<LSFSDKLightingDelegate>)delegate item: (id)item trackingID: (LSFSDKTrackingID *)trackingID
{
    @throw [NSException exceptionWithName: NSInternalInconsistencyException reason: [NSString stringWithFormat:@"You must override %@ in a subclass", NSStringFromSelector(_cmd)] userInfo: nil];
}

-(void)sendChangedEvent: (id<LSFSDKLightingDelegate>)delegate item: (id)item
{
    @throw [NSException exceptionWithName: NSInternalInconsistencyException reason: [NSString stringWithFormat:@"You must override %@ in a subclass", NSStringFromSelector(_cmd)] userInfo: nil];
}

-(void)sendRemovedEvent: (id<LSFSDKLightingDelegate>)delegate item: (id)item
{
    @throw [NSException exceptionWithName: NSInternalInconsistencyException reason: [NSString stringWithFormat:@"You must override %@ in a subclass", NSStringFromSelector(_cmd)] userInfo: nil];
}

-(void)sendErrorEvent: (id<LSFSDKLightingDelegate>)delegate errorEvent: (LSFSDKLightingItemErrorEvent *)errorEvent
{
    @throw [NSException exceptionWithName: NSInternalInconsistencyException reason: [NSString stringWithFormat:@"You must override %@ in a subclass", NSStringFromSelector(_cmd)] userInfo: nil];
}

@end