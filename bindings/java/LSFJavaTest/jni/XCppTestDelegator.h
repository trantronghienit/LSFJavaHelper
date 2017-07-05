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

#ifndef LSF_JNI_XCPPTESTDELEGATOR_H_
#define LSF_JNI_XCPPTESTDELEGATOR_H_

#include <jni.h>

#include "JEnum.h"
#include "JStringArray.h"
#include "NDefs.h"

namespace lsf {

class XCppTestDelegator {
private:
    XCppTestDelegator();

public:
    template <typename T> static jstring Call_Void(JNIEnv *env, jobject jCallback, void (T::*cMethod)(), char const *cFunc);
    template <typename T> static jstring Call_Void_String_String(JNIEnv *env, jobject jCallback, jstring jString1, jstring jString2, void (T::*cMethod)(const std::string&, const std::string&), char const *cFunc);
    template <typename T> static jstring Call_Void_StringList(JNIEnv *env, jobject jCallback, jobjectArray jStringList, void (T::*cMethod)(const std::list<std::string>&), char const *cFunc);

    template <typename T1, typename T2, typename T3> static jstring Call_Void_String_Object(JNIEnv *env, jobject jCallback, jstring jString, jobject jObject, void (T1::*cMethod)(const std::string&, const T3&), char const *cFunc);

    template <typename T> static jstring Call_Void_ResponseCode(JNIEnv *env, jobject jCallback, jobject jResponseCode, void (T::*cMethod)(const LSFResponseCode &), char const *cFunc);
    template <typename T> static jstring Call_Void_ResponseCode_StringList(JNIEnv *env, jobject jCallback, jobject jResponseCode, jobjectArray jStringList, void (T::*cMethod)(const LSFResponseCode &, const std::list<std::string>&), char const *cFunc);
    template <typename T> static jstring Call_Void_ResponseCode_String(JNIEnv *env, jobject jCallback, jobject jResponseCode, jstring jString, void (T::*cMethod)(const LSFResponseCode &, const std::string&), char const *cFunc);
    template <typename T> static jstring Call_Void_ResponseCode_String_String(JNIEnv *env, jobject jCallback, jobject jResponseCode, jstring jString1, jstring jString2, void (T::*cMethod)(const LSFResponseCode &, const std::string&, const std::string&), char const *cFunc);
    template <typename T> static jstring Call_Void_ResponseCode_String_String_String(JNIEnv *env, jobject jCallback, jobject jResponseCode, jstring jString1, jstring jString2, jstring jString3, void (T::*cMethod)(const LSFResponseCode &, const std::string&, const std::string&, const std::string&), char const *cFunc);
    template <typename T> static jstring Call_Void_ResponseCode_String_Boolean(JNIEnv *env, jobject jCallback, jobject jResponseCode, jstring jString, jboolean jBoolean, void (T::*cMethod)(const LSFResponseCode &, const std::string&, const bool&), char const *cFunc);
    template <typename T> static jstring Call_Void_ResponseCode_String_UInt32(JNIEnv *env, jobject jCallback, jobject jResponseCode, jstring jString, jlong jLong, void (T::*cMethod)(const LSFResponseCode &, const std::string&, const uint32_t&), char const *cFunc);
    template <typename T> static jstring Call_Void_ResponseCode_String_UInt32List(JNIEnv *env, jobject jCallback, jobject jResponseCode, jstring jString, jlongArray jLongList, void (T::*cMethod)(const LSFResponseCode &, const std::string&, const std::list<uint32_t>&), char const *cFunc);
    template <typename T> static jstring Call_Void_ResponseCode_String_StringList(JNIEnv *env, jobject jCallback, jobject jResponseCode, jstring jString, jobjectArray jStringList, void (T::*cMethod)(const LSFResponseCode &, const std::string&, const std::list<std::string>&), char const *cFunc);

    template <typename T1, typename T2, typename T3> static jstring Call_Void_ResponseCode_Object(JNIEnv *env, jobject jCallback, jobject jResponseCode, jobject jObject, void (T1::*cMethod)(const LSFResponseCode &, const T3&), char const *cFunc);
    template <typename T1, typename T2, typename T3> static jstring Call_Void_ResponseCode_String_Object(JNIEnv *env, jobject jCallback, jobject jResponseCode, jstring jString, jobject jObject, void (T1::*cMethod)(const LSFResponseCode &, const std::string&, const T3&), char const *cFunc);
};

} /* namespace lsf */

// The .cpp file is #include'd in this .h file because some templated
// methods must be defined here. The following #define prevents the
// non-templated code from being visible here.
#define LSF_JNI_XCPPTESTDELEGATOR_H_INCLUDE_TEMPLATE_METHODS
#include "XCppTestDelegator.cpp"
#undef LSF_JNI_XCPPTESTDELEGATOR_H_INCLUDE_TEMPLATE_METHODS

#endif /* LSF_JNI_XCPPTESTDELEGATOR_H_ */