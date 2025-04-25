plugins {
    id(libs.plugins.android.library.get().pluginId)
    id(libs.plugins.jetbrains.kotlin.android.get().pluginId)
    id(libs.plugins.liveFootball.plugin.get().pluginId)
    id(libs.plugins.ksp.plugin.get().pluginId)
    id(libs.plugins.sqldelight.plugin.get().pluginId)
}

android {
    namespace = "com.diegopizzo.livefootball.match.api"
}

sqldelight {
    databases {
        create("MatchDatabase") {
            packageName.set("sqldelight.database")
        }
    }
}

dependencies {
    implementation(project(path = ":core"))
    implementation(project(path = ":match:domain"))

    androidTestImplementation(project(":android_test_utils"))
    testImplementation(project(":test_utils"))
}
