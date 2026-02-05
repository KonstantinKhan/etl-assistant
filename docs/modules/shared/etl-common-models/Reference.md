# Reference

## Responsibility
- Represents a reference object with metadata and hierarchical information
- Contains catalog information (document and viewpoint catalogs)
- Provides comprehensive view of a reference's properties and relationships

## Fields
- `id`: ReferenceId - Unique identifier for the reference
- `name`: ElementName - Name of the reference
- `description`: Description - Description of the reference
- `objectId`: ObjectId - Object identifier
- `typeId`: TypeId - Type identifier
- `iconCode`: IconCode - Icon code for visual representation
- `iconColor`: IconColor - Icon color for visual representation
- `writeAccess`: WriteAccess - Write permission flag
- `path`: List<PathElement> - Hierarchical path to the reference
- `documentCatalog`: DocumentCatalog? - Associated document catalog
- `viewpointCatalog`: ViewpointCatalog? - Associated viewpoint catalog

## Used by
- Reference browsing interfaces
- Catalog navigation systems
- Reference management features