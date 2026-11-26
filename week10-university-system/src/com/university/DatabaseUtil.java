package com.university;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

// This class is the "bridge" between the Java application and MySQL
public class DatabaseUtil {

    // "university_db" is the database we created in Part B
    private static final String URL = "jdbc:mysql://localhost:3306/university_db";

    // The default MySQL username
    private static final String USER = "root";

    // The password is read from an environment variable, so it never
    // appears in the code or on GitHub
    private static String password() {
        String p = System.getenv("UNI_DB_PASSWORD");
        return p == null ? "" : p;
    }

    // Returns a connection, or throws SQLException if it fails
    public static Connection getConnection() throws SQLException {
        return DriverManager.getConnection(URL, USER, password());
    }
}