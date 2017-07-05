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

#import "LSFLampAbout.h"

@implementation LSFLampAbout

@synthesize appID = _appID;
@synthesize defaultLanguage = _defaultLanguage;
@synthesize deviceName = _deviceName;
@synthesize deviceID = _deviceID;
@synthesize appName = _appName;
@synthesize manufacturer = _manufacturer;
@synthesize modelNumber = _modelNumber;
@synthesize supportedLanguages = _supportedLanguages;
@synthesize description = _description;
@synthesize dateOfManufacture = _dateOfManufacture;
@synthesize softwareVersion = _softwareVersion;
@synthesize ajSoftwareVersion = _ajSoftwareVersion;
@synthesize hardwareVersion = _hardwareVersion;
@synthesize supportURL = _supportURL;

-(id)init
{
    self = [super init];
    
    if (self)
    {
        self.appID = @"-";
        self.defaultLanguage = @"-";
        self.deviceName = @"-";
        self.deviceID = @"-";
        self.appName = @"-";
        self.manufacturer = @"-";
        self.modelNumber = @"-";
        self.supportedLanguages = @"-";
        self.description = @"-";
        self.dateOfManufacture = @"-";
        self.softwareVersion = @"-";
        self.ajSoftwareVersion = @"-";
        self.hardwareVersion = @"-";
        self.supportURL = @"-";
    }
    
    return self;
}

-(id)initWithAboutData: (LSFLampAbout *)aboutData
{
    self = [super init];

    if (self)
    {
        self.appID = aboutData.appID;
        self.defaultLanguage = aboutData.defaultLanguage;
        self.deviceName = aboutData.deviceName;
        self.deviceID = aboutData.deviceID;
        self.appName = aboutData.appName;
        self.manufacturer = aboutData.manufacturer;
        self.modelNumber = aboutData.modelNumber;
        self.supportedLanguages = aboutData.supportedLanguages;
        self.description = aboutData.description;
        self.dateOfManufacture = aboutData.dateOfManufacture;
        self.softwareVersion = aboutData.softwareVersion;
        self.ajSoftwareVersion = aboutData.ajSoftwareVersion;
        self.hardwareVersion = aboutData.hardwareVersion;
        self.supportURL = aboutData.supportURL;
    }

    return self;
}

@end