plugins {
    alias(libs.plugins.kotlin.jvm)
}

dependencies {
    implementation(projects.loggingModule)

    implementation(libs.kotlinx.serialization.json)
    implementation(libs.poi)

    testImplementation(libs.kotest)
    testImplementation(libs.kotest.runner)
}

tasks.test {
    useJUnitPlatform()
}

kotlin {
    jvmToolchain(21)
}