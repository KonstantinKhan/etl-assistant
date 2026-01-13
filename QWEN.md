# QWEN.md

This tool is designed to help with Extract, Transform, and Load processes.

## Project Structure

The project follows a modular architecture. Below is an overview of the modules:

| Module Name                               | Description                                                     | Docs                                              |
|-------------------------------------------|-----------------------------------------------------------------|---------------------------------------------------|
| `common-models-module`                    | Shared data models and business logic                           |                                                   |
| [console-app-module/](console-app-module) | Console application entry point                                 |                                                   |
| [excel-module/](excel-module)             | Excel file processing functionality                             |                                                   |
| [logging-module/](logging-module)         | Logging utilities and configurations                            |                                                   |
| [parser-module/](parser-module)           | Provides parsing functionalities.                               | [parser-module.md](docs/modules/parser-module.md) |
| [polynom-bff-module/](polynom-bff-module) | Backend-for-frontend (BFF) module for polynom-related services. |                                                   |

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

## Skills

Before completing the task, be sure to read the skill description file [skills](/docs/SKILLS_INDEX.md) and use them if it
is appropriate for the context of the task. The description of the skills is in the files `SKILLS.md`