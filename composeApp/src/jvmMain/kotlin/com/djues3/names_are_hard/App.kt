package com.djues3.names_are_hard

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.window.Window
import com.djues3.names_are_hard.script.KotlinScriptExecutor
import com.djues3.names_are_hard.ui.editor.EditorScreen
import com.djues3.names_are_hard.ui.editor.EditorViewModel
import com.djues3.names_are_hard.ui.theme.Theme

@Composable
fun App() {
    val editorViewModel = EditorViewModel(KotlinScriptExecutor)
    editorViewModel.updateScript("fun main() {\n    val msg = \"Hello world!\"\n    println(msg)\n}\nmain()")
    Surface(modifier = Modifier.background(MaterialTheme.colorScheme.background).fillMaxSize()) {
        EditorScreen(editorViewModel)
    }
}