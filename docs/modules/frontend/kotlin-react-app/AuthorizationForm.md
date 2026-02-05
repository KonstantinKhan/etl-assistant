# AuthorizationForm.kt

## Responsibility

The AuthorizationForm.kt file implements a React component that provides a user interface for entering authorization credentials and selecting a storage option. It handles user input for username, password, and storage selection, and communicates submission events to parent components via callbacks.

## Intended usage

The AuthorizationForm component should be used in contexts where users need to authenticate with the backend service. It accepts a list of storage options, handles user input for credentials, and calls the onSubmit callback when the form is submitted.

## Non-goals and boundaries

- The AuthorizationForm component does not perform the actual authentication - that is handled by backend services
- It does not manage application state beyond the local form inputs
- It does not handle API communication directly
- It does not validate credentials on the frontend

## Key invariants

- The component uses Material UI components for consistent styling
- It provides input fields for username and password
- It includes a dropdown selector for choosing a storage option
- It supports loading states to disable interaction during processing:
  - Storage loading state: Shows CircularProgress spinner (20px) next to the Select field and helper text "Загрузка доступных хранилищ..." to indicate data is being fetched
  - Form submission loading state: Shows CircularProgress spinner (24px) inside the submit button
- It can display error messages passed from parent components via FormHelperText
- It handles form submission via the onSubmit callback
- It follows accessibility best practices for form elements