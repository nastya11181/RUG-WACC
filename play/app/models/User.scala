package models

import play.api.libs.json.Json
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

case class User(id: Option[BSONObjectID], username: String, firstname: String, lastname: String, email: String, password: String) 
// case class Bike(id: BSONObjectID, coords:Coordinates)

object User {
  /** serialize/Deserialize a Celebrity into/from JSON value */
  implicit val userFormat = Json.format[User]

  /** serialize a Celebrity into a BSON */
  implicit object UserBSONWriter extends BSONDocumentWriter[User] {
    def write(user: User): BSONDocument =
      BSONDocument(
        "_id" -> user.id.getOrElse(BSONObjectID.generate),
        "username" -> user.username,
        "firstname" -> user.firstname,
        "lastname" -> user.lastname,
        "email" -> user.email,
        "password" -> user.password 
        )
  }

  /** deserialize a Celebrity from a BSON 
  implicit object BikeBSONReader extends BSONDocumentReader[Bike] {
    def read(doc: BSONDocument): Bike =
      Bike(
        doc.getAsTry[BSONObjectID]("_id"),
        doc.getAsTry[Coordinates]("coords").get )
  }
*/

  implicit object UserBSONReader extends BSONDocumentReader[User] {
    def read(bson: BSONDocument): User = {
      val opt: Option[User] = for {
        id <- bson.getAs[BSONObjectID]("_id")
        username <- bson.getAs[String]("username")
        firstname <- bson.getAs[String]("firstname")
        lastname <- bson.getAs[String]("lastname")
        email <- bson.getAs[String]("email")
        password <- bson.getAs[String]("password")
      } yield new User(Option(id), username, firstname, lastname, email, password)
      opt.get
    }

  } 

}
