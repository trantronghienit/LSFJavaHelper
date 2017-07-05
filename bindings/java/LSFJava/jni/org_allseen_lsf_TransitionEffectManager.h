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
/* Header for class org_allseen_lsf_TransitionEffectManager */

#ifndef _Included_org_allseen_lsf_TransitionEffectManager
#define _Included_org_allseen_lsf_TransitionEffectManager
#ifdef __cplusplus
extern "C" {
#endif
#undef org_allseen_lsf_TransitionEffectManager_MAX_TRANSITION_EFFECTS
#define org_allseen_lsf_TransitionEffectManager_MAX_TRANSITION_EFFECTS 100L
/*
 * Class:     org_allseen_lsf_TransitionEffectManager
 * Method:    getAllTransitionEffectIDs
 * Signature: ()Lorg/allseen/lsf/sdk/ControllerClientStatus;
 */
JNIEXPORT jobject JNICALL Java_org_allseen_lsf_TransitionEffectManager_getAllTransitionEffectIDs
  (JNIEnv *, jobject);

/*
 * Class:     org_allseen_lsf_TransitionEffectManager
 * Method:    getTransitionEffect
 * Signature: (Ljava/lang/String;)Lorg/allseen/lsf/sdk/ControllerClientStatus;
 */
JNIEXPORT jobject JNICALL Java_org_allseen_lsf_TransitionEffectManager_getTransitionEffect
  (JNIEnv *, jobject, jstring);

/*
 * Class:     org_allseen_lsf_TransitionEffectManager
 * Method:    applyTransitionEffectOnLamps
 * Signature: (Ljava/lang/String;[Ljava/lang/String;)Lorg/allseen/lsf/sdk/ControllerClientStatus;
 */
JNIEXPORT jobject JNICALL Java_org_allseen_lsf_TransitionEffectManager_applyTransitionEffectOnLamps
  (JNIEnv *, jobject, jstring, jobjectArray);

/*
 * Class:     org_allseen_lsf_TransitionEffectManager
 * Method:    applyTransitionEffectOnLampGroups
 * Signature: (Ljava/lang/String;[Ljava/lang/String;)Lorg/allseen/lsf/sdk/ControllerClientStatus;
 */
JNIEXPORT jobject JNICALL Java_org_allseen_lsf_TransitionEffectManager_applyTransitionEffectOnLampGroups
  (JNIEnv *, jobject, jstring, jobjectArray);

/*
 * Class:     org_allseen_lsf_TransitionEffectManager
 * Method:    getTransitionEffectName
 * Signature: (Ljava/lang/String;Ljava/lang/String;)Lorg/allseen/lsf/sdk/ControllerClientStatus;
 */
JNIEXPORT jobject JNICALL Java_org_allseen_lsf_TransitionEffectManager_getTransitionEffectName
  (JNIEnv *, jobject, jstring, jstring);

/*
 * Class:     org_allseen_lsf_TransitionEffectManager
 * Method:    setTransitionEffectName
 * Signature: (Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;)Lorg/allseen/lsf/sdk/ControllerClientStatus;
 */
JNIEXPORT jobject JNICALL Java_org_allseen_lsf_TransitionEffectManager_setTransitionEffectName
  (JNIEnv *, jobject, jstring, jstring, jstring);

/*
 * Class:     org_allseen_lsf_TransitionEffectManager
 * Method:    createTransitionEffect
 * Signature: (Lorg/allseen/lsf/sdk/TrackingID;Lorg/allseen/lsf/TransitionEffect;Ljava/lang/String;Ljava/lang/String;)Lorg/allseen/lsf/sdk/ControllerClientStatus;
 */
JNIEXPORT jobject JNICALL Java_org_allseen_lsf_TransitionEffectManager_createTransitionEffect
  (JNIEnv *, jobject, jobject, jobject, jstring, jstring);

/*
 * Class:     org_allseen_lsf_TransitionEffectManager
 * Method:    updateTransitionEffect
 * Signature: (Ljava/lang/String;Lorg/allseen/lsf/TransitionEffect;)Lorg/allseen/lsf/sdk/ControllerClientStatus;
 */
JNIEXPORT jobject JNICALL Java_org_allseen_lsf_TransitionEffectManager_updateTransitionEffect
  (JNIEnv *, jobject, jstring, jobject);

/*
 * Class:     org_allseen_lsf_TransitionEffectManager
 * Method:    deleteTransitionEffect
 * Signature: (Ljava/lang/String;)Lorg/allseen/lsf/sdk/ControllerClientStatus;
 */
JNIEXPORT jobject JNICALL Java_org_allseen_lsf_TransitionEffectManager_deleteTransitionEffect
  (JNIEnv *, jobject, jstring);

/*
 * Class:     org_allseen_lsf_TransitionEffectManager
 * Method:    getTransitionEffectDataSet
 * Signature: (Ljava/lang/String;Ljava/lang/String;)Lorg/allseen/lsf/sdk/ControllerClientStatus;
 */
JNIEXPORT jobject JNICALL Java_org_allseen_lsf_TransitionEffectManager_getTransitionEffectDataSet
  (JNIEnv *, jobject, jstring, jstring);

/*
 * Class:     org_allseen_lsf_TransitionEffectManager
 * Method:    createNativeObject
 * Signature: (Lorg/allseen/lsf/ControllerClient;Lorg/allseen/lsf/TransitionEffectManagerCallback;)V
 */
JNIEXPORT void JNICALL Java_org_allseen_lsf_TransitionEffectManager_createNativeObject
  (JNIEnv *, jobject, jobject, jobject);

/*
 * Class:     org_allseen_lsf_TransitionEffectManager
 * Method:    destroyNativeObject
 * Signature: ()V
 */
JNIEXPORT void JNICALL Java_org_allseen_lsf_TransitionEffectManager_destroyNativeObject
  (JNIEnv *, jobject);

#ifdef __cplusplus
}
#endif
#endif