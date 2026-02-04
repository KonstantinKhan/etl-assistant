# Component Index for etl-excel-service

## Core Workbook Management
- `ManagedWorkbook` - Central class for representing and manipulating Excel workbooks
- `ManagedWorkbookBuilder` - Builder pattern implementation for constructing ManagedWorkbook instances
- `ManagedWorkbookDsl` - DSL functions and extensions for declarative workbook manipulation

## Data Mapping and Transformation
- `Mappers` - Functions for converting between Excel data structures and transport models
- `ManagedWorkbookAction` - Enum defining possible actions that can be performed on workbooks

## Utility Components
- `ExcelExtentions` - Extension functions for common Excel operations
- `Main` - Entry point for standalone execution of the service