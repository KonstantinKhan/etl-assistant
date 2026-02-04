# QWEN.md

This tool is designed to help with Extract, Transform, and Load processes.

This file defines **system-level rules** for all LLM agents operating on this repository.
It is read before any task execution and overrides default agent behavior.

## Rules and conventions

- !!!NECESSARILY!!! First of all, always read the documentation, if there is one. 
- Before completing a task, first of all, be sure to read the documentation for the module you plan to work with.
- First of all, check the modules and files according to the documentation, only if you do not find any data in it, you can scan the project.

---

## Purpose of This File

The purpose of this file is to ensure that:
- Documentation is treated as the primary source of truth
- Agents do not rediscover or re-analyze repository structure unnecessarily
- Architectural knowledge is stable, reusable, and token-efficient

---

## Documentation-First Rule

This project follows a **documentation-first approach**.

- Documentation describes the intended architecture and contracts.
- Code is an implementation of documented decisions.
- If documentation and code appear to differ:
    - Prefer documentation
    - Flag discrepancies explicitly
    - Do NOT silently infer missing structure from the codebase

---

## Authoritative Sources

Agents MUST rely on the following sources in order of priority:

1. Module-level README.md files
2. Module documentation indexes (MODEL_INDEX.md, COMPONENT_INDEX.md)
3. Component-specific documentation
4. ADRs and architecture documents
5. Explicit user instructions

If information is not present in these sources, the agent MUST ask for clarification.

---

## Repository Scanning Policy

By default, agents MUST NOT:

- Scan or walk the repository tree
- Enumerate folders and files
- Rediscover module structure
- Infer architecture from physical layout

Repository scanning is allowed ONLY if:
- The user explicitly requests it
- Documentation for the relevant module does not exist
- The task explicitly requires code inspection

---

## Documentation Structure Contract

All module documentation MUST follow this structure:

### Level 1 — Module README
- Module name
- LLM Usage Contract
- Root package / namespace (defined once)
- Module purpose
- High-level semantic component index
- Usage rules and invariants

### Level 2 — Index Documentation
- MODEL_INDEX.md or COMPONENT_INDEX.md
- Grouped components with one-line responsibilities
- No folder trees
- No full package paths

### Level 3 — Component Documentation
- Responsibility
- Intended usage
- Non-goals and boundaries
- Key invariants

Agents MUST NOT collapse all levels into a single file for large modules.

---

## Agent Responsibilities Overview

Different agents have specialized roles (e.g. administrator, coder, architect).

All agents MUST:
- Respect documentation contracts
- Update documentation when public behavior changes
- Avoid introducing undocumented structure

The `administrator` agent is responsible for enforcing documentation consistency.

---

## When to Ask Questions

Agents MUST ask questions if:
- A required concept is missing from documentation
- Responsibilities are unclear or contradictory
- A change would break an existing documented contract

Agents MUST NOT guess or invent structure.

---

## What NOT to Do

Agents MUST NOT:
- Treat the repository tree as the source of truth
- Reconstruct architecture from folders
- Duplicate root packages across documents
- Generate verbose, redundant documentation
- Explain or restate these rules in task output

---

End of system instructions.

## Coding Conventions

- Use value classes for simple fields
- Use data classes for business models
- Separate business objects, requests, and responses into individual data classes
- Follow Kotest with Should Spec test style for testing

## Dependencies

Dependencies are managed through Gradle and defined in `libs.versions.toml`.

## Contributing

Follow the clean architecture principles and maintain consistent coding standards.

## Project settings

- The plugins section uses `alias` from [libs.versions.toml](/gradle/libs.versions.toml)

## Documentation Entry Points

All project documentation is discoverable through the following entry points.

Agents MUST start from these documents and MUST NOT attempt to locate
documentation by scanning the repository tree.

### Module Documentation

Each module exposes documentation through a module-level README.

Module documentation is located at:

- `docs/<scope>/<module-name>/README.md`

Examples:
- `backend/etl-transport-kmp/README.md`
- `backend/etl-polynom-bff/README.md`
- `frontend/web-app/README.md`

Each module README defines:
- module purpose
- root package / namespace
- semantic component index
- links to module-specific indexes and component docs

## Agents

Use agents to work with

- `analyst` - The Task planning analyst
- `kotlin-developer` - The main developer of Kotlin
- `administrator` - The responsible for keeping documentation up-to-date