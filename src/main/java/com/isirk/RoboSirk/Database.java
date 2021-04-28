package com.isirk.RoboSirk;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

/*
 * Still Working on the database
 */

public class Database {
    public static void main(String[] args) throws SQLException {
        String url = "jdbc:postgresql://localhost/test";
        Properties props = new Properties();
        props.setProperty("user","isirk");
        props.setProperty("password","pwd");
        props.setProperty("ssl","true");
        Connection conn = DriverManager.getConnection(url, props);
    }
}
