# App.kt

## Responsibility

The App.kt file implements the main application component that serves as the root of the React component tree. It orchestrates the UI state, manages the primary user workflow, and integrates various UI components and view models to provide a cohesive user experience.

## Intended usage

The App component should be used as the top-level component that encompasses all other components in the application. It manages the state of the workbook and authorization systems using view models and provides the main layout structure using Material UI components.

## Non-goals and boundaries

- The App component does not implement business logic beyond UI orchestration
- It does not directly handle API communication - that is delegated to the ApiClient
- It does not manage complex state transformations - that is handled by the view models
- It does not implement detailed UI controls - those are delegated to child components

## Key invariants

- The App component uses React.memo and useMemo hooks for performance optimization
- It initializes and manages the lifecycle of the view models (WorkbookViewModel, AuthorizationViewModel)
- It handles cleanup of resources when the component unmounts
- It uses MUI components for consistent styling and responsive layout
- It implements a tab-based interface for different sections of the application
- It manages the display of different UI states (Initial, Loading, Error, Success) for the workbook functionality
- It coordinates the display of authorization and storage selection UI