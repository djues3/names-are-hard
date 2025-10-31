package com.djues3.names_are_hard.errors

object KotlinErrorParser: ErrorParser {
    private val regex = """.*:(\d+:\d+):\s*error:.*""".toRegex()

    override fun parse(errorLine: String): ErrorLocation? {
        val match = regex.find(errorLine) ?: return null
        val location = match.groups[1] ?: return null
        val parts = location.value.trimEnd(':').split(":")
        if (parts.size != 2) return null

        return ErrorLocation(
            line = parts[0].toInt(),
            column = parts[1].toInt(),
            matchRange = location.range
        )
    }

}