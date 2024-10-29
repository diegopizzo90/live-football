import org.jetbrains.kotlin.gradle.dsl.JvmTarget
import org.jetbrains.kotlin.gradle.tasks.KotlinJvmCompile

plugins {
    `kotlin-dsl`
    kotlin("jvm") version libs.versions.kotlin.get()
    `java-gradle-plugin`
}

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
    google()
    mavenCentral()
}

gradlePlugin {
    plugins {
        create("LiveFootballPlugin") {
            id = libs.plugins.liveFootball.plugin.get().pluginId
            implementationClass = "com.diegopizzo.build.LiveFootballPlugin"
        }
    }
}

dependencies {
    compileOnly(gradleApi())
    implementation(libs.gradlePlugin.agp)
    implementation(libs.gradlePlugin.kotlin)
    implementation(libs.detekt.plugin)
    implementation(libs.kotlin.serialization)
}
