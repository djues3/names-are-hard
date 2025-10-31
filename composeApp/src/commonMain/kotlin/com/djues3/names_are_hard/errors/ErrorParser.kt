package com.djues3.names_are_hard.errors

interface ErrorParser {
    fun parse(errorLine: String): ErrorLocation?
}

data class ErrorLocation(val line: Int, val column: Int, val matchRange: IntRange)