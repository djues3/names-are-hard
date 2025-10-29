package com.djues3.names_are_hard

import androidx.compose.ui.window.Window
import androidx.compose.ui.window.application
import com.djues3.names_are_hard.ui.theme.Theme

fun main() = application {
    Window(
        onCloseRequest = ::exitApplication,
        title = "names_are_hard",
    ) {
        Theme {
            App()
        }
    }
}