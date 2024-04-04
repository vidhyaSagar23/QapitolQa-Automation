package com.dbtesting.jdbc;

import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBConnection {
   static Connection con=null;

    @BeforeMethod
    public void createConnection() {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            con = DriverManager.getConnection("jdbc:mysql://localhost:3306/qapitolqa_dbtesting?createDatabaseIfNotExist=true",
                    "root", "Sagarvp1323!");
            System.out.println("Connection established");
        } catch (ClassNotFoundException e) {
            System.out.println("Driver class failed to load");
        } catch (SQLException e) {
            System.out.println("Connection failed");
        }
    }

    @AfterMethod
    public void closeConnection(){
        try {
            con.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}