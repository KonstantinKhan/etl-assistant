plugins {
    alias(libs.plugins.kotlin.jvm)
    alias(libs.plugins.kotlin.plugin.serialization)
}

dependencies {
    implementation(projects.commonModelsModule)
    implementation(projects.polynomBffModule)
    implementation(projects.parserModule)
    implementation(projects.excelModule)
    implementation(projects.loggingModule)

    implementation(libs.kotlinx.coroutines.core)

    testImplementation(libs.kotest)
    testImplementation(libs.kotest.runner)
}

kotlin {
    jvmToolchain(21)
}

tasks.test {
    useJUnitPlatform()
}