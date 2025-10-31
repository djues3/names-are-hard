package com.djues3.names_are_hard.ui.editor

import com.djues3.names_are_hard.errors.ErrorLocation

data class EditorState(
    val script: String = "",
    val output: List<OutputLine> = emptyList(),
    val isRunning: Boolean = false,
    val isCancelling: Boolean = false,
    val exitCode: Int? = null,
)

data class OutputLine(
    val line: String,
    val isError: Boolean = false,
    val location: ErrorLocation? = null,
)