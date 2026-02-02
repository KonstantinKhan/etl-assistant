rootProject.name = "etl-assistant"
enableFeaturePreview("TYPESAFE_PROJECT_ACCESSORS")


include(":shared")
include(":shared:transport-kmp")
include(":shared:common-models")

include(":backend")
include(":backend:etl-excel-service")
include(":backend:etl-ktor-server-app")

include(":frontend")
include(":frontend:kotlin-react-app")

include("parser-module")
include("console-app-module")
include("polynom-bff-module")
include("logging-module")
include("classifier-structure-module")
include("database-module")



dependencyResolutionManagement {
    repositories {
        mavenCentral()
    }
}
include("shared:etl-mapper")