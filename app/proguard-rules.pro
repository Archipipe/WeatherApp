# Retrofit
-dontwarn retrofit2.**
-keep class retrofit2.** { *; }
-keepattributes Signature
-keepattributes Exceptions

# OkHttp
-dontwarn okhttp3.**
-keep class okhttp3.** { *; }

# Gson
-dontwarn com.google.gson.**
-keep class com.google.gson.** { *; }

# WebView
-keep class android.webkit.** { *; }
-dontwarn android.webkit.**

# Ваши модели данных
-keep class com.example.weatherapplicationretrofit.models.** { *; }
-keep class com.example.weatherapplicationretrofit.api.** { *; }

# Корутины
-dontwarn kotlinx.coroutines.**
-keep class kotlinx.coroutines.** { *; }

# Другие зависимости
-dontwarn org.jetbrains.**
-keep class org.jetbrains.** { *; }

# AndroidSVG
-keep class com.caverock.androidsvg.** { *; }
-dontwarn com.caverock.androidsvg.**

# Lottie
-keep class com.airbnb.lottie.** { *; }
-dontwarn com.airbnb.lottie.**

# Play Services Location
-keep class com.google.android.gms.location.** { *; }
-dontwarn com.google.android.gms.location.**

# ViewBinding
-keep class * extends androidx.viewbinding.ViewBinding {
    public static ** bind(android.view.View);
    public static ** inflate(android.view.LayoutInflater);
}