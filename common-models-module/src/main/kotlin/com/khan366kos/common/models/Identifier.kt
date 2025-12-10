package com.khan366kos.common.models

import com.khan366kos.common.models.simple.ObjectId
import com.khan366kos.common.models.simple.TypeId
import kotlinx.serialization.Serializable

@Serializable
data class Identifier(
    val objectId: ObjectId,
    val typeId: TypeId
)