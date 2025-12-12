plugins {
    alias(libs.plugins.kotlin.jvm)
}

dependencies {
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