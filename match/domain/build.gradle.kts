plugins {
    id(libs.plugins.kotlin.multiplatform.get().pluginId)
    id(libs.plugins.android.library.get().pluginId)
    id(libs.plugins.liveFootball.plugin.get().pluginId)
}

liveFootballPlugin {
    composeEnabled = false
}

kotlin {
    androidTarget()
    iosX64()
    iosSimulatorArm64()
    iosArm64()

    sourceSets {
        commonMain {
            dependencies {
                implementation(project(path = ":core"))
            }
        }
        androidMain {
            dependencies {
            }
        }
        commonTest {
            dependencies {
                implementation(project(path = ":test_utils"))
            }
        }
        iosMain {
            dependencies {}
        }
    }
}

android {
    namespace = "com.diegopizzo.livefootball.match.domain"
}

