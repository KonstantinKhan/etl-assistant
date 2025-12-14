# Parser Module

This module is responsible for parsing string representations of parts and extracting their properties.

## Location

[parser-module](../../parser-module)

## Module Structure

The module consists of the following main components:

- **[`Parser`](../../parser-module/src/main/kotlin/com/khan366kos/parser/partlib/Parser.kt)**: The main class that orchestrates the parsing process. It takes a string as input and returns a
  `PartData` object.
- **[`PartData`](../../parser-module/src/main/kotlin/com/khan366kos/parser/partlib/PartData.kt)**: A data class that holds the extracted properties of a part, such as coating, material, dimensions, and
  strength.
- **Helper Objects**: A set of singleton objects that provide mapping and lookup functionality for various part
  attributes:
    - [`CoatingCondition`](../../parser-module/src/main/kotlin/com/khan366kos/parser/partlib/CoatingCondition.kt): Maps coating codes to their descriptions.
    - [`Material`](../../parser-module/src/main/kotlin/com/khan366kos/parser/partlib/Material.kt): Maps material abbreviations to full material specifications.
    - [`StrengthGrade`](../../parser-module/src/main/kotlin/com/khan366kos/parser/partlib/StrengthGrade.kt): Maps strength grade codes to their corresponding values.
    - [`ThreadPitch`](../../parser-module/src/main/kotlin/com/khan366kos/parser/partlib/ThreadPitch.kt): Provides the standard thread pitch for a given thread diameter.
    - [`ThreadWrenchSize`](../../parser-module/src/main/kotlin/com/khan366kos/parser/partlib/ThreadWrenchSize.kt): Provides the standard wrench size for a given thread diameter.

## Parsing Logic

The `Parser` class uses a series of regular expressions and lookup tables to extract the following information from an
input string:

1. **Coating and Thickness**: Identifies the coating code and its thickness from the end of the string, using the
   `CoatingCondition` object for validation.
2. **Material**: Extracts the material specification.
3. **Strength Grade**: Parses the strength grade of the material.
4. **Length**: Extracts the length of the part.
5. **Thread Diameter**: Finds the main thread diameter (e.g., M8, M10).
6. **Wrench Size**: Determines the wrench size, either from an explicit notation or by looking it up based on the thread
   diameter in `ThreadWrenchSize`.
7. **Thread Pitch**: Extracts the thread pitch if specified, otherwise determines a default pitch from the thread
   diameter using `ThreadPitch`.

The final parsed data is encapsulated in a `PartData` object, which also provides a formatted string representation of
the parsed attributes.
