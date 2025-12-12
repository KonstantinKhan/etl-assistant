plugins {
    alias(libs.plugins.kotlin.jvm)
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