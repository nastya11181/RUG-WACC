package controllers

import java.io.File
import play.api.libs.json.Json

import play.Play
import play.api.mvc.Action
import play.api.mvc.Controller

object Application extends Controller {
  /** serve the index page app/views/index.scala.html */
  def index(any: String) = Action {
    Ok(Json.obj())
    /**Ok("Nothing returned")*/
  }

  /** resolve "any" into the corresponding HTML page URI 
  def getURI(any: String): String = any match {
    case "main" => "/public/html/main.html"
    case "detail" => "/public/html/detail.html"
    case _ => "error"
  }

  /** load an HTML page from public/html */
  def loadPublicHTML(any: String) = Action {
    val projectRoot = Play.application().path()
    val file = new File(projectRoot + getURI(any))
    if (file.exists())
      Ok(scala.io.Source.fromFile(file.getCanonicalPath()).mkString).as("text/html");
    else
      NotFound
  }
  */
}
