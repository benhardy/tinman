package net.aethersanctum.tinman

import org.junit.Test
import org.junit.Assert._

class DatabaseTest {

  @Test
  def testDescribe = {
    val db = new Database("myDB", Array(new Table("t1", Array()), new Table("t2", Array())))
    val desc = db.describe
    val expect = "DATABASE myDB {\n  t1\n  t2\n}\n"
    assertEquals(expect, desc)
  }
}