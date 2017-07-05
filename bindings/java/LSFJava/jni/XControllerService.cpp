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

#include "NUtil.h"
#include "XControllerService.h"
#include "XControllerServiceManagerInit.h"
#include "XJavaDelegator.h"

// Global functions defined by the Controller Service in Main.cc
extern int main(int argc, char** argv);
extern void ControllerServiceStop();

#define QCC_MODULE "AJN-LSF-JNI"

namespace lsf {

XControllerService* XControllerService::cActive = NULL;

XControllerService::XControllerService(jobject jobj) : cNetworkCallback(NULL)
{
    JNIEnv* env = GetEnv();

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

    // We can currently have only one active Controller Service
    if (cActive == NULL) {
        QCC_DbgPrintf(("Created active"));
        cActive = this;
    } else {
        QCC_DbgPrintf(("Created inactive"));
    }
}

XControllerService::~XControllerService()
{
    // Get the JNIEnv for the current native thread
    JScopedEnv env;

    env->DeleteWeakGlobalRef(jdelegate);
    if (env->ExceptionCheck()) {
        QCC_LogError(ER_FAIL, ("DeleteWeakGlobalRef() failed"));
        return;
    }

    if (cActive == this) {
        QCC_DbgPrintf(("Destroyed active"));
        cActive = NULL;
    } else {
        QCC_DbgPrintf(("Destroyed inactive"));
    }
}

void XControllerService::Start(const LSFString& keyStorePath)
{
    if (cActive != this) {
        QCC_DbgPrintf(("Inactive"));
        return;
    }

    // Initialize arguments to main()
    char* argv[] = {
        strdup("controllerService"),
        strdup("-f"),
        strdup("-l"),
        strdup("-k"),
        strdup(keyStorePath.c_str())
    };
    int argc = sizeof(argv)/sizeof(argv[0]);

    // Call global function defined by the Controller Service in Main.cc
    main(argc, argv);

    // Clean up arguments to main()
    for (int i = 0; i < argc; i++) {
        free(argv[i]);
    }
}

void XControllerService::Stop()
{
    if (cActive != this) {
        QCC_DbgPrintf(("Inactive"));
        return;
    }

    // Call global function defined by the Controller Service in Main.cc
    ControllerServiceStop();
}

void XControllerService::LightingReset()
{
    if (cActive != this) {
        QCC_DbgPrintf(("Inactive"));
        return;
    }

    if (managerInstance == NULL) {
        QCC_DbgPrintf(("Invalid manager"));
        return;
    }

    managerInstance->GetControllerServicePtr()->LightingResetAPI();
}

void XControllerService::FactoryReset()
{
    if (cActive != this) {
        QCC_DbgPrintf(("Inactive"));
        return;
    }

    if (managerInstance == NULL) {
        QCC_DbgPrintf(("Invalid manager"));
        return;
    }

    managerInstance->GetControllerServicePtr()->FactoryResetAPI();
}

void XControllerService::SendNetworkConnected()
{
    if (cActive != this) {
        QCC_DbgPrintf(("Inactive"));
        return;
    }

    if (!cNetworkCallback) {
        QCC_DbgPrintf(("No network callback"));
        return;
    }

    cNetworkCallback->Connected();
}

void XControllerService::SendNetworkDisconnected()
{
    if (cActive != this) {
        QCC_DbgPrintf(("Inactive"));
        return;
    }

    if (!cNetworkCallback) {
        QCC_DbgPrintf(("No network callback"));
        return;
    }

    cNetworkCallback->Disconnected();
}

jstring XControllerService::GetName()
{
    JScopedEnv env;

    if (cActive != this) {
        QCC_DbgPrintf(("Inactive"));
        return (jstring) NULL;
    }

    if (managerInstance == NULL) {
        QCC_DbgPrintf(("Invalid manager"));
        return (jstring) NULL;
    }

    ControllerService *controller = managerInstance->GetControllerServicePtr();

    if (controller == NULL) {
        QCC_DbgPrintf(("Invalid LSFAboutDataStore"));
        return (jstring) NULL;
    }

    char *controllerName = NULL;
    controller->GetAboutDataStore().GetDeviceName(&controllerName);

    if (controllerName == NULL) {
        QCC_DbgPrintf(("Invalid Controller Name"));
        return (jstring) NULL;
    }

    jstring jstrValue = env->NewStringUTF(controllerName);
    if (env->ExceptionCheck() || !jstrValue) {
        QCC_LogError(ER_FAIL, ("NewStringUTF() failed"));
        return NULL;
    }

    return jstrValue;
}

jboolean XControllerService::IsLeader()
{
    if (cActive != this) {
        QCC_DbgPrintf(("Inactive"));
        return (jboolean) false;
    }

    if (managerInstance == NULL) {
        QCC_DbgPrintf(("Invalid manager"));
        return (jboolean) false;
    }

    ControllerService *controller = managerInstance->GetControllerServicePtr();

    if (controller == NULL) {
        QCC_DbgPrintf(("Invalid ControllerServicePtr"));
        return (jboolean) false;
    }

    return (jboolean) controller->IsLeader();
}

void XControllerService::PopulateDefaultProperties(const AboutData *aboutData)
{
    return XJavaDelegator::Call_Void_VoidPointer(jdelegate, __func__, (void *)aboutData);
}

LSFString XControllerService::GetMacAddress(const LSFString &defaultMacAddress)
{
    return XJavaDelegator::Call_String_String(jdelegate, __func__, defaultMacAddress, defaultMacAddress);
}

bool XControllerService::IsNetworkConnected(const bool &defaultIsNetworkConnected)
{
    return XJavaDelegator::Call_Boolean(jdelegate, __func__, defaultIsNetworkConnected);
}

OEM_CS_RankParam_Power XControllerService::GetRankPower(const OEM_CS_RankParam_Power &defaultRankPower)
{
    return XJavaDelegator::Call_Enum<OEM_CS_RankParam_Power>(jdelegate, __func__, JEnum::jRankPowerEnum, defaultRankPower);
}

OEM_CS_RankParam_Mobility XControllerService::GetRankMobility(const OEM_CS_RankParam_Mobility &defaultRankMobility)
{
    return XJavaDelegator::Call_Enum<OEM_CS_RankParam_Mobility>(jdelegate, __func__, JEnum::jRankMobilityEnum, defaultRankMobility);
}

OEM_CS_RankParam_Availability XControllerService::GetRankAvailability(const OEM_CS_RankParam_Availability &defaultRankAvailability)
{
    return XJavaDelegator::Call_Enum<OEM_CS_RankParam_Availability>(jdelegate, __func__, JEnum::jRankAvailabilityEnum, defaultRankAvailability);
}

OEM_CS_RankParam_NodeType XControllerService::GetRankNodeType(const OEM_CS_RankParam_NodeType &defaultRankNodeType)
{
    return XJavaDelegator::Call_Enum<OEM_CS_RankParam_NodeType>(jdelegate, __func__, JEnum::jRankNodeTypeEnum, defaultRankNodeType);
}

} /* namespace lsf */