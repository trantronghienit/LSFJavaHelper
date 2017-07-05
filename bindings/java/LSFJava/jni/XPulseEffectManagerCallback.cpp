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

#include "JEnum.h"
#include "XJavaDelegator.h"
#include "XPulseEffectV2.h"
#include "XPulseEffectManagerCallback.h"

#define QCC_MODULE "AJN-LSF-JNI"

using namespace ajn;

namespace lsf {

XPulseEffectManagerCallback::XPulseEffectManagerCallback(jobject jobj)
{
    // Get the JNIEnv for the current native thread
    JScopedEnv env;

    /*
     * Be careful when using a weak global reference.  They can only be
     * passed to NewLocalRef, NewGlobalRef and DeleteWeakGlobalRef.
     */
    QCC_DbgPrintf(("Taking weak global reference to Java object %p", jobj));
    jdelegate = env->NewWeakGlobalRef(jobj);
    if (env->ExceptionCheck() || !jdelegate) {
        QCC_LogError(ER_FAIL, ("NewWeakGlobalRef() failed"));
        return;
    }
}

XPulseEffectManagerCallback::~XPulseEffectManagerCallback()
{
    // Get the JNIEnv for the current native thread
    JScopedEnv env;

    env->DeleteWeakGlobalRef(jdelegate);
    if (env->ExceptionCheck()) {
        QCC_LogError(ER_FAIL, ("DeleteWeakGlobalRef() failed"));
        return;
    }
}

void XPulseEffectManagerCallback::GetAllPulseEffectIDsReplyCB(const LSFResponseCode& responseCode, const LSFStringList& pulseEffectIDs)
{
    XJavaDelegator::Call_Void_ResponseCode_StringList(jdelegate, __func__, responseCode, pulseEffectIDs);
}

void XPulseEffectManagerCallback::ApplyPulseEffectOnLampsReplyCB(const LSFResponseCode& responseCode, const LSFString& pulseEffectID, const LSFStringList& lampIDs)
{
    XJavaDelegator::Call_Void_ResponseCode_String_StringList(jdelegate, __func__, responseCode, pulseEffectID, lampIDs);
}

void XPulseEffectManagerCallback::ApplyPulseEffectOnLampGroupsReplyCB(const LSFResponseCode& responseCode, const LSFString& pulseEffectID, const LSFStringList& lampGroupIDs)
{
    XJavaDelegator::Call_Void_ResponseCode_String_StringList(jdelegate, __func__, responseCode, pulseEffectID, lampGroupIDs);
}

void XPulseEffectManagerCallback::GetPulseEffectNameReplyCB(const LSFResponseCode& responseCode, const LSFString& pulseEffectID, const LSFString& language, const LSFString& pulseEffectName)
{
    XJavaDelegator::Call_Void_ResponseCode_String_String_String(jdelegate, __func__, responseCode, pulseEffectID, language, pulseEffectName);
}

void XPulseEffectManagerCallback::SetPulseEffectNameReplyCB(const LSFResponseCode& responseCode, const LSFString& pulseEffectID, const LSFString& language)
{
    XJavaDelegator::Call_Void_ResponseCode_String_String(jdelegate, __func__, responseCode, pulseEffectID, language);
}

void XPulseEffectManagerCallback::PulseEffectsNameChangedCB(const LSFStringList& pulseEffectIDs)
{
    XJavaDelegator::Call_Void_StringList(jdelegate, __func__, pulseEffectIDs);
}

void XPulseEffectManagerCallback::CreatePulseEffectReplyCB(const LSFResponseCode& responseCode, const LSFString& pulseEffectID, const uint32_t& trackingID)
{
    XJavaDelegator::Call_Void_ResponseCode_String_UInt32(jdelegate, __func__, responseCode, pulseEffectID, trackingID);
}

void XPulseEffectManagerCallback::PulseEffectsCreatedCB(const LSFStringList& pulseEffectIDs)
{
    XJavaDelegator::Call_Void_StringList(jdelegate, __func__, pulseEffectIDs);
}

void XPulseEffectManagerCallback::UpdatePulseEffectReplyCB(const LSFResponseCode& responseCode, const LSFString& pulseEffectID)
{
    XJavaDelegator::Call_Void_ResponseCode_String(jdelegate, __func__, responseCode, pulseEffectID);
}

void XPulseEffectManagerCallback::PulseEffectsUpdatedCB(const LSFStringList& pulseEffectIDs)
{
    XJavaDelegator::Call_Void_StringList(jdelegate, __func__, pulseEffectIDs);
}

void XPulseEffectManagerCallback::DeletePulseEffectReplyCB(const LSFResponseCode& responseCode, const LSFString& pulseEffectID)
{
    XJavaDelegator::Call_Void_ResponseCode_String(jdelegate, __func__, responseCode, pulseEffectID);
}

void XPulseEffectManagerCallback::PulseEffectsDeletedCB(const LSFStringList& pulseEffectIDs)
{
    XJavaDelegator::Call_Void_StringList(jdelegate, __func__, pulseEffectIDs);
}

void XPulseEffectManagerCallback::GetPulseEffectReplyCB(const LSFResponseCode& responseCode, const LSFString& pulseEffectID, const PulseEffect& pulseEffect)
{
    XJavaDelegator::Call_Void_ResponseCode_String_Object<PulseEffect, XPulseEffectV2>(jdelegate, __func__, XClass::xPulseEffectV2, responseCode, pulseEffectID, pulseEffect);
}

} /* namespace lsf */