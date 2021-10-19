package com.kea.cinemaxx.configuration;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;

public class DBSetup {
    String username = "root";
    String password = "root";
    String url = "jdbc:mysql://52.207.193.24:3306";
    private Connection conn;
    String schemaName = "cinemaxxx";

    private void initializeDatabase(Connection conn) throws Exception{
        String sql = "CREATE DATABASE IF NOT EXISTS " + schemaName;
        Statement statement = conn.createStatement();
        statement.execute(sql);
        statement.execute("USE " + schemaName );
        System.out.println("done selecting schema");
    }

    public DBSetup(){
        connectAndQuery();
    }

    private void connectAndQuery(){
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        try{
            conn = DriverManager.getConnection(url, username,password);
            if(!conn.isClosed()){
                System.out.println("DB Conn ok ");
                initializeDatabase(conn);
            }
        }catch (Exception e){
            System.out.println("Error " + e.getMessage());
        }
    }
}
