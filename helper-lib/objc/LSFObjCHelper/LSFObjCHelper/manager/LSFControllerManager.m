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

#import "LSFControllerManager.h"
#import "LSFSDKControllerDelegate.h"

@interface LSFControllerManager()

@property (nonatomic, strong) LSFSDKController *leader;

@end

@implementation LSFControllerManager

-(id)init
{
    self = [super init];

    if (self)
    {
        self.leader = [[LSFSDKController alloc] init];
    }

    return self;
}

-(LSFSDKController *)getLeader
{
    return self.leader;
}

-(void)sendLeaderStateChangedEvent
{
    [self processPendingAdditions];
    [self processPendingRemovals];

    for (id delegate in delegates)
    {
        if ([delegate conformsToProtocol: @protocol(LSFSDKControllerDelegate)])
        {
            id<LSFSDKControllerDelegate> controllerDelegate = (id<LSFSDKControllerDelegate>)delegate;
            [controllerDelegate onLeaderChange: [self getLeader]];
        }
        else
        {
            NSLog(@"LSFControllerManager - sendLeaderStateChangedEvent() delegate does not conform to \"LSFControllerListener\" protocol.");
        }
    }
}

-(void)sendErrorEventWithName: (NSString *)name andResonseCode: (LSFResponseCode)responseCode
{
    [self processPendingAdditions];
    [self processPendingRemovals];

    [self sendErrorEvent: [[LSFSDKControllerErrorEvent alloc] initWithName: name andResponseCode: responseCode]];
}

-(void)sendErrorEventWithName: (NSString *)name andErrorCodes: (NSArray *)errorCodes
{
    LSFSDKControllerErrorEvent *errorEvent = [[LSFSDKControllerErrorEvent alloc] initWithName: name andErrorCodes: errorCodes];
    [self sendErrorEvent: errorEvent];
}

-(void)sendErrorEvent: (LSFSDKControllerErrorEvent *)errorEvent
{
    for (id delegate in delegates)
    {
        if ([delegate conformsToProtocol: @protocol(LSFSDKControllerDelegate)])
        {
            id<LSFSDKControllerDelegate> controllerDelegate = (id<LSFSDKControllerDelegate>)delegate;
            [controllerDelegate onControllerError: errorEvent];
        }
        else
        {
            NSLog(@"LSFControllerManager - sendErrorEvent() delegate does not conform to \"LSFControllerListener\" protocol.");
        }
    }
}

@end