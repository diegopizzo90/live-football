plugins {
    id(libs.plugins.android.library.get().pluginId)
    id(libs.plugins.jetbrains.kotlin.android.get().pluginId)
    id(libs.plugins.liveFootball.plugin.get().pluginId)
}

android {
    namespace = "com.diegopizzo.design"
}

dependencies {

    implementation(libs.androidx.core.ktx)
    implementation(libs.androidx.appcompat)

    //Compose
    implementation(platform(libs.compose.bom))
    implementation(libs.compose.coil)
    implementation(libs.compose.ui)
    implementation(libs.compose.uiTooling) {
        exclude(group = "androidx.compose.material", module = "material")
    }
    implementation(libs.compose.design)
    api(libs.compose.lottie)
}
