plugins {
    alias(libs.plugins.kotlin.multiplatform)
}

kotlin {
    js(IR) {
        browser {
            binaries.executable()
            commonWebpackConfig {
                devServer = devServer?.copy(
                    open = false
                )
                cssSupport {
                    enabled.set(true)
                }
            }
        }
        compilerOptions {
            optIn.add("kotlin.js.ExperimentalJsExport")
        }
    }

    sourceSets {

        jsMain.dependencies {
            implementation(libs.kotlin.react)
            implementation(libs.kotlin.react.core)
            implementation(libs.kotlin.react.dom)
            implementation(libs.kotlin.emotion.react)
            implementation(libs.kotlin.mui.material)

            implementation(npm("@emotion/styled", libs.versions.emotion.styled.get()))

            // HTTP client for backend requests
            implementation(libs.ktor.client.core)
            implementation(libs.ktor.client.js)
            implementation(libs.ktor.client.content.negotiation)
            implementation(libs.ktor.serialization.kotlinx.json)

            // Decompose
            implementation(libs.decompose)
            implementation(libs.decompose.lifecycle.coroutines)

            // Shared models for transport
            implementation(projects.shared.etlTransportKmp)
        }

    }
}





