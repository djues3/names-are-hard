package com.djues3.names_are_hard.highlighting

interface CodeHighlighter {
    fun highlight(code: String): Sequence<CodeSnippet>
}