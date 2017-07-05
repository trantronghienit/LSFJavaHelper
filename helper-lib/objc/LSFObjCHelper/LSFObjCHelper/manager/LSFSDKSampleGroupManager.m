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

#import "LSFSDKSampleGroupManager.h"
#import "LSFSDKAllLampsLampGroup.h"
#import "LSFSDKAllLampsDataModel.h"
#import "LSFSDKAllJoynManager.h"

@interface LSFSDKSampleGroupManager()

@property (nonatomic, weak) LSFSDKHelperGroupManagerCallback *callback;

-(NSArray *)getLampIDs;

@end

@implementation LSFSDKSampleGroupManager

@synthesize callback = _callback;

-(id)initWithControllerClient: (LSFControllerClient *)controllerClient andLampManagerCallback: (LSFSDKHelperGroupManagerCallback *)gmc
{
    self = [super initWithControllerClient: controllerClient andLampManagerCallbackDelegate: gmc];

    if (self)
    {
        self.callback = gmc;
    }

    return self;
}

-(ControllerClientStatus)getLampGroupNameForID: (NSString *)groupID
{
    return [self getLampGroupNameForID: groupID andLanguage: @"en"];
}

-(ControllerClientStatus)getLampGroupNameForID: (NSString *)groupID andLanguage: (NSString *)language
{
    ControllerClientStatus status = CONTROLLER_CLIENT_OK;

    if ([groupID isEqualToString: [LSFSDKAllLampsDataModel getAllLampsGroupID]])
    {
        [self.callback getLampGroupNameReplyWithCode: LSF_OK groupID: [LSFSDKAllLampsDataModel getAllLampsGroupID] language: @"en" andGroupName: ALL_LAMPS_GROUP_NAME];
    }
    else
    {
        status = [super getLampGroupNameForID: groupID andLanguage: language];
    }

    return status;
}

-(ControllerClientStatus)getLampGroupWithID: (NSString *)groupID
{
    ControllerClientStatus status = CONTROLLER_CLIENT_OK;

    if ([groupID isEqualToString: [LSFSDKAllLampsDataModel getAllLampsGroupID]])
    {
        [self.callback getLampGroupReplyWithCode: LSF_OK groupID: [LSFSDKAllLampsDataModel getAllLampsGroupID] andLampGroup: [LSFSDKAllLampsLampGroup getInstance]];
    }
    else
    {
        status = [super getLampGroupWithID: groupID];
    }

    return status;
}

-(ControllerClientStatus)transitionLampGroupID: (NSString *)groupID toState: (LSFLampState *)state
{
    return [self transitionLampGroupID: groupID toState: state withTransitionPeriod: 0];
}

-(ControllerClientStatus)transitionLampGroupID: (NSString *)groupID toState: (LSFLampState *)state withTransitionPeriod: (unsigned int)transitionPeriod
{
    ControllerClientStatus status = CONTROLLER_CLIENT_OK;

    if ([self interceptAllLampsGroupStateChangeRequests] && [groupID isEqualToString: [LSFSDKAllLampsDataModel getAllLampsGroupID]])
    {
        for (NSString *lampID in [self getLampIDs])
        {
            status = [[LSFSDKAllJoynManager getLampManager] transitionLampID: lampID toLampState: state withTransitionPeriod: transitionPeriod];

            if (status != CONTROLLER_CLIENT_OK)
            {
                break;
            }
        }
    }
    else
    {
        status = [super transitionLampGroupID: groupID toState: state withTransitionPeriod: transitionPeriod];
    }

    return status;
}

-(ControllerClientStatus)pulseLampGroupID: (NSString *)groupID toLampState: (LSFLampState *)toState withPeriod: (unsigned int)period duration: (unsigned int)duration andNumPulses: (unsigned int)numPulses
{
    ControllerClientStatus status = CONTROLLER_CLIENT_OK;

    if ([self interceptAllLampsGroupStateChangeRequests] && [groupID isEqualToString: [LSFSDKAllLampsDataModel getAllLampsGroupID]])
    {
        for (NSString *lampID in [self getLampIDs])
        {
            status = [[LSFSDKAllJoynManager getLampManager] pulseLampID: lampID toLampState: toState withPeriod: period duration: duration andNumPulses: numPulses];

            if (status != CONTROLLER_CLIENT_OK)
            {
                break;
            }
        }
    }
    else
    {
        status = [super pulseLampGroupID: groupID toLampState: toState withPeriod: period duration: duration andNumPulses: numPulses];
    }

    return status;
}

