# Transport2Domain

## Responsibility
- Provides extension functions to convert transport models back to domain models
- Enables deserialization of external data into internal domain objects
- Maintains type safety during the conversion process
- Implements the reverse mapping logic from transport to domain layers

## Functions
- `toEtlWorkbook()`: Converts an EtlWorkbookTransport transport model to an EtlWorkbook domain model
- `toEtlSheet()`: Converts an EtlSheetTransport transport model to an EtlSheet domain model
- `toStorageDefinition()`: Converts a StorageDefinitionTransport to a StorageDefinition domain model
- `toAuthorizationCredentials()`: Converts an AuthorizationRequestTransport to an AuthorizationCredentials domain model
- `toPathElement()`: Converts a NamedObjectTransport to a PathElement domain model
- `toDocumentCatalog()`: Converts a DocumentCatalogTransport to a DocumentCatalog domain model
- `toViewpointCatalog()`: Converts a ViewpointCatalogTransport to a ViewpointCatalog domain model
- `toReference()`: Converts a ReferenceTransport to a Reference domain model

## Mapping Details
- `EtlWorkbookTransport` → `EtlWorkbook`: Maps transport workbook containing sheets back to domain format
- `EtlSheetTransport` → `EtlSheet`: Maps sheet title, headers, and entries size from transport format back to domain format
- `StorageDefinitionTransport` → `StorageDefinition`: Maps storage ID and display name to domain format
- `AuthorizationRequestTransport` → `AuthorizationCredentials`: Maps username, password, and storage ID to domain credentials
- `NamedObjectTransport` → `PathElement`: Maps named object with objectId, typeId, and name to path element
- `DocumentCatalogTransport` → `DocumentCatalog`: Maps document catalog with all metadata and hierarchical information to domain format
- `ViewpointCatalogTransport` → `ViewpointCatalog`: Maps viewpoint catalog with metadata and optional reference to domain format
- `ReferenceTransport` → `Reference`: Maps reference with catalogs, path, and metadata to domain format

## Used by
- API deserialization systems
- Data import functionality
- Internal processing systems
- Domain layer components