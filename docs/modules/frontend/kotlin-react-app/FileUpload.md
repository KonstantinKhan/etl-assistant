# FileUpload.kt

## Responsibility

The FileUpload.kt file implements a React component that provides a user interface for selecting and uploading Excel files to the backend service. It handles the file selection process and communicates the selected file to parent components via callbacks.

## Intended usage

The FileUpload component should be used in contexts where users need to upload Excel files (.xlsx, .xls). It provides a button interface that triggers a hidden file input element, displays the selected filename, and calls the onFileSelected callback when a file is chosen.

## Non-goals and boundaries

- The FileUpload component does not perform the actual file upload to the server - that is handled by other components or view models
- It does not validate file contents or format beyond restricting file type selection
- It does not manage any application state beyond the local UI state for displaying the selected filename
- It does not handle API communication directly

## Key invariants

- The component uses a hidden HTML file input element to trigger the file selection dialog
- It restricts file selection to Excel formats (.xlsx, .xls) using the accept attribute
- It provides visual feedback showing the name of the selected file
- It supports a disabled state to prevent interaction during processing
- It resets the file input value when clicked to allow re-selection of the same file
- It uses Material UI components for consistent styling