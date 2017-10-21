package jobs

import play.jobs.*

import models.Bike
import models.Bike.bikeFormat
import models.Bike.BikeBSONReader
import models.Bike.BikeBSONWriter
import reactivemongo.bson.BSONDocument


@Every("15s")
class UpdateDB extends Job {
  override def doJob(){
    val collection = db[BSONCollection]("bikes")
    val cursor = collection.find(BSONDocument()).cursor[Bike] // get all the fields of all the bikes
      val futureList = cursor.collect[List]() 
      for (bike <- futureList){
        bike.map { data => 
        println(Json.toJson(data))}
      }
  }
}
