plugins {
    alias(libs.plugins.kotlin.jvm)
    alias(libs.plugins.kotlin.plugin.serialization)
}

dependencies {
    implementation(libs.kotlinx.coroutines.core)

    implementation(projects.shared.etlCommonModels)
    implementation(projects.shared.etlTransportKmp)

    implementation(projects.backend.etlExcelService)

    implementation(projects.loggingModule)
    implementation(projects.backend.etlPolynomBff)
    implementation(projects.parserModule)
}

kotlin {
    jvmToolchain(21)
}

tasks.test {
    useJUnitPlatform()
}
