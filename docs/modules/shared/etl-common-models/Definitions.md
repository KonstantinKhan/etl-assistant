# Definitions

## Responsibility
- Container for all property definitions
- Aggregates different types of property definitions in a single structure
- Enables unified access to all property definitions of an object

## Fields
- `doubleDefinitions`: Map<String, DoubleProperty>? - Numeric property definitions
- `stringDefinitions`: Map<String, StringProperty>? - String property definitions
- `booleanDefinitions`: Map<String, BooleanProperty>? - Boolean property definitions
- `colorDefinitions`: Map<String, ColorProperty>? - Color property definitions
- `opticDefinitions`: Map<String, OpticProperty>? - Optic property definitions
- `dateTimeDefinitions`: Map<String, DateTimeProperty>? - Date/time property definitions
- `imageDefinitions`: Map<String, ImageProperty>? - Image property definitions
- `rtfDefinitions`: Map<String, RtfProperty>? - Rich text format property definitions
- `enumDefinitions`: Map<String, EnumProperty>? - Enumerated property definitions
- `setDefinitions`: Map<String, SetProperty>? - Set property definitions
- `integerDefinitions`: Map<String, IntegerProperty>? - Integer property definitions
- `binaryDefinitions`: Map<String, BinaryProperty>? - Binary property definitions
- `guidDefinitions`: Map<String, GuidProperty>? - GUID property definitions
- `tableDefinitions`: Map<String, TableProperty>? - Table property definitions

## Used by
- Property definition aggregation systems
- Schema management systems
- Object structure definition