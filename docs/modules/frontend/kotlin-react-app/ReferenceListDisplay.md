# ReferenceListDisplay.kt

## Responsibility

The ReferenceListDisplay.kt file implements a React functional component that renders the actual list of references as Material-UI cards. It displays reference metadata including name, description, ID, and access permissions.

## Intended usage

ReferenceListDisplay should be used by ReferenceListScreen when the state is ReferenceListUiState.Success. It receives a list of Reference domain models and renders them in a visually organized manner.

## Non-goals and boundaries

- The component does not handle state management - it receives data via props
- It does not implement business logic or data fetching
- It does not handle user interactions beyond display (no edit/delete functionality)
- It does not perform data transformation - it displays domain models as-is

## Key invariants

- The component is implemented as a React FC (functional component)
- It receives props containing a List<Reference>
- Each reference is rendered as a Material-UI Card
- The component uses type-safe accessors (asString(), asBoolean()) on value classes
- It uses Material-UI components for consistent styling
- Write access is displayed as a colored Chip (success for write enabled, default for read-only)

## UI Structure

- Box container with flex column layout and gap between items
- Typography showing total count of references
- For each reference:
  - Card with CardContent
  - Typography h6 variant for reference name
  - Typography body2 with secondary color for description
  - Typography caption for technical details (ID, Object ID, Type ID)
  - Chip showing write access status ("Запись разрешена" / "Только чтение")