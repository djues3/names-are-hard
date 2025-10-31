@file:OptIn(ExperimentalWasmJsInterop::class)

package com.djues3.names_are_hard.script

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlin.Int
import kotlin.OptIn
import kotlin.String
import kotlin.collections.forEach
import kotlin.js.ExperimentalWasmJsInterop
import kotlin.js.JsArray
import kotlin.js.JsString
import kotlin.js.Promise
import kotlin.js.toList


external fun setupConsoleInterceptor()
external fun executeScriptWithCapture(script: String): JsScriptResult
external fun restoreConsole()
external interface JsScriptResult {
    val outputs: JsArray<JsString>
    val errors: JsArray<JsString>
    val exitCode: Int
}

object JsScriptExecutor: ScriptExecutor {
    override suspend fun execute(script: String): Flow<ExecutionEvent> = flow {
        emit(ExecutionEvent.Started)

        try {
            setupConsoleInterceptor()
            val result = executeScriptWithCapture(script)

            restoreConsole()

            result.outputs.toList().forEach { line ->
                emit(ExecutionEvent.Output("$line"))
            }

            result.errors.toList().forEach { line ->
                emit(ExecutionEvent.Error("$line"))
            }

            emit(ExecutionEvent.Finished(result.exitCode))

        } finally {
            restoreConsole()
        }
    }
}