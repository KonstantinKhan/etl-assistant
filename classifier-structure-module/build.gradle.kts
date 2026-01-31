plugins {
    kotlin("jvm") version "2.2.20"
    application
}

group = "com.khan366kos"
version = "0.0.1"

repositories {
    mavenCentral()
}

dependencies {
    implementation(libs.poi)
    implementation(projects.shared.commonModels)
    implementation(projects.databaseModule)
    implementation(libs.exposed.core)
    implementation(libs.exposed.dao)
    implementation(libs.exposed.jdbc)
    implementation(libs.sqlite.jdbc)
    implementation(libs.flyway.core)

    testImplementation(kotlin("test"))
}

kotlin {
    jvmToolchain(21)
}

application {
    mainClass.set("MainKt")
}

tasks.test {
    useJUnitPlatform()
}