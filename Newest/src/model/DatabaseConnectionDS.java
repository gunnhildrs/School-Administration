package model;

import java.sql.*;
import javax.swing.JOptionPane;

public class DatabaseConnectionDS {
    private static final String JDBC_DRIVER = "com.mysql.jdbc.Driver";
    private static final String DB_URL = "jdbc:mysql://localhost:3306/tiara_school_db";
    private static final String USER = "root";
    private static final String PASS = "";
    
    private static Connection connection;
    
    public static Connection getConnection() {
        try {
            if (connection == null || connection.isClosed()) {
                Class.forName(JDBC_DRIVER);
                connection = DriverManager.getConnection(DB_URL, USER, PASS);
                System.out.println("Database connection established");
            }
        } catch (ClassNotFoundException e) {
            JOptionPane.showMessageDialog(null, "JDBC Driver not found: " + e.getMessage());
            System.exit(1);
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Database connection failed: " + e.getMessage());
            System.exit(1);
        }
        
        return connection;
    }
    
    public static void closeConnection() {
        try {
            if (connection != null && !connection.isClosed()) {
                connection.close();
                System.out.println("Database connection closed");
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Error closing database connection: " + e.getMessage());
        }
    }
    
    // Method to execute SELECT queries
    public static ResultSet executeQuery(String query) {
        ResultSet rs = null;
        try {
            Statement stmt = getConnection().createStatement();
            rs = stmt.executeQuery(query);
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Error executing query: " + e.getMessage());
        }
        return rs;
    }
    
    // Method to execute INSERT, UPDATE, DELETE queries
    public static int executeUpdate(String query) {
        int result = 0;
        try {
            Statement stmt = getConnection().createStatement();
            result = stmt.executeUpdate(query);
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Error executing update: " + e.getMessage());
        }
        return result;
    }
}