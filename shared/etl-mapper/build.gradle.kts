plugins {
    kotlin("jvm") version "2.3.0"
}

group = "com.khan366kos"
version = "0.0.1"

repositories {
    mavenCentral()
}

dependencies {
    implementation(project(":shared:common-models"))
    implementation(project(":shared:transport-kmp"))
    testImplementation(kotlin("test"))
}

tasks.test {
    useJUnitPlatform()
}