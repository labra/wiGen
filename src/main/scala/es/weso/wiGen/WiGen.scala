package es.weso.wiGen

import es.weso.rdf._
import es.weso.rdf.jena._
import es.weso.rdf.jena.RDFAsJenaModel
import es.weso.rdf.nodes._
import es.weso.rdf.triples._
import es.weso.rdf._
import org.scalactic.Requirements._

object WiGen {

  case class NumberElements(max: Int, incorrect: Int)

  def generate(
    allScopeNodes: Boolean,
    numCountries: Int,
    numDataSets: Int,
    numSlices: Int,
    numObs: Int,
    numComps: Int,
    numIndicators: Int,
    numOrgs: Int,
    rdf: RDFAsJenaModel,
    wrongCountries: Int = 0,
    wrongDataSets: Int = 0,
    wrongSlices: Int = 0,
    wrongObs: Int = 0,
    wrongComps: Int = 0,
    wrongIndicators: Int = 0,
    wrongOrgs: Int = 0): RDFAsJenaModel = {

    //TODO: Add better message when failing...
    require(wrongCountries <= numCountries, ". Number of bad Countries")
    require(wrongDataSets <= numDataSets, ". Number of bad DataSets")
    require(wrongSlices <= numSlices, ". Number of bad Slices")
    require(wrongObs <= numObs, ". Number of bad Observations")
    require(wrongComps <= numComps, ". Number of bad Computations")
    require(wrongIndicators <= numIndicators, ". Number of bad Indicators")
    require(wrongOrgs <= numOrgs, ". Number of bad Organizations")

    addPrefixes(rdf)

    val o = IRI("o")

    for (i <- 1 to numCountries) {
      val mkInvalid = i <= wrongCountries
      addCountry(i, mkInvalid, allScopeNodes)(rdf)
    }

    for (i <- 1 to numDataSets) {
      val mkInvalid = i <= wrongDataSets
      addDataSet(i, mkInvalid, numOrgs, allScopeNodes)(rdf)
    }
    for (i <- 1 to numSlices) {
      val mkInvalid = i <= wrongSlices
      addSlice(i, mkInvalid, numDataSets, numIndicators, allScopeNodes)(rdf)
    }
    for (i <- 1 to numObs) {
      val mkInvalid = i <= wrongObs
      addObservation(i, mkInvalid, numCountries, numDataSets, numSlices, numIndicators, numOrgs, allScopeNodes)(rdf)
    }
    for (i <- 1 to numIndicators) {
      val mkInvalid = i <= wrongIndicators
      addIndicator(i, mkInvalid, numOrgs, allScopeNodes)(rdf)
    }
    for (i <- 1 to numOrgs) {
      val mkInvalid = i <= wrongOrgs
      addOrganization(i, mkInvalid, allScopeNodes)(rdf)
    }

    if (!allScopeNodes) {
      // Add a single scope node to validate the first dataSet
      val dataset1 = mkNode(dataSetName, 1)
      addTriple(rdf, (ex_DataSet, sh_scopeNode, dataset1))
    }
    rdf
  }

  def addPrefixes(rdf: RDFBuilder): RDFBuilder = {
    rdf.addPrefix("", ex)
    rdf.addPrefix("sh", sh)
    rdf.addPrefix("xsd", xsd)
    rdf.addPrefix("rdf", rdf_)
    rdf.addPrefix("wf", "http://data.webfoundation.org#")
    rdf.addPrefix("rdfs", rdfs)
    rdf.addPrefix("qb", qb)
    rdf.addPrefix("cex", cex)
    rdf.addPrefix("dct", dct)
    rdf.addPrefix("skos", skos)
    rdf.addPrefix("foaf", foaf)
    rdf.addPrefix("org", org)
    rdf
  }

  val random = scala.util.Random

