package model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class DatabaseConnection {
    
    // Database configuration
    private static final String DB_URL = "jdbc:mysql://localhost:3306/tiara_school";
    private static final String DB_USERNAME = "root";
    private static final String DB_PASSWORD = "";
    
    // Method untuk mendapatkan koneksi database
    public static Connection getConnection() throws SQLException {
        try {
            // Load MySQL JDBC driver (versi baru)
            Class.forName("com.mysql.cj.jdbc.Driver");
            
            // Tambahkan parameter untuk timezone
            String url = DB_URL + "?useSSL=false&serverTimezone=UTC&allowPublicKeyRetrieval=true";
            return DriverManager.getConnection(url, DB_USERNAME, DB_PASSWORD);
            
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
                    return count > 0;
                }
            }
            
        } catch (SQLException e) {
            System.err.println("Error saat validasi user: " + e.getMessage());
            e.printStackTrace();
        }
        
        return false;
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
}