plugins {
    alias(libs.plugins.kotlin.jvm)
}

group = "com.khan366kos"
version = "0.0.1"

repositories {
    mavenCentral()
}

kotlin {
    jvmToolchain(21)
}

dependencies {
    implementation(projects.shared.etlCommonModels)
    implementation(projects.shared.etlTransportKmp)
    testImplementation(kotlin("test"))
}

tasks.test {
    useJUnitPlatform()
}