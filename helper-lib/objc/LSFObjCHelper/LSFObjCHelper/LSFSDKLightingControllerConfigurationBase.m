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

#import "LSFSDKLightingControllerConfigurationBase.h"
#import <alljoyn/AboutKeys.h>

@implementation LSFSDKLightingControllerConfigurationBase

-(id)initWithKeystorePath: (NSString *)keystorePath
{
    self = [super init];

    if (self)
    {
        keystoreFilePath = keystorePath;
    }

    return self;
}

-(NSString *)getKeystorePath
{
    return keystoreFilePath;
}

-(NSString *)getMacAddress: (NSString *)generatedMacAddress
{
    return generatedMacAddress;
}

-(BOOL)isNetworkConnected
{
    return YES;
}

-(OEM_CS_RankParam_Mobility)getRankMobility
{
    return HIGH_MOBILITY;
}

-(OEM_CS_RankParam_Power)getRankPower
{
    return BATTERY_POWERED_CHARGABLE;
}

-(OEM_CS_RankParam_Availability)getRankAvailability
{
    return ZERO_TO_THREE_HOURS;
}

-(OEM_CS_RankParam_NodeType)getRankNodeType
{
    return WIRELESS;
}

-(void)populateDefaultProperties:(LSFSDKAboutData *)aboutData
{
    [aboutData putKey: [NSString stringWithUTF8String: AboutKeys::DATE_OF_MANUFACTURE] withStringValue: @"10/1/2199"];
    [aboutData putKey: [NSString stringWithUTF8String: AboutKeys::DEFAULT_LANGUAGE] withStringValue: @"en"];
    [aboutData putKey: [NSString stringWithUTF8String: AboutKeys::HARDWARE_VERSION] withStringValue: @"355.499. b"];
    [aboutData putKey: [NSString stringWithUTF8String: AboutKeys::MODEL_NUMBER] withStringValue: @"100"];
    [aboutData putKey: [NSString stringWithUTF8String: AboutKeys::SOFTWARE_VERSION] withStringValue: @"1"];
    [aboutData putKey: [NSString stringWithUTF8String: AboutKeys::SUPPORT_URL] withStringValue: @"http://www.company_a.com"];
    [aboutData putKey: [NSString stringWithUTF8String: AboutKeys::SUPPORTED_LANGUAGES] withStringArrayValue: [NSArray arrayWithObjects: @"en", @"de-AT", nil]];

    NSString *appName = @"LightingControllerService";
    NSString *description = @"Controller Service";
    NSString *appIDHex = [self generateRandomHexStringWithLength: 32];
    NSData *appID = [self hexStringToBytes: appIDHex];
    NSString *deviceName = [[NSString alloc] initWithFormat: @"%@%@", @"LightingC-", [appIDHex substringFromIndex: appIDHex.length - 5]];

    [aboutData putKey: [NSString stringWithUTF8String: AboutKeys::APP_NAME] withStringValue: appName andLanguage: @"en"];
    [aboutData putKey: [NSString stringWithUTF8String: AboutKeys::APP_NAME] withStringValue: appName andLanguage: @"de-AT"];
    [aboutData putKey: [NSString stringWithUTF8String: AboutKeys::DESCRIPTION] withStringValue: description andLanguage: @"en"];
    [aboutData putKey: [NSString stringWithUTF8String: AboutKeys::DESCRIPTION] withStringValue: description andLanguage: @"de-AT"];
    [aboutData putKey: [NSString stringWithUTF8String: AboutKeys::DEVICE_NAME] withStringValue: deviceName andLanguage: @"en"];
    [aboutData putKey: [NSString stringWithUTF8String: AboutKeys::DEVICE_NAME] withStringValue: deviceName andLanguage: @"de-AT"];
    [aboutData putKey: [NSString stringWithUTF8String: AboutKeys::MANUFACTURER] withStringValue: @"Company A (EN)" andLanguage: @"en"];
    [aboutData putKey: [NSString stringWithUTF8String: AboutKeys::MANUFACTURER] withStringValue: @"Firma A (DE-AT)" andLanguage: @"de-AT"];
    [aboutData putKey: [NSString stringWithUTF8String: AboutKeys::APP_ID] witDataValue: appID];
}

-(NSString *) generateRandomHexStringWithLength: (int) len
{
    NSString *digits = @"0123456789abcdef";
    NSMutableString *randomString = [NSMutableString stringWithCapacity: len];

    for (int i = 0; i < len; i++)
    {
        unichar c = [digits characterAtIndex: (arc4random() % [digits length])];
        [randomString appendFormat: @"%c", c];
    }

    return randomString;
}

-(char)hexToChar:(char)c
{
    if ('0' <= c && c <= '9') {
        return c - '0';
    } else if ('a' <= c && c <= 'f') {
        return c + 10 - 'a';
    } else if ('A' <= c && c <= 'F') {
        return c + 10 - 'A';
    }

    return -1;
}

-(NSData *)hexStringToBytes: (NSString *)hex
{
    uint8_t bytes[16];

    for (int i = 0; i < 16; i++)
    {
        char a = [self hexToChar: [hex characterAtIndex: i * 2]];
        char b = [self hexToChar: [hex characterAtIndex: i * 2 + 1]];
        bytes[i] = ((a << 4) | b);
    }

    return [[NSData alloc] initWithBytes: bytes length: 16];
}

@end