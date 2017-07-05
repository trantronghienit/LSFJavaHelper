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
*
 ******************************************************************************/
#include <stddef.h>

#include <qcc/Debug.h>
#include <qcc/Log.h>

#include "XMasterScene.h"
#include "XMasterSceneManagerCallback.h"


#include "JEnum.h"
#include "JEnumArray.h"
#include "JLampDetails.h"
#include "JLampState.h"
#include "JStringArray.h"
#include "NTypes.h"
#include "NUtil.h"
#include "XCppTestDelegator.h"

#include "org_allseen_lsf_test_MasterSceneManagerCallbackTest.h"

#define QCC_MODULE "AJN-LSF-JNI-TEST"

using namespace ajn;
using namespace lsf;


JNIEXPORT
jstring JNICALL Java_org_allseen_lsf_test_MasterSceneManagerCallbackTest_getAllMasterSceneIDsReplyCB(JNIEnv *env, jobject thiz, jobject jCallback, jobject jResponseCode, jobjectArray jMasterSceneIDs)
{
    return XCppTestDelegator::Call_Void_ResponseCode_StringList<XMasterSceneManagerCallback>(env, jCallback, jResponseCode, jMasterSceneIDs, &XMasterSceneManagerCallback::GetAllMasterSceneIDsReplyCB, __func__);
}

JNIEXPORT
jstring JNICALL Java_org_allseen_lsf_test_MasterSceneManagerCallbackTest_getMasterSceneNameReplyCB(JNIEnv *env, jobject thiz, jobject jCallback, jobject jResponseCode, jstring jMasterSceneID, jstring jLanguage, jstring jMasterSceneName)
{
    return XCppTestDelegator::Call_Void_ResponseCode_String_String_String<XMasterSceneManagerCallback>(env, jCallback, jResponseCode, jMasterSceneID, jLanguage, jMasterSceneName, &XMasterSceneManagerCallback::GetMasterSceneNameReplyCB, __func__);
}

JNIEXPORT
jstring JNICALL Java_org_allseen_lsf_test_MasterSceneManagerCallbackTest_setMasterSceneNameReplyCB(JNIEnv *env, jobject thiz, jobject jCallback, jobject jResponseCode, jstring jMasterSceneID, jstring jLanguage)
{
    return XCppTestDelegator::Call_Void_ResponseCode_String_String<XMasterSceneManagerCallback>(env, jCallback, jResponseCode, jMasterSceneID, jLanguage, &XMasterSceneManagerCallback::SetMasterSceneNameReplyCB, __func__);
}

JNIEXPORT
jstring JNICALL Java_org_allseen_lsf_test_MasterSceneManagerCallbackTest_createMasterSceneReplyCB(JNIEnv *env, jobject thiz, jobject jCallback, jobject jResponseCode, jstring jMasterSceneID)
{
    return XCppTestDelegator::Call_Void_ResponseCode_String<XMasterSceneManagerCallback>(env, jCallback, jResponseCode, jMasterSceneID, &XMasterSceneManagerCallback::CreateMasterSceneReplyCB, __func__);
}

JNIEXPORT
jstring JNICALL Java_org_allseen_lsf_test_MasterSceneManagerCallbackTest_deleteMasterSceneReplyCB(JNIEnv *env, jobject thiz, jobject jCallback, jobject jResponseCode, jstring jMasterSceneID)
{
    return XCppTestDelegator::Call_Void_ResponseCode_String<XMasterSceneManagerCallback>(env, jCallback, jResponseCode, jMasterSceneID, &XMasterSceneManagerCallback::DeleteMasterSceneReplyCB, __func__);
}

JNIEXPORT
jstring JNICALL Java_org_allseen_lsf_test_MasterSceneManagerCallbackTest_updateMasterSceneReplyCB(JNIEnv *env, jobject thiz, jobject jCallback, jobject jResponseCode, jstring jMasterSceneID)
{
    return XCppTestDelegator::Call_Void_ResponseCode_String<XMasterSceneManagerCallback>(env, jCallback, jResponseCode, jMasterSceneID, &XMasterSceneManagerCallback::UpdateMasterSceneReplyCB, __func__);
}

