# Transport2Domain

## Responsibility
- Provides extension functions to convert transport models back to domain models
- Enables deserialization of external data into internal domain objects
- Maintains type safety during the conversion process
- Implements the reverse mapping logic from transport to domain layers

## Functions
- `toEtlWorkbook()`: Converts an EtlWorkbookTransport transport model to an EtlWorkbook domain model
- `toEtlSheet()`: Converts an EtlSheetTransport transport model to an EtlSheet domain model

## Mapping Details
- `EtlWorkbookTransport` → `EtlWorkbook`: Maps transport workbook containing sheets back to domain format
- `EtlSheetTransport` → `EtlSheet`: Maps sheet title, headers, and entries size from transport format back to domain format

## Used by
- API deserialization systems
- Data import functionality
- Internal processing systems
- Domain layer components