plugins {
    id(libs.plugins.android.library.get().pluginId)
    id(libs.plugins.jetbrains.kotlin.android.get().pluginId)
    id(libs.plugins.liveFootball.plugin.get().pluginId)
    id(libs.plugins.ksp.plugin.get().pluginId)
}

android {
    namespace = "com.diegopizzo.livefootball.match.domain"
}

dependencies {
    implementation(project(path = ":core"))

    testImplementation(project(path = ":test_utils"))
}
