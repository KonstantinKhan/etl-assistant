# ReferenceListComponent.kt

## Responsibility

The ReferenceListComponent.kt file implements a Decompose component that manages the state and business logic for displaying a list of references. It handles loading references from the backend API and manages the UI state throughout the loading lifecycle.

## Intended usage

ReferenceListComponent should be used as part of the navigation component tree. It is instantiated by RootComponent when navigating to the reference list screen. The component exposes a StateFlow that React components can observe to render the appropriate UI.

## Non-goals and boundaries

- The component does not handle UI rendering - that is the responsibility of ReferenceListScreen and ReferenceListDisplay
- It does not implement navigation logic - navigation is handled by RootComponent
- It does not transform or map data - it receives domain models from ApiClient
- It does not handle authentication - it assumes the user is already authenticated

## Key invariants

- The component follows the Decompose component pattern by implementing ComponentContext
- State is managed through a StateFlow<ReferenceListUiState> that React components can observe
- All API calls are executed in a coroutine scope tied to the component lifecycle
- The component uses the Initial → Loading → Success/Error state pattern
- The loadReferences() method can be called to reload data

## State Management

### ReferenceListUiState
- `Initial`: Component initialized but no data loaded yet
- `Loading`: Currently fetching references from the backend
- `Success(references: List<Reference>)`: References successfully loaded
- `Error(message: String)`: Failed to load references with error message

## Methods

### `loadReferences()`
Initiates loading of references from the backend. Sets state to Loading, calls ApiClient.fetchReferences(), and updates state to Success or Error based on the result.