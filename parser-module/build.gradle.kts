plugins {
    alias(libs.plugins.kotlin.jvm)
}

dependencies {
    implementation(projects.loggingModule)

    implementation(libs.kotlinx.serialization.json)
    implementation(libs.poi)

}

tasks.test {
    useJUnitPlatform()
}

kotlin {
    jvmToolchain(21)
}