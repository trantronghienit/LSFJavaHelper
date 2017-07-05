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
#include "XPulseEffectV2.h"
#include "XPulseEffectManager.h"
#include "XPulseEffectManagerCallback.h"

#include "org_allseen_lsf_PulseEffectManager.h"

#define QCC_MODULE "AJN-LSF-JNI"

using namespace ajn;
using namespace lsf;

JNIEXPORT jobject JNICALL Java_org_allseen_lsf_PulseEffectManager_getAllPulseEffectIDs(JNIEnv *env, jobject thiz)
{
    return XCppDelegator::Call_ControllerClientStatus<XPulseEffectManager>(env, thiz, &XPulseEffectManager::GetAllPulseEffectIDs);
}

JNIEXPORT jobject JNICALL Java_org_allseen_lsf_PulseEffectManager_getPulseEffect(JNIEnv *env, jobject thiz, jstring jPulseEffectID)
{
    return XCppDelegator::Call_ControllerClientStatus_String<XPulseEffectManager>(env, thiz, jPulseEffectID, &XPulseEffectManager::GetPulseEffect);
}

JNIEXPORT jobject JNICALL Java_org_allseen_lsf_PulseEffectManager_applyPulseEffectOnLamps(JNIEnv *env, jobject thiz, jstring jPulseEffectID, jobjectArray jLampIDs)
{
    return XCppDelegator::Call_ControllerClientStatus_String_StringList<XPulseEffectManager>(env, thiz, jPulseEffectID, jLampIDs, &XPulseEffectManager::ApplyPulseEffectOnLamps);
}

JNIEXPORT jobject JNICALL Java_org_allseen_lsf_PulseEffectManager_applyPulseEffectOnLampGroups(JNIEnv *env, jobject thiz, jstring jPulseEffectID, jobjectArray jGroupIDs)
{
    return XCppDelegator::Call_ControllerClientStatus_String_StringList<XPulseEffectManager>(env, thiz, jPulseEffectID, jGroupIDs, &XPulseEffectManager::ApplyPulseEffectOnLampGroups);
}

JNIEXPORT jobject JNICALL Java_org_allseen_lsf_PulseEffectManager_getPulseEffectName(JNIEnv *env, jobject thiz, jstring jPulseEffectID, jstring jLanguage)
{
    return XCppDelegator::Call_ControllerClientStatus_String_String<XPulseEffectManager>(env, thiz, jPulseEffectID, jLanguage, &XPulseEffectManager::GetPulseEffectName);
}

JNIEXPORT jobject JNICALL Java_org_allseen_lsf_PulseEffectManager_setPulseEffectName(JNIEnv *env, jobject thiz, jstring jPulseEffectID, jstring jPulseEffectName, jstring jLanguage)
{
    return XCppDelegator::Call_ControllerClientStatus_String_String_String<XPulseEffectManager>(env, thiz, jPulseEffectID, jPulseEffectName, jLanguage, &XPulseEffectManager::SetPulseEffectName);
}

JNIEXPORT jobject JNICALL Java_org_allseen_lsf_PulseEffectManager_createPulseEffect(JNIEnv *env, jobject thiz, jobject jTrackingID, jobject jPulseEffect, jstring jPulseEffectName, jstring jLanguage)
{
    return XCppDelegator::Call_ControllerClientStatus_TrackingID_Object_String_String<XPulseEffectManager, XPulseEffectV2, PulseEffect>(env, thiz, jTrackingID, jPulseEffect, jPulseEffectName, jLanguage, &XPulseEffectManager::CreatePulseEffect);
}

JNIEXPORT jobject JNICALL Java_org_allseen_lsf_PulseEffectManager_updatePulseEffect(JNIEnv *env, jobject thiz, jstring jPulseEffectID, jobject jPulseEffect)
{
    return XCppDelegator::Call_ControllerClientStatus_String_Object<XPulseEffectManager, XPulseEffectV2, PulseEffect>(env, thiz, jPulseEffectID, jPulseEffect, &XPulseEffectManager::UpdatePulseEffect);
}

JNIEXPORT jobject JNICALL Java_org_allseen_lsf_PulseEffectManager_deletePulseEffect(JNIEnv *env, jobject thiz, jstring jPulseEffectID)
{
    return XCppDelegator::Call_ControllerClientStatus_String<XPulseEffectManager>(env, thiz, jPulseEffectID, &XPulseEffectManager::DeletePulseEffect);
}

JNIEXPORT jobject JNICALL Java_org_allseen_lsf_PulseEffectManager_getPulseEffectDataSet(JNIEnv *env, jobject thiz, jstring jPulseEffectID, jstring jLanguage)
{
    return XCppDelegator::Call_ControllerClientStatus_String_String<XPulseEffectManager>(env, thiz, jPulseEffectID, jLanguage, &XPulseEffectManager::GetPulseEffectDataSet);
}

JNIEXPORT void JNICALL Java_org_allseen_lsf_PulseEffectManager_createNativeObject(JNIEnv *env, jobject thiz, jobject jControllerClient, jobject jPulseEffectManagerCallback)
{
    JControllerClient *xControllerClient = GetHandle<JControllerClient*>(jControllerClient);
    if (env->ExceptionCheck() || !xControllerClient) {
        QCC_LogError(ER_FAIL, ("GetHandle() failed"));
        return;
    }

    XPulseEffectManagerCallback *xPulseEffectManagerCallback = GetHandle<XPulseEffectManagerCallback*>(jPulseEffectManagerCallback);
    if (env->ExceptionCheck() || !xPulseEffectManagerCallback) {
        QCC_LogError(ER_FAIL, ("GetHandle() failed"));
        return;
    }

    XPulseEffectManager* xPulseEffectManager = new XPulseEffectManager(thiz, *xControllerClient, *xPulseEffectManagerCallback);
    if (env->ExceptionCheck() || !xPulseEffectManager) {
        QCC_LogError(ER_FAIL, ("JSavedStateManager() failed"));
        return;
    }

    CreateHandle<XPulseEffectManager>(thiz, xPulseEffectManager);
}

JNIEXPORT void JNICALL Java_org_allseen_lsf_PulseEffectManager_destroyNativeObject(JNIEnv *env, jobject thiz)
{
    DestroyHandle<XPulseEffectManager>(thiz);
}