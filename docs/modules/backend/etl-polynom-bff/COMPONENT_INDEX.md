# Component Index for etl-polynom-bff

## Core Service Integration
- `PolynomClient` - HTTP client for communicating with the Polynom service, encapsulates request/response mapping
- `AuthModels` - Data transfer objects for authentication-related requests and responses

## Authentication and Authorization
- `AuthPlugin` - Ktor authentication plugin implementation
- `TokenManager` - Token lifecycle management (refresh, cache)
- `AuthConfig` - Configuration properties for the authentication subsystem