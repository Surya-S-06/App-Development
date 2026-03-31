package com.seatbooking;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 * DBConnection.java
 * ------------------
 * Utility class for getting a MySQL database connection using JDBC.
 * All servlets call DBConnection.getConnection() to get a Connection object.
 */
public class DBConnection {

    // ── Database Credentials ──────────────────────────────────────────────
    private static final String DB_URL  = "jdbc:mysql://localhost:3306/seat_booking_db";
    private static final String DB_USER = "root";
    private static final String DB_PASS = "surya@2006";

    /**
     * Returns a new MySQL Connection.
     * Always close the connection in a finally block after use.
     *
     * @return Connection object
     * @throws SQLException if connection fails
     */
    public static Connection getConnection() throws SQLException {
        try {
            // Load the MySQL JDBC driver
            Class.forName("com.mysql.cj.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            throw new SQLException("MySQL JDBC Driver not found. Add mysql-connector-j.jar to /WEB-INF/lib/", e);
        }
        // Create and return the connection
        return DriverManager.getConnection(DB_URL, DB_USER, DB_PASS);
    }
}
