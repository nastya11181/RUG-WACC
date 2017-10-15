package controllers;

import java.util.Set;

import javax.ejb.EJB;
import javax.ejb.Stateless;

import com.mongodb.BasicDBObject;
import com.mongodb.DB;
import com.mongodb.DBCollection;
import com.mongodb.DBObject;
import com.mongodb.DBCursor;
import com.mongodb.MongoClient;
import com.mongodb.util.JSON;
import com.mongodb.client.FindIterable;

import java.net.UnknownHostException;
import java.util.Date;
import com.mongodb.BasicDBObject;
import com.mongodb.DB;
import com.mongodb.DBCollection;
import com.mongodb.DBCursor;
import com.mongodb.MongoClient;
import com.mongodb.MongoException;
import play.mvc.*;
import javax.inject.Inject;
import controllers.MongoClientProvider;


/**
 * Java + MongoDB Hello world Example
 * 
 */

public class App extends Controller {	
	
	private MongoClientProvider mongoClientProvider;
	
// constructor based injector	
	@Inject
	public App(MongoClientProvider mongoClientProvider){
		this.mongoClientProvider = mongoClientProvider; 
	}


	

	public Result getCollectionNames(){	
	
		/**** Get database ****/
		// if database doesn't exist, MongoDB will create it for you
		DB db = mongoClientProvider.get().getDB("uber");
		
		/**** Get collection / table from 'testdb' ****/
		// if collection doesn't exists, MongoDB will create it for you
		DBCollection table = db.getCollection("bikes");
		
		Set<String> colls = db.getCollectionNames();
		
		for (String s : colls) {
		    System.out.println(s);
		}	
		
		
		return ok(views.html.index.render());
					
	}
	
	public Result getAllBikes(){
		DB db = mongoClientProvider.get().getDB("uber");		
		DBCollection coll = db.getCollection("bikes");		
		DBCursor cursor = coll.find();
		while (cursor.hasNext()){
			System.out.println(cursor.next());
		}
//		subscriber = printDocumentSubscriber();
//		coll.find().subscribe(subscriber);
//		subscriber.awaitTerminalEvent();
		
		return ok(views.html.index.render());
	}
	
	public Result getAvailableBikes(){
		DB db = mongoClientProvider.get().getDB("uber");		
		DBCollection coll = db.getCollection("bikes");	
		BasicDBObject whereQuery = new BasicDBObject();
		whereQuery.put("status", "available");
		DBCursor cursor = coll.find(whereQuery);
		while (cursor.hasNext()){
			System.out.println(cursor.next());
		}
		return ok(views.html.index.render());
		
		
	}


	
	
}
