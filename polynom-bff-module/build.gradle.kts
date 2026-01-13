plugins {
    alias(libs.plugins.kotlin.jvm)
    alias(libs.plugins.kotlin.plugin.serialization)
}

dependencies {
    implementation(projects.commonModelsModule)
    implementation(projects.transportModels)

    implementation(libs.ktor.client.core)
    implementation(libs.ktor.client.cio)
    implementation(libs.ktor.client.content.negotiation)
    implementation(libs.ktor.serialization.kotlinx.json)

    implementation(libs.logback.classic)

    implementation(libs.logging)

    testImplementation(libs.kotest)
    testImplementation(libs.kotest.runner)
}

tasks.test {
    useJUnitPlatform()
}

kotlin {
    jvmToolchain(21)
}