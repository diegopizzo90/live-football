plugins {
    id(libs.plugins.android.library.get().pluginId)
    id(libs.plugins.jetbrains.kotlin.android.get().pluginId)
    id(libs.plugins.liveFootball.plugin.get().pluginId)
}

liveFootballPlugin {
    composeEnabled = false
}

android {
    namespace = "com.diegopizzo.livefootball.android_test_utils"
}

dependencies {
    api(libs.coroutines.test)
    api(libs.androidx.junit)
    api(libs.androidx.espresso.core)
}
