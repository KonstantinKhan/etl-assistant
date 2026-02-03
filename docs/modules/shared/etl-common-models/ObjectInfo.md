# ObjectInfo

## Responsibility
- Contains comprehensive information about a domain object
- Includes metadata, hierarchical path, ownership, and properties
- Provides a complete view of an object's context within the system

## Fields
- `identifier`: Identifier - Unique identifier for the object
- `name`: String? - Name of the object
- `description`: String? - Description of the object
- `path`: List<PathElement> - Hierarchical path to the object
- `ownerGroup`: OwnerGroup? - Ownership information for the object
- `properties`: Map<String, *> - Properties associated with the object

## Used by
- Object detail views
- Hierarchical navigation systems
- Metadata management systems
- Property browsing interfaces