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

#import "LSFConstants.h"
#import <internal/LSFLampGroup.h>
#import <SystemConfiguration/CaptiveNetwork.h>

@implementation LSFConstants

@synthesize lampDetailsAboutData = _lampDetailsAboutData;
@synthesize lampDetailsFields = _lampDetailsFields;
@synthesize aboutFields = _aboutFields;
@synthesize lampServiceObjectDescription = _lampServiceObjectDescription;
@synthesize lampStateInterfaceName = _lampStateInterfaceName;
@synthesize lampDetailsInterfaceName = _lampDetailsInterfaceName;
@synthesize lampParametersInterfaceName = _lampParametersInterfaceName;
@synthesize lampServiceInterfaceName = _lampServiceInterfaceName;
@synthesize controllerServiceObjectDescription = _controllerServiceObjectDescription;
@synthesize controllerServiceInterfaceName = _controllerServiceInterfaceName;
@synthesize controllerServiceLampInterfaceName = _controllerServiceLampInterfaceName;
@synthesize controllerServiceLampGroupInterfaceName = _controllerServiceLampGroupInterfaceName;
@synthesize controllerServicePresetInterfaceName = _controllerServicePresetInterfaceName;
@synthesize controllerServiceSceneInterfaceName = _controllerServiceSceneInterfaceName;
@synthesize controllerServiceMasterSceneInterfaceName = _controllerServiceMasterSceneInterfaceName;
@synthesize aboutInterfaceName = _aboutInterfaceName;
@synthesize configServiceInterfaceName = _configServiceInterfaceName;
@synthesize defaultLanguage = _defaultLanguage;
@synthesize supportedEffects = _supportedEffects;
@synthesize effectImages = _effectImages;
@synthesize pollingDelaySeconds = _pollingDelaySeconds;
@synthesize lampExperationMilliseconds = _lampExperationMilliseconds;
@synthesize RETRY_INTERVAL = _RETRY_INTERVAL;
@synthesize UI_DELAY = _UI_DELAY;
@synthesize SOURCE_CODE_TEXT = _SOURCE_CODE_TEXT;
@synthesize TEAM_TEXT = _TEAM_TEXT;
@synthesize NOTICE_TEXT = _NOTICE_TEXT;
@synthesize MIN_BRIGHTNESS = _MIN_BRIGHTNESS;
@synthesize MAX_BRIGHTNESS = _MAX_BRIGHTNESS;
@synthesize MIN_HUE = _MIN_HUE;
@synthesize MAX_HUE = _MAX_HUE;
@synthesize MIN_SATURATION = _MIN_SATURATION;
@synthesize MAX_SATURATION = _MAX_SATURATION;
@synthesize MIN_COLOR_TEMP = _MIN_COLOR_TEMP;
@synthesize MAX_COLOR_TEMP = _MAX_COLOR_TEMP;

+(LSFConstants *)getConstants
{
    static LSFConstants *constants;
    static dispatch_once_t onceToken;
    
    dispatch_once(&onceToken, ^{
        constants = [[self alloc] init];
    });
    
    return constants;
}

