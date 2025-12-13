plugins {
    alias(libs.plugins.kotlin.jvm)
    alias(libs.plugins.kotlin.plugin.serialization)
}

dependencies {
    implementation(projects.commonModelsModule)
    implementation(projects.loggingModule)
    implementation(projects.polynomBffModule)
    implementation(projects.parserModule)
    implementation(projects.excelModule)

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