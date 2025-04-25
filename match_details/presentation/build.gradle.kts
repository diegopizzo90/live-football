plugins {
    id(libs.plugins.android.library.get().pluginId)
    id(libs.plugins.jetbrains.kotlin.android.get().pluginId)
    id(libs.plugins.liveFootball.plugin.get().pluginId)
}

android {
    namespace = "com.diegopizzo.livefootball.match_details.presentation"
}

dependencies {
    implementation(project(path = ":core"))
    implementation(project(path = ":design"))

    androidTestImplementation(project(path = ":android_test_utils"))
    testImplementation(project(path = ":test_utils"))
}
