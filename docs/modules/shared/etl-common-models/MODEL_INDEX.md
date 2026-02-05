# Model Index

This document provides brief descriptions of each domain model in the etl-common-models module.

## Business Models

### [Reference](Reference.md)
Represents a reference object with metadata, hierarchical information, and catalog associations.

### [DocumentCatalog](DocumentCatalog.md)
Represents a document catalog with metadata and hierarchical path information.

### [ViewpointCatalog](ViewpointCatalog.md)
Represents a viewpoint catalog with metadata and optional reference association.

### [Identifier](Identifier.md)
Combines ObjectId and TypeId to uniquely identify domain objects in the system.

### [ObjectInfo](ObjectInfo.md)
Comprehensive information about a domain object including metadata, path, ownership, and properties.

### [PathElement](PathElement.md)
Represents an element in an object's hierarchical path.

### [OwnerGroup](OwnerGroup.md)
Represents ownership groups for access control.

### [InnerElement](InnerElement.md)
Represents elements contained within other elements.

## Simple Types

### [ReferenceId](ReferenceId.md)
Unique identifier for references (wrapper around String), providing type safety.

### [Description](Description.md)
Textual description for domain objects (wrapper around String), providing type safety and semantic meaning.

### [WriteAccess](WriteAccess.md)
Write permission indicator (wrapper around Boolean), providing type safety for access control flags.

### [ObjectId](ObjectId.md)
Unique identifier for objects (wrapper around Int), providing type safety.

### [TypeId](TypeId.md)
Type identifier for objects (wrapper around Int), providing type safety.

### [ElementName](ElementName.md)
Name of an element (wrapper around String), providing type safety.

### [IconCode](IconCode.md)
Icon representation code, providing type safety.

### [IconColor](IconColor.md)
Color representation for icons, providing type safety.

### [PathId](PathId.md)
Identifier for paths, providing type safety.

### [GroupId](GroupId.md)
Group identifier, providing type safety.

### [Applicability](Applicability.md)
Defines where an element can be applied, providing type safety.

## Value Models

### [Values](Values.md)
Container for all property values of an object.

### [DoublePropertyValues](DoublePropertyValues.md)
Values for numeric properties with min/max bounds and tolerance.

### [StringPropertyValues](StringPropertyValues.md)
Values for string properties.

### [BooleanPropertyValues](BooleanPropertyValues.md)
Values for boolean properties.

### [ColorPropertyValues](ColorPropertyValues.md)
RGBA color values.

### [OpticPropertyValues](OpticPropertyValues.md)
Material properties for visualization.

### [DateTimePropertyValues](DateTimePropertyValues.md)
Date/time values.

### [ImagePropertyValues](ImagePropertyValues.md)
Image references.

### [RtfPropertyValues](RtfPropertyValues.md)
Rich text format values.

### [EnumPropertyValues](EnumPropertyValues.md)
Enumerated values.

### [SetPropertyValues](SetPropertyValues.md)
Set of string values.

### [IntegerPropertyValues](IntegerPropertyValues.md)
Integer values.

### [BinaryPropertyValues](BinaryPropertyValues.md)
Binary data.

### [GuidPropertyValues](GuidPropertyValues.md)
GUID values.

### [EnumBoolPropertyValues](EnumBoolPropertyValues.md)
Boolean enumerated values.

### [EnumDoublePropertyValues](EnumDoublePropertyValues.md)
Double enumerated values.

### [EnumIntPropertyValues](EnumIntPropertyValues.md)
Integer enumerated values.

### [EnumStringPropertyValues](EnumStringPropertyValues.md)
String enumerated values.

### [TablePropertyValues](TablePropertyValues.md)
Tabular data with columns and rows.

### [EnumItemValue](EnumItemValue.md)
Generic enum item with description and position.

### [TableColumnValue](TableColumnValue.md)
Column definition in a table.

### [TableRowValue](TableRowValue.md)
Row data in a table.

## Definition Models

### [Definitions](Definitions.md)
Container for all property definitions.

### [DoubleProperty](DoubleProperty.md)
Definition for double properties.

### [StorageDefinition](StorageDefinition.md)
Represents a storage definition in the domain layer, containing information about a storage entity including its unique identifier and display name.

### [StringProperty](StringProperty.md)
Definition for string properties.

### [BooleanProperty](BooleanProperty.md)
Definition for boolean properties.

### [ColorProperty](ColorProperty.md)
Definition for color properties.

### [OpticProperty](OpticProperty.md)
Definition for optic properties.

### [DateTimeProperty](DateTimeProperty.md)
Definition for datetime properties.

### [ImageProperty](ImageProperty.md)
Definition for image properties.

### [RtfProperty](RtfProperty.md)
Definition for RTF properties.

### [EnumProperty](EnumProperty.md)
Definition for enum properties.

### [SetProperty](SetProperty.md)
Definition for set properties.

### [IntegerProperty](IntegerProperty.md)
Definition for integer properties.

### [BinaryProperty](BinaryProperty.md)
Definition for binary properties.

### [GuidProperty](GuidProperty.md)
Definition for GUID properties.

### [EnumBoolProperty](EnumBoolProperty.md)
Definition for boolean enum properties.

### [EnumDoubleProperty](EnumDoubleProperty.md)
Definition for double enum properties.

### [EnumIntProperty](EnumIntProperty.md)
Definition for integer enum properties.

### [EnumStringProperty](EnumStringProperty.md)
Definition for string enum properties.

### [TableProperty](TableProperty.md)
Definition for table properties.

### [EnumItem](EnumItem.md)
Definition for enum items.

### [SetItem](SetItem.md)
Definition for set items.

### [TableColumn](TableColumn.md)
Definition for table columns.

## Item Models

### [Item](Item.md)
A domain item with ID, contracts, and properties.

### [ItemProperty](ItemProperty.md)
Property definition for an item.

### [ItemsList](ItemsList.md)
Collection of items.

## Measure Models

### [MeasureEntity](MeasureEntity.md)
Represents a measurable entity with unit information.

### [MeasureEntities](MeasureEntities.md)
Collection of measure entities.

## Classifier Models

### [ClassifierGroup](ClassifierGroup.md)
Groups objects by classification criteria.

### [RawClassifierGroup](RawClassifierGroup.md)
Raw classifier data.

## Excel Models

### [EtlWorkbook](EtlWorkbook.md)
Represents an Excel workbook.

### [EtlSheet](EtlSheet.md)
Represents a worksheet with headers and data.

### [EtlSheetTitle](EtlSheetTitle.md)
Title of a sheet.

### [EtlTableHeader](EtlTableHeader.md)
Header information for tables.

## Request/Response Models

### [Request Models](RequestModels.md)
Data transfer objects for API requests.

### [Response Models](ResponseModels.md)
Data transfer objects for API responses.