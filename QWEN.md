# QWEN.md

## Dependencies

- Use dependencies from [libs.versions.toml](gradle/libs.versions.toml)
- Use projects.{module_name} for include dependencies

## Testing

- Use Kotest, Should Spec test style

## Building & Running

To build or run the project, use one of the following tasks:
| Task | Description |
|-------------------|------------------|
| `./gradlew.bat test`  | Run the tests |
| `./gradlew.bat build` | Build everything |
| `./gradlew.bat run`   | Run the server |

## Developmnet conventions
- All simple fields should be value classes.
- All business models should be data classes.
- Each business object, request or response should be in a separate data class.