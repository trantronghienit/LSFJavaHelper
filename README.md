# Yêu Cầu:
+ 1. Java SDK(cài java 6).
+ 2. Android SDK and NDK cho eclipse.
+ 3. AllJoyn SDK for Android.
+ 4. import **LSFJava** , **LSFJavaTest** trong đường dẫn "bindings/java" và **LSFJavaHelper** trong đường dẫn "helper-lib/java" vào eclipse
sao cho 3 project này nằm chung workspace.

# Thiết lập Đường dẫn môi trường để build thư viện
chọn trên menu eclipse Window -> Preferences -> General -> Workspace -> Linked Resources </br>
+ Click "New" tạo mục name nhập "ALLJOYN_HOME" , location : đường dẫn đến "alljoyn_android/core/alljoyn-14.02.00-rel" click ok
+ tương tự làm như trên đối với "NDK_HOME"  location : E:\Android\sdk\ndk-bundle  --> thay đổi đường dẫn của bạn
+ tương tự làm như trên đối với "JAVA_HOME" location : C:\Program Files\Java\jdk1.6.0_45  --> thay đổi đường dẫn của bạn

![image](https://user-images.githubusercontent.com/18228937/27872012-9f108b08-61d1-11e7-95b0-62ad4ad37bdd.png)

+ cuối cùng tiến hành export ra file jar giống như java lib ta thu được 1 file thư viện tên lsfjavahelper.jar trong thư mục bin trong project LSFJavaHelper
