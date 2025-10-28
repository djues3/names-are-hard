import com.djues3.names_are_hard.highlighting.KotlinHighlighter
import com.djues3.names_are_hard.theme.*
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
        assertEquals(Keyword, first.color)
        assertEquals("fun", first.code)
        val second = highlights[1]
        assertEquals(Overlay, second.color)
        // should probably skip whitespace
        assertEquals(" main() {}", second.code)
    }
}