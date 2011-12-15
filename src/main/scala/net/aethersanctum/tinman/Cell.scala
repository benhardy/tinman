package net.aethersanctum.tinman

/**
 * cell contains a value of type V
 */
sealed trait Cell {
}

case class VarcharCell(value:String) extends Cell {
}

case class IntegerCell(value:Int) extends Cell {
}

case class BooleanCell(value:Boolean) extends Cell {
}


