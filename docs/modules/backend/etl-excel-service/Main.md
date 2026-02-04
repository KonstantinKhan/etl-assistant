# Main.kt

## Responsibility

The Main.kt file implements the entry point for the etl-excel-service when run as a standalone application. It provides the main function that initializes and executes the Excel processing functionality.

## Intended usage

The main function should be used when running the Excel service as a standalone application, typically for testing, debugging, or batch processing scenarios.

## Non-goals and boundaries

- The main function does not implement the core Excel processing logic - that is handled by other components
- It does not handle API requests or web-based interactions
- It does not manage the service lifecycle in a production server environment

## Key invariants

- The main function serves as the entry point for standalone execution
- It initializes the necessary components for Excel processing
- It follows Kotlin/JVM application conventions for the main entry point
- It may be used for demonstration or testing purposes