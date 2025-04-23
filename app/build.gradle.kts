plugins {
    id(libs.plugins.android.application.get().pluginId)
    id(libs.plugins.jetbrains.kotlin.android.get().pluginId)
    id(libs.plugins.liveFootball.plugin.get().pluginId)
    id(libs.plugins.compose.compiler.get().pluginId)
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

dependencies {
    implementation(libs.androidx.core.ktx)
    implementation(libs.androidx.appcompat)
    implementation(libs.compose.navigation)
    implementation(libs.ktor.android)
    implementation(libs.sqldelight.android.driver)
    implementation(project(":design"))
    implementation(project(":core"))
    implementation(project(":league:api"))
    implementation(project(":league:domain"))
    implementation(project(":match:api"))
    implementation(project(":match:domain"))
    implementation(project(":match:presentation"))
    testImplementation(project(":test_utils"))
    androidTestImplementation(project(":android_test_utils"))
}
