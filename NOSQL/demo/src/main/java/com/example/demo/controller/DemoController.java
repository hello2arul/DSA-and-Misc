package com.example.demo.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.beans.factory.annotation.Autowired;


import com.example.demo.db.MongoDBClient;
import com.example.demo.db.MySQLClient;

@RestController
@RequestMapping("/")
public class DemoController {

    @Autowired
    private MongoDBClient mongoDBClient;

    @Autowired
    private MySQLClient mySQLClient;

    @GetMapping("/test")
    public String sayHello() {
        return mongoDBClient.call();
    }

    @GetMapping("/testJDBC")
    public void testJDBC() {
        mySQLClient.queryUsingJDBC();
    }
}
