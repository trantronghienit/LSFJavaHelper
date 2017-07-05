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

#import "LSFSDKLightingController.h"

@implementation LSFSDKLightingController

+(LSFSDKLightingController *)getLightingController
{
    static LSFSDKLightingController *instance = nil;
    static dispatch_once_t onceToken;

    dispatch_once(&onceToken, ^{
        instance = [[self alloc] init];
    });

    return instance;
}

-(id)init
{
    self = [super init];

    if (self)
    {
        controllerRunning = NO;
        controllerService = nil;
    }

    return self;
}

-(LightingControllerStatus)initializeWithControllerConfiguration: (id<LSFSDKLightingControllerConfiguration>)configuration
{
    if (controllerRunning || configuration == nil)
    {
        return ERROR_INIT;
    }

    controllerService = [[LSFSDKBasicControllerService alloc] initWithControllerConfiguration: configuration];
    return OK;
}

-(LightingControllerStatus)start
{
    if (controllerService != nil && controllerService.controllerConfiguration == nil)
    {
        return ERROR_INIT;
    }
    else if (controllerRunning)
    {
        return ERROR_ALREADY_RUNNING;
    }

    controllerRunning = YES;

    dispatch_async(dispatch_get_global_queue(DISPATCH_QUEUE_PRIORITY_HIGH, 0), ^{
        [controllerService startControllerWithKeyStoreFilePath: [controllerService.controllerConfiguration getKeystorePath]];
    });

    return OK;
}

-(LightingControllerStatus)stop
{
    controllerRunning = NO;

    dispatch_async(dispatch_get_global_queue(DISPATCH_QUEUE_PRIORITY_HIGH, 0), ^{
        [controllerService stopController];
    });

    return OK;
}

-(LightingControllerStatus)factoryReset
{
    [self stop];
    [controllerService factoryResetController];
    return OK;
}

-(LightingControllerStatus)lightingReset
{
    [self stop];
    [controllerService lightingResetController];
    return OK;
}

-(BOOL)isRunning
{
    return controllerRunning;
}

-(BOOL)isLeader
{
    return [controllerService isLeader];
}

-(NSString *)name
{
    return [controllerService name];
}

-(void)sendNetworkConnected
{
    if (controllerRunning)
    {
        [controllerService sendNetworkConnected];
    }
}

-(void)sendNetworkDisconnected
{
    if (controllerRunning)
    {
        [controllerService sendNetworkDisconnected];
    }
}

@end