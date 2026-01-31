plugins {
    alias(libs.plugins.kotlin.jvm)
    alias(libs.plugins.kotlin.plugin.serialization)
}

dependencies {
    implementation(projects.shared.commonModels)
    implementation(projects.loggingModule)
    implementation(projects.polynomBffModule)
    implementation(projects.parserModule)
    implementation(projects.backend.excel)
    implementation(projects.shared.transportKmp)

    implementation(libs.kotlinx.coroutines.core)

}

kotlin {
    jvmToolchain(21)
}

tasks.test {
    useJUnitPlatform()
}