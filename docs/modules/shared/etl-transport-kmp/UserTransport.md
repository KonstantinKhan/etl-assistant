# UserTransport

## Responsibility
- Represents user data in transport layer
- Contains user profile information, roles, and permissions
- Used for authentication and authorization systems
- Enables user data exchange between layers

## Fields
- `description`: String? - Description of the user
- `code`: String? - Code associated with the user
- `externalId`: String? - External identifier for the user
- `writeAccess`: Boolean - Indicates if the user has write access (default: false)
- `id`: String? - Unique identifier for the user
- `objectId`: Int - Object identifier (default: 0)
- `typeId`: Int - Type identifier
- `firstName`: String? - User's first name
- `patronymic`: String? - User's patronymic name
- `lastName`: String? - User's last name
- `isAdministrator`: Boolean - Indicates if the user is an administrator (default: false)
- `email`: String? - User's email address
- `fullName`: String? - User's full name
- `im`: String? - Instant messaging contact information
- `login`: String? - User's login name
- `phone`: String? - User's phone number
- `hasPhoto`: Boolean - Indicates if the user has a photo (default: false)
- `additionalInfo`: String? - Additional information about the user
- `web`: String? - Web-related information
- `roles`: List<RoleTransport>? - List of roles assigned to the user
- `positions`: List<PositionTransport>? - List of positions held by the user

## Used by
- Authentication and authorization systems
- User data exchange between layers