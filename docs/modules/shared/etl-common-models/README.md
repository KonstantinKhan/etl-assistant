# etl-common-models

## LLM Usage Contract

This document is the **authoritative source of truth** for the module structure.

Rules for LLM agents:
- Do NOT scan the repository tree unless explicitly instructed.
- Do NOT re-discover folder structure.
- Use this document as a cached mental model.
- Assume the structure described here is complete and up-to-date.
- Ask questions ONLY if a symbol is missing from this document.

## Root package specification
`com.khan366kos.common.models`

## Thematic grouping of domain models

### Business Models
- `Identifier` - Combines ObjectId and TypeId to uniquely identify domain objects
- `ObjectInfo` - Comprehensive information about a domain object including metadata, path, ownership, and properties
- `PathElement` - Represents an element in an object's hierarchical path
- `OwnerGroup` - Represents ownership groups for access control
- `InnerElement` - Represents elements contained within other elements

### Simple Types
- `ObjectId` - Unique identifier for objects (wrapper around Int)
- `TypeId` - Type identifier for objects (wrapper around Int)
- `ElementName` - Name of an element (wrapper around String)
- `IconCode` - Icon representation code
- `IconColor` - Color representation for icons
- `PathId` - Identifier for paths
- `GroupId` - Group identifier
- `Applicability` - Defines where an element can be applied

### Value Models
- `Values` - Container for all property values of an object
- `DoublePropertyValues` - Values for numeric properties with min/max bounds and tolerance
- `StringPropertyValues` - Values for string properties
- `BooleanPropertyValues` - Values for boolean properties
- `ColorPropertyValues` - RGBA color values
- `OpticPropertyValues` - Material properties for visualization
- `DateTimePropertyValues` - Date/time values
- `ImagePropertyValues` - Image references
- `RtfPropertyValues` - Rich text format values
- `EnumPropertyValues` - Enumerated values
- `SetPropertyValues` - Set of string values
- `IntegerPropertyValues` - Integer values
- `BinaryPropertyValues` - Binary data
- `GuidPropertyValues` - GUID values
- `EnumBoolPropertyValues` - Boolean enumerated values
- `EnumDoublePropertyValues` - Double enumerated values
- `EnumIntPropertyValues` - Integer enumerated values
- `EnumStringPropertyValues` - String enumerated values
- `TablePropertyValues` - Tabular data with columns and rows
- `EnumItemValue` - Generic enum item with description and position
- `TableColumnValue` - Column definition in a table
- `TableRowValue` - Row data in a table

### Definition Models
- `Definitions` - Container for all property definitions
- `DoubleProperty` - Definition for double properties
- `StorageDefinition` - Represents a storage definition in the domain layer, containing information about a storage entity including its unique identifier and display name
- `StringProperty` - Definition for string properties
- `BooleanProperty` - Definition for boolean properties
- `ColorProperty` - Definition for color properties
- `OpticProperty` - Definition for optic properties
- `DateTimeProperty` - Definition for datetime properties
- `ImageProperty` - Definition for image properties
- `RtfProperty` - Definition for RTF properties
- `EnumProperty` - Definition for enum properties
- `SetProperty` - Definition for set properties
- `IntegerProperty` - Definition for integer properties
- `BinaryProperty` - Definition for binary properties
- `GuidProperty` - Definition for GUID properties
- `EnumBoolProperty` - Definition for boolean enum properties
- `EnumDoubleProperty` - Definition for double enum properties
- `EnumIntProperty` - Definition for integer enum properties
- `EnumStringProperty` - Definition for string enum properties
- `TableProperty` - Definition for table properties
- `EnumItem` - Definition for enum items
- `SetItem` - Definition for set items
- `TableColumn` - Definition for table columns

### Item Models
- `Item` - A domain item with ID, contracts, and properties
- `ItemProperty` - Property definition for an item
- `ItemsList` - Collection of items

### Measure Models
- `MeasureEntity` - Represents a measurable entity with unit information
- `MeasureEntities` - Collection of measure entities

### Classifier Models
- `ClassifierGroup` - Groups objects by classification criteria
- `RawClassifierGroup` - Raw classifier data

### Excel Models
- `EtlWorkbook` - Represents an Excel workbook
- `EtlSheet` - Represents a worksheet with headers and data
- `EtlSheetTitle` - Title of a sheet
- `EtlTableHeader` - Header information for tables

### Request/Response Models
- `Request Models` - Data transfer objects for API requests
- `Response Models` - Data transfer objects for API responses

## LLM Loading Strategy section

When working with the etl-common-models module:
- Understand that these are shared domain models used across the entire ETL application
- Recognize that all models are immutable data classes with serialization support
- Note that value classes are used for type safety (ObjectId, TypeId, etc.)
- Remember that these models represent the core business domain of the ETL system
- Be aware of the relationships between models (hierarchical structure, property system, etc.)