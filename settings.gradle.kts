rootProject.name = "etl-assistant"
enableFeaturePreview("TYPESAFE_PROJECT_ACCESSORS")

include("parser-module")
include("console-app-module")
include("polynom-bff-module")
include("common-models-module")
include("excel-module")
include("logging-module")

dependencyResolutionManagement {
    repositories {
        mavenCentral()
    }
}
