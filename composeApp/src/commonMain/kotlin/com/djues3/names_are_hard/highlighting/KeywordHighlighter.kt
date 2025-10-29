package com.djues3.names_are_hard.highlighting

class KeywordHighlighter(keywords: Set<String>) : CodeHighlighter {
    val regex = Regex("\\b(${keywords.joinToString("|")})\\b")
    override fun highlight(code: String): Sequence<CodeSnippet> = sequence {
        var currentIndex = 0
        val matches = regex.findAll(code)

        for (match in matches) {
            val start = match.range.first
            val end = match.range.last + 1

            if (currentIndex < start) {
                yield(CodeSnippet(code.substring(currentIndex, start), type = SnippetType.NORMAL))
            }
            yield(CodeSnippet(code.substring(start, end), SnippetType.KEYWORD))
            currentIndex = end
        }

        if (currentIndex < code.length) {
            yield(CodeSnippet(code.substring(currentIndex), SnippetType.NORMAL))
        }
    }
}