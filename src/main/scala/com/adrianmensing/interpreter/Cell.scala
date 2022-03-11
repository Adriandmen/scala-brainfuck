package com.adrianmensing.interpreter

final class Cell(var byte: Byte) {
  def write(byteValue: Int): Unit = byte = byteValue

  def +=(num: Int): Unit = byte += num

  def char: Char = byte.toChar
}
