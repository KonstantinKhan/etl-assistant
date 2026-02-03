# EtlSheetTransport

## Responsibility
- Represents a single sheet in a workbook
- Contains sheet metadata like title, headers, and entry count
- Used for data transfer between application layers
- Enables workbook serialization

## Fields
- `title`: String? - Title of the sheet
- `headers`: List<String>? - List of header values for the sheet
- `entryCount`: Int - Number of entries in the sheet

## Used by
- Data transfer between application layers
- Workbook serialization