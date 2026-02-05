# WorkbookComponent

## Responsibility

WorkbookComponent is a Decompose component responsible for managing workbook operations including file uploads and demo sheet loading. It maintains workbook state and exposes methods for UI interactions.

## Intended usage

- Created by RootComponent as part of navigation hierarchy
- Consumed by WorkbookScreen React component via props
- Receives ApiClient through constructor (Dependency Injection)
- Lifecycle is managed automatically by ComponentContext

## Public API

### Constructor Parameters
- `componentContext: ComponentContext` - Decompose component context for lifecycle management
- `apiClient: ApiClient` - HTTP client for backend communication (injected)

### State
- `state: StateFlow<WorkbookUiState>` - Current workbook state
  - `Initial` - No workbook loaded
  - `Loading` - Operation in progress (upload or loading)
  - `Success(workbook)` - Workbook loaded successfully
  - `Error(message)` - Operation failed with error message

### Methods
- `uploadFile(file: BrowserFile)` - Uploads Excel file to backend and loads workbook
- `loadSheets()` - Loads demo workbook from backend

## Non-goals and boundaries

- Does NOT handle UI rendering (delegated to WorkbookScreen)
- Does NOT create API client (receives it via constructor)
- Does NOT manage navigation (no child components or navigation logic)

## Key invariants

- Uses lifecycle-aware coroutineScope() for automatic cleanup
- All operations update state appropriately (Loading → Success/Error)
- Errors are caught and reported through state system
- ApiClient is injected through constructor (Dependency Injection pattern)
- State transitions are atomic and predictable