package com.khan366kos.mdm.bff.partlib

object Material {
    private val materialMap = mapOf(
        "ЛС59-1" to "ЛС59-1 ГОСТ 15527-2004",
        "20Х13" to "Сталь 20Х13 ГОСТ 5632-2014"
    )

    fun getMaterialByCode(code: String): String {
        return materialMap[code] ?: code
    }
}

