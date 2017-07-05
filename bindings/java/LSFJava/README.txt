Building the LSF Java Bindings

Prerequisites:

 1) The Java SDK.
 2) The Eclipse development environment with the Android SDK and NDK plugins.
 3) An AllJoyn SDK for Android.
 4) An AllJoyn Config Service build for Android
 5) An AllJoyn Notification Service build for Android
 6) The AllJoyn Lighting Service Framework (LSF) git repo or source release

Six path variables in Eclipse are required to build the LSF Java bindings.
The following steps will show you how to add the required variables to Eclipse.

 1) From the top menu bar, select Window -> Preferences.
 2) On the left side, expand the General -> Workspace menus and select Linked Resources.
 3) Click "New" to create a new variable.
 4) Enter "ALLJOYN_HOME" for the variable name.
 5) For location, click the "Folder..." button.
 6) Set the ALLJOYN_HOME variable to be the path to the root folder of the AllJoyn SDK
    for Android (e.g., "<path_to_downloads_folder>/alljoyn_android/core/alljoyn-14.02.00-rel"
 7) Click OK and then OK again. You should see your newly created variable.
 8) Repeat steps 3-7 to create another variable named "NDK_HOME" that points to the root
    folder of the Android NDK (e.g., "<path_to_downloads_folder>/android-ndk-r9c").
 9) Repeat steps 3-7 to create another variable named "JAVA_HOME" that points to the root
    folder of the Java SDK (e.g., "<path_to_downloads_folder>/jdk1.6.0_45").
10) Repeat steps 3-7 to create another variable named "CONFIG_CPP_HOME" that points to the dist
    folder of an AllJoyn Config Service build for Android (e.g.,
    "<path_to_git_clones_folder>/base/config/build/android/arm/debug/dist").
11) Repeat steps 3-7 to create another variable named "NOTIFICATION_CPP_HOME" that points to
    the dist folder of an AllJoyn Notification Service build for Android (e.g.,
    "<path_to_git_clones_folder>/base/notification/build/android/arm/debug/dist").
12) Repeat steps 3-7 to create another variable named "LSF_HOME" that points to
    the root folder of the AllJoyn Lighting Service Framework source for Android (e.g.,
    "<path_to_git_clones_folder>/service_framework").

Threading Rules for LSF SDK Usage:
1) One must not block the Lighting thread (note: all of the listener methods are called on the Lighting thread)
2) One must perform all interaction with the lighting system (i.e. the LightingDirector) on the lighting thread

Example of Mandatory Calls to Initialize the Lighting Director:

        lightingDirector = LightingDirector.get();
        lightingDirector.addListener(YOUR_LISTENER);
        lightingDirector.start("YOUR_APP_NAME");

Once Generated, Full Documentation can be found at [../../../helper-lib/java/LSFJavaHelper/doc/overview-summary.html]
