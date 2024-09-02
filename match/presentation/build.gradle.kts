plugins {
    id(libs.plugins.android.library.get().pluginId)
    id(libs.plugins.jetbrains.kotlin.android.get().pluginId)
    id(libs.plugins.liveFootball.plugin.get().pluginId)
}

android {
    namespace = "com.diegopizzo.match.presentation"
}

dependencies {
    implementation(project(path = ":core"))
    implementation(project(path = ":design"))
    implementation(project(path = ":league"))
    implementation(project(path = ":match:api"))

    androidTestImplementation(project(path = ":android_test_utils"))
    testImplementation(project(path = ":test_utils"))
}
