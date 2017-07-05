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

#import "LSFSDKHelperSceneManagerCallback.h"
#import "../manager/LSFSDKLightingSystemManager.h"
#import "../manager/LSFSDKAllJoynManager.h"

@interface LSFSDKHelperSceneManagerCallback()

@property (nonatomic, strong) LSFSDKLightingSystemManager *manager;
@property (nonatomic, strong) NSMutableDictionary *creationTrackingIDs;

@end

@implementation LSFSDKHelperSceneManagerCallback

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
 * Implementation of LSFSceneManagerCallbackDelegate
 */
-(void)getAllSceneIDsReplyWithCode: (LSFResponseCode)rc andSceneIDs: (NSArray *)sceneIDs
{
    if (rc != LSF_OK)
    {
        [[self getSceneCollectionManager] sendErrorEvent: @"getAllSceneIDsReplyCB" statusCode: rc];
    }

    for (NSString *sceneID in sceneIDs)
    {
        [self postProcessSceneID: sceneID];
    }
}

-(void)getSceneNameReplyWithCode: (LSFResponseCode)rc sceneID: (NSString *)sceneID language: (NSString *)language andName: (NSString *)sceneName
{
    if (rc != LSF_OK)
    {
        [[self getSceneCollectionManager] sendErrorEvent: @"getSceneNameReplyCB" statusCode: rc itemID: sceneID];
    }

    [self postUpdateSceneNameForID: sceneID sceneName: sceneName];
}

-(void)setSceneNameReplyWithCode: (LSFResponseCode)rc sceneID: (NSString *)sceneID andLanguage: (NSString *)language
{
    if (rc != LSF_OK)
    {
        [[self getSceneCollectionManager] sendErrorEvent: @"setSceneNameReplyCB" statusCode: rc itemID: sceneID];
    }

    [[LSFSDKAllJoynManager getSceneManager] getSceneNameWithID: sceneID andLanguage: self.manager.defaultLanguage];
}

-(void)scenesNameChanged: (NSArray *)sceneIDs
{
    dispatch_async(self.manager.dispatchQueue, ^{
        BOOL containsNewIDs = NO;

        for (NSString *sceneID in sceneIDs)
        {
            if ([[self getSceneCollectionManager] hasID: sceneID])
            {
                [[LSFSDKAllJoynManager getSceneManager] getSceneNameWithID: sceneID andLanguage: self.manager.defaultLanguage];
            }
            else
            {
                containsNewIDs = YES;
            }
        }

        if (containsNewIDs)
        {
            [[LSFSDKAllJoynManager getSceneManager] getAllSceneIDs];
        }
    });
}

-(void)createSceneReplyWithCode: (LSFResponseCode)rc andSceneID: (NSString *)sceneID
{
    if (rc != LSF_OK)
    {
        [[self getSceneCollectionManager] sendErrorEvent: @"createSceneReplyCB" statusCode: rc itemID: sceneID];
    }
}

-(void)createSceneTrackingReplyWithCode: (LSFResponseCode)rc sceneID: (NSString *)sceneID andTrackingID: (unsigned int)trackingID
{
    if (rc != LSF_OK)
    {
        [[self getSceneCollectionManager] sendErrorEvent: @"createSceneTrackingReplyCB" statusCode: rc itemID: sceneID withTrackingID: [[LSFSDKTrackingID alloc] initWithValue:trackingID]];
    }
    else
    {
        LSFSDKTrackingID *myTrackingID = [[LSFSDKTrackingID alloc] initWithValue: trackingID];
        [self.creationTrackingIDs setValue: myTrackingID forKey: sceneID];
    }
}

-(void)createSceneWithSceneElementsReplyWithCode: (LSFResponseCode)rc sceneID: (NSString *)sceneID andTrackingID: (unsigned int)trackingID
{
    if (rc != LSF_OK)
    {
        [[self getSceneCollectionManager] sendErrorEvent: @"createSceneWithSceneElementsReplyCB" statusCode: rc itemID: sceneID withTrackingID: [[LSFSDKTrackingID alloc] initWithValue: trackingID]];
    }
    else
    {
        LSFSDKTrackingID *myTrackingID = [[LSFSDKTrackingID alloc] initWithValue: trackingID];
        [self.creationTrackingIDs setValue: myTrackingID forKey: sceneID];
    }
}

