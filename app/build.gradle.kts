plugins {
    id(libs.plugins.kotlin.multiplatform.get().pluginId)
    id(libs.plugins.android.application.get().pluginId)
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
                implementation(project(":league:api"))
                implementation(project(":league:domain"))
                implementation(project(":match:api"))
                implementation(project(":match:domain"))
                implementation(project(":match:presentation"))
                implementation(project(":design"))
            }
        }
        androidMain {
            dependencies {
                implementation(libs.androidx.core.ktx)
                implementation(libs.androidx.appcompat)
                implementation(libs.compose.navigation)
                implementation(libs.ktor.android)
                implementation(libs.sqldelight.android.driver)
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
    namespace = "com.diegopizzo.livefootball"

    defaultConfig {
        applicationId = "com.diegopizzo.livefootball"
    }

    buildTypes {
        debug {
            isMinifyEnabled = false
            applicationIdSuffix = ".debug"
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "../proguard-rules.pro",
            )
        }

        release {
            isMinifyEnabled = true
            isShrinkResources = true
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "../proguard-rules.pro",
            )
        }
    }
}