  val ex = "http://example.org/"
  val sh = "http://www.w3.org/ns/shacl#"
  val xsd = "http://www.w3.org/2001/XMLSchema#"
  val rdf_ = "http://www.w3.org/1999/02/22-rdf-syntax-ns#"
  val rdfs = "http://www.w3.org/2000/01/rdf-schema#"
  val qb = "http://purl.org/linked-data/cube#"
  val wf = "http://data.webfoundation.org#"
  val cex = "http://purl.org/weso/ontology/computex#"
  val dct = "http://purl.org/dc/terms/"
  val skos = "http://www.w3.org/2004/02/skos/core#"
  val foaf = "http://xmlns.com/foaf/0.1/"
  val org = "http://www.w3.org/ns/org#"

  val cex_computation = iri(cex, "computation")
  val cex_Computation = iri(cex, "Computation")
  val cex_value = iri(cex, "value")
  val cex_indicator = iri(cex, "indicator")
  val cex_ref_area = iri(cex, "ref-area")

  val dct_publisher = iri(dct, "publisher")
  val dct_issued = iri(dct, "issued")

  val ex_Country = iri(ex, "Country")
  val ex_DataSet = iri(ex, "DataSet")
  val ex_Slice = iri(ex, "Slice")
  val ex_Observation = iri(ex, "Observation")
  val ex_Indicator = iri(ex, "Indicator")
  val ex_Organization = iri(ex, "Organization")

  val foaf_homepage = iri(foaf, "homepage")

  val org_Organization = iri(org, "Organization")

  val qb_Observation = iri(qb, "Observation")
  val qb_observation = iri(qb, "observation")
  val qb_dataSet = iri(qb, "dataSet")
  val qb_DataSet = iri(qb, "DataSet")
  val qb_structure = iri(qb, "structure")
  val qb_slice = iri(qb, "slice")
  val qb_Slice = iri(qb, "Slice")
  val qb_sliceStructure = iri(qb, "sliceStructure")

  val rdf_type = iri(rdf_, "type")
  val rdfs_label = iri(rdfs, "label")

  val wf_Observation = iri(wf, "Observation")
  val wf_sliceByYear = iri(wf, "sliceByYear")
  val wf_PrimaryIndicator = iri(wf, "PrimaryIndicator")
  val wf_SecondaryIndicator = iri(wf, "SecondaryIndicator")
  val wf_WebFoundation = iri(wf, "WebFoundation")
  val wf_iso2 = iri(wf, "iso2")
  val wf_provider = iri(wf, "provider")
  val wf_source = iri(wf, "source")

  val wf_DSD = IRI(wf + "DSD")

  val xsd_integer = iri(xsd, "integer")
  val xsd_float = iri(xsd, "float")

  val xsd_dateTime = iri(xsd, "dateTime")

  val sh_scopeNode = IRI(sh + "scopeNode")

  val countryName = "country"
  val dataSetName = "dataSet"
  val sliceName = "slice"
  val obsName = "obs"
  val indicatorName = "indicator"
  val orgName = "org"

  def addCountry(n: Int, mkInvalid: Boolean, allScopeNodes: Boolean): RDFBuilder => (RDFNode, RDFBuilder) = { rdf =>
    val node = mkNode(countryName, n)
    addTripleString(rdf, (node, rdfs_label, countryName + n))
    addTripleString(rdf, (node, wf_iso2, "c" + n))

    if (mkInvalid) {
      // We add another wf_iso2 value...
      addTripleInteger(rdf, (node, wf_iso2, n))
    }
    if (allScopeNodes) {
      addTriple(rdf, (ex_Country, sh_scopeNode, node))
    }
    (node, rdf)
  }

