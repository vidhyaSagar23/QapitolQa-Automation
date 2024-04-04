package com.dbtesting.jdbc;

import org.testng.annotations.Test;

import java.sql.*;

public class Runner extends DBConnection{

    @Test
    public void createTable(){
        final String QUERY="CREATE TABLE IF NOT EXISTS Employee (id int(5) primary key AUTO_INCREMENT, " +
                "name varchar(25) not null, age int(3) not null)";
        try {
            Statement stmt=con.createStatement();
            stmt.executeUpdate(QUERY);
            System.out.println("Table Created Successfully");
            stmt.close();
        }
        catch (SQLException e) {
            System.out.println("Table Not created");
        }
    }

    @Test
    public void insertData() {
        final String QUERY="INSERT INTO Employee (name,age) values (?,?)";
        try {
            PreparedStatement stmt=con.prepareStatement(QUERY);
            stmt.setString(1,"sagar");
            stmt.setInt(2,22);
            int res=stmt.executeUpdate();
            System.out.println(res+" rows affected");
            stmt.close();
        }
        catch (SQLException e) {
            System.out.println("Data Not Stored");
        }
    }

    @Test
    public void display() {
        final String QUERY="SELECT * FROM Employee";
        try {
            Statement stmt=con.createStatement();
            ResultSet rs=  stmt.executeQuery(QUERY);
                while (rs.next()){
                    int id=rs.getInt("id");
                    String name=rs.getString("name");
                    int age=rs.getInt("age");
                    System.out.println(id+" "+name+" "+age);
                }
        } catch (SQLException e) {
            System.out.println("Can't execute the query");
        }
    }

    @Test
    public void delete() {
        final String QUERY="DELETE FROM Employee where id = ?";
        try{
            PreparedStatement stmt=con.prepareStatement(QUERY);
            stmt.setInt(1,1);
            int result=stmt.executeUpdate();
            if (result != 0){
                System.out.println(result+" rows affected");
            }
            else {
                System.out.println("No records Found");
            }
            stmt.close();
        }
        catch (SQLException e) {
            System.out.println("Cannot execute query");
        }
    }

    @Test
    public void update() {
        final String QUERY="UPDATE EMPLOYEE SET name = ?, age = ? where id = ?";
        try {
            PreparedStatement stmt=con.prepareStatement(QUERY);
            stmt.setString(1,"vidhyasagar");
            stmt.setInt(2,23);
            stmt.setInt(3,3);
            int result=stmt.executeUpdate();
            System.out.println("updated successfully "+result +" record");
            stmt.close();
        } catch (SQLException e) {
            System.out.println("Can't Execute Query");
        }
    }
}
