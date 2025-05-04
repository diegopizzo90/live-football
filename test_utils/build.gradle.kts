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
    jvm()
    iosX64()
    iosSimulatorArm64()
    iosArm64()

    sourceSets {
        commonMain {
            dependencies {
                implementation(project(":core"))

                api(kotlin("test"))
                api(libs.coroutines.test)
                api(libs.ktor.mock)
            }
        }
        androidMain {
            dependencies {
                implementation(libs.sqldelight.android.driver)
            }
        }
        jvmMain {
            dependencies {
                implementation(libs.sqldelight.driver.jvm)
            }
        }
        iosMain {
            dependencies {
                implementation(libs.sqldelight.driver.native)
            }
        }
    }
}

android {
    namespace = "com.diegopizzo.livefootball.test_utils"

    packaging {
        resources {
            excludes += setOf(
                "META-INF/LICENSE.md",
                "META-INF/LICENSE",
                "META-INF/NOTICE.md",
                "META-INF/NOTICE",
                "META-INF/LICENSE-notice.md",
            )
        }
    }
}