-(ControllerClientStatus)pulseLampGroupID: (NSString *)groupID toLampState: (LSFLampState *)toState withPeriod: (unsigned int)period duration: (unsigned int)duration numPulses: (unsigned int)numPulses fromLampState: (LSFLampState *)fromState
{
    ControllerClientStatus status = CONTROLLER_CLIENT_OK;

    if ([self interceptAllLampsGroupStateChangeRequests] && [groupID isEqualToString: [LSFSDKAllLampsDataModel getAllLampsGroupID]])
    {
        for (NSString *lampID in [self getLampIDs])
        {
            status = [[LSFSDKAllJoynManager getLampManager] pulseLampID: lampID toLampState: toState withPeriod: period duration: duration numPulses: numPulses andFromLampState: fromState];

            if (status != CONTROLLER_CLIENT_OK)
            {
                break;
            }
        }
    }
    else
    {
        status = [super pulseLampGroupID: groupID toLampState: toState withPeriod: period duration: duration numPulses: numPulses fromLampState: fromState];
    }

    return status;
}

-(ControllerClientStatus)transitionLampGroupID: (NSString *)groupID onOffField: (BOOL)onOff
{
    ControllerClientStatus status = CONTROLLER_CLIENT_OK;

    if ([self interceptAllLampsGroupStateChangeRequests] && [groupID isEqualToString: [LSFSDKAllLampsDataModel getAllLampsGroupID]])
    {
        for (NSString *lampID in [self getLampIDs])
        {
            status = [[LSFSDKAllJoynManager getLampManager] transitionLampID: lampID onOffField: onOff];

            if (status != CONTROLLER_CLIENT_OK)
            {
                break;
            }
        }
    }
    else
    {
        status = [super transitionLampGroupID: groupID onOffField: onOff];
    }

    return status;
}

-(ControllerClientStatus)transitionLampGroupID: (NSString *)groupID hueField: (unsigned int)hue
{
    return [self transitionLampGroupID: groupID hueField: hue withTransitionPeriod: 0];
}

-(ControllerClientStatus)transitionLampGroupID: (NSString *)groupID hueField: (unsigned int)hue withTransitionPeriod: (unsigned int)transitionPeriod
{
    ControllerClientStatus status = CONTROLLER_CLIENT_OK;

    if ([self interceptAllLampsGroupStateChangeRequests] && [groupID isEqualToString: [LSFSDKAllLampsDataModel getAllLampsGroupID]])
    {
        for (NSString *lampID in [self getLampIDs])
        {
            status = [[LSFSDKAllJoynManager getLampManager] transitionLampID: lampID hueField: hue withTransitionPeriod: transitionPeriod];

            if (status != CONTROLLER_CLIENT_OK)
            {
                break;
            }
        }
    }
    else
    {
        status = [super transitionLampGroupID: groupID hueField: hue withTransitionPeriod: transitionPeriod];
    }

    return status;
}

-(ControllerClientStatus)transitionLampGroupID: (NSString *)groupID saturationField: (unsigned int)saturation
{
    return [self transitionLampGroupID: groupID saturationField: saturation withTransitionPeriod: 0];
}

-(ControllerClientStatus)transitionLampGroupID: (NSString *)groupID saturationField: (unsigned int)saturation withTransitionPeriod: (unsigned int)transitionPeriod
{
    ControllerClientStatus status = CONTROLLER_CLIENT_OK;

    if ([self interceptAllLampsGroupStateChangeRequests] && [groupID isEqualToString: [LSFSDKAllLampsDataModel getAllLampsGroupID]])
    {
        for (NSString *lampID in [self getLampIDs])
        {
            status = [[LSFSDKAllJoynManager getLampManager] transitionLampID: lampID saturationField: saturation withTransitionPeriod: transitionPeriod];

            if (status != CONTROLLER_CLIENT_OK)
            {
                break;
            }
        }
    }
    else
    {
        status = [super transitionLampGroupID: groupID saturationField: saturation withTransitionPeriod: transitionPeriod];
    }

    return status;
}

