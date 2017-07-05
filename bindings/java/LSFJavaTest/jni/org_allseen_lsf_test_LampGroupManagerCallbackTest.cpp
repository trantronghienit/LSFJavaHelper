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

#include "JLampGroup.h"
#include "JLampGroupManagerCallback.h"
#include "XCppTestDelegator.h"
#include "org_allseen_lsf_test_LampGroupManagerCallbackTest.h"

#define QCC_MODULE "AJN-LSF-JNI-TEST"

using namespace ajn;
using namespace lsf;

JNIEXPORT
jstring JNICALL Java_org_allseen_lsf_test_LampGroupManagerCallbackTest_getAllLampGroupIDsReplyCB(JNIEnv *env, jobject thiz, jobject jCallback, jobject jResponseCode, jobjectArray jLampGroupIDs)
{
    return XCppTestDelegator::Call_Void_ResponseCode_StringList<JLampGroupManagerCallback>(env, jCallback, jResponseCode, jLampGroupIDs, &JLampGroupManagerCallback::GetAllLampGroupIDsReplyCB, __func__);
}

JNIEXPORT
jstring JNICALL Java_org_allseen_lsf_test_LampGroupManagerCallbackTest_getLampGroupNameReplyCB(JNIEnv *env, jobject thiz, jobject jCallback, jobject jResponseCode, jstring jLampGroupID, jstring jLanguage, jstring jLampGroupName)
{
    return XCppTestDelegator::Call_Void_ResponseCode_String_String_String<JLampGroupManagerCallback>(env, jCallback, jResponseCode, jLampGroupID, jLanguage, jLampGroupName, &JLampGroupManagerCallback::GetLampGroupNameReplyCB, __func__);
}

JNIEXPORT
jstring JNICALL Java_org_allseen_lsf_test_LampGroupManagerCallbackTest_setLampGroupNameReplyCB(JNIEnv *env, jobject thiz, jobject jCallback, jobject jResponseCode, jstring jLampGroupID, jstring jLanguage)
{
    return XCppTestDelegator::Call_Void_ResponseCode_String_String<JLampGroupManagerCallback>(env, jCallback, jResponseCode, jLampGroupID, jLanguage, &JLampGroupManagerCallback::SetLampGroupNameReplyCB, __func__);
}

JNIEXPORT
jstring JNICALL Java_org_allseen_lsf_test_LampGroupManagerCallbackTest_getLampGroupReplyCB(JNIEnv *env, jobject thiz, jobject jCallback, jobject jResponseCode, jstring jLampGroupID, jobject jLampGroup)
{
    return XCppTestDelegator::Call_Void_ResponseCode_String_Object<JLampGroupManagerCallback, JLampGroup, LampGroup>(env, jCallback, jResponseCode, jLampGroupID, jLampGroup, &JLampGroupManagerCallback::GetLampGroupReplyCB, __func__);
}

JNIEXPORT
jstring JNICALL Java_org_allseen_lsf_test_LampGroupManagerCallbackTest_transitionLampGroupStateOnOffFieldReplyCB(JNIEnv *env, jobject thiz, jobject jCallback, jobject jResponseCode, jstring jLampGroupID)
{
    return XCppTestDelegator::Call_Void_ResponseCode_String<JLampGroupManagerCallback>(env, jCallback, jResponseCode, jLampGroupID, &JLampGroupManagerCallback::TransitionLampGroupStateOnOffFieldReplyCB, __func__);
}

JNIEXPORT
jstring JNICALL Java_org_allseen_lsf_test_LampGroupManagerCallbackTest_lampGroupsNameChangedCB(JNIEnv *env, jobject thiz, jobject jCallback, jobjectArray jLampGroupIDs)
{
    return XCppTestDelegator::Call_Void_StringList<JLampGroupManagerCallback>(env, jCallback, jLampGroupIDs, &JLampGroupManagerCallback::LampGroupsNameChangedCB, __func__);
}


