package com.khan366kos.mdm.bff

import com.khan366kos.etlassistant.logging.LogbackLogger
import mdm.bff.partlib.CoatingCondition
import mdm.bff.partlib.Material
import mdm.bff.partlib.PartData
import com.khan366kos.mdm.bff.partlib.ThreadPitch
import com.khan366kos.mdm.bff.partlib.ThreadWrenchSize

class Parser {

    companion object {
        private val STRENGTH_GRADE_MAP = mapOf(
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
    }

    private val parserLogger = LogbackLogger("Parser")

    suspend fun parsePartData(input: String): PartData {
        return parserLogger.doWithLogging("parse string") {
            val coatingCodeFull = extractCoatingCode(input)
            val coatingCode = if (coatingCodeFull.length > 2) coatingCodeFull.take(2) else coatingCodeFull
            val coating = CoatingCondition.getCoatingByCode(coatingCode)

            val coatingThickness = when (coatingCode) {
                "05", "06", "11", "12" -> "Не нормируется"
                else -> coatingCodeFull.lastOrNull()?.toString() ?: "Нет"
            }

            val material = extractMaterial(input, coatingCodeFull.isNotEmpty())
            val strengthGrade = extractStrengthGrade(input)
            val length = extractLength(input)
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

    private fun extractStrengthGrade(input: String): String {
        val defaultStrengthGrade = "-"

        var markerIndex = input.indexOf("-6gx")
        var marker = "-6gx"

        // If "-6gx" is not found, try "-6g"
        if (markerIndex == -1) {
            markerIndex = input.indexOf("-6g")
            marker = "-6g"
        }

        if (markerIndex != -1) {
            // Handle format with -6g marker
            var startIndex = markerIndex + marker.length
            if (startIndex >= input.length) return defaultStrengthGrade

            // Skip the 'x' or 'х' character if it follows the marker immediately
            if (startIndex < input.length && (input[startIndex] == 'x' || input[startIndex] == 'X' ||
                        input[startIndex] == 'х' || input[startIndex] == 'Х')
            ) {
                startIndex++
                if (startIndex >= input.length) return defaultStrengthGrade
            }

            // Find the first dot after the length (this should lead to the strength grade)
            val firstDotIndex = input.indexOf('.', startIndex)
            if (firstDotIndex == -1) return defaultStrengthGrade

            // Find the second dot after the first dot (this separates strength grade from material)
            val secondDotIndex = input.indexOf('.', firstDotIndex + 1)
            val gostIndex = input.indexOf(" ГОСТ 7805-70", firstDotIndex)

            // Determine the end index for extracting the strength grade
            val endIndex = when {
                secondDotIndex != -1 && (gostIndex == -1 || secondDotIndex < gostIndex) -> secondDotIndex
                gostIndex != -1 -> gostIndex
                else -> input.length
            }

            // Extract the potential strength grade (the part right after the first dot)
            val potentialGrade = input.substring(firstDotIndex + 1, endIndex).trim()

            // Check if the potential grade exists in the dictionary
            if (STRENGTH_GRADE_MAP.containsKey(potentialGrade)) {
                // Transform the value according to the rules
                return STRENGTH_GRADE_MAP[potentialGrade] ?: defaultStrengthGrade
            }

            return defaultStrengthGrade
        } else {
            // Handle format without -6g marker: look for first dot after 'х' or 'x' after thread diameter
            // Find the end of thread diameter (after M<number>)
            val mIndex = input.indexOf('M').let {
                if (it != -1) it else input.indexOf('m').let { idx ->
                    if (idx != -1) idx else input.indexOf('М').let { idx2 ->
                        if (idx2 != -1) idx2 else input.indexOf('м')
                    }
                }
            }

            if (mIndex == -1) return defaultStrengthGrade

            // Find the end of the thread diameter number (find where the number ends)
            var afterDiameterIndex = mIndex + 1
            while (afterDiameterIndex < input.length &&
                (input[afterDiameterIndex].isDigit() || input[afterDiameterIndex] == '.' || input[afterDiameterIndex] == ',')
            ) {
                afterDiameterIndex++
            }

            if (afterDiameterIndex >= input.length) return defaultStrengthGrade

            // Look for 'х' or 'x' after the thread diameter number
            if (input[afterDiameterIndex] == 'х' || input[afterDiameterIndex] == 'х' ||
                input[afterDiameterIndex] == 'x' || input[afterDiameterIndex] == 'X'
            ) {

                var afterXIndex = afterDiameterIndex + 1
                if (afterXIndex >= input.length) return defaultStrengthGrade

                // Find the first dot after the length number
                val firstDotIndex = input.indexOf('.', afterXIndex)
                if (firstDotIndex == -1) return defaultStrengthGrade

                // Find the second dot after the first dot (this separates strength grade from material)
                val secondDotIndex = input.indexOf('.', firstDotIndex + 1)
                val gostIndex = input.indexOf(" ГОСТ 7805-70", firstDotIndex)

                // Determine the end index for extracting the strength grade
                val endIndex = when {
                    secondDotIndex != -1 && (gostIndex == -1 || secondDotIndex < gostIndex) -> secondDotIndex
                    gostIndex != -1 -> gostIndex
                    else -> input.length
                }

                // Extract the potential strength grade (the part right after the first dot)
                val potentialGrade = input.substring(firstDotIndex + 1, endIndex).trim()

                // Check if the potential grade exists in the dictionary
                if (STRENGTH_GRADE_MAP.containsKey(potentialGrade)) {
                    // Transform the value according to the rules
                    return STRENGTH_GRADE_MAP[potentialGrade] ?: defaultStrengthGrade
                }
            }

            return defaultStrengthGrade
        }
    }

    private fun extractLength(input: String): String {
        val defaultLength = "-"
        var markerIndex = input.indexOf("-6gx")
        var marker = "-6gx"

        // If "-6gx" is not found, try "-6g"
        if (markerIndex == -1) {
            markerIndex = input.indexOf("-6g")
            marker = "-6g"
        }

        if (markerIndex != -1) {
            // Handle format with -6g marker
            var startIndex = markerIndex + marker.length
            if (startIndex >= input.length) return defaultLength

            // Skip the 'x' or 'х' character if it follows the marker immediately
            if (startIndex < input.length && (input[startIndex] == 'x' || input[startIndex] == 'X' ||
                        input[startIndex] == 'х' || input[startIndex] == 'Х')
            ) {
                startIndex++
                if (startIndex >= input.length) return defaultLength
            }

            // Find the position of the first dot after the start index
            val dotIndex = input.indexOf('.', startIndex)
            val gostIndex = input.indexOf(" ГОСТ 7805-70", startIndex)

            // Determine the end index - either the first dot or the start of ГОСТ
            val endIndex = when {
                dotIndex != -1 && (gostIndex == -1 || dotIndex < gostIndex) -> dotIndex
                gostIndex != -1 -> gostIndex
                else -> input.length  // Extract until end of string if no markers are found
            }

            val candidate = input.substring(startIndex, endIndex).trim()
            // Оставляем только варианты, где есть цифры
            return if (candidate.isEmpty() || candidate.none { it.isDigit() }) defaultLength else candidate
        } else {
            // Handle format without -6g marker: look for 'х' or 'x' after thread diameter
            // Find the end of thread diameter (after M<number>)
            val threadDiameter = extractThreadDiameter(input)
            val mIndex = input.indexOf('M').let {
                if (it != -1) it else input.indexOf('m').let { idx ->
                    if (idx != -1) idx else input.indexOf('М').let { idx2 ->
                        if (idx2 != -1) idx2 else input.indexOf('м')
                    }
                }
            }

            if (mIndex == -1) return defaultLength

            // Find the end of the thread diameter number (find where the number ends)
            var afterDiameterIndex = mIndex + 1
            while (afterDiameterIndex < input.length &&
                (input[afterDiameterIndex].isDigit() || input[afterDiameterIndex] == '.' || input[afterDiameterIndex] == ',')
            ) {
                afterDiameterIndex++
            }

            if (afterDiameterIndex >= input.length) return defaultLength

            // Look for 'х' or 'x' after the thread diameter number
            if (input[afterDiameterIndex] == 'х' || input[afterDiameterIndex] == 'х' ||
                input[afterDiameterIndex] == 'x' || input[afterDiameterIndex] == 'X'
            ) {

                var startIndex = afterDiameterIndex + 1
                if (startIndex >= input.length) return defaultLength

                // Find the next dot or " ГОСТ 7805-70"
                val dotIndex = input.indexOf('.', startIndex)
                val gostIndex = input.indexOf(" ГОСТ 7805-70", startIndex)

                val endIndex = when {
                    dotIndex != -1 && (gostIndex == -1 || dotIndex < gostIndex) -> dotIndex
                    gostIndex != -1 -> gostIndex
                    else -> input.length
                }

                val candidate = input.substring(startIndex, endIndex).trim()
                return if (candidate.isEmpty() || candidate.none { it.isDigit() }) defaultLength else candidate
            }

            return defaultLength
        }
    }

    private fun extractMaterial(input: String, hasCoating: Boolean): String {
        val defaultMaterial = "Без указания материала"

        var markerIndex = input.indexOf("-6gx")
        var marker = "-6gx"

        // If "-6gx" is not found, try "-6g"
        if (markerIndex == -1) {
            markerIndex = input.indexOf("-6g")
            marker = "-6g"
        }

        if (markerIndex != -1) {
            // Handle format with -6g marker
            var startIndex = markerIndex + marker.length
            if (startIndex >= input.length) return defaultMaterial

            // Skip the 'x' or 'х' character if it follows the marker immediately
            if (startIndex < input.length && (input[startIndex] == 'x' || input[startIndex] == 'X' ||
                        input[startIndex] == 'х' || input[startIndex] == 'Х')
            ) {
                startIndex++
                if (startIndex >= input.length) return defaultMaterial
            }

            // Find the first dot (after length)
            val firstDotIndex = input.indexOf('.', startIndex)
            if (firstDotIndex == -1) return defaultMaterial

            // Find the " ГОСТ 7805-70" marker
            val gostIndex = input.indexOf(" ГОСТ 7805-70", firstDotIndex)
            if (gostIndex == -1) return defaultMaterial

            // Extract what comes after the first dot (this could be strength grade, coating, or other)
            val firstDotEndIndex = firstDotIndex + 1
            val secondDotIndex = input.indexOf('.', firstDotEndIndex)

            if (secondDotIndex == -1) {
                // Only one dot after -6g/-6gx marker - likely format is "length.coating", no material
                return defaultMaterial
            }

            // Get the value after the first dot to determine format
            val endIndexAfterFirst =
                if (secondDotIndex != -1 && secondDotIndex < gostIndex) secondDotIndex else gostIndex
            val potentialStrengthGrade = input.substring(firstDotEndIndex, endIndexAfterFirst).trim()

            // Check if the first value after dot is a valid strength grade
            if (STRENGTH_GRADE_MAP.containsKey(potentialStrengthGrade)) {
                // Format is likely "length.strengthGrade.material[.coating]" - extract material after second dot
                val thirdDotIndex = input.indexOf('.', secondDotIndex + 1)
                val materialEndIndex =
                    if (thirdDotIndex != -1 && thirdDotIndex < gostIndex) thirdDotIndex else gostIndex
                val candidate = input.substring(secondDotIndex + 1, materialEndIndex).trim()

                if (candidate.isEmpty()) {
                    return defaultMaterial
                } else {
                    // Check if the material contains letters (not just numbers)
                    if (candidate.none { it.isLetter() }) {
                        // If it's only numeric, return default material
                        return defaultMaterial
                    }

                    // Check in the material lookup table
                    val material = Material.getMaterialByCode(candidate)
                    return material
                }
            } else {
                // The first value after dot is not a strength grade, so format is likely "length.coating"
                // This means there's no material in this format
                return defaultMaterial
            }
        } else {
            // Handle format without -6g marker: find material after second dot after 'х' or 'x' after thread diameter
            // Find the end of thread diameter (after M<number>)
            val mIndex = input.indexOf('M').let {
                if (it != -1) it else input.indexOf('m').let { idx ->
                    if (idx != -1) idx else input.indexOf('М').let { idx2 ->
                        if (idx2 != -1) idx2 else input.indexOf('м')
                    }
                }
            }

            if (mIndex == -1) return defaultMaterial

            // Find the end of the thread diameter number (find where the number ends)
            var afterDiameterIndex = mIndex + 1
            while (afterDiameterIndex < input.length &&
                (input[afterDiameterIndex].isDigit() || input[afterDiameterIndex] == '.' || input[afterDiameterIndex] == ',')
            ) {
                afterDiameterIndex++
            }

            if (afterDiameterIndex >= input.length) return defaultMaterial

            // Look for 'х' or 'x' after the thread diameter number
            if (input[afterDiameterIndex] == 'х' || input[afterDiameterIndex] == 'х' ||
                input[afterDiameterIndex] == 'x' || input[afterDiameterIndex] == 'X'
            ) {

                var afterXIndex = afterDiameterIndex + 1
                if (afterXIndex >= input.length) return defaultMaterial

                // Find the first dot (after length)
                val firstDotIndex = input.indexOf('.', afterXIndex)
                if (firstDotIndex == -1) return defaultMaterial

                // Find the " ГОСТ 7805-70" marker
                val gostIndex = input.indexOf(" ГОСТ 7805-70", firstDotIndex)
                if (gostIndex == -1) return defaultMaterial

                // Extract what comes after the first dot (this could be strength grade, coating, or other)
                val firstDotEndIndex = firstDotIndex + 1
                val secondDotIndex = input.indexOf('.', firstDotEndIndex)

                if (secondDotIndex == -1) {
                    // Only one dot after -6g/-6gx marker - likely format is "length.coating", no material
                    return defaultMaterial
                }

                // Get the value after the first dot to determine format
                val endIndexAfterFirst =
                    if (secondDotIndex != -1 && secondDotIndex < gostIndex) secondDotIndex else gostIndex
                val potentialStrengthGrade = input.substring(firstDotEndIndex, endIndexAfterFirst).trim()

                // Check if the first value after dot is a valid strength grade
                if (STRENGTH_GRADE_MAP.containsKey(potentialStrengthGrade)) {
                    // Format is likely "length.strengthGrade.material[.coating]" - extract material after second dot
                    val thirdDotIndex = input.indexOf('.', secondDotIndex + 1)
                    val materialEndIndex =
                        if (thirdDotIndex != -1 && thirdDotIndex < gostIndex) thirdDotIndex else gostIndex
                    val candidate = input.substring(secondDotIndex + 1, materialEndIndex).trim()

                    if (candidate.isEmpty()) {
                        return defaultMaterial
                    } else {
                        // Check if the material contains letters (not just numbers)
                        if (candidate.none { it.isLetter() }) {
                            // If it's only numeric, return default material
                            return defaultMaterial
                        }

                        // Check in the material lookup table
                        val material = Material.getMaterialByCode(candidate)
                        return material
                    }
                } else {
                    // The first value after dot is not a strength grade, so format is likely "length.coating"
                    // This means there's no material in this format
                    return defaultMaterial
                }
            }

            return defaultMaterial
        }
    }

    /**
     * Определяет позицию точки перед группой точности (двухзначным числом после длины).
     * Возвращает индекс точки или -1, если группа точности не найдена.
     */
    private fun getStrengthGradeDotIndex(input: String): Int {
        val gostIndex = input.indexOf(" ГОСТ 7805-70")
        if (gostIndex == -1) return -1

        var markerIndex = input.indexOf("-6gx")
        var marker = "-6gx"

        // If "-6gx" is not found, try "-6g"
        if (markerIndex == -1) {
            markerIndex = input.indexOf("-6g")
            marker = "-6g"
        }

        if (markerIndex != -1) {
            // Handle format with -6g marker
            var startIndex = markerIndex + marker.length
            if (startIndex >= input.length) return -1

            // Skip the 'x' or 'х' character if it follows the marker immediately
            if (startIndex < input.length && (input[startIndex] == 'x' || input[startIndex] == 'X' ||
                        input[startIndex] == 'х' || input[startIndex] == 'Х')
            ) {
                startIndex++
                if (startIndex >= input.length) return -1
            }

            // Find the first dot after the length (this should lead to the strength grade)
            val firstDotIndex = input.indexOf('.', startIndex)
            if (firstDotIndex == -1 || firstDotIndex >= gostIndex) return -1

            // Find the second dot after the first dot (this separates strength grade from material)
            val secondDotIndex = input.indexOf('.', firstDotIndex + 1)
            val endIndex = when {
                secondDotIndex != -1 && (gostIndex == -1 || secondDotIndex < gostIndex) -> secondDotIndex
                else -> gostIndex
            }

            // Extract the potential strength grade (the part right after the first dot)
            val potentialGrade = input.substring(firstDotIndex + 1, endIndex).trim()

            // Check if the potential grade is a 2-digit number
            if (potentialGrade.length == 2 && potentialGrade.all { it.isDigit() }) {
                return firstDotIndex
            }

            return -1
        } else {
            // Handle format without -6g marker: find the first dot after 'х' or 'x' after thread diameter
            // Find the end of thread diameter (after M<number>)
            val mIndex = input.indexOf('M').let {
                if (it != -1) it else input.indexOf('m').let { idx ->
                    if (idx != -1) idx else input.indexOf('М').let { idx2 ->
                        if (idx2 != -1) idx2 else input.indexOf('м')
                    }
                }
            }

            if (mIndex == -1) return -1

            // Find the end of the thread diameter number (find where the number ends)
            var afterDiameterIndex = mIndex + 1
            while (afterDiameterIndex < input.length &&
                (input[afterDiameterIndex].isDigit() || input[afterDiameterIndex] == '.' || input[afterDiameterIndex] == ',')
            ) {
                afterDiameterIndex++
            }

            if (afterDiameterIndex >= input.length) return -1

            // Look for 'х' or 'x' after the thread diameter number
            if (input[afterDiameterIndex] == 'х' || input[afterDiameterIndex] == 'х' ||
                input[afterDiameterIndex] == 'x' || input[afterDiameterIndex] == 'X'
            ) {

                var afterXIndex = afterDiameterIndex + 1
                if (afterXIndex >= input.length) return -1

                // Find the first dot (after length)
                val firstDotIndex = input.indexOf('.', afterXIndex)
                if (firstDotIndex == -1 || firstDotIndex >= gostIndex) return -1

                // Find the second dot after the first dot (this separates strength grade from material)
                val secondDotIndex = input.indexOf('.', firstDotIndex + 1)
                val endIndex = when {
                    secondDotIndex != -1 && (gostIndex == -1 || secondDotIndex < gostIndex) -> secondDotIndex
                    else -> gostIndex
                }

                // Extract the potential strength grade (the part right after the first dot)
                val potentialGrade = input.substring(firstDotIndex + 1, endIndex).trim()

                // Check if the potential grade is a 2-digit number
                if (potentialGrade.length == 2 && potentialGrade.all { it.isDigit() }) {
                    return firstDotIndex
                }
            }

            return -1
        }
    }

    private fun extractCoatingCode(input: String): String {
        // Находим последнюю точку до " ГОСТ"
        val gostIndex = input.indexOf(" ГОСТ")
        if (gostIndex == -1) return ""

        // Ищем текст между последней точкой и " ГОСТ"
        var lastIndex = input.lastIndexOf('.', gostIndex)
        if (lastIndex == -1) return ""

        // Пропускаем саму точку и берем текст до " ГОСТ"
        lastIndex += 1
        val coatingText = input.substring(lastIndex, gostIndex).trim()

        // Проверяем, что код покрытия двузначный или трехзначный
        if (coatingText.length !in 2..3 || !coatingText.all { it.isDigit() }) {
            return ""
        }

        // Check if the potential coating code might be a strength grade in the context
        // If the text matches a strength grade and there's no additional text after it before ГОСТ
        // that would indicate a material (which typically follows strength grade),
        // then it's more likely to be a strength grade rather than a coating code
        if (STRENGTH_GRADE_MAP.containsKey(coatingText)) {
            // Count total dots between the -6gx/-6g marker and the ГОСТ
            var markerIndex = input.indexOf("-6gx")
            var marker = "-6gx"

            // If "-6gx" is not found, try "-6g"
            if (markerIndex == -1) {
                markerIndex = input.indexOf("-6g")
                marker = "-6g"
            }

            if (markerIndex != -1) {
                val afterMarkerStart = markerIndex + marker.length
                if (afterMarkerStart < gostIndex) {
                    val contentBetweenMarkerAndGost = input.substring(afterMarkerStart, gostIndex)
                    val totalDotsCount = contentBetweenMarkerAndGost.count { it == '.' }

                    // If total dots count is 1 (format like "length.strength_grade"),
                    // and the potential code is a valid strength grade, it's likely a strength grade
                    // For example: "Болт М5-6gx12.24 ГОСТ 7805-70" has format length.strength_grade
                    if (totalDotsCount == 1) {
                        // Format is likely "length.potential_strength_grade", treat as strength grade
                        return ""
                    }
                }
            }
        }

        // Возвращаем полный код покрытия
        return coatingText
    }

    private fun extractThreadDiameter(input: String): String {
        // Ищем первое вхождение M/М + число (возможна запись с запятой/точкой)
        val pattern = """[MmМм]\s*([0-9]+(?:[.,][0-9]+)?)""".toRegex()
        val match = pattern.find(input) ?: return "1,6"
        val diameter = match.groupValues[1].trim()
        return if (diameter.isNotEmpty()) diameter else "1,6"
    }


    private fun extractWrenchSize(input: String, threadDiameter: String): String {
        // Пытаемся найти значение в круглых скобках непосредственно перед "ГОСТ 7805-70"
        val parenthesesPattern = "\\(([^)]*?)\\)\\s*ГОСТ 7805-70".toRegex()
        val match = parenthesesPattern.find(input)

        if (match != null) {
            val insideParentheses = match.groupValues[1]
            // Удаляем все буквенные символы (латиница/кириллица), оставляя цифры и разделители
            val cleaned = insideParentheses.replace(Regex("\\p{L}"), "").trim()
            if (cleaned.isNotEmpty()) {
                return cleaned
            }
        }

        // Если значения в скобках нет или оно пустое, используем справочник по диаметру резьбы
        val normalizedDiameter = threadDiameter.replace(',', '.')
        return ThreadWrenchSize.getWrenchSizeByDiameter(normalizedDiameter)
    }

    private fun extractThreadPitch(input: String, threadDiameter: String): String {
        // Находим позицию диаметра резьбы (M/М + число)
        val diameterPattern = """[MmМм]\s*([0-9]+(?:[.,][0-9]+)?)""".toRegex()
        val diameterMatch = diameterPattern.find(input) ?: return getDefaultPitch(threadDiameter)

        val diameterEndIndex = diameterMatch.range.last + 1
        if (diameterEndIndex >= input.length) return getDefaultPitch(threadDiameter)

        // Ищем маркер класса точности резьбы "-6g" или "-6gx" после диаметра
        val precisionMarker = "-6g"
        val precisionMarkerIndex = input.indexOf(precisionMarker, diameterEndIndex)

        // Определяем границу поиска: до маркера "-6g" или до конца строки, если маркер не найден
        val searchEndIndex = if (precisionMarkerIndex != -1) {
            precisionMarkerIndex
        } else {
            input.length
        }

        // Ищем символ 'x' после диаметра, но только до маркера "-6g"
        val xIndex = input.indexOf('x', diameterEndIndex)
        if (xIndex == -1 || xIndex >= searchEndIndex) return getDefaultPitch(threadDiameter)

        // Ищем числовое значение после 'x' (может содержать точку или запятую)
        // Останавливаемся на точке (если это не часть числа), пробеле, маркере "-6g" или конце строки
        val startIndex = xIndex + 1
        if (startIndex >= input.length || startIndex >= searchEndIndex) return getDefaultPitch(threadDiameter)

        // Извлекаем числовое значение: цифры, точка/запятая, цифры
        val numberPattern = """([0-9]+(?:[.,][0-9]+)?)""".toRegex()
        val numberMatch = numberPattern.find(input, startIndex)

        if (numberMatch != null) {
            val numberEndIndex = numberMatch.range.last + 1
            // Проверяем, что число находится до маркера "-6g"
            if (numberEndIndex <= searchEndIndex) {
                val candidate = numberMatch.value
                // Проверяем, что после числа идет точка (разделитель), пробел или маркер "-6g", а не продолжение материала
                if (numberEndIndex < input.length) {
                    val nextChar = input[numberEndIndex]
                    // Если после числа идет точка и затем буква, это часть материала, не шаг резьбы
                    if (nextChar == '.' && numberEndIndex + 1 < input.length && input[numberEndIndex + 1].isLetter()) {
                        return getDefaultPitch(threadDiameter)
                    }
                }
                // Нормализуем результат: заменяем точку на запятую для консистентности формата
                val normalized = candidate.replace('.', ',')
                return normalizePitchValue(normalized)
            }
        }

        return getDefaultPitch(threadDiameter)
    }

    private fun getDefaultPitch(threadDiameter: String): String {
        val normalizedDiameter = threadDiameter.replace(',', '.')
        val pitch = ThreadPitch.getPitchByDiameter(normalizedDiameter)
        // Нормализуем результат: заменяем точку на запятую для соответствия формату диаметра
        val normalizedPitch = pitch.replace('.', ',')
        return normalizePitchValue(normalizedPitch)
    }

    /**
     * Нормализует значение шага резьбы, удаляя завершающие нули после запятой.
     * - Если есть ненулевые цифры после запятой, удаляет только завершающие нули (например, "0,80" -> "0,8", "1,50" -> "1,5")
     * - Если после запятой только нули, удаляет запятую и нули полностью (например, "3,00" -> "3")
     */
    private fun normalizePitchValue(pitch: String): String {
        // Сначала удаляем завершающие нули после запятой, но сохраняем запятую если есть ненулевые цифры
        // Например: "0,80" -> "0,8", "1,50" -> "1,5"
        var normalized = pitch.replace(Regex("(,\\d*[1-9])0+$"), "$1")
        // Затем удаляем запятую и нули, если после запятой только нули
        // Например: "3,00" -> "3", "1,0" -> "1"
        normalized = normalized.replace(Regex(",0+$"), "")
        return normalized
    }
}

