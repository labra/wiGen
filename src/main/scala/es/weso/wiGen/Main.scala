package es.weso.wiGen

import org.rogach.scallop._
import org.rogach.scallop.exceptions._
import es.weso.shacl._
import es.weso.rdf.jena.RDFAsJenaModel

object Main extends App {
 override def main(args: Array[String]): Unit = {
    
    val opts = new MainOpts(args, errorDriver)

    val rdf = RDFAsJenaModel.empty
    val generated  = 
      WiGen.generate(
        opts.numCountries(),
        opts.numDataSets(),
        opts.numSlices(),
        opts.numObs(),
        opts.numComps(),
        opts.numIndicators(),
        opts.numOrgs(),
        rdf)
        
    if (opts.show()) {
      val str = generated.serialize(opts.format())
      println(str)
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

}
    
