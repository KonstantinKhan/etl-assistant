# WorkbookViewModel.kt

## Responsibility

The WorkbookViewModel.kt file implements a ViewModel class responsible for managing the state related to workbook processing and file uploads. It handles the state transitions between initial, loading, success, and error states for workbook operations.

## Intended usage

The WorkbookViewModel should be instantiated and used by UI components that need to manage workbook-related state. It provides methods for uploading files, loading sheets, and managing the state of these operations. UI components can observe the state property to react to state changes.

## Non-goals and boundaries

- The WorkbookViewModel does not directly handle UI rendering or DOM manipulation
- It does not implement the actual API calls - that is delegated to the ApiClient
- It does not manage authorization or storage selection state - that is handled by other ViewModels
- It does not perform file parsing or processing - that is done by backend services

## Key invariants

- The ViewModel manages a state flow that can be one of four states: Initial, Loading, Success, or Error
- It properly cleans up resources when the cleanup method is called
- It uses Kotlin coroutines for handling asynchronous operations
- It delegates API communication to the ApiClient singleton
- The state is immutable, with each state change creating a new state object
- It handles error cases gracefully and reports them through the state system