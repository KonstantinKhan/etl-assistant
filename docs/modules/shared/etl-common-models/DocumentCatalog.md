# DocumentCatalog

## Responsibility
- Represents a document catalog with metadata and classification
- Contains reference to parent object and hierarchical information
- Provides entry status and count information

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
- `reference`: Identifier - Reference to parent object
- `isEntry`: Boolean - Indicates if this is an entry point

## Used by
- Document catalog browsing
- Hierarchical navigation
- Document management systems