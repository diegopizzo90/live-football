plugins {
    id(libs.plugins.android.library.get().pluginId)
    id(libs.plugins.jetbrains.kotlin.android.get().pluginId)
    id(libs.plugins.liveFootball.plugin.get().pluginId)
}

android {
    namespace = "com.diegopizzo.feature.presentation"
}

dependencies {
    implementation(project(path = ":core"))

    androidTestImplementation(project(":android_test_utils"))
    testImplementation(project(":test_utils"))
}
