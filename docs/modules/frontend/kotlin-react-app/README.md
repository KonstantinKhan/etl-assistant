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

The module uses **Decompose** library for component-based architecture with lifecycle management, state management via StateFlow, and navigation via ChildStack. React is used purely for UI rendering, while Decompose components handle all business logic and state.

## High-level semantic component index

### Decompose Components (State Management & Navigation)
- `RootComponent` - Root Decompose component managing navigation via ChildStack and global dependencies
- `AuthComponent` - Decompose component handling authentication flow and storage selection
- `WorkbookComponent` - Decompose component managing workbook state and operations

### React UI Components (Presentation Layer)
- `App` - Main React component rendering active navigation child from RootComponent
- `AuthScreen` - React screen component rendering authentication UI by consuming AuthComponent
- `WorkbookScreen` - React screen component rendering workbook UI by consuming WorkbookComponent
- `AuthorizationForm` - Presentational form component for user login and storage selection
- `FileUpload` - File upload UI component
- `SheetsDisplay` - Component that renders the sheets of an uploaded workbook
- `SheetCard` - Individual card component representing a single sheet with its headers and metadata

### Core Application
- `Main` - Entry point of the application that mounts the React component to the DOM

### API Communication Layer
- `ApiClient` - Singleton object handling all HTTP communication with the backend API using Ktor HTTP client

### Utilities
- `hooks` - React hooks (useCollectState for StateFlow integration with React)

## Usage rules and invariants

### Architecture
- **Decompose for state management**: All business logic and state management is handled by Decompose components
- **React for UI only**: React components are purely presentational and consume Decompose components via props
- **No React Context for Decompose**: Do NOT use React Context to pass Decompose components. Always pass them via props.
- **Dependency Injection**: Dependencies (ApiClient, repositories) are passed through constructors of Decompose components
- **Navigation**: Navigation between screens is handled exclusively via Decompose's ChildStack

### Component Structure
- **RootComponent** → Navigation (ChildStack) → Screen Components (AuthComponent, WorkbookComponent) → React UI (AuthScreen, WorkbookScreen)
- Global state lives in RootComponent and is passed to child components through constructors
- Each Decompose component uses lifecycle-aware coroutineScope() for automatic cleanup
- React UI components subscribe to Decompose component state using useCollectState hook

### API and Communication
- The application uses Material UI components for consistent styling
- All API communication happens through the ApiClient singleton
- The application connects to the backend at `http://localhost:8080` by default, but can be overridden via window.API_BASE_URL
- File uploads are limited to Excel formats (.xlsx, .xls)

### Best Practices
- All React components follow functional component patterns with hooks
- Error handling is implemented consistently across all async operations
- StateFlow is used for reactive state management in Decompose components
- Lifecycle management is automatic via ComponentContext (no manual cleanup() needed)