  def addDataSet(n: Int,
                 mkInvalid: Boolean,
                 numOrgs: Int,
                 allScopeNodes: Boolean): RDFBuilder => (RDFNode, RDFBuilder) = { rdf =>
    val node = mkNode(dataSetName, n)
    addTriple(rdf, (node, rdf_type, qb_DataSet))
    addTriple(rdf, (node, qb_structure, wf_DSD))
    addTripleString(rdf, (node, rdfs_label, dataSetName + n))
    
    // Notice that slices are added when defining slice

    val randomOrg = randomNode(orgName, numOrgs)
    addTriple(rdf, (node, dct_publisher, randomOrg))

    if (allScopeNodes) {
      addTriple(rdf, (ex_DataSet, sh_scopeNode, node))
    }

    if (mkInvalid) { 
     // We make invalid dataSets by adding an extra rdfs_label
     addTripleInteger(rdf, (node, rdfs_label, n))
    }
    
    (node, rdf)
  }

  def addSlice(n: Int,
               mkInvalid: Boolean,
               numDataSets: Int,
               numIndicators: Int,
               allScopeNodes: Boolean): RDFBuilder => (RDFNode, RDFBuilder) = { rdf =>
    val node = mkNode(sliceName, n)
    addTriple(rdf, (node, rdf_type, qb_Slice))
    addTriple(rdf, (node, qb_sliceStructure, wf_sliceByYear))
    val indicator = randomNode(indicatorName, numIndicators)
    addTriple(rdf, (node, cex_indicator, indicator))

    // Associate with a dataSet
    val randomDataSet = randomNode(dataSetName, numDataSets)
    addTriple(rdf, (randomDataSet, qb_slice, node))

    // Observations are added to slices when defining observations
    
    if (mkInvalid) { 
     // We make invalid slices by adding an extra qb_sliceStructure declaration
     addTripleInteger(rdf, (node, qb_sliceStructure, n))
    }
    

    if (allScopeNodes) {
      addTriple(rdf, (ex_Slice, sh_scopeNode, node))
    }
    (node, rdf)
  }

  def addObservation(n: Int,
                     mkInvalid: Boolean,
                     numCountries: Int,
                     numDataSets: Int,
                     numSlices: Int,
                     numIndicators: Int,
                     numOrgs: Int,
                     allScopeNodes: Boolean): RDFBuilder => (RDFNode, RDFBuilder) = { rdf =>
    val node = mkNode(obsName, n)
    addTriple(rdf, (node, rdf_type, qb_Observation))
    addTriple(rdf, (node, rdf_type, wf_Observation))

    addTripleFloat(rdf, (node, cex_value, random.nextFloat()))
    addTripleString(rdf, (node, rdfs_label, obsName + n))
    addTripleDateTime(rdf, (node, dct_issued, "2015-05-30T09:00:00"))

    val addPublisher = random.nextBoolean()
    if (addPublisher) {
      addTriple(rdf, (node, dct_publisher, wf_WebFoundation))
    }

    val randomDataSet = randomNode(dataSetName, numDataSets)
    addTriple(rdf, (node, qb_dataSet, randomDataSet))

    // TODO: The following triples are not consistent
    // It would be better to search which are the slices of the dataSet and select 
    // one of them 
    val randomSlice = randomNode(sliceName, numSlices)
    addTriple(rdf, (randomDataSet, qb_slice, randomSlice))
    addTriple(rdf, (randomSlice, qb_observation, node))

    val randomCountry = randomNode(countryName, numCountries)
    addTriple(rdf, (node, cex_ref_area, randomCountry))

    val randomIndicator = randomNode(indicatorName, numIndicators)
    addTriple(rdf, (node, cex_indicator, randomIndicator))

    val isComputed = random.nextBoolean()
    if (isComputed) {
      val (computation, _) = rdf.createBNode
      addTriple(rdf, (computation, rdf_type, cex_Computation))
      addTriple(rdf, (node, cex_computation, computation))
    } else {
      addTriple(rdf, (node, wf_source, mkNode("source", n)))
    }
    
    if (mkInvalid) { 
     // We make invalid observations by adding an extra rdfs_label
     // TODO: There could be multiple ways to make invalid observations 
     //  which could be explored
     addTripleInteger(rdf, (node, rdfs_label, n))
    }

    if (allScopeNodes) {
      addTriple(rdf, (ex_Observation, sh_scopeNode, node))
    }
    (node, rdf)
  }

