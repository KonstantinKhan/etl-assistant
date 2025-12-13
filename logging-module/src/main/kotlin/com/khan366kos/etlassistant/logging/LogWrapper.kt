package com.khan366kos.etlassistant.logging

import ch.qos.logback.classic.Logger
import kotlin.time.measureTimedValue

class LogWrapper(val logger: Logger, loggerId: String = "") {
    suspend fun <T> doWithLogging(
        id: String = "",
        level: LogLevel = LogLevel.INFO,
        function: suspend () -> T
    ): T = try {
        logger.info("$level: $id started")
        val (result, duration) = measureTimedValue { function() }
        logger.info("$level: $id finished")
        logger.info("$level: $id duration=$duration")
        result
    } catch (t: Throwable) {
        logger.error("$level: $id error", t)
        throw t
    }
}