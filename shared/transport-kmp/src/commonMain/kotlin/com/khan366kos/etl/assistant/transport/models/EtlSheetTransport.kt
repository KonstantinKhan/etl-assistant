package com.khan366kos.etl.assistant.transport.models

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class EtlSheetTransport(
    @SerialName("title")
    val title: String = "",

    @SerialName("headers")
    val headers: List<String> = emptyList(),

    @SerialName("entriesSize")
    val entriesSize: Int = 0
)