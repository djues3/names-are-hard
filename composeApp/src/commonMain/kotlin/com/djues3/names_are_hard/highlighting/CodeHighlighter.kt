package com.djues3.names_are_hard.highlighting

import CodeSnippet
import androidx.compose.ui.graphics.Color

interface CodeHighlighter {
    fun highlight(code: String): Sequence<CodeSnippet>
}