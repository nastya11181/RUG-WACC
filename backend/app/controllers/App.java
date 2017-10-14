package controllers;

import java.net.UnknownHostException;
import java.util.Date;
import com.mongodb.BasicDBObject;
import com.mongodb.DB;
import com.mongodb.DBCollection;
import com.mongodb.DBCursor;
import com.mongodb.MongoClient;
import com.mongodb.MongoException;
import play.mvc.*;

/**
 * Java + MongoDB Hello world Example
 * 
 */
public class App extends Controller {
	public Result index(){
		/**** Connect to MongoDB ****/
		MongoClient mongo = new MongoClient("localhost", 27017);

		/**** Get database ****/
		// if database doesn't exist, MongoDB will create it for you
		DB db = mongo.getDB("testdb");
		
		/**** Get collection / table from 'testdb' ****/
		// if collection doesn't exists, MongoDB will create it for you
		DBCollection table = db.getCollection("user");

		/**** Insert ****/
		// create a document to store key and value
		BasicDBObject document = new BasicDBObject();
		document.put("id", 1);
		document.put("x", 30.456);
		document.put("y", 65.345);
		table.insert(document);

		/**** Find and display ****/
		BasicDBObject searchQuery = new BasicDBObject();
		searchQuery.put("id", 1);

		DBCursor cursor = table.find(searchQuery);

		while (cursor.hasNext()) {
			System.out.println(cursor.next());
		}

		/**** Update ****/
		// search document where name="mkyong" and update it with new values
		BasicDBObject query = new BasicDBObject();
		query.put("id", 1);

		BasicDBObject newDocument = new BasicDBObject();
		newDocument.put("id", 100);

		BasicDBObject updateObj = new BasicDBObject();
		updateObj.put("$set", newDocument);

		table.update(query, updateObj);

		/**** Find and display ****/
		BasicDBObject searchQuery2 = new BasicDBObject().append("id", 100);

		DBCursor cursor2 = table.find(searchQuery2);

		while (cursor2.hasNext()) {
			System.out.println(cursor2.next());
		}

		/**** Done ****/
		System.out.println("Done");
		
		return ok(views.html.index.render());
					
	}
	
}
