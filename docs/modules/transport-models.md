# Transport Models Module

## Location

### Module

[transport-models](../../transport-models)

### Models

[models](../../transport-models/src/main/kotlin/com/khan366kos/etl/assistant/transport/models)

## Tech stack

- kotlin data classes
- kotlin serialization json

## Rules and conventions

- Do not use `I` at the beginning of the data class name
- Add `Transport` at the end of the data class name
- All fields of the class must have @SerialName written, use camelcase
- For `typeId` with `IdentifiableObjectType` always use non-nullable `Int` type
- For `applicability` with `Applicability` always use non-nullable `Int` type
- All nullable class fields must have a default value of `null`

## Exapmles

```kotlin
@Serializable
data class RoleTransport(
    @SerialName("description")
    val description: String? = null,

    @SerialName("code")
    val code: String? = null,

    @SerialName("externalId")
    val externalId: String? = null,

    @SerialName("writeAccess")
    val writeAccess: Boolean = false,

    @SerialName("id")
    val id: String? = null,

    @SerialName("name")
    val name: String? = null,

    @SerialName("isSystemObject")
    val isSystemObject: Boolean = false,

    @SerialName("objectId")
    val objectId: Int = 0,

    @SerialName("typeId")
    val typeId: Int
)
```