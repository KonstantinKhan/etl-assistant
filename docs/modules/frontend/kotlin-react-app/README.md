# kotlin-react-app

## LLM Usage Contract

This document is the **authoritative source of truth** for the module structure.

Rules for LLM agents:
- Do NOT scan the repository tree unless explicitly instructed.
- Do NOT re-discover folder structure.
- Use this document as a cached mental model.
- Assume the structure described here is complete and up-to-date.
- Ask questions ONLY if a symbol is missing from this document.

## Root package specification
`com.khan366kos.frontend.kotlin.react.app`

## Module purpose

The `kotlin-react-app` module is a Kotlin/JS React frontend application that serves as the user interface for the ETL Assistant system. It provides functionality for uploading Excel files, displaying workbook sheets, and managing user authorization to connect with backend services.

## High-level semantic component index

### Core Application Components
- `App` - Main application component that orchestrates UI state and manages the primary user workflow
- `Main` - Entry point of the application that mounts the React component to the DOM

### UI Components
- `FileUpload` - Component for selecting and uploading Excel files to the backend
- `SheetsDisplay` - Component that renders the sheets of an uploaded workbook
- `SheetCard` - Individual card component representing a single sheet with its headers and metadata
- `AuthorizationForm` - Form component for user authentication and storage selection

### API Communication Layer
- `ApiClient` - Singleton object handling all HTTP communication with the backend API using Ktor HTTP client

### State Management Components (ViewModels)
- `WorkbookViewModel` - Manages state related to workbook processing and file uploads
- `AuthorizationViewModel` - Manages user authentication state and storage connection
- `StorageDefinitionsViewModel` - Manages the list of available storage options

### Utilities
- `hooks` - Collection of custom React hooks for state management in functional components

## Usage rules and invariants

- The application uses Material UI components for consistent styling
- All API communication happens through the ApiClient singleton
- State management follows a ViewModel pattern similar to MVVM architecture
- The application connects to the backend at `http://localhost:8080` by default, but can be overridden via window.API_BASE_URL
- File uploads are limited to Excel formats (.xlsx, .xls)
- All React components follow functional component patterns with hooks for state management
- Error handling is implemented consistently across all async operations