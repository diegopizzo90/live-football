plugins {
    id(libs.plugins.android.library.get().pluginId)
    id(libs.plugins.jetbrains.kotlin.android.get().pluginId)
    id(libs.plugins.liveFootball.plugin.get().pluginId)
}

android {
    namespace = "com.diegopizzo.android_test_utils"
}

dependencies {
    api(libs.android.coroutines.test)
    api(libs.androidx.junit)
    api(libs.androidx.espresso.core)
}
