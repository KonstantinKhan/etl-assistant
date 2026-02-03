# Values

## Responsibility
- Container for all property values of an object
- Aggregates different types of property values in a single structure
- Enables unified access to all property values of an object

## Fields
- `doubleValues`: Map<String, DoublePropertyValues>? - Numeric property values
- `stringValues`: Map<String, StringPropertyValues>? - String property values
- `booleanValues`: Map<String, BooleanPropertyValues>? - Boolean property values
- `colorValues`: Map<String, ColorPropertyValues>? - Color property values
- `opticValues`: Map<String, OpticPropertyValues>? - Optic property values
- `dateTimeValues`: Map<String, DateTimePropertyValues>? - Date/time property values
- `imageValues`: Map<String, ImagePropertyValues>? - Image property values
- `rtfValues`: Map<String, RtfPropertyValues>? - Rich text format property values
- `enumValues`: Map<String, EnumPropertyValues>? - Enumerated property values
- `setValues`: Map<String, SetPropertyValues>? - Set property values
- `integerValues`: Map<String, IntegerPropertyValues>? - Integer property values
- `binaryValues`: Map<String, BinaryPropertyValues>? - Binary property values
- `guidValues`: Map<String, GuidPropertyValues>? - GUID property values
- `tableValues`: Map<String, TablePropertyValues>? - Table property values

## Used by
- Property value aggregation systems
- Object state management
- Data serialization mechanisms