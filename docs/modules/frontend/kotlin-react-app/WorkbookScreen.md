# WorkbookScreen

## Responsibility

WorkbookScreen is a React functional component that renders the workbook operations UI. It observes state from WorkbookComponent and renders file upload controls, demo file loading button, and workbook sheets display.

## Intended usage

- React FC (Functional Component) consuming WorkbookComponent via props
- Created and rendered by App component when RootComponent.childStack.active is Child.Workbook
- Subscribes to WorkbookComponent state using useCollectState hook
- Delegates all user actions to WorkbookComponent methods

## Props

```kotlin
external interface WorkbookScreenProps : Props {
    var component: WorkbookComponent
}
```

## Rendering behavior

- Renders FileUpload component (disabled during loading)
- Renders "Загрузить демо-файл" button (disabled during loading)
- Displays appropriate UI based on state:
  - `Initial` - Shows prompt to select or load file
  - `Loading` - Shows loading spinner
  - `Success` - Renders SheetsDisplay with workbook data
  - `Error` - Shows error alert with message

## Non-goals and boundaries

- Does NOT manage state (delegated to WorkbookComponent)
- Does NOT perform API calls directly
- Does NOT handle navigation
- Purely presentational - only renders UI based on component state

## Key invariants

- Always receives WorkbookComponent through props (NOT via React Context)
- Uses useCollectState to subscribe to StateFlow from Decompose component
- All user actions (file upload, load demo) are delegated to WorkbookComponent methods
- Uses Material UI components for consistent styling