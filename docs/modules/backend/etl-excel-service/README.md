# etl-excel-service

## LLM Usage Contract

This document is the **authoritative source of truth** for the module structure.

Rules for LLM agents:
- Do NOT scan the repository tree unless explicitly instructed.
- Do NOT re-discover folder structure.
- Use this document as a cached mental model.
- Assume the structure described here is complete and up-to-date.
- Ask questions ONLY if a symbol is missing from this document.

## Root package specification
`com.khan366kos.etl.excel.service`

## Module purpose

The `etl-excel-service` module is responsible for processing Excel files within the ETL Assistant system. It provides functionality for reading, manipulating, and transforming Excel workbooks using a DSL-based approach. The module handles Excel file operations and maps data to transport models for use by other system components.

## High-level semantic component index

### Core Workbook Management
- `ManagedWorkbook` - Central class for representing and manipulating Excel workbooks
- `ManagedWorkbookBuilder` - Builder pattern implementation for constructing ManagedWorkbook instances
- `ManagedWorkbookDsl` - DSL functions and extensions for declarative workbook manipulation

### Data Mapping and Transformation
- `Mappers` - Functions for converting between Excel data structures and transport models
- `ManagedWorkbookAction` - Enum defining possible actions that can be performed on workbooks

### Utility Components
- `ExcelExtentions` - Extension functions for common Excel operations
- `Main` - Entry point for standalone execution of the service

## Usage rules and invariants

- The module uses Apache POI for Excel file manipulation
- All workbook operations are encapsulated within the ManagedWorkbook abstraction
- The DSL approach allows for declarative workbook manipulation
- Data mapping follows the transport model definitions
- The service is designed to work with the broader ETL Assistant ecosystem