package com.task.inserter

import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.Test

internal class BracketSequenceTest {
    @Test
    fun nextTest() {
        val bracketSequence = BracketSequence.parseFromString("{ ( [ (")!!
        assertEquals("{", bracketSequence.next())
        assertEquals("(", bracketSequence.next())
        assertEquals("[", bracketSequence.next())
        assertEquals("(", bracketSequence.next())
        assertEquals("{", bracketSequence.next())
    }

    @Test
    fun cloneTest() {
        val bracketSequence = BracketSequence.parseFromString("[ {")!!
        val copiedSequence = bracketSequence.clone()
        assertEquals("[", bracketSequence.next())
        assertEquals("[", copiedSequence.next())
        assertEquals("{", bracketSequence.next())
        assertEquals("{", copiedSequence.next())
        assertEquals("[", bracketSequence.next())
        assertEquals("{", bracketSequence.next())
        assertEquals("[", copiedSequence.next())
    }

    @Test
    fun resetTest() {
        val bracketSequence = BracketSequence.parseFromString("[ {")!!
        assertEquals("[", bracketSequence.next())
        bracketSequence.reset()
        assertEquals("[", bracketSequence.next())
        assertEquals("{", bracketSequence.next())
        bracketSequence.reset()
        bracketSequence.reset()
        assertEquals("[", bracketSequence.next())
        assertEquals("{", bracketSequence.next())
    }

    @Test
    fun parseIncorrectTest() {
        assertNull(BracketSequence.parseFromString("]"))
        assertNull(BracketSequence.parseFromString("(, ("))
        assertNull(BracketSequence.parseFromString("( a x [ ["))
        assertNull(BracketSequence.parseFromString("{ }"))
    }
}