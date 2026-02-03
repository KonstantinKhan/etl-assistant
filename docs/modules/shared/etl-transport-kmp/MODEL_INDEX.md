# Model Index

This document provides brief descriptions of each transport model in the etl-transport-model module.

## Base Models

### [IdentifiableObjectTransport](IdentifiableObjectTransport.md)
Base class for objects with unique identification, containing objectId and typeId properties.

### [NamedObjectTransport](NamedObjectTransport.md)
Extends IdentifiableObjectTransport with a name property, serving as a base for named objects.

## Workbook/Sheet Models

### [EtlWorkbookTransport](EtlWorkbookTransport.md)
Represents a workbook containing multiple sheets, used for data transfer between application layers.

### [EtlSheetTransport](EtlSheetTransport.md)
Represents a single sheet in a workbook, containing sheet metadata like title, headers, and entry count.

## User Management Models

### [UserTransport](UserTransport.md)
Represents user data in the transport layer, containing user profile information, roles, and permissions.

### [RoleTransport](RoleTransport.md)
Represents role information in the transport layer, containing role metadata.

### [PositionTransport](PositionTransport.md)
Represents position information in the transport layer, containing position metadata.

## Catalog Models

### [DocumentCatalogTransport](DocumentCatalogTransport.md)
Represents document catalog in the transport layer, containing document catalog metadata.

### [ElementCatalogTransport](ElementCatalogTransport.md)
Represents element catalog in the transport layer, containing element catalog metadata.

### [ViewpointCatalogTransport](ViewpointCatalogTransport.md)
Represents viewpoint catalog in the transport layer, containing viewpoint catalog metadata.

## Group/Reference Models

### [ElementGroupTransport](ElementGroupTransport.md)
Represents element group in the transport layer, containing element group metadata.

### [ReferenceTransport](ReferenceTransport.md)
Represents reference data in the transport layer, containing reference metadata.

## Configuration/Option Models

### [UserOptionsTransport](UserOptionsTransport.md)
Represents user options in the transport layer, containing user preference settings.

### [AdditionalUserOptionsTransport](AdditionalUserOptionsTransport.md)
Represents additional user options in the transport layer, containing extended user preference settings.

### [UserOptionsForRestrictedListTransport](UserOptionsForRestrictedListTransport.md)
Represents user options for restricted lists in the transport layer, containing user preferences for restricted list handling.

## Specialized Models

### [RestrictedListTransport](RestrictedListTransport.md)
Represents restricted list in the transport layer, containing restricted list metadata.

### [StorageDefinitionTransport](StorageDefinitionTransport.md)
Represents storage definition in the transport layer, containing storage definition metadata.