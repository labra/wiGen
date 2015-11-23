package es.weso.wiGen

import es.weso.rdf._
import es.weso.rdf.jena._
import es.weso.rdf.jena.RDFAsJenaModel
import es.weso.rdfgraph.nodes._
import es.weso.rdfgraph.statements._
import es.weso.rdfgraph._

object WiGen {
  def generate(
      numCountries: Int, 
      numDataSets: Int,
      numSlices: Int,
      numObs: Int,
      numComps: Int,
      numIndicators: Int,
      numOrgs: Int,
      rdf: RDFAsJenaModel): RDFAsJenaModel = {
    addPrefixes(rdf)
    val o = IRI("o")
    addTriple(rdf,(o,rdf_type,qb_Observation))
    addTriple(rdf,(o,rdf_type,wf_Observation))
    addTriple(rdf,(o,rdfs_label,StringLiteral("y")))
    rdf 
  }
  
  def addPrefixes(rdf: RDFBuilder): RDFBuilder = {
    rdf.addPrefix("","http://example.org/")
    rdf.addPrefix("sh", "http://www.w3.org/ns/shacl#")
    rdf.addPrefix("xsd","http://www.w3.org/2001/XMLSchema#")
    rdf.addPrefix("rdf",rdf_)
    rdf.addPrefix("wf","http://data.webfoundation.org#")
    rdf.addPrefix("rdfs",rdfs)
    rdf.addPrefix("qb",qb)
    rdf.addPrefix("cex","http://purl.org/weso/ontology/computex#")
    rdf.addPrefix("dct","http://purl.org/dc/terms/")
    rdf.addPrefix("skos","http://www.w3.org/2004/02/skos/core#")
    rdf.addPrefix("foaf","http://xmlns.com/foaf/0.1/")
    rdf.addPrefix("org","http://www.w3.org/ns/org#")
    rdf
  }

  val rdf_ = "http://www.w3.org/1999/02/22-rdf-syntax-ns#"
  val rdfs = "http://www.w3.org/2000/01/rdf-schema#"
  val qb = "http://purl.org/linked-data/cube#"
  val wf = "http://data.webfoundation.org#"

  val rdf_type = IRI(rdf_ + "type")
  val rdfs_label = IRI(rdfs + "label")
  val qb_Observation = IRI(qb + "observation")
  val wf_Observation = IRI(wf + "observation")
  
 /**
  * addTriple 
  */
 def addTriple(rdf: RDFBuilder, triple:(RDFNode, IRI, RDFNode)): RDFBuilder = {
  rdf.addTriple(RDFTriple(triple._1,triple._2,triple._3))
 }

  
}