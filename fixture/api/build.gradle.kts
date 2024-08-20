plugins {
    id(libs.plugins.android.library.get().pluginId)
    id(libs.plugins.jetbrains.kotlin.android.get().pluginId)
    id(libs.plugins.liveFootball.plugin.get().pluginId)
}

android {
    namespace = "com.diegopizzo.fixture.api"
}

dependencies {
    implementation(project(path = ":core"))

    testImplementation(project(":test_utils"))
}
