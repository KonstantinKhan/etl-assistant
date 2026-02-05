# AuthScreen

## Responsibility

AuthScreen is a React functional component responsible for rendering the authentication UI. It observes state from AuthComponent and renders the authorization form with loading states, error messages, and success notifications.

## Intended usage

- React FC (Functional Component) consuming AuthComponent via props
- Created and rendered by App component when RootComponent.childStack.active is Child.Auth
- Subscribes to AuthComponent state using useCollectState hook
- Delegates all user actions (form submission) to AuthComponent

## Props

```kotlin
external interface AuthScreenProps : Props {
    var component: AuthComponent
}
```

## Rendering behavior

- Always renders AuthorizationForm immediately (форма доступна сразу)
- Passes storage loading state to the form via props
- Username/password fields are available for input while storage definitions load
- Storage selector is disabled during loading with "Загрузка хранилищ..." placeholder
- Shows error in storage selector if storage definitions fail to load
- Displays success alert briefly after successful authentication (before navigation)
- Handles form submission by calling component.submitAuthorization()

## Non-goals and boundaries

- Does NOT manage state (delegated to AuthComponent)
- Does NOT perform API calls directly
- Does NOT handle navigation (AuthComponent triggers navigation via callback)
- Purely presentational - only renders UI based on component state

## Key invariants

- Always receives AuthComponent through props (not via React Context)
- Uses useCollectState to subscribe to StateFlow from Decompose component
- All user actions are delegated to AuthComponent methods