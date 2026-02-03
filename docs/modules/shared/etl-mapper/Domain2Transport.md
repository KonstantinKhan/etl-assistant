# Domain2Transport

## Responsibility
- Provides extension functions to convert domain models to transport models
- Enables serialization of internal domain objects for API communication
- Maintains type safety during the conversion process
- Implements the mapping logic between domain and transport layers

## Functions
- `toEtlWorkbookTransport()`: Converts an EtlWorkbook domain model to an EtlWorkbookTransport transport model
- `toEtlSheetTransport()`: Converts an EtlSheet domain model to an EtlSheetTransport transport model

## Mapping Details
- `EtlWorkbook` → `EtlWorkbookTransport`: Maps workbook containing sheets to transport format
- `EtlSheet` → `EtlSheetTransport`: Maps sheet title, headers, and entries size to transport format

## Used by
- API serialization systems
- External service communication
- Data export functionality
- Transport layer components