package com.djues3.names_are_hard.theme

import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.darkColorScheme
import androidx.compose.runtime.Composable

private val colorScheme = darkColorScheme(
    primary = Base,
    secondary = Red,
    tertiary = Keyword,
    surface = Surface,
    onSurface = Surface1,
    background = Base,
    onBackground = Base,
    error = Red,
    onError = Base,
    onPrimary = Base,
)

@Composable
fun Theme(content: @Composable () -> Unit) {
    MaterialTheme(
        colorScheme = colorScheme,
        content = content
    )
}