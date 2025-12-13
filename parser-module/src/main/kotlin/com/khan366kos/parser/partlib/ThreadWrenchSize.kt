package com.khan366kos.parser.partlib

object ThreadWrenchSize {
    private val wrenchSizeMap = mapOf(
        "1.6" to "3.2",
        "2" to "4",
        "2.5" to "5",
        "3" to "5.5",
        "3.5" to "6",
        "4" to "7",
        "5" to "8",
        "6" to "10",
        "8" to "13",
        "10" to "17",
        "12" to "19",
        "14" to "22",
        "16" to "24",
        "18" to "27",
        "20" to "30",
        "22" to "32",
        "24" to "36",
        "27" to "41",
        "30" to "46",
        "36" to "55",
        "42" to "65",
        "48" to "75"
    )

    fun getWrenchSizeByDiameter(diameter: String): String {
        return wrenchSizeMap[diameter] ?: "Неизвестное значение"
    }
}


