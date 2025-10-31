package com.djues3.names_are_hard

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import com.djues3.names_are_hard.errors.KotlinErrorParser
import com.djues3.names_are_hard.highlighting.KotlinHighlighter
import com.djues3.names_are_hard.script.KotlinScriptExecutor
import com.djues3.names_are_hard.ui.editor.EditorScreen
import com.djues3.names_are_hard.ui.editor.EditorViewModel
import org.intellij.lang.annotations.Language

@Composable
fun App() {
    val editorViewModel = remember {
        EditorViewModel(KotlinScriptExecutor, KotlinErrorParser).apply {
            @Language("kotlin")
            val script = """
              repeat(5) {
                println("Hello world!")
              }
            """.trimIndent()
            updateScript(script)
        }
    }

    Surface(modifier = Modifier.background(MaterialTheme.colorScheme.background).fillMaxSize()) {
        EditorScreen(editorViewModel, KotlinHighlighter())
    }
}