package com.djues3.names_are_hard.ui.editor

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.djues3.names_are_hard.script.ExecutionEvent
import com.djues3.names_are_hard.script.ScriptExecutor
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class EditorViewModel(private val executor: ScriptExecutor) : ViewModel() {

    private val _state = MutableStateFlow(EditorState())
    val state = _state


    fun updateScript(content: String) {
        _state.update { it.copy(script = content) }
    }

    fun executeScript() {
        viewModelScope.launch {

            executor.execute(state.value.script).collect { event ->
                println("Event: $event")
                when (event) {
                    is ExecutionEvent.Output -> {
                        println(event.line)
                        _state.update { it.copy(output = it.output + event.line + "\n") }
                    }

                    is ExecutionEvent.Error -> {
                        _state.update { it.copy(output = it.output + event.line + "\n") }
                    }

                    is ExecutionEvent.Finished -> {
                        _state.update {
                            it.copy(isRunning = false, exitCode = event.exitCode)
                        }
                    }
                    ExecutionEvent.Started -> {
                        _state.update { it.copy(isRunning = true, output = "", exitCode = null) }
                    }
                }
            }
        }

    }

}