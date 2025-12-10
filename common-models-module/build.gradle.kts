plugins {
    kotlin("jvm")
    id("java-library") // Using java-library to support publishing
    id("org.jetbrains.kotlin.plugin.serialization") // For kotlinx.serialization
}

group = "com.khan366kos"
version = "0.0.1"

repositories {
    mavenCentral()
}

dependencies {
    // Add dependencies needed for common models
    implementation(kotlin("stdlib"))
    implementation(libs.kotlinx.serialization.json)
    implementation(libs.kotlinx.coroutines.core)
    
    testImplementation(libs.kotest)
    testImplementation(libs.kotest.runner)
}

tasks.test {
    useJUnitPlatform()
}

kotlin {
    jvmToolchain(21)
}