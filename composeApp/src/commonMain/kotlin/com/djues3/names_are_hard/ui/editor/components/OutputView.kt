package com.djues3.names_are_hard.ui.editor.components

import androidx.compose.foundation.background
import androidx.compose.material.Text
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.style.TextOverflow

@Composable
fun OutputView(
    content: String,
    modifier: Modifier = Modifier
) {
    Text(
        content,
        modifier = modifier.background(MaterialTheme.colorScheme.primaryContainer),
        style = TextStyle(fontFamily = FontFamily.Monospace, color = MaterialTheme.colorScheme.onPrimaryContainer),
        maxLines = 40,
        overflow = TextOverflow.Visible
    )
}