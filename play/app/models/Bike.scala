package models

import play.api.libs.json.Json
import models.Coordinates.CoordBSONReader
import models.Coordinates.CoordBSONWriter
import models.Coordinates.coordFormat
import models.Coordinates

import play.api.libs.functional.syntax.functionalCanBuildApplicative
import play.api.libs.functional.syntax.toFunctionalBuilderOps
import reactivemongo.bson.BSONDocument
import reactivemongo.bson.BSONDocumentReader
import reactivemongo.bson.BSONDocumentWriter
import reactivemongo.bson.BSONObjectID
import reactivemongo.bson.BSONObjectIDIdentity
import reactivemongo.bson.BSONStringHandler
import reactivemongo.bson.BSONBinary
import reactivemongo.bson.Producer.nameValue2Producer
import play.modules.reactivemongo.json.BSONFormats.BSONObjectIDFormat

case class Bike(id: Option[BSONObjectID], coords: Coordinates, status:Boolean) 
// case class Bike(id: BSONObjectID, coords:Coordinates)

object Bike {
  /** serialize/Deserialize a Celebrity into/from JSON value */
  implicit val bikeFormat = Json.format[Bike]

  /** serialize a Celebrity into a BSON */
  implicit object BikeBSONWriter extends BSONDocumentWriter[Bike] {
    def write(bike: Bike): BSONDocument =
      BSONDocument(
        "_id" -> bike.id.getOrElse(BSONObjectID.generate),
        "coords" -> bike.coords,
        "status" -> bike.status)
  }

  /** deserialize a Celebrity from a BSON 
  implicit object BikeBSONReader extends BSONDocumentReader[Bike] {
    def read(doc: BSONDocument): Bike =
      Bike(
        doc.getAsTry[BSONObjectID]("_id"),
        doc.getAsTry[Coordinates]("coords").get )
  }
*/

  implicit object BikeBSONReader extends BSONDocumentReader[Bike] {
    def read(bson: BSONDocument): Bike = {
      val opt: Option[Bike] = for {
        id <- bson.getAs[BSONObjectID]("_id")
        coords <- bson.getAs[Coordinates]("coords")
        status <- bson.getAs[Boolean]("status")
      } yield new Bike(Option(id), coords, status)
      opt.get
    }

  } 

}
