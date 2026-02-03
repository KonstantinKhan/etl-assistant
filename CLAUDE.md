# CLAUDE.md

This tool is designed to help with Extract, Transform, and Load processes.

## Rules and conventions

- !!!NECESSARILY!!! First of all, always read the documentation, if there is one.
- Before completing a task, first of all, be sure to read the documentation for the module you plan to work with.
- First of all, check the modules and files according to the documentation, only if you do not find any data in it, you can scan the project.

## Project Structure

The project follows a modular architecture. Below is an overview of the modules:

| Module Name           | Description                                    | Docs                                                                     |
|-----------------------|------------------------------------------------|--------------------------------------------------------------------------|
| `etl-common-models`   | Shared data models                             | [etl-common-models.md](/docs/modules/shared/etl-common-models/README.md) |
| `etl-mapper`          | Mapping between domain and transport models    | [etl-mapper.md](/docs/modules/shared/etl-mapper/README.md)               |
| `etl-transport-kmp`   | Transport layer models                         | [etl-transport-kmp.md](/docs/modules/shared/etl-transport-kmp/README.md) |
| `etl-excel-service`   | Excel file handler                             | [etl-excel-service.md](/docs/modules/backend/etl-excel-service.md)       |
| `etl-ktor-server-app` | Ktor server application                        | [etl-ktor-server-app.md](/docs/modules/backend/etl-ktor-server-app.md)   |
| `etl-polynom-bff`     | Backend for Frontend layer for polynom service | [etl-polynom-bff.md](/docs/modules/backend/etl-polynom-bff.md)           |
| `kotlin-react-app`    | Frontend React application                     | [kotlin-react-app.md](/docs/modules/frontend/kotlin-react-app.md)        |

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