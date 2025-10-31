package com.djues3.names_are_hard.ui.editor

import androidx.compose.ui.text.TextRange
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.djues3.names_are_hard.errors.ErrorLocation
import com.djues3.names_are_hard.errors.ErrorParser
import com.djues3.names_are_hard.script.ExecutionEvent
import com.djues3.names_are_hard.script.ScriptExecutor
import kotlinx.coroutines.CancellationException
import kotlinx.coroutines.Job
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch

class EditorViewModel(private val executor: ScriptExecutor, private val errorParser: ErrorParser) : ViewModel() {

    private val _state = MutableStateFlow(EditorState())
    val state = _state.asStateFlow()

    private val _navigationEvent = MutableSharedFlow<TextRange>()
    val navigationEvent = _navigationEvent.asSharedFlow()

    private var executionJob: Job? = null
    fun updateScript(content: String) {
        _state.update { it.copy(script = content) }
    }

    fun executeScript() {
        executionJob?.cancel()

        executionJob = viewModelScope.launch {
            try {
                executor.execute(state.value.script).collect { event ->
                    println("Event: $event")
                    when (event) {
                        is ExecutionEvent.Output -> {
                            appendOutput(event.line)
                        }

                        is ExecutionEvent.Error -> {
                            val location = errorParser.parse(event.line)
                            appendOutput(event.line, true, location)
                        }

                        is ExecutionEvent.Finished -> {
                            _state.update {
                                it.copy(
                                    isRunning = false, output = it.output + OutputLine(
                                        "[Script finished with exit code: ${event.exitCode}]\n",
                                        isError = event.exitCode != 0
                                    ), exitCode = event.exitCode
                                )
                            }
                        }

                        ExecutionEvent.Started -> {
                            _state.update { it.copy(isRunning = true, output = emptyList(), exitCode = null) }
                        }
                    }
                }

            // Further work: Handle other exception which might get thrown
            } catch (_: CancellationException) {
                println("Cancelled script execution")
                _state.update {
                    it.copy(
                        isRunning = false,
                        output = it.output + OutputLine("\n[Script cancelled]\n", isError = true),
                        exitCode = -1,
                        isCancelling = false
                    )
                }
            }
        }

    }

    fun cancelExecution() {
        _state.update { it.copy(isCancelling = true) }
        println("Canceling execution")
        executionJob?.cancel()
        executionJob = null
    }

    override fun onCleared() {
        super.onCleared()
        cancelExecution()
    }

    fun navigateToError(location: ErrorLocation) {
        val text = state.value.script
        val position = calculatePosition(text, location.line, location.column)
        viewModelScope.launch {
            _navigationEvent.emit(TextRange(position, position))
        }
    }



    private fun appendOutput(line: String, isError: Boolean = false, location: ErrorLocation? = null) {
        _state.update {
            it.copy(
                output = it.output + OutputLine(
                    line = "$line\n", isError = isError, location = location
                )
            )
        }
    }

    private fun calculatePosition(text: String, line: Int, column: Int): Int {
        var currentLine = 1
        var position = 0

        for (char in text) {
            if (currentLine == line) {
                val start = position
                var end = start
                while (end < text.length && text[end] != '\n') {
                    end++
                }
                val length = end - position
                return start + (column - 1).coerceIn(0, length)
            }
            if (char == '\n') {
                currentLine++
            }
            position++
        }

        return text.length
    }


}