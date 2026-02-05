# AuthConfig.kt

## Responsibility

The AuthConfig.kt file defines configuration properties for the authentication subsystem. It provides a centralized location for authentication-related settings and parameters.

## Intended usage

AuthConfig should be used to configure the authentication system with necessary parameters such as token expiration times, secret keys, and other authentication settings. It provides a way to customize the authentication behavior through configuration.

## Non-goals and boundaries

- The config does not implement authentication logic
- It does not handle runtime authentication decisions
- It does not manage token creation or validation
- It does not store user-specific authentication data

## Key invariants

- The configuration follows consistent naming conventions
- It provides default values for optional parameters
- It validates configuration parameters during initialization
- It maintains separation between configuration and implementation logic

## Environment Variables

The following environment variables are **required**:

- `LOGIN` - Username for Polynom API authentication
- `PASSWORD` - Password for Polynom API authentication

The application will fail at startup if these variables are not set.

### Example
```bash
export LOGIN="your-username"
export PASSWORD="your-password"
```