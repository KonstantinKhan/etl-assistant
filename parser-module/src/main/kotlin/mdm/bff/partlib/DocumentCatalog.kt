package com.khan366kos.mdm.bff.partlib

import com.khan366kos.mdm.bff.models.IdentifiableObject
import com.khan366kos.mdm.bff.models.NamedObject
import kotlinx.serialization.Serializable

@Serializable
data class DocumentCatalog(
    val id: String,
    val objectId: Int,
    val typeId: Int,
    val name: String,
    val iconCode: Int,
    val iconColor: Int,
    val path: List<NamedObject>,
    val count: Int,
    val reference: IdentifiableObject,
    val isEntry: Boolean,
    val classId: String
)
