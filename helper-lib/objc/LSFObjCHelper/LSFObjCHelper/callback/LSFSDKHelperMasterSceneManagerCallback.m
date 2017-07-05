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

#import "LSFSDKHelperMasterSceneManagerCallback.h"
#import "LSFSDKLightingSystemManager.h"
#import "LSFSDKAllJoynManager.h"

@interface LSFSDKHelperMasterSceneManagerCallback()

@property (nonatomic, strong) LSFSDKLightingSystemManager *manager;
@property (nonatomic, strong) NSMutableDictionary *creationTrackingIDs;

-(void)postProcessMasterSceneID: (NSString *)masterSceneID;
-(void)postUpdateMasterSceneID: (NSString *)masterSceneID;
-(void)postUpdateMasterScene: (NSString *)masterSceneID masterScene: (LSFMasterScene *)masterScene;
-(void)postUpdateMasterSceneNameForID: (NSString *)masterSceneID masterSceneName: (NSString *)masterSceneName;
-(void)postDeleteMasterScenes: (NSArray *)masterSceneIDs;
-(void)postSendMasterSceneChanged: (NSString *)masterSceneID;
-(void)postSendMasterSceneInitialized: (NSString *)masterSceneID;

@end

@implementation LSFSDKHelperMasterSceneManagerCallback

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
 * Implementation of LSFMasterSceneManagerCallbackDelegate
 */
-(void)getAllMasterSceneIDsReplyWithCode: (LSFResponseCode)rc andMasterSceneIDs: (NSArray *)masterSceneIDs
{
    if (rc != LSF_OK)
    {
        [self.manager.masterSceneCollectionManager sendErrorEvent: @"getAllMasterSceneIDsReplyCB" statusCode: rc];
    }

    for (NSString *masterSceneID in masterSceneIDs)
    {
        [self postProcessMasterSceneID: masterSceneID];
    }
}

-(void)getMasterSceneNameReplyWithCode: (LSFResponseCode)rc masterSceneID: (NSString *)masterSceneID language: (NSString *)language andName: (NSString *)masterSceneName
{
    if (rc != LSF_OK)
    {
        [self.manager.masterSceneCollectionManager sendErrorEvent: @"getMasterSceneNameReplyCB" statusCode: rc itemID: masterSceneID];
    }

    [self postUpdateMasterSceneNameForID: masterSceneID masterSceneName: masterSceneName];
}

-(void)setMasterSceneNameReplyWithCode: (LSFResponseCode)rc masterSceneID: (NSString *)masterSceneID andLanguage: (NSString *)language
{
    if (rc != LSF_OK)
    {
        [self.manager.masterSceneCollectionManager sendErrorEvent: @"setMasterSceneNameReplyCB" statusCode: rc itemID: masterSceneID];
    }

    [[LSFSDKAllJoynManager getMasterSceneManager] getMasterSceneNameWithID: masterSceneID andLanguage: self.manager.defaultLanguage];
}

-(void)masterScenesNameChanged: (NSArray *)masterSceneIDs
{
    dispatch_async(self.manager.dispatchQueue, ^{
        BOOL containsNewIDs = NO;

        for (NSString *masterSceneID in masterSceneIDs)
        {
            if ([self.manager.masterSceneCollectionManager hasID: masterSceneID])
            {
                [[LSFSDKAllJoynManager getMasterSceneManager] getMasterSceneNameWithID: masterSceneID andLanguage: self.manager.defaultLanguage];
            }
            else
            {
                containsNewIDs = YES;
            }
        }

        if (containsNewIDs)
        {
            [[LSFSDKAllJoynManager getMasterSceneManager] getAllMasterSceneIDs];
        }
    });
}

-(void)createMasterSceneReplyWithCode: (LSFResponseCode)rc andMasterSceneID: (NSString *)masterSceneID
{
    if (rc != LSF_OK)
    {
        [self.manager.masterSceneCollectionManager sendErrorEvent: @"createMasterSceneReplyCB" statusCode: rc itemID: masterSceneID];
    }
}

-(void)createMasterSceneTrackingReplyWithCode: (LSFResponseCode)rc masterSceneID: (NSString *)masterSceneID andTrackingID: (unsigned int)trackingID
{
    if (rc != LSF_OK)
    {
        [self.manager.masterSceneCollectionManager sendErrorEvent: @"createMasterSceneTrackingReplyCB" statusCode: rc itemID: masterSceneID];
    }
    else
    {
        LSFSDKTrackingID *myTrackingID = [[LSFSDKTrackingID alloc] initWithValue: trackingID];
        [self.creationTrackingIDs setValue: myTrackingID forKey: masterSceneID];
    }
}

-(void)masterScenesCreated: (NSArray *)masterSceneIDs
{
    [[LSFSDKAllJoynManager getMasterSceneManager] getAllMasterSceneIDs];
}

-(void)getMasterSceneReplyWithCode: (LSFResponseCode)rc masterSceneID: (NSString *)masterSceneID andMasterScene: (LSFMasterScene *)masterScene
{
    if (rc != LSF_OK)
    {
        [self.manager.masterSceneCollectionManager sendErrorEvent: @"getMasterSceneReplyCB" statusCode: rc itemID: masterSceneID];
    }

    [self postUpdateMasterScene: masterSceneID masterScene: masterScene];
}

