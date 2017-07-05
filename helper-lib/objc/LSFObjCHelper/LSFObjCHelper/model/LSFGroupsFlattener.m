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

#import "LSFGroupsFlattener.h"
#import "LSFSDKGroup.h"

@interface LSFGroupsFlattener()

@property (nonatomic, strong) NSMutableSet *lampIDSet;
@property (nonatomic, strong) NSMutableSet *groupIDSet;
@property (nonatomic) int duplicates;

-(void)walkGroups: (NSMutableDictionary *)groups groupDataModel: (LSFGroupModel *)parentModel;
-(void)walkLamps: (NSMutableDictionary *)groups;

@end

@implementation LSFGroupsFlattener

@synthesize groupIDSet = _groupIDSet;
@synthesize lampIDSet = _lampIDSet;
@synthesize duplicates = _duplicates;

-(id)init
{
    self = [super init];
    
    if (self)
    {
        //Do Nothing
    }
    
    return self;
}

-(void)flattenGroups: (NSMutableDictionary *)groups
{
    for (LSFSDKGroup *group in [groups allValues])
    {
        [self flattenGroups: groups withGroup: group];
    }
}

-(void)flattenGroups: (NSMutableDictionary *)groups withGroup: (LSFSDKGroup *)group;
{
    LSFGroupModel *groupModel = [group getLampGroupDataModel];

    self.groupIDSet = [[NSMutableSet alloc] init];
    self.lampIDSet = [[NSMutableSet alloc] init];
    self.duplicates = 0;

    [self walkGroups: groups groupDataModel: groupModel];
    [self walkLamps: groups];

//    NSLog(@"LSFGroupsFlattener - Printing groups and lamps sets");
//
//    for (NSString *groupID in self.groupIDSet)
//    {
//        NSLog(@"%@", groupID);
//    }
//
//    for (NSString *lampID in self.lampIDSet)
//    {
//        NSLog(@"%@", lampID);
//    }

    groupModel.groups = self.groupIDSet;
    groupModel.lamps = self.lampIDSet;
    groupModel.duplicates = self.duplicates;
}

-(void)walkGroups: (NSMutableDictionary *)groups groupDataModel: (LSFGroupModel *)parentModel
{
    if (![self.groupIDSet containsObject: parentModel.theID])
    {
        [self.groupIDSet addObject: parentModel.theID];

        for (NSString *childGroupID in parentModel.members.lampGroups)
        {
            LSFGroupModel *childModel = [[groups valueForKey: childGroupID] getLampGroupDataModel];

            if (childModel != nil)
            {
                [self walkGroups: groups groupDataModel: childModel];
            }
        }
    }
    else
    {
        self.duplicates++;
    }

}

-(void)walkLamps: (NSMutableDictionary *)groups
{
    for (NSString *groupID in self.groupIDSet)
    {
        LSFGroupModel *groupModel = [[groups valueForKey: groupID] getLampGroupDataModel];

        if (groupModel != nil)
        {
            [self.lampIDSet addObjectsFromArray: groupModel.members.lamps];
        }
    }

}

@end