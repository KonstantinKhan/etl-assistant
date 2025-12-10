plugins {
    kotlin("jvm")
}

group = "com.khan366kos"
version = "0.0.1"

repositories {
    mavenCentral()
}

dependencies {
    implementation(libs.poi)
    implementation(projects.commonModelsModule)

    testImplementation(libs.kotest)
    testImplementation(libs.kotest.runner)
}


kotlin {
    jvmToolchain(21)
}