JNIEXPORT
jstring JNICALL Java_org_allseen_lsf_test_LampGroupManagerCallbackTest_lampGroupsUpdatedCB(JNIEnv *env, jobject thiz, jobject jCallback, jobjectArray jLampGroupIDs)
{
    return XCppTestDelegator::Call_Void_StringList<JLampGroupManagerCallback>(env, jCallback, jLampGroupIDs, &JLampGroupManagerCallback::LampGroupsUpdatedCB, __func__);
}


JNIEXPORT
jstring JNICALL Java_org_allseen_lsf_test_LampGroupManagerCallbackTest_createLampGroupReplyCB(JNIEnv *env, jobject thiz, jobject jCallback, jobject jResponseCode, jstring jLampGroupID)
{
    return XCppTestDelegator::Call_Void_ResponseCode_String<JLampGroupManagerCallback>(env, jCallback, jResponseCode, jLampGroupID, &JLampGroupManagerCallback::CreateLampGroupReplyCB, __func__);
}

JNIEXPORT
jstring JNICALL Java_org_allseen_lsf_test_LampGroupManagerCallbackTest_lampGroupsCreatedCB(JNIEnv *env, jobject thiz, jobject jCallback, jobjectArray jLampGroupIDs)
{
    return XCppTestDelegator::Call_Void_StringList<JLampGroupManagerCallback>(env, jCallback, jLampGroupIDs, &JLampGroupManagerCallback::LampGroupsCreatedCB, __func__);
}


JNIEXPORT
jstring JNICALL Java_org_allseen_lsf_test_LampGroupManagerCallbackTest_deleteLampGroupReplyCB(JNIEnv *env, jobject thiz, jobject jCallback, jobject jResponseCode, jstring jLampGroupID)
{
    return XCppTestDelegator::Call_Void_ResponseCode_String<JLampGroupManagerCallback>(env, jCallback, jResponseCode, jLampGroupID, &JLampGroupManagerCallback::DeleteLampGroupReplyCB, __func__);
}


JNIEXPORT
jstring JNICALL Java_org_allseen_lsf_test_LampGroupManagerCallbackTest_lampGroupsDeletedCB(JNIEnv *env, jobject thiz, jobject jCallback, jobjectArray jLampGroupIDs)
{

    return XCppTestDelegator::Call_Void_StringList<JLampGroupManagerCallback>(env, jCallback, jLampGroupIDs, &JLampGroupManagerCallback::LampGroupsDeletedCB, __func__);
}



JNIEXPORT
jstring JNICALL Java_org_allseen_lsf_test_LampGroupManagerCallbackTest_transitionLampGroupStateReplyCB(JNIEnv *env, jobject thiz, jobject jCallback, jobject jResponseCode, jstring jLampGroupID)
{
    return XCppTestDelegator::Call_Void_ResponseCode_String<JLampGroupManagerCallback>(env, jCallback, jResponseCode, jLampGroupID, &JLampGroupManagerCallback::TransitionLampGroupStateReplyCB, __func__);
}



JNIEXPORT
jstring JNICALL Java_org_allseen_lsf_test_LampGroupManagerCallbackTest_pulseLampGroupWithStateReplyCB(JNIEnv *env, jobject thiz, jobject jCallback, jobject jResponseCode, jstring jLampGroupID)
{
    return XCppTestDelegator::Call_Void_ResponseCode_String<JLampGroupManagerCallback>(env, jCallback, jResponseCode, jLampGroupID, &JLampGroupManagerCallback::PulseLampGroupWithStateReplyCB, __func__);
}



JNIEXPORT
jstring JNICALL Java_org_allseen_lsf_test_LampGroupManagerCallbackTest_pulseLampGroupWithPresetReplyCB(JNIEnv *env, jobject thiz, jobject jCallback, jobject jResponseCode, jstring jLampGroupID)
{
    return XCppTestDelegator::Call_Void_ResponseCode_String<JLampGroupManagerCallback>(env, jCallback, jResponseCode, jLampGroupID, &JLampGroupManagerCallback::PulseLampGroupWithPresetReplyCB, __func__);
}

