plugins {
    id(libs.plugins.android.library.get().pluginId)
    id(libs.plugins.jetbrains.kotlin.android.get().pluginId)
    id(libs.plugins.liveFootball.plugin.get().pluginId)
    id(libs.plugins.ksp.plugin.get().pluginId)
}

liveFootballPlugin {
    composeEnabled = false
}

android {
    namespace = "com.diegopizzo.livefootball.core_api"
}

dependencies {
    api(libs.coroutines.core)
    api(libs.koin)
    api(platform(libs.ktor.bom))
    api(libs.ktor.serialization)
    api(libs.ktor.serialization.json)
    api(libs.ktor.content.negotiation)
    api(libs.ktor.logging)
    api(libs.kotlin.serialization)
    api(libs.store5)
    api(libs.sqldelight.runtime)
    api(libs.sqldelight.coroutines.extensions)
    implementation(libs.ktor.test.mock)
}
