package com.example.demo.db;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import org.springframework.beans.factory.annotation.Autowired;

import com.example.demo.config.ConfigLoader;

public class MySQLClient {

    @Autowired
    private ConfigLoader configLoader;

    public void queryUsingJDBC() {
        String jdbcString = "jdbc:mysql://localhost:3306";
        String userName = configLoader.getDbUsername();
        String password = configLoader.getDbPassword();

        try (Connection connection = DriverManager.getConnection(jdbcString, userName, password);
                Statement statement = connection.createStatement();) {
            String query = "SELECT * FROM test.binarytree";
            ResultSet res = statement.executeQuery(query);
            
            while (res.next()) {
                int node = res.getInt("node");
                Integer parentNode = res.getObject("parent_node") != null ? res.getInt("parent_node") : null;
                
                System.out.println("Node: " + node);
                System.out.println("Parent Node: " + parentNode);

            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