-(ControllerClientStatus)transitionLampGroupID: (NSString *)groupID brightnessField: (unsigned int)brightness
{
    return [self transitionLampGroupID: groupID brightnessField: brightness withTransitionPeriod: 0];
}

-(ControllerClientStatus)transitionLampGroupID: (NSString *)groupID brightnessField: (unsigned int)brightness withTransitionPeriod: (unsigned int)transitionPeriod
{
    ControllerClientStatus status = CONTROLLER_CLIENT_OK;

    if ([self interceptAllLampsGroupStateChangeRequests] && [groupID isEqualToString: [LSFSDKAllLampsDataModel getAllLampsGroupID]])
    {
        for (NSString *lampID in [self getLampIDs])
        {
            status = [[LSFSDKAllJoynManager getLampManager] transitionLampID: lampID brightnessField: brightness withTransitionPeriod: transitionPeriod];

            if (status != CONTROLLER_CLIENT_OK)
            {
                break;
            }
        }
    }
    else
    {
        status = [super transitionLampGroupID: groupID brightnessField: brightness withTransitionPeriod: transitionPeriod];
    }

    return status;
}

-(ControllerClientStatus)transitionLampGroupID: (NSString *)groupID colorTempField: (unsigned int)colorTemp
{
    return [self transitionLampGroupID: groupID colorTempField: colorTemp withTransitionPeriod: 0];
}

-(ControllerClientStatus)transitionLampGroupID: (NSString *)groupID colorTempField: (unsigned int)colorTemp withTransitionPeriod: (unsigned int)transitionPeriod
{
    ControllerClientStatus status = CONTROLLER_CLIENT_OK;

    if ([self interceptAllLampsGroupStateChangeRequests] && [groupID isEqualToString: [LSFSDKAllLampsDataModel getAllLampsGroupID]])
    {
        for (NSString *lampID in [self getLampIDs])
        {
            status = [[LSFSDKAllJoynManager getLampManager] transitionLampID: lampID colorTempField: colorTemp withTransitionPeriod: transitionPeriod];

            if (status != CONTROLLER_CLIENT_OK)
            {
                break;
            }
        }
    }
    else
    {
        status = [super transitionLampGroupID: groupID colorTempField: colorTemp withTransitionPeriod: transitionPeriod];
    }

    return status;
}

-(ControllerClientStatus)transitionLampGroupID: (NSString *)groupID toPreset: (NSString *)presetID
{
    return [self transitionLampGroupID: groupID toPreset: presetID withTransitionPeriod: 0];
}

-(ControllerClientStatus)transitionLampGroupID: (NSString *)groupID toPreset: (NSString *)presetID withTransitionPeriod: (unsigned int)transitionPeriod
{
    ControllerClientStatus status = CONTROLLER_CLIENT_OK;

    if ([self interceptAllLampsGroupStateChangeRequests] && [groupID isEqualToString: [LSFSDKAllLampsDataModel getAllLampsGroupID]])
    {
        for (NSString *lampID in [self getLampIDs])
        {
            status = [[LSFSDKAllJoynManager getLampManager] transitionLampID: lampID toPresetID: presetID withTransitionPeriod: transitionPeriod];

            if (status != CONTROLLER_CLIENT_OK)
            {
                break;
            }
        }
    }
    else
    {
        status = [super transitionLampGroupID: groupID toPreset: presetID withTransitionPeriod: transitionPeriod];
    }

    return status;
}

-(ControllerClientStatus)resetLampGroupStateForID: (NSString *)groupID
{
    ControllerClientStatus status = CONTROLLER_CLIENT_OK;

    if ([self interceptAllLampsGroupStateChangeRequests] && [groupID isEqualToString: [LSFSDKAllLampsDataModel getAllLampsGroupID]])
    {
        for (NSString *lampID in [self getLampIDs])
        {
            status = [[LSFSDKAllJoynManager getLampManager] resetLampStateForID: lampID];

            if (status != CONTROLLER_CLIENT_OK)
            {
                break;
            }
        }
    }
    else
    {
        status = [super resetLampGroupStateForID: groupID];
    }

    return status;
}

