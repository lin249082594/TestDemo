# Add project specific ProGuard rules here.
# By default, the flags in this file are appended to flags specified
# in D:\androidsdk/tools/proguard/proguard-android.txt
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

## glide
#-keep public class * implements com.bumptech.glide.module.GlideModule
#-keep public class * extends com.bumptech.glide.module.AppGlideModule
#-keep public enum com.bumptech.glide.load.ImageHeaderParser$** {
#  **[] $VALUES;
#  public *;
#}
#
## for DexGuard only
#-keepresourcexmlelements manifest/application/meta-data@value=GlideModule
#
#
## glide
##-keep public class * implements com.bumptech.glide.module.GlideModule
##-keep public class * extends com.bumptech.glide.module.AppGlideModule
##-keep public enum com.bumptech.glide.load.ImageHeaderParser$** {
##  **[] $VALUES;
##  public *;
##}
###glide如果你的API级别<=Android API 27 则需要添加
##-dontwarn com.bumptech.glide.load.resource.bitmap.VideoDecoder
#
#
### okhttp
#-dontwarn okhttp3.**
#-dontwarn okio.**
#-dontwarn javax.annotation.**
## A resource is loaded with a relative path so the package of this class must be preserved.
#-keepnames class okhttp3.internal.publicsuffix.PublicSuffixDatabase
#
#
#-keep class com.linxf.base.model.**{*;}
#-keep class com.linxf.base.constant.**{*;}

