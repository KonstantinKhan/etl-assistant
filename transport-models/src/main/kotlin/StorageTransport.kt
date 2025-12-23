import kotlinx.serialization.Serializable

@Serializable
data class StorageTransport(
    val storageId: String,
    val displayName: String
)
