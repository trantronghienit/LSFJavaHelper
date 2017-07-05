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

#import "LSFModel.h"
#import "LSFNoEffectDataModel.h"
#import "LSFTransitionEffectDataModel.h"
#import "LSFPulseEffectDataModel.h"
#import <internal/LSFScene.h>

/**
 * @warning *Note:* This class is not intended to be used by clients, and its interface may change
 * in subsequent releases of the SDK.
 */
@interface LSFSceneDataModel : LSFModel
{
    @protected BOOL sceneInitialized;
}

@property (nonatomic, strong) NSMutableArray *noEffects;
@property (nonatomic, strong) NSMutableArray *transitionEffects;
@property (nonatomic, strong) NSMutableArray *pulseEffects;

-(id)initWithSceneID: (NSString *)theID;
-(void)updateNoEffect: (LSFNoEffectDataModel *)elementModel;
-(void)updateTransitionEffect: (LSFTransitionEffectDataModel *)elementModel;
-(void)updatePulseEffect: (LSFPulseEffectDataModel *)elementModel;
-(BOOL)removeElement: (NSString *)elementID;
-(LSFScene *)toScene;
-(void)fromScene: (LSFScene *)scene;
-(BOOL)containsPreset: (NSString *)presetID;
-(BOOL)containsGroup: (NSString *)groupID;

@end