# QWEN.md

This tool is designed to help with Extract, Transform, and Load processes.

## Rules and conventions

- !!!NECESSARILY!!! First of all, always read the documentation, if there is one. 
- Before completing a task, first of all, be sure to read the documentation for the module you plan to work with.
- First of all, check the modules and files according to the documentation, only if you do not find any data in it, you can scan the project.

## Project Structure

The project follows a modular architecture. Below is an overview of the modules:

| Module Name                               | Description                                                     | Docs                                                    |
|-------------------------------------------|-----------------------------------------------------------------|---------------------------------------------------------|
| `common-models-module`                    | Shared data models and business logic                           |                                                         |
| [console-app-module/](console-app-module) | Console application entry point                                 |                                                         |
| [excel-module/](excel-module)             | Excel file processing functionality                             |                                                         |
| [logging-module/](logging-module)         | Logging utilities and configurations                            |                                                         |
| [parser-module/](parser-module)           | Provides parsing functionalities.                               | [parser-module.md](docs/modules/parser-module.md)       |
| [polynom-bff-module/](polynom-bff-module) | Backend-for-frontend (BFF) module for polynom-related services. |                                                         |
| `transport-models`                        | Transport layer models                                          | [transport-models.md](docs/modules/transport-models.md) |

## Development

### Building the Project

### Running Tests

```bash
./gradlew test
```

For Windows, use:
```bash
.\gradlew.bat test
```

**Note:** If you encounter test discovery issues with Kotest's ShouldSpec in specific modules, make sure the module's `build.gradle.kts` contains:

```kotlin
tasks.test {
    useJUnitPlatform()
}
```

### Running the Application

```bash
./gradlew run
```

## Coding Conventions

- Use value classes for simple fields
- Use data classes for business models
- Separate business objects, requests, and responses into individual data classes
- Follow Kotest with Should Spec test style for testing

## Dependencies

Dependencies are managed through Gradle and defined in `libs.versions.toml`.

## Contributing

Follow the clean architecture principles and maintain consistent coding standards.

## Project settings

- The plugins section uses `alias` from [libs.versions.toml](/gradle/libs.versions.toml)
- Don't use `implementation(kotlin("stdlib"))`, the standard library is used via

```kotlin 
plugins {
    alias(libs.plugins.kotlin.jvm)
}
 ```