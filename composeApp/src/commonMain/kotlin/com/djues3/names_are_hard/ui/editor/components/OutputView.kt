package com.djues3.names_are_hard.ui.editor.components

import androidx.compose.foundation.background
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.text.selection.SelectionContainer
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.*
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.text.style.TextOverflow
import com.djues3.names_are_hard.errors.ErrorLocation
import com.djues3.names_are_hard.ui.editor.OutputLine
import com.djues3.names_are_hard.ui.theme.Red

@Composable
fun OutputView(
    content: List<OutputLine>, modifier: Modifier = Modifier, onErrorClick: (ErrorLocation) -> Unit = {}
) {
    val annotatedText = remember(content) {
        content.annotate {
            // NOTE: LinkAnnotation will *always* be Clickable, so cast to get the tag with index
            val clickable = it as LinkAnnotation.Clickable
            val index = clickable.tag.removePrefix("error_").toIntOrNull()
            index?.let { i ->
                content.getOrNull(i)?.location?.let { errorLoc ->
                    println("Clicked error at $errorLoc")
                    onErrorClick(errorLoc) }
            }
        }
    }
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


fun List<OutputLine>.annotate(onErrorClick: (LinkAnnotation) -> Unit): AnnotatedString =
    if (this.isEmpty()) buildAnnotatedString {
        append("No output")
    }
    else {
        buildAnnotatedString {
            this@annotate.forEachIndexed { index, output ->
                if (output.isError) {
                    if (output.location != null) {
                        val range = output.location.matchRange
                        withStyle(SpanStyle(color = Red)) {
                            append(output.line.take(range.first))
                        }
                        withLink(
                            LinkAnnotation.Clickable(
                                "error_$index", linkInteractionListener = onErrorClick
                            )
                        ) {
                            withStyle(SpanStyle(color = Red, textDecoration = TextDecoration.Underline)) {
                                append(output.line.substring(range))
                            }
                        }
                        withStyle(SpanStyle(color = Red)) {
                            append(output.line.substring(range.last + 1))
                        }
                    } else {
                        withStyle(SpanStyle(color = Red)) {
                            append(output.line)
                        }
                    }
                } else {
                    append(output.line)
                }
            }
        }
    }
