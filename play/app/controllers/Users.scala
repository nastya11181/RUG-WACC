package controllers

import scala.concurrent.ExecutionContext.Implicits.global

import models.User
import models.User.userFormat
import models.User.UserBSONReader
import models.User.UserBSONWriter
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
import play.modules.reactivemongo.json.BSONFormats._
import play.api.libs.json._

object Users extends Controller with MongoController {
  val collection = db[BSONCollection]("users")

  /** list all celebrities */
  def index = Action.async {
    val cursor = collection.find(
      BSONDocument(), BSONDocument()).cursor[User] // get all the fields of all the celebrities
    val futureList = cursor.collect[List]() // convert it to a list of Celebrity
    futureList.map { users => Ok(Json.toJson(users)) } // convert it to a JSON and return it
  }

  /** create a user from the given JSON */
  def create() = Action.async(parse.json) { request =>
    val username = request.body.\("username").toString().replace("\"", "")
    val firstname = request.body.\("firstname").toString().replace("\"", "")
    val lastname = request.body.\("lastname").toString().replace("\"", "")
    val email = request.body.\("email").toString().replace("\"", "")
    val password = request.body.\("password").toString().replace("\"", "")

    val user = User(Option(BSONObjectID.generate), username, firstname, lastname, email, password) // create the celebrity
    collection.insert(user).map(
      _ => Ok(Json.toJson(user))) // return the created celebrity in a JSON
  }


  /** retrieve the user for the given username/password as JSON */
  def show(username: String, password: String) = Action.async(parse.empty) { request =>
    // get the celebrity having this id (there will be 0 or 1 result)
    val futureUser = collection.find(BSONDocument("username" -> username, "password" -> password)).one[User]
    futureUser.map { user => Ok(Json.toJson(user)) }
  }



}
