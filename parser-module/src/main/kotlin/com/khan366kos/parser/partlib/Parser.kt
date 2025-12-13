package com.khan366kos.parser.partlib

import com.khan366kos.etlassistant.logging.logger

class Parser {
    private val parserLogger = logger("Parser")

    suspend fun parsePartData(input: String): PartData {
        return parserLogger.doWithLogging("parsePartData()") {
            val coatingCodeFullCandidate = coatingCodeFullCandidate(input)
            println("coatingCodeFullCandidate: $coatingCodeFullCandidate")
            val coatingCodeCandidate =
                if (coatingCodeFullCandidate.length > 2) coatingCodeFullCandidate.take(2) else coatingCodeFullCandidate
            val coating = CoatingCondition.getCoatingByCode(coatingCodeCandidate)

            val coatingThickness =
                if (CoatingCondition.existCoating(coatingCodeCandidate)) {
                    when (coatingCodeCandidate) {
                        "05", "06", "11", "12" -> "Не нормируется"
                        else -> if (coatingCodeFullCandidate.length > 3) coatingCodeFullCandidate.takeLast(2)
                        else coatingCodeFullCandidate.last().toString()
                    }
                } else "Нет"

            val materialCandidatePattern =
                Regex("""\.([a-zA-Z0-9а-яА-Я]*[a-zA-Zа-яА-Я][a-zA-Z0-9а-яА-Я]*)(?=\.| |\n|$)""")
            val materialMatch = materialCandidatePattern.find(input)
            val materialCandidate = materialMatch?.groupValues?.getOrNull(1) ?: ""

            val strengthGrageCandidatePattern = Regex("""\.(\d+)(?=\.| |\s|\n|$)""")
            val strengthGradeMatch = strengthGrageCandidatePattern.find(input)
            val strengthGradeCandidate = strengthGradeMatch?.groupValues?.getOrNull(1) ?: ""

            val lengthPattern = Regex("""-6gx([^.]+)\.""")
            val lengthMatch = lengthPattern.find(input)

            val material = extractMaterial(materialCandidate)
            val strengthGrade = extractStrengthGrade(strengthGradeCandidate)
            val length = lengthMatch?.groupValues?.getOrNull(1) ?: ""
            val threadDiameter = extractThreadDiameter(input)
            val wrenchSize = extractWrenchSize(input, threadDiameter)
            val threadPitch = extractThreadPitch(input, threadDiameter)

            val partData = PartData(
                coatingThickness = coatingThickness,
                coating = coating,
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

    private fun extractStrengthGrade(input: String): String = StrengthGrade.getStrengthGradeCode(input)

    private fun extractMaterial(input: String): String = Material.getMaterialByCode(input)

    private fun coatingCodeFullCandidate(input: String): String {
        val gostIndex = input.indexOf(" ГОСТ")
        if (gostIndex == -1) return ""

        var lastIndex = input.lastIndexOf('.', gostIndex)
        if (lastIndex == -1) return ""

        lastIndex += 1
        val coatingText = input.substring(lastIndex, gostIndex).trim().split(" ")[0]

        if (coatingText.length !in 2..4 || !coatingText.all { it.isDigit() }) {
            return ""
        }

        return coatingText
    }

    private fun extractThreadDiameter(input: String): String {
        val pattern = """[MmМм]\s*([0-9]+(?:[.,][0-9]+)?)""".toRegex()
        val match = pattern.find(input) ?: return ""
        val diameter = match.groupValues[1].trim()
        return diameter
    }

    private fun extractWrenchSize(input: String, threadDiameter: String): String {
        val parenthesesPattern = "\\(([^)]*?)\\)\\s*ГОСТ".toRegex()
        val match = parenthesesPattern.find(input)

        if (match != null) {
            val insideParentheses = match.groupValues[1]
            val cleaned = insideParentheses.replace(Regex("\\p{L}"), "").trim()
            if (cleaned.isNotEmpty()) {
                return cleaned
            }
        }

        val normalizedDiameter = threadDiameter.replace(',', '.')
        return ThreadWrenchSize.getWrenchSizeByDiameter(normalizedDiameter)
    }

    private fun extractThreadPitch(input: String, threadDiameter: String): String {
        val threadPitchRegex = Regex("x(\\d+(?:,\\d+)?)-")
        val threadPitchMatch = threadPitchRegex.find(input)
        val threadePitch = threadPitchMatch?.groupValues?.getOrNull(1) ?: ""

        return threadePitch.ifEmpty { getDefaultPitch(threadDiameter) }
    }

    private fun getDefaultPitch(threadDiameter: String): String {
        val pitch = ThreadPitch.getPitchByDiameter(threadDiameter)
        return pitch
    }
}