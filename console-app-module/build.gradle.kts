plugins {
    alias(libs.plugins.kotlin.jvm)
    alias(libs.plugins.kotlin.plugin.serialization)
}

dependencies {
    implementation(libs.kotlinx.coroutines.core)

    implementation(projects.shared.etlCommonModels)
    implementation(projects.shared.transportKmp)

    implementation(projects.backend.etlExcelService)

    implementation(projects.loggingModule)
    implementation(projects.polynomBffModule)
    implementation(projects.parserModule)
}

kotlin {
    jvmToolchain(21)
}

tasks.test {
    useJUnitPlatform()
}
