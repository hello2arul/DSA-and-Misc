package com.example.demo.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.example.demo.db.MongoDBClient;
import com.example.demo.db.MySQLClient;

@Configuration
public class SpringConfig {
    
    @Bean
    public MongoDBClient mongoDBClient() {
        return new MongoDBClient();
    }

    @Bean
    public ConfigLoader configLoader() {
        return new ConfigLoader("application.properties");
    }

    @Bean
    public MySQLClient mySQLClient() {
        return new MySQLClient();
    }
}
