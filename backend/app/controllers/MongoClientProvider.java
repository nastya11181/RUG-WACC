package controllers;

import javax.inject.Singleton;

import com.mongodb.MongoClient;
import com.mongodb.async.client.MongoClients;

@Singleton
public class MongoClientProvider {
		
	private MongoClient mongoClient = null;

	public MongoClient get() {
		if (mongoClient == null){
			mongoClient = new MongoClient("localhost", 27017);
			//mongoClient = MongoClients.create();
			System.out.println("New mongo client created");
		}
				
		return mongoClient;
	}
		
}