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
#include <android/log.h>

#include "XPulseEffectManagerCallback.h"
#include "XCppTestDelegator.h"

#include "JEnum.h"
#include "JEnumArray.h"
#include "JLampDetails.h"
#include "XPulseEffectV2.h"
#include "JLampState.h"
#include "JStringArray.h"
#include "NTypes.h"
#include "NUtil.h"

#include "XCppTestDelegator.h"

#include "org_allseen_lsf_test_PulseEffectManagerCallbackTest.h"

#define QCC_MODULE "AJN-LSF-JNI-TEST"

using namespace ajn;
using namespace lsf;

JNIEXPORT
jstring JNICALL Java_org_allseen_lsf_test_PulseEffectManagerCallbackTest_getPulseEffectReplyCB(JNIEnv *env, jobject thiz, jobject jCallback, jobject jResponseCode, jstring jPulseEffectID, jobject jPulseEffect)
{

    return XCppTestDelegator::Call_Void_ResponseCode_String_Object<XPulseEffectManagerCallback, XPulseEffectV2, PulseEffect>(env, jCallback, jResponseCode, jPulseEffectID, jPulseEffect, &XPulseEffectManagerCallback::GetPulseEffectReplyCB, __func__);
}

JNIEXPORT
jstring JNICALL Java_org_allseen_lsf_test_PulseEffectManagerCallbackTest_applyPulseEffectOnLampsReplyCB(JNIEnv *env, jobject thiz, jobject jCallback, jobject jResponseCode, jstring jPulseEffectID, jobjectArray jLampIDs)
{
    return XCppTestDelegator::Call_Void_ResponseCode_String_StringList<XPulseEffectManagerCallback>(env, jCallback, jResponseCode, jPulseEffectID, jLampIDs, &XPulseEffectManagerCallback::ApplyPulseEffectOnLampsReplyCB, __func__);
}

JNIEXPORT
jstring JNICALL Java_org_allseen_lsf_test_PulseEffectManagerCallbackTest_applyPulseEffectOnLampGroupsReplyCB(JNIEnv *env, jobject thiz, jobject jCallback, jobject jResponseCode, jstring jPulseEffectID, jobjectArray jLampGroupIDs)
{
    return XCppTestDelegator::Call_Void_ResponseCode_String_StringList<XPulseEffectManagerCallback>(env, jCallback, jResponseCode, jPulseEffectID, jLampGroupIDs, &XPulseEffectManagerCallback::ApplyPulseEffectOnLampGroupsReplyCB, __func__);
}

JNIEXPORT
jstring JNICALL Java_org_allseen_lsf_test_PulseEffectManagerCallbackTest_getAllPulseEffectIDsReplyCB(JNIEnv *env, jobject thiz, jobject jCallback, jobject jResponseCode, jobjectArray jPulseEffectIDs)
{
    return XCppTestDelegator::Call_Void_ResponseCode_StringList<XPulseEffectManagerCallback>(env, jCallback, jResponseCode, jPulseEffectIDs, &XPulseEffectManagerCallback::GetAllPulseEffectIDsReplyCB, __func__);
}

JNIEXPORT
jstring JNICALL Java_org_allseen_lsf_test_PulseEffectManagerCallbackTest_getPulseEffectNameReplyCB(JNIEnv *env, jobject thiz, jobject jCallback, jobject jResponseCode, jstring jPulseEffectID, jstring jLanguage, jstring jPulseEffectName)
{

    return XCppTestDelegator::Call_Void_ResponseCode_String_String_String<XPulseEffectManagerCallback>(env, jCallback, jResponseCode, jPulseEffectID, jLanguage, jPulseEffectName, &XPulseEffectManagerCallback::GetPulseEffectNameReplyCB, __func__);
}

