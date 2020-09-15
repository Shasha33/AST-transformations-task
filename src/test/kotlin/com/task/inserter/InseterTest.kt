package com.task.inserter

import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test


internal class InserterTest {
    private fun assertEqualsAfterInsert(outer: Boolean, center: Boolean, stringSequence: String,
                                        initialString: String, resultString: String) {
        val charSequence = BracketSequence.parseFromString(stringSequence)
        val inserter = Inserter(outer, center, charSequence!!)
        assertEquals(resultString, inserter.insert(initialString))
    }

    @Test
    fun exampleTest() {
        assertEqualsAfterInsert(outer = false, center = false, stringSequence = "(",
                initialString = "abcde", resultString = "a(b(c)d)e")
        assertEqualsAfterInsert(outer = true, center = true, stringSequence = "( [",
                initialString = "abcdefgh", resultString = "(a[b(c[d()e]f)g]h)")
    }

    @Test
    fun emptyStringTest() {
        assertEqualsAfterInsert(outer = false, center = false, stringSequence = "( {",
                initialString = "", resultString = "")
        assertEqualsAfterInsert(outer = true, center = false, stringSequence = "(",
                initialString = "", resultString = "()")
        assertEqualsAfterInsert(outer = true, center = true, stringSequence = "(",
                initialString = "", resultString = "()")
        assertEqualsAfterInsert(outer = false, center = true, stringSequence = "(",
                initialString = "", resultString = "")
    }

    @Test
    fun oneElementStringTest() {
        assertEqualsAfterInsert(outer = true, center = false, stringSequence = "(",
                initialString = "a", resultString = "(a)")
        assertEqualsAfterInsert(outer = false, center = true, stringSequence = "(",
                initialString = "a", resultString = "a")
        assertEqualsAfterInsert(outer = true, center = false, stringSequence = "( {",
                initialString = "a", resultString = "(a)")
        assertEqualsAfterInsert(outer = true, center = false, stringSequence = "(",
                initialString = "a", resultString = "(a)")
        assertEqualsAfterInsert(outer = true, center = true, stringSequence = "(",
                initialString = "a", resultString = "(a)")
    }

    @Test
    fun centerBracesTest() {
        assertEqualsAfterInsert(outer = false, center = true, stringSequence = "(",
                initialString = "aaaa", resultString = "a(a()a)a")
        assertEqualsAfterInsert(outer = false, center = true, stringSequence = "(",
                initialString = "aa", resultString = "a()a")
        assertEqualsAfterInsert(outer = false, center = true, stringSequence = "( {",
                initialString = "aa", resultString = "a()a")
        assertEqualsAfterInsert(outer = false, center = true, stringSequence = "( {",
                initialString = "1234", resultString = "1(2{}3)4")
    }

    @Test
    fun outerBracesTest() {
        assertEqualsAfterInsert(outer = true, center = false, stringSequence = "(",
                initialString = "1234", resultString = "(1(23)4)")
        assertEqualsAfterInsert(outer = true, center = false, stringSequence = "(",
                initialString = "aa", resultString = "(aa)")
        assertEqualsAfterInsert(outer = true, center = true, stringSequence = "[ {",
                initialString = "aa", resultString = "[a{}a]")
        assertEqualsAfterInsert(outer = true, center = true, stringSequence = "( {",
                initialString = "123321", resultString = "(1{2(3{}3)2}1)")
    }

    @Test
    fun longBracesSequenceTest() {
        assertEqualsAfterInsert(outer = true, center = true, stringSequence = "( (",
                initialString = "12345678", resultString = "(1(2(3(4()5)6)7)8)")
        assertEqualsAfterInsert(outer = true, center = true, stringSequence = "( ( ( ( [",
                initialString = "12345678", resultString = "(1(2(3(4[]5)6)7)8)")
        assertEqualsAfterInsert(outer = true, center = true, stringSequence = "( { ( ( [",
                initialString = "12345678", resultString = "(1{2(3(4[]5)6)7}8)")
        assertEqualsAfterInsert(outer = true, center = true, stringSequence = "{ ( [",
                initialString = "12345678", resultString = "{1(2[3{4()5}6]7)8}")
        assertEqualsAfterInsert(outer = true, center = true, stringSequence = "{ ( [",
                initialString = "123456789", resultString = "{1(2[3{4(5)6}7]8)9}")
        assertEqualsAfterInsert(outer = true, center = true, stringSequence = "{ (",
                initialString = "123456789", resultString = "{1(2{3(4{5}6)7}8)9}")
        assertEqualsAfterInsert(outer = false, center = true, stringSequence = "( {",
                initialString = "123456789", resultString = "1(2{3(4{5}6)7}8)9")
        assertEqualsAfterInsert(outer = true, center = false, stringSequence = "{ ( [",
                initialString = "12345678", resultString = "{1(2[3{45}6]7)8}")
        assertEqualsAfterInsert(outer = false, center = false, stringSequence = "( [ {",
                initialString = "12345678", resultString = "1(2[3{45}6]7)8")
    }
}