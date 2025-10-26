package com.djues3.names_are_hard.script

import app.cash.turbine.test
import com.djues3.names_are_hard.script.ExecutionEvent.*
import kotlinx.coroutines.flow.toList
import kotlinx.coroutines.runBlocking
import kotlin.test.Test
import kotlin.test.assertEquals


// Since these tests are slow (on my machine, each test takes a few seconds), only two basic scenarios are tested.
class KotlinScriptExecutorTest {
    val self = KotlinScriptExecutor

    // since stdout is correctly emitted, I assume that stderr is too.
    @Test
    fun `test that hello world works`() {
        runBlocking {
            val flow = self.execute("""println("Hello world!")""")
            flow.toList()
            flow.test {
                assertEquals(Started, awaitItem())
                assertEquals(Output("Hello world!"), awaitItem())
                assertEquals(Finished(0), awaitItem())
                awaitComplete()
            }
        }
    }

    @Test
    fun `test that exit code is correctly passed`() {
        runBlocking {
            val flow = self.execute("""System.exit(1)""")
            flow.toList()
            flow.test {
                assertEquals(Started, awaitItem())
                assertEquals(Finished(1), awaitItem())
                awaitComplete()
            }
        }
    }
}