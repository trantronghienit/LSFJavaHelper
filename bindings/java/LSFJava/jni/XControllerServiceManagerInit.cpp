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

#include "XControllerServiceManagerInit.h"

namespace lsf {
namespace controllerservice {

ControllerServiceManager* managerInstance = NULL;

ControllerServiceManager* InitializeControllerServiceManager(
    const std::string& factoryConfigFile,
    const std::string& configFile,
    const std::string& lampGroupFile,
    const std::string& presetFile,
    const std::string& transitionEffectFile,
    const std::string& pulseEffectFile,
    const std::string& sceneElementsFile,
    const std::string& sceneFile,
    const std::string& sceneWithSceneElementsFile,
    const std::string& masterSceneFile)
{
    // Save a copy of the ControllerServiceManager pointer so that we can
    // later invoke the LightingReset() and FactoryReset() API methods.
    managerInstance = new ControllerServiceManager(
        factoryConfigFile,
        configFile,
        lampGroupFile,
        presetFile,
        transitionEffectFile,
        pulseEffectFile,
        sceneElementsFile,
        sceneFile,
        sceneWithSceneElementsFile,
        masterSceneFile);

    return managerInstance;
}

} /* namespace controllerservice */
} /* namespace lsf */