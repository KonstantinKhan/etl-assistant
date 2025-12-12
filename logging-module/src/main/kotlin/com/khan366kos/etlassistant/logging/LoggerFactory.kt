package com.khan366kos.etlassistant.logging

/**
 * Factory for creating Logger instances.
 */
object LoggerFactory {
    fun createLogger(name: String): Logger {
        return LogbackLogger(name)
    }

    inline fun <reified T> createLogger(): Logger {
        return createLogger(T::class.simpleName ?: T::class.java.name)
    }
}