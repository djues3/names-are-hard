package com.djues3.names_are_hard.ui.theme

import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.darkColorScheme
import androidx.compose.runtime.Composable

// Catppuccin Mocha Colors (https://catppuccin.com/palette/)
val colorScheme = darkColorScheme(
    primary = Lavender,
    onPrimary = Base,
    primaryContainer = Surface0,
    onPrimaryContainer = Lavender,

    secondary = Mauve,
    onSecondary = Base,
    secondaryContainer = Surface0,
    onSecondaryContainer = Mauve,

    tertiary = Teal,
    onTertiary = Base,
    tertiaryContainer = Surface0,
    onTertiaryContainer = Teal,

    background = Base,
    onBackground = Text,

    surface = Surface0,
    onSurface = Text,
    surfaceVariant = Surface1,
    onSurfaceVariant = Subtext1,

    error = Red,
    onError = Base,
    errorContainer = Surface0,
    onErrorContainer = Red,

    outline = Surface2,
    outlineVariant = Surface1,

    scrim = Crust,
    inverseSurface = Text,
    inverseOnSurface = Base,
    inversePrimary = Blue,
)

@Composable
fun Theme(content: @Composable () -> Unit) {
    MaterialTheme(
        colorScheme = colorScheme,
        content = content
    )
}