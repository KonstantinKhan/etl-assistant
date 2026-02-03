# etl-polynom-bff

## LLM Usage Contract

This document is the **authoritative source of truth** for the module structure.

Rules for LLM agents:
- Do NOT scan the repository tree unless explicitly instructed.
- Do NOT re-discover folder structure.
- Use this document as a cached mental model.
- Assume the structure described here is complete and up-to-date.
- Ask questions ONLY if a symbol is missing from this document.

## Module Semantic Map

### Root package
`com.khan366kos.etl.polynom.bff`

### Core components

#### PolynomClient
- Location: `bff/PolynomClient.kt`
- Responsibility:
    - HTTP client for Polynom service
    - Encapsulates request/response mapping
- Used by:
    - BFF routes
- Does NOT handle:
    - Authentication logic

#### auth package
Authentication and authorization layer.

- AuthModels
    - DTOs for auth-related requests/responses
- AuthPlugin
    - Ktor authentication plugin
- TokenManager
    - Token lifecycle management (refresh, cache)

#### config package
- AuthConfig
    - Configuration properties for auth subsystem

## Navigation Hints

- Any HTTP interaction with Polynom → `PolynomClient`
- Polynom HTTP client auth plugin → `auth` package
- Auth configuration → `config` package

## Stability Guarantee

- Package structure is stable.
- File paths may change, but component responsibilities do not.
- Refer to components by name, not by path.