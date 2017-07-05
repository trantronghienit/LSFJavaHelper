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

#include "JLampState.h"
#include "XPresetManagerCallback.h"
#include "XCppTestDelegator.h"

#include "org_allseen_lsf_test_PresetManagerCallbackTest.h"

#define QCC_MODULE "AJN-LSF-JNI-TEST"

using namespace ajn;
using namespace lsf;

JNIEXPORT
jstring JNICALL Java_org_allseen_lsf_test_PresetManagerCallbackTest_getAllPresetIDsReplyCB(JNIEnv *env, jobject thiz, jobject jCallback, jobject jResponseCode, jobjectArray jPresetIDs)
{
    return XCppTestDelegator::Call_Void_ResponseCode_StringList<XPresetManagerCallback>(env, jCallback, jResponseCode, jPresetIDs, &XPresetManagerCallback::GetAllPresetIDsReplyCB, __func__);
}

JNIEXPORT
jstring JNICALL Java_org_allseen_lsf_test_PresetManagerCallbackTest_getPresetReplyCB(JNIEnv *env, jobject thiz, jobject jCallback, jobject jResponseCode, jstring jPresetID, jobject jPreset)
{
    return XCppTestDelegator::Call_Void_ResponseCode_String_Object<XPresetManagerCallback, JLampState, LampState>(env, jCallback, jResponseCode, jPresetID, jPreset, &XPresetManagerCallback::GetPresetReplyCB, __func__);
}

JNIEXPORT
jstring JNICALL Java_org_allseen_lsf_test_PresetManagerCallbackTest_getPresetNameReplyCB(JNIEnv *env, jobject thiz, jobject jCallback, jobject jResponseCode, jstring jPresetID, jstring jLanguage, jstring jPresetName)
{
    return XCppTestDelegator::Call_Void_ResponseCode_String_String_String<XPresetManagerCallback>(env, jCallback, jResponseCode, jPresetID, jLanguage, jPresetName, &XPresetManagerCallback::GetPresetNameReplyCB, __func__);
}

JNIEXPORT
jstring JNICALL Java_org_allseen_lsf_test_PresetManagerCallbackTest_setPresetNameReplyCB(JNIEnv *env, jobject thiz, jobject jCallback, jobject jResponseCode, jstring jPresetID, jstring jLanguage)
{
    return XCppTestDelegator::Call_Void_ResponseCode_String_String<XPresetManagerCallback>(env, jCallback, jResponseCode, jPresetID, jLanguage, &XPresetManagerCallback::SetPresetNameReplyCB, __func__);
}






JNIEXPORT
jstring JNICALL Java_org_allseen_lsf_test_PresetManagerCallbackTest_presetsNameChangedCB(JNIEnv *env, jobject thiz, jobject jCallback, jobjectArray jPresetIDs)
{
    return XCppTestDelegator::Call_Void_StringList<XPresetManagerCallback>(env, jCallback, jPresetIDs, &XPresetManagerCallback::PresetsNameChangedCB, __func__);
}

JNIEXPORT
jstring JNICALL Java_org_allseen_lsf_test_PresetManagerCallbackTest_createPresetReplyCB(JNIEnv *env, jobject thiz, jobject jCallback, jobject jResponseCode, jstring jPresetID)
{
    return XCppTestDelegator::Call_Void_ResponseCode_String<XPresetManagerCallback>(env, jCallback, jResponseCode, jPresetID, &XPresetManagerCallback::CreatePresetReplyCB, __func__);
}

JNIEXPORT
jstring JNICALL Java_org_allseen_lsf_test_PresetManagerCallbackTest_presetsCreatedCB(JNIEnv *env, jobject thiz, jobject jCallback, jobjectArray jPresetIDs)
{
	return XCppTestDelegator::Call_Void_StringList<XPresetManagerCallback>(env, jCallback, jPresetIDs, &XPresetManagerCallback::PresetsCreatedCB, __func__);
}

