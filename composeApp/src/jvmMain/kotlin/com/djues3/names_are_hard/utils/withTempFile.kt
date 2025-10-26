package com.djues3.names_are_hard.utils

import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.coroutineScope
import kotlin.io.path.absolutePathString
import kotlin.io.path.createTempFile
import kotlin.io.path.deleteIfExists
import kotlin.io.path.writeText

suspend fun <T> withTempFile(
    content: String,
    prefix: String,
    suffix: String,
    block: suspend CoroutineScope.(String) -> T
): T = coroutineScope {
    val tempFile = createTempFile(prefix, suffix)
    try {
        tempFile.writeText(content)
        block(tempFile.absolutePathString())
    } finally {
        tempFile.deleteIfExists()
    }
}