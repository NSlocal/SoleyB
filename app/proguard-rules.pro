# Solely Browser — R8 Compatible Rules
# ====================================

# Keep all app code
-keep class com.solely.browser.** { *; }

# Suppress warnings
-dontwarn android.webkit.**
-keepattributes *Annotation*

# Optimization — R8 handles automatically
-optimizations !code/allocation/variable

# Strip debug logs in release
-assumenosideeffects class android.util.Log {
    public static boolean isLoggable(java.lang.String, int);
    public static int v(...);
    public static int d(...);
}
