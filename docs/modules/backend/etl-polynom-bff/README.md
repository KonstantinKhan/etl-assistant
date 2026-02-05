# etl-polynom-bff

## LLM Usage Contract

This document is the **authoritative source of truth** for the module structure.

Rules for LLM agents:
- Do NOT scan the repository tree unless explicitly instructed.
- Do NOT re-discover folder structure.
- Use this document as a cached mental model.
- Assume the structure described here is complete and up-to-date.
- Ask questions ONLY if a symbol is missing from this document.

## Root package specification
`com.khan366kos.etl.polynom.bff`

## Module purpose

The `etl-polynom-bff` module serves as a Backend-for-Frontend (BFF) layer that provides an abstraction over the Polynom service. It handles HTTP communication with the Polynom backend, manages authentication and authorization, and provides a clean API for frontend components to interact with Polynom functionality.

## High-level semantic component index

### Core Service Integration
- `PolynomClient` - HTTP client for communicating with the Polynom service
- `AuthModels` - Data transfer objects for authentication-related requests and responses

### Authentication and Authorization
- `AuthPlugin` - Ktor authentication plugin implementation
- `TokenManager` - Token lifecycle management (refresh, cache)
- `AuthConfig` - Configuration properties for the authentication subsystem

## Configuration

### Required Environment Variables

- `LOGIN` - Polynom API username
- `PASSWORD` - Polynom API password

See [AuthConfig.md](AuthConfig.md) for details.

## Usage rules and invariants

- The module follows the Backend-for-Frontend pattern to provide a tailored API for frontend needs
- Authentication is handled through the auth package components
- HTTP communication with Polynom service is encapsulated in the PolynomClient
- The module integrates with the broader ETL Assistant ecosystem
- Token management ensures secure and efficient API access