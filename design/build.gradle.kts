plugins {
    id(libs.plugins.android.library.get().pluginId)
    id(libs.plugins.jetbrains.kotlin.android.get().pluginId)
    id(libs.plugins.liveFootball.plugin.get().pluginId)
    id(libs.plugins.compose.compiler.get().pluginId)
}

android {
    namespace = "com.diegopizzo.livefootball.design"
}

dependencies {
    implementation(project(path = ":core"))
    implementation(libs.androidx.core.ktx)
    implementation(libs.androidx.appcompat)

    //Compose
    api(platform(libs.compose.bom))
    api(libs.compose.coil)
    api(libs.compose.ui)
    api(libs.compose.uiTooling) {
        exclude(group = "androidx.compose.material", module = "material")
    }
    api(libs.compose.design)
    api(libs.compose.activity)

    api(libs.compose.lottie)
    api(libs.compose.haze)
}
