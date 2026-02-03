# EtlWorkbookTransport

## Responsibility
- Represents a workbook containing multiple sheets
- Contains a list of EtlSheetTransport objects
- Used for data transfer between application layers
- Enables serialization/deserialization of workbook data

## Fields
- `sheets`: List<EtlSheetTransport> - List of sheets contained in the workbook (default: emptyList())

## Used by
- Data transfer between application layers
- Serialization/deserialization of workbook data