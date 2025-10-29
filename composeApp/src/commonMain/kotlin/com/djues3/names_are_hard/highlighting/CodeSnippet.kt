package com.djues3.names_are_hard.highlighting

data class CodeSnippet(val code: String, val type: SnippetType) {}

enum class SnippetType {
    KEYWORD,
    NORMAL,
}