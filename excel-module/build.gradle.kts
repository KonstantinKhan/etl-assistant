plugins {
    alias(libs.plugins.kotlin.jvm)
    application
}

dependencies {
    implementation(libs.poi)
    implementation(libs.kotlinx.coroutines.core)
    implementation(projects.commonModelsModule)

    testImplementation(libs.kotest)
    testImplementation(libs.kotest.runner)
}

tasks.test {
    useJUnitPlatform()
}

kotlin {
    jvmToolchain(21)
}

application {
    mainClass.set("khan366kos.excel.handler.GreetingMainKt")
}

tasks.test {
    useJUnitPlatform()
    failOnNoDiscoveredTests = false
}