package es.weso.main

import scala.collection.JavaConversions._

object SchemaFormats {

  lazy val formats = List("TURTLE","SHEXC")

  def available(format: String): Boolean = {
    formats.contains(format.toUpperCase)
  }

  def default = formats(0)

  lazy val toList: List[String] = formats

  override def toString(): String = {
    toList.mkString(",")
  }
  
  def show: String = {
    toString
  }
  
}