JNIEXPORT
jstring JNICALL Java_org_allseen_lsf_test_LampGroupManagerCallbackTest_transitionLampGroupStateHueFieldReplyCB(JNIEnv *env, jobject thiz, jobject jCallback, jobject jResponseCode, jstring jLampGroupID)
{
    return XCppTestDelegator::Call_Void_ResponseCode_String<JLampGroupManagerCallback>(env, jCallback, jResponseCode, jLampGroupID, &JLampGroupManagerCallback::TransitionLampGroupStateHueFieldReplyCB, __func__);
}


JNIEXPORT
jstring JNICALL Java_org_allseen_lsf_test_LampGroupManagerCallbackTest_transitionLampGroupStateSaturationFieldReplyCB(JNIEnv *env, jobject thiz, jobject jCallback, jobject jResponseCode, jstring jLampGroupID)
{
    return XCppTestDelegator::Call_Void_ResponseCode_String<JLampGroupManagerCallback>(env, jCallback, jResponseCode, jLampGroupID, &JLampGroupManagerCallback::TransitionLampGroupStateSaturationFieldReplyCB, __func__);
}

JNIEXPORT
jstring JNICALL Java_org_allseen_lsf_test_LampGroupManagerCallbackTest_transitionLampGroupStateBrightnessFieldReplyCB(JNIEnv *env, jobject thiz, jobject jCallback, jobject jResponseCode, jstring jLampGroupID)
{
    return XCppTestDelegator::Call_Void_ResponseCode_String<JLampGroupManagerCallback>(env, jCallback, jResponseCode, jLampGroupID, &JLampGroupManagerCallback::TransitionLampGroupStateBrightnessFieldReplyCB, __func__);
}

JNIEXPORT
jstring JNICALL Java_org_allseen_lsf_test_LampGroupManagerCallbackTest_transitionLampGroupStateColorTempFieldReplyCB(JNIEnv *env, jobject thiz, jobject jCallback, jobject jResponseCode, jstring jLampGroupID)
{
    return XCppTestDelegator::Call_Void_ResponseCode_String<JLampGroupManagerCallback>(env, jCallback, jResponseCode, jLampGroupID, &JLampGroupManagerCallback::TransitionLampGroupStateColorTempFieldReplyCB, __func__);
}

JNIEXPORT
jstring JNICALL Java_org_allseen_lsf_test_LampGroupManagerCallbackTest_resetLampGroupStateReplyCB(JNIEnv *env, jobject thiz, jobject jCallback, jobject jResponseCode, jstring jLampGroupID)
{
    return XCppTestDelegator::Call_Void_ResponseCode_String<JLampGroupManagerCallback>(env, jCallback, jResponseCode, jLampGroupID, &JLampGroupManagerCallback::ResetLampGroupStateReplyCB, __func__);
}

JNIEXPORT
jstring JNICALL Java_org_allseen_lsf_test_LampGroupManagerCallbackTest_resetLampGroupStateOnOffFieldReplyCB(JNIEnv *env, jobject thiz, jobject jCallback, jobject jResponseCode, jstring jLampGroupID)
{
   return XCppTestDelegator::Call_Void_ResponseCode_String<JLampGroupManagerCallback>(env, jCallback, jResponseCode, jLampGroupID, &JLampGroupManagerCallback::ResetLampGroupStateOnOffFieldReplyCB, __func__);
}

JNIEXPORT
jstring JNICALL Java_org_allseen_lsf_test_LampGroupManagerCallbackTest_resetLampGroupStateHueFieldReplyCB(JNIEnv *env, jobject thiz, jobject jCallback, jobject jResponseCode, jstring jLampGroupID)
{
   return XCppTestDelegator::Call_Void_ResponseCode_String<JLampGroupManagerCallback>(env, jCallback, jResponseCode, jLampGroupID, &JLampGroupManagerCallback::ResetLampGroupStateHueFieldReplyCB, __func__);
}

