package app.services;

import javax.inject.Singleton;
//import com.mongodb.MongoClient;
import com.mongodb.reactivestreams.client.MongoClient;
import com.mongodb.reactivestreams.client.MongoClients;


@Singleton
public class MongoClientProvider {
		
	private MongoClient mongoClient = null;

	public MongoClient get() {
		if (mongoClient == null){
			//mongoClient = new MongoClient("localhost", 27017);
			mongoClient = MongoClients.create();
			System.out.println("New mongo client created");
		}
				
		return mongoClient;
	}
		
}