# Add project specific ProGuard rules here.
# You can control the set of applied configuration files using the
# proguardFiles setting in build.gradle.
#
# For more details, see
#   http://developer.android.com/guide/developing/tools/proguard.html

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

-dontusemixedcaseclassnames
-dontskipnonpubliclibraryclasses
-verbose
-dontwarn

# Optimization is turned off by default. Dex does not like code run
# through the ProGuard optimize and preverify steps (and performs some
# of these optimizations on its own).
-dontoptimize
-dontpreverify
# Note that if you want to enable optimization, you cannot just
# include optimization flags in your own project configuration file;
# instead you will need to point to the
# "proguard-android-optimize.txt" file instead of this one from your
# project.properties file.

-keepattributes *Annotation*
-keep public class com.google.vending.licensing.ILicensingService
-keep public class com.android.vending.licensing.ILicensingService

# For native methods, see http://proguard.sourceforge.net/manual/examples.html#native
-keepclasseswithmembernames class * {
    native <methods>;
}

# keep setters in Views so that animations can still work.
# see http://proguard.sourceforge.net/manual/examples.html#beans
-keepclassmembers public class * extends android.view.View {
   void set*(***);
   *** get*();
}

# We want to keep methods in Activity that could be used in the XML attribute onClick
-keepclassmembers class * extends android.app.Activity {
   public void *(android.view.View);
}

# For enumeration classes, see http://proguard.sourceforge.net/manual/examples.html#enumerations
-keepclassmembers enum * {
    public static **[] values();
    public static ** valueOf(java.lang.String);
}
-keepnames class * implements java.io.Serializable
-keep class * implements android.os.Parcelable {
  public static final android.os.Parcelable$Creator *;
}

-keepclassmembers class **.R$* {
    public static <fields>;
}

-keep class android.support.v4.** {*;}
-dontwarn java.lang.annotation.Annotation




#保持自定义控件类不被混淆
-keepclasseswithmembers class * {
    public <init>(android.content.Context, android.util.AttributeSet);
}
# We want to keep methods in Activity that could be used in the XML attribute onClick
-keepclassmembers class * extends android.app.Activity {
   public void *(android.view.View);
}
-keepclassmembers class * extends android.support.v7.app.AppCompatActivity {
   public void *(android.view.View);
}
-keep public class * extends android.view.View {
    public <init>(android.content.Context);
    public <init>(android.content.Context, android.util.AttributeSet);
    public <init>(android.content.Context, android.util.AttributeSet, int);
    public void set*(...);
}



#------------------  下方是android平台自带的排除项，这里不要动         ----------------

-keep public class * extends android.app.Activity{
	public <fields>;
	public <methods>;
}
-keep public class * extends android.app.Application{
	public <fields>;
	public <methods>;
}
-keep public class * extends android.app.Service
-keep public class * extends android.content.
-keep public class * extends android.content.ContentProvider
-keep public class * extends android.app.backup.BackupAgentHelper
-keep public class * extends android.preference.Preference

-keepclassmembers enum * {
    public static **[] values();
    public static ** valueOf(java.lang.String);
}

-keepclasseswithmembers class * {
	public <init>(android.content.Context, android.util.AttributeSet);
}

-keepclasseswithmembers class * {
	public <init>(android.content.Context, android.util.AttributeSet, int);
}

-keepattributes *Annotation*

-keepclasseswithmembernames class *{
	native <methods>;
}

-keep class * implements android.os.Parcelable {
  public static final android.os.Parcelable$Creator *;
}

#------------------  下方是共性的排除项目         ----------------
# 方法名中含有“JNI”字符的，认定是Java Native Interface方法，自动排除
# 方法名中含有“JRI”字符的，认定是Java Reflection Interface方法，自动排除

-keepclasseswithmembers class * {
    ... *JNI*(...);
}

-keepclasseswithmembernames class * {
	... *JRI*(...);
}

-keep class **JNI* {*;}


-keep class com.linxf.lintestdemo.dagger.**{*;}
-keep class com.linxf.lintestdemo.entity.**{*;}
####################### base ############################
# greenDAO开始
-keep class org.greenrobot.greendao.**{*;}
-keepclassmembers class * extends org.greenrobot.greendao.AbstractDao {
public static java.lang.String TABLENAME;
}
-keep class **$Properties
-dontwarn org.greenrobot.greendao.database.**
-dontwarn rx.**
# greenDAO结束

##databinding
-dontwarn android.databinding.**
-dontwarn com.linxf.base.databinding.**
-dontwarn com.linxf.lintestdemo.databinding.**
-keep class com.linxf.lintestdemo.databinding.** {*;}
-keep class com.linxf.lintestdemo.DataBinderMapperImpl {*;}
-keep class com.linxf.base.databinding.** { *;}
-keep class com.linxf.base.DataBinderMapperImpl { *;}


