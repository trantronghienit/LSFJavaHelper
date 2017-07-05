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

#import "LSFSDKHelperSceneElementManagerCallback.h"
#import "LSFSDKLightingSystemManager.h"
#import "LSFSDKAllJoynManager.h"

@interface LSFSDKHelperSceneElementManagerCallback()

@property (nonatomic, strong) LSFSDKLightingSystemManager *manager;
@property (nonatomic, strong) NSMutableDictionary *creationTrackingIDs;

-(void)postProcessSceneElementID: (NSString *)sceneElementID;
-(void)postUpdateSceneElementID: (NSString *)sceneElementID;
-(void)postUpdateSceneElementNameForID: (NSString *)sceneElementID sceneElementName: (NSString *)sceneElementName;
-(void)postUpdateSceneElementID: (NSString *)sceneElementID sceneElement: (LSFSceneElement *)sceneElement;
-(void)postDeleteSceneElementIDs: (NSArray *)sceneElementIDs;
-(void)postSendSceneElementChanged: (NSString *)sceneElementID;
-(void)postSendSceneElementInitialized: (NSString *)sceneElementID;

@end

@implementation LSFSDKHelperSceneElementManagerCallback

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
 * Implementation of LSFSceneElementManagerCallbackDelegate
 */
-(void)getAllSceneElementIDsReplyWithCode: (LSFResponseCode)rc andSceneElementIDs: (NSArray *)sceneElementIDs
{
    if (rc != LSF_OK)
    {
        [self.manager.sceneElementCollectionManager sendErrorEvent: @"getAllSceneElementIDsReplyCB" statusCode: rc];
    }

    for (NSString *sceneElementID in sceneElementIDs)
    {
        [self postProcessSceneElementID: sceneElementID];
    }
}

-(void)getSceneElementNameReplyWithCode: (LSFResponseCode)rc sceneElementID: (NSString *)sceneElementID language: (NSString *)language andSceneElementName: (NSString *)sceneElementName
{
    if (rc != LSF_OK)
    {
        [self.manager.sceneElementCollectionManager sendErrorEvent: @"getSceneElementNameReplyCB" statusCode: rc itemID: sceneElementID];
    }

    [self postUpdateSceneElementNameForID: sceneElementID sceneElementName: sceneElementName];
}

-(void)setSceneElementNameReplyWithCode: (LSFResponseCode)rc sceneElementID: (NSString *)sceneElementID andLanguage: (NSString *)language
{
    if (rc != LSF_OK)
    {
        [self.manager.sceneElementCollectionManager sendErrorEvent: @"setSceneElementNameReplyCB" statusCode: rc itemID: sceneElementID];
    }

    [[LSFSDKAllJoynManager getSceneElementManager] getSceneElementNameWithID: sceneElementID andLanguage: self.manager.defaultLanguage];
}

-(void)sceneElementsNameChanged: (NSArray *)sceneElementIDs
{
    dispatch_async(self.manager.dispatchQueue, ^{
        BOOL containsNewIDs = NO;

        for (NSString *sceneElementID in sceneElementIDs)
        {
            if ([self.manager.sceneElementCollectionManager hasID: sceneElementID])
            {
                [[LSFSDKAllJoynManager getSceneElementManager] getSceneElementNameWithID: sceneElementID andLanguage: self.manager.defaultLanguage];
            }
            else
            {
                containsNewIDs = YES;
            }
        }

        if (containsNewIDs)
        {
            [[LSFSDKAllJoynManager getSceneElementManager] getAllSceneElementIDs];
        }
    });
}

-(void)createSceneElementReplyWithCode: (LSFResponseCode)rc sceneElementID: (NSString *)sceneElementID andTrackingID: (unsigned int)trackingID
{
    if (rc != LSF_OK)
    {
        NSLog(@"LSFSDKHelperSceneElementManagerCallback - createSceneElement() error %@", [NSString stringWithUTF8String: LSFResponseCodeText(rc)]);
        [self.manager.sceneElementCollectionManager sendErrorEvent: @"createSceneElementReplyCB" statusCode: rc itemID: sceneElementID withTrackingID: [[LSFSDKTrackingID alloc] initWithValue:trackingID]];
    }
    else
    {
        LSFSDKTrackingID *myTrackingID = [[LSFSDKTrackingID alloc] initWithValue: trackingID];
        [self.creationTrackingIDs setValue: myTrackingID forKey: sceneElementID];
    }
}

-(void)sceneElementsCreated: (NSArray *)sceneElementIDs
{
    [[LSFSDKAllJoynManager getSceneElementManager] getAllSceneElementIDs];
}

-(void)updateSceneElementReplyWithCode: (LSFResponseCode)rc andSceneElementID: (NSString *)sceneElementID
{
    if (rc != LSF_OK)
    {
        [self.manager.sceneElementCollectionManager sendErrorEvent: @"updateTransitionEffectReplyCB" statusCode: rc itemID: sceneElementID];
    }
}

-(void)sceneElementsUpdated: (NSArray *)sceneElementIDs
{
    for (NSString *sceneElementID in sceneElementIDs)
    {
        [[LSFSDKAllJoynManager getSceneElementManager] getSceneElementWithID: sceneElementID];
    }
}

