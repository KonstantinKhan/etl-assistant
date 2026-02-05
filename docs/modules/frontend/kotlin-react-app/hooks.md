# hooks.kt

## Responsibility

The hooks.kt file implements custom React hooks that provide reusable stateful logic for functional components in the application. It implements the useCollectState hook for collecting Flow state updates and the useValue hook for observing Decompose Value<T> changes.

## Intended usage

The hooks in this file should be used by functional components that need to subscribe to Kotlin Flows and update their state based on emissions from those flows. The useCollectState hook is particularly useful for connecting ViewModel state to React components.

## Non-goals and boundaries

- The hooks do not implement business logic beyond state collection
- They do not handle API communication directly
- They do not manage complex application state - they simply bridge Kotlin Flows with React state
- They do not perform data transformation or validation

## Key invariants

- The useCollectState hook uses React's useState and useEffectOnce hooks internally
- It properly collects from the provided Flow and updates the component state when new values are emitted
- It handles the collection lifecycle appropriately, starting collection when the component mounts
- It ensures that the component re-renders when the Flow emits new values
- It follows React hook naming conventions by starting with "use"
- It properly manages the subscription to avoid memory leaks

## Hooks

### `useCollectState<T>(flow: StateFlow<T>): T`
Subscribes to a Kotlin StateFlow and returns the current state value. The component will re-render whenever the Flow emits a new value.

### `useValue<T>(value: Value<T>): T`
Subscribes to a Decompose Value<T> and returns the current value. The component will re-render whenever the Value changes. This hook is specifically designed for Decompose navigation components that use Value<ChildStack> instead of StateFlow.