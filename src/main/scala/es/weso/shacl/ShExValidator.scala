package es.weso.shacl

import com.hp.hpl.jena.rdf.model.Model
import com.hp.hpl.jena.util.FileUtils
import org.apache.jena.riot._
import java.io.ByteArrayOutputStream
import es.weso.rdf.jena.RDFAsJenaModel
 

object ShExValidator {
  
  lazy val webindexFile = "schemas/webindex.shex"
  lazy val (webindexSchema,pm) = Schema.fromFile(webindexFile,"SHEXC",None).get
  
  def validate(dataModel: Model): (Boolean,Long) = {
    
    val rdf = RDFAsJenaModel(dataModel)
    val validator = ShaclMatcher(webindexSchema,rdf)
    val startTime = System.nanoTime()
    val result = validator.validate
    val endTime = System.nanoTime()
    val time = endTime - startTime
    (result.isValid,time)
  }
  
  def result2Str(m: Model): String = {
    if (m.size == 0) {
      "Validated!"
    } else {
      model2Str(m)
    }
  }
  
  def model2Str(m: Model): String = {
    val out = new ByteArrayOutputStream
    RDFDataMgr.write(out,m,RDFFormat.TURTLE_PRETTY)
    out.toString       
  }


}