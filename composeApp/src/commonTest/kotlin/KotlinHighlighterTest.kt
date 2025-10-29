import com.djues3.names_are_hard.highlighting.KotlinHighlighter
import com.djues3.names_are_hard.highlighting.SnippetType
import kotlin.test.Test
import kotlin.test.assertEquals

class KotlinHighlighterTest {

    @Test
    fun `test that keywords are recognized`() {
        val highlighter = KotlinHighlighter()
        val script = """
            fun main() {}
        """.trimIndent()

        val highlights = highlighter.highlight(script).toList()
        assertEquals(2, highlights.size)
        val first = highlights[0]
        assertEquals(SnippetType.KEYWORD, first.type)
        assertEquals("fun", first.code)
        val second = highlights[1]
        assertEquals(SnippetType.NORMAL, second.type)
        // should probably skip whitespace
        assertEquals(" main() {}", second.code)
    }
}