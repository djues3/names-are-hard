package com.djues3.names_are_hard.script

import kotlinx.coroutines.flow.Flow

interface ScriptExecutor {
    suspend fun execute(script: String): Flow<ExecutionEvent>
}