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

#import "LSFSDKHelperPulseEffectManagerCallback.h"
#import "LSFSDKLightingSystemManager.h"
#import "LSFSDKAllJoynManager.h"
#import "LSFConstants.h"

@interface LSFSDKHelperPulseEffectManagerCallback()

@property (nonatomic, strong) LSFSDKLightingSystemManager *manager;
@property (nonatomic, strong) NSMutableDictionary *creationTrackingIDs;

-(void)postProcessPulseEffectID: (NSString *)pulseEffectID;
-(void)postUpdatePulseEffectID: (NSString *)pulseEffectID;
-(void)postUpdatePulseEffectNameForID: (NSString *)pulseEffectID pulseEffectName: (NSString *)pulseEffectName;
-(void)postUpdatePulseEffectID: (NSString *)pulseEffectID pulseEffect: (LSFPulseEffectV2 *)pulseEffect;
-(void)postDeletePulseEffectIDs: (NSArray *)pulseEffectIDs;
-(void)postSendPulseEffectChanged: (NSString *)pulseEffectID;
-(void)postSendPulseEffectInitialized: (NSString *)pulseEffectID;

@end

@implementation LSFSDKHelperPulseEffectManagerCallback

@synthesize manager = _manager;
@synthesize creationTrackingIDs = _creationTrackingIDs;

-(id)initWithLightingSystemManager: (LSFSDKLightingSystemManager *)manager
{
    self = [super init];

    if (self)
    {
        self.manager = manager;
        self.creationTrackingIDs = [[NSMutableDictionary alloc] init];
    }

    return self;
}

/*
 * Implementation of LSFPulseEffectManagerCallbackDelegate
 */
-(void)getPulseEffectReplyWithCode: (LSFResponseCode)rc pulseEffectID: (NSString *)pulseEffectID andPulseEffect: (LSFPulseEffectV2 *)pulseEffect
{
    if (rc != LSF_OK)
    {
        [self.manager.pulseEffectCollectionManager sendErrorEvent: @"getPulseEffectReplyCB" statusCode: rc itemID: pulseEffectID];
    }

    [self postUpdatePulseEffectID: pulseEffectID pulseEffect: pulseEffect];
}

-(void)applyPulseEffectOnLampsReplyWithCode: (LSFResponseCode)rc pulseEffectID: (NSString *)pulseEffectID andLampIDs: (NSArray *)lampIDs
{
    if (rc != LSF_OK)
    {
        [self.manager.pulseEffectCollectionManager sendErrorEvent: @"applyPulseEffectOnLampsReplyCB" statusCode: rc itemID: pulseEffectID];
    }
}

-(void)applyPulseEffectOnLampGroupsReplyWithCode: (LSFResponseCode)rc pulseEffectID: (NSString *)pulseEffectID andLampGroupIDs: (NSArray *)lampGroupIDs
{
    if (rc != LSF_OK)
    {
        [self.manager.pulseEffectCollectionManager sendErrorEvent: @"applyPulseEffectOnLampGroupsReplyCB" statusCode: rc itemID: pulseEffectID];
    }
}

-(void)getAllPulseEffectIDsReplyWithCode: (LSFResponseCode)rc pulseEffectIDs: (NSArray *)pulseEffectIDs
{
    if (rc != LSF_OK)
    {
        [self.manager.pulseEffectCollectionManager sendErrorEvent: @"getAllPulseEffectIDsReplyCB" statusCode: rc];
    }

    for (NSString *pulseEffectID in pulseEffectIDs)
    {
        [self postProcessPulseEffectID: pulseEffectID];
    }
}

-(void)getPulseEffectNameReplyWithCode: (LSFResponseCode)rc pulseEffectID: (NSString *)pulseEffectID language: (NSString *)language andPulseEffectName: (NSString *)pulseEffectName
{
    if (rc != LSF_OK)
    {
        [self.manager.pulseEffectCollectionManager sendErrorEvent: @"getPulseEffectNameReplyCB" statusCode: rc itemID: pulseEffectID];
    }

    [self postUpdatePulseEffectNameForID: pulseEffectID pulseEffectName: pulseEffectName];
}