JNIEXPORT
jstring JNICALL Java_org_allseen_lsf_test_PulseEffectManagerCallbackTest_setPulseEffectNameReplyCB(JNIEnv *env, jobject thiz, jobject jCallback, jobject jResponseCode, jstring jPulseEffectID, jstring jLanguage)
{

    return XCppTestDelegator::Call_Void_ResponseCode_String_String<XPulseEffectManagerCallback>(env, jCallback, jResponseCode, jPulseEffectID, jLanguage, &XPulseEffectManagerCallback::SetPulseEffectNameReplyCB, __func__);
}

JNIEXPORT
jstring JNICALL Java_org_allseen_lsf_test_PulseEffectManagerCallbackTest_updatePulseEffectReplyCB(JNIEnv *env, jobject thiz, jobject jCallback, jobject jResponseCode, jstring jPulseEffectID)
{

    return XCppTestDelegator::Call_Void_ResponseCode_String<XPulseEffectManagerCallback>(env, jCallback, jResponseCode, jPulseEffectID, &XPulseEffectManagerCallback::UpdatePulseEffectReplyCB, __func__);
}

JNIEXPORT
jstring JNICALL Java_org_allseen_lsf_test_PulseEffectManagerCallbackTest_deletePulseEffectReplyCB(JNIEnv *env, jobject thiz, jobject jCallback, jobject jResponseCode, jstring jPulseEffectID)
{

    return XCppTestDelegator::Call_Void_ResponseCode_String<XPulseEffectManagerCallback>(env, jCallback, jResponseCode, jPulseEffectID, &XPulseEffectManagerCallback::DeletePulseEffectReplyCB, __func__);
}

JNIEXPORT
jstring JNICALL Java_org_allseen_lsf_test_PulseEffectManagerCallbackTest_pulseEffectsNameChangedCB(JNIEnv *env, jobject thiz, jobject jCallback, jobjectArray jPulseEffectIDs)
{

    return XCppTestDelegator::Call_Void_StringList<XPulseEffectManagerCallback>(env, jCallback, jPulseEffectIDs, &XPulseEffectManagerCallback::PulseEffectsNameChangedCB, __func__);
}

JNIEXPORT
jstring JNICALL Java_org_allseen_lsf_test_PulseEffectManagerCallbackTest_pulseEffectsCreatedCB(JNIEnv *env, jobject thiz, jobject jCallback, jobjectArray jPulseEffectIDs)
{
    return XCppTestDelegator::Call_Void_StringList<XPulseEffectManagerCallback>(env, jCallback, jPulseEffectIDs, &XPulseEffectManagerCallback::PulseEffectsCreatedCB, __func__);
}


JNIEXPORT
jstring JNICALL Java_org_allseen_lsf_test_PulseEffectManagerCallbackTest_pulseEffectsUpdatedCB(JNIEnv *env, jobject thiz, jobject jCallback, jobjectArray jPulseEffectIDs)
{
    return XCppTestDelegator::Call_Void_StringList<XPulseEffectManagerCallback>(env, jCallback, jPulseEffectIDs, &XPulseEffectManagerCallback::PulseEffectsUpdatedCB, __func__);
}

JNIEXPORT
jstring JNICALL Java_org_allseen_lsf_test_PulseEffectManagerCallbackTest_pulseEffectsDeletedCB(JNIEnv *env, jobject thiz, jobject jCallback, jobjectArray jPulseEffectIDs)
{
    return XCppTestDelegator::Call_Void_StringList<XPulseEffectManagerCallback>(env, jCallback, jPulseEffectIDs, &XPulseEffectManagerCallback::PulseEffectsDeletedCB, __func__);
}

JNIEXPORT
jstring JNICALL Java_org_allseen_lsf_test_PulseEffectManagerCallbackTest_createPulseEffectReplyCB(JNIEnv *env, jobject thiz, jobject jCallback, jobject jResponseCode, jstring jPulseEffectID, jlong jTrackingID)
{

    return XCppTestDelegator::Call_Void_ResponseCode_String_UInt32<XPulseEffectManagerCallback>(env, jCallback, jResponseCode, jPulseEffectID, jTrackingID, &XPulseEffectManagerCallback::CreatePulseEffectReplyCB, __func__);
}

