# ETL Excel Service module

## Location

### Module

[etl-excel-service](../../../backend/etl-excel-service)

### Source code

[src](../../../backend/etl-excel-service/src/main/kotlin/com/khan366kos/etl/excel/service)

## Module Structure

### Folder Structure with Files

```
backend/etl-excel-service/
├── build.gradle.kts
├── src/
│   ├── main/
│   │   ├── kotlin/
│   │   │   └── khan366kos/
│   │   │       └── excel/
│   │   │           ├── dsl/
│   │   │           │   ├── builders/
│   │   │           │   │   └── ManagedWorkbookBuilder.kt
│   │   │           │   └── function/
│   │   │           │       └── ManagedWorkbookDsl.kt
│   │   │           ├── mapper/
│   │   │           │   └── Mappers.kt
│   │   │           ├── types/
│   │   │           │   └── ManagedWorkbookAction.kt
│   │   │           ├── ExcelExtentions.kt
│   │   │           ├── Main.kt
│   │   │           └── ManagedWorkbook.kt
│   │   └── resources/
│   │       ├── Book.xlsx
│   │       └── logback.xml
│   └── test/
│       ├── kotlin/
│       │   └── khan366kos/
│       │       └── excel/
│       │           └── ExcelReadTest.kt
│       └── resources/
│           ├── Book.xlsx
│           └── logback-test.xml
└── build/
```