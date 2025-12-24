package com.khan366kos.parser.partlib

import com.khan366kos.etlassistant.logging.logger

class Parser {
    private val parserLogger = logger("Parser")
    private val nonStandardizedCoatingCodes = setOf("05", "06", "11", "12")

    suspend fun parsePartData(input: String): PartData {
        return parserLogger.doWithLogging("parsePartData()") {
            val coatingData = extractCoatingData(input)
            val material = extractMaterial(input)
            val strengthGrade = extractStrengthGrade(input)
            val length = extractLength(input)
            val threadDiameter = extractThreadDiameter(input)
            val wrenchSize = extractWrenchSize(input, threadDiameter)
            val threadPitch = extractThreadPitch(input, threadDiameter)

            val partData = PartData(
                coatingThickness = coatingData.thickness,
                coating = coatingData.coating,
                material = material,
                length = length,
                threadDiameter = threadDiameter,
                wrenchSize = wrenchSize,
                threadPitch = threadPitch,
                strengthGrade = strengthGrade
            )

            println("$input -> ${partData.toFormattedString()}")
            partData
        }
    }

    private fun extractCoatingData(input: String): CoatingData {
        val gostIndex = input.indexOf(" ГОСТ")
        if (gostIndex == -1) return CoatingData.empty()

        val lastDotIndex = input.lastIndexOf('.', gostIndex)
        if (lastDotIndex == -1) return CoatingData.empty()

        val coatingCodeCandidate = input.substring(lastDotIndex + 1, gostIndex)
            .trim()
            .split(" ")[0]

        if (coatingCodeCandidate.length !in 2..4 || !coatingCodeCandidate.all { it.isDigit() }) {
            return CoatingData.empty()
        }

        val coatingCode = coatingCodeCandidate.take(2)
        val coating = CoatingCondition.getCoatingByCode(coatingCode)

        val thickness = when {
            !CoatingCondition.existCoating(coatingCode) -> "Нет"
            coatingCode in nonStandardizedCoatingCodes -> "Не нормируется"
            coatingCodeCandidate.length > 3 -> coatingCodeCandidate.takeLast(2)
            coatingCodeCandidate.length == 3 -> coatingCodeCandidate.last().toString()
            else -> "Нет"
        }

        return CoatingData(coating, thickness)
    }

    private fun extractMaterial(input: String): String {
        val pattern = Regex("""\.([a-zA-Z0-9а-яА-Я]*[a-zA-Zа-яА-Я][a-zA-Z0-9а-яА-Я]*)(?=\.| |\n|$)""")
        val candidate = pattern.find(input)?.groupValues?.getOrNull(1) ?: ""
        return Material.getMaterialByCode(candidate)
    }

    private fun extractStrengthGrade(input: String): String {
        val pattern = Regex("""\.(\d+)(?=\.| |\s|\n|$)""")
        val candidate = pattern.find(input)?.groupValues?.getOrNull(1) ?: ""
        return StrengthGrade.getStrengthGradeCode(candidate)
    }

    private fun extractLength(input: String): String {
        val pattern = Regex("""-6g[хx]([^.]+)\.""")
        return pattern.find(input)?.groupValues?.getOrNull(1) ?: ""
    }

    private fun extractThreadDiameter(input: String): String {
        val pattern = Regex("""[MmМм]\s*([0-9]+(?:[.,][0-9]+)?)""")
        val candidate = pattern.find(input)?.groupValues?.getOrNull(1) ?: ""
        return candidate.trim()
    }

    private fun extractWrenchSize(input: String, threadDiameter: String): String {
        val pattern = Regex("""\(([^)]*?)\)\s*ГОСТ""")
        val parenthesesCandidate = pattern.find(input)
            ?.groupValues
            ?.getOrNull(1)
            ?.replace(Regex("\\p{L}"), "")
            ?.trim()

        return parenthesesCandidate
            ?.takeIf { it.isNotEmpty() }
            ?: ThreadWrenchSize.getWrenchSizeByDiameter(threadDiameter.replace(',', '.'))
    }

    private fun extractThreadPitch(input: String, threadDiameter: String): String {
        val pattern = Regex("""x(\d+(?:,\d+)?)-""")
        val candidate = pattern.find(input)?.groupValues?.getOrNull(1) ?: ""
        return candidate.ifEmpty {
            ThreadPitch.getPitchByDiameter(threadDiameter)
        }
    }

    private data class CoatingData(
        val coating: String,
        val thickness: String
    ) {
        companion object {
            fun empty() = CoatingData("Без покрытия", "Нет")
        }
    }
}