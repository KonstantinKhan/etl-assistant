# BinaryProperty

## Responsibility
- Definition for binary properties
- Specifies characteristics for binary property values
- Enables validation and processing of binary properties

## Fields
- `name`: String? - Name of the property
- `description`: String? - Description of the property
- `defaultValue`: ByteArray? - Default binary value
- `maxSize`: Long? - Maximum allowed size in bytes
- `allowedTypes`: List<String>? - List of allowed MIME types

## Used by
- Binary property definition systems
- File attachment systems
- Media management systems