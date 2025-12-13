package com.khan366kos.etlassistant.logging

import ch.qos.logback.classic.Logger
import org.slf4j.LoggerFactory

fun logger(loggerId: String): LogWrapper = logger(
    logger = LoggerFactory.getLogger(loggerId) as Logger
)

fun logger(logger: Logger): LogWrapper = LogWrapper(
    logger
)