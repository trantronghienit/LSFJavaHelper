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

#include "XTrackingID.h"
#include "NUtil.h"

#define QCC_MODULE "AJN-LSF-JNI"

namespace lsf {

jobject XTrackingID::NewTrackingID()
{
    JScopedEnv env;

    jmethodID jConstructor = env->GetMethodID(XClass::xTrackingID->classRef, "<init>", "()V");
    if (env->ExceptionCheck() || !jConstructor) {
        QCC_LogError(ER_FAIL, ("GetMethodID() failed"));
        return NULL;
    }

    jobject jTrackingID = env->NewObject(XClass::xTrackingID->classRef, jConstructor);
    if (env->ExceptionCheck() || !jTrackingID) {
        QCC_LogError(ER_FAIL, ("NewObject() failed"));
        return NULL;
    }

    return jTrackingID;
}

jobject XTrackingID::SetTrackingID(jobject jTrackingID, const uint32_t& cTrackingID)
{
    JScopedEnv env;

    jfieldID jFieldID = env->GetFieldID(XClass::xTrackingID->classRef, "value", "J");
    if (env->ExceptionCheck() || !jFieldID) {
        QCC_LogError(ER_FAIL, ("GetFieldID() failed"));
        return NULL;
    }

    env->SetLongField(jTrackingID, jFieldID, (jlong)cTrackingID);

    return jTrackingID;
}

} /* namespace lsf */

#undef QCC_MODULE