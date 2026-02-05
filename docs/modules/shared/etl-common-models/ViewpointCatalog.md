# ViewpointCatalog

## Responsibility
- Represents a viewpoint catalog with metadata and classification
- Contains optional reference to parent object
- Provides count information for catalog items

## Fields
- `id`: ReferenceId - Unique identifier for the catalog
- `classId`: ReferenceId - Classification identifier
- `name`: ElementName - Name of the catalog
- `objectId`: ObjectId - Object identifier
- `typeId`: TypeId - Type identifier
- `iconCode`: IconCode - Icon code for visual representation
- `iconColor`: IconColor - Icon color for visual representation
- `writeAccess`: WriteAccess - Write permission flag
- `path`: List<PathElement> - Hierarchical path to the catalog
- `count`: Int - Number of items in the catalog
- `reference`: Identifier? - Optional reference to parent object

## Used by
- Viewpoint catalog browsing
- Hierarchical navigation
- Catalog management systems