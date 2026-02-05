# RootComponent

## Responsibility

RootComponent is the root Decompose component that manages the navigation hierarchy and global dependencies for the entire application. It uses Decompose's ChildStack for screen-based navigation and serves as the single source of truth for global state and dependencies.

## Intended usage

- Created once by App component with DefaultComponentContext
- Manages navigation between Auth, ReferenceList, and Workbook screens via ChildStack
- Provides global dependencies (ApiClient) to child components through constructors
- Exposes childStack as Value<ChildStack<Config, Child>> for React to observe

## Public API

### Navigation Configuration
```kotlin
sealed interface Config {
    @Serializable data object Auth : Config
    @Serializable data object Workbook : Config
    @Serializable data object ReferenceList : Config
}
```

### Child Types
```kotlin
sealed interface Child {
    data class Auth(val component: AuthComponent) : Child
    data class Workbook(val component: WorkbookComponent) : Child
    data class ReferenceList(val component: ReferenceListComponent) : Child
}
```

### State
- `childStack: Value<ChildStack<Config, Child>>` - Observable navigation stack

### Private Methods
- `createChild(config, context)` - Factory method creating screen components based on Config
- `onAuthSuccess()` - Navigation callback that pushes ReferenceList screen to stack

## Component Creation

When creating child components:
- **AuthComponent**: Receives ApiClient and onAuthSuccess callback
- **WorkbookComponent**: Receives ApiClient for dependency injection
- **ReferenceListComponent**: Receives ApiClient for dependency injection

## Non-goals and boundaries

- Does NOT handle UI rendering (delegated to React components in App)
- Does NOT perform API calls directly (provides ApiClient to children)
- Does NOT manage screen-specific state (delegated to screen components)

## Key invariants

- Navigation is managed exclusively through ChildStack
- Global dependencies are passed through child component constructors (Dependency Injection)
- Automatically handles back button navigation
- Initial screen is always Auth
- Navigation to ReferenceList happens only after successful authentication