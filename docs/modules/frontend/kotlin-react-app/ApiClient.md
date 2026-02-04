# ApiClient.kt

## Responsibility

The ApiClient.kt file implements a singleton object responsible for all HTTP communication between the frontend application and the backend API. It handles requests for uploading files, fetching workbook data, retrieving storage definitions, and performing user authorization.

## Intended usage

The ApiClient should be used as the central communication layer for all backend interactions. It provides asynchronous methods that can be called from view models or other components to perform API operations. The client uses Ktor HTTP client with JSON serialization for communication.

## Non-goals and boundaries

- The ApiClient does not manage application state - that is the responsibility of the view models
- It does not handle UI-specific concerns or formatting of data for display
- It does not implement business logic beyond the API communication layer
- It does not cache data locally (other than through HTTP mechanisms)

## Key invariants

- The ApiClient is implemented as a singleton object to maintain a single HTTP client instance
- It defaults to connecting to "http://localhost:8080" but can be overridden via window.API_BASE_URL
- All API methods are suspend functions to support Kotlin coroutines
- The client uses JSON serialization for request/response bodies
- The client properly closes the underlying HTTP client when no longer needed via the close() method
- File uploads are handled using multipart form data