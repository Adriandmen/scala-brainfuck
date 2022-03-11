package com.adrianmensing.interpreter

import com.adrianmensing.parser._

import java.io.{InputStream, OutputStream}
import scala.annotation.tailrec

// let's make things more complicated than necessary by taking in the tape size as a type parameter.
class BrainfuckRunner[Size <: Int](
    code: Seq[Expr],
    in: InputStream = System.in,
    out: OutputStream = System.out
)(implicit tv: ValueOf[Size]) {

  final val tape: Tape[Size] = Tape[Size]

  final def run(): Unit = runCode(code.iterator.buffered)

  def printTape(): Unit = tape.show()

  final private[this] def runCodeProxy(it: Iterator[Expr]): Unit = runCode(it)

  @tailrec
  private def runCode(it: Iterator[Expr]): Unit = {
    it.next() match {
      case AddCellExpr(offset)    => tape.addToCell(offset)
      case AddPointerExpr(offset) => tape.addToPointer(offset)
      case OutputByteExpr         => out.write(tape.getCell.char)
      case AcceptByteExpr         => tape.getCell.write(in.read())
      case LoopExpr(body)         => while (tape.truthy) runCodeProxy(body.iterator)
    }

    if (it.hasNext) runCode(it)
  }
}
