package com.djues3.names_are_hard.ui.editor.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.TextField
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.*
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.input.TextFieldValue
import com.djues3.names_are_hard.highlighting.CodeHighlighter
import com.djues3.names_are_hard.highlighting.SnippetType
import com.djues3.names_are_hard.ui.theme.Mauve

@Composable
fun CodeEditorView(
    content: String,
    onContentChange: (String) -> Unit,
    highlighter: CodeHighlighter,
    modifier: Modifier = Modifier
) {
    var textFieldValue by remember {
        mutableStateOf(TextFieldValue(content))
    }

    TextField(
        value = textFieldValue.copy(annotatedString = highlighter.annotate(textFieldValue.text)),
        onValueChange = { new ->
            textFieldValue = new
            onContentChange(new.text)
        },
        modifier = modifier.background(MaterialTheme.colorScheme.primaryContainer),
        textStyle = TextStyle(fontFamily = FontFamily.Monospace, color = MaterialTheme.colorScheme.onPrimaryContainer),
    )
}

private fun CodeHighlighter.annotate(code: String): AnnotatedString {
    return buildAnnotatedString {
        highlight(code).forEach { snippet ->
            when (snippet.type) {
                SnippetType.KEYWORD -> {
                    withStyle(SpanStyle(color = Mauve)) {
                        append(snippet.code)
                    }
                }

                SnippetType.NORMAL -> {
                    append(snippet.code)
                }
            }
        }
    }
}