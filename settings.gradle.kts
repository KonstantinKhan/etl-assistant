rootProject.name = "etl-assistant"

include("parser-module")
include("web-module")
include("console-app-module")
include("polynom-bff-module")

dependencyResolutionManagement {
    repositories {
        mavenCentral()
    }
}
