plugins {
    alias(libs.plugins.kotlin.jvm)
}

dependencies {
    implementation(libs.logback.classic)
}

tasks.test {
    useJUnitPlatform()
}

kotlin {
    jvmToolchain(21)
}