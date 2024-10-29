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

# Koin
-keepattributes *Annotation*
-keep class org.koin.** { *; }
-keepclassmembers class * {
    @org.koin.core.annotation.* <methods>;
}
-dontwarn org.koin.**

# Room
-keep class androidx.room.** { *; }
-keepclassmembers class androidx.room.** { *; }
-dontwarn androidx.room.**
-keepclassmembers class * {
    @androidx.room.* <fields>;
    @androidx.room.* <methods>;
}

# Kotlin serialization
-keepattributes *Annotation*, InnerClasses
-dontnote kotlinx.serialization.AnnotationsKt
-keepclassmembers class kotlinx.serialization.json.** {
    *** Companion;
}
-keepclasseswithmembers class kotlinx.serialization.json.** {
    kotlinx.serialization.KSerializer serializer(...);
}
-keep,includedescriptorclasses class com.diegopizzo.**$$serializer { *; }
-keepclassmembers class com.diegopizzo.** {
    *** Companion;
}
-keepclasseswithmembers class com.diegopizzo.** {
    kotlinx.serialization.KSerializer serializer(...);
}

# Keep rules for OkHttp
-keep class okhttp3.** { *; }
-dontwarn okhttp3.**

# Ktor
-keep class io.ktor.** { *; }
-keep class io.ktor.client.** { *; }
-dontwarn io.ktor.**

-keepclassmembers enum * { *; }

# MockK - Ignore warnings
-dontwarn io.mockk.**

# Don't include test libraries in production builds
-dontwarn org.junit.**
-dontwarn androidx.test.**
-dontwarn androidx.test.espresso.**
-dontwarn org.slf4j.impl.StaticLoggerBinder