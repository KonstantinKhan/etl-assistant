#!/bin/bash
set -o pipefail

cd "$(dirname "$0")/../../../.." || exit 1

OUTPUT=$(./gradlew build 2>&1)
GRADLE_EXIT_CODE=$?

if [ $GRADLE_EXIT_CODE -eq 0 ]; then
    echo "$OUTPUT" | grep -E "BUILD SUCCESSFUL|actionable task"
else
    echo "$OUTPUT" | grep -E "(error|fail|ERROR|FAIL|BUILD FAILED)" || echo "$OUTPUT"
fi

exit $GRADLE_EXIT_CODE