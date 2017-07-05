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

#include "JControllerClientCallback.h"
#include "JEnumArray.h"
#include "JLampState.h"

#include "org_allseen_lsf_test_ControllerClientCallbackTest.h"

#define QCC_MODULE LSF_QCC_MODULE

using namespace ajn;
using namespace lsf;

jstring CallMethodVoidStringString(JNIEnv *env, jobject jcc, jstring jdid, jstring jname, void (JControllerClientCallback::*method)(const LSFString&, const LSFString&), char const *func)
{
    JControllerClientCallback* cobj = GetHandle<JControllerClientCallback*>(jcc);
    if (!cobj) {
        QCC_LogError(ER_FAIL, ("GetHandle() failed"));
        return NULL;
    }

    JString xdid(jdid);
    if (env->ExceptionCheck() || !xdid.c_str()) {
        QCC_LogError(ER_FAIL, ("JString() failed"));
        return NULL;
    }

    JString xname(jname);
    if (env->ExceptionCheck() || !xname.c_str()) {
        QCC_LogError(ER_FAIL, ("JString() failed"));
        return NULL;
    }

    LSFString cdid = xdid.c_str();
    LSFString cname = xname.c_str();

    (cobj->*method)(cdid, cname);
    if (env->ExceptionCheck()) {
        return NULL;
    }

    jstring jfunc = env->NewStringUTF(GetJavaNativeMethodNameFromFunc(func).c_str());
    if (env->ExceptionCheck() || !jfunc) {
        QCC_LogError(ER_FAIL, ("NewStringUTF() failed"));
        return NULL;
    }

    return jfunc;
}

jstring CallMethodVoidErrorCodeArr(JNIEnv *env, jobject jcc, jobjectArray jecl, void (JControllerClientCallback::*method)(const ErrorCodeList& errorCodeList), char const *func)
{
    JControllerClientCallback* cobj = GetHandle<JControllerClientCallback*>(jcc);
    if (!cobj) {
        QCC_LogError(ER_FAIL, ("GetHandle() failed"));
        return NULL;
    }

    ErrorCodeList cecl;
    JEnumArray::CopyEnumArray(jecl, *JEnum::jErrorCodeEnum, cecl);

    (cobj->*method)(cecl);
    if (env->ExceptionCheck()) {
        return NULL;
    }

    jstring jfunc = env->NewStringUTF(GetJavaNativeMethodNameFromFunc(func).c_str());
    if (env->ExceptionCheck() || !jfunc) {
        QCC_LogError(ER_FAIL, ("NewStringUTF() failed"));
        return NULL;
    }

    return jfunc;
}

JNIEXPORT
jstring JNICALL Java_org_allseen_lsf_test_ControllerClientCallbackTest_connectedToControllerServiceCB(JNIEnv *env, jobject thiz, jobject jcc, jstring jdid, jstring jname)
{
    return CallMethodVoidStringString(env, jcc, jdid, jname, &JControllerClientCallback::ConnectedToControllerServiceCB, __func__);
}

JNIEXPORT
jstring JNICALL Java_org_allseen_lsf_test_ControllerClientCallbackTest_connectToControllerServiceFailedCB(JNIEnv *env, jobject thiz, jobject jcc, jstring jdid, jstring jname)
{
    return CallMethodVoidStringString(env, jcc, jdid, jname, &JControllerClientCallback::ConnectToControllerServiceFailedCB, __func__);
}

JNIEXPORT
jstring JNICALL Java_org_allseen_lsf_test_ControllerClientCallbackTest_disconnectedFromControllerServiceCB(JNIEnv *env, jobject thiz, jobject jcc, jstring jdid, jstring jname)
{
    return CallMethodVoidStringString(env, jcc, jdid, jname, &JControllerClientCallback::DisconnectedFromControllerServiceCB, __func__);
}

JNIEXPORT
jstring JNICALL Java_org_allseen_lsf_test_ControllerClientCallbackTest_controllerClientErrorCB(JNIEnv *env, jobject thiz, jobject jcc, jobjectArray jecl)
{
    return CallMethodVoidErrorCodeArr(env, jcc, jecl, &JControllerClientCallback::ControllerClientErrorCB, __func__);
}

#undef QCC_MODULE