-(void)scenesCreated: (NSArray *)sceneIDs
{
    [[LSFSDKAllJoynManager getSceneManager] getAllSceneIDs];
}

-(void)updateSceneReplyWithCode: (LSFResponseCode)rc andSceneID: (NSString *)sceneID
{
    if (rc != LSF_OK)
    {
        [[self getSceneCollectionManager] sendErrorEvent: @"updateSceneReplyCB" statusCode: rc itemID: sceneID];
    }
}

-(void)updateSceneWithSceneElementsReplyWithCode: (LSFResponseCode)rc andSceneID: (NSString *)sceneID
{
    if (rc != LSF_OK)
    {
        [[self getSceneCollectionManager] sendErrorEvent: @"updateSceneWithSceneElementsReplyCB" statusCode: rc itemID: sceneID];
    }
}

-(void)scenesUpdated: (NSArray *)sceneIDs
{
    [self postUpdateScenes: sceneIDs];
}

-(void)deleteSceneReplyWithCode: (LSFResponseCode)rc andSceneID: (NSString *)sceneID
{
    if (rc != LSF_OK)
    {
        [[self getSceneCollectionManager] sendErrorEvent: @"deleteSceneReplyCB" statusCode: rc itemID: sceneID];
    }
}

-(void)scenesDeleted: (NSArray *)sceneIDs
{
    [self postDeleteScenes: sceneIDs];
}

-(void)getSceneReplyWithCode: (LSFResponseCode)rc sceneID: (NSString *)sceneID andScene: (LSFScene *)scene
{
    if (rc != LSF_OK)
    {
        [[self getSceneCollectionManager] sendErrorEvent: @"getSceneReplyCB" statusCode: rc itemID: sceneID];
    }

    [self postUpdateSceneWithID: sceneID sceneV1: scene];
}

-(void)getSceneWithSceneElementsReplyWithCode: (LSFResponseCode)rc sceneID: (NSString *)sceneID andSceneWithSceneElements: (LSFSceneWithSceneElements *)sceneWithSceneElements
{
    if (rc != LSF_OK)
    {
        [[self getSceneCollectionManager] sendErrorEvent: @"getSceneReplyCB" statusCode: rc itemID: sceneID];
    }

    [self postUpdateSceneWithID: sceneID sceneV2: sceneWithSceneElements];
}

-(void)applySceneReplyWithCode: (LSFResponseCode)rc andSceneID: (NSString *)sceneID
{
    if (rc != LSF_OK)
    {
        [[self getSceneCollectionManager] sendErrorEvent: @"applySceneReplyCB" statusCode: rc itemID: sceneID];
    }
}

-(void)scenesApplied: (NSArray *)sceneIDs
{
    // Currently nothing to do
}

-(void)postProcessSceneID: (NSString *)sceneID
{
    dispatch_async(self.manager.dispatchQueue, ^{
        BOOL hasID = [[self getSceneCollectionManager] hasID: sceneID];
        if (!hasID)
        {
            [self postUpdateSceneID: sceneID];

            if ([LSFSDKAllJoynManager isControllerServiceLeaderV1])
            {
                [[LSFSDKAllJoynManager getSceneManager] getSceneDataWithID: sceneID andLanguage: self.manager.defaultLanguage];
            }
            else
            {
                [[LSFSDKAllJoynManager getSceneManager] getSceneWithSceneElementsDataWithID: sceneID andLanguage: self.manager.defaultLanguage];
            }
        }
    });
}

-(void)postUpdateSceneID: (NSString *)sceneID
{
    dispatch_async(self.manager.dispatchQueue, ^{
        BOOL hasID = [[self getSceneCollectionManager] hasID: sceneID];
        if (!hasID)
        {
            [[self getSceneCollectionManager] addSceneWithID: sceneID];
        }
    });

    [self postSendSceneChanged: sceneID];
}

