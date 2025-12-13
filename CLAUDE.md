# ETL Assistant Project Documentation

Welcome to the ETL Assistant project. This tool is designed to help with Extract, Transform, and Load processes.

## Project Structure

The project follows a modular architecture. Below is an overview of the modules:

| Module Name                                   | Description                                                     |
|-----------------------------------------------|-----------------------------------------------------------------|
| [common-models-module/](common-models-module) | Shared data models and business logic                           |
| [console-app-module/](console-app-module)     | Console application entry point                                 |
| [excel-module/](excel-module)                 | Excel file processing functionality                             |
| [logging-module/](logging-module)             | Logging utilities and configurations                            |
| [parser-module/](parser-module)               | Provides parsing functionalities.                               |
| [polynom-bff-module/](polynom-bff-module)     | Backend-for-frontend (BFF) module for polynom-related services. |

## Development

### Prerequisites

- Kotlin (latest stable version)
- Gradle (wrapper provided)

### Building the Project

```bash
./gradlew build
```

### Running Tests

```bash
./gradlew test
```

### Running the Application

```bash
./gradlew run
```

## Modules

### Common Models Module

Contains shared value classes, data classes, and business models used across the application.

### Console App Module

Main entry point for the console application with command-line interface.

### Excel Module

Handles Excel file parsing, manipulation, and ETL operations.

### Logging Module

Provides structured logging capabilities.

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

- 