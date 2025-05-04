plugins {
    id(libs.plugins.kotlin.multiplatform.get().pluginId)
    id(libs.plugins.android.library.get().pluginId)
    id(libs.plugins.liveFootball.plugin.get().pluginId)
    id(libs.plugins.sqldelight.plugin.get().pluginId)
    id(libs.plugins.mockative.plugin.get().pluginId) version libs.versions.mockative
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
                implementation(project(path = ":match:domain"))
            }
        }
        androidMain {
            dependencies {}
        }
        commonTest {
            dependencies {
                implementation(project(":test_utils"))
                implementation(libs.mockative)
                implementation(libs.sqldelight.runtime)
            }
        }
        iosMain {
            dependencies {}
        }
        jvmTest {
            dependencies {}
        }
        iosTest {
            dependencies {}
        }
    }
}

sqldelight {
    databases {
        create("MatchDatabase") {
            packageName.set("sqldelight.database")
        }
    }
}

android {
    namespace = "com.diegopizzo.livefootball.match.api"
}
