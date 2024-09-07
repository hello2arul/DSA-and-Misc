package com.example.demo.db;

import org.bson.Document;
import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import com.mongodb.client.model.Filters;

public class MongoDBClient {
    public String call() {
        // Replace the placeholder with your MongoDB deployment's connection string
        String uri = "mongodb://localhost:27017";
        try (MongoClient mongoClient = MongoClients.create(uri)) {
            MongoDatabase database = mongoClient.getDatabase("bookstore");
            MongoCollection<Document> collection = database.getCollection("books");
            Document doc = collection.find(Filters.eq("title", "Back to the Future")).first();
            
            if (doc != null) {
                return doc.toJson();
            } else {
                return "No matching documents found.";
            }
        }
    }
}
