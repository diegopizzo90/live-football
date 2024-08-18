import org.jetbrains.kotlin.gradle.dsl.JvmTarget
import org.jetbrains.kotlin.gradle.tasks.KotlinJvmCompile
import java.net.URI

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
    id(libs.plugins.compose.compiler.get().pluginId) version libs.versions.kotlin apply false
    id(libs.plugins.ksp.plugin.get().pluginId) version libs.versions.ksp apply false
    id(libs.plugins.jetbrains.kotlin.serialization.get().pluginId) version libs.versions.kotlin
}

allprojects {
    tasks.withType<KotlinJvmCompile> {
        compilerOptions {
            jvmTarget.set(JvmTarget.fromTarget(libs.versions.jvmTarget.get()))
            freeCompilerArgs.add("-opt-in=kotlin.RequiresOptIn")
        }
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
        maven { url = URI("https://oss.sonatype.org/content/repositories/snapshots/") }
    }
}

task("clean") {
    delete(rootProject.layout.buildDirectory)
}
