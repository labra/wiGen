package es.weso.wiGen

import org.rogach.scallop._
import org.rogach.scallop.exceptions._
import es.weso.shacl._
import es.weso.main.Processors

class MainOpts(
  arguments: Array[String],
  onError: (Throwable, Scallop) => Nothing) extends ScallopConf(arguments) {

  banner("""| ShExperiments
            | Options:
            |""".stripMargin)

  footer("Enjoy!")

  val numCountries = opt[Int]("countries",
    short = 'c',
    default = Some(1),
    descr = "Number of countries"
  )

  val numDataSets = opt[Int]("dataSets",
    short = 'd',
    default = Some(1),
    descr = "Number of data sets"
  )
    
  val numSlices = opt[Int]("slices",
    short = 'l',
    default = Some(1),
    descr = "Number of slices"
  )
  
  val numObs = opt[Int]("observations",
    short = 'o',
    default = Some(1),
    descr = "Number of observations"
  )
    
  val numComps = opt[Int]("computations",
    short = 'p',
    default = Some(1),
    descr = "Number of computations"
  )
  
  val numIndicators = opt[Int]("Indicators",
    short = 'i',
    default = Some(1),
    descr = "Number of indicators"
  )

  val numOrgs = opt[Int]("Organizations",
    short = 'g',
    default = Some(1),
    descr = "Number of organizations"
  )
  
  val show = toggle("show",
    prefix = "no-",
    default = Some(false),
    descrYes = "show data generated",
    descrNo = "don't show data generated",
    short = 's'
    )
    
  val format = opt[String]("format",
    default = Some("TURTLE"),
    descr = "format"
    )
    
  val outputFile = opt[String]("file",
    default = None,
    descr = "save generated data in a file",
    short = 'f'
    )
    
  val singleScope = toggle("single",
    prefix = "no-",
    default = Some(true),
    descrYes = "generate a single scopeNode declaration",
    descrNo = "generate all scopeNode declarations",
    noshort = true)
    
  val shex = toggle("shex",
    prefix = "no-",
    default = Some(false),
    descrYes = "validate data with shex",
    descrNo = "don't validate data with shex",
    short = 'x')
    
  val shacl = toggle("shacl",
    prefix = "no-",
    default = Some(false),
    descrYes = "validate data with shacl",
    descrNo = "don't validate data with shacl",
    short = 'h')
    
  val time = toggle("time",
    prefix = "no-",
    default = Some(false),
    descrYes = "show time",
    descrNo = "don't show time",
    short = 't')
    
  override protected def onError(e: Throwable) = onError(e, builder)

}
