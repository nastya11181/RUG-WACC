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

object Bikes extends Controller with MongoController {
  val collection = db[BSONCollection]("bikes")

  /** list all bikes */
  def index = Action.async {
    val cursor = collection.find(
      BSONDocument(), BSONDocument()).cursor[Bike] // get all the fields of all the bikes
    val futureList = cursor.collect[List]() // convert it to a list of Bikes
    futureList.map { bikes => Ok(Json.toJson(bikes)) } // convert it to a JSON and return it
  }

  /** create a bike from the given JSON */
  def create() = Action.async(parse.json) { request =>
    val coordJSON = request.body.\("coords")
    val coords = coordFormat.reads(coordJSON).get
    val status = true
    val bike = Bike(Option(BSONObjectID.generate), coords, status) // create the bike
    collection.insert(bike).map(
      _ => Ok(Json.toJson(bike))) // return the created bike in a JSON
  }

  /** retrieve the bike for the given id as JSON */
  def show(id: String) = Action.async(parse.empty) { request =>
    val objectID = new BSONObjectID(id) // get the corresponding BSONObjectID
    // get the bike having this id (there will be 0 or 1 result)
    val futureBike = collection.find(BSONDocument("_id" -> objectID)).one[Bike]
    futureBike.map { bike => Ok(Json.toJson(bike)) }
  }

  /** update the bike for the given id from the JSON body */
  def update(id: String) = Action.async(parse.json) { request =>
    val objectID = new BSONObjectID(id) // get the corresponding BSONObjectID
    val coordJSON = request.body.\("coords")
    val coords = coordFormat.reads(coordJSON).get

    val modifier = BSONDocument( // create the modifier bike
      "$set" -> BSONDocument(
        "coords" -> coords,
        "status" -> false))
    collection.update(BSONDocument("_id" -> objectID), modifier).map(
      _ => Ok(Json.toJson(Bike(Option(objectID), coords, false)))) // return the modified bike in a JSON
  }

  /** delete a bike for the given id */
  def delete(id: String) = Action.async(parse.empty) { request =>
    val objectID = new BSONObjectID(id) // get the corresponding BSONObjectID
    collection.remove(BSONDocument("_id" -> objectID)).map( // remove the bike
      _ => Ok(Json.obj())).recover { case _ => InternalServerError } // and return an empty JSON while recovering from errors if any


  }
}
