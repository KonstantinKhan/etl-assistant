plugins {
    alias(libs.plugins.kotlin.jvm)
    alias(libs.plugins.flyway)
}

repositories {
    mavenCentral()
}

dependencies {
    implementation(projects.commonModelsModule)
    implementation(libs.exposed.core)
    implementation(libs.exposed.dao)
    implementation(libs.exposed.jdbc)
    implementation(libs.hikaricp)
    implementation(libs.sqlite.jdbc)
    implementation(libs.flyway.core)

}

kotlin {
    jvmToolchain(21)
}

tasks.test {
    useJUnitPlatform()
}