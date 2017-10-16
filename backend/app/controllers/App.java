package app.controllers;

import java.util.Set;

import play.mvc.*;
import javax.inject.Inject;


import com.mongodb.reactivestreams.client.MongoClient;
import com.mongodb.reactivestreams.client.MongoClients;
import com.mongodb.reactivestreams.client.MongoCollection;
import com.mongodb.reactivestreams.client.MongoDatabase;
import org.bson.Document;
import static com.mongodb.client.model.Filters.eq;
import com.mongodb.client.model.UpdateOneModel;
import com.mongodb.client.result.UpdateResult;


import app.services.SubscriberHelpers.*;
import app.services.MongoClientProvider;



public class App extends Controller {	
	
	private MongoClientProvider mongoClientProvider;
	
	// constructor based injector	
	@Inject
	public App(MongoClientProvider mongoClientProvider){
		this.mongoClientProvider = mongoClientProvider; 
	}

	
	public Result setUnavailable(String id){
		MongoDatabase db = mongoClientProvider.get().getDatabase("uber");		
		MongoCollection<Document> coll = db.getCollection("bikes");
		
		coll.updateOne(eq("id", id), new Document("$set", new Document("status", "unavailable")))
			.subscribe(new PrintSubscriber<UpdateResult>("Update Result: %s"));
		return ok(views.html.index.render());
		
	}
	
	public Result getAvailableBikes(){
		MongoDatabase db = mongoClientProvider.get().getDatabase("uber");		
		MongoCollection<Document> coll = db.getCollection("bikes");
		coll.find(eq("status", "available")).subscribe(new PrintDocumentSubscriber());
		
		
		//BasicDBObject whereQuery = new BasicDBObject();
		//whereQuery.put("status", "available");
		//DBCursor cursor = coll.find(whereQuery);
		//while (cursor.hasNext()){
		//	System.out.println(cursor.next());
		//}
		return ok(views.html.index.render());
		
		
	}
	
//	public Result getAllBikes(){
//		MongoDatabase db = mongoClientProvider.get().getDatabase("uber");		
//		MongoCollection<Document> coll = db.getCollection("bikes");	
//		
//		DBCursor cursor = coll.find();
//		while (cursor.hasNext()){
//			System.out.println(cursor.next());
//		}
//
//		return ok(views.html.index.render());
//	}
	

	



	
	
}
