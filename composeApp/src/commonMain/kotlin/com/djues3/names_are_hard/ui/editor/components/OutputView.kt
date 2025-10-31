package com.djues3.names_are_hard.ui.editor.components

import androidx.compose.foundation.background
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.text.selection.SelectionContainer
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.Text
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.*
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.style.TextOverflow
import com.djues3.names_are_hard.ui.editor.OutputLine
import com.djues3.names_are_hard.ui.theme.Red

@Composable
fun OutputView(
    content: List<OutputLine>,
    modifier: Modifier = Modifier
) {
    val annotatedText = remember(content) { content.annotate() }
    SelectionContainer(modifier = modifier) {
        Text(
            annotatedText,
            modifier = Modifier.background(MaterialTheme.colorScheme.primaryContainer)
                .verticalScroll(rememberScrollState()),
            style = TextStyle(fontFamily = FontFamily.Monospace, color = MaterialTheme.colorScheme.onPrimaryContainer),
            overflow = TextOverflow.Visible
        )
    }
}


fun List<OutputLine>.annotate(): AnnotatedString {
    return if (this.isEmpty())
        buildAnnotatedString {
            append("No output")
        }
    else {
        buildAnnotatedString {
            this@annotate.forEach { line ->
                if (line.isError) {
                    withStyle(SpanStyle(color = Red)) {
                        append(line.line)
                    }
                } else {
                    append(line.line)
                }
            }
        }
    }
}

