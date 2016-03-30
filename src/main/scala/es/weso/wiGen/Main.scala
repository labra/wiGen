package es.weso.wiGen

import org.rogach.scallop._
import org.rogach.scallop.exceptions._
import es.weso.shacl._
import es.weso.rdf.jena.RDFAsJenaModel
import org.apache.jena.riot._
import scala.concurrent.duration._
import org.slf4j._
import org.apache.log4j._
import es.weso.utils.FileUtils

object Main extends App {
  override def main(args: Array[String]): Unit = {

    lazy val log = LogManager.getRootLogger
    val appenders = log.getAllAppenders
    val appender: ConsoleAppender = log.getAppender("stdout").asInstanceOf[ConsoleAppender]

    val error = Level.ERROR
    log.setLevel(error)
    appender.setThreshold(error)

    val opts = new MainOpts(args, errorDriver)
    opts.verify()

    val rdf = RDFAsJenaModel.empty
    val generated =
      WiGen.generate(
        opts.allScopeNodes(),
        opts.numCountries(),
        opts.numDataSets(),
        opts.numSlices(),
        opts.numObs(),
        opts.numComps(),
        opts.numIndicators(),
        opts.numOrgs(),
        rdf,
        opts.numBadCountries(),
        opts.numBadDataSets(),
        opts.numBadSlices(),
        opts.numBadObs(),
        opts.numBadComps(),
        opts.numBadIndicators(),
        opts.numBadOrgs(),
        opts.allTypes())

    if (opts.shex.isDefined) {
      val (valid, time) = validateShEx(opts.shex(),rdf, opts.explain())
      if (valid) {
        printTime("shex,      valid",opts,time)
      } else {
        printTime("shex,  not valid",opts,time)
      }
    }

    if (opts.shacl.isDefined) {
      try {
      val (valid,time) = validateShacl(opts.shacl(),rdf, opts.explain())
      if (valid) {
        printTime("shacl,     valid", opts, time)
      } else {
        printTime("shacl, not valid", opts, time)
      } 
      } catch {
        case e : Throwable => 
          printTime("shacl error " + e,opts,-1)
      }
      
    }

    if (opts.show()) {
      val str = generated.serialize(opts.format())
      println(str)
    }
    
    if (opts.outputFile.get.isDefined) {
      val fileName = opts.outputFile.get.get 
      val str = generated.serialize(opts.format())
      FileUtils.writeFile(fileName,str)
    }
  }

  def printTime(msg: String, opts: MainOpts, nanos: Long): Unit = {
    if (opts.time()) {
      val time = Duration(nanos, NANOSECONDS).toMillis
      println(f"$msg%s, ${opts.allScopeNodes()}, ${opts.numCountries()}%3d, ${opts.numDataSets()}%3d, ${opts.numSlices()}%3d,${opts.numObs()}%3d,${opts.numComps()}%3d,${opts.numIndicators()}%3d,${opts.numOrgs()}%3d,$time%10d")
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

  def validateShEx(schemaFile: String, rdf: RDFAsJenaModel, explain: Boolean): (Boolean, Long) = {
    ShExValidator.validate(schemaFile, rdf.model, explain)
  }

  def validateShacl(
      shaclFile: String, 
      rdf: RDFAsJenaModel,
      explain: Boolean): (Boolean,Long) = {
    val model = rdf.model
    val shaclModel = RDFDataMgr.loadModel(shaclFile)
    val (result, time) = ShaclValidator.validate(model, shaclModel)
    if (result.size == 0) {
      (true,time)
    } else {
      if (explain) {
       println("Model not valid\n" + ShaclValidator.result2Str(result))
      }
      (false,time)
    }
  }

}
    
