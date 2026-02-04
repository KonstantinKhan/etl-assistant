# AuthorizationViewModel.kt

## Responsibility

The AuthorizationViewModel.kt file implements a ViewModel class responsible for managing the state related to user authentication and storage connection. It handles the state transitions for authorization operations and manages the list of available storage options.

## Intended usage

The AuthorizationViewModel should be instantiated and used by UI components that need to manage authorization-related state. It provides methods for submitting authorization credentials, loading storage definitions, and managing the state of these operations. UI components can observe the authState and storageState properties to react to state changes.

## Non-goals and boundaries

- The AuthorizationViewModel does not directly handle UI rendering or DOM manipulation
- It does not implement the actual API calls - that is delegated to the ApiClient
- It does not manage workbook processing state - that is handled by the WorkbookViewModel
- It does not perform credential validation on the frontend - that is handled by backend services

## Key invariants

- The ViewModel manages two separate state flows: authState for authorization status and storageState for storage options
- It properly cleans up resources when the cleanup method is called
- It uses Kotlin coroutines for handling asynchronous operations
- It delegates API communication to the ApiClient singleton
- The states are immutable, with each state change creating a new state object
- It handles error cases gracefully and reports them through the state system
- It loads storage definitions automatically when initialized