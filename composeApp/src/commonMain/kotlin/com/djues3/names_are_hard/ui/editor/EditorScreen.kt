package com.djues3.names_are_hard.ui.editor

import androidx.compose.foundation.layout.*
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment.Companion.CenterHorizontally
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.focus.focusRequester
import androidx.compose.ui.unit.dp
import com.djues3.names_are_hard.highlighting.CodeHighlighter
import com.djues3.names_are_hard.highlighting.KotlinHighlighter
import com.djues3.names_are_hard.ui.editor.components.CodeEditorView
import com.djues3.names_are_hard.ui.editor.components.OutputView


@Composable
fun EditorScreen(viewModel: EditorViewModel, highlighter: CodeHighlighter) {
    val state by viewModel.state.collectAsState()
    val editorFocusRequester = remember { FocusRequester() }

    Column(modifier = Modifier.fillMaxHeight()) {
        Row(
            modifier = Modifier.fillMaxWidth().fillMaxHeight(0.8f).weight(1f).padding(8.dp),
            horizontalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            CodeEditorView(
                content = state.script,
                onContentChange = { viewModel.updateScript(it) },
                highlighter = highlighter,
                focusRequester = editorFocusRequester,
                navigationEvent = viewModel.navigationEvent,
                modifier = Modifier.weight(1f).fillMaxHeight().focusRequester(editorFocusRequester),
            )
            OutputView(
                content = state.output, modifier = Modifier.weight(1f).fillMaxHeight(), onErrorClick = {
                    viewModel.navigateToError(it)
                })
        }

        Column(
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = CenterHorizontally,
            modifier = Modifier.fillMaxWidth().padding(8.dp)
        ) {
            Button(onClick = { viewModel.executeScript() }, enabled = !state.isRunning, modifier = Modifier) {
                Text(if (state.isRunning) "Please wait for the script to finish..." else "Run")
            }


            Button(
                onClick = { viewModel.cancelExecution() },
                enabled = state.isRunning && !state.isCancelling,
                modifier = Modifier
            ) {
                Text(if (state.isCancelling) "Cancelling..." else "Cancel")
            }
        }
    }
}
