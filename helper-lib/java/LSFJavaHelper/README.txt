Building the LSF Helper Library for Java (LSFJavaHelper)

Prerequisites:
1) The Java SDK.
1) The Eclipse development environment with the Android SDK and NDK plugins.
2) The AllJoyn SDK for Android.
3) This project depends on the LSFJava bindings project. Make sure both are
   imported into your Eclipse workspace.

Three path variables in Eclipse are required to build the LSFJavaHelper project.
The following steps will show you how to add the required variables to Eclipse.

1) From the top menu bar, select Window -> Preferences.
2) On the left side, expand the General -> Workspace menus and select Linked Resources.
3) Click "New" to create a new variable.
4) Enter "ALLJOYN_HOME" for the variable name.
5) For location, click the "Folder..." button.
6) Locate the root folder of the AllJoyn SDK for Android and set the path of the ALLJOYN_HOME
   variable to "<path_to_AllJoyn_SDK_for_Android_root_folder>/alljoyn_android/core/alljoyn-14.02.00-rel"
7) Click OK and then OK again. You should see your newly created variable.
8) Repeat steps 3-7 to create another variable named "NDK_HOME" that points to the root folder
   of the Android NDK (e.g., "<path_to_downloads_folder>/android-ndk-r9c").
9) Repeat steps 3-7 to create another variable named "JAVA_HOME" that points to the root folder
   of the Java SDK (e.g., "<path_to_downloads_folder>/jdk1.6.0_45").

Threading Rules for LSF SDK Usage:
1) One must not block the Lighting thread (note: all of the listener methods are called on the Lighting thread)
2) One must perform all interaction with the lighting system (i.e. the LightingDirector) on the lighting thread

Example of Mandatory Calls to Initialize the Lighting Director:

        lightingDirector = LightingDirector.get();
        lightingDirector.addListener(YOUR_LISTENER);
        lightingDirector.start("YOUR_APP_NAME");

Once Generated, Full Documentation can be found at [/doc/overview-summary.html]