-(void)deleteMasterSceneReplyWithCode: (LSFResponseCode)rc andMasterSceneID: (NSString *)masterSceneID
{
    if (rc != LSF_OK)
    {
        [self.manager.masterSceneCollectionManager sendErrorEvent: @"deleteMasterSceneReplyCB" statusCode: rc itemID: masterSceneID];
    }
}

-(void)masterScenesDeleted: (NSArray *)masterSceneIDs
{
    [self postDeleteMasterScenes: masterSceneIDs];
}

-(void)updateMasterSceneReplyWithCode: (LSFResponseCode)rc andMasterSceneID: (NSString *)masterSceneID
{
    if (rc != LSF_OK)
    {
        [self.manager.masterSceneCollectionManager sendErrorEvent: @"updateMasterSceneReplyCB" statusCode: rc itemID: masterSceneID];
    }
}

-(void)masterScenesUpdated: (NSArray *)masterSceneIDs
{
    for (NSString *masterSceneID in masterSceneIDs)
    {
        [[LSFSDKAllJoynManager getMasterSceneManager] getMasterSceneWithID: masterSceneID];
    }
}

-(void)applyMasterSceneReplyWithCode: (LSFResponseCode)rc andMasterSceneID: (NSString *)masterSceneID
{
    if (rc != LSF_OK)
    {
        [self.manager.masterSceneCollectionManager sendErrorEvent: @"applyMasterSceneReplyCB" statusCode: rc itemID: masterSceneID];
    }
}

-(void)masterScenesApplied: (NSArray *)masterSceneIDs
{
    //Currently does nothing
}

/*
 * Private function implementations
 */
-(void)postProcessMasterSceneID: (NSString *)masterSceneID
{
    dispatch_async(self.manager.dispatchQueue, ^{
        BOOL hasID = [self.manager.masterSceneCollectionManager hasID: masterSceneID];
        if (!hasID)
        {
            [self postUpdateMasterSceneID: masterSceneID];
            [[LSFSDKAllJoynManager getMasterSceneManager] getMasterSceneDataWithID: masterSceneID andLanguage: self.manager.defaultLanguage];
        }
    });
}

-(void)postUpdateMasterSceneID: (NSString *)masterSceneID
{
    dispatch_async(self.manager.dispatchQueue, ^{
        BOOL hasID = [self.manager.masterSceneCollectionManager hasID: masterSceneID];
        if (!hasID)
        {
            [self.manager.masterSceneCollectionManager addMasterSceneWithID: masterSceneID];
        }
    });

    [self postSendMasterSceneChanged: masterSceneID];
}

-(void)postUpdateMasterScene: (NSString *)masterSceneID masterScene: (LSFMasterScene *)masterScene
{
    dispatch_async(self.manager.dispatchQueue, ^{
        LSFMasterSceneDataModel *masterSceneDataModel = [self.manager.masterSceneCollectionManager getModelWithID: masterSceneID];

        if (masterSceneDataModel != nil)
        {
            BOOL wasInitialized = [masterSceneDataModel isInitialized];
            masterSceneDataModel.masterScene = masterScene;

            if (wasInitialized != [masterSceneDataModel isInitialized])
            {
                [self postSendMasterSceneInitialized: masterSceneID];
            }
        }
    });

    [self postSendMasterSceneChanged: masterSceneID];
}

-(void)postUpdateMasterSceneNameForID: (NSString *)masterSceneID masterSceneName: (NSString *)masterSceneName
{
    dispatch_async(self.manager.dispatchQueue, ^{
        LSFMasterSceneDataModel *masterSceneDataModel = [self.manager.masterSceneCollectionManager getModelWithID: masterSceneID];

        if (masterSceneDataModel != nil)
        {
            BOOL wasInitialized = [masterSceneDataModel isInitialized];
            masterSceneDataModel.name = [NSString stringWithString: masterSceneName];

            if (wasInitialized != [masterSceneDataModel isInitialized])
            {
                [self postSendMasterSceneInitialized: masterSceneID];
            }
        }
    });

    [self postSendMasterSceneChanged: masterSceneID];
}

-(void)postDeleteMasterScenes: (NSArray *)masterSceneIDs
{
    dispatch_async(self.manager.dispatchQueue, ^{
        for (NSString *masterSceneID in masterSceneIDs)
        {
            [self.manager.masterSceneCollectionManager removeMasterSceneWithID: masterSceneID];
        }
    });
}

-(void)postSendMasterSceneChanged: (NSString *)masterSceneID
{
    dispatch_async(self.manager.dispatchQueue, ^{
        [self.manager.masterSceneCollectionManager sendChangedEvent: masterSceneID];
    });
}

-(void)postSendMasterSceneInitialized: (NSString *)masterSceneID
{
    dispatch_async(self.manager.dispatchQueue, ^{
        LSFSDKTrackingID *trackingID = [self.creationTrackingIDs valueForKey: masterSceneID];

        if (trackingID != nil)
        {
            [self.creationTrackingIDs removeObjectForKey: masterSceneID];
        }

        [self.manager.masterSceneCollectionManager sendInitializedEvent: masterSceneID withTrackingID: trackingID];
    });
}

@end