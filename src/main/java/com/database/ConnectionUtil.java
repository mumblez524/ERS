package com.database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnectionUtil {

    public static Connection getConnection() {
        // Obtain all the information to connect JDBC
        Connection con = null;
        String driverClass = "oracle.jdbc.driver.OracleDriver";
        String dbURL = "jdbc:oracle:thin:@project1database.cap5i9plbaly.us-east-2.rds.amazonaws.com:1521:ORCL";
        String dbMyUserName = "admin";
        String dbMyPass = "3artwItch";

        try {
            Class.forName(driverClass);
            con = DriverManager.getConnection(dbURL, dbMyUserName, dbMyPass);
        } catch (ClassNotFoundException | SQLException e) {
            System.out.println("FAIL");
            e.printStackTrace();
        }
        return con;
    }

}