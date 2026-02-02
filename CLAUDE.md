# CLAUDE.md

This tool is designed to help with Extract, Transform, and Load processes.

## Rules and conventions

- !!!NECESSARILY!!! First of all, always read the documentation, if there is one.
- Before completing a task, first of all, be sure to read the documentation for the module you plan to work with.
- First of all, check the modules and files according to the documentation, only if you do not find any data in it, you can scan the project.

## Project Structure

The project follows a modular architecture. Below is an overview of the modules:

| Module Name           | Description                                 | Docs                                                            |
|-----------------------|---------------------------------------------|-----------------------------------------------------------------|
| `common-models`       | Shared data models                          | [common-models.md](/docs/modules/shared/common-models.md)       |
| `etl-mapper`          | Mapping between domain and transport models | [mapper](/docs/modules/shared/etl-mapper.md)                    |
| `transport-kmp`       | Transport layer models                      | [transport](/docs/modules/shared/transport-kmp.md)              |
| `etl-excel-service`   | Excel file handler                          | [etl-excel-service](/docs/modules/backend/etl-excel-service.md) |
| `etl-ktor-server-app` | Ktor server application                     | [server-app](/docs/modules/backend/etl-ktor-server-app.md)      |
| `kotlin-react-app`    | Frontend React application                  | [react-app](/docs/modules/frontend/kotlin-react-app.md)         |

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