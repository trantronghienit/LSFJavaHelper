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

#include "JLampState.h"
#include "NUtil.h"
#include "XTransitionEffectV2.h"
#include "XTransitionEffect.h"

#include "org_allseen_lsf_TransitionEffectV2.h"

#define QCC_MODULE "AJN-LSF-JNI"

using namespace ajn;
using namespace lsf;

JNIEXPORT
void JNICALL Java_org_allseen_lsf_TransitionEffectV2_setLampState(JNIEnv *env, jobject thiz, jobject jLampState)
{
    XTransitionEffectV2 *xTransitionEffectV2 = GetHandle<XTransitionEffectV2*>(thiz);
    if (env->ExceptionCheck() || !xTransitionEffectV2) {
        QCC_LogError(ER_FAIL, ("GetHandle() failed"));
        return;
    }

    JLampState::CopyLampState(jLampState, xTransitionEffectV2->state);
}

JNIEXPORT
jobject JNICALL Java_org_allseen_lsf_TransitionEffectV2_getLampState(JNIEnv *env, jobject thiz)
{
    XTransitionEffectV2 *xTransitionEffectV2 = GetHandle<XTransitionEffectV2*>(thiz);
    if (env->ExceptionCheck() || !xTransitionEffectV2) {
        QCC_LogError(ER_FAIL, ("GetHandle() failed"));
        return NULL;
    }

    return JLampState::NewLampState(xTransitionEffectV2->state);
}

JNIEXPORT
void JNICALL Java_org_allseen_lsf_TransitionEffectV2_setPresetID(JNIEnv *env, jobject thiz, jstring jPresetID)
{
    XTransitionEffectV2 *xTransitionEffectV2 = GetHandle<XTransitionEffectV2*>(thiz);
    if (env->ExceptionCheck() || !xTransitionEffectV2) {
        QCC_LogError(ER_FAIL, ("GetHandle() failed"));
        return;
    }

    JString xPresetID(jPresetID);
    if (env->ExceptionCheck()) {
        QCC_LogError(ER_FAIL, ("JString failed"));
        return;
    }

    if (!xPresetID.c_str()) {
        QCC_LogError(ER_FAIL, ("JString invalid"));
        return;
    }

    xTransitionEffectV2->presetID = xPresetID.c_str();
}

JNIEXPORT
jstring JNICALL Java_org_allseen_lsf_TransitionEffectV2_getPresetID(JNIEnv *env, jobject thiz)
{
    XTransitionEffectV2 *xTransitionEffectV2 = GetHandle<XTransitionEffectV2*>(thiz);
    if (env->ExceptionCheck() || !xTransitionEffectV2) {
        QCC_LogError(ER_FAIL, ("GetHandle() failed"));
        return NULL;
    }

    jstring jPresetID = env->NewStringUTF(xTransitionEffectV2->presetID.c_str());
    if (env->ExceptionCheck() || !jPresetID) {
        QCC_LogError(ER_FAIL, ("NewStringUTF() failed"));
        return NULL;
    }

    return jPresetID;
}

JNIEXPORT
void JNICALL Java_org_allseen_lsf_TransitionEffectV2_setTransitionPeriod(JNIEnv *env, jobject thiz, jlong jTransitionPeriod)
{
    XTransitionEffect::SetTransitionPeriod<XTransitionEffectV2>(env, thiz, jTransitionPeriod);
}

JNIEXPORT
jlong JNICALL Java_org_allseen_lsf_TransitionEffectV2_getTransitionPeriod(JNIEnv *env, jobject thiz)
{
    return XTransitionEffect::GetTransitionPeriod<XTransitionEffectV2>(env, thiz);
}

JNIEXPORT
void JNICALL Java_org_allseen_lsf_TransitionEffectV2_createNativeObject(JNIEnv *env, jobject thiz)
{
    CreateHandle<XTransitionEffectV2>(thiz);
}

JNIEXPORT
void JNICALL Java_org_allseen_lsf_TransitionEffectV2_destroyNativeObject(JNIEnv *env, jobject thiz)
{
    DestroyHandle<XTransitionEffectV2>(thiz);
}
