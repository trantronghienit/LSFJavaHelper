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

#include <jni.h>

#include <qcc/Debug.h>

#include <alljoyn/AboutData.h>

#include "NDefs.h"
#include "NUtil.h"

#include "org_allseen_lsf_sdk_AboutData.h"

#define QCC_MODULE LSF_QCC_MODULE

using namespace ajn;

#ifdef __cplusplus
extern "C" {
#endif

JNIEXPORT
jstring JNICALL Java_org_allseen_lsf_sdk_AboutData_getString(JNIEnv *env, jobject thiz, jlong jNativeAboutDataHandle, jstring jKey, jstring jLanguage)
{
    JString xKey(jKey);
    if (env->ExceptionCheck()) {
        QCC_LogError(ER_FAIL, ("JString failed"));
        return NULL;
    }

    JString xLanguage(jLanguage);
    if (env->ExceptionCheck()) {
        QCC_LogError(ER_FAIL, ("JString failed"));
        return NULL;
    }

    MsgArg *cVariant = NULL;
    QStatus status = ((AboutData *)jNativeAboutDataHandle)->GetField(xKey.c_str(), cVariant, xLanguage.c_str());
    if (status != ER_OK || cVariant == NULL) {
        QCC_LogError(status, ("GetField() failed"));
        return NULL;
    }

    char *cValue = NULL;
    status = cVariant->Get("s", &cValue);
    if (status != ER_OK || cValue == NULL) {
        QCC_LogError(status, ("GetField() failed"));
        return NULL;
    }

    jstring jValue = env->NewStringUTF(cValue);
    if (env->ExceptionCheck() || !jValue) {
        QCC_LogError(ER_FAIL, ("NewStringUTF() failed"));
        return NULL;
    }

    return jValue;
}

JNIEXPORT
void JNICALL Java_org_allseen_lsf_sdk_AboutData_put(JNIEnv *env, jobject thiz, jlong jNativeAboutDataHandle, jstring jKey, jobject jVariant, jstring jLanguage)
{
    JString xKey(jKey);
    if (env->ExceptionCheck()) {
        QCC_LogError(ER_FAIL, ("JString failed"));
        return;
    }

    jmethodID jMarshal = env->GetStaticMethodID(XClass::xMsgArg->classRef, "marshal", "(JLjava/lang/String;Ljava/lang/Object;)V");
    if (env->ExceptionCheck() || !jMarshal) {
        QCC_LogError(ER_FAIL, ("GetStaticMethodID() failed"));
        return;
    }

    jstring jSignature = env->NewStringUTF("v");
    if (env->ExceptionCheck() || !jSignature) {
        QCC_LogError(ER_FAIL, ("NewStringUTF() failed"));
        return;
    }

    MsgArg cVariant;
    env->CallStaticVoidMethod(XClass::xMsgArg->classRef, jMarshal, (jlong)&cVariant, jSignature, jVariant);
    if (env->ExceptionCheck()) {
        QCC_LogError(ER_FAIL, ("CallStaticVoidMethod() failed"));
        return;
    }

//    QCC_LogError(ER_FAIL, ("cVariant sig: %s", cVariant.Signature().c_str()));

    MsgArg* cValue = NULL;
    cVariant.Get("v", &cValue);
    if (cValue == NULL) {
        QCC_LogError(ER_FAIL, ("Get variant failed"));
        return;
    }

//    QCC_LogError(ER_FAIL, ("cValue sig: %s", cValue->Signature().c_str()));

    JString xLanguage(jLanguage);
    if (env->ExceptionCheck()) {
        QCC_LogError(ER_FAIL, ("JString failed"));
        return;
    }

//    QCC_LogError(ER_FAIL, ("Calling SetField: %s, %s, %s", xKey.c_str(), cValue->ToString().c_str(), xLanguage.c_str()));
    ((AboutData *)jNativeAboutDataHandle)->SetField(xKey.c_str(), *cValue, xLanguage.c_str());
}

#ifdef __cplusplus
}
#endif

