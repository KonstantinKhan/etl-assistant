package com.khan366kos.etlassistant.logging

/**
 * Interface for logging functionality.
 */
interface Logger {
    fun debug(message: String)
    fun debug(message: String, throwable: Throwable?)
    
    fun info(message: String)
    fun info(message: String, throwable: Throwable?)
    
    fun warn(message: String)
    fun warn(message: String, throwable: Throwable?)
    
    fun error(message: String)
    fun error(message: String, throwable: Throwable?)
    
    fun isEnabled(level: LogLevel): Boolean
}