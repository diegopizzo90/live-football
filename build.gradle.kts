// Top-level build file where you can add configuration options common to all sub-projects/modules.
plugins {
    alias(libs.plugins.android.application) apply false
    alias(libs.plugins.jetbrains.kotlin.android) apply false
    alias(libs.plugins.android.library) apply false
}

allprojects {
    val javaVersion by extra(JavaVersion.VERSION_11)
}

task("clean") {
    delete(rootProject.layout.buildDirectory)
}