JNIEXPORT
jstring JNICALL Java_org_allseen_lsf_test_PresetManagerCallbackTest_updatePresetReplyCB(JNIEnv *env, jobject thiz, jobject jCallback, jobject jResponseCode, jstring jPresetID)
{
    return XCppTestDelegator::Call_Void_ResponseCode_String<XPresetManagerCallback>(env, jCallback, jResponseCode, jPresetID, &XPresetManagerCallback::UpdatePresetReplyCB, __func__);
}

JNIEXPORT
jstring JNICALL Java_org_allseen_lsf_test_PresetManagerCallbackTest_presetsUpdatedCB(JNIEnv *env, jobject thiz, jobject jCallback, jobjectArray jPresetIDs)
{
    return XCppTestDelegator::Call_Void_StringList<XPresetManagerCallback>(env, jCallback, jPresetIDs, &XPresetManagerCallback::PresetsUpdatedCB, __func__);
}


JNIEXPORT
jstring JNICALL Java_org_allseen_lsf_test_PresetManagerCallbackTest_deletePresetReplyCB(JNIEnv *env, jobject thiz, jobject jCallback, jobject jResponseCode, jstring jPresetID)
{
    return XCppTestDelegator::Call_Void_ResponseCode_String<XPresetManagerCallback>(env, jCallback, jResponseCode, jPresetID, &XPresetManagerCallback::DeletePresetReplyCB, __func__);
}


JNIEXPORT
jstring JNICALL Java_org_allseen_lsf_test_PresetManagerCallbackTest_presetsDeletedCB(JNIEnv *env, jobject thiz, jobject jCallback, jobjectArray jPresetIDs)
{
    return XCppTestDelegator::Call_Void_StringList<XPresetManagerCallback>(env, jCallback, jPresetIDs, &XPresetManagerCallback::PresetsDeletedCB, __func__);
}

JNIEXPORT
jstring JNICALL Java_org_allseen_lsf_test_PresetManagerCallbackTest_getDefaultLampStateReplyCB(JNIEnv *env, jobject thiz, jobject jCallback, jobject jResponseCode, jobject jDefaultLampState)
{
    return XCppTestDelegator::Call_Void_ResponseCode_Object<XPresetManagerCallback, JLampState, LampState>(env, jCallback, jResponseCode, jDefaultLampState, &XPresetManagerCallback::GetDefaultLampStateReplyCB, __func__);
}

JNIEXPORT
jstring JNICALL Java_org_allseen_lsf_test_PresetManagerCallbackTest_setDefaultLampStateReplyCB(JNIEnv *env, jobject thiz, jobject jCallback, jobject jResponseCode)
{
    return XCppTestDelegator::Call_Void_ResponseCode<XPresetManagerCallback>(env, jCallback, jResponseCode, &XPresetManagerCallback::SetDefaultLampStateReplyCB, __func__);
}

JNIEXPORT
jstring JNICALL Java_org_allseen_lsf_test_PresetManagerCallbackTest_defaultLampStateChangedCB(JNIEnv *env, jobject thiz, jobject jCallback)
{
    return XCppTestDelegator::Call_Void<XPresetManagerCallback>(env, jCallback, &XPresetManagerCallback::DefaultLampStateChangedCB, __func__);
}

JNIEXPORT
jstring JNICALL Java_org_allseen_lsf_test_PresetManagerCallbackTest_createPresetWithTrackingReplyCB(JNIEnv *env, jobject thiz, jobject jCallback, jobject jResponseCode, jstring jPresetID, jlong jTrackingID)
{
    return XCppTestDelegator::Call_Void_ResponseCode_String_UInt32<XPresetManagerCallback>(env, jCallback, jResponseCode, jPresetID, jTrackingID, &XPresetManagerCallback::CreatePresetWithTrackingReplyCB, __func__);
}
