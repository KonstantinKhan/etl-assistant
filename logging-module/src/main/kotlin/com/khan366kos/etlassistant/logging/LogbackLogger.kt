package com.khan366kos.etlassistant.logging

import ch.qos.logback.classic.Level
import org.slf4j.LoggerFactory
import kotlin.time.ExperimentalTime
import kotlin.time.measureTimedValue

class LogbackLogger(loggerName: String) : Logger {
    private val logger = LoggerFactory.getLogger(loggerName)
    private val logbackLogger = logger

    override fun debug(message: String) {
        if (isEnabled(LogLevel.DEBUG)) {
            logger.debug(message)
        }
    }

    override fun debug(message: String, throwable: Throwable?) {
        if (isEnabled(LogLevel.DEBUG)) {
            logger.debug(message, throwable)
        }
    }

    override fun info(message: String) {
        if (isEnabled(LogLevel.INFO)) {
            logger.info(message)
        }
    }

    override fun info(message: String, throwable: Throwable?) {
        if (isEnabled(LogLevel.INFO)) {
            logger.info(message, throwable)
        }
    }

    override fun warn(message: String) {
        if (isEnabled(LogLevel.WARN)) {
            logger.warn(message)
        }
    }

    override fun warn(message: String, throwable: Throwable?) {
        if (isEnabled(LogLevel.WARN)) {
            logger.warn(message, throwable)
        }
    }

    override fun error(message: String) {
        if (isEnabled(LogLevel.ERROR)) {
            logger.error(message)
        }
    }

    override fun error(message: String, throwable: Throwable?) {
        if (isEnabled(LogLevel.ERROR)) {
            logger.error(message, throwable)
        }
    }

    override fun isEnabled(level: LogLevel): Boolean {
        return true
    }

    private fun toLogbackLevel(logLevel: LogLevel): Level {
        return when (logLevel) {
            LogLevel.DEBUG -> Level.DEBUG
            LogLevel.INFO -> Level.INFO
            LogLevel.WARN -> Level.WARN
            LogLevel.ERROR -> Level.ERROR
        }
    }

    @OptIn(ExperimentalTime::class)
    suspend fun <T> doWithLogging(
        id: String = "",
        level: String = "INFO",
        block: suspend () -> T
    ): T = try {
        logger.info("$id, $level: start")
        val (result, timeTaken) = measureTimedValue { block() }
        logger.info("$id, $level, finish")
        logger.info("$id take for $timeTaken")
        result
    } catch (t: Throwable) {
        logger.error(t.message)
        throw t
    }
}