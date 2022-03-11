package com.adrianmensing

import scala.language.implicitConversions

package object interpreter {
  implicit final def int2byte(int: Int): Byte = int.toByte
}
