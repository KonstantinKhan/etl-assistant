# ReferenceListScreen.kt

## Responsibility

The ReferenceListScreen.kt file implements a React functional component that renders the main screen for displaying the list of references. It observes the ReferenceListComponent state and renders appropriate UI based on the current state (loading, success, error).

## Intended usage

ReferenceListScreen should be used as the main screen component when the user navigates to the reference list view. It is rendered by App.kt when the active child is RootComponent.Child.ReferenceList.

## Non-goals and boundaries

- The screen does not handle business logic - that is the responsibility of ReferenceListComponent
- It does not implement the actual reference list rendering - that is delegated to ReferenceListDisplay
- It does not manage navigation - navigation is handled by RootComponent
- It does not perform API calls - those are handled by ReferenceListComponent

## Key invariants

- The screen is implemented as a React FC (functional component)
- It uses the useCollectState hook to observe ReferenceListComponent.state
- It renders different UI based on the ReferenceListUiState (Initial, Loading, Success, Error)
- It provides a refresh button that calls component.loadReferences()
- It uses Material-UI components for consistent styling

## UI Structure

- Container with top margin
- Typography heading "Список справочников"
- Refresh button (disabled during loading)
- Conditional rendering based on state:
  - Loading: CircularProgress spinner
  - Success: ReferenceListDisplay with references
  - Error: Alert with error message
  - Initial: Loading text