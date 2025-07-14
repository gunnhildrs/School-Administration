package model;

import java.sql.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import javax.swing.table.DefaultTableModel;

public class StudentModel {
    
    // Get all students
    public static DefaultTableModel getAllStudents() {
        DefaultTableModel tableModel = new DefaultTableModel();
        tableModel.addColumn("No");
        tableModel.addColumn("NISN");
        tableModel.addColumn("NIS");
        tableModel.addColumn("NAMA");
        tableModel.addColumn("Tempat Tanggal Lahir");
        tableModel.addColumn("Alamat");
        tableModel.addColumn("Tahun Lulus");
        
        try {
            String query = "SELECT * FROM students ORDER BY nama ASC";
            ResultSet rs = DatabaseConnectionDS.executeQuery(query);
            
            int no = 1;
            while (rs.next()) {
                Object[] row = {
                    no++,
                    rs.getString("nisn"),
                    rs.getString("nis"),
                    rs.getString("nama"),
                    rs.getString("tempat_lahir") + ", " + rs.getString("tanggal_lahir"),
                    rs.getString("alamat"),
                    rs.getString("tahun_lulus")
                };
                tableModel.addRow(row);
            }
            
            rs.close();
            
        } catch (SQLException e) {
            System.err.println("Error retrieving student data: " + e.getMessage());
        }
        
        return tableModel;
    }
    
    // Get students by graduation year
    public static DefaultTableModel getStudentsByYear(String year) {
        DefaultTableModel tableModel = new DefaultTableModel();
        tableModel.addColumn("No");
        tableModel.addColumn("NISN");
        tableModel.addColumn("NIS");
        tableModel.addColumn("NAMA");
        tableModel.addColumn("Tempat Tanggal Lahir");
        tableModel.addColumn("Alamat");
        tableModel.addColumn("Tahun Lulus");
        
        try {
            String query = "SELECT * FROM students WHERE tahun_lulus = '" + year + "' ORDER BY nama ASC";
            ResultSet rs = DatabaseConnectionDS.executeQuery(query);
            
            int no = 1;
            while (rs.next()) {
                Object[] row = {
                    no++,
                    rs.getString("nisn"),
                    rs.getString("nis"),
                    rs.getString("nama"),
                    rs.getString("tempat_lahir") + ", " + rs.getString("tanggal_lahir"),
                    rs.getString("alamat"),
                    rs.getString("tahun_lulus")
                };
                tableModel.addRow(row);
            }
            
            rs.close();
            
        } catch (SQLException e) {
            System.err.println("Error retrieving student data: " + e.getMessage());
        }
        
        return tableModel;
    }
    
    // Search students by keyword
    public static DefaultTableModel searchStudents(String keyword) {
        DefaultTableModel tableModel = new DefaultTableModel();
        tableModel.addColumn("No");
        tableModel.addColumn("NISN");
        tableModel.addColumn("NIS");
        tableModel.addColumn("NAMA");
        tableModel.addColumn("Tempat Tanggal Lahir");
        tableModel.addColumn("Alamat");
        tableModel.addColumn("Tahun Lulus");
        
        try {
            String query = "SELECT * FROM students WHERE " +
                           "nisn LIKE '%" + keyword + "%' OR " +
                           "nis LIKE '%" + keyword + "%' OR " +
                           "nama LIKE '%" + keyword + "%' OR " +
                           "tempat_lahir LIKE '%" + keyword + "%' OR " +
                           "alamat LIKE '%" + keyword + "%' OR " +
                           "tahun_lulus LIKE '%" + keyword + "%' " +
                           "ORDER BY nama ASC";
            ResultSet rs = DatabaseConnectionDS.executeQuery(query);
            
            int no = 1;
            while (rs.next()) {
                Object[] row = {
                    no++,
                    rs.getString("nisn"),
                    rs.getString("nis"),
                    rs.getString("nama"),
                    rs.getString("tempat_lahir") + ", " + rs.getString("tanggal_lahir"),
                    rs.getString("alamat"),
                    rs.getString("tahun_lulus")
                };
                tableModel.addRow(row);
            }
            
            rs.close();
            
        } catch (SQLException e) {
            System.err.println("Error searching student data: " + e.getMessage());
        }
        
        return tableModel;
    }
    
    // Add a new student
    public static boolean addStudent(String nisn, String nis, String nama, String tempatLahir, 
                                  String tanggalLahir, String alamat, String tahunLulus) {
        try {
            String query = "INSERT INTO students (nisn, nis, nama, tempat_lahir, tanggal_lahir, alamat, tahun_lulus) " +
                           "VALUES ('" + nisn + "', '" + nis + "', '" + nama + "', '" + tempatLahir + "', '" + 
                           tanggalLahir + "', '" + alamat + "', '" + tahunLulus + "')";
            
            return DatabaseConnectionDS.executeUpdate(query) > 0;
            
        } catch (Exception e) {
            System.err.println("Error adding student: " + e.getMessage());
            return false;
        }
    }
    
    // Update an existing student
    public static boolean updateStudent(String nisn, String nis, String nama, String tempatLahir, 
                                     String tanggalLahir, String alamat, String tahunLulus) {
        try {
            String query = "UPDATE students SET " +
                           "nis = '" + nis + "', " +
                           "nama = '" + nama + "', " +
                           "tempat_lahir = '" + tempatLahir + "', " +
                           "tanggal_lahir = '" + tanggalLahir + "', " +
                           "alamat = '" + alamat + "', " +
                           "tahun_lulus = '" + tahunLulus + "' " +
                           "WHERE nisn = '" + nisn + "'";
            
            return DatabaseConnectionDS.executeUpdate(query) > 0;
            
        } catch (Exception e) {
            System.err.println("Error updating student: " + e.getMessage());
            return false;
        }
    }
    
    // Delete a student
    public static boolean deleteStudent(String nisn) {
        try {
            String query = "DELETE FROM students WHERE nisn = '" + nisn + "'";
            
            return DatabaseConnectionDS.executeUpdate(query) > 0;
            
        } catch (Exception e) {
            System.err.println("Error deleting student: " + e.getMessage());
            return false;
        }
    }
    
    // Get student gender statistics
    public static Map<String, Integer> getGenderStatistics() {
        Map<String, Integer> stats = new HashMap<>();
        stats.put("male", 0);
        stats.put("female", 0);
        
        try {
            String query = "SELECT gender, COUNT(*) as count FROM students GROUP BY gender";
            ResultSet rs = DatabaseConnectionDS.executeQuery(query);
            
            while (rs.next()) {
                String gender = rs.getString("gender");
                int count = rs.getInt("count");
                
                if (gender.equalsIgnoreCase("L")) {
                    stats.put("male", count);
                } else if (gender.equalsIgnoreCase("P")) {
                    stats.put("female", count);
                }
            }
            
            rs.close();
            
        } catch (SQLException e) {
            System.err.println("Error getting gender statistics: " + e.getMessage());
        }
        
        return stats;
    }
    
    // Get total number of students
    public static int getTotalStudents() {
        int total = 0;
        
        try {
            String query = "SELECT COUNT(*) as total FROM students";
            ResultSet rs = DatabaseConnectionDS.executeQuery(query);
            
            if (rs.next()) {
                total = rs.getInt("total");
            }
            
            rs.close();
            
        } catch (SQLException e) {
            System.err.println("Error getting total students: " + e.getMessage());
        }
        
        return total;
    }
    
    // Export data to Excel
    public static boolean exportToExcel(String filePath) {
        // Implementation would require a library like Apache POI
        // This is a placeholder for the actual implementation
        return false;
    }
}