-(void)postUpdateSceneWithID: (NSString *)sceneID sceneV1: (LSFScene *)scene
{
    dispatch_async(self.manager.dispatchQueue, ^{
        LSFSceneDataModel *sceneModel = [self.manager.sceneCollectionManagerV1 getModelWithID: sceneID];

        if (sceneModel != nil)
        {
            BOOL wasInitialized = [sceneModel isInitialized];

            [sceneModel fromScene: scene];

            if (wasInitialized != [sceneModel isInitialized])
            {
                [self postSendSceneInitialized: sceneID];
            }
        }
    });

    [self postSendSceneChanged: sceneID];
}

-(void)postUpdateSceneWithID: (NSString *)sceneID sceneV2: (LSFSceneWithSceneElements *)scene
{
    dispatch_async(self.manager.dispatchQueue, ^{
        LSFSceneDataModelV2 *sceneModel = [self.manager.sceneCollectionManager getModelWithID: sceneID];

        if (sceneModel != nil)
        {
            BOOL wasInitialized = [sceneModel isInitialized];

            [sceneModel setSceneWithSceneElements: scene];

            if (wasInitialized != [sceneModel isInitialized])
            {
                [self postSendSceneInitialized: sceneID];
            }
        }
    });

    [self postSendSceneChanged: sceneID];
}

-(void)postUpdateSceneNameForID: (NSString *)sceneID sceneName: (NSString *)sceneName
{
    dispatch_async(self.manager.dispatchQueue, ^{
        LSFModel *model = [self getItemDataModelWithID: sceneID];

        if (model != nil)
        {
            BOOL wasInitialized = [model isInitialized];

            model.name = sceneName;

            if (wasInitialized != [model isInitialized])
            {
                [self postSendSceneInitialized: sceneID];
            }
        }
    });

    [self postSendSceneChanged: sceneID];
}

-(void)postUpdateScenes: (NSArray *)sceneIDs
{
    dispatch_async(self.manager.dispatchQueue, ^{
        for (NSString *sceneID in sceneIDs)
        {
            if ([LSFSDKAllJoynManager isControllerServiceLeaderV1])
            {
                [[LSFSDKAllJoynManager getSceneManager] getSceneWithID: sceneID];
            }
            else
            {
                [[LSFSDKAllJoynManager getSceneManager] getSceneWithSceneElementsWithID: sceneID];
            }
        }
    });
}

-(void)postDeleteScenes: (NSArray *)sceneIDs
{
    dispatch_async(self.manager.dispatchQueue, ^{
        for (NSString *sceneID in sceneIDs)
        {
            [[self getSceneCollectionManager] removeSceneWithID: sceneID];
        }
    });
}

-(void)postSendSceneChanged: (NSString *)sceneID
{
    dispatch_async(self.manager.dispatchQueue, ^{
        [[self getSceneCollectionManager] sendChangedEvent: sceneID];
    });
}

-(void)postSendSceneInitialized: (NSString *)sceneID
{
    dispatch_async(self.manager.dispatchQueue, ^{
        LSFSDKTrackingID *trackingID = [self.creationTrackingIDs valueForKey: sceneID];

        if (trackingID != nil)
        {
            [self.creationTrackingIDs removeObjectForKey: sceneID];
        }

        [[self getSceneCollectionManager] sendInitializedEvent: sceneID withTrackingID: trackingID];
    });
}

-(LSFSDKSceneCollectionManager *)getSceneCollectionManager
{
    return [LSFSDKAllJoynManager isControllerServiceLeaderV1] ? self.manager.sceneCollectionManagerV1 : self.manager.sceneCollectionManager;
}

-(LSFModel *)getItemDataModelWithID: (NSString *)itemID
{
    return [LSFSDKAllJoynManager isControllerServiceLeaderV1] ? [self.manager.sceneCollectionManagerV1 getModelWithID: itemID] : [self.manager.sceneCollectionManager getModelWithID: itemID];
}

@end