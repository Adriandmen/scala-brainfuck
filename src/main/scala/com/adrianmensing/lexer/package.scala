package com.adrianmensing

/**
  * The lexer package.
  *
  * This package contains all the 'lexical analysis' logic. For Brainfuck, this is not extremely
  * difficult, due to its simple syntax. Here, all token types that are used are defined.
  *
  * @since 1.0.0
  */
package object lexer {
  sealed trait Token

  case object IncrPointerToken extends Token // >
  case object DecrPointerToken extends Token // <
  case object IncrCellToken    extends Token // +
  case object DecrCellToken    extends Token // -
  case object OutputByteToken  extends Token // .
  case object AcceptByteToken  extends Token // ,
  case object LoopStartToken   extends Token // [
  case object LoopEndToken     extends Token // ]
}
