package net.aethersanctum.tinman

import javax.net.ssl.SSLEngineResult.Status
import javax.management.remote.rmi._RMIConnection_Stub


/**
 * BigLookup is like an array with copy-on-write semantics,
 * which tries to replace the smallest parts possible.
 *
 * Stores items in a tree. read and write should amortize to O(log N)
 */

trait BigLookup[T] {
  def put(address:Int, item:T) : Option[BigLookup[T]]
  def delete(address:Int) : Option[BigLookup[T]]
  def get(address:Int) : Option[T]
}

object BigLookup {
  val LOG_2_NODE_CAPACITY = 2                     // bits per tree level in address
  val NODE_CAPACITY = 1 << LOG_2_NODE_CAPACITY    //

  def emptyLeaf[T] : Array[Option[T]] = Array(None, None, None, None)

  sealed trait Node[T] {
    def isEmpty : Boolean
  }

  case class Leaf[T](items:Array[Option[T]] = emptyLeaf[T]) extends Node[T] {
    override def isEmpty = ! items.exists { ! _.isEmpty }
  }

  case class Branch[T](children : Array[Option[Node[T]]]) extends Node[T]  {
    override def isEmpty = ! children.exists { ! _.isEmpty }
  }

  private class BigLookupImpl[T](root:Node[T], altitude:Int = 0) extends BigLookup[T] {

    lazy val capacity = NODE_CAPACITY << (altitude * LOG_2_NODE_CAPACITY)

    override def get(address:Int) : Option[T] = {
      throw new UnsupportedOperationException("TODO")
    }

    override def put(address:Int, item:T) : Option[BigLookup[T]] = {
      throw new UnsupportedOperationException("TODO")
    }

    override def delete(address:Int) : Option[BigLookup[T]] = {
      throw new UnsupportedOperationException("TODO")
    }
  }

  def apply[T] : BigLookup[T] = {
    val leaved = new Leaf[T]()
    new BigLookupImpl[T](leaved)
  }
}



