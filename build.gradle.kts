plugins {
    alias(libs.plugins.kotlin.jvm)
    alias(libs.plugins.ktor)
    alias(libs.plugins.kotlin.plugin.serialization)
    id("application") // Add application plugin for main executable
}

group = "com.khan366kos"
version = "0.0.1"

repositories {
    mavenCentral()
}

// Common configurations for all subprojects
subprojects {
    apply(plugin = "org.jetbrains.kotlin.jvm")
    apply(plugin = "org.jetbrains.kotlin.plugin.serialization")

    repositories {
        mavenCentral()
    }

    tasks.test {
        useJUnitPlatform()
    }

    kotlin {
        jvmToolchain(21)
    }
}

// Configuration for the root project if needed
application {
    mainClass = "io.ktor.server.netty.EngineMain"
}
