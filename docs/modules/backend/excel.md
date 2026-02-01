# Excel module

## Location

### Module

[excel](../../../backend/excel)

### Source code

[src](../../../backend/excel/src/main/kotlin/com/khan366kos/excel)

## Module Structure

### Folder Structure with Files

```
backend/excel/
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