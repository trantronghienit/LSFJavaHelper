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
#include "XTransitionEffectV2.h"
#include "XTransitionEffectManager.h"
#include "XTransitionEffectManagerCallback.h"

#include "org_allseen_lsf_TransitionEffectManager.h"

#define QCC_MODULE "AJN-LSF-JNI"

using namespace ajn;
using namespace lsf;

JNIEXPORT jobject JNICALL Java_org_allseen_lsf_TransitionEffectManager_getAllTransitionEffectIDs(JNIEnv *env, jobject thiz)
{
    return XCppDelegator::Call_ControllerClientStatus<XTransitionEffectManager>(env, thiz, &XTransitionEffectManager::GetAllTransitionEffectIDs);
}

JNIEXPORT jobject JNICALL Java_org_allseen_lsf_TransitionEffectManager_getTransitionEffect(JNIEnv *env, jobject thiz, jstring jTransitionEffectID)
{
    return XCppDelegator::Call_ControllerClientStatus_String<XTransitionEffectManager>(env, thiz, jTransitionEffectID, &XTransitionEffectManager::GetTransitionEffect);
}

JNIEXPORT jobject JNICALL Java_org_allseen_lsf_TransitionEffectManager_applyTransitionEffectOnLamps(JNIEnv *env, jobject thiz, jstring jTransitionEffectID, jobjectArray jLampIDs)
{
    return XCppDelegator::Call_ControllerClientStatus_String_StringList<XTransitionEffectManager>(env, thiz, jTransitionEffectID, jLampIDs, &XTransitionEffectManager::ApplyTransitionEffectOnLamps);
}

JNIEXPORT jobject JNICALL Java_org_allseen_lsf_TransitionEffectManager_applyTransitionEffectOnLampGroups(JNIEnv *env, jobject thiz, jstring jTransitionEffectID, jobjectArray jGroupIDs)
{
    return XCppDelegator::Call_ControllerClientStatus_String_StringList<XTransitionEffectManager>(env, thiz, jTransitionEffectID, jGroupIDs, &XTransitionEffectManager::ApplyTransitionEffectOnLampGroups);
}

JNIEXPORT jobject JNICALL Java_org_allseen_lsf_TransitionEffectManager_getTransitionEffectName(JNIEnv *env, jobject thiz, jstring jTransitionEffectID, jstring jLanguage)
{
    return XCppDelegator::Call_ControllerClientStatus_String_String<XTransitionEffectManager>(env, thiz, jTransitionEffectID, jLanguage, &XTransitionEffectManager::GetTransitionEffectName);
}

JNIEXPORT jobject JNICALL Java_org_allseen_lsf_TransitionEffectManager_setTransitionEffectName(JNIEnv *env, jobject thiz, jstring jTransitionEffectID, jstring jTransitionEffectName, jstring jLanguage)
{
    return XCppDelegator::Call_ControllerClientStatus_String_String_String<XTransitionEffectManager>(env, thiz, jTransitionEffectID, jTransitionEffectName, jLanguage, &XTransitionEffectManager::SetTransitionEffectName);
}

JNIEXPORT jobject JNICALL Java_org_allseen_lsf_TransitionEffectManager_createTransitionEffect(JNIEnv *env, jobject thiz, jobject jTrackingID, jobject jTransitionEffect, jstring jTransitionEffectName, jstring jLanguage)
{
    return XCppDelegator::Call_ControllerClientStatus_TrackingID_Object_String_String<XTransitionEffectManager, XTransitionEffectV2, TransitionEffect>(env, thiz, jTrackingID, jTransitionEffect, jTransitionEffectName, jLanguage, &XTransitionEffectManager::CreateTransitionEffect);
}

JNIEXPORT jobject JNICALL Java_org_allseen_lsf_TransitionEffectManager_updateTransitionEffect(JNIEnv *env, jobject thiz, jstring jTransitionEffectID, jobject jTransitionEffect)
{
    return XCppDelegator::Call_ControllerClientStatus_String_Object<XTransitionEffectManager, XTransitionEffectV2, TransitionEffect>(env, thiz, jTransitionEffectID, jTransitionEffect, &XTransitionEffectManager::UpdateTransitionEffect);
}

JNIEXPORT jobject JNICALL Java_org_allseen_lsf_TransitionEffectManager_deleteTransitionEffect(JNIEnv *env, jobject thiz, jstring jTransitionEffectID)
{
    return XCppDelegator::Call_ControllerClientStatus_String<XTransitionEffectManager>(env, thiz, jTransitionEffectID, &XTransitionEffectManager::DeleteTransitionEffect);
}

JNIEXPORT jobject JNICALL Java_org_allseen_lsf_TransitionEffectManager_getTransitionEffectDataSet(JNIEnv *env, jobject thiz, jstring jTransitionEffectID, jstring jLanguage)
{
    return XCppDelegator::Call_ControllerClientStatus_String_String<XTransitionEffectManager>(env, thiz, jTransitionEffectID, jLanguage, &XTransitionEffectManager::GetTransitionEffectDataSet);
}

JNIEXPORT void JNICALL Java_org_allseen_lsf_TransitionEffectManager_createNativeObject(JNIEnv *env, jobject thiz, jobject jControllerClient, jobject jTransitionEffectManagerCallback)
{
    JControllerClient *xControllerClient = GetHandle<JControllerClient*>(jControllerClient);
    if (env->ExceptionCheck() || !xControllerClient) {
        QCC_LogError(ER_FAIL, ("GetHandle() failed"));
        return;
    }

    XTransitionEffectManagerCallback *xTransitionEffectManagerCallback = GetHandle<XTransitionEffectManagerCallback*>(jTransitionEffectManagerCallback);
    if (env->ExceptionCheck() || !xTransitionEffectManagerCallback) {
        QCC_LogError(ER_FAIL, ("GetHandle() failed"));
        return;
    }

    XTransitionEffectManager* xTransitionEffectManager = new XTransitionEffectManager(thiz, *xControllerClient, *xTransitionEffectManagerCallback);
    if (env->ExceptionCheck() || !xTransitionEffectManager) {
        QCC_LogError(ER_FAIL, ("JSavedStateManager() failed"));
        return;
    }

    CreateHandle<XTransitionEffectManager>(thiz, xTransitionEffectManager);
}

JNIEXPORT void JNICALL Java_org_allseen_lsf_TransitionEffectManager_destroyNativeObject(JNIEnv *env, jobject thiz)
{
    DestroyHandle<XTransitionEffectManager>(thiz);
}