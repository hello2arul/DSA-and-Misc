package com.example.demo.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.example.demo.db.MongoDBClient;

@Configuration
public class SpringConfig {
    
    @Bean
    public MongoDBClient mongoDBClient() {
        return new MongoDBClient();
    }
}
