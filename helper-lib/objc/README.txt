LSF SDK for iOS (Objective-C)

The LSF SDK for iOS contains all libraries that are needed to build an iOS application
as well as tutorial applications that demonstrate some of the core lighting functionality. In
addition to the SDK libraries, the SDK contains the AllJoyn core and base services libraries
that are necessary for lighting.

Instructions on how to build the Android tutorial apps can be found here:
1) <lsf_sdk_install_dir>/iOS/LSFPresetTutorial/README.txt
2) <lsf_sdk_install_dir>/iOS/LSFPulseEffectTutorial/README.txt
3) <lsf_sdk_install_dir>/iOS/LSFSceneTutorial/README.txt
4) <lsf_sdk_install_dir>/iOS/LSFTransitionEffectTutorial/README.txt
5) <lsf_sdk_install_dir>/iOS/LSFTutorial/README.txt
6) <lsf_sdk_install_dir>/iOS/LSFUITutorial/README.txt

Threading Rules for LSF SDK Usage:
1) One must not block the Lighting thread (note: all of the listener methods are called on the Lighting thread)
2) One must perform all interaction with the lighting system (i.e. the LightingDirector) on the lighting thread

Example of Mandatory Calls to Initialize the Lighting Director:

        lightingDirector = [LSFSDKLightingDirector getLightingDirector];
        [lightingDirector addDelegate: YOUR_LISTENER];
        [lightingDirector start: @"YOUR_APP_NAME"];

Full Documentation can be found at <lsf_sdk_install_dir>/iOS/Libraries/LSFObjCHelper/doc/html/index.html