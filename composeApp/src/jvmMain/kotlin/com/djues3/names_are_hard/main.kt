package com.djues3.names_are_hard

import androidx.compose.ui.window.Window
import androidx.compose.ui.window.application

fun main() = application {
    Window(
        onCloseRequest = ::exitApplication,
        title = "names_are_hard",
    ) {
        App()
    }
}