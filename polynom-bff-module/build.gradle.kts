plugins {
    alias(libs.plugins.kotlin.jvm)
    alias(libs.plugins.kotlin.plugin.serialization)
}

dependencies {
    implementation(projects.shared.commonModels)
    implementation(projects.shared.transportKmp)

    implementation(libs.ktor.client.core)
    implementation(libs.ktor.client.cio)
    implementation(libs.ktor.client.content.negotiation)
    implementation(libs.ktor.serialization.kotlinx.json)

    implementation(libs.logback.classic)

    implementation(libs.logging)

}

tasks.test {
    useJUnitPlatform()
}

kotlin {
    jvmToolchain(21)
}