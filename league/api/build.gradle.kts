plugins {
    id(libs.plugins.android.library.get().pluginId)
    id(libs.plugins.jetbrains.kotlin.android.get().pluginId)
    id(libs.plugins.liveFootball.plugin.get().pluginId)
    id(libs.plugins.sqldelight.plugin.get().pluginId)
}

android {
    namespace = "com.diegopizzo.livefootball.league.api"
}

sqldelight {
    databases {
        create("LeagueDatabase") {
            packageName.set("sqldelight.database")
        }
    }
}


dependencies {
    implementation(project(path = ":core"))
    implementation(project(path = ":league:domain"))
    androidTestImplementation(project(":android_test_utils"))
    testImplementation(project(":test_utils"))
}
