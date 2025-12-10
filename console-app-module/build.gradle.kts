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
    implementation(kotlin("stdlib"))

    implementation(projects.commonModelsModule)
    implementation(projects.polynomBffModule)
    implementation(projects.parserModule)

    implementation(libs.kotlinx.coroutines.core)

    implementation(libs.kotlinx.serialization.json)


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