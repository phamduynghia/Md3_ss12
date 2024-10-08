package com.ra.database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnectionDB {

    private static final String DRIVER = "com.mysql.cj.jdbc.Driver";
    private static final String URL = "jdbc:mysql://localhost:3306/s12b2";
    private static final String USER = "root";
    private static final String PASSWORD = "duong123";

    public static Connection getConnection() {
        try {
            Class.forName(DRIVER);
            Connection conn= DriverManager.getConnection(URL,USER,PASSWORD);
            return conn;
        }
        catch (SQLException | ClassNotFoundException e) {
            throw new RuntimeException(e);
        }


    }
    public static void closeConnection(Connection conn) {
        try {
            if (!conn.isClosed()) {
                conn.close();
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }


}
