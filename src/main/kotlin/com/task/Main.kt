@file:JvmName("Main")

package com.task

import com.task.inserter.BracketSequence
import com.task.inserter.BracketSequence.Companion.parseFromString
import com.task.inserter.Inserter
import com.xenomachina.argparser.ArgParser
import com.xenomachina.argparser.default
import com.xenomachina.argparser.mainBody

fun main(args: Array<String>) = mainBody {
    val parsedArgs = ArgParser(args).parseInto(::Args)
    val braceSequence = parseFromString(parsedArgs.bracketSequence) ?: error("Invalid bracket sequence option passed")
    val injector = Inserter(parsedArgs.w, parsedArgs.c, braceSequence)

    val string = parsedArgs.string ?: readLine() ?: ""
    println(injector.insert(string))
}

private class Args(parser: ArgParser) {
    val w by parser.flagging("-w",
        "--wrap",
        help = "place brackets around the line")
        .default(false)

    val c by parser.flagging("-c",
        "--center",
        help = "insert empty brackets in the middle of a string if the length is even")
        .default(false)

    val bracketSequence by parser.storing("-b",
        "--brackets",
        help = "sequence of brackets to insert." +
                " Any combination of ${BracketSequence.allowedSymbols.joinToString(", ")}")
        .default("(")

    val string by parser.positional(help = "string to modify").default { null }
}
