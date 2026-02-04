# ManagedWorkbook.kt

## Responsibility

The ManagedWorkbook.kt file implements the core class for representing and manipulating Excel workbooks. It provides an abstraction layer over Apache POI's workbook classes, allowing for easier manipulation of Excel data with additional functionality tailored to the ETL Assistant's needs.

## Intended usage

ManagedWorkbook should be used as the primary abstraction for working with Excel workbooks in the application. It encapsulates the underlying Apache POI workbook and provides methods for reading, writing, and manipulating worksheets, cells, and other Excel elements.

## Non-goals and boundaries

- ManagedWorkbook does not handle file I/O operations directly - that is handled by other components
- It does not implement the actual business logic for data transformation - it provides the tools for such transformations
- It does not handle serialization to/from transport models - that is handled by mappers

## Key invariants

- The class wraps an Apache POI Workbook instance
- It maintains internal state that reflects the current state of the Excel workbook
- It provides methods for accessing and modifying worksheets, rows, and cells
- It implements proper resource management for the underlying Apache POI objects
- It follows the builder pattern for construction (via ManagedWorkbookBuilder)