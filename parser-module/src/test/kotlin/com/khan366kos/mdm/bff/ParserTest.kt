package com.khan366kos.mdm.bff

import com.khan366kos.parser.partlib.Parser
import io.kotest.core.spec.style.ShouldSpec
import io.kotest.matchers.shouldBe

class ParserTest : ShouldSpec({
    should("parse bolt with coating and strength grade") {
        val parser = Parser()
        val input = "Болт М8-6gx55.88.019 ГОСТ 7805-70"
        val expectedOutput = "[9;Цинковое, хроматированное;Без указания материала;55;8;13;1,25;8.8]"
        val result = parser.parsePartData(input).toFormattedString()

        result shouldBe expectedOutput
    }

    should("parse bolt with coating without standardized and material") {
        val parser = Parser()
        val input = "Болт М8-6gx30.23.20Х13.11 ГОСТ 7805-70"
        val expectedOutput = "[Не нормируется;Окисное из кислых растворов;Сталь 20Х13 ГОСТ 5632-2014;30;8;13;1,25;23]"
        val result = parser.parsePartData(input).toFormattedString()

        result shouldBe expectedOutput
    }

    should("parse bolt with with coating without standardized") {
        val parser = Parser()
        val input = "Болт М14-6gx50.11 ГОСТ 7805-70"
        val expectedOutput = "[Не нормируется;Окисное из кислых растворов;Без указания материала;50;14;22;2;-]"
        val result = parser.parsePartData(input).toFormattedString()

        result shouldBe expectedOutput
    }

    should("parse bolt with strength grade and material") {
        val parser = Parser()
        val input = "Болт М14-6gx50.23.20Х13 ГОСТ 7805-70"
        val expectedOutput = "[Нет;Без покрытия;Сталь 20Х13 ГОСТ 5632-2014;50;14;22;2;23]"
        val result = parser.parsePartData(input).toFormattedString()

        result shouldBe expectedOutput
    }

    should("parse bolt Болт М5-6gx12.24 ГОСТ 7805-70") {
        val parser = Parser()
        val input = "Болт М5-6gx12.24 ГОСТ 7805-70"
        val expectedOutput = "[Нет;Без покрытия;Без указания материала;12;5;8;0,8;24]"
        val result = parser.parsePartData(input).toFormattedString()

        result shouldBe expectedOutput
    }

    should("parse bolt Болт М10x1,25-6gx30.36.0812 (S16) ГОСТ 7805-70") {
        val parser = Parser()
        val input = "Болт М10x1,25-6gx30.36.0812 (S16) ГОСТ 7805-70"
        val expectedOutput = "[12;Медное;Без указания материала;30;10;16;1,25;3.6]"
        val result = parser.parsePartData(input).toFormattedString()

        result shouldBe expectedOutput
    }
})