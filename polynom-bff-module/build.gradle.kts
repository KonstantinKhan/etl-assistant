plugins {
    alias(libs.plugins.kotlin.jvm)
    `maven-publish`
}

group = "com.khan366kos"
version = "0.0.1"

repositories {
    mavenCentral()
}

dependencies {
    implementation(libs.ktor.client.core)
    implementation(libs.ktor.client.cio)
    implementation(libs.ktor.client.content.negotiation)
    implementation(libs.ktor.serialization.kotlinx.json)

    // Standard Kotlin libraries
    implementation(kotlin("stdlib"))

    // Test dependencies
    testImplementation(libs.kotest)
    testImplementation(libs.kotest.runner)
}

kotlin {
    jvmToolchain(21)
}

publishing {
    publications {
        create<MavenPublication>("maven") {
            groupId = "com.khan366kos"
            artifactId = "bff-module"
            version = "0.0.1"
            from(components["java"])
        }
    }
}

tasks.test {
    useJUnitPlatform()
}