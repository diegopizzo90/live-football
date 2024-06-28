import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

// Top-level build file where you can add configuration options common to all sub-projects/modules.
buildscript {
    dependencies {
        classpath(libs.gradlePlugin.agp)
    }

    repositories {
        gradlePluginPortal()
        google {
            content {
                includeGroupByRegex("com\\.android.*")
                includeGroupByRegex("com\\.google.*")
                includeGroupByRegex("androidx.*")
            }
        }
        mavenCentral()
    }

    dependencies {
        classpath(libs.gradlePlugin.agp)
        classpath(libs.gradlePlugin.kotlin)
        classpath(libs.gradlePlugin.kotlinSerialization)
    }
}

plugins {
    id(libs.plugins.android.application.get().pluginId) apply false
    id(libs.plugins.android.library.get().pluginId) apply false
    id(libs.plugins.jetbrains.kotlin.android.get().pluginId) apply false
}

allprojects {
    tasks.withType<KotlinCompile> {
        kotlinOptions.jvmTarget = libs.versions.jvmTarget.get()
    }

    tasks.withType<JavaCompile> {
        sourceCompatibility = libs.versions.jvmTarget.get()
        targetCompatibility = libs.versions.jvmTarget.get()
    }

    repositories {
        gradlePluginPortal()
        google {
            content {
                includeGroupByRegex("com\\.android.*")
                includeGroupByRegex("com\\.google.*")
                includeGroupByRegex("androidx.*")
            }
        }
        mavenCentral()
    }
}

task("clean") {
    delete(rootProject.layout.buildDirectory)
}
