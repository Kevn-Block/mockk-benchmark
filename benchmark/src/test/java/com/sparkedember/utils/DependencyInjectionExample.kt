package com.sparkedember.utils

interface Logger {
    fun log(msg: String): String
}

object SingletonLogger : Logger {
    override fun log(msg: String): String {
        // Do work and return the log
        return msg
    }
}

class EventLogger {
    fun logEvent(event: String): String =
        SingletonLogger.log(event)
}

class EventLoggerWithDI(
    private val logger: Logger
) {
    fun logEvent(event: String): String =
        logger.log(event)
}