-(void)deleteSceneElementReplyWithCode: (LSFResponseCode)rc andSceneElementID: (NSString *)sceneElementID
{
    if (rc != LSF_OK)
    {
        [self.manager.sceneElementCollectionManager sendErrorEvent: @"deleteSceneElementReplyCB" statusCode: rc itemID: sceneElementID];
    }
}

-(void)sceneElementsDeleted: (NSArray *)sceneElementIDs
{
    [self postDeleteSceneElementIDs: sceneElementIDs];
}

-(void)getSceneElementReplyWithCode: (LSFResponseCode)rc sceneElementID: (NSString *)sceneElementID andSceneElement: (LSFSceneElement *)sceneElement
{
    if (rc != LSF_OK)
    {
        [self.manager.sceneElementCollectionManager sendErrorEvent: @"getSceneElementReplyCB" statusCode: rc itemID: sceneElementID];
    }

    [self postUpdateSceneElementID: sceneElementID sceneElement: sceneElement];
}

-(void)applySceneElementReplyWithCode: (LSFResponseCode)rc andSceneElementID: (NSString *)sceneElementID
{
    if (rc != LSF_OK)
    {
        [self.manager.sceneElementCollectionManager sendErrorEvent: @"applySceneElementReplyCB" statusCode: rc itemID: sceneElementID];
    }
}

-(void)sceneElementsApplied: (NSArray *)sceneElementIDs
{
    //Currently does nothing
}

/*
 * Private function implementations
 */
-(void)postProcessSceneElementID: (NSString *)sceneElementID
{
    dispatch_async(self.manager.dispatchQueue, ^{
        BOOL hasID = [self.manager.sceneElementCollectionManager hasID: sceneElementID];
        if (!hasID)
        {
            [self postUpdateSceneElementID: sceneElementID];
            [[LSFSDKAllJoynManager getSceneElementManager] getSceneElementDataWithID: sceneElementID andLanguage: self.manager.defaultLanguage];
        }
    });
}

-(void)postUpdateSceneElementID: (NSString *)sceneElementID
{
    dispatch_async(self.manager.dispatchQueue, ^{
        BOOL hasID = [self.manager.sceneElementCollectionManager hasID: sceneElementID];
        if (!hasID)
        {
            [self.manager.sceneElementCollectionManager addSceneElementWithID: sceneElementID];
        }
    });

    [self postSendSceneElementChanged: sceneElementID];
}

-(void)postUpdateSceneElementNameForID: (NSString *)sceneElementID sceneElementName: (NSString *)sceneElementName
{
    dispatch_async(self.manager.dispatchQueue, ^{
        LSFSceneElementDataModelV2 *sceneElementDataModel = [self.manager.sceneElementCollectionManager getModelWithID: sceneElementID];

        if (sceneElementDataModel != nil)
        {
            BOOL wasInitialized = [sceneElementDataModel isInitialized];
            sceneElementDataModel.name = [NSString stringWithString: sceneElementName];

            if (wasInitialized != [sceneElementDataModel isInitialized])
            {
                [self postSendSceneElementInitialized: sceneElementID];
            }
        }
    });

    [self postSendSceneElementChanged: sceneElementID];
}

-(void)postUpdateSceneElementID: (NSString *)sceneElementID sceneElement: (LSFSceneElement *)sceneElement
{
    dispatch_async(self.manager.dispatchQueue, ^{
        LSFSceneElementDataModelV2 *sceneElementDataModel = [self.manager.sceneElementCollectionManager getModelWithID: sceneElementID];

        if (sceneElementDataModel != nil)
        {
            BOOL wasInitialized = [sceneElementDataModel isInitialized];

            [sceneElementDataModel setEffectID: sceneElement.effectID];
            [sceneElementDataModel setLamps: [[NSSet alloc] initWithArray: sceneElement.lamps]];
            [sceneElementDataModel setGroups: [[NSSet alloc] initWithArray: sceneElement.lampGroups]];


            if (wasInitialized != [sceneElementDataModel isInitialized])
            {
                [self postSendSceneElementInitialized: sceneElementID];
            }
        }
    });

    [self postSendSceneElementChanged: sceneElementID];
}

-(void)postDeleteSceneElementIDs: (NSArray *)sceneElementIDs
{
    dispatch_async(self.manager.dispatchQueue, ^{
        for (NSString *sceneElementID in sceneElementIDs)
        {
            [self.manager.sceneElementCollectionManager removeSceneElementWithID: sceneElementID];
        }
    });
}

-(void)postSendSceneElementChanged: (NSString *)sceneElementID
{
    dispatch_async(self.manager.dispatchQueue, ^{
        [self.manager.sceneElementCollectionManager sendChangedEvent: sceneElementID];
    });
}

-(void)postSendSceneElementInitialized: (NSString *)sceneElementID
{
    dispatch_async(self.manager.dispatchQueue, ^{
        LSFSDKTrackingID *trackingID = [self.creationTrackingIDs valueForKey: sceneElementID];

        if (trackingID != nil)
        {
            [self.creationTrackingIDs removeObjectForKey: sceneElementID];
        }

        [self.manager.sceneElementCollectionManager sendInitializedEvent: sceneElementID withTrackingID: trackingID];
    });
}

@end