-(void)setPulseEffectNameReplyWithCode: (LSFResponseCode)rc pulseEffectID: (NSString *)pulseEffectID andLanguage: (NSString *)language
{
    if (rc != LSF_OK)
    {
        [self.manager.pulseEffectCollectionManager sendErrorEvent: @"setPulseEffectNameReplyCB" statusCode: rc itemID: pulseEffectID];
    }

    [[LSFSDKAllJoynManager getPulseEffectManager] getPulseEffectNameWithID: pulseEffectID andLanguage: self.manager.defaultLanguage];
}

-(void)pulseEffectsNameChanged: (NSArray *)pulseEffectIDs
{
    dispatch_async(self.manager.dispatchQueue, ^{
        BOOL containsNewIDs = NO;

        for (NSString *pulseEffectID in pulseEffectIDs)
        {
            if ([self.manager.pulseEffectCollectionManager hasID: pulseEffectID])
            {
                [[LSFSDKAllJoynManager getPulseEffectManager] getPulseEffectNameWithID: pulseEffectID andLanguage: self.manager.defaultLanguage];
            }
            else
            {
                containsNewIDs = YES;
            }
        }

        if (containsNewIDs)
        {
            [[LSFSDKAllJoynManager getPulseEffectManager] getAllPulseEffectIDs];
        }
    });
}

-(void)createPulseEffectReplyWithCode: (LSFResponseCode)rc pulseEffectID: (NSString *)pulseEffectID andTrackingID: (unsigned int)trackingID
{
    if (rc != LSF_OK)
    {
        [self.manager.pulseEffectCollectionManager sendErrorEvent: @"createPulseEffectReplyCB" statusCode: rc itemID: pulseEffectID withTrackingID: [[LSFSDKTrackingID alloc] initWithValue:trackingID]];
    }
    else
    {
        LSFSDKTrackingID *myTrackingID = [[LSFSDKTrackingID alloc] initWithValue: trackingID];
        [self.creationTrackingIDs setValue: myTrackingID forKey: pulseEffectID];
    }
}

-(void)pulseEffectsCreated: (NSArray *)pulseEffectIDs
{
    [[LSFSDKAllJoynManager getPulseEffectManager] getAllPulseEffectIDs];
}

-(void)updatePulseEffectReplyWithCode: (LSFResponseCode)rc andPulseEffectID: (NSString *)pulseEffectID
{
    if (rc != LSF_OK)
    {
        [self.manager.pulseEffectCollectionManager sendErrorEvent: @"updatePulseEffectReplyCB" statusCode: rc itemID: pulseEffectID];
    }
}

-(void)pulseEffectsUpdated: (NSArray *)pulseEffectIDs
{
    for (NSString *pulseEffectID in pulseEffectIDs)
    {
        [[LSFSDKAllJoynManager getPulseEffectManager] getPulseEffectWithID: pulseEffectID];
    }
}

-(void)deletePulseEffectReplyWithCode: (LSFResponseCode)rc andPulseEffectID: (NSString *)pulseEffectID
{
    if (rc != LSF_OK)
    {
        [self.manager.pulseEffectCollectionManager sendErrorEvent: @"deletePulseEffectReplyCB" statusCode: rc itemID: pulseEffectID];
    }
}

-(void)pulseEffectsDeleted: (NSArray *)pulseEffectIDs
{
    [self postDeletePulseEffectIDs: pulseEffectIDs];
}

/*
 * Private function implementations
 */
-(void)postProcessPulseEffectID: (NSString *)pulseEffectID
{
    dispatch_async(self.manager.dispatchQueue, ^{
        BOOL hasID = [self.manager.pulseEffectCollectionManager hasID: pulseEffectID];
        if (!hasID)
        {
            [self postUpdatePulseEffectID: pulseEffectID];
            [[LSFSDKAllJoynManager getPulseEffectManager] getPulseEffectDataSetWithID: pulseEffectID andLanguage: self.manager.defaultLanguage];
        }
    });
}

-(void)postUpdatePulseEffectID: (NSString *)pulseEffectID
{
    dispatch_async(self.manager.dispatchQueue, ^{
        BOOL hasID = [self.manager.pulseEffectCollectionManager hasID: pulseEffectID];
        if (!hasID)
        {
            [self.manager.pulseEffectCollectionManager addPulseEffectWithID: pulseEffectID];
        }
    });

    [self postSendPulseEffectChanged: pulseEffectID];
}

