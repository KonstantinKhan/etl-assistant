package com.khan366kos.parser.partlib

object StrengthGrade {
    private val strengthGradeMap = mapOf(
        "36" to "3.6",
        "46" to "4.6",
        "48" to "4.8",
        "56" to "5.6",
        "58" to "5.8",
        "66" to "6.6",
        "68" to "6.8",
        "88" to "8.8",
        "98" to "9.8",
        "109" to "10.9",
        "129" to "12.9",
        "21" to "21",
        "22" to "22",
        "23" to "23",
        "24" to "24",
        "25" to "25",
        "26" to "26",
        "31" to "31",
        "32" to "32",
        "33" to "33",
        "34" to "34",
        "35" to "35"
    )

    fun getStrengthGradeCode(code: String): String = strengthGradeMap[code] ?: "-"
}