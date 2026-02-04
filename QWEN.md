# QWEN.md

## Status
- Type: System Specification
- Scope: Global
- Priority: P0

## Purpose
Define mandatory system-level rules for all LLM agents operating in this repository.
Ensure documentation-first behavior, architectural stability, and token-efficient reuse.

---

## Rule Priority Levels

- P0 — Architectural & Documentation Contracts (must never be violated)
- P1 — Agent Behavior Constraints
- P2 — Efficiency & Token Optimization
- P3 — Style & Conventions

In case of conflict, higher priority rules override lower ones.

---

## Core Contract: Documentation-First (P0)

### Rule 1
Before performing ANY task, an agent MUST:
1. Read all relevant documentation
2. Treat documentation as the primary source of truth
3. Avoid inferring structure from the repository tree

### Rule 2
If documentation and code differ:
- Prefer documentation
- Explicitly flag the discrepancy
- MUST NOT silently infer missing structure from code

---

## Authoritative Sources (P0)

Agents MUST rely on the following sources in strict order:

1. Module-level README.md
2. MODEL_INDEX.md / COMPONENT_INDEX.md
3. Component-specific documentation
4. ADRs and architecture documents
5. Explicit user instructions

If required information is missing, the agent MUST request clarification.

---

## Repository Scanning Policy (P1)

By default, agents MUST NOT:
- Scan or walk the repository tree
- Enumerate files or folders
- Reconstruct architecture from layout

Scanning is allowed ONLY if one of the following is true:
- The user explicitly requests it
- Relevant documentation does not exist
- Documentation is internally contradictory or incomplete
- The task explicitly requires code inspection

If scanning is used:
- The reason MUST be stated explicitly
- Findings MUST be reconciled back into documentation

---

## Documentation Structure Contract (P0)

### Level 1 — Module README (mandatory)
Must include:
- Module name
- LLM Usage Contract
- Module purpose
- Root package / namespace (defined exactly once)
- High-level semantic component index
- Usage rules and invariants
- Explicit non-goals (if applicable)

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

Agents MUST NOT collapse all levels into a single document.

---

## Prohibited Agent Behavior (P0)

Agents MUST NOT:
- Treat repository layout as source of truth
- Invent undocumented components or layers
- Assume industry conventions unless documented
- Duplicate root package definitions
- Generate verbose or redundant documentation
- Explain or restate system rules in task output

---

## Conflict Resolution & Fallback (P0)

If rules conflict:
1. Follow the higher-priority rule
2. Explicitly state the conflict
3. Request clarification if P0 or P1 is affected

---

## Coding & Project Conventions (P3)

- Use value classes for simple fields
- Use data classes for business models
- Separate business objects, requests, and responses
- Use Kotest with Should Spec style
- Dependencies are managed via `libs.versions.toml`

---

## Documentation Entry Points (P1)

Agents MUST start from module-level README files located at:

`docs/<scope>/<module-name>/README.md`

Agents MUST NOT attempt to discover documentation by scanning the repository tree.

---

End of system specification.