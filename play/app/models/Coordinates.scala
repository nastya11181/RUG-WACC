package models

import play.api.libs.json.Json
import play.api.libs.functional.syntax.functionalCanBuildApplicative
import play.api.libs.functional.syntax.toFunctionalBuilderOps
import reactivemongo.bson.BSONDocument
import reactivemongo.bson.BSONDocumentReader
import reactivemongo.bson.BSONDocumentWriter
import reactivemongo.bson.BSONStringHandler
import reactivemongo.bson.Producer.nameValue2Producer
import reactivemongo.bson.BSONNumberLike

case class Coordinates(x: Double, y: Double)

object Coordinates {
  /** serialize/deserialize a Name into/from JSON value */
  implicit val coordFormat = Json.format[Coordinates]

  /** serialize Coordinates into a BSON */
  implicit object CoordBSONWriter extends BSONDocumentWriter[Coordinates] {
    def write(coords: Coordinates): BSONDocument =
      BSONDocument(
        "x" -> coords.x,
        "y" -> coords.y)
  }


  implicit object CoordBSONReader extends BSONDocumentReader[Coordinates]{
    def read(bson: BSONDocument): Coordinates = {
      val opt: Option[Coordinates] = for {
        x <- bson.getAs[Double]("x") //.map(_.toDouble)
        y <- bson.getAs[Double]("y")
      } yield new Coordinates(x,y)
      
      opt.get     
    }
}

  /** deserialize Coordinates from a BSON 
  implicit object CoordBSONReader extends BSONDocumentReader[Coordinates] {
    def read(doc: BSONDocument): Coordinates =
      Coordinates(
        doc.getAs[BSONNumberLike]("x").map(_.toDouble),
        doc.getAs[BSONNumberLike]("y").map(_.toDouble))
  }
*/
}
