package com.khan366kos.parser.partlib

object ThreadPitch {
    private val pitchMap = mapOf(
        "1.6" to "0,35",
        "2" to "0,4",
        "2.5" to "0,45",
        "3" to "0,5",
        "3.5" to "0,6",
        "4" to "0,7",
        "5" to "0,8",
        "6" to "1",
        "8" to "1,25",
        "10" to "1,5",
        "12" to "1,75",
        "14" to "2",
        "16" to "2",
        "18" to "2,5",
        "20" to "2,5",
        "22" to "2,5",
        "24" to "3",
        "27" to "3",
        "30" to "3,5",
        "36" to "4",
        "42" to "4,5",
        "48" to "5"
    )

    fun getPitchByDiameter(diameter: String): String {
        return pitchMap[diameter] ?: "Неизвестное значение"
    }
}


