# AuthComponent

## Responsibility

AuthComponent is a Decompose component responsible for managing the authentication flow and storage definitions loading. It handles user authentication state, loads available storage options from the backend, and triggers navigation to the Workbook screen upon successful authorization.

## Intended usage

- Created by RootComponent as part of the navigation hierarchy
- Consumed by AuthScreen React component via props
- Receives ApiClient and navigation callback through constructor (Dependency Injection)
- Automatically loads storage definitions on initialization
- Calls onAuthSuccess callback to trigger navigation after successful authentication

## Public API

### Constructor Parameters
- `componentContext: ComponentContext` - Decompose component context for lifecycle management
- `apiClient: ApiClient` - HTTP client for backend communication (injected)
- `onAuthSuccess: () -> Unit` - Callback to trigger navigation on successful auth

### State
- `authState: StateFlow<AuthorizationUiState>` - Current authentication state
  - `Initial` - No authentication attempted
  - `Loading` - Authentication in progress
  - `Success(message)` - Authentication succeeded with message
  - `Error(message)` - Authentication failed with error message

- `storageState: StateFlow<StorageLoadingState>` - Storage definitions loading state
  - `Initial` - Not yet loaded
  - `Loading` - Loading in progress
  - `Success(storageDefinitions)` - Loaded successfully
  - `Error(message)` - Loading failed

### Methods
- `loadStorageDefinitions()` - Loads available storage options from backend
- `submitAuthorization(username, password, storageId)` - Submits authentication request

## Non-goals and boundaries

- Does NOT handle UI rendering (delegated to AuthScreen)
- Does NOT manage navigation directly (uses callback provided by RootComponent)
- Does NOT store authentication tokens (future enhancement)

## Key invariants

- Automatically loads storage definitions on component initialization
- Calls onAuthSuccess() callback after successful authentication to trigger navigation
- Uses lifecycle-aware coroutineScope() for automatic cleanup
- All API calls are executed in component's coroutine scope (automatic cancellation on component disposal)