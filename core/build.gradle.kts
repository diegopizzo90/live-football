plugins {
    id(libs.plugins.android.library.get().pluginId)
    id(libs.plugins.jetbrains.kotlin.android.get().pluginId)
    id(libs.plugins.liveFootball.plugin.get().pluginId)
}

android {
    namespace = "com.diegopizzo.core_api"
}

dependencies {
    api(libs.android.coroutines)
    api(libs.koin)
    api(libs.ktor.android)
    api(libs.ktor.serialization)
    api(libs.ktor.logging)
    api(libs.kotlin.serialization)

    //Compose
    api(platform(libs.compose.bom))
    api(libs.compose.coil)
    api(libs.compose.ui)
    api(libs.compose.uiTooling) {
        exclude(group = "androidx.compose.material", module = "material")
    }
    api(libs.compose.design)

    api(libs.android.coroutines.test)
}