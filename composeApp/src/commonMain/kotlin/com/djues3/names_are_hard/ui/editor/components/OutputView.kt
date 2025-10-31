package com.djues3.names_are_hard.ui.editor.components

import androidx.compose.foundation.background
import androidx.compose.material.Text
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.text.withStyle
import com.djues3.names_are_hard.ui.editor.OutputLine
import com.djues3.names_are_hard.ui.theme.Red
import kotlin.collections.forEach

@Composable
fun OutputView(
    content: List<OutputLine>,
    modifier: Modifier = Modifier
) {
    val annotatedText = remember(content){ content.annotate() }

    Text(
        annotatedText,
        modifier = modifier.background(MaterialTheme.colorScheme.primaryContainer),
        style = TextStyle(fontFamily = FontFamily.Monospace, color = MaterialTheme.colorScheme.onPrimaryContainer),
        maxLines = 40,
        overflow = TextOverflow.Visible
    )
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

