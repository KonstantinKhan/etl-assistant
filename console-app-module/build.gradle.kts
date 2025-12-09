plugins {
    alias(libs.plugins.kotlin.jvm)
    alias(libs.plugins.kotlin.plugin.serialization)
    id("application")
}

group = "com.khan366kos"
version = "0.0.1"

repositories {
    mavenCentral()
}

dependencies {
    implementation(project(":polynom-bff-module"))

    // Coroutines for runBlocking
    implementation(libs.kotlinx.coroutines.core)

    // Serialization for @Serializable annotation
    implementation(libs.kotlinx.serialization.json)

    // Standard Kotlin libraries
    implementation(kotlin("stdlib"))

    // Test dependencies
    testImplementation(libs.kotest)
    testImplementation(libs.kotest.runner)
}

kotlin {
    jvmToolchain(21)
}

application {
    mainClass = "com.khan366kos.ConsoleAppKt"
}

tasks.test {
    useJUnitPlatform()
}