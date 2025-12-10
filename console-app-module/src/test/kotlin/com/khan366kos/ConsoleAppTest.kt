package com.khan366kos

import io.kotest.core.spec.style.ShouldSpec
import io.kotest.matchers.shouldBe
import io.kotest.matchers.string.shouldContain
import kotlin.text.get

class ConsoleAppTest : ShouldSpec({
    should("contain correct data in PostmanEchoGetResponse") {
        val response = PostmanEchoGetResponse(
            args = mapOf("foo1" to "bar1"),
            headers = mapOf("content-type" to "application/json"),
            url = "https://postman-echo.com/get?foo1=bar1"
        )
        
        response.args["foo1"] shouldBe "bar1"
        response.headers["content-type"] shouldBe "application/json"
        response.url shouldContain "postman-echo.com"
    }
})