# PolynomClient.kt

## Responsibility

The PolynomClient.kt file implements an HTTP client for communicating with the Polynom service. It handles HTTP
communication and serializes/deserializes transport models. The client returns transport models which can be mapped
to domain models using mappers from the etl-mapper module.

## Intended usage

PolynomClient should be used when making HTTP requests to the Polynom service. It abstracts away the details of HTTP
communication, request formatting, and response parsing, providing a clean API for interacting with Polynom
functionality.

## Non-goals and boundaries

- The client does not implement business logic beyond the communication layer
- It does not manage application state
- It does not handle UI concerns or formatting for display
- It does not perform authentication directly (relies on auth components)

## Key invariants

- The client handles HTTP communication using appropriate libraries
- It properly serializes requests and deserializes responses to/from transport models
- It follows consistent error handling patterns
- It maintains separation between communication logic and business logic
- It does NOT perform mapping to domain models - mapping is handled by the etl-mapper module

## API Methods

### `getReference(): List<ReferenceTransport>`
Retrieves all references from the Polynom service. Returns a list of ReferenceTransport models which can be mapped
to Reference domain models using the `toReference()` mapper from etl-mapper module.