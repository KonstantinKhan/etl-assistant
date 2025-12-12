package com.khan366kos.etlassistant.logging

import io.kotest.core.spec.style.ShouldSpec
import io.kotest.matchers.shouldBe
import io.kotest.matchers.shouldNotBe

// Simple class to test logger creation by class reference
internal class TestLogger

class LoggerTest : ShouldSpec({
    should("create a logger instance") {
        val logger = LoggerFactory.createLogger("TestLogger")

        logger shouldNotBe null
    }

    should("allow creating logger by class reference") {
        val logger = LoggerFactory.createLogger<TestLogger>()

        logger shouldNotBe null
    }

    should("properly log messages at different levels") {
        val logger = LoggerFactory.createLogger("TestLogger")

        // Since we can't easily capture log output in this simple test,
        // we'll just verify that calling the methods doesn't throw exceptions
        logger.debug("Debug message")
        logger.info("Info message")
        logger.warn("Warning message")
        logger.error("Error message")

        // Also test with throwables
        val exception = RuntimeException("Test exception")
        logger.debug("Debug message with exception", exception)
        logger.info("Info message with exception", exception)
        logger.warn("Warning message with exception", exception)
        logger.error("Error message with exception", exception)
    }

    should("correctly identify enabled log levels") {
        val logger = LoggerFactory.createLogger("TestLogger") as LogbackLogger
        logger.isEnabled(LogLevel.DEBUG) shouldBe false
        logger.isEnabled(LogLevel.INFO) shouldBe true
        logger.isEnabled(LogLevel.WARN) shouldBe true
        logger.isEnabled(LogLevel.ERROR) shouldBe true
    }
})