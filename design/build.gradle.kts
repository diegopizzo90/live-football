plugins {
    id(libs.plugins.kotlin.multiplatform.get().pluginId)
    id(libs.plugins.android.library.get().pluginId)
    id(libs.plugins.liveFootball.plugin.get().pluginId)
    id(libs.plugins.compose.compiler.get().pluginId)
}

kotlin {
    androidTarget()
    iosX64()
    iosSimulatorArm64()
    iosArm64()

    sourceSets {
        val commonMain by getting {
            dependencies {}
        }
        val androidMain by getting {
            dependencies {
                implementation(project(":core"))
                implementation(libs.androidx.core.ktx)
                implementation(libs.androidx.appcompat)

                // Compose
                api(project.dependencies.platform(libs.compose.bom))
                api(libs.compose.coil)
                api(libs.compose.ui)
                api(libs.compose.uiTooling.get().toString()) {
                    exclude(group = "androidx.compose.material", module = "material")
                }
                api(libs.compose.design)
                api(libs.compose.activity)
                api(libs.compose.lottie)
                api(libs.compose.haze)
            }
        }
        val commonTest by getting {
            dependencies {
                implementation(kotlin("test"))
            }
        }
        val iosMain by creating {
            dependencies {}
        }
    }
}

android {
    namespace = "com.diegopizzo.livefootball.design"
}
