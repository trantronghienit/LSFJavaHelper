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
#include <qcc/Log.h>

#include "JLampParameters.h"

#include "NUtil.h"

#include "org_allseen_lsf_sdk_LampParameters.h"

#define QCC_MODULE "AJN-LSF-JNI"

using namespace lsf;

JNIEXPORT
jint JNICALL Java_org_allseen_lsf_sdk_LampParameters_getEnergyUsageMilliwatts(JNIEnv *env, jobject thiz)
{
    JLampParameters* xLampParams = GetHandle<JLampParameters*>(thiz);
    if (env->ExceptionCheck() || !xLampParams) {
        QCC_LogError(ER_FAIL, ("GetHandle() failed"));
        return 0;
    }

    return (jint)xLampParams->energyUsageMilliwatts;
}

JNIEXPORT
jint JNICALL Java_org_allseen_lsf_sdk_LampParameters_getLumens(JNIEnv *env, jobject thiz)
{
    JLampParameters* xLampParams = GetHandle<JLampParameters*>(thiz);
    if (env->ExceptionCheck() || !xLampParams) {
        QCC_LogError(ER_FAIL, ("GetHandle() failed"));
        return 0;
    }

    return (jint)xLampParams->lumens;
}

JNIEXPORT
void JNICALL Java_org_allseen_lsf_sdk_LampParameters_createNativeObject(JNIEnv *env, jobject thiz)
{
    CreateHandle<JLampParameters>(thiz);
}

JNIEXPORT
void JNICALL Java_org_allseen_lsf_sdk_LampParameters_destroyNativeObject(JNIEnv *env, jobject thiz)
{
    DestroyHandle<JLampParameters>(thiz);
}