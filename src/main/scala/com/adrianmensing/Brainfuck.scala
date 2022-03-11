package com.adrianmensing

import com.adrianmensing.interpreter.BrainfuckRunner
import com.adrianmensing.lexer._
import com.adrianmensing.parser.Parser

object Brainfuck {
  private val code =
    "+[>[<-[]>+[>+++>[+++++++++++>][>]-[<]>-]]++++++++++<]>\n>>>>>----.<<+++.<-..+++.<-.>>>.<<.+++.------.>-.<<+.<."

  def main(args: Array[String]): Unit = {
    val tokens = Lexer.tokensFrom(code)
    val parsed = Parser.parse(tokens)
    val runner = new BrainfuckRunner[1024](parsed)

    runner.run()
    runner.printTape()
  }
}
