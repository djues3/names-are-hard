package com.djues3.names_are_hard.script

class ExitCode(private val code: Int) {
    fun isError(): Boolean {
        return code != 0
    }

    override fun toString(): String {
        return code.toString()
    }
}