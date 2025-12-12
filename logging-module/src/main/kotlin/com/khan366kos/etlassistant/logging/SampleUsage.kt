package com.khan366kos.etlassistant.logging


class SampleUsage {
    private val logger = LoggerFactory.createLogger<SampleUsage>()

    fun performSomeOperation() {
        LoggingUtil.logFunctionEntryExit(logger, "performSomeOperation") {
            logger.info("Starting operation...")
            
            // Simulating some work
            Thread.sleep(100)
            
            logger.debug("Performing intermediate step...")
            
            if (true) { // some condition
                logger.warn("This is just a warning for demo purposes")
            }
            
            // More simulated work
            Thread.sleep(50)
            
            logger.info("Operation completed successfully!")
        }
    }

    fun riskyOperation() {
        LoggingUtil.measureAndLog(logger, "riskyOperation") {
            logger.info("Attempting risky operation...")
            try {
                // Simulate some risky work
                Thread.sleep(75)
                
                // Simulate random failure for demo
                if (Math.random() > 0.7) {
                    throw RuntimeException("Simulated failure for demo")
                }
                
                logger.info("Risky operation succeeded!")
            } catch (e: Exception) {
                logger.error("Risky operation failed!", e)
                throw e
            }
        }
    }
}