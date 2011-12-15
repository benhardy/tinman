
package net.aethersanctum.tinman

import scala.collection.mutable.{StringBuilder => ScalaStringBuilder}
import javax.management.remote.rmi._RMIConnection_Stub

trait Described {
  def describe : String
}

class Database(val name: String, val tables : Array[Table]) extends Described {
  private val nameIndices = tables.zipWithIndex.map {  pair =>
        val (table, index) = pair
        (table.name, index)
    }
  val tableIndices = nameIndices.toMap

  override def describe : String = {
    val sorted_names : Array[String] = tables.map{ _.name }.sortWith{ _ < _ }
    val init_buf = new ScalaStringBuilder("DATABASE ") ++= name ++= " {\n"

    (sorted_names.foldLeft(init_buf) {
        (buf, table_name) => buf ++= "  " ++= table_name ++= "\n"
    } ++= "}\n").toString
  }
}

class Table(
    val name: String,
    val structure : Array[ColumnDef[_]],
    val rows : Map[Long, Row] = Map(),
    val rowCount : Int = 0
) extends Described  {

  override def describe : String = {
    val initBuf = new ScalaStringBuilder("TABLE ") ++= name ++= " {\n"
    (structure.foldLeft(initBuf) {
      (buf, column) => buf ++=  "  "  ++= column.name  ++=  "\n"
    } ++= "}\n").toString
  }
}

class Row(
    val table : Table,
    val cells : Array[Cell]
) {
}
