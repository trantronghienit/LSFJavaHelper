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

#include "XTransitionEffectManagerCallback.h"
#include "XCppTestDelegator.h"

#include "JEnum.h"
#include "JEnumArray.h"
#include "JLampDetails.h"
#include "XTransitionEffectV2.h"

#include "JLampState.h"
#include "JStringArray.h"
#include "NTypes.h"
#include "NUtil.h"

#include "XCppTestDelegator.h"

#include "org_allseen_lsf_test_TransitionEffectManagerCallbackTest.h"

#define QCC_MODULE "AJN-LSF-JNI-TEST"

using namespace ajn;
using namespace lsf;


JNIEXPORT
jstring JNICALL Java_org_allseen_lsf_test_TransitionEffectManagerCallbackTest_getTransitionEffectReplyCB(JNIEnv *env, jobject thiz, jobject jCallback, jobject jResponseCode, jstring jTransitionEffectID, jobject jTransitionEffect)
{
    return XCppTestDelegator::Call_Void_ResponseCode_String_Object<XTransitionEffectManagerCallback, XTransitionEffectV2, TransitionEffect>(env, jCallback, jResponseCode, jTransitionEffectID, jTransitionEffect, &XTransitionEffectManagerCallback::GetTransitionEffectReplyCB, __func__);
}

JNIEXPORT
jstring JNICALL Java_org_allseen_lsf_test_TransitionEffectManagerCallbackTest_applyTransitionEffectOnLampsReplyCB(JNIEnv *env, jobject thiz, jobject jCallback, jobject jResponseCode, jstring jTransitionEffectID, jobjectArray jLampIDs)
{
    return XCppTestDelegator::Call_Void_ResponseCode_String_StringList<XTransitionEffectManagerCallback>(env, jCallback, jResponseCode, jTransitionEffectID, jLampIDs, &XTransitionEffectManagerCallback::ApplyTransitionEffectOnLampsReplyCB, __func__);
}

JNIEXPORT
jstring JNICALL Java_org_allseen_lsf_test_TransitionEffectManagerCallbackTest_applyTransitionEffectOnLampGroupsReplyCB(JNIEnv *env, jobject thiz, jobject jCallback, jobject jResponseCode, jstring jTransitionEffectID, jobjectArray jLampGroupIDs)
{
    return XCppTestDelegator::Call_Void_ResponseCode_String_StringList<XTransitionEffectManagerCallback>(env, jCallback, jResponseCode, jTransitionEffectID, jLampGroupIDs, &XTransitionEffectManagerCallback::ApplyTransitionEffectOnLampGroupsReplyCB, __func__);
}

JNIEXPORT
jstring JNICALL Java_org_allseen_lsf_test_TransitionEffectManagerCallbackTest_getAllTransitionEffectIDsReplyCB(JNIEnv *env, jobject thiz, jobject jCallback, jobject jResponseCode, jobjectArray jTransitionEffectIDs)
{
    return XCppTestDelegator::Call_Void_ResponseCode_StringList<XTransitionEffectManagerCallback>(env, jCallback, jResponseCode, jTransitionEffectIDs, &XTransitionEffectManagerCallback::GetAllTransitionEffectIDsReplyCB, __func__);
}


JNIEXPORT
jstring JNICALL Java_org_allseen_lsf_test_TransitionEffectManagerCallbackTest_updateTransitionEffectReplyCB(JNIEnv *env, jobject thiz, jobject jCallback, jobject jResponseCode, jstring jTransitionEffectID)
{
    return XCppTestDelegator::Call_Void_ResponseCode_String<XTransitionEffectManagerCallback>(env, jCallback, jResponseCode, jTransitionEffectID, &XTransitionEffectManagerCallback::UpdateTransitionEffectReplyCB, __func__);
}

JNIEXPORT
jstring JNICALL Java_org_allseen_lsf_test_TransitionEffectManagerCallbackTest_deleteTransitionEffectReplyCB(JNIEnv *env, jobject thiz, jobject jCallback, jobject jResponseCode, jstring jTransitionEffectID)
{
    return XCppTestDelegator::Call_Void_ResponseCode_String<XTransitionEffectManagerCallback>(env, jCallback, jResponseCode, jTransitionEffectID, &XTransitionEffectManagerCallback::DeleteTransitionEffectReplyCB, __func__);
}

