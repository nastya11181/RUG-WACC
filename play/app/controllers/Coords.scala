package controllers

import scala.concurrent.ExecutionContext.Implicits.global

import models.Bike
import models.Bike.bikeFormat
import models.Bike.BikeBSONReader
import models.Bike.BikeBSONWriter
import models.Coordinates
import models.Coordinates.coordFormat
import models.Coordinates.CoordBSONWriter
import models.Coordinates.CoordBSONReader

import play.api.libs.json.Json
import play.api.mvc.Action
import play.api.mvc.Controller
import play.modules.reactivemongo.MongoController
import reactivemongo.api.collections.default.BSONCollection
import reactivemongo.bson.BSONDocument
import reactivemongo.bson.BSONDocumentIdentity
import reactivemongo.bson.BSONObjectID
import reactivemongo.bson.BSONObjectIDIdentity
import reactivemongo.bson.BSONStringHandler
import reactivemongo.bson.Producer.nameValue2Producer

object Coords extends Controller with MongoController {
  val collection = db[BSONCollection]("coords")

  /** list all bikes */
  def index = Action.async {
    val cursor = collection.find(
      BSONDocument(), BSONDocument()).cursor[Coordinates] // get all the fields of all the bikes
    val futureList = cursor.collect[List]() // convert it to a list of Bikes
    futureList.map { bikes => Ok(Json.toJson(bikes)) } // convert it to a JSON and return it
  }

}
