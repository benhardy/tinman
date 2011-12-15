package net.aethersanctum.tinman

sealed trait ColumnDef[T] {
    def createCell(value:String) : Cell
    def name : String
    def size : Int
}

case class VARCHAR(name:String, size:Int) extends ColumnDef[String] {
    override def createCell(value:String) : Cell = {
       new VarcharCell(value)
    }
}

case class BOOL(name:String, size:Int = 1) extends ColumnDef[Boolean] {
    override def createCell(value:String) : Cell = {
        val b = value match {
            case "true" => true
            case "TRUE" => true
            case "false" => false
            case "FALSE" => false
            case _ => throw new IllegalArgumentException("Illegal boolean value: " + value)
        }
        new BooleanCell(b)
    }
}

case class INT(name:String, size:Int) extends ColumnDef[Int] {
    override def createCell(value:String) : Cell = {
        val i = Integer.parseInt(value)
        new IntegerCell(i)
    }
}