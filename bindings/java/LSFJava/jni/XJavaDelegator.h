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

#ifndef LSF_JNI_XJAVADELEGATOR_H_
#define LSF_JNI_XJAVADELEGATOR_H_

#include <jni.h>

#include "NTypes.h"
#include "NUtil.h"
#include "JEnum.h"

namespace lsf {

class XJavaDelegator {
  private:
    XJavaDelegator();

  public:
    // Void / 0 Arg
    static void Call_Void(const jweak jdelegate, char const *func);

    // Void / 1 Arg
    static void Call_Void_UInt32(const jweak jdelegate, char const *func, const uint32_t &uint32Value);
    static void Call_Void_VoidPointer(const jweak jdelegate, char const *func, const void *voidPointer);
    static void Call_Void_ResponseCode(const jweak jdelegate, char const *func, const LSFResponseCode &responseCode);
    static void Call_Void_StringList(const jweak jdelegate, char const *func, const LSFStringList &strList);

    template <typename CTYPE>
    static void Call_Void_EnumList(const jweak jdelegate, char const *func, JEnum *xEnum, const std::list<CTYPE> &cEnumList);

    // Void / 2 Args
    static void Call_Void_ResponseCode_String(const jweak jdelegate, char const *func, const LSFResponseCode &responseCode, const LSFString &strValue);
    static void Call_Void_ResponseCode_StringList(const jweak jdelegate, char const *func, const LSFResponseCode &responseCode, const LSFStringList &strList);

    static void Call_Void_String_String(const jweak jdelegate, char const *func, const LSFString &strValue1, const LSFString &strValue2);

    template <typename CTYPE, typename XTYPE>
    static void Call_Void_String_Object(const jweak jdelegate, char const *func, XClass *xClass, const LSFString &strValue, const CTYPE &objValue);

    // Void / 3 Args
    static void Call_Void_ResponseCode_String_Boolean(const jweak jdelegate, char const *func, const LSFResponseCode &responseCode, const LSFString &strValue, const bool &boolValue);
    static void Call_Void_ResponseCode_String_UInt32(const jweak jdelegate, char const *func, const LSFResponseCode &responseCode, const LSFString &strValue, const uint32_t &uint32Value);
    static void Call_Void_ResponseCode_String_Enum(const jweak jdelegate, char const *func, const LSFResponseCode &responseCode, const LSFString &strValue, JEnum *enumObject, const int &enumValue, char const *enumClass);
    static void Call_Void_ResponseCode_String_String(const jweak jdelegate, char const *func, const LSFResponseCode &responseCode, const LSFString &strValue1, const LSFString &strValue2);

    template <typename CTYPE, typename XTYPE>
    static void Call_Void_ResponseCode_String_Object(const jweak jdelegate, char const *func, XClass *xClass, const LSFResponseCode &responseCode, const LSFString &strValue, const CTYPE &objValue);

    static void Call_Void_ResponseCode_String_UInt32List(const jweak jdelegate, char const *func, const LSFResponseCode &responseCode, const LSFString &strValue, const UInt32List& uint32List);
    static void Call_Void_ResponseCode_String_StringList(const jweak jdelegate, char const *func, const LSFResponseCode &responseCode, const LSFString &strValue, const LSFStringList& strList);

    // Void / 4 Args
    static void Call_Void_ResponseCode_String_String_String(const jweak jdelegate, char const *func, const LSFResponseCode &responseCode, const LSFString &strValue1, const LSFString &strValue2, const LSFString &strValue3);

    // Void / 7 Args
    template <typename CTYPE1, typename XTYPE1, typename CTYPE2, typename XTYPE2, typename CTYPE3, typename XTYPE3>
    static void Call_Void_ResponseCode_String_String_String_Object_Object_Object(const jweak jdelegate, char const *func, XClass *xClass1, XClass *xClass2, XClass *xClass3, const LSFResponseCode &responseCode, const LSFString &strValue1, const LSFString &strValue2, const LSFString &strValue3, const CTYPE1 &objValue1, const CTYPE2 &objValue2, const CTYPE3 &objValue3);

    // Void / N Args
    static void Call_Void_Variadic(JScopedEnv& env, const jweak jdelegate, char const *func, char const * const sig, ...);

    // Non-Void / Misc
    static LSFString Call_String_String(const jweak jdelegate, char const *func, const LSFString &strValue, const LSFString &strDefault);
    static bool Call_Boolean(const jweak jdelegate, char const *func, const bool &boolDefault);

    template <typename CTYPE>
    static CTYPE Call_Enum(const jweak jdelegate, char const *func, JEnum *xEnum, const CTYPE &enumDefault);
};

} // namespace lsf

// The .cpp file is #include'd in this .h file because some templated
// methods must be defined here. The following #define prevents the
// non-templated code from being visible here.
#define LSF_JNI_XJAVADELEGATOR_H_INCLUDE_TEMPLATE_METHODS
#include "XJavaDelegator.cpp"
#undef LSF_JNI_XJAVADELEGATOR_H_INCLUDE_TEMPLATE_METHODS

#endif /* LSF_JNI_XJAVADELEGATOR_H_ */