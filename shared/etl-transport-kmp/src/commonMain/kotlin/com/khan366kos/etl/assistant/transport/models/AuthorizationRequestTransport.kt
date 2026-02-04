package com.khan366kos.etl.assistant.transport.models

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class AuthorizationRequestTransport(
    @SerialName("username")
    val username: String,

    @SerialName("password")
    val password: String,

    @SerialName("storageId")
    val storageId: String
)