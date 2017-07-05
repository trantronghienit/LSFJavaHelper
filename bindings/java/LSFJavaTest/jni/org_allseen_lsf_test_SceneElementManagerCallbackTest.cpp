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

#include "XSceneElement.h"
#include "XSceneElementManagerCallback.h"
#include "XCppTestDelegator.h"
#include "org_allseen_lsf_test_SceneElementManagerCallbackTest.h"

#define QCC_MODULE "AJN-LSF-JNI-TEST"

using namespace ajn;
using namespace lsf;


JNIEXPORT
jstring JNICALL Java_org_allseen_lsf_test_SceneElementManagerCallbackTest_getAllSceneElementIDsReplyCB(JNIEnv *env, jobject thiz, jobject jCallback, jobject jResponseCode, jobjectArray jSceneElementIDs)
{
    return XCppTestDelegator::Call_Void_ResponseCode_StringList<XSceneElementManagerCallback>(env, jCallback, jResponseCode, jSceneElementIDs, &XSceneElementManagerCallback::GetAllSceneElementIDsReplyCB, __func__);
}

JNIEXPORT
jstring JNICALL Java_org_allseen_lsf_test_SceneElementManagerCallbackTest_getSceneElementNameReplyCB(JNIEnv *env, jobject thiz, jobject jCallback, jobject jResponseCode, jstring jSceneElementID, jstring jLanguage, jstring jSceneElementName)
{
    return XCppTestDelegator::Call_Void_ResponseCode_String_String_String<XSceneElementManagerCallback>(env, jCallback, jResponseCode, jSceneElementID, jLanguage, jSceneElementName, &XSceneElementManagerCallback::GetSceneElementNameReplyCB, __func__);
}

JNIEXPORT
jstring JNICALL Java_org_allseen_lsf_test_SceneElementManagerCallbackTest_setSceneElementNameReplyCB(JNIEnv *env, jobject thiz, jobject jCallback, jobject jResponseCode, jstring jSceneElementID, jstring jLanguage)
{
    return XCppTestDelegator::Call_Void_ResponseCode_String_String<XSceneElementManagerCallback>(env, jCallback, jResponseCode, jSceneElementID, jLanguage, &XSceneElementManagerCallback::SetSceneElementNameReplyCB, __func__);
}


JNIEXPORT
jstring JNICALL Java_org_allseen_lsf_test_SceneElementManagerCallbackTest_getSceneElementReplyCB(JNIEnv *env, jobject thiz, jobject jCallback, jobject jResponseCode, jstring jSceneElementID, jobject jSceneElement)
{
    return XCppTestDelegator::Call_Void_ResponseCode_String_Object<XSceneElementManagerCallback, XSceneElement, SceneElement>(env, jCallback, jResponseCode, jSceneElementID, jSceneElement, &XSceneElementManagerCallback::GetSceneElementReplyCB, __func__);
}

JNIEXPORT
jstring JNICALL Java_org_allseen_lsf_test_SceneElementManagerCallbackTest_updateSceneElementReplyCB(JNIEnv *env, jobject thiz, jobject jCallback, jobject jResponseCode, jstring jSceneElementID)
{
    return XCppTestDelegator::Call_Void_ResponseCode_String<XSceneElementManagerCallback>(env, jCallback, jResponseCode, jSceneElementID, &XSceneElementManagerCallback::UpdateSceneElementReplyCB, __func__);
}

JNIEXPORT
jstring JNICALL Java_org_allseen_lsf_test_SceneElementManagerCallbackTest_deleteSceneElementReplyCB(JNIEnv *env, jobject thiz, jobject jCallback, jobject jResponseCode, jstring jSceneElementID)
{
    return XCppTestDelegator::Call_Void_ResponseCode_String<XSceneElementManagerCallback>(env, jCallback, jResponseCode, jSceneElementID, &XSceneElementManagerCallback::DeleteSceneElementReplyCB, __func__);
}

JNIEXPORT
jstring JNICALL Java_org_allseen_lsf_test_SceneElementManagerCallbackTest_applySceneElementReplyCB(JNIEnv *env, jobject thiz, jobject jCallback, jobject jResponseCode, jstring jSceneElementID)
{
    return XCppTestDelegator::Call_Void_ResponseCode_String<XSceneElementManagerCallback>(env, jCallback, jResponseCode, jSceneElementID, &XSceneElementManagerCallback::ApplySceneElementReplyCB, __func__);
}

JNIEXPORT
jstring JNICALL Java_org_allseen_lsf_test_SceneElementManagerCallbackTest_sceneElementsNameChangedCB(JNIEnv *env, jobject thiz, jobject jCallback, jobjectArray jSceneElementIDs)
{
    return XCppTestDelegator::Call_Void_StringList<XSceneElementManagerCallback>(env, jCallback, jSceneElementIDs, &XSceneElementManagerCallback::SceneElementsNameChangedCB, __func__);
}

JNIEXPORT
jstring JNICALL Java_org_allseen_lsf_test_SceneElementManagerCallbackTest_sceneElementsCreatedCB(JNIEnv *env, jobject thiz, jobject jCallback, jobjectArray jSceneElementIDs)
{
    return XCppTestDelegator::Call_Void_StringList<XSceneElementManagerCallback>(env, jCallback, jSceneElementIDs, &XSceneElementManagerCallback::SceneElementsCreatedCB, __func__);
}

JNIEXPORT
jstring JNICALL Java_org_allseen_lsf_test_SceneElementManagerCallbackTest_sceneElementsUpdatedCB(JNIEnv *env, jobject thiz, jobject jCallback, jobjectArray jSceneElementIDs)
{
    return XCppTestDelegator::Call_Void_StringList<XSceneElementManagerCallback>(env, jCallback, jSceneElementIDs, &XSceneElementManagerCallback::SceneElementsUpdatedCB, __func__);
}

JNIEXPORT
jstring JNICALL Java_org_allseen_lsf_test_SceneElementManagerCallbackTest_sceneElementsDeletedCB(JNIEnv *env, jobject thiz, jobject jCallback, jobjectArray jSceneElementIDs)
{
    return XCppTestDelegator::Call_Void_StringList<XSceneElementManagerCallback>(env, jCallback, jSceneElementIDs, &XSceneElementManagerCallback::SceneElementsDeletedCB, __func__);
}

JNIEXPORT
jstring JNICALL Java_org_allseen_lsf_test_SceneElementManagerCallbackTest_sceneElementsAppliedCB(JNIEnv *env, jobject thiz, jobject jCallback, jobjectArray jSceneElementIDs)
{
    return XCppTestDelegator::Call_Void_StringList<XSceneElementManagerCallback>(env, jCallback, jSceneElementIDs, &XSceneElementManagerCallback::SceneElementsAppliedCB, __func__);
}

JNIEXPORT
jstring JNICALL Java_org_allseen_lsf_test_SceneElementManagerCallbackTest_createSceneElementReplyCB(JNIEnv *env, jobject thiz, jobject jCallback, jobject jResponseCode, jstring jSceneElementID, jlong jTrackingID)
{
    return XCppTestDelegator::Call_Void_ResponseCode_String_UInt32<XSceneElementManagerCallback>(env, jCallback, jResponseCode, jSceneElementID, jTrackingID, &XSceneElementManagerCallback::CreateSceneElementReplyCB, __func__);
}

