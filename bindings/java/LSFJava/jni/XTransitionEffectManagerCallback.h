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

#ifndef LSF_JNI_XTRANSITIONEFFECTMANAGERCALLBACK_H_
#define LSF_JNI_XTRANSITIONEFFECTMANAGERCALLBACK_H_

#include <list>

#include <jni.h>

#include <TransitionEffectManager.h>   // lighting/service_framework

#include "NDefs.h"
#include "NUtil.h"
#include "NTypes.h"

namespace lsf {

class XTransitionEffectManagerCallback : public TransitionEffectManagerCallback {
  public:
    XTransitionEffectManagerCallback(jobject jobj);
    virtual ~XTransitionEffectManagerCallback();

    virtual void GetTransitionEffectReplyCB(const LSFResponseCode& responseCode, const LSFString& transitionEffectID, const TransitionEffect& transitionEffect) LSF_OVERRIDE;
    virtual void ApplyTransitionEffectOnLampsReplyCB(const LSFResponseCode& responseCode, const LSFString& transitionEffectID, const LSFStringList& lampIDs) LSF_OVERRIDE;
    virtual void ApplyTransitionEffectOnLampGroupsReplyCB(const LSFResponseCode& responseCode, const LSFString& transitionEffectID, const LSFStringList& lampGroupIDs) LSF_OVERRIDE;
    virtual void GetAllTransitionEffectIDsReplyCB(const LSFResponseCode& responseCode, const LSFStringList& transitionEffectIDs) LSF_OVERRIDE;
    virtual void GetTransitionEffectNameReplyCB(const LSFResponseCode& responseCode, const LSFString& transitionEffectID, const LSFString& language, const LSFString& transitionEffectName) LSF_OVERRIDE;
    virtual void SetTransitionEffectNameReplyCB(const LSFResponseCode& responseCode, const LSFString& transitionEffectID, const LSFString& language) LSF_OVERRIDE;
    virtual void TransitionEffectsNameChangedCB(const LSFStringList& transitionEffectIDs) LSF_OVERRIDE;
    virtual void CreateTransitionEffectReplyCB(const LSFResponseCode& responseCode, const LSFString& transitionEffectID, const uint32_t& trackingID) LSF_OVERRIDE;
    virtual void TransitionEffectsCreatedCB(const LSFStringList& transitionEffectIDs) LSF_OVERRIDE;
    virtual void UpdateTransitionEffectReplyCB(const LSFResponseCode& responseCode, const LSFString& transitionEffectID) LSF_OVERRIDE;
    virtual void TransitionEffectsUpdatedCB(const LSFStringList& transitionEffectIDs) LSF_OVERRIDE;
    virtual void DeleteTransitionEffectReplyCB(const LSFResponseCode& responseCode, const LSFString& transitionEffectID) LSF_OVERRIDE;
    virtual void TransitionEffectsDeletedCB(const LSFStringList& transitionEffectIDs) LSF_OVERRIDE;

  protected:
    jweak jdelegate;
};

} /* namespace lsf */
#endif /* LSF_JNI_XTRANSITIONEFFECTMANAGERCALLBACK_H_ */