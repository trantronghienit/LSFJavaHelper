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
/* DO NOT EDIT THIS FILE - it is machine generated */
#include <jni.h>
/* Header for class org_allseen_lsf_test_SceneManagerCallbackTest */

#ifndef _Included_org_allseen_lsf_test_SceneManagerCallbackTest
#define _Included_org_allseen_lsf_test_SceneManagerCallbackTest
#ifdef __cplusplus
extern "C" {
#endif
/*
 * Class:     org_allseen_lsf_test_SceneManagerCallbackTest
 * Method:    getAllSceneIDsReplyCB
 * Signature: (Lorg/allseen/lsf/SceneManagerCallback;Lorg/allseen/lsf/ResponseCode;[Ljava/lang/String;)Ljava/lang/String;
 */
JNIEXPORT jstring JNICALL Java_org_allseen_lsf_test_SceneManagerCallbackTest_getAllSceneIDsReplyCB
  (JNIEnv *, jobject, jobject, jobject, jobjectArray);

/*
 * Class:     org_allseen_lsf_test_SceneManagerCallbackTest
 * Method:    getSceneNameReplyCB
 * Signature: (Lorg/allseen/lsf/SceneManagerCallback;Lorg/allseen/lsf/ResponseCode;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;)Ljava/lang/String;
 */
JNIEXPORT jstring JNICALL Java_org_allseen_lsf_test_SceneManagerCallbackTest_getSceneNameReplyCB
  (JNIEnv *, jobject, jobject, jobject, jstring, jstring, jstring);

/*
 * Class:     org_allseen_lsf_test_SceneManagerCallbackTest
 * Method:    setSceneNameReplyCB
 * Signature: (Lorg/allseen/lsf/SceneManagerCallback;Lorg/allseen/lsf/ResponseCode;Ljava/lang/String;Ljava/lang/String;)Ljava/lang/String;
 */
JNIEXPORT jstring JNICALL Java_org_allseen_lsf_test_SceneManagerCallbackTest_setSceneNameReplyCB
  (JNIEnv *, jobject, jobject, jobject, jstring, jstring);

/*
 * Class:     org_allseen_lsf_test_SceneManagerCallbackTest
 * Method:    createSceneReplyCB
 * Signature: (Lorg/allseen/lsf/SceneManagerCallback;Lorg/allseen/lsf/ResponseCode;Ljava/lang/String;)Ljava/lang/String;
 */
JNIEXPORT jstring JNICALL Java_org_allseen_lsf_test_SceneManagerCallbackTest_createSceneReplyCB
  (JNIEnv *, jobject, jobject, jobject, jstring);

/*
 * Class:     org_allseen_lsf_test_SceneManagerCallbackTest
 * Method:    updateSceneReplyCB
 * Signature: (Lorg/allseen/lsf/SceneManagerCallback;Lorg/allseen/lsf/ResponseCode;Ljava/lang/String;)Ljava/lang/String;
 */
JNIEXPORT jstring JNICALL Java_org_allseen_lsf_test_SceneManagerCallbackTest_updateSceneReplyCB
  (JNIEnv *, jobject, jobject, jobject, jstring);

/*
 * Class:     org_allseen_lsf_test_SceneManagerCallbackTest
 * Method:    deleteSceneReplyCB
 * Signature: (Lorg/allseen/lsf/SceneManagerCallback;Lorg/allseen/lsf/ResponseCode;Ljava/lang/String;)Ljava/lang/String;
 */
JNIEXPORT jstring JNICALL Java_org_allseen_lsf_test_SceneManagerCallbackTest_deleteSceneReplyCB
  (JNIEnv *, jobject, jobject, jobject, jstring);

/*
 * Class:     org_allseen_lsf_test_SceneManagerCallbackTest
 * Method:    scenesNameChangedCB
 * Signature: (Lorg/allseen/lsf/SceneManagerCallback;[Ljava/lang/String;)Ljava/lang/String;
 */
JNIEXPORT jstring JNICALL Java_org_allseen_lsf_test_SceneManagerCallbackTest_scenesNameChangedCB
  (JNIEnv *, jobject, jobject, jobjectArray);

/*
 * Class:     org_allseen_lsf_test_SceneManagerCallbackTest
 * Method:    scenesCreatedCB
 * Signature: (Lorg/allseen/lsf/SceneManagerCallback;[Ljava/lang/String;)Ljava/lang/String;
 */
JNIEXPORT jstring JNICALL Java_org_allseen_lsf_test_SceneManagerCallbackTest_scenesCreatedCB
  (JNIEnv *, jobject, jobject, jobjectArray);

/*
 * Class:     org_allseen_lsf_test_SceneManagerCallbackTest
 * Method:    scenesUpdatedCB
 * Signature: (Lorg/allseen/lsf/SceneManagerCallback;[Ljava/lang/String;)Ljava/lang/String;
 */
JNIEXPORT jstring JNICALL Java_org_allseen_lsf_test_SceneManagerCallbackTest_scenesUpdatedCB
  (JNIEnv *, jobject, jobject, jobjectArray);

/*
 * Class:     org_allseen_lsf_test_SceneManagerCallbackTest
 * Method:    scenesAppliedCB
 * Signature: (Lorg/allseen/lsf/SceneManagerCallback;[Ljava/lang/String;)Ljava/lang/String;
 */
JNIEXPORT jstring JNICALL Java_org_allseen_lsf_test_SceneManagerCallbackTest_scenesAppliedCB
  (JNIEnv *, jobject, jobject, jobjectArray);

/*
 * Class:     org_allseen_lsf_test_SceneManagerCallbackTest
 * Method:    scenesDeletedCB
 * Signature: (Lorg/allseen/lsf/SceneManagerCallback;[Ljava/lang/String;)Ljava/lang/String;
 */
JNIEXPORT jstring JNICALL Java_org_allseen_lsf_test_SceneManagerCallbackTest_scenesDeletedCB
  (JNIEnv *, jobject, jobject, jobjectArray);

/*
 * Class:     org_allseen_lsf_test_SceneManagerCallbackTest
 * Method:    updateSceneWithSceneElementsReplyCB
 * Signature: (Lorg/allseen/lsf/SceneManagerCallback;Lorg/allseen/lsf/ResponseCode;Ljava/lang/String;)Ljava/lang/String;
 */
JNIEXPORT jstring JNICALL Java_org_allseen_lsf_test_SceneManagerCallbackTest_updateSceneWithSceneElementsReplyCB
  (JNIEnv *, jobject, jobject, jobject, jstring);

/*
 * Class:     org_allseen_lsf_test_SceneManagerCallbackTest
 * Method:    getSceneWithSceneElementsReplyCB
 * Signature: (Lorg/allseen/lsf/SceneManagerCallback;Lorg/allseen/lsf/ResponseCode;Ljava/lang/String;Lorg/allseen/lsf/SceneWithSceneElements;)Ljava/lang/String;
 */
JNIEXPORT jstring JNICALL Java_org_allseen_lsf_test_SceneManagerCallbackTest_getSceneWithSceneElementsReplyCB
  (JNIEnv *, jobject, jobject, jobject, jstring, jobject);

/*
 * Class:     org_allseen_lsf_test_SceneManagerCallbackTest
 * Method:    getSceneReplyCB
 * Signature: (Lorg/allseen/lsf/SceneManagerCallback;Lorg/allseen/lsf/ResponseCode;Ljava/lang/String;Lorg/allseen/lsf/Scene;)Ljava/lang/String;
 */
JNIEXPORT jstring JNICALL Java_org_allseen_lsf_test_SceneManagerCallbackTest_getSceneReplyCB
  (JNIEnv *, jobject, jobject, jobject, jstring, jobject);

/*
 * Class:     org_allseen_lsf_test_SceneManagerCallbackTest
 * Method:    applySceneReplyCB
 * Signature: (Lorg/allseen/lsf/SceneManagerCallback;Lorg/allseen/lsf/ResponseCode;Ljava/lang/String;)Ljava/lang/String;
 */
JNIEXPORT jstring JNICALL Java_org_allseen_lsf_test_SceneManagerCallbackTest_applySceneReplyCB
  (JNIEnv *, jobject, jobject, jobject, jstring);

/*
 * Class:     org_allseen_lsf_test_SceneManagerCallbackTest
 * Method:    createSceneWithTrackingReplyCB
 * Signature: (Lorg/allseen/lsf/SceneManagerCallback;Lorg/allseen/lsf/ResponseCode;Ljava/lang/String;J)Ljava/lang/String;
 */
JNIEXPORT jstring JNICALL Java_org_allseen_lsf_test_SceneManagerCallbackTest_createSceneWithTrackingReplyCB
  (JNIEnv *, jobject, jobject, jobject, jstring, jlong);

/*
 * Class:     org_allseen_lsf_test_SceneManagerCallbackTest
 * Method:    createSceneWithSceneElementsReplyCB
 * Signature: (Lorg/allseen/lsf/SceneManagerCallback;Lorg/allseen/lsf/ResponseCode;Ljava/lang/String;J)Ljava/lang/String;
 */
JNIEXPORT jstring JNICALL Java_org_allseen_lsf_test_SceneManagerCallbackTest_createSceneWithSceneElementsReplyCB
  (JNIEnv *, jobject, jobject, jobject, jstring, jlong);

#ifdef __cplusplus
}
#endif
#endif