  def addIndicator(n: Int,
                   mkInvalid: Boolean,
                   numOrgs: Int,
                   allScopeNodes: Boolean): RDFBuilder => (RDFNode, RDFBuilder) = { rdf =>
    val node = mkNode(indicatorName, n)
    val isPrimary = random.nextBoolean()
    if (isPrimary) {
      addTriple(rdf, (node, rdf_type, wf_PrimaryIndicator))
    } else {
      addTriple(rdf, (node, rdf_type, wf_SecondaryIndicator))
    }
    addTripleString(rdf, (node, rdfs_label, indicatorName + n))
    val org = randomNode(orgName, numOrgs)
    addTriple(rdf, (node, wf_provider, org))

    if (mkInvalid) { 
    // We make invalid indicators by adding an extra rdfs_label
    addTripleInteger(rdf, (node, rdfs_label, n))
    }
    
    if (allScopeNodes) {
      addTriple(rdf, (ex_Indicator, sh_scopeNode, node))
    }

    (node, rdf)
  }

  def addOrganization(
    n: Int,
    mkInvalid: Boolean,
    allScopeNodes: Boolean): RDFBuilder => (RDFNode, RDFBuilder) = { rdf =>
    val node = mkNode(orgName, n)
    addTriple(rdf, (node, rdf_type, org_Organization))
    addTripleString(rdf, (node, rdfs_label, orgName + n))
    addTriple(rdf, (node, foaf_homepage, iri(ex, "homepageOrg" + n)))

    if (mkInvalid) { 
     // We make invalid organizations by adding an extra rdfs_label
     addTripleInteger(rdf, (node, rdfs_label, n))
    }
    
    if (allScopeNodes) {
      addTriple(rdf, (ex_Organization, sh_scopeNode, node))
    }
    (node, rdf)
  }

  def iri(base: String, localName: String) = {
    IRI(base + localName)
  }

  def mkNode(str: String, num: Int): IRI = {
    IRI(ex + (str + num))
  }

  def randomNode(str: String, num: Int): IRI = {
    IRI(ex + (str + (random.nextInt(num) + 1)))
  }

  /* TODO: Move the following definitions to SRDF */

  /**
   * addTriple
   */
  def addTriple(rdf: RDFBuilder,
                triple: (RDFNode, IRI, RDFNode)): RDFBuilder = {
    rdf.addTriple(RDFTriple(triple._1, triple._2, triple._3))
  }

  /**
   * addTriple with an string literal
   */
  def addTripleString(rdf: RDFBuilder, triple: (RDFNode, IRI, String)): RDFBuilder = {
    rdf.addTriple(RDFTriple(triple._1, triple._2, StringLiteral(triple._3)))
  }

  /**
   * addTriple with a float literal
   */
  def addTripleFloat(rdf: RDFBuilder, triple: (RDFNode, IRI, Float)): RDFBuilder = {
    val f: Float = triple._3
    val dt = DatatypeLiteral(f.toString, xsd_float)
    rdf.addTriple(
      RDFTriple(triple._1, triple._2, dt))
  }

  /**
   * addTriple with an integer literal
   */
  def addTripleInteger(rdf: RDFBuilder, triple: (RDFNode, IRI, Int)): RDFBuilder = {
    val f: Int = triple._3
    val dt = DatatypeLiteral(f.toString, xsd_integer)
    rdf.addTriple(
      RDFTriple(triple._1, triple._2, dt))
  }

  /**
   * addTriple with a dateTime literal
   */
  def addTripleDateTime(rdf: RDFBuilder, triple: (RDFNode, IRI, String)): RDFBuilder = {
    val dt = DatatypeLiteral(triple._3, xsd_dateTime)
    rdf.addTriple(
      RDFTriple(triple._1, triple._2, dt))
  }

}