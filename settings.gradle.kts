rootProject.name = "etl-assistant"
enableFeaturePreview("TYPESAFE_PROJECT_ACCESSORS")

include(":shared:transport-kmp")
include(":shared:common-models")
include("parser-module")
include("console-app-module")
include("polynom-bff-module")
include(":backend:excel")
include("logging-module")
include("classifier-structure-module")
include("database-module")

dependencyResolutionManagement {
    repositories {
        mavenCentral()
    }
}
include("backend")
include("shared")
include("frontend")
include("frontend:kotlin-react-app")