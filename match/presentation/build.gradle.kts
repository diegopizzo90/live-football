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
        commonMain {
            dependencies {
                implementation(project(":core"))
                implementation(project(":league:domain"))
                implementation(project(":match:domain"))
                implementation(project(":design"))
            }
        }
        androidMain {
            dependencies {
                implementation(project(":design"))
            }
        }
        commonTest {
            dependencies {
                implementation(project(":test_utils"))
            }
        }
        iosMain {
            dependencies {}
        }
    }
}

android {
    namespace = "com.diegopizzo.livefootball.match.presentation"
}
