# App

## Responsibility

App is the main React component that serves as the root of the React component tree. It creates the RootComponent (Decompose), observes the navigation stack, and renders the active screen component based on the current navigation state.

## Intended usage

- Top-level React component rendered by Main.kt
- Creates RootComponent once using useMemo with DefaultComponentContext
- Subscribes to RootComponent.childStack using useValue hook
- Renders active child screen (AuthScreen, ReferenceListScreen, or WorkbookScreen) based on navigation
- Provides application title and container layout

## Rendering behavior

- Creates RootComponent once on mount (via useMemo)
- Observes childStack.active.instance using useValue to determine which screen to render
- Renders AuthScreen when active child is RootComponent.Child.Auth
- Renders ReferenceListScreen when active child is RootComponent.Child.ReferenceList
- Renders WorkbookScreen when active child is RootComponent.Child.Workbook
- Passes the corresponding Decompose component to screen via props

## Structure

```kotlin
Container {
    Typography { +"ETL Assistant" }  // App title

    when (val child = childStack.active.instance) {
        is RootComponent.Child.Auth -> AuthScreen { component = child.component }
        is RootComponent.Child.ReferenceList -> ReferenceListScreen { component = child.component }
        is RootComponent.Child.Workbook -> WorkbookScreen { component = child.component }
    }
}
```

## Non-goals and boundaries

- Does NOT manage business logic (delegated to Decompose components)
- Does NOT perform API calls (delegated to Decompose components)
- Does NOT manage ViewModels (architecture migrated to Decompose)
- Does NOT use React Context for passing Decompose components (uses props)

## Key invariants

- RootComponent is created only once using useMemo
- Cleanup of ApiClient happens on component unmount
- Navigation state is observed reactively via useValue hook
- Screen components always receive Decompose components via props
- Uses Material UI components for consistent styling and layout