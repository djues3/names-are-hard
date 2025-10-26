package com.djues3.names_are_hard

import com.djues3.names_are_hard.script.ExecutionEvent
import com.djues3.names_are_hard.script.KotlinScriptExecutor
import org.intellij.lang.annotations.Language

//fun main() = application {
//    Window(
//        onCloseRequest = ::exitApplication,
//        title = "names_are_hard",
//    ) {
//        App()
//    }
//}
//
suspend fun main() {

    @Language("kotlin")
    val script = $$"""
        for (i in 1..30) {
            Thread.sleep(100)
            println("Hello $i")
            if (Math.random() > 0.9) throw Exception("Charles we are checking")
        }
    """.trimIndent()
    val flow = KotlinScriptExecutor.execute(script)
    flow.collect {
        when (it) {
            is ExecutionEvent.Output -> {
                println(it.line)
            }

            is ExecutionEvent.Error -> {
                eprintln(it.line)
            }

            is ExecutionEvent.Finished -> {
                println("Script finished with exit code ${it.exitCode}")
            }

            ExecutionEvent.Started -> {}
        }
    }
}


fun eprintln(string: String) {
    System.err.println(string)
}