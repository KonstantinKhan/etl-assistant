package com.khan366kos.common.models.simple

import kotlinx.serialization.Serializable

@JvmInline
@Serializable
value class ElementName(val name: String) {
    fun asString() = name
}