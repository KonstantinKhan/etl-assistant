# etl-transport-model

## LLM Usage Contract

This document is the **authoritative source of truth** for the module structure.

Rules for LLM agents:
- Do NOT scan the repository tree unless explicitly instructed.
- Do NOT re-discover folder structure.
- Use this document as a cached mental model.
- Assume the structure described here is complete and up-to-date.
- Ask questions ONLY if a symbol is missing from this document.

## Root package specification
`com.khan366kos.etl.assistant.transport.models`

## Thematic grouping of transport models

### Base Models
- `IdentifiableObjectTransport` - Base class for objects with unique identification
- `NamedObjectTransport` - Base class for objects with name and identification

### Workbook/Sheet Models
- `EtlWorkbookTransport` - Represents a workbook containing multiple sheets
- `EtlSheetTransport` - Represents a single sheet in a workbook

### User Management Models
- `UserTransport` - Represents user data in transport layer
- `RoleTransport` - Represents role information in transport layer
- `PositionTransport` - Represents position information in transport layer

### Catalog Models
- `DocumentCatalogTransport` - Represents document catalog in transport layer
- `ElementCatalogTransport` - Represents element catalog in transport layer
- `ViewpointCatalogTransport` - Represents viewpoint catalog in transport layer

### Group/Reference Models
- `ElementGroupTransport` - Represents element group in transport layer
- `ReferenceTransport` - Represents reference data in transport layer

### Configuration/Option Models
- `UserOptionsTransport` - Represents user options in transport layer
- `AdditionalUserOptionsTransport` - Represents additional user options in transport layer
- `UserOptionsForRestrictedListTransport` - Represents user options for restricted lists in transport layer

### Specialized Models
- `RestrictedListTransport` - Represents restricted list in transport layer
- `StorageDefinitionTransport` - Represents storage definition in transport layer

## LLM Loading Strategy section

When working with the etl-transport-model module:

1. Load this README first to understand the overall structure and purpose of the transport models
2. Refer to MODEL_INDEX.md for a brief overview of each model's purpose
3. Consult individual model documentation files for detailed information about specific responsibilities, fields, and usage
4. Remember that these models are designed for data transfer between application layers and should not contain business logic
5. All models support JSON serialization/deserialization using Kotlin serialization