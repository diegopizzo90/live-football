package com.diegopizzo.build

import com.android.build.api.dsl.ApplicationExtension
import com.android.build.gradle.AppPlugin
import com.android.build.gradle.LibraryExtension
import com.android.build.gradle.LibraryPlugin
import io.gitlab.arturbosch.detekt.extensions.DetektExtension
import org.gradle.api.JavaVersion
import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.api.artifacts.VersionCatalog
import org.gradle.api.artifacts.VersionCatalogsExtension
import org.gradle.api.tasks.JavaExec
import java.io.File
import java.io.FileInputStream
import java.util.Properties

private const val COMPILE_SDK = 34
private const val TARGET_SDK = 33
private const val MIN_SDK = 29

class LiveFootballPlugin : Plugin<Project> {

    private lateinit var versionCatalog: VersionCatalog

    override fun apply(target: Project) {
        versionCatalog =
            target.extensions.getByType(VersionCatalogsExtension::class.java).named("libs")

        target.configureDetekt()
        target.configureKtlint()
        target.configureKotlinSerialization()

        target.plugins.all {
            when (this) {
                is LibraryPlugin -> {
                    target.extensions.configure(LibraryExtension::class.java, configureLibrary)
                }

                is AppPlugin -> {
                    target.extensions.configure(ApplicationExtension::class.java) {
                        configureApp(this, getAppVersion())
                    }
                }
            }
        }
    }

    private val configureLibrary: (LibraryExtension) -> Unit = { library ->
        library.apply {
            compileSdk = COMPILE_SDK
            compileOptions {
                sourceCompatibility = JavaVersion.VERSION_11
                targetCompatibility = JavaVersion.VERSION_11
            }

            defaultConfig {
                minSdk = MIN_SDK
                multiDexEnabled = true
                testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
            }

            buildFeatures.buildConfig = false
            buildFeatures.compose = true
            composeOptions.kotlinCompilerExtensionVersion =
                versionCatalog.findVersion("kotlinComposeCompiler").get().requiredVersion
        }
    }

    private val configureApp: (ApplicationExtension, AppVersion) -> Unit = { app, appVersion ->
        app.apply {
            compileSdk = COMPILE_SDK
            compileOptions {
                sourceCompatibility = JavaVersion.VERSION_11
                targetCompatibility = JavaVersion.VERSION_11
            }

            defaultConfig {
                minSdk = MIN_SDK
                targetSdk = TARGET_SDK
                multiDexEnabled = true
                testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
                versionCode = appVersion.versionCode
                versionName = appVersion.versionName
            }

            sourceSets.getByName("androidTest").assets.setSrcDirs(listOf("src/androidTest/assets"))

            buildFeatures.compose = true
            composeOptions.kotlinCompilerExtensionVersion =
                versionCatalog.findVersion("kotlinComposeCompiler").get().requiredVersion
        }
    }

    private fun getAppVersion(): AppVersion {
        val properties = Properties().apply {
            load(FileInputStream(File("version.properties")))
        }
        return AppVersion(
            versionName = properties.getProperty("versionName"),
            versionCode = properties.getProperty("buildNumber").toInt(),
        )
    }

    data class AppVersion(val versionName: String, val versionCode: Int)


    private fun Project.configureKotlinSerialization() {
        dependencies.addProvider(
            "implementation",
            versionCatalog.findLibrary("kotlin-serialization").get(),
        )
        plugins.apply("org.jetbrains.kotlin.plugin.serialization")
    }
}

private const val DETEKT_PLUGIN_ID = "io.gitlab.arturbosch.detekt"
private fun Project.configureDetekt() {
    plugins.apply(DETEKT_PLUGIN_ID)
    plugins.withId(DETEKT_PLUGIN_ID) {
        extensions.configure(DetektExtension::class.java) {
            config.setFrom(files("${project.rootDir}/config/detekt/detekt.yml"))
            buildUponDefaultConfig = true
            source.setFrom(files("${project.rootDir}/${project.name}/src/main/kotlin"))
        }
    }
}

private fun Project.configureKtlint() {
    val ktlint = configurations.create("ktlint")

    val catalog = extensions.getByType(VersionCatalogsExtension::class.java).named("libs")

    dependencies.addProvider("ktlint", catalog.findLibrary("ktlint").get())

    val inputFiles = project.fileTree(mapOf("dir" to "src", "include" to "**/*.kt"))

    tasks.register("ktlint", JavaExec::class.java) {
        inputs.files(inputFiles)
        group = "verification"
        description = "Check Kotlin code style."
        classpath = ktlint
        mainClass.set("com.pinterest.ktlint.Main")
        args = listOf("src/**/*.kt")
    }

    tasks.register("ktlintFormat", JavaExec::class.java) {
        inputs.files(inputFiles)
        group = "formatting"
        description = "Fix Kotlin code style deviations."
        classpath = ktlint
        mainClass.set("com.pinterest.ktlint.Main")
        args = listOf("-F", "src/**/*.kt")
        jvmArgs("--add-opens", "java.base/java.lang=ALL-UNNAMED")
    }
}
