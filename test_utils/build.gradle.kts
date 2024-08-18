plugins {
    id(libs.plugins.android.library.get().pluginId)
    id(libs.plugins.jetbrains.kotlin.android.get().pluginId)
    id(libs.plugins.liveFootball.plugin.get().pluginId)
}

android {
    namespace = "com.diegopizzo.test_utils"
}

dependencies {
    implementation(project(path = ":core"))

    api(libs.junit)
    api(libs.mockk)
    api(libs.android.coroutines.test)
    api(libs.junit)
    api(libs.ktor.test.mock)
}
