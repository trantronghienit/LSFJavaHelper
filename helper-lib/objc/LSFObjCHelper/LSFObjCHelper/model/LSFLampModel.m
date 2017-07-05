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

#import "LSFLampModel.h"

NSString *LAMP_DEFAULT_NAME = @"<Loading lamp info...>";

@interface LSFLampModel()

@property (nonatomic) BOOL detailsInitialized;

@end

@implementation LSFLampModel

@synthesize lampDetails = _lampDetails;
@synthesize lampParameters = _lampParameters;
@synthesize aboutData = _aboutData;
@synthesize detailsInitialized = _detailsInitialized;

-(id)initWithLampID: (NSString *)lampID
{
    return [self initWithLampID: lampID andLampName: @"<Loading lamp info...>"];
}

-(id)initWithLampID: (NSString *)lampID andLampName: (NSString *)name
{
    self = [super initWithID: lampID andName: name];

    if (self)
    {
        self.lampDetails = [[LSFSDKLampDetails alloc] init];
        self.lampParameters = [[LSFSDKLampParameters alloc] init];
        self.aboutData = [[LSFLampAbout alloc] init];
        self.detailsInitialized = NO;
    }

    return self;
}

-(void)setLampDetails: (LSFSDKLampDetails *)lampDetails
{
    _lampDetails = lampDetails;
    self.capability = [[LSFCapabilityData alloc] initWithDimmable: self.lampDetails.dimmable color: self.lampDetails.color andTemp: self.lampDetails.variableColorTemp];
    self.detailsInitialized = YES;
}

-(BOOL)isInitialized
{
    return ([super isInitialized] && self.detailsInitialized);
}

@end