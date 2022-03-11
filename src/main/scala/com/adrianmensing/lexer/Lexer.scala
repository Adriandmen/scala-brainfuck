package com.adrianmensing.lexer

object Lexer {
  def tokensFrom(code: String): Seq[Token] =
    code collect {
      case '>' => IncrPointerToken
      case '<' => DecrPointerToken
      case '+' => IncrCellToken
      case '-' => DecrCellToken
      case '.' => OutputByteToken
      case ',' => AcceptByteToken
      case '[' => LoopStartToken
      case ']' => LoopEndToken
    }
}
