# To enable ProGuard in your project, edit project.properties
# to define the proguard.config property as described in that file.
#
# Add project specific ProGuard rules here.
# By default, the flags in this file are appended to flags specified
# in ${sdk.dir}/tools/proguard/proguard-android.txt
# You can edit the include path and order by changing the ProGuard
# include property in project.properties.
#
# For more details, see
# TODO  http://developer.android.com/guide/developing/tools/proguard.html

# Add any project specific keep options here:

# If your project uses WebView with JS, uncomment the following
# and specify the fully qualified class name to the JavaScript interface
# class:
#-keepclassmembers class fqcn.of.javascript.interface.for.webview {
#   public *;
#}
#-dontwarn com.google.android.maps.**
#-keep class com.google.android.maps.** { *;}

#-dontwarn com.xtremelabs.robolectric.**
#-keep class com.xtremelabs.robolectric.** { *;}

# TODO http://www.cnblogs.com/royi123/archive/2013/02/28/2937657.html
# TODO -dontwarn 缺省proguard 会检查每一个引用是否正确，但是第三方库里面往往有些不会用到的类，没有正确引用。如果不配置的话，系统就会报错。
# TODO -keep 指定的类和类成员被保留作为 入口
# TODO -keepclassmembers 指定的类成员被保留。
# TODO -keepclasseswithmembers 指定的类和类成员被保留，假如指定的类成员存在的话。

#忽略警告, 直接运行时as会报这句的错误
#-ignorewarning
-dontskipnonpubliclibraryclasses
-dontskipnonpubliclibraryclassmembers

#注解
-keepattributes *Annotation*,Signature
-keepclassmembers,allowobfuscation class * {
    @com.google.inject.Inject <fields>;
    @com.google.inject.Inject <init>(...);
    @roboguice.inject.InjectView <fields>;
    @roboguice.inject.InjectFragment <fields>;
    native <methods>;
    @com.google.inject.Singleton <methods>;
    @org.greenrobot.eventbus.Subscribe <methods>;
}

# 保留某些包下的类名及属性
#-keep class com.jeeinc.save.worry.core.** { *;}
#-keep class com.teaframework.** { *;}
#-keep class net.tsz.afinal.** { *;}
#-keep class com.jeeinc.save.worry.entities.** { *;}
#-keep class com.jeeinc.save.worry.entity.** { *;}
#-keep class com.jeeinc.save.worry.base.** { *;}

-keep enum org.greenrobot.eventbus.ThreadMode { *; }
-keepclassmembers class * extends org.greenrobot.eventbus.util.ThrowableFailureEvent {
    <init>(java.lang.Throwable);
}

# 保留继承自BaseEntity的类及属性
-keepclassmembers class * extends com.sxb.sxbdock.base.BaseEntity { *;}
# -keepclassmembers class com.example.kiven.test302.entity.User { *;}


-keepnames class * extends android.app.Activity
-keep public class * extends android.app.Application
-keep public class * extends android.app.Service
-keep public class * extends android.content.BroadcastReceiver
-keep public class * extends android.content.ContentProvider
-keep class com.google.inject.Binder
# 微信支付
-keep class net.sourceforge.simcpux.wxapi.WXPayEntryActivity
-keep class com.tencent.** { *;}
-keep interface com.tencent.** { *;}
#-keep class com.tencent.mm.sdk.openapi.WXMediaMessage {*;}
#-keep class com.tencent.mm.sdk.openapi.** implements com.tencent.mm.sdk.openapi.WXMediaMessage$IMediaObject {*;}

#-keepclassmembers class * {
#    @com.google.inject.Inject <init>(...);
#}
# There's no way to keep all @Observes methods, so use the On*Event convention to identify event handlers

-keepclassmembers class * {
    void *(**On*Event);
    @android.webkit.JavascriptInterface <methods>;# TODO js调用方法名称保留
}
#-keep public class * extends android.view.View {
#    public <init>(android.content.Context);
#    public <init>(android.content.Context, android.util.AttributeSet);
#    public <init>(android.content.Context, android.util.AttributeSet, int);
#    public void set*(...);
#}

# 几个库文件
-dontwarn roboguice.**
-keep class roboguice.** { *;}

-dontwarn cn.jpush.**
-keep class cn.jpush.** { *; }

-dontwarn com.google.**
-keep class com.google.** { *;}

-dontwarn android.support.v4.**
-keep class android.support.v4.** { *;}

# -------------- @Keep ----------------
# http://www.jianshu.com/p/be7ec1819d2f
-keep @android.support.annotation.Keep class *
-keepclassmembers class * {
    @android.support.annotation.Keep *;
}
# -------------- proto ----------------
-keep class proto.** { *;}
# -------------- xutils start ----------------
-keep public class org.xutils.** {
    public protected *;
}
-keep public interface org.xutils.** {
    public protected *;
}
-keepclassmembers class * extends org.xutils.** {
    public protected *;
}
-keepclassmembers @org.xutils.db.annotation.* class * {*;}
-keepclassmembers @org.xutils.http.annotation.* class * {*;}
-keepclassmembers class * {
    @org.xutils.view.annotation.Event <methods>;
}
# -------------- xutils end   ----------------

# sharesdk 分享
-keep class cn.sharesdk.**{*;}
-keep class com.sina.**{*;}
-keep class **.R$* {*;}
-keep class **.R{*;}
-dontwarn cn.sharesdk.**
-dontwarn **.R$*

# 友盟更新() 友盟统计(http://bbs.umeng.com/thread-5446-1-1.html, http://bbs.umeng.com/thread-7454-1-1.html)
-keepattributes SourceFile,LineNumberTable
-keepclassmembers class * {
   public <init>(org.json.JSONObject);
}
-keep public class com.jeeinc.save.worry.R$*{
public static final int *;
}
-keepclassmembers enum * {
    public static **[] values();
    public static ** valueOf(java.lang.String);
}