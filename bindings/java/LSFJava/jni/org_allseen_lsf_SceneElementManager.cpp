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

#include <qcc/Debug.h>

#include "JControllerClient.h"
#include "NUtil.h"
#include "XCppDelegator.h"
#include "XSceneElement.h"
#include "XSceneElementManager.h"
#include "XSceneElementManagerCallback.h"

#include "org_allseen_lsf_SceneElementManager.h"

#define QCC_MODULE "AJN-LSF-JNI"

using namespace ajn;
using namespace lsf;

JNIEXPORT jobject JNICALL Java_org_allseen_lsf_SceneElementManager_getAllSceneElementIDs(JNIEnv *env, jobject thiz)
{
    return XCppDelegator::Call_ControllerClientStatus<XSceneElementManager>(env, thiz, &XSceneElementManager::GetAllSceneElementIDs);
}

JNIEXPORT jobject JNICALL Java_org_allseen_lsf_SceneElementManager_getSceneElementName(JNIEnv *env, jobject thiz, jstring jSceneElementID, jstring jLanguage)
{
    return XCppDelegator::Call_ControllerClientStatus_String_String<XSceneElementManager>(env, thiz, jSceneElementID, jLanguage, &XSceneElementManager::GetSceneElementName);
}

JNIEXPORT jobject JNICALL Java_org_allseen_lsf_SceneElementManager_setSceneElementName(JNIEnv *env, jobject thiz, jstring jSceneElementID, jstring jSceneElementName, jstring jLanguage)
{
    return XCppDelegator::Call_ControllerClientStatus_String_String_String<XSceneElementManager>(env, thiz, jSceneElementID, jSceneElementName, jLanguage, &XSceneElementManager::SetSceneElementName);
}

JNIEXPORT jobject JNICALL Java_org_allseen_lsf_SceneElementManager_createSceneElement(JNIEnv *env, jobject thiz, jobject jTrackingID, jobject jSceneElement, jstring jSceneElementName, jstring jLanguage)
{
    return XCppDelegator::Call_ControllerClientStatus_TrackingID_Object_String_String<XSceneElementManager, XSceneElement, SceneElement>(env, thiz, jTrackingID, jSceneElement, jSceneElementName, jLanguage, &XSceneElementManager::CreateSceneElement);
}

JNIEXPORT jobject JNICALL Java_org_allseen_lsf_SceneElementManager_updateSceneElement(JNIEnv *env, jobject thiz, jstring jSceneElementID, jobject jSceneElement)
{
    return XCppDelegator::Call_ControllerClientStatus_String_Object<XSceneElementManager, XSceneElement, SceneElement>(env, thiz, jSceneElementID, jSceneElement, &XSceneElementManager::UpdateSceneElement);
}

JNIEXPORT jobject JNICALL Java_org_allseen_lsf_SceneElementManager_deleteSceneElement(JNIEnv *env, jobject thiz, jstring jSceneElementID)
{
    return XCppDelegator::Call_ControllerClientStatus_String<XSceneElementManager>(env, thiz, jSceneElementID, &XSceneElementManager::DeleteSceneElement);
}

JNIEXPORT jobject JNICALL Java_org_allseen_lsf_SceneElementManager_getSceneElement(JNIEnv *env, jobject thiz, jstring jSceneElementID)
{
    return XCppDelegator::Call_ControllerClientStatus_String<XSceneElementManager>(env, thiz, jSceneElementID, &XSceneElementManager::GetSceneElement);
}

JNIEXPORT jobject JNICALL Java_org_allseen_lsf_SceneElementManager_applySceneElement(JNIEnv *env, jobject thiz, jstring jSceneElementID)
{
    return XCppDelegator::Call_ControllerClientStatus_String<XSceneElementManager>(env, thiz, jSceneElementID, &XSceneElementManager::ApplySceneElement);
}

JNIEXPORT jobject JNICALL Java_org_allseen_lsf_SceneElementManager_getSceneElementDataSet(JNIEnv *env, jobject thiz, jstring jSceneElementID, jstring jLanguage)
{
    return XCppDelegator::Call_ControllerClientStatus_String_String<XSceneElementManager>(env, thiz, jSceneElementID, jLanguage, &XSceneElementManager::GetSceneElementDataSet);
}

JNIEXPORT void JNICALL Java_org_allseen_lsf_SceneElementManager_createNativeObject(JNIEnv *env , jobject thiz, jobject jControllerClient, jobject jSceneElementManagerCallback)
{
    JControllerClient *xControllerClient = GetHandle<JControllerClient*>(jControllerClient);
    if (env->ExceptionCheck() || !xControllerClient) {
        QCC_LogError(ER_FAIL, ("GetHandle() failed"));
        return;
    }

    XSceneElementManagerCallback *xSceneElementManagerCallback = GetHandle<XSceneElementManagerCallback*>(jSceneElementManagerCallback);
    if (env->ExceptionCheck() || !xSceneElementManagerCallback) {
        QCC_LogError(ER_FAIL, ("GetHandle() failed"));
        return;
    }

    XSceneElementManager* xSceneElementManager = new XSceneElementManager(thiz, *xControllerClient, *xSceneElementManagerCallback);
    if (env->ExceptionCheck() || !xSceneElementManager) {
        QCC_LogError(ER_FAIL, ("JSavedStateManager() failed"));
        return;
    }

    CreateHandle<XSceneElementManager>(thiz, xSceneElementManager);
}

JNIEXPORT void JNICALL Java_org_allseen_lsf_SceneElementManager_destroyNativeObject(JNIEnv *env, jobject thiz)
{
    DestroyHandle<XSceneElementManager>(thiz);
}