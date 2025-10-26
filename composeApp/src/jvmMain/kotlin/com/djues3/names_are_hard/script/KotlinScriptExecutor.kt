package com.djues3.names_are_hard.script

import androidx.compose.runtime.snapshotFlow
import com.djues3.names_are_hard.utils.withTempFile
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.channelFlow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.launch

object KotlinScriptExecutor : ScriptExecutor {
    override suspend fun execute(script: String): Flow<ExecutionEvent> = flow {
        withTempFile(script, "script_", ".kts") {
            runScript(it).collect { event -> emit(event) }
        }
    }
}

fun runScript(script: String): Flow<ExecutionEvent> = channelFlow {
    val command = listOf("/usr/bin/env", "kotlinc", "-script", script)
    val process = ProcessBuilder(command).redirectErrorStream(false).start()
    send(ExecutionEvent.Started)

    launch {
        process.inputStream.bufferedReader().useLines { lines ->
            lines.forEach { line ->
                send(ExecutionEvent.Output(line))
            }
        }
    }

    launch {
        process.errorStream.bufferedReader().useLines { lines ->
            lines.forEach { line ->
                send(ExecutionEvent.Error(line))
            }
        }
    }

    send(ExecutionEvent.Finished(process.waitFor()))
}.flowOn(Dispatchers.IO)
