package com.egen.code.challenge.dbaccess.config;

import org.mongodb.morphia.Datastore;
import org.mongodb.morphia.Morphia;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.mongodb.MongoClient;

@Configuration
public class MorphianConfigurationDataStore {

	@Autowired
    private MongoClient mongoClient;
	
	@Bean
    public Datastore datastore() {
        Morphia morphia = new Morphia();
        //morphia.mapPackage("com.egen.code.challenge.dbaccess.entity");
        return morphia.createDatastore(mongoClient, "code_challenge_db"); // "code_challenge_db" may come from properties?
    }
	
	
}
