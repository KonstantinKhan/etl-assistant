plugins {
    kotlin("jvm")
}

group = "com.khan366kos"
version = "0.0.1"

repositories {
    mavenCentral()
}

dependencies {
    // Add dependencies needed for logging
    implementation(kotlin("stdlib"))
    implementation(libs.logback.classic)
    
    testImplementation(libs.kotest)
    testImplementation(libs.kotest.runner)
}

tasks.test {
    useJUnitPlatform()
}

kotlin {
    jvmToolchain(21)
}