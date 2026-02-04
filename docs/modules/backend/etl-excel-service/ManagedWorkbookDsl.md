# ManagedWorkbookDsl.kt

## Responsibility

The ManagedWorkbookDsl.kt file implements DSL (Domain Specific Language) functions and extensions that enable declarative manipulation of ManagedWorkbook objects. It provides a more intuitive, readable syntax for defining workbook structure and content.

## Intended usage

The DSL functions should be used when defining workbook structure and content in a more declarative, readable way. It allows developers to express workbook manipulations using a syntax that closely resembles the structure of the resulting Excel file.

## Non-goals and boundaries

- The DSL does not handle file I/O operations
- It does not implement the underlying Apache POI operations directly (delegates to ManagedWorkbook)
- It does not handle serialization to/from transport models

## Key invariants

- The DSL functions use Kotlin's extension function and lambda features to create a fluent API
- It operates on ManagedWorkbook instances to modify their state
- It provides a more readable alternative to direct API calls on ManagedWorkbook
- It maintains the same functionality as the underlying API while improving readability