# Component Index for kotlin-react-app

## UI Components
- `App` - Main application component that orchestrates UI state and manages the primary user workflow
- `FileUpload` - Component for selecting and uploading Excel files to the backend
- `SheetsDisplay` - Component that renders the sheets of an uploaded workbook
- `SheetCard` - Individual card component representing a single sheet with its headers and metadata
- `AuthorizationForm` - Form component for user authentication and storage selection
- `Main` - Entry point of the application that mounts the React component to the DOM

## API Communication Layer
- `ApiClient` - Singleton object handling all HTTP communication with the backend API using Ktor HTTP client

## State Management Components (ViewModels)
- `WorkbookViewModel` - Manages state related to workbook processing and file uploads
- `AuthorizationViewModel` - Manages user authentication state and storage connection
- `StorageDefinitionsViewModel` - Manages the list of available storage options

## Utilities
- `hooks` - Collection of custom React hooks for state management in functional components