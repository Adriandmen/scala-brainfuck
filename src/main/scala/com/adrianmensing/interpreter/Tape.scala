package com.adrianmensing.interpreter

class Tape[A <: Int] private (size: A) {
  final private val tape: Array[Cell] = Array.fill[Cell](size)(new Cell(0))

  private[this] var pointer = 0

  final def getCell: Cell             = tape(pointer)
  final def truthy: Boolean           = tape(pointer).byte != 0
  final def addToCell(num: Int): Unit = tape(pointer) += num
  final def addToPointer(num: Int): Unit = {
    pointer = (pointer + num) % tape.length
    if (pointer < 0)
      pointer += tape.length
  }

  final def show(): Unit = {
    println()
    println(tape.toSeq.map(_.byte.toInt))
  }
}

object Tape {
  def apply[Size <: Int](implicit tv: ValueOf[Size]): Tape[Size] = new Tape[Size](tv.value)
}
