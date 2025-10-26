package com.djues3.names_are_hard.script

sealed class ExecutionEvent {
    data object Started : ExecutionEvent()
    data class Output(val line: String) : ExecutionEvent()
    data class Error(val line: String) : ExecutionEvent()
    data class Finished(val exitCode: Int) : ExecutionEvent()
}