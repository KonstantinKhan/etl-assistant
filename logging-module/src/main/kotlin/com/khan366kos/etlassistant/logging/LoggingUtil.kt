package com.khan366kos.etlassistant.logging

/**
 * Utility functions for common logging patterns.
 */
object LoggingUtil {
    /**
     * Executes the given block and logs its execution time.
     *
     * @param logger The logger to use for logging
     * @param message A message to include in the log
     * @param block The block to execute
     * @return The result of the block execution
     */
    inline fun <T> measureAndLog(logger: Logger, message: String, block: () -> T): T {
        val startTime = System.currentTimeMillis()
        try {
            logger.info("Starting: $message")
            val result = block()
            val endTime = System.currentTimeMillis()
            logger.info("Completed: $message in ${endTime - startTime}ms")
            return result
        } catch (e: Exception) {
            val endTime = System.currentTimeMillis()
            logger.error("Failed: $message after ${endTime - startTime}ms", e)
            throw e
        }
    }

    /**
     * Logs entry and exit of a function with timing information.
     *
     * @param logger The logger to use for logging
     * @param blockName The name of the block/function to log
     * @param block The block to execute
     * @return The result of the block execution
     */
    inline fun <T> logFunctionEntryExit(logger: Logger, blockName: String, block: () -> T): T {
        logger.info("Entering $blockName")
        val startTime = System.currentTimeMillis()
        return try {
            val result = block()
            val duration = System.currentTimeMillis() - startTime
            logger.info("Exiting $blockName normally in $duration ms")
            result
        } catch (e: Exception) {
            val duration = System.currentTimeMillis() - startTime
            logger.error("Exiting $blockName with exception after $duration ms", e)
            throw e
        }
    }
}