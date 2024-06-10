import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

plugins {
    `kotlin-dsl`
    kotlin("jvm") version libs.versions.kotlin.get()
    `java-gradle-plugin`
}

tasks.withType<KotlinCompile> {
    kotlinOptions.jvmTarget = libs.versions.jvmTarget.get()
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
}
