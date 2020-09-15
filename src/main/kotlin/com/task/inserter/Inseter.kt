package com.task.inserter

class Inserter(
    private val isWrapped: Boolean,
    private val isCenter: Boolean,
    private val bracketSequence: BracketSequence
) {
    fun insert(string: String): String {
        var prefixBracket = ""
        if (isWrapped) {
            prefixBracket = bracketSequence.next()
        }
        val innerString = unwrappedInsert(string)
        return prefixBracket + innerString + BracketSequence.getPair(prefixBracket)
    }

    private fun unwrappedInsert(string: String, index: Int = 0): String {
        val pairIndex = string.length - index - 1
        if (index >= pairIndex || (pairIndex == index + 1 && !isCenter)) {
            return string.substring((index..pairIndex))
        }

        val brace = bracketSequence.next()
        val pairBrace = BracketSequence.getPair(brace)

        return (string[index] + brace
                + unwrappedInsert(string, index + 1)
                + pairBrace + string[pairIndex])
    }
}

class BracketSequence private constructor(private val brackets: List<String>) {
    private var index = 0

    companion object {
        val allowedSymbols = listOf("{", "[", "(")
        private val pairs = mapOf("{" to "}", "[" to "]", "(" to ")")

        fun parseFromString(str: String): BracketSequence? {
            val braces = str.split(" ")
            if (braces.all { allowedSymbols.contains(it) }) return BracketSequence(braces)
            return null
        }

        fun getPair(str: String): String {
            return pairs[str] ?: ""
        }
    }

    fun next(): String {
        return brackets[index++ % brackets.size]
    }
}
