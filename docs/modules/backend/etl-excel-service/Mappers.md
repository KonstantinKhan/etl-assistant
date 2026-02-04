# Mappers.kt

## Responsibility

The Mappers.kt file implements functions for converting between Excel data structures and the transport models used by the ETL Assistant system. It handles the transformation of data from Apache POI objects to application-specific data structures and vice versa.

## Intended usage

The mapping functions should be used when converting Excel data to transport models for use by other system components, or when converting transport models back to Excel format. This enables integration between the Excel processing functionality and the rest of the ETL system.

## Non-goals and boundaries

- The mappers do not perform business logic or data validation beyond the conversion process
- They do not handle file I/O operations
- They do not manage the state of workbooks or worksheets

## Key invariants

- The mapping functions preserve data integrity during transformation
- They follow consistent patterns for converting between types
- They handle edge cases such as empty cells, different data types, and formatting
- They map to the transport models defined in the shared transport models module