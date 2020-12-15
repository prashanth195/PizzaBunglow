package com.mytectra.springboot.PizzaBunglow.MongoConfig;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.mongodb.config.AbstractMongoConfiguration;
import org.springframework.data.mongodb.core.MongoTemplate;

import com.mongodb.MongoClient;

//@Configuration
public class MongoConfig {

	 @Bean
	    public MongoClient mongo() {
	        return new MongoClient("localhost",27017);
	    }
	 
	    @Bean
	    public MongoTemplate mongoTemplate() throws Exception {
	       // return new MongoTemplate(mongo(), "test");
	    	return new MongoTemplate(mongo(),"pizzabunglow");
	    }
	    
	    
	/*@Override
	public MongoClient mongoClient() {
		// TODO Auto-generated method stub
		return new MongoClient("localhost",27017);
	}

	@Override
	protected String getDatabaseName() {
		// TODO Auto-generated method stub
		return "pizzabunglow";
	}*/

}