-(id)init
{
    self = [super init];
    
    if (self)
    {
        //NSLog(@"LSFConstants - init() function executing");
        
        self.lampDetailsFields = [[NSArray alloc] initWithObjects: @"Lamp Make", @"Lamp Model", @"Device Type", @"Lamp Type", @"Base Type", @"Lamp Beam Angle",
                                  @"Dimmable", @"Supports Color", @"Supports Color Temperature", @"Supports Effects", @"Min Voltage", @"Max Voltage", @"Wattage",
                                  @"Incandescent Equivalent", @"Max Lumens", @"Min Temperature", @"Max Temperature", @"Color Rendering Index", nil];
        
        self.aboutFields = [[NSArray alloc] initWithObjects: @"App ID", @"Default Language", @"Device Name", @"Device ID", @"App Name", @"Manufacturer", @"Model Number",
                            @"Supported Languages", @"Description", @"Date of Manufacture", @"Software Version", @"AJ Software Version", @"Hardware Version", @"Support URL", nil];
        
        NSArray *values = [[NSArray alloc] initWithObjects: self.aboutFields, self.lampDetailsFields, nil];
        NSArray *keys = [[NSArray alloc] initWithObjects: @"About", @"Details", nil];
        
        self.lampDetailsAboutData = [[NSDictionary alloc] initWithObjects: values forKeys: keys];

        self.lampServiceObjectDescription = @"/org/allseen/LSF/Lamp";
        self.lampServiceInterfaceName = @"org.allseen.LSF.LampService";
        self.lampStateInterfaceName = @"org.allseen.LSF.LampState";
        self.lampParametersInterfaceName = @"org.allseen.LSF.LampParameters";
        self.lampDetailsInterfaceName = @"org.allseen.LSF.LampDetails";

        self.controllerServiceObjectDescription = @"/org/allseen/LSF/ControllerService";
        self.controllerServiceInterfaceName = @"org.allseen.LSF.ControllerService";
        self.controllerServiceLampInterfaceName = @"org.allseen.LSF.ControllerService.Lamp";
        self.controllerServiceLampGroupInterfaceName = @"org.allseen.LSF.ControllerService.LampGroup";
        self.controllerServicePresetInterfaceName = @"org.allseen.LSF.ControllerService.Preset";
        self.controllerServiceSceneInterfaceName = @"org.allseen.LSF.ControllerService.Scene";
        self.controllerServiceMasterSceneInterfaceName = @"org.allseen.LSF.ControllerService.MasterScene";

        self.aboutInterfaceName = @"org.alljoyn.About";
        self.configServiceInterfaceName = @"org.alljoyn.Config";

        self.defaultLanguage = @"en";
        
        self.supportedEffects = [NSArray arrayWithObjects: @"No Effect", @"Transition", @"Pulse", nil];
        self.effectImages = [NSArray arrayWithObjects: @"list_constant_icon.png", @"list_transition_icon.png", @"list_pulse_icon.png", nil];

        self.pollingDelaySeconds = 2;
        self.lampExperationMilliseconds = 5000;

        self.RETRY_INTERVAL = 0.2;

        self.uniformPowerGreen = [[UIColor alloc] initWithRed: (CGFloat)(205.0 / 255.0) green: (CGFloat)(245.0 / 255) blue: (CGFloat)(78.0 / 255.0) alpha: 1.0];
        self.midstatePowerOrange = [[UIColor alloc] initWithRed: (CGFloat)(249.0 / 255.0) green: (CGFloat)(233.0 / 255) blue: (CGFloat)(55.0 / 255.0) alpha: 1.0];

        self.UI_DELAY = 250;

        self.SOURCE_CODE_TEXT = [[NSMutableAttributedString alloc] initWithString: @"AllSeen Alliance Working Group"];
        [self.SOURCE_CODE_TEXT addAttribute: NSLinkAttributeName value: @"https://wiki.allseenalliance.org/tsc/connected_lighting" range: NSMakeRange(0, self.SOURCE_CODE_TEXT.length)];
        [self.SOURCE_CODE_TEXT addAttribute: NSFontAttributeName value: [UIFont fontWithName: @"Helvetica Neue" size: 18.0f] range: NSMakeRange(0, self.SOURCE_CODE_TEXT.length)];

        self.TEAM_TEXT = [[NSMutableAttributedString alloc] initWithString: @"AllSeen Alliance Working Group"];
        [self.TEAM_TEXT addAttribute: NSLinkAttributeName value: @"https://wiki.allseenalliance.org/tsc/connected_lighting" range: NSMakeRange(0, self.TEAM_TEXT.length)];
        [self.TEAM_TEXT addAttribute: NSFontAttributeName value: [UIFont fontWithName: @"Helvetica Neue" size: 18.0f] range: NSMakeRange(0, self.TEAM_TEXT.length)];

        self.NOTICE_TEXT = [[NSMutableAttributedString alloc] initWithString: @"Copyright (c) Open Connectivity Foundation (OCF), AllJoyn Open Source Project (AJOSP) Contributors and others.  SPDX-License-Identifier: Apache-2.0  All rights reserved. This program and the accompanying materials are made available under the terms of the Apache License, Version 2.0 which accompanies this distribution, and is available at http://www.apache.org/licenses/LICENSE-2.0  Copyright (c) Open Connectivity Foundation and Contributors to AllSeen Alliance. All rights reserved.  Permission to use, copy, modify, and/or distribute this software for any purpose with or without fee is hereby granted, provided that the above copyright notice and this permission notice appear in all copies.  THE SOFTWARE IS PROVIDED "AS IS" AND THE AUTHOR DISCLAIMS ALL WARRANTIES WITH REGARD TO THIS SOFTWARE INCLUDING ALL IMPLIED WARRANTIES OF MERCHANTABILITY AND FITNESS. IN NO EVENT SHALL THE AUTHOR BE LIABLE FOR ANY SPECIAL, DIRECT, INDIRECT, OR CONSEQUENTIAL DAMAGES OR ANY DAMAGES WHATSOEVER RESULTING FROM LOSS OF USE, DATA OR PROFITS, WHETHER IN AN ACTION OF CONTRACT, NEGLIGENCE OR OTHER TORTIOUS ACTION, ARISING OUT OF OR IN CONNECTION WITH THE USE OR PERFORMANCE OF THIS SOFTWARE."];
        [self.NOTICE_TEXT addAttribute: NSFontAttributeName value: [UIFont fontWithName: @"Helvetica Neue" size: 18.0f] range: NSMakeRange(0, self.NOTICE_TEXT.length)];

        self.MIN_BRIGHTNESS = 0.0;
        self.MAX_BRIGHTNESS = 100.0;
        self.MIN_HUE = 0.0;
        self.MAX_HUE = 360.0;
        self.MIN_SATURATION = 0.0;
        self.MAX_SATURATION = 100.0;
        self.MIN_COLOR_TEMP = 1000.0;
        self.MAX_COLOR_TEMP = 20000.0;
    }
    
    return self;
}

-(unsigned int)scaleLampStateValue: (unsigned int)value withMax: (unsigned int)max
{
    return round((double)value * 4294967295.0 / (double)max);
}

-(unsigned int)unscaleLampStateValue: (unsigned int)value withMax: (unsigned int)max
{
    return round((double)value * (double)max / 4294967295.0);
}

-(unsigned int)scaleColorTemp: (unsigned int)value
{
    unsigned int scaledColorTemp = round(4294967295.0 * (((double)value - self.MIN_COLOR_TEMP) / (self.MAX_COLOR_TEMP - self.MIN_COLOR_TEMP)));
    return scaledColorTemp;
}

-(unsigned int)unscaleColorTemp: (unsigned int)value
{
    unsigned int unscaledColorTemp = round(self.MIN_COLOR_TEMP * (1 - ((double)value / 4294967295.0)) + (self.MAX_COLOR_TEMP * ((double)value / 4294967295.0)));
    return unscaledColorTemp;
}

-(NSString *)currentWifiSSID
{
    NSString *ssid = nil;
    NSArray *ifs = (__bridge_transfer id)CNCopySupportedInterfaces();
    
    for (NSString *ifnam in ifs)
    {
        NSDictionary *info = (__bridge_transfer id)CNCopyCurrentNetworkInfo((__bridge CFStringRef)ifnam);
        if (info[@"SSID"])
        {
            ssid = info[@"SSID"];
            //NSLog(@"SSID = %@", ssid);
        }
    }
    
    return ssid;
}

@end