JNIEXPORT
jstring JNICALL Java_org_allseen_lsf_test_MasterSceneManagerCallbackTest_applyMasterSceneReplyCB(JNIEnv *env, jobject thiz, jobject jCallback, jobject jResponseCode, jstring jMasterSceneID)
{
    return XCppTestDelegator::Call_Void_ResponseCode_String<XMasterSceneManagerCallback>(env, jCallback, jResponseCode, jMasterSceneID, &XMasterSceneManagerCallback::ApplyMasterSceneReplyCB, __func__);
}

JNIEXPORT
jstring JNICALL Java_org_allseen_lsf_test_MasterSceneManagerCallbackTest_createMasterSceneWithTrackingReplyCB(JNIEnv *env, jobject thiz, jobject jCallback, jobject jResponseCode, jstring jMasterSceneID, jlong jTrackingID)
{
    return XCppTestDelegator::Call_Void_ResponseCode_String_UInt32<XMasterSceneManagerCallback>(env, jCallback, jResponseCode, jMasterSceneID, jTrackingID, &XMasterSceneManagerCallback::CreateMasterSceneWithTrackingReplyCB, __func__);
}

JNIEXPORT
jstring JNICALL Java_org_allseen_lsf_test_MasterSceneManagerCallbackTest_getMasterSceneReplyCB(JNIEnv *env, jobject thiz, jobject jCallback, jobject jResponseCode, jstring jMasterSceneID, jobject jMasterScene)
{
    return XCppTestDelegator::Call_Void_ResponseCode_String_Object<XMasterSceneManagerCallback, XMasterScene, MasterScene>(env, jCallback, jResponseCode, jMasterSceneID, jMasterScene, &XMasterSceneManagerCallback::GetMasterSceneReplyCB, __func__);
}

JNIEXPORT
jstring JNICALL Java_org_allseen_lsf_test_MasterSceneManagerCallbackTest_masterScenesNameChangedCB(JNIEnv *env, jobject thiz, jobject jCallback, jobjectArray jMasterSceneIDs)
{
    return XCppTestDelegator::Call_Void_StringList<XMasterSceneManagerCallback>(env, jCallback, jMasterSceneIDs, &XMasterSceneManagerCallback::MasterScenesNameChangedCB, __func__);
}

JNIEXPORT
jstring JNICALL Java_org_allseen_lsf_test_MasterSceneManagerCallbackTest_masterScenesCreatedCB(JNIEnv *env, jobject thiz, jobject jCallback, jobjectArray jMasterSceneIDs)
{
    return XCppTestDelegator::Call_Void_StringList<XMasterSceneManagerCallback>(env, jCallback, jMasterSceneIDs, &XMasterSceneManagerCallback::MasterScenesCreatedCB, __func__);
}

JNIEXPORT
jstring JNICALL Java_org_allseen_lsf_test_MasterSceneManagerCallbackTest_masterScenesDeletedCB(JNIEnv *env, jobject thiz, jobject jCallback, jobjectArray jMasterSceneIDs)
{
    return XCppTestDelegator::Call_Void_StringList<XMasterSceneManagerCallback>(env, jCallback, jMasterSceneIDs, &XMasterSceneManagerCallback::MasterScenesDeletedCB, __func__);
}

JNIEXPORT
jstring JNICALL Java_org_allseen_lsf_test_MasterSceneManagerCallbackTest_masterScenesUpdatedCB(JNIEnv *env, jobject thiz, jobject jCallback, jobjectArray jMasterSceneIDs)
{
    return XCppTestDelegator::Call_Void_StringList<XMasterSceneManagerCallback>(env, jCallback, jMasterSceneIDs, &XMasterSceneManagerCallback::MasterScenesUpdatedCB, __func__);
}

JNIEXPORT
jstring JNICALL Java_org_allseen_lsf_test_MasterSceneManagerCallbackTest_masterScenesAppliedCB(JNIEnv *env, jobject thiz, jobject jCallback, jobjectArray jMasterSceneIDs)
{
    return XCppTestDelegator::Call_Void_StringList<XMasterSceneManagerCallback>(env, jCallback, jMasterSceneIDs, &XMasterSceneManagerCallback::MasterScenesAppliedCB, __func__);
}

