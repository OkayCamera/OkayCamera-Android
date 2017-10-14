# Add project specific ProGuard rules here.
# By default, the flags in this file are appended to flags specified
# in D:\Android\android-sdk/tools/proguard/proguard-android.txt
# You can edit the include path and order by changing the proguardFiles
# directive in build.gradle.
#
# For more details, see
#   http://developer.android.com/guide/developing/tools/proguard.html

# Add any project specific keep options here:

# If your project uses WebView with JS, uncomment the following
# and specify the fully qualified class name to the JavaScript interface
# class:
#-keepclassmembers class fqcn.of.javascript.interface.for.webview {
#   public *;
#}

# Uncomment this to preserve the line number information for
# debugging stack traces.
#-keepattributes SourceFile,LineNumberTable

# If you keep the line number information, uncomment this to
# hide the original source file name.
#-renamesourcefileattribute SourceFile


# proGuard 混淆文件

# 代码混淆压缩比，0~7之间，一般为5
-optimizationpasses 5

# 混淆时不采用大小写混合，默认为小写，windows用户必须配置，unix可以删除，可进一步减少大小
-dontusemixedcaseclassnames

# 指定不去忽略非公共的库的类及成员
-dontskipnonpubliclibraryclasses
-dontskipnonpubliclibraryclassmembers

# 不做预校验，Preverify 是 proguard的4个步骤之一，Android无需执行该步骤
-dontpreverify

# 混淆后产生映射文件
-verbose
-printmapping proguardMapping.txt

# 指定混淆算法，一般用google推荐的
-optimizations !code/simplification/arithmetic,!filed/*,!class/merging/*

# 保护Annotation和泛型不被混淆,在使用到Json实体映射时很必要
-keepattributes *Annotation*
-keepattributes Signature

# 抛出异常时保留行号
-keepattributes SourceFile,LineNumberTable


# 保留本地native方法
-keepclasseswithmembernames class * {
    native <methods>;
}

# 保留继承自Activity，Application类的子类，这些类可能被外部调用到
-keep public class * extends android.app.Activity
-keep public class * extends andorid.app.Application
-keep public class * extends andorid.app.Service
-keep public class * extends andorid.content.BroadcastReceiver
-keep public class * extends andorid.content.ContentProvider
-keep public class * extends andorid.app.backup.BackUpAgentHelper
-keep public class * extends andorid.preference.Preference
-keep public class * extends andorid.view.View


# 避免混淆support v4
-keep public class com.tuniu.app.ui.fragment.** {*;}

# 保留在Activity中的方法是View的方法，从而能够在layout中编写onclick
-keepclassmembers class * extends android.app.Activity {
    public void *(android.view.View);
}

# 枚举类型不被混淆
-keepclassmembers enum * {
    public static **[] values();
    public static ** valueOf(java.lang.String);
}

# 保留自定义控件不被混淆
-keep public class * extends android.view.View {
    *** get*();
    void set*(***);
    public <init>(android.content.Context);
    public <init>(android.content.Context, android.util.AttributeSet);
    public <init>(android.content.Context, android.util.AttributeSet, int);
}

# 保留parcelable不被混淆
-keep class * implement android.os.Parcelable {
    public static final android.os.Parcelable$Creator *;
}


# 保留Serializable 不被混淆
-keepclassmembers class * implements java.io.Serializable {
    static final long serialVersionUID;
    private static final java.io.ObjectStreamField[] serialPersistentFields;
    private void writeObject(java.io.ObjectOutputStream);
    private void readObject(java.io.OBjectInputStream);
    java.lang.Object writeReplace();
    java.lang.Object readResolve();
}


# 对于R文件的资源不混淆
-keep class **.R$* {
    *;
}


# 对带有回调的函数不混淆
-keepclassmembers class * {
    void *(**On*Event);
}