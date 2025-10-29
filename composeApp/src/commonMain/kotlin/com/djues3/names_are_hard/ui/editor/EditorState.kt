package com.djues3.names_are_hard.ui.editor

data class EditorState(
    val script: String = "",
    val output: String = "",
    val error: String = "",
    val isRunning: Boolean = false,
    val exitCode: Int? = null,
)
