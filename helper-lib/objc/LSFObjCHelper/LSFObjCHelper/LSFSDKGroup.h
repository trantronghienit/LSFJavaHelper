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

#import <Foundation/Foundation.h>
#import "LSFSDKGroupMember.h"
#import "LSFSDKPreset.h"
#import "LSFSDKEffect.h"
#import "LSFSDKLamp.h"
#import "model/LSFGroupModel.h"

/**
 * This class represents a Group definition in the lighting controller. This class
 * provides an interface to perform Group operations. Supported operations include changing
 * color and power state, renaming, applying effects and presets, adding and removing group
 * members, and deleting the group. Groups are considered fully initialized when the name
 * and the groups members have been received. Groups are still operational even in the
 * uninitialized state.
 *
 * @warning This class is not meant to be instantiated directly. Groups should be retrieved
 * from the LSFSDKLightingDirector using the [LSFSDKLightingDirector groups] method.
 *
 * @warning This class does not support Group creation. See [LSFSDKLightingDirector createGroupWithMembers:groupName:]
 * to create a group on the lighting controller.
 */
@interface LSFSDKGroup : LSFSDKGroupMember <LSFSDKDeletableItem>
{
    @protected LSFGroupModel *groupModel;
}

/** @name Class Properties */

/**
 * Returns the absolute minimum color temperature value supported by the Lamps in the current LSFSDKGroup.
 *
 * @return The Lamps minimum color temperature value
 */
@property (nonatomic, readonly) int colorTempMin;

/**
 * Returns the absolute maximum color temperature value supported by the Lamps in the current LSFSDKGroup.
 *
 * @return The Lamps maximum color temperature value
 */
@property (nonatomic, readonly) int colorTempMax;

/**
 * Tests to see if the current LSFSDKGroup is the AllLampsGroup.
 *
 * @return Returns true if the current LSFSDKGroup is the AllLampsGroup, false otherwise
 */
@property (nonatomic, readonly) BOOL isAllLampsGroup;

/** @name Initializing an LSFSDKGroup Object */

/**
 * @warning This method is not intended to be used by clients, and may change or be
 * removed in subsequent releases of the SDK.
 */
-(instancetype)init NS_UNAVAILABLE;

/** @name Group Operations */

/**
 * Adds a member to the current LSFSDKGroup.
 *
 * @param member The LSFSDKGroupMember object to be added to the LSFSDKGroup
 */
-(void)add: (LSFSDKGroupMember *)member;

/**
 * Removes a member from the current LSFSDKGroup.
 *
 * @param member The LSFSDKGroupMember object to be removed from the LSFSDKGroup
 */
-(void)remove: (LSFSDKGroupMember *)member;

/**
 * Replaces the current LSFSDKGroup memebers with the provided GroupMember array.
 *
 * @param members The array of LSFSDKGroupMembers
 */
-(void)modify: (NSArray *)members;

/**
 * Permanently deletes the current LSFSDKGroup from the lighting controller.
 *
 * @warning You cannot delete a group that is a member of another group. The dependency must
 * be deleted first.
 */
-(void)deleteItem;

/** @name Finding Objects in a Group */

/**
 * Tests to see if the current LSFSDKGroup contains the specified Lamp.
 *
 * @param lamp The LSFSDKLamp to be confirmed present in the LSFSDKGroup
 *
 * @return Returns true if the LSFSDKGroup contains the specified Lamp, false otherwise
 */
-(BOOL)hasLamp: (LSFSDKLamp *)lamp;

/**
 * Tests to see if the current LSFSDKGroup contains the specified LSFSDKGroup.
 *
 * @param group The LSFSDKGroup to be confirmed present in the LSFSDKGroup
 *
 * @return Returns true if the LSFSDKGroup contains the specified LSFSDKGroup, false otherwise
 */
-(BOOL)hasGroup: (LSFSDKGroup *)group;

/**
 * Tests to see if the current LSFSDKGroup contains the specified lamp ID.
 *
 * @param lampID The lamp ID to be confirmed present in the LSFSDKGroup.
 *
 * @return Returns true if the LSFSDKGroup contains the specified lamp ID, false otherwise
 */
-(BOOL)hasLampWithID: (NSString *)lampID;

/**
 * Tests to see if the current LSFSDKGroup contains the specified group ID.
 *
 * @param groupID The group ID to be confirmed present in the LSFSDKGroup.
 *
 * @return Returns true if the LSFSDKGroup contains the specified group ID, false otherwise
 */
-(BOOL)hasGroupWithID: (NSString *)groupID;

/** @name Querying a Group */

/**
 * Returns an array of LSFSDKLamps that are members of the current LSFSDKGroup.
 *
 * @return Array of all LSFSDKLamps in the current LSFSDKGroup
 */
-(NSArray *)getLamps;

/**
 * Returns an array of LSFSDKGroups that are members of the current LSFSDKGroup.
 *
 * @return Array of all LSFSDKGroups in the current LSFSDKGroup
 */
-(NSArray *)getGroups;

/**
 * Returns an array of lamp IDs that are members of the current LSFSDKGroup.
 *
 * @return Array of all lamp IDs in the current LSFSDKGroup
 */
-(NSSet *)getLampIDs;

/**
 * Returns an array of group IDs that are members of the current LSFSDKGroup.
 *
 * @return Array of all group IDs in the current LSFSDKGroup
 */
-(NSSet *)getGroupIDs;

/** @name Protected methods */

/**
 * @warning This method is not intended to be used by clients, and may change or be
 * removed in subsequent releases of the SDK.
 */
-(LSFGroupModel *)getLampGroupDataModel;

@end