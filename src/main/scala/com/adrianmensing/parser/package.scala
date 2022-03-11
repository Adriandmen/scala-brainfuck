package com.adrianmensing

package object parser {
  sealed trait Expr

  case class AddPointerExpr(offset: Int) extends Expr
  case class AddCellExpr(offset: Int)    extends Expr
  case class LoopExpr(body: Seq[Expr])   extends Expr
  case object OutputByteExpr             extends Expr
  case object AcceptByteExpr             extends Expr
}
