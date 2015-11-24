package es.weso.wiGen

import org.rogach.scallop._
import org.rogach.scallop.exceptions._
import es.weso.shacl._
import es.weso.rdf.jena.RDFAsJenaModel
import org.apache.jena.riot._
import scala.concurrent.duration._
import org.slf4j._
import org.apache.log4j._

object Main extends App {
  override def main(args: Array[String]): Unit = {

    lazy val log = LogManager.getRootLogger
    val appenders = log.getAllAppenders
    val appender: ConsoleAppender = log.getAppender("stdout").asInstanceOf[ConsoleAppender]

    val error = Level.ERROR
    log.setLevel(error)
    appender.setThreshold(error)

    val opts = new MainOpts(args, errorDriver)

    val rdf = RDFAsJenaModel.empty
    val generated =
      WiGen.generate(
        opts.numCountries(),
        opts.numDataSets(),
        opts.numSlices(),
        opts.numObs(),
        opts.numComps(),
        opts.numIndicators(),
        opts.numOrgs(),
        rdf)

    if (opts.shex()) {
      val (valid, nanos) = validateShEx(rdf)
      if (valid) {
        printTime(opts,nanos)
      } else {
        println("Not valid")
      }
    }

    if (opts.shacl()) {
      val nanos = validateShacl(rdf)
      if (nanos == -1) {
        println("Not valid")
      } else {
        printTime(opts,nanos)
      }
    }

    if (opts.show()) {
      val str = generated.serialize(opts.format())
      println(str)
    }

  }

  def printTime(opts: MainOpts, nanos: Long): Unit = {
    if (opts.time()) {
      val time = Duration(nanos, NANOSECONDS).toMillis
      println(f"${opts.numCountries()}%3d,${opts.numDataSets()}%3d,${opts.numSlices()}%3d,${opts.numObs()}%3d,${opts.numIndicators()}%3d,${opts.numOrgs()}%3d,${opts.numOrgs()}%3d,$time")
    }
  }

  private def errorDriver(e: Throwable, scallop: Scallop) = e match {
    case Help(s) =>
      println("Help: " + s)
      scallop.printHelp
      sys.exit(0)
    case _ =>
      println("Error: %s".format(e.getMessage))
      scallop.printHelp
      sys.exit(1)
  }

  def validateShEx(rdf: RDFAsJenaModel): (Boolean, Long) = {
    ShExValidator.validate(rdf.model)
  }

  def validateShacl(rdf: RDFAsJenaModel): Long = {
    val shaclFile = "schemas/webindexShapes.ttl"
    val model = rdf.model
    val shaclModel = RDFDataMgr.loadModel(shaclFile)
    val (result, time) = ShaclValidator.validate(model, shaclModel)
    if (result.size == 0) {
      time
    } else {
      println("Model not valid\n" + ShaclValidator.result2Str(result))
      -1
    }
  }

}
    
