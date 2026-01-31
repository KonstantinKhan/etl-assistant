plugins {
    alias(libs.plugins.kotlin.jvm)
    alias(libs.plugins.kotlin.plugin.serialization)
}

dependencies {
    implementation(libs.kotlinx.serialization.json)

}

tasks.test {
    useJUnitPlatform()
}

kotlin {
    jvmToolchain(21)
}