JNIEXPORT
jstring JNICALL Java_org_allseen_lsf_test_TransitionEffectManagerCallbackTest_getTransitionEffectNameReplyCB(JNIEnv *env, jobject thiz, jobject jCallback, jobject jResponseCode, jstring jTransitionEffectID, jstring jLanguage, jstring jTransitionEffectName)
{
    return XCppTestDelegator::Call_Void_ResponseCode_String_String_String<XTransitionEffectManagerCallback>(env, jCallback, jResponseCode, jTransitionEffectID, jLanguage, jTransitionEffectName, &XTransitionEffectManagerCallback::GetTransitionEffectNameReplyCB, __func__);
}

JNIEXPORT
jstring JNICALL Java_org_allseen_lsf_test_TransitionEffectManagerCallbackTest_setTransitionEffectNameReplyCB(JNIEnv *env, jobject thiz, jobject jCallback, jobject jResponseCode, jstring jTransitionEffectID, jstring jLanguage)
{
    return XCppTestDelegator::Call_Void_ResponseCode_String_String<XTransitionEffectManagerCallback>(env, jCallback, jResponseCode, jTransitionEffectID, jLanguage, &XTransitionEffectManagerCallback::SetTransitionEffectNameReplyCB, __func__);
}

JNIEXPORT
jstring JNICALL Java_org_allseen_lsf_test_TransitionEffectManagerCallbackTest_createTransitionEffectReplyCB(JNIEnv *env, jobject thiz, jobject jCallback, jobject jResponseCode, jstring jTransitionEffectID, jlong jTrackingID)
{
    return XCppTestDelegator::Call_Void_ResponseCode_String_UInt32<XTransitionEffectManagerCallback>(env, jCallback, jResponseCode, jTransitionEffectID, jTrackingID, &XTransitionEffectManagerCallback::CreateTransitionEffectReplyCB, __func__);
}

JNIEXPORT
jstring JNICALL Java_org_allseen_lsf_test_TransitionEffectManagerCallbackTest_transitionEffectsNameChangedCB(JNIEnv *env, jobject thiz, jobject jCallback, jobjectArray jTransitionEffectIDs)
{
    return XCppTestDelegator::Call_Void_StringList<XTransitionEffectManagerCallback>(env, jCallback, jTransitionEffectIDs, &XTransitionEffectManagerCallback::TransitionEffectsNameChangedCB, __func__);
}

JNIEXPORT
jstring JNICALL Java_org_allseen_lsf_test_TransitionEffectManagerCallbackTest_transitionEffectsCreatedCB(JNIEnv *env, jobject thiz, jobject jCallback, jobjectArray jTransitionEffectIDs)
{
    return XCppTestDelegator::Call_Void_StringList<XTransitionEffectManagerCallback>(env, jCallback, jTransitionEffectIDs, &XTransitionEffectManagerCallback::TransitionEffectsCreatedCB, __func__);
}

JNIEXPORT
jstring JNICALL Java_org_allseen_lsf_test_TransitionEffectManagerCallbackTest_transitionEffectsUpdatedCB(JNIEnv *env, jobject thiz, jobject jCallback, jobjectArray jTransitionEffectIDs)
{
    return XCppTestDelegator::Call_Void_StringList<XTransitionEffectManagerCallback>(env, jCallback, jTransitionEffectIDs, &XTransitionEffectManagerCallback::TransitionEffectsUpdatedCB, __func__);
}

JNIEXPORT
jstring JNICALL Java_org_allseen_lsf_test_TransitionEffectManagerCallbackTest_transitionEffectsDeletedCB(JNIEnv *env, jobject thiz, jobject jCallback, jobjectArray jTransitionEffectIDs)
{
    return XCppTestDelegator::Call_Void_StringList<XTransitionEffectManagerCallback>(env, jCallback, jTransitionEffectIDs, &XTransitionEffectManagerCallback::TransitionEffectsDeletedCB, __func__);
}