JNIEXPORT
jstring JNICALL Java_org_allseen_lsf_test_LampGroupManagerCallbackTest_resetLampGroupStateSaturationFieldReplyCB(JNIEnv *env, jobject thiz, jobject jCallback, jobject jResponseCode, jstring jLampGroupID)
{
   return XCppTestDelegator::Call_Void_ResponseCode_String<JLampGroupManagerCallback>(env, jCallback, jResponseCode, jLampGroupID, &JLampGroupManagerCallback::ResetLampGroupStateSaturationFieldReplyCB, __func__);
}

JNIEXPORT
jstring JNICALL Java_org_allseen_lsf_test_LampGroupManagerCallbackTest_resetLampGroupStateBrightnessFieldReplyCB(JNIEnv *env, jobject thiz, jobject jCallback, jobject jResponseCode, jstring jLampGroupID)
{
   return XCppTestDelegator::Call_Void_ResponseCode_String<JLampGroupManagerCallback>(env, jCallback, jResponseCode, jLampGroupID, &JLampGroupManagerCallback::ResetLampGroupStateBrightnessFieldReplyCB, __func__);
}

JNIEXPORT
jstring JNICALL Java_org_allseen_lsf_test_LampGroupManagerCallbackTest_resetLampGroupStateColorTempFieldReplyCB(JNIEnv *env, jobject thiz, jobject jCallback, jobject jResponseCode, jstring jLampGroupID)
{
   return XCppTestDelegator::Call_Void_ResponseCode_String<JLampGroupManagerCallback>(env, jCallback, jResponseCode, jLampGroupID, &JLampGroupManagerCallback::ResetLampGroupStateColorTempFieldReplyCB, __func__);
}

JNIEXPORT
jstring JNICALL Java_org_allseen_lsf_test_LampGroupManagerCallbackTest_updateLampGroupReplyCB(JNIEnv *env, jobject thiz, jobject jCallback, jobject jResponseCode, jstring jLampGroupID)
{
   return XCppTestDelegator::Call_Void_ResponseCode_String<JLampGroupManagerCallback>(env, jCallback, jResponseCode, jLampGroupID, &JLampGroupManagerCallback::UpdateLampGroupReplyCB, __func__);
}

JNIEXPORT
jstring JNICALL Java_org_allseen_lsf_test_LampGroupManagerCallbackTest_transitionLampGroupStateToPresetReplyCB(JNIEnv *env, jobject thiz, jobject jCallback, jobject jResponseCode, jstring jLampGroupID)
{
   return XCppTestDelegator::Call_Void_ResponseCode_String<JLampGroupManagerCallback>(env, jCallback, jResponseCode, jLampGroupID, &JLampGroupManagerCallback::TransitionLampGroupStateToPresetReplyCB, __func__);
}


JNIEXPORT
jstring JNICALL Java_org_allseen_lsf_test_LampGroupManagerCallbackTest_createLampGroupWithTrackingReplyCB(JNIEnv *env, jobject thiz, jobject jCallback, jobject jResponseCode, jstring jLampGroupID, jlong jTrackingID)
{

   return XCppTestDelegator::Call_Void_ResponseCode_String_UInt32<JLampGroupManagerCallback>(env, jCallback, jResponseCode, jLampGroupID, jTrackingID, &JLampGroupManagerCallback::CreateLampGroupWithTrackingReplyCB, __func__);
}


JNIEXPORT
jstring JNICALL Java_org_allseen_lsf_test_LampGroupManagerCallbackTest_setLampGroupEffectReplyCB(JNIEnv *env, jobject thiz, jobject jCallback, jobject jResponseCode, jstring jLampGroupID, jstring jEffectID)
{
    return XCppTestDelegator::Call_Void_ResponseCode_String_String<JLampGroupManagerCallback>(env, jCallback, jResponseCode, jLampGroupID, jEffectID, &JLampGroupManagerCallback::SetLampGroupEffectReplyCB, __func__);
}

#undef QCC_MODULE
