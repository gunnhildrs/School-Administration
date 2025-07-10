package model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class DatabaseConnection {
    
    // Database configuration
    private static final String DB_URL = "jdbc:mysql://localhost:3306/tiara_school";
    private static final String DB_USERNAME = "root"; // Ganti dengan username database Anda
    private static final String DB_PASSWORD = ""; // Ganti dengan password database Anda
    
    // Method untuk mendapatkan koneksi database
    public static Connection getConnection() throws SQLException {
        try {
            // Load MySQL JDBC driver
            Class.forName("com.mysql.cj.jdbc.Driver");
            return DriverManager.getConnection(DB_URL, DB_USERNAME, DB_PASSWORD);
        } catch (ClassNotFoundException e) {
            throw new SQLException("MySQL JDBC Driver tidak ditemukan", e);
        }
    }
    
    // Method untuk validasi user login
    public static boolean validateUser(String idAccess, String password) {
        String query = "SELECT COUNT(*) FROM users WHERE id_access = ? AND password = ?";
        
        try (Connection connection = getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            
            preparedStatement.setString(1, idAccess);
            preparedStatement.setString(2, password);
            
            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                if (resultSet.next()) {
                    int count = resultSet.getInt(1);
                    return count > 0; // Return true jika user ditemukan
                }
            }
            
        } catch (SQLException e) {
            System.err.println("Error saat validasi user: " + e.getMessage());
            e.printStackTrace();
        }
        
        return false; // Return false jika terjadi error atau user tidak ditemukan
    }
    
    // Method untuk mendapatkan informasi user (opsional)
    public static String getUserInfo(String idAccess) {
        String query = "SELECT id_access FROM users WHERE id_access = ?";
        
        try (Connection connection = getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            
            preparedStatement.setString(1, idAccess);
            
            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                if (resultSet.next()) {
                    return resultSet.getString("id_access");
                }
            }
            
        } catch (SQLException e) {
            System.err.println("Error saat mengambil info user: " + e.getMessage());
            e.printStackTrace();
        }
        
        return null;
    }
    
    // Method untuk test koneksi database
    public static boolean testConnection() {
        try (Connection connection = getConnection()) {
            return connection != null && !connection.isClosed();
        } catch (SQLException e) {
            System.err.println("Error saat test koneksi: " + e.getMessage());
            return false;
        }
    }
    
    // Method untuk menutup koneksi (cleanup)
    public static void closeConnection(Connection connection) {
        if (connection != null) {
            try {
                connection.close();
            } catch (SQLException e) {
                System.err.println("Error saat menutup koneksi: " + e.getMessage());
            }
        }
    }
}