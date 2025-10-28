package com.djues3.names_are_hard.highlighting

import CodeSnippet
import com.djues3.names_are_hard.theme.Keyword
import com.djues3.names_are_hard.theme.Overlay

class KeywordHighlighter(private val keywords: Set<String>) : CodeHighlighter {
    override fun highlight(code: String): Sequence<CodeSnippet> = sequence {
        var currentIndex = 0
        val regex = Regex("\\b(${keywords.joinToString("|")})\\b")
        val matches = regex.findAll(code)

        for (match in matches) {
            val start = match.range.first
            val end = match.range.last + 1

            if (currentIndex < start) {
                yield(CodeSnippet(code.substring(currentIndex, start), Overlay))
            }
            yield(CodeSnippet(code.substring(start, end), Keyword))
            currentIndex = end
        }

        if (currentIndex < code.length) {
            yield(CodeSnippet(code.substring(currentIndex), Overlay))
        }
    }
}