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
        val commonMain by getting {
            dependencies {
                api(libs.coroutines.core)
                api(libs.koin)
                api(project.dependencies.platform(libs.ktor.bom))
                api(libs.ktor.serialization)
                api(libs.ktor.serialization.json)
                api(libs.ktor.content.negotiation)
                api(libs.ktor.logging)
                api(libs.kotlin.serialization)
                api(libs.store5)
                api(libs.sqldelight.runtime)
                api(libs.sqldelight.coroutines.extensions)
                api(libs.kermit)
                implementation(libs.ktor.mock)
            }
        }
        val commonTest by getting {
            dependencies {
                implementation(kotlin("test"))
            }
        }
        val androidMain by getting {
            dependencies {
                api(libs.koin.android)
            }
        }
        val iosMain by creating {
            dependencies {}
        }
    }
}

android {
    namespace = "com.diegopizzo.livefootball.core"
}
