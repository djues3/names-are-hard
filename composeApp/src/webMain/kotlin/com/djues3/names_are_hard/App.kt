package com.djues3.names_are_hard

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import com.djues3.names_are_hard.errors.ErrorLocation
import com.djues3.names_are_hard.errors.ErrorParser
import com.djues3.names_are_hard.highlighting.JsHighlighter
import com.djues3.names_are_hard.script.JsScriptExecutor
import com.djues3.names_are_hard.ui.editor.EditorScreen
import com.djues3.names_are_hard.ui.editor.EditorViewModel

@Composable
fun App() {
    val editorViewModel = remember {
        EditorViewModel(JsScriptExecutor,
            // cannot parse errors to error locations as `eval` doesn't return nicely formatted error messages,
            // so give up and use a noop parser
            object : ErrorParser {
            override fun parse(errorLine: String): ErrorLocation? = null
        }).apply {
            val script = """
                class Foo {
                  bar() {
                    console.log("Hello from Foo.bar()");
                  }
                }
                console.log("Hello world!")
                new Foo().bar()
            """.trimIndent()
            updateScript(script)
        }
    }

    Surface(modifier = Modifier.background(MaterialTheme.colorScheme.background).fillMaxSize()) {
        EditorScreen(editorViewModel, JsHighlighter())
    }
}
