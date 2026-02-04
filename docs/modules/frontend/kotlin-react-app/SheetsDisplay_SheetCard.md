# SheetsDisplay.kt and SheetCard.kt

## Responsibility

These files implement React components for displaying workbook data in a user-friendly format. SheetsDisplay.kt renders a collection of sheets from an Excel workbook, while SheetCard.kt represents an individual sheet with its metadata and headers.

## Intended usage

SheetsDisplay should be used to render the collection of sheets from an uploaded workbook. It takes an EtlWorkbookTransport object as input and renders a SheetCard for each sheet in the workbook. SheetCard should be used to display the details of a single sheet, including its title, entry count, and headers.

## Non-goals and boundaries

- These components do not handle file parsing or workbook processing - that is done by backend services
- They do not manage application state or perform API calls
- They do not implement complex data manipulation or transformation logic
- They do not handle file uploads or downloads

## Key invariants

- SheetsDisplay iterates through the sheets in the workbook and renders a SheetCard for each one
- SheetCard displays the sheet title, number of entries, and headers in a card layout
- Both components use Material UI components for consistent styling
- SheetCard renders headers as chips for easy scanning
- The components are designed to be reusable and accept data through props
- They focus on presentation rather than data processing