-keep class android.databinding.** {*;}
-keep interface android.databinding.** {*;}





#-keep class com.linxf.base.entity.**{*;}
-keep class com.linxf.base.constant.**{*;}
-keep class com.linxf.base.controller.**{*;}
-keep class com.linxf.base.utils.BottomNavigationViewHelper{*;}
-keepclassmembers class android.support.design.internal.BottomNavigationMenuView {
    boolean mShiftingMode;
}


##Rxjava RxAndroid
-dontwarn rx.*
-dontwarn sun.misc.**
-keepclassmembers class rx.internal.util.unsafe.*ArrayQuene*Field*{
long producerIndex;
long consumerIndex;
}

-keepclassmembers class rx.internal.util.unsafe.BaseLinkedQueueProducerNodeRef {
rx.internal.util.atomic.LinkedQueueNode producerNode;
rx.internal.util.atomic.LinkedQueueNode consumerNode;
}
-keepclassmembers class rx.internal.util.unsafe.BaseLinkedQueueConsumerNodeRef {
rx.internal.util.atomic.LinkedQueueNode consumerNode;
}
## RxPermission
-keep class com.tbruyelle.rxpermissions2.** { *; }
-keep interface com.tbruyelle.rxpermissions2.** { *; }

# RxLifeCycle
-keep class com.trello.rxlifecycle2.** { *; }
-keep interface com.trello.rxlifecycle2.** { *; }
-dontwarn com.trello.rxlifecycle2.**

## glide
-keep public class * implements com.bumptech.glide.module.GlideModule
-keep public class * extends com.bumptech.glide.module.AppGlideModule
-keep public enum com.bumptech.glide.load.ImageHeaderParser$** {
  **[] $VALUES;
  public *;
}
# for DexGuard only
#-keepresourcexmlelements manifest/application/meta-data@value=GlideModule
#glide如果你的API级别<=Android API 27 则需要添加
-dontwarn com.bumptech.glide.load.resource.bitmap.VideoDecode

## okhttp
-dontwarn okhttp3.**
-dontwarn okio.**
-dontwarn javax.annotation.**
# A resource is loaded with a relative path so the package of this class must be preserved.
-keepnames class okhttp3.internal.publicsuffix.PublicSuffixDatabase
-dontwarn org.codehaus.mojo.animal_sniffer.*
-dontwarn okhttp3.internal.platform.ConscryptPlatform
-dontwarn com.squareup.okhttp3.**
-keep class com.squareup.okhttp3.** { *;}
-keep class okhttp3.**{*;}
-keep interface okhttp3.**{*;}
-keep class okio.**{*;}




## retrofit
-dontwarn retrofit2.**
-dontwarn org.codehaus.mojo.**
-keep class retrofit2.** { *; }
-keepattributes Signature
-keepattributes Exceptions
-keepattributes *Annotation*
-keepattributes RuntimeVisibleAnnotations
-keepattributes RuntimeInvisibleAnnotations
-keepattributes RuntimeVisibleParameterAnnotations
-keepattributes RuntimeInvisibleParameterAnnotations
-keepattributes EnclosingMethod
-keepclasseswithmembers class * {@retrofit2.* <methods>;}
-keepclasseswithmembers interface * {@retrofit2.* <methods>;}

##gson
-keepattributes Signature
-keepattributes *Annotation*
-dontwarn sun.misc.**
-keep class * implements com.google.gson.TypeAdapterFactory
-keep class * implements com.google.gson.JsonSerializer
-keep class * implements com.google.gson.JsonDeserializer
-keepclassmembers,allowobfuscation class * {
  @com.google.gson.annotations.SerializedName <fields>;
}



## fastjson
-dontwarn com.alibaba.fastjson.**
-keep class com.alibaba.fastjson.** { *; }
-keepattributes Signature

## xbanner
-keep class com.stx.xhb.xbanner.**{*;}



