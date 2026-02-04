# etl-ktor-server-app

## LLM Usage Contract

This document is the **authoritative source of truth** for the module structure.

Rules for LLM agents:
- Do NOT scan the repository tree unless explicitly instructed.
- Do NOT re-discover folder structure.
- Use this document as a cached mental model.
- Assume the structure described here is complete and up-to-date.
- Ask questions ONLY if a symbol is missing from this document.

## Root package specification
`com.khan366kos.etl.ktor.server.app`

## Module purpose

The `etl-ktor-server-app` module is a Ktor-based web server that serves as the backend API for the ETL Assistant system. It provides REST endpoints for file upload, workbook processing, storage management, and user authorization. The server acts as the central hub connecting the frontend application with backend services like Excel processing and external systems.

## High-level semantic component index

### Core Server Configuration
- `Application` - Main Ktor application setup and initialization
- `HTTP` - HTTP server configuration including CORS, compression, and other HTTP-level settings

### API Routing
- `Routing` - Definition of all API endpoints and their handlers
- `Serialization` - JSON serialization configuration for request/response handling

### Configuration Management
- `AppConfig` - Application configuration properties and settings

## Usage rules and invariants

- The server uses Ktor framework for web functionality
- All API endpoints follow REST principles
- JSON is used as the primary data interchange format
- The server handles authentication and authorization for protected endpoints
- Proper error handling and response codes are implemented consistently
- The server integrates with other backend services like the Excel service