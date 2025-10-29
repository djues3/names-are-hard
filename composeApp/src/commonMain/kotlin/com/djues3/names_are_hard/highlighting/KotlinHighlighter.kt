package com.djues3.names_are_hard.highlighting

class KotlinHighlighter : CodeHighlighter {
    private val keywordHighlighter = KeywordHighlighter(keywords)
    override fun highlight(code: String): Sequence<CodeSnippet> = keywordHighlighter.highlight(code)
}


// NOTE: this only contains hard keywords
private val keywords = setOf(
    "as",
    "as?",
    "break",
    "class",
    "continue",
    "do",
    "else",
    "false",
    "for",
    "fun",
    "if",
    "in",
    "!in",
    "interface",
    "is",
    "!is",
    "null",
    "object",
    "package",
    "return",
    "super",
    "this",
    "throw",
    "true",
    "try",
    "typealias",
    "typeof",
    "val",
    "var",
    "when",
    "while"
)