-(ControllerClientStatus)resetLampGroupStateOnOffFieldForID: (NSString *)groupID
{
    ControllerClientStatus status = CONTROLLER_CLIENT_OK;

    if ([self interceptAllLampsGroupStateChangeRequests] && [groupID isEqualToString: [LSFSDKAllLampsDataModel getAllLampsGroupID]])
    {
        for (NSString *lampID in [self getLampIDs])
        {
            status = [[LSFSDKAllJoynManager getLampManager] resetLampStateOnOffFieldForID: lampID];

            if (status != CONTROLLER_CLIENT_OK)
            {
                break;
            }
        }
    }
    else
    {
        status = [super resetLampGroupStateOnOffFieldForID: groupID];
    }

    return status;
}

-(ControllerClientStatus)resetLampGroupStateHueFieldForID: (NSString *)groupID
{
    ControllerClientStatus status = CONTROLLER_CLIENT_OK;

    if ([self interceptAllLampsGroupStateChangeRequests] && [groupID isEqualToString: [LSFSDKAllLampsDataModel getAllLampsGroupID]])
    {
        for (NSString *lampID in [self getLampIDs])
        {
            status = [[LSFSDKAllJoynManager getLampManager] resetLampStateHueFieldForID: lampID];

            if (status != CONTROLLER_CLIENT_OK)
            {
                break;
            }
        }
    }
    else
    {
        status = [super resetLampGroupStateHueFieldForID: groupID];
    }

    return status;
}

-(ControllerClientStatus)resetLampGroupStateSaturationFieldForID: (NSString *)groupID
{
    ControllerClientStatus status = CONTROLLER_CLIENT_OK;

    if ([self interceptAllLampsGroupStateChangeRequests] && [groupID isEqualToString: [LSFSDKAllLampsDataModel getAllLampsGroupID]])
    {
        for (NSString *lampID in [self getLampIDs])
        {
            status = [[LSFSDKAllJoynManager getLampManager] resetLampStateSaturationFieldForID: lampID];

            if (status != CONTROLLER_CLIENT_OK)
            {
                break;
            }
        }
    }
    else
    {
        status = [super resetLampGroupStateSaturationFieldForID: groupID];
    }

    return status;
}

-(ControllerClientStatus)resetLampGroupStateBrightnessFieldForID: (NSString *)groupID
{
    ControllerClientStatus status = CONTROLLER_CLIENT_OK;

    if ([self interceptAllLampsGroupStateChangeRequests] && [groupID isEqualToString: [LSFSDKAllLampsDataModel getAllLampsGroupID]])
    {
        for (NSString *lampID in [self getLampIDs])
        {
            status = [[LSFSDKAllJoynManager getLampManager] resetLampStateBrightnessFieldForID: lampID];

            if (status != CONTROLLER_CLIENT_OK)
            {
                break;
            }
        }
    }
    else
    {
        status = [super resetLampGroupStateBrightnessFieldForID: groupID];
    }

    return status;
}

-(ControllerClientStatus)resetLampGroupStateColorTempFieldForID: (NSString *)groupID
{
    ControllerClientStatus status = CONTROLLER_CLIENT_OK;

    if ([self interceptAllLampsGroupStateChangeRequests] && [groupID isEqualToString: [LSFSDKAllLampsDataModel getAllLampsGroupID]])
    {
        for (NSString *lampID in [self getLampIDs])
        {
            status = [[LSFSDKAllJoynManager getLampManager] resetLampStateColorTempFieldForID: lampID];

            if (status != CONTROLLER_CLIENT_OK)
            {
                break;
            }
        }
    }
    else
    {
        status = [super resetLampGroupStateColorTempFieldForID: groupID];
    }

    return status;
}

/*
 * Private Functions
 */
-(NSArray *)getLampIDs
{
    NSMutableArray *lampIDs = [[NSMutableArray alloc] init];
    NSArray *lamps = [self.callback.manager.lampCollectionManager getLamps];

    for (LSFSDKLamp *lamp in lamps)
    {
        [lampIDs addObject: [lamp theID]];
    }

    return lampIDs;
}

-(BOOL)interceptAllLampsGroupStateChangeRequests
{
    return [LSFSDKAllJoynManager isControllerServiceLeaderV1];
}

@end