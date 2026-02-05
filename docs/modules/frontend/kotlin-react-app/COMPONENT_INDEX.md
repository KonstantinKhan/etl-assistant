# Component Index for kotlin-react-app

## Decompose Components (State Management & Navigation)
- `RootComponent` - Root Decompose component managing navigation and global state
- `AuthComponent` - Handles authentication flow and storage selection
- `WorkbookComponent` - Manages workbook state and operations
- `ReferenceListComponent` - Manages reference list state and loading

## React UI Components (Presentation Layer)
- `App` - Main React component rendering active navigation child
- `AuthScreen` - Renders authentication UI consuming AuthComponent
- `WorkbookScreen` - Renders workbook UI consuming WorkbookComponent
- `ReferenceListScreen` - Renders reference list UI consuming ReferenceListComponent
- `AuthorizationForm` - Form for user login and storage selection
- `FileUpload` - File upload UI component
- `SheetsDisplay` - Renders the sheets of an uploaded workbook
- `SheetCard` - Individual card representing a single sheet
- `ReferenceListDisplay` - Renders individual reference cards with metadata

## Core Application
- `Main` - Entry point that mounts the React component to the DOM

## API Communication
- `ApiClient` - HTTP client for backend communication

## Utilities
- `hooks` - React hooks (useCollectState for StateFlow integration, useValue for Decompose Value integration)