# etl-mapper

## LLM Usage Contract

This document is the **authoritative source of truth** for the module structure.

Rules for LLM agents:
- Do NOT scan the repository tree unless explicitly instructed.
- Do NOT re-discover folder structure.
- Use this document as a cached mental model.
- Assume the structure described here is complete and up-to-date.
- Ask questions ONLY if a symbol is missing from this document.

## Overview

The ETL Mapper module provides bidirectional mapping functionality between domain models and transport models in the ETL Assistant application. This module serves as a bridge between the internal domain representation and the external transport layer, enabling seamless conversion of data structures for API communication while maintaining clean separation of concerns.

## Purpose

The primary purpose of the ETL Mapper module is to:

- Convert domain models to transport models for API serialization
- Convert transport models back to domain models for internal processing
- Maintain type safety during conversions
- Encapsulate mapping logic in a centralized location
- Support serialization/deserialization of complex data structures

## Components

The ETL Mapper module consists of the following components:

- [Domain2Transport](Domain2Transport.md) - Functions for converting domain models to transport models
- [Transport2Domain](Transport2Domain.md) - Functions for converting transport models to domain models

## Usage

The mapper functions are implemented as extension functions that allow easy conversion between domain and transport models:

```kotlin
// Converting from domain to transport
val transportWorkbook = domainWorkbook.toEtlWorkbookTransport()

// Converting from transport to domain
val domainWorkbook = transportWorkbook.toEtlWorkbook()
```

## Architecture

The ETL Mapper module follows a bidirectional mapping pattern, with separate files for each direction of conversion:
- `Domain2Transport.kt` handles domain → transport conversions
- `Transport2Domain.kt` handles transport → domain conversions

This separation ensures clear responsibilities and maintainable mapping logic.

## Root Package

com.khan366kos.etl.mapper