package com.adrianmensing.parser

import com.adrianmensing.lexer._

import scala.annotation.tailrec

object Parser {
  def parse(tokens: Seq[Token]): Seq[Expr] = {
    val iterator = tokens.iterator
    val result = parseTokens(iterator)

    require(!iterator.hasNext, s"Found unexpected ']' with remaining code: ${iterator.toSeq}")
    result
  }

  // Intermediate method to maintain tail-recursive property of `parseTokens`
  private def parseInner(it: Iterator[Token]): Seq[Expr] = parseTokens(it)

  @tailrec
  private def parseTokens(it: Iterator[Token], acc: Seq[Expr] = Seq.empty): Seq[Expr] = {
    if (!it.hasNext) return acc

    it.next() match {
      case IncrPointerToken => parseTokens(it, acc :+ AddPointerExpr(+1))
      case DecrPointerToken => parseTokens(it, acc :+ AddPointerExpr(-1))
      case IncrCellToken    => parseTokens(it, acc :+ AddCellExpr(+1))
      case DecrCellToken    => parseTokens(it, acc :+ AddCellExpr(-1))
      case OutputByteToken  => parseTokens(it, acc :+ OutputByteExpr)
      case AcceptByteToken  => parseTokens(it, acc :+ AcceptByteExpr)
      case LoopEndToken     => acc
      case LoopStartToken =>
        val inner = LoopExpr(parseInner(it))
        parseTokens(it, acc :+ inner)
    }
  }
}
