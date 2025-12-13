package com.khan366kos.parser.partlib

object Material {
    private val materialMap = mapOf(
        "ЛС59-1" to "ЛС59-1 ГОСТ 15527-2004",
        "20Х13" to "Сталь 20Х13 ГОСТ 5632-2014",
        "14Х17Н2" to "Сталь 14Х17Н2 ГОСТ 5632-2014",
        "10Х11Н23Т3МР" to "Сталь 10Х11Н23Т3МР ГОСТ 5632-2014",
        "12Х18Н10Т" to "Сталь 12Х18Н10Т ГОСТ 5632-2014",
        "40Х" to "Сталь 40Х ГОСТ 4543-2016"
    )

    fun getMaterialByCode(code: String): String = materialMap[code] ?: "Без указания материала"
}

