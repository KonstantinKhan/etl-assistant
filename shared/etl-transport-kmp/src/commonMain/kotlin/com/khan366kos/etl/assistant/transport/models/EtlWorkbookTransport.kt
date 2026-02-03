package com.khan366kos.etl.assistant.transport.models

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class EtlWorkbookTransport(
    @SerialName("sheets")
    val sheets: List<EtlSheetTransport> = emptyList()
)