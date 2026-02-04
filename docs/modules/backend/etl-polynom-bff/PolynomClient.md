# PolynomClient.kt

## Responsibility

The PolynomClient.kt file implements an HTTP client for communicating with the Polynom service. It encapsulates the request/response mapping and handles the communication layer between the BFF and the Polynom backend service.

## Intended usage

PolynomClient should be used when making HTTP requests to the Polynom service. It abstracts away the details of HTTP communication, request formatting, and response parsing, providing a clean API for interacting with Polynom functionality.

## Non-goals and boundaries

- The client does not implement business logic beyond the communication layer
- It does not manage application state
- It does not handle UI concerns or formatting for display
- It does not perform authentication directly (relies on auth components)

## Key invariants

- The client handles HTTP communication using appropriate libraries
- It properly serializes requests and deserializes responses
- It follows consistent error handling patterns
- It maintains separation between communication logic and business logic