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

#ifndef LSF_JNI_XLAMPMEMBERLIST_H_
#define LSF_JNI_XLAMPMEMBERLIST_H_

#include <jni.h>

namespace lsf {

class XLampMemberList {
private:
    XLampMemberList();

public:
    template <typename T> static void SetLamps(JNIEnv *env, jobject thiz, jobjectArray jLampIDs);
    template <typename T> static jobjectArray GetLamps(JNIEnv *env, jobject thiz);

    template <typename T> static void SetLampGroups(JNIEnv *env, jobject thiz, jobjectArray jLampGroupIDs);
    template <typename T> static jobjectArray GetLampGroups(JNIEnv *env, jobject thiz);
  };

} /* namespace lsf */

// The .cpp file is #include'd in this .h file because some templated
// methods must be defined here. The following #define prevents the
// non-templated code from being visible here.
#define LSF_JNI_XLAMPMEMBERLIST_H_INCLUDE_TEMPLATE_METHODS
#include "XLampMemberList.cpp"
#undef LSF_JNI_XLAMPMEMBERLIST_H_INCLUDE_TEMPLATE_METHODS

#endif /* LSF_JNI_XLAMPMEMBERLIST_H_ */