#x5浏览器内核
#加dontwarn这句是为了解决打包出错Warning:com.tencent.smtt.export.external.DexLoader: can't find referenced class dalvik.system.VMStack
#-dontwarn com.tencent.smtt.export.external.**
#-keep class com.tencent.smtt.export.external.**{*;}
#-keep class com.tencent.tbs.video.interfaces.IUserStateChangedListener {*;}
#-keep class com.tencent.smtt.sdk.CacheManager {public *;}
#-keep class com.tencent.smtt.sdk.CookieManager {public *;}
#-keep class com.tencent.smtt.sdk.WebHistoryItem {public *;}
#-keep class com.tencent.smtt.sdk.WebViewDatabase {public *;}
#-keep class com.tencent.smtt.sdk.WebBackForwardList {public *;}
#-keep public class com.tencent.smtt.sdk.WebView {public <fields>;public <methods>;}
#-keep public class com.tencent.smtt.sdk.WebView$HitTestResult {public static final <fields>;public java.lang.String getExtra();public int getType();}
#-keep public class com.tencent.smtt.sdk.WebView$WebViewTransport {public <methods>;}
#-keep public class com.tencent.smtt.sdk.WebView$PictureListener {public <fields>;public <methods>;}
#-keepattributes InnerClasses
#-keep public enum com.tencent.smtt.sdk.WebSettings$** {*;}
#-keep public enum com.tencent.smtt.sdk.QbSdk$** {*;}
#-keep public class com.tencent.smtt.sdk.WebSettings {public *;}
#-keepattributes Signature
#-keep public class com.tencent.smtt.sdk.ValueCallback {public <fields>;public <methods>;}
#-keep public class com.tencent.smtt.sdk.WebViewClient {public <fields>;public <methods>;}
#-keep public class com.tencent.smtt.sdk.DownloadListener {public <fields>;public <methods>;}
#-keep public class com.tencent.smtt.sdk.WebChromeClient {public <fields>;public <methods>;}
#-keep public class com.tencent.smtt.sdk.WebChromeClient$FileChooserParams {public <fields>;public <methods>;}
#-keep class com.tencent.smtt.sdk.SystemWebChromeClient{public *;}
## 1. extension interfaces should be apparent
#-keep public class com.tencent.smtt.export.external.extension.interfaces.* {public protected *;}
## 2. interfaces should be apparent
#-keep public class com.tencent.smtt.export.external.interfaces.* {public protected *;}
#-keep public class com.tencent.smtt.sdk.WebViewCallbackClient {public protected *;}
#-keep public class com.tencent.smtt.sdk.WebStorage$QuotaUpdater {public <fields>;public <methods>;}
#-keep public class com.tencent.smtt.sdk.WebIconDatabase {public <fields>;public <methods>;}
#-keep public class com.tencent.smtt.sdk.WebStorage {public <fields>;	public <methods>;}
#-keep public class com.tencent.smtt.sdk.DownloadListener {public <fields>;public <methods>;}
#-keep public class com.tencent.smtt.sdk.QbSdk {public <fields>;public <methods>;}
#-keep public class com.tencent.smtt.sdk.QbSdk$PreInitCallback {	public <fields>;public <methods>;}
#-keep public class com.tencent.smtt.sdk.CookieSyncManager {public <fields>;public <methods>;}
#-keep public class com.tencent.smtt.sdk.Tbs* {public <fields>;public <methods>;}
#-keep public class com.tencent.smtt.utils.LogFileUtils {	public <fields>;	public <methods>;}
#-keep public class com.tencent.smtt.utils.TbsLog {public <fields>;public <methods>;}
#-keep public class com.tencent.smtt.utils.TbsLogClient {public <fields>;public <methods>;}
#-keep public class com.tencent.smtt.sdk.CookieSyncManager {public <fields>;public <methods>;}
## Added for game demos
#-keep public class com.tencent.smtt.sdk.TBSGamePlayer {public <fields>;	public <methods>;}
#-keep public class com.tencent.smtt.sdk.TBSGamePlayerClient* {public <fields>;public <methods>;}
#-keep public class com.tencent.smtt.sdk.TBSGamePlayerClientExtension {public <fields>;	public <methods>;}
#-keep public class com.tencent.smtt.sdk.TBSGamePlayerService* {public <fields>;public <methods>;}
#-keep public class com.tencent.smtt.utils.Apn {public <fields>;public <methods>;}
#-keep class com.tencent.smtt.** {*;}
## end
#-keep public class com.tencent.smtt.export.external.extension.proxy.ProxyWebViewClientExtension {public <fields>;public <methods>;}
#-keep class MTT.ThirdAppInfoNew {*;}
#-keep class com.tencent.mtt.MttTraceEvent {*;}
## Game related
#-keep public class com.tencent.smtt.gamesdk.* {public protected *;}
#-keep public class com.tencent.smtt.sdk.TBSGameBooter {public <fields>;public <methods>;}
#-keep public class com.tencent.smtt.sdk.TBSGameBaseActivity {public protected *;}
#-keep public class com.tencent.smtt.sdk.TBSGameBaseActivityProxy {public protected *;}
#-keep public class com.tencent.smtt.gamesdk.internal.TBSGameServiceClient {public *;}