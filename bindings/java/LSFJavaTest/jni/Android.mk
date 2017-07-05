#    Copyright (c) Open Connectivity Foundation (OCF), AllJoyn Open Source
#    Project (AJOSP) Contributors and others.
#    
#    SPDX-License-Identifier: Apache-2.0
#    
#    All rights reserved. This program and the accompanying materials are
#    made available under the terms of the Apache License, Version 2.0
#    which accompanies this distribution, and is available at
#    http://www.apache.org/licenses/LICENSE-2.0
#    
#    Copyright (c) Open Connectivity Foundation and Contributors to AllSeen
#    Alliance. All rights reserved.
#    
#    Permission to use, copy, modify, and/or distribute this software for
#    any purpose with or without fee is hereby granted, provided that the
#    above copyright notice and this permission notice appear in all
#    copies.
#    
#    THE SOFTWARE IS PROVIDED "AS IS" AND THE AUTHOR DISCLAIMS ALL
#    WARRANTIES WITH REGARD TO THIS SOFTWARE INCLUDING ALL IMPLIED
#    WARRANTIES OF MERCHANTABILITY AND FITNESS. IN NO EVENT SHALL THE
#    AUTHOR BE LIABLE FOR ANY SPECIAL, DIRECT, INDIRECT, OR CONSEQUENTIAL
#    DAMAGES OR ANY DAMAGES WHATSOEVER RESULTING FROM LOSS OF USE, DATA OR
#    PROFITS, WHETHER IN AN ACTION OF CONTRACT, NEGLIGENCE OR OTHER
#    TORTIOUS ACTION, ARISING OUT OF OR IN CONNECTION WITH THE USE OR
#    PERFORMANCE OF THIS SOFTWARE.
LOCAL_PATH := $(call my-dir)

MY_LSF_JAVA_HOME  := $(shell $(LOCAL_PATH)/../project_var LSF_JAVA_HOME $(ECLIPSE_WORKSPACE_PATH))
MY_LSF_JAVA_INC   := $(MY_LSF_JAVA_HOME)/jni
MY_LSF_JAVA_LIB   := $(MY_LSF_JAVA_HOME)/obj/local/$(APP_ABI)
$(info LSF_JAVA_HOME   = $(MY_LSF_JAVA_HOME))

MY_LSF_HOME            := $(shell $(LOCAL_PATH)/../project_var LSF_HOME $(ECLIPSE_WORKSPACE_PATH))
MY_LSF_COMMON_HOME     := $(MY_LSF_HOME)/common
MY_LSF_COMMON_INC      := $(MY_LSF_COMMON_HOME)/inc
MY_LSF_STD_COMMON_HOME := $(MY_LSF_HOME)/standard_core_library/common
MY_LSF_STD_COMMON_INC  := $(MY_LSF_STD_COMMON_HOME)/inc
MY_LSF_STD_COMMON_SRC  := ../$(MY_LSF_STD_COMMON_HOME)/src
MY_LSF_STD_CLIENT_HOME := $(MY_LSF_HOME)/standard_core_library/lighting_controller_client
MY_LSF_STD_CLIENT_INC  := $(MY_LSF_STD_CLIENT_HOME)/inc
MY_LSF_STD_CLIENT_SRC  := ../$(MY_LSF_STD_CLIENT_HOME)/src

MY_ALLJOYN_HOME    := $(shell $(MY_LSF_JAVA_HOME)/project_var ALLJOYN_HOME $(ECLIPSE_WORKSPACE_PATH))
MY_ALLJOYN_INC     := $(MY_ALLJOYN_HOME)/cpp/inc
MY_ALLJOYN_LIB     := $(MY_ALLJOYN_HOME)/java/lib

MY_ABOUT_HOME          := $(MY_ALLJOYN_HOME)
MY_ABOUT_INC           := $(MY_ABOUT_HOME)/about/inc
MY_ABOUT_LIB           := $(MY_ABOUT_HOME)/about/lib

LOCAL_LDLIBS := -L$(SYSROOT)/usr/lib -llog


# -----------------------------------------------------------------------------
include $(CLEAR_VARS)

LOCAL_MODULE            := alljoyn_java
LOCAL_SRC_FILES         := $(MY_ALLJOYN_LIB)/liballjoyn_java.so

include $(PREBUILT_SHARED_LIBRARY)


# -----------------------------------------------------------------------------
include $(CLEAR_VARS)

LOCAL_MODULE            := alljoyn_lsf_java
LOCAL_SRC_FILES         := $(MY_LSF_JAVA_LIB)/liballjoyn_lsf_java.so

ifneq ($(MAKECMDGOALS),clean)
include $(PREBUILT_SHARED_LIBRARY)
endif


# -----------------------------------------------------------------------------
include $(CLEAR_VARS)

LOCAL_C_INCLUDES := $(MY_ALLJOYN_INC) \
                    $(MY_ABOUT_INC) \
                    $(MY_LSF_COMMON_INC) \
                    $(MY_LSF_STD_COMMON_INC) \
                    $(MY_LSF_STD_CLIENT_INC) \
                    $(MY_LSF_JAVA_INC)

LOCAL_SHARED_LIBRARIES := alljoyn_java alljoyn_lsf_java

LOCAL_CFLAGS     := -DQCC_OS_GROUP_POSIX -DQCC_OS_ANDROID -DQCC_CPU_ARM -fpic
LOCAL_LDLIBS     := -llog

LOCAL_MODULE     := alljoyn_lsf_java_test
LOCAL_SRC_FILES  := org_allseen_lsf_test_ControllerClientCallbackTest.cpp \
                    org_allseen_lsf_test_LampGroupManagerCallbackTest.cpp \
                    org_allseen_lsf_test_LampManagerCallbackTest.cpp \
                    org_allseen_lsf_test_PresetManagerCallbackTest.cpp \
                    org_allseen_lsf_test_ResponseCodeTest.cpp \
                    org_allseen_lsf_test_StatusCodeTest.cpp \
                    org_allseen_lsf_test_TransitionEffectManagerCallbackTest.cpp\
                    org_allseen_lsf_test_PulseEffectManagerCallbackTest.cpp\
                    org_allseen_lsf_test_SceneElementManagerCallbackTest.cpp\
                    org_allseen_lsf_test_SceneManagerCallbackTest.cpp\
                    org_allseen_lsf_test_MasterSceneManagerCallbackTest.cpp\
                    XCppTestDelegator.cpp

include $(BUILD_SHARED_LIBRARY)

