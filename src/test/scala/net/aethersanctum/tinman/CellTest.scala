package net.aethersanctum.tinman

import org.junit.Test
import org.junit.Assert._
import com.sun.xml.internal.ws.developer.MemberSubmissionAddressing.Validation

class CellTest {


  @Test
  def cellUpdate = {
    val hdr = new BOOL("column_1")
    val table = new Table("my_table", Array(hdr))
    val db = new Database("my_schema", Array(table))
    val cell1 = hdr.createCell("true")
    val cell2 = cell1 match {
      case b:BooleanCell => {
        assertEquals("cell1 should be boolean true", true, b.value)
        Some(b.copy(value = false))
      }
      case _ => {
        fail("cell1 wasn't expected type")
        None
      }
    }
    cell2 match {
      case Some(BooleanCell(bool_val)) => {
        assertEquals("cell2 should be boolean true", false, bool_val)
      }
      case _ => {
        fail("cell2 wasn't expected type")
      }
    }
  }
}