-(void)postUpdatePulseEffectNameForID: (NSString *)pulseEffectID pulseEffectName: (NSString *)pulseEffectName
{
    dispatch_async(self.manager.dispatchQueue, ^{
        LSFPulseEffectDataModelV2 *pulseEffectDataModel = [self.manager.pulseEffectCollectionManager getModelWithID: pulseEffectID];

        if (pulseEffectDataModel != nil)
        {
            BOOL wasInitialized = [pulseEffectDataModel isInitialized];
            pulseEffectDataModel.name = [NSString stringWithString: pulseEffectName];

            if (wasInitialized != [pulseEffectDataModel isInitialized])
            {
                [self postSendPulseEffectInitialized: pulseEffectID];
            }
        }
    });

    [self postSendPulseEffectChanged: pulseEffectID];
}

-(void)postUpdatePulseEffectID: (NSString *)pulseEffectID pulseEffect: (LSFPulseEffectV2 *)pulseEffect
{
    dispatch_async(self.manager.dispatchQueue, ^{
        LSFConstants *constants = [LSFConstants getConstants];
        LSFPulseEffectDataModelV2 *pulseEffectDataModel = [self.manager.pulseEffectCollectionManager getModelWithID: pulseEffectID];

        if (pulseEffectDataModel != nil)
        {
            BOOL wasInitialized = [pulseEffectDataModel isInitialized];

            LSFLampState *newFromState = [[LSFLampState alloc] initWithOnOff: pulseEffect.fromState.onOff brightness: [constants unscaleLampStateValue: pulseEffect.fromState.brightness withMax: 100] hue: [constants unscaleLampStateValue: pulseEffect.fromState.hue withMax: 360] saturation: [constants unscaleLampStateValue: pulseEffect.fromState.saturation withMax: 100] colorTemp: [constants unscaleColorTemp: pulseEffect.fromState.colorTemp]];

            LSFLampState *newEndState = [[LSFLampState alloc] initWithOnOff: pulseEffect.toState.onOff brightness: [constants unscaleLampStateValue: pulseEffect.toState.brightness withMax: 100] hue: [constants unscaleLampStateValue: pulseEffect.toState.hue withMax: 360] saturation: [constants unscaleLampStateValue: pulseEffect.toState.saturation withMax: 100] colorTemp: [constants unscaleColorTemp: pulseEffect.toState.colorTemp]];

            [pulseEffectDataModel setState: newFromState];
            [pulseEffectDataModel setEndState: newEndState];
            [pulseEffectDataModel setStartPresetID: pulseEffect.fromPreset];
            [pulseEffectDataModel setEndPresetID: pulseEffect.toPreset];
            [pulseEffectDataModel setDuration: pulseEffect.pulseDuration];
            [pulseEffectDataModel setPeriod: pulseEffect.pulsePeriod];
            [pulseEffectDataModel setCount: pulseEffect.numPulses];

            if (wasInitialized != [pulseEffectDataModel isInitialized])
            {
                [self postSendPulseEffectInitialized: pulseEffectID];
            }
        }
    });

    [self postSendPulseEffectChanged: pulseEffectID];
}

-(void)postDeletePulseEffectIDs: (NSArray *)pulseEffectIDs
{
    dispatch_async(self.manager.dispatchQueue, ^{
        for (NSString *pulseEffectID in pulseEffectIDs)
        {
            [self.manager.pulseEffectCollectionManager removePulseEffectWithID: pulseEffectID];
        }
    });
}

-(void)postSendPulseEffectChanged: (NSString *)pulseEffectID
{
    dispatch_async(self.manager.dispatchQueue, ^{
        [self.manager.pulseEffectCollectionManager sendChangedEvent: pulseEffectID];
    });
}

-(void)postSendPulseEffectInitialized: (NSString *)pulseEffectID
{
    dispatch_async(self.manager.dispatchQueue, ^{
        LSFSDKTrackingID *trackingID = [self.creationTrackingIDs valueForKey: pulseEffectID];

        if (trackingID != nil)
        {
            [self.creationTrackingIDs removeObjectForKey: pulseEffectID];
        }

        [self.manager.pulseEffectCollectionManager sendInitializedEvent: pulseEffectID withTrackingID: trackingID];
    });
}

@end