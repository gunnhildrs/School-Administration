/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package view;
import java.awt.Color;
import java.awt.Cursor;
import java.awt.Image;
import java.sql.*;
import java.util.HashMap;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import model.DatabaseConnection;
import javax.swing.table.TableColumn;
import javax.swing.table.TableColumnModel;
import java.awt.FontMetrics;
import java.io.File;
import java.io.InputStream;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.SwingUtilities;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.util.JRLoader;
import net.sf.jasperreports.view.JasperViewer;
/**
 *
 * @author Ahmad Irsyad Rosyadi
 */

public class Rapor extends javax.swing.JFrame {
    
    public Rapor() {
    try {
        ImageIcon appIcon = new ImageIcon(home.class.getResource("/photos/logo.png"));
        setIconImage(appIcon.getImage());
    } catch (Exception e) {
        System.out.println("Could not load application icon");
    }
    
    initComponents();
    this.setExtendedState(JFrame.MAXIMIZED_BOTH);
    
    // Inisialisasi tabel dengan struktur RAPOR
    DefaultTableModel model = new DefaultTableModel();
    model.setColumnIdentifiers(new String[]{
        "NIS", "Kelas", "Semester", "Tahun Ajaran", "Jumlah Sakit", 
        "Jumlah Izin", "Jumlah Alpa", "Catatan Wali Kelas", "Tanggal Rapor", 
        "Status Naik"
    });
    jTable1.setModel(model);
    
    // Inisialisasi tabel dengan struktur NILAI
    DefaultTableModel nilaiModel = new DefaultTableModel();
    nilaiModel.setColumnIdentifiers(new String[]{
        "NIS", "ID Pelajaran", "Nama Pelajaran", "ID Kelas", "Semester", 
        "Tahun Ajaran", "Nilai Harian", "Nilai UTS", "Nilai UAS", 
        "Nilai Tugas", "Nilai Akhir", "Grade"
    });
    jTable2.setModel(nilaiModel);
    
    // PENGATURAN MANUAL UNTUK TABEL
    jTable1.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
    jTable1.getTableHeader().setReorderingAllowed(false);
    jTable1.setRowHeight(25);
    
    jTable2.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
    jTable2.getTableHeader().setReorderingAllowed(false);
    jTable2.setRowHeight(25);
    
    // Kustomisasi header tabel
    customizeTableHeader();
    customizeNilaiTableHeader();
    
    aktif();
    kosong();
    loadDataToTable();
    addComponentListener(new java.awt.event.ComponentAdapter() {
        @Override
        public void componentShown(java.awt.event.ComponentEvent evt) {
            SwingUtilities.invokeLater(() -> {
                setManualColumnWidths();
                setManualColumnWidthsForNilaiTable();
            });
        }
    });
}

private void customizeNilaiTableHeader() {
    javax.swing.table.JTableHeader header = jTable2.getTableHeader();
    header.setFont(new java.awt.Font("Tahoma", java.awt.Font.BOLD, 16));
    header.setBackground(new java.awt.Color(15, 65, 67));
    header.setForeground(new java.awt.Color(245, 244, 230));
    header.setPreferredSize(new java.awt.Dimension(header.getWidth(), 40));
    header.setReorderingAllowed(false);
    header.setResizingAllowed(false);
    header.setDefaultRenderer(new CustomHeaderRenderer());
}
    
    protected void aktif() {
    jTextField1.requestFocus();
    jTextField1.setEnabled(true);
    }
    protected void kosong() {
    jTextField1.setText("");
     } 
    
    private void setManualColumnWidths() {
    // Gunakan SwingUtilities.invokeLater untuk memastikan tabel sudah ter-render
    SwingUtilities.invokeLater(() -> {
        TableColumnModel columnModel = jTable1.getColumnModel();
        
        // ATUR LEBAR SETIAP KOLOM SECARA MANUAL (dalam pixel) untuk tabel rapor
        int[] columnWidths = {
            100,  // NIS
            300,  // Nama
            100,  // Semester
            80,  // Tahun Ajaran
            110,  // Jumlah Sakit
            50,  // Jumlah Izin
            50,  // Jumlah Alpa
            50,  // Catatan Wali Kelas - lebih lebar
            850,  // Tanggal Rapor
            120  // Status Naik
        };
        
        // Terapkan lebar ke setiap kolom
        for (int i = 0; i < columnWidths.length && i < columnModel.getColumnCount(); i++) {
            TableColumn column = columnModel.getColumn(i);
            column.setPreferredWidth(columnWidths[i]);
            column.setMinWidth(50);
            column.setMaxWidth(columnWidths[i] * 2);
        }
        
        jTable1.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
        jTable1.revalidate();
        jTable1.repaint();
    });
}
    
protected void loadDataToTable() {
    try {
        Connection conn = getConnection();
        if (conn != null) {
            String query = "SELECT * FROM rapor";
            PreparedStatement pst = conn.prepareStatement(query);
            ResultSet rs = pst.executeQuery();
            
            // Clear existing data
            DefaultTableModel model = (DefaultTableModel) jTable1.getModel();
            model.setRowCount(0);
            
            // Set column headers sesuai struktur database rapor
            model.setColumnIdentifiers(new String[]{
                "NIS","Nama", "Kelas", "Semester", "Tahun Ajaran", "Sakit", 
                "Izin", "Alpa", "Catatan Wali Kelas", "Status Naik"
            });
            
            // Add data to table
            while (rs.next()) {
                Object[] row = {
                    rs.getString("NIS"),
                    rs.getString("Nama"),
                    rs.getString("nama_kelas"),
                    rs.getString("semester"),
                    rs.getString("tahun_ajaran"),
                    rs.getInt("jumlah_sakit"),
                    rs.getInt("jumlah_izin"),
                    rs.getInt("jumlah_alpa"),
                    rs.getString("catatan_wali_kelas"),
                    rs.getString("status_naik")
                };
                model.addRow(row);
            }
            
            conn.close();
            
            SwingUtilities.invokeLater(() -> {
                setManualColumnWidths();
            });
        }
    } catch (SQLException e) {
        JOptionPane.showMessageDialog(this, "Error loading data: " + e.getMessage());
    }
}

protected void loadNilaiDataToTable(String nis) {
    try {
        Connection conn = getConnection();
        if (conn != null) {
            String query = "SELECT * FROM nilai WHERE NIS = ?";
            PreparedStatement pst = conn.prepareStatement(query);
            pst.setString(1, nis);
            ResultSet rs = pst.executeQuery();
            
            // Clear existing data
            DefaultTableModel model = (DefaultTableModel) jTable2.getModel();
            model.setRowCount(0);
            
            // Set column headers sesuai struktur database nilai
            model.setColumnIdentifiers(new String[]{
                "NIS", "Pelajaran", "Kelas", "Semester", 
                "Tahun Ajaran", "Nilai", "Grade"
            });
            
            // Add data to table
            while (rs.next()) {
                Object[] row = {
                    rs.getString("NIS"),
                    rs.getString("nama_pelajaran"),
                    rs.getString("id_kelas"),
                    rs.getString("semester"),
                    rs.getString("tahun_ajaran"),
                    rs.getBigDecimal("nilai_akhir"),
                    rs.getString("grade")
                };
                model.addRow(row);
            }
            
            conn.close();
            
            // Atur lebar kolom untuk jTable2
            SwingUtilities.invokeLater(() -> {
                setManualColumnWidthsForNilaiTable();
            });
        }
    } catch (SQLException e) {
        JOptionPane.showMessageDialog(this, "Error loading nilai data: " + e.getMessage());
    }
}

private void setManualColumnWidthsForNilaiTable() {
    SwingUtilities.invokeLater(() -> {
        TableColumnModel columnModel = jTable2.getColumnModel();
        
        // ATUR LEBAR SETIAP KOLOM SECARA MANUAL (dalam pixel) untuk tabel nilai
        int[] columnWidths = {
            100,  // NIS
            350,  // Nama Pelajaran
            100,  // ID Kelas
            80,   // Semester
            120,  // Tahun Ajaran
            80,   // Nilai Akhir
            80    // Grade
        };
        
        // Terapkan lebar ke setiap kolom
        for (int i = 0; i < columnWidths.length && i < columnModel.getColumnCount(); i++) {
            TableColumn column = columnModel.getColumn(i);
            column.setPreferredWidth(columnWidths[i]);
            column.setMinWidth(50);
            column.setMaxWidth(columnWidths[i] * 2);
        }
        
        jTable2.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
        jTable2.revalidate();
        jTable2.repaint();
    });
}

private Connection getConnection() {
    try {
        String url = "jdbc:mysql://localhost:3306/tiara_school";
        String username = "root"; // sesuaikan dengan username MySQL Anda
        String password = "";     // sesuaikan dengan password MySQL Anda
        return DriverManager.getConnection(url, username, password);
    } catch (SQLException e) {
        JOptionPane.showMessageDialog(this, "Koneksi database gagal: " + e.getMessage());
        return null;
    }
}

private void resetSearch() {
    jTextField1.setText("");
    loadDataToTable(); 
    
    // TAMBAHKAN INI: Pastikan lebar kolom tetap setelah reset
    SwingUtilities.invokeLater(() -> {
        setManualColumnWidths();
    });
    
    JOptionPane.showMessageDialog(this, "Menampilkan semua data");
}


private void customizeTableHeader() {
    // Dapatkan header tabel
    javax.swing.table.JTableHeader header = jTable1.getTableHeader();
    
    // 1. MENGUBAH FONT HEADER
    header.setFont(new java.awt.Font("Tahoma", java.awt.Font.BOLD, 16));
    
    // 2. MENGUBAH WARNA BACKGROUND HEADER
    header.setBackground(new java.awt.Color(15, 65, 67)); // Warna hijau tua sesuai tema
    
    // 3. MENGUBAH WARNA TEXT HEADER
    header.setForeground(new java.awt.Color(245, 244, 230)); // Warna putih krem
    
    // 4. MENGUBAH TINGGI HEADER
    header.setPreferredSize(new java.awt.Dimension(header.getWidth(), 40));
    
    // 5. MENONAKTIFKAN REORDERING KOLOM (sudah ada di code Anda)
    header.setReorderingAllowed(false);
    
    // 6. MENGUBAH BORDER HEADER
    
    // 7. MEMBUAT HEADER TIDAK BISA DIRESIZABLE (opsional)
    header.setResizingAllowed(false);
    
    // 8. CUSTOM RENDERER UNTUK HEADER (lebih advanced)
    header.setDefaultRenderer(new CustomHeaderRenderer());
}

// Custom renderer class untuk header yang lebih advanced
class CustomHeaderRenderer extends javax.swing.table.DefaultTableCellRenderer {
    public CustomHeaderRenderer() {
        setHorizontalAlignment(javax.swing.JLabel.CENTER);
        setOpaque(true);
    }
    
    @Override
    public java.awt.Component getTableCellRendererComponent(
        javax.swing.JTable table, Object value, boolean isSelected, 
        boolean hasFocus, int row, int column) {
        
        // Set background dan foreground
        setBackground(new java.awt.Color(15, 65, 67));
        setForeground(new java.awt.Color(245, 244, 230));
        
        // Set font
        setFont(new java.awt.Font("Tahoma", java.awt.Font.BOLD, 14));
        
        // Set text
        setText(value != null ? value.toString() : "");
        
        // Set border
       
        
        return this;
    }
}


    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jPanel5 = new javax.swing.JPanel();
        jLabel5 = new javax.swing.JLabel();
        jPanel4 = new javax.swing.JPanel();
        jButton3 = new javax.swing.JButton();
        jButton7 = new javax.swing.JButton();
        jButton6 = new javax.swing.JButton();
        jButton5 = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTable1 = new javax.swing.JTable();
        jButton8 = new javax.swing.JButton();
        jScrollPane2 = new javax.swing.JScrollPane();
        jTable2 = new javax.swing.JTable();
        jLabel4 = new javax.swing.JLabel();
        jButton10 = new javax.swing.JButton();
        jButton13 = new javax.swing.JButton();
        jButton14 = new javax.swing.JButton();
        jLabel3 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jButton2 = new javax.swing.JButton();
        jButton9 = new javax.swing.JButton();
        jButton11 = new javax.swing.JButton();
        jButton12 = new javax.swing.JButton();
        jLabel2 = new javax.swing.JLabel();
        jTextField1 = new javax.swing.JTextField();
        jButton1 = new javax.swing.JButton();
        jButton4 = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jPanel1.setBackground(new java.awt.Color(15, 65, 67));

        jPanel5.setBackground(new java.awt.Color(245, 244, 230));

        jLabel5.setBackground(new java.awt.Color(15, 65, 67));
        jLabel5.setFont(new java.awt.Font("Tahoma", 1, 72)); // NOI18N
        jLabel5.setForeground(new java.awt.Color(15, 65, 67));
        jLabel5.setText("Tiara School");
        jLabel5.setHorizontalTextPosition(javax.swing.SwingConstants.LEADING);

        javax.swing.GroupLayout jPanel5Layout = new javax.swing.GroupLayout(jPanel5);
        jPanel5.setLayout(jPanel5Layout);
        jPanel5Layout.setHorizontalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addGap(26, 26, 26)
                .addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, 805, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel5Layout.setVerticalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel5, javax.swing.GroupLayout.DEFAULT_SIZE, 129, Short.MAX_VALUE)
                .addContainerGap())
        );

        jPanel4.setBackground(new java.awt.Color(245, 244, 230));
        jPanel4.setAlignmentX(0.0F);
        jPanel4.setAlignmentY(0.0F);
        jPanel4.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jButton3.setBackground(new java.awt.Color(15, 65, 67));
        jButton3.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jButton3.setForeground(new java.awt.Color(245, 244, 230));
        jButton3.setText("Lihat Nilai Siswa");
        jButton3.setBorder(null);
        jButton3.setBorderPainted(false);
        jButton3.setContentAreaFilled(false);
        jButton3.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        jButton3.setFocusPainted(false);
        jButton3.setOpaque(true);
        jButton3.setPreferredSize(new java.awt.Dimension(200, 60));
        jButton3.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                jButton3MouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                jButton3MouseExited(evt);
            }
        });
        jButton3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton3ActionPerformed(evt);
            }
        });
        jPanel4.add(jButton3, new org.netbeans.lib.awtextra.AbsoluteConstraints(990, 590, 400, 60));

        jButton7.setBackground(new java.awt.Color(15, 65, 67));
        jButton7.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jButton7.setForeground(new java.awt.Color(245, 244, 230));
        jButton7.setText("Hapus Data");
        jButton7.setBorder(null);
        jButton7.setBorderPainted(false);
        jButton7.setContentAreaFilled(false);
        jButton7.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        jButton7.setFocusPainted(false);
        jButton7.setOpaque(true);
        jButton7.setPreferredSize(new java.awt.Dimension(200, 60));
        jButton7.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                jButton7MouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                jButton7MouseExited(evt);
            }
        });
        jButton7.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton7ActionPerformed(evt);
            }
        });
        jPanel4.add(jButton7, new org.netbeans.lib.awtextra.AbsoluteConstraints(1410, 380, 400, 60));

        jButton6.setBackground(new java.awt.Color(15, 65, 67));
        jButton6.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jButton6.setForeground(new java.awt.Color(245, 244, 230));
        jButton6.setText("Ubah Data");
        jButton6.setBorder(null);
        jButton6.setBorderPainted(false);
        jButton6.setContentAreaFilled(false);
        jButton6.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        jButton6.setFocusPainted(false);
        jButton6.setOpaque(true);
        jButton6.setPreferredSize(new java.awt.Dimension(200, 60));
        jButton6.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                jButton6MouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                jButton6MouseExited(evt);
            }
        });
        jButton6.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton6ActionPerformed(evt);
            }
        });
        jPanel4.add(jButton6, new org.netbeans.lib.awtextra.AbsoluteConstraints(990, 460, 400, 60));

        jButton5.setBackground(new java.awt.Color(15, 65, 67));
        jButton5.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jButton5.setForeground(new java.awt.Color(245, 244, 230));
        jButton5.setText("Tambahkan Data");
        jButton5.setBorder(null);
        jButton5.setBorderPainted(false);
        jButton5.setContentAreaFilled(false);
        jButton5.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        jButton5.setFocusPainted(false);
        jButton5.setFocusable(false);
        jButton5.setOpaque(true);
        jButton5.setPreferredSize(new java.awt.Dimension(600, 60));
        jButton5.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                jButton5MouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                jButton5MouseExited(evt);
            }
        });
        jButton5.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton5ActionPerformed(evt);
            }
        });
        jPanel4.add(jButton5, new org.netbeans.lib.awtextra.AbsoluteConstraints(990, 380, 400, 60));

        jTable1.setBackground(new java.awt.Color(245, 244, 230));
        jTable1.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jTable1.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        jTable1.setAlignmentX(1.0F);
        jTable1.setAlignmentY(1.0F);
        jTable1.setAutoscrolls(false);
        jTable1.setGridColor(new java.awt.Color(15, 65, 67));
        jTable1.setIntercellSpacing(new java.awt.Dimension(2, 2));
        jTable1.addAncestorListener(new javax.swing.event.AncestorListener() {
            public void ancestorMoved(javax.swing.event.AncestorEvent evt) {
                jTable1AncestorMoved(evt);
            }
            public void ancestorAdded(javax.swing.event.AncestorEvent evt) {
            }
            public void ancestorRemoved(javax.swing.event.AncestorEvent evt) {
            }
        });
        jScrollPane1.setViewportView(jTable1);

        jPanel4.add(jScrollPane1, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 20, 1820, 290));

        jButton8.setBackground(new java.awt.Color(15, 65, 67));
        jButton8.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jButton8.setForeground(new java.awt.Color(245, 244, 230));
        jButton8.setText("Cetak Rapor");
        jButton8.setBorder(null);
        jButton8.setBorderPainted(false);
        jButton8.setContentAreaFilled(false);
        jButton8.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        jButton8.setFocusPainted(false);
        jButton8.setOpaque(true);
        jButton8.setPreferredSize(new java.awt.Dimension(200, 60));
        jButton8.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                jButton8MouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                jButton8MouseExited(evt);
            }
        });
        jButton8.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton8ActionPerformed(evt);
            }
        });
        jPanel4.add(jButton8, new org.netbeans.lib.awtextra.AbsoluteConstraints(1410, 460, 400, 60));

        jTable2.setBackground(new java.awt.Color(245, 244, 230));
        jTable2.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jTable2.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        jTable2.setAlignmentX(1.0F);
        jTable2.setAlignmentY(1.0F);
        jTable2.setAutoscrolls(false);
        jTable2.setGridColor(new java.awt.Color(15, 65, 67));
        jTable2.setIntercellSpacing(new java.awt.Dimension(2, 2));
        jTable2.addAncestorListener(new javax.swing.event.AncestorListener() {
            public void ancestorMoved(javax.swing.event.AncestorEvent evt) {
                jTable2AncestorMoved(evt);
            }
            public void ancestorAdded(javax.swing.event.AncestorEvent evt) {
            }
            public void ancestorRemoved(javax.swing.event.AncestorEvent evt) {
            }
        });
        jScrollPane2.setViewportView(jTable2);

        jPanel4.add(jScrollPane2, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 380, 920, 350));

        jLabel4.setBackground(new java.awt.Color(15, 65, 67));
        jLabel4.setFont(new java.awt.Font("Tahoma", 1, 36)); // NOI18N
        jLabel4.setForeground(new java.awt.Color(15, 65, 67));
        jLabel4.setText("Nilai Siswa");
        jLabel4.setHorizontalTextPosition(javax.swing.SwingConstants.LEADING);
        jPanel4.add(jLabel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 290, 460, 108));

        jButton10.setBackground(new java.awt.Color(15, 65, 67));
        jButton10.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jButton10.setForeground(new java.awt.Color(245, 244, 230));
        jButton10.setText("Ubah Data");
        jButton10.setBorder(null);
        jButton10.setBorderPainted(false);
        jButton10.setContentAreaFilled(false);
        jButton10.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        jButton10.setFocusPainted(false);
        jButton10.setOpaque(true);
        jButton10.setPreferredSize(new java.awt.Dimension(200, 60));
        jButton10.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                jButton10MouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                jButton10MouseExited(evt);
            }
        });
        jButton10.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton10ActionPerformed(evt);
            }
        });
        jPanel4.add(jButton10, new org.netbeans.lib.awtextra.AbsoluteConstraints(990, 670, 400, 60));

        jButton13.setBackground(new java.awt.Color(15, 65, 67));
        jButton13.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jButton13.setForeground(new java.awt.Color(245, 244, 230));
        jButton13.setText("Tambahkan Data");
        jButton13.setBorder(null);
        jButton13.setBorderPainted(false);
        jButton13.setContentAreaFilled(false);
        jButton13.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        jButton13.setFocusPainted(false);
        jButton13.setFocusable(false);
        jButton13.setOpaque(true);
        jButton13.setPreferredSize(new java.awt.Dimension(600, 60));
        jButton13.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                jButton13MouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                jButton13MouseExited(evt);
            }
        });
        jButton13.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton13ActionPerformed(evt);
            }
        });
        jPanel4.add(jButton13, new org.netbeans.lib.awtextra.AbsoluteConstraints(1410, 590, 400, 60));

        jButton14.setBackground(new java.awt.Color(15, 65, 67));
        jButton14.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jButton14.setForeground(new java.awt.Color(245, 244, 230));
        jButton14.setText("Hapus Data");
        jButton14.setBorder(null);
        jButton14.setBorderPainted(false);
        jButton14.setContentAreaFilled(false);
        jButton14.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        jButton14.setFocusPainted(false);
        jButton14.setOpaque(true);
        jButton14.setPreferredSize(new java.awt.Dimension(200, 60));
        jButton14.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                jButton14MouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                jButton14MouseExited(evt);
            }
        });
        jButton14.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton14ActionPerformed(evt);
            }
        });
        jPanel4.add(jButton14, new org.netbeans.lib.awtextra.AbsoluteConstraints(1410, 670, 400, 60));

        jLabel3.setBackground(new java.awt.Color(15, 65, 67));
        jLabel3.setFont(new java.awt.Font("Tahoma", 1, 36)); // NOI18N
        jLabel3.setForeground(new java.awt.Color(15, 65, 67));
        jLabel3.setText("--- Nilai ---");
        jLabel3.setHorizontalTextPosition(javax.swing.SwingConstants.LEADING);
        jPanel4.add(jLabel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(1300, 500, 310, 110));

        jLabel6.setBackground(new java.awt.Color(15, 65, 67));
        jLabel6.setFont(new java.awt.Font("Tahoma", 1, 36)); // NOI18N
        jLabel6.setForeground(new java.awt.Color(15, 65, 67));
        jLabel6.setText("--- Rapor ---");
        jLabel6.setHorizontalTextPosition(javax.swing.SwingConstants.LEADING);
        jPanel4.add(jLabel6, new org.netbeans.lib.awtextra.AbsoluteConstraints(1290, 290, 310, 110));

        jButton2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/photos/school2.png"))); // NOI18N
        jButton2.setBorder(javax.swing.BorderFactory.createCompoundBorder());
        jButton2.setBorderPainted(false);
        jButton2.setContentAreaFilled(false);
        jButton2.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        jButton2.setMargin(new java.awt.Insets(0, 0, 0, 0));
        jButton2.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jButton2MouseClicked(evt);
            }
        });
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });

        jButton9.setIcon(new javax.swing.ImageIcon(getClass().getResource("/photos/student2.png"))); // NOI18N
        jButton9.setBorder(javax.swing.BorderFactory.createCompoundBorder());
        jButton9.setBorderPainted(false);
        jButton9.setContentAreaFilled(false);
        jButton9.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        jButton9.setMargin(new java.awt.Insets(0, 0, 0, 0));
        jButton9.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jButton9MouseClicked(evt);
            }
        });
        jButton9.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton9ActionPerformed(evt);
            }
        });

        jButton11.setIcon(new javax.swing.ImageIcon(getClass().getResource("/photos/calendar2.png"))); // NOI18N
        jButton11.setBorder(javax.swing.BorderFactory.createCompoundBorder());
        jButton11.setBorderPainted(false);
        jButton11.setContentAreaFilled(false);
        jButton11.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        jButton11.setMargin(new java.awt.Insets(0, 0, 0, 0));
        jButton11.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jButton11MouseClicked(evt);
            }
        });
        jButton11.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton11ActionPerformed(evt);
            }
        });

        jButton12.setIcon(new javax.swing.ImageIcon(getClass().getResource("/photos/exam2.png"))); // NOI18N
        jButton12.setBorder(javax.swing.BorderFactory.createCompoundBorder());
        jButton12.setBorderPainted(false);
        jButton12.setContentAreaFilled(false);
        jButton12.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        jButton12.setMargin(new java.awt.Insets(0, 0, 0, 0));
        jButton12.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jButton12MouseClicked(evt);
            }
        });
        jButton12.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton12ActionPerformed(evt);
            }
        });

        jLabel2.setBackground(new java.awt.Color(15, 65, 67));
        jLabel2.setFont(new java.awt.Font("Tahoma", 1, 64)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(245, 244, 230));
        jLabel2.setText("Rapor Siswa");
        jLabel2.setHorizontalTextPosition(javax.swing.SwingConstants.LEADING);

        jTextField1.setBackground(new java.awt.Color(245, 244, 230));
        jTextField1.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jTextField1.setForeground(new java.awt.Color(15, 65, 67));
        jTextField1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextField1ActionPerformed(evt);
            }
        });

        jButton1.setBackground(new java.awt.Color(245, 244, 230));
        jButton1.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jButton1.setForeground(new java.awt.Color(15, 65, 67));
        jButton1.setText("Cari");
        jButton1.setBorder(null);
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        jButton4.setBackground(new java.awt.Color(245, 244, 230));
        jButton4.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jButton4.setForeground(new java.awt.Color(15, 65, 67));
        jButton4.setText("Reset");
        jButton4.setBorder(null);
        jButton4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton4ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jButton2, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 61, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButton9, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 61, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButton11, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 61, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButton12, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 61, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jPanel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel5, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(84, 84, 84)
                .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 460, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(762, 762, 762)
                .addComponent(jTextField1, javax.swing.GroupLayout.PREFERRED_SIZE, 278, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(12, 12, 12)
                .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 143, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(17, 17, 17)
                .addComponent(jButton4, javax.swing.GroupLayout.PREFERRED_SIZE, 118, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addComponent(jPanel5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 108, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(33, 33, 33)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jTextField1, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jButton4, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, 819, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(17, 17, 17)
                        .addComponent(jButton2, javax.swing.GroupLayout.PREFERRED_SIZE, 61, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(jButton9, javax.swing.GroupLayout.PREFERRED_SIZE, 61, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(jButton11, javax.swing.GroupLayout.PREFERRED_SIZE, 61, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(jButton12, javax.swing.GroupLayout.PREFERRED_SIZE, 61, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        scaleButtonIcon(jButton2, "/photos/school2.png");
        scaleButtonIcon(jButton9, "/photos/student2.png");
        scaleButtonIcon(jButton11, "/photos/calendar2.png");
        scaleButtonIcon(jButton12, "/photos/exam2.png");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, 1025, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jTextField1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextField1ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jTextField1ActionPerformed

    private void jButton5ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton5ActionPerformed
        // TODO add your handling code here:
        TambahRapor tambahSiswaForm = new TambahRapor();
    tambahSiswaForm.setVisible(true);
    this.dispose();
    }//GEN-LAST:event_jButton5ActionPerformed

    private void jButton3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton3ActionPerformed
        // TODO add your handling code here:
    int selectedRow = jTable1.getSelectedRow();
    
    if (selectedRow == -1) {
        JOptionPane.showMessageDialog(this, "Pilih siswa terlebih dahulu di tabel rapor!");
        return;
    }
    
    // Ambil NIS dari baris yang dipilih
    String nis = jTable1.getValueAt(selectedRow, 0).toString();
    
    // Muat data nilai berdasarkan NIS
    loadNilaiDataToTable(nis);
    
   
    }//GEN-LAST:event_jButton3ActionPerformed

    private void jButton6ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton6ActionPerformed
    // In your main form class (where jTable1 exists)
    int selectedRow = jTable1.getSelectedRow();
    
    if (selectedRow == -1) {
        JOptionPane.showMessageDialog(this, "Pilih data yang akan diubah terlebih dahulu!");
        return;
    }
    
    // Prepare data from the table
    String[] raporData = new String[9];
    raporData[0] = jTable1.getValueAt(selectedRow, 1).toString(); // Nama
    raporData[1] = jTable1.getValueAt(selectedRow, 2).toString(); // Kelas
    raporData[2] = jTable1.getValueAt(selectedRow, 3).toString(); // Semester
    raporData[3] = jTable1.getValueAt(selectedRow, 4).toString(); // Tahun Ajaran
    raporData[4] = jTable1.getValueAt(selectedRow, 5).toString(); // Sakit
    raporData[5] = jTable1.getValueAt(selectedRow, 6).toString(); // Izin
    raporData[6] = jTable1.getValueAt(selectedRow, 7).toString(); // Alfa
    raporData[7] = jTable1.getValueAt(selectedRow, 9).toString(); // Catatan Wali Kelas
    raporData[8] = jTable1.getValueAt(selectedRow, 8).toString(); // Status Naik
    
    
    // Open UbahRapor form with the selected data
    UbahRapor ubahForm = new UbahRapor(raporData);
    ubahForm.setVisible(true);
    this.dispose();


    }//GEN-LAST:event_jButton6ActionPerformed

    private void jButton7ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton7ActionPerformed
        // TODO add your handling code here:
     int selectedRow = jTable1.getSelectedRow();
    
    if (selectedRow == -1) {
        JOptionPane.showMessageDialog(this, "Pilih data yang akan dihapus!");
        return;
    }
    
    // Konfirmasi hapus
    int confirm = JOptionPane.showConfirmDialog(this, 
        "Apakah Anda yakin ingin menghapus data rapor ini?", 
        "Konfirmasi Hapus", 
        JOptionPane.YES_NO_OPTION);
    
    if (confirm == JOptionPane.YES_OPTION) {
        try {
            // Ambil NIS dari baris yang dipilih (kolom ke-1, index 0)
            String nis = jTable1.getValueAt(selectedRow, 0).toString();
            // Ambil semester dan tahun ajaran untuk identifikasi unik
            String semester = jTable1.getValueAt(selectedRow, 2).toString();
            String tahunAjaran = jTable1.getValueAt(selectedRow, 3).toString();
            
            Connection conn = getConnection();
            if (conn != null) {
                String query = "DELETE FROM rapor WHERE NIS = ? AND semester = ? AND tahun_ajaran = ?";
                PreparedStatement pst = conn.prepareStatement(query);
                pst.setString(1, nis);
                pst.setString(2, semester);
                pst.setString(3, tahunAjaran);
                
                int result = pst.executeUpdate();
                
                if (result > 0) {
                    JOptionPane.showMessageDialog(this, "Data rapor berhasil dihapus!");
                    loadDataToTable(); // Refresh table
                } else {
                    JOptionPane.showMessageDialog(this, "Data rapor gagal dihapus!");
                }
                
                conn.close();
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(this, "Error deleting data: " + e.getMessage());
        }
    }
    }//GEN-LAST:event_jButton7ActionPerformed

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        // TODO add your handling code here:
    String keyword = jTextField1.getText().trim();
    
    if (keyword.isEmpty()) {
        JOptionPane.showMessageDialog(this, "Masukkan kata kunci pencarian!");
        loadDataToTable();
        return;
    }
    
    try {
        Connection conn = getConnection();
        if (conn != null) {
            String query = "SELECT * FROM rapor WHERE " +
                          "NIS LIKE ? OR " +
                          "nama_kelas LIKE ? OR " +
                          "semester LIKE ? OR " +
                          "tahun_ajaran LIKE ? OR " +
                          "jumlah_sakit LIKE ? OR " +
                          "jumlah_izin LIKE ? OR " +
                          "jumlah_alpa LIKE ? OR " +
                          "catatan_wali_kelas LIKE ? OR " +
                          "DATE_FORMAT(tanggal_rapor, '%Y-%m-%d') LIKE ? OR " +
                          "status_naik LIKE ?";
            
            PreparedStatement pst = conn.prepareStatement(query);
            String searchPattern = "%" + keyword + "%";
            
            for (int i = 1; i <= 10; i++) {
                pst.setString(i, searchPattern);
            }
            
            ResultSet rs = pst.executeQuery();
            
            DefaultTableModel model = (DefaultTableModel) jTable1.getModel();
            model.setRowCount(0);
            
            model.setColumnIdentifiers(new String[]{
                "NIS", "ID Kelas", "Semester", "Tahun Ajaran", "Jumlah Sakit", 
                "Jumlah Izin", "Jumlah Alpa", "Catatan Wali Kelas", "Tanggal Rapor", 
                "Status Naik", "Created At", "Updated At"
            });
            
            boolean hasResults = false;
            while (rs.next()) {
                hasResults = true;
                Object[] row = {
                    rs.getString("NIS"),
                    rs.getInt("nama_kelas"),
                    rs.getString("semester"),
                    rs.getString("tahun_ajaran"),
                    rs.getInt("jumlah_sakit"),
                    rs.getInt("jumlah_izin"),
                    rs.getInt("jumlah_alpa"),
                    rs.getString("catatan_wali_kelas"),
                    rs.getDate("tanggal_rapor"),
                    rs.getString("status_naik"),
                    rs.getTimestamp("created_at"),
                    rs.getTimestamp("updated_at")
                };
                model.addRow(row);
            }
            
            if (!hasResults) {
                JOptionPane.showMessageDialog(this, "Data tidak ditemukan untuk: " + keyword);
            } else {
                JOptionPane.showMessageDialog(this, "Ditemukan " + model.getRowCount() + " data untuk: " + keyword);
            }
            
            conn.close();
            
            SwingUtilities.invokeLater(() -> {
                setManualColumnWidths();
            });
        }
    } catch (SQLException e) {
        JOptionPane.showMessageDialog(this, "Error searching data: " + e.getMessage());
    }
    }//GEN-LAST:event_jButton1ActionPerformed

    private void jTable1AncestorMoved(javax.swing.event.AncestorEvent evt) {//GEN-FIRST:event_jTable1AncestorMoved
        // TODO add your handling code here:
    }//GEN-LAST:event_jTable1AncestorMoved

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        // TODO add your handling code here:
        home homeForm = new home();
    homeForm.setVisible(true);
    
    this.dispose();
    }//GEN-LAST:event_jButton2ActionPerformed

    private void jButton2MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jButton2MouseClicked
    home homeForm = new home();
    homeForm.setVisible(true);
    
    this.dispose();
    }//GEN-LAST:event_jButton2MouseClicked

    private void jButton9MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jButton9MouseClicked
    Rapor homeForm = new Rapor();
    homeForm.setVisible(true);
    
    this.dispose();    }//GEN-LAST:event_jButton9MouseClicked

    private void jButton9ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton9ActionPerformed
        // TODO add your handling code here:
        DataSiswaa homeForm = new DataSiswaa();
    homeForm.setVisible(true);
    this.dispose();
    }//GEN-LAST:event_jButton9ActionPerformed

    private void jButton11MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jButton11MouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_jButton11MouseClicked

    private void jButton11ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton11ActionPerformed
        // TODO add your handling code here:
    Ujian homeForm = new Ujian();
    homeForm.setVisible(true);
    
    this.dispose(); 
    }//GEN-LAST:event_jButton11ActionPerformed

    private void jButton12MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jButton12MouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_jButton12MouseClicked

    private void jButton12ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton12ActionPerformed
        // TODO add your handling code here:
        Rapor homeForm = new Rapor();
    homeForm.setVisible(true);
    
    this.dispose();
    }//GEN-LAST:event_jButton12ActionPerformed

    private void jButton3MouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jButton3MouseEntered
    jButton3.setBackground(new Color(30, 90, 92)); // Warna hover
    jButton3.setCursor(new Cursor(Cursor.HAND_CURSOR)); // Kursor tangan
    }//GEN-LAST:event_jButton3MouseEntered

    private void jButton3MouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jButton3MouseExited
    jButton3.setBackground(new Color(15, 65, 67)); // Warna default
    jButton3.setCursor(new Cursor(Cursor.DEFAULT_CURSOR)); // Kursor normal
    }//GEN-LAST:event_jButton3MouseExited

    private void jButton7MouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jButton7MouseEntered
    jButton7.setBackground(new Color(30, 90, 92)); // Warna hover
    jButton7.setCursor(new Cursor(Cursor.HAND_CURSOR)); // Kursor tangan
    }//GEN-LAST:event_jButton7MouseEntered

    private void jButton7MouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jButton7MouseExited
    jButton7.setBackground(new Color(15, 65, 67)); // Warna default
    jButton7.setCursor(new Cursor(Cursor.DEFAULT_CURSOR)); // Kursor normal
    }//GEN-LAST:event_jButton7MouseExited

    private void jButton6MouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jButton6MouseEntered
    jButton6.setBackground(new Color(30, 90, 92)); // Warna hover
    jButton6.setCursor(new Cursor(Cursor.HAND_CURSOR)); // Kursor tangan
    }//GEN-LAST:event_jButton6MouseEntered

    private void jButton6MouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jButton6MouseExited
    jButton6.setBackground(new Color(15, 65, 67)); // Warna default
    jButton6.setCursor(new Cursor(Cursor.DEFAULT_CURSOR)); // Kursor normal
    }//GEN-LAST:event_jButton6MouseExited

    private void jButton5MouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jButton5MouseEntered
    jButton5.setBackground(new Color(30, 90, 92)); // Warna hover
    jButton5.setCursor(new Cursor(Cursor.HAND_CURSOR)); // Kursor tangan
    }//GEN-LAST:event_jButton5MouseEntered

    private void jButton5MouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jButton5MouseExited
    jButton5.setBackground(new Color(15, 65, 67)); // Warna default
    jButton5.setCursor(new Cursor(Cursor.DEFAULT_CURSOR)); // Kursor normal
    }//GEN-LAST:event_jButton5MouseExited

    private void jButton4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton4ActionPerformed
        // TODO add your handling code here:
        resetSearch();
    }//GEN-LAST:event_jButton4ActionPerformed

    private void jButton8MouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jButton8MouseEntered
        // TODO add your handling code here:
    }//GEN-LAST:event_jButton8MouseEntered

    private void jButton8MouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jButton8MouseExited
        // TODO add your handling code here:
    }//GEN-LAST:event_jButton8MouseExited

    private void jButton8ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton8ActionPerformed
// Button Action Method
    int selectedRow = jTable1.getSelectedRow();
    if (selectedRow == -1) {
        JOptionPane.showMessageDialog(this, "Pilih siswa terlebih dahulu!");
        return;
    }

    // Get NIS from the correct column (adjust index as needed)
    int nisColumnIndex = 0; // Change this based on your table structure
    String nis = jTable1.getValueAt(selectedRow, nisColumnIndex).toString().trim();
    
    System.out.println("NIS yang dipilih: " + nis);
    
    // Verify data exists before generating report
    if (!verifyReportData(nis)) {
        JOptionPane.showMessageDialog(this, 
            "Data tidak lengkap untuk NIS " + nis, 
            "Peringatan", 
            JOptionPane.WARNING_MESSAGE);
        return;
    }

    // Show report type selection
    Object[] options = {"Report Nilai", "Report Rapor"};
    int choice = JOptionPane.showOptionDialog(
        this,
        "Pilih jenis laporan:",
        "Pilihan Laporan",
        JOptionPane.DEFAULT_OPTION,
        JOptionPane.QUESTION_MESSAGE,
        null,
        options,
        options[0]
    );

    if (choice == 0) {
        showJasperReport("report2.jasper", nis, "Laporan Nilai");
    } else if (choice == 1) {
        showJasperReport("raport3.jasper", nis, "Laporan Rapor");
    }
}

private boolean verifyReportData(String nis) {
    Connection conn = null;
    try {
        conn = getConnection();
        
        // Check student exists
        String studentQuery = "SELECT COUNT(*) FROM siswa WHERE NIS = ?";
        try (PreparedStatement ps = conn.prepareStatement(studentQuery)) {
            ps.setString(1, nis);
            ResultSet rs = ps.executeQuery();
            if (rs.next() && rs.getInt(1) == 0) {
                System.out.println("Siswa dengan NIS " + nis + " tidak ditemukan");
                return false;
            }
        }
        
        // Check nilai data exists
        String nilaiQuery = "SELECT COUNT(*) FROM nilai WHERE NIS = ?";
        try (PreparedStatement ps = conn.prepareStatement(nilaiQuery)) {
            ps.setString(1, nis);
            ResultSet rs = ps.executeQuery();
            if (rs.next() && rs.getInt(1) == 0) {
                System.out.println("Tidak ada data nilai untuk NIS " + nis);
                return false;
            }
        }
        
        // Check rapor data exists
        String raporQuery = "SELECT COUNT(*) FROM rapor WHERE NIS = ?";
        try (PreparedStatement ps = conn.prepareStatement(raporQuery)) {
            ps.setString(1, nis);
            ResultSet rs = ps.executeQuery();
            if (rs.next() && rs.getInt(1) == 0) {
                System.out.println("Tidak ada data rapor untuk NIS " + nis);
                return false;
            }
        }
        
        return true;
        
    } catch (SQLException e) {
        e.printStackTrace();
        return false;
    } finally {
        if (conn != null) {
            try { conn.close(); } catch (SQLException e) { e.printStackTrace(); }
        }
    }
}

private void showJasperReport(String reportFileName, String nis, String title) {
    Connection conn = null;
    try {
        // 1. Try to find report file in multiple locations
        String reportPath = findReportFile(reportFileName);
        
        if (reportPath == null) {
            // Try to load from resources as fallback
            InputStream reportStream = getClass().getResourceAsStream("/view/" + reportFileName);
            if (reportStream == null) {
                reportStream = getClass().getResourceAsStream("/" + reportFileName);
            }
            
            if (reportStream != null) {
                System.out.println("Found report in resources: " + reportFileName);
                generateReportFromStream(reportStream, nis, title);
                return;
            }
            
            // If still not found, show error
            JOptionPane.showMessageDialog(this, 
                "File report tidak ditemukan: " + reportFileName + 
                "\nPastikan file ada di folder yang sama dengan aplikasi atau di resources.", 
                "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        // 2. Get database connection
        conn = getConnection();
        if (conn == null) {
            JOptionPane.showMessageDialog(this, 
                "Koneksi database gagal!", 
                "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }
        
        // 3. Debug: Print parameter value
        System.out.println("Generating report for NIS: " + nis);
        System.out.println("Using report file: " + reportPath);
        
        // 4. Create parameters map
        HashMap<String, Object> parameters = new HashMap<>();
        parameters.put("NIS_PARAMETER", nis);
        
        // 5. Debug: Print SQL query with parameter
        System.out.println("Executing query with parameter: " + nis);
        
        // 6. Generate report
        JasperPrint jasperPrint = JasperFillManager.fillReport(
            reportPath, 
            parameters, 
            conn
        );
        
        // 7. Check if report has content
        if (jasperPrint.getPages().isEmpty()) {
            JOptionPane.showMessageDialog(this, 
                "Tidak ada data untuk NIS: " + nis + 
                "\nPastikan siswa memiliki data yang lengkap.", 
                "Data Kosong", 
                JOptionPane.WARNING_MESSAGE);
        } else {
            // 8. Display report
            JasperViewer viewer = new JasperViewer(jasperPrint, false);
            viewer.setTitle(title + " - NIS: " + nis);
            viewer.setVisible(true);
            System.out.println("Report berhasil ditampilkan");
        }
        
    } catch (JRException e) {
        JOptionPane.showMessageDialog(this, 
            "Error generating report:\n" + e.getMessage(), 
            "Error Jasper", 
            JOptionPane.ERROR_MESSAGE);
        e.printStackTrace();
    } catch (Exception e) {
        JOptionPane.showMessageDialog(this, 
            "Error saat mencetak laporan:\n" + e.getMessage(), 
            "Error", 
            JOptionPane.ERROR_MESSAGE);
        e.printStackTrace();
    } finally {
        if (conn != null) {
            try { 
                conn.close(); 
            } catch (SQLException e) { 
                e.printStackTrace(); 
            }
        }
    }
}

private String findReportFile(String reportFileName) {
    // Try different possible paths
    String[] possiblePaths = {
        reportFileName,                          // Same folder as exe
        "./reports/" + reportFileName,           // reports subfolder
        "./src/view/" + reportFileName,          // Original NetBeans structure
        "./view/" + reportFileName,              // view subfolder
        "./" + reportFileName,                   // Current directory
        "src/view/" + reportFileName             // Relative to project root
    };
    
    for (String path : possiblePaths) {
        File reportFile = new File(path);
        System.out.println("Checking path: " + path);
        System.out.println("File exists: " + reportFile.exists());
        System.out.println("Absolute path: " + reportFile.getAbsolutePath());
        
        if (reportFile.exists()) {
            System.out.println("Found report at: " + path);
            return reportFile.getAbsolutePath();
        }
    }
    
    System.out.println("Report file not found in any location");
    return null;
}

private void generateReportFromStream(InputStream reportStream, String nis, String title) {
    Connection conn = null;
    try {
        // Get database connection
        conn = getConnection();
        if (conn == null) {
            JOptionPane.showMessageDialog(this, 
                "Koneksi database gagal!", 
                "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }
        
        // Create parameters map
        HashMap<String, Object> parameters = new HashMap<>();
        parameters.put("NIS_PARAMETER", nis);
        
        System.out.println("Generating report from resources for NIS: " + nis);
        
        // Generate report from InputStream
        JasperPrint jasperPrint = JasperFillManager.fillReport(
            reportStream, 
            parameters, 
            conn
        );
        
        // Check if report has content
        if (jasperPrint.getPages().isEmpty()) {
            JOptionPane.showMessageDialog(this, 
                "Tidak ada data untuk NIS: " + nis + 
                "\nPastikan siswa memiliki data yang lengkap.", 
                "Data Kosong", 
                JOptionPane.WARNING_MESSAGE);
        } else {
            // Display report
            JasperViewer viewer = new JasperViewer(jasperPrint, false);
            viewer.setTitle(title + " - NIS: " + nis);
            viewer.setVisible(true);
            System.out.println("Report berhasil ditampilkan dari resources");
        }
        
    } catch (JRException e) {
        JOptionPane.showMessageDialog(this, 
            "Error generating report:\n" + e.getMessage(), 
            "Error Jasper", 
            JOptionPane.ERROR_MESSAGE);
        e.printStackTrace();
    } catch (Exception e) {
        JOptionPane.showMessageDialog(this, 
            "Error saat mencetak laporan:\n" + e.getMessage(), 
            "Error", 
            JOptionPane.ERROR_MESSAGE);
        e.printStackTrace();
    } finally {
        if (conn != null) {
            try { 
                conn.close(); 
            } catch (SQLException e) { 
                e.printStackTrace(); 
            }
        }
    }

    
    }//GEN-LAST:event_jButton8ActionPerformed

    private void jTable2AncestorMoved(javax.swing.event.AncestorEvent evt) {//GEN-FIRST:event_jTable2AncestorMoved
        // TODO add your handling code here:
    }//GEN-LAST:event_jTable2AncestorMoved

    private void jButton10MouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jButton10MouseEntered
        // TODO add your handling code here:
    }//GEN-LAST:event_jButton10MouseEntered

    private void jButton10MouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jButton10MouseExited
       // TODO add your handling code here:
    }//GEN-LAST:event_jButton10MouseExited

    private void jButton10ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton10ActionPerformed
int selectedRow = jTable2.getSelectedRow();
    if (selectedRow == -1) {
        JOptionPane.showMessageDialog(this, "Pilih data yang akan diubah!");
        return;
    }
    
    // Prepare data array from table
    String[] nilaiData = {
        jTable2.getValueAt(selectedRow, 0).toString(), // NIS
        jTable2.getValueAt(selectedRow, 1).toString(), // Pelajaran
        jTable2.getValueAt(selectedRow, 2).toString(), // Kelas
        jTable2.getValueAt(selectedRow, 3).toString(), // Semester
        jTable2.getValueAt(selectedRow, 4).toString(), // Tahun Ajaran
        jTable2.getValueAt(selectedRow, 5).toString()  // Nilai
    };
    
    // Open UbahNilai form with the data
    UbahNilai ubahNilaiForm = new UbahNilai(nilaiData);
    ubahNilaiForm.setVisible(true);
    this.dispose();        // TODO add your handling code here:
    }//GEN-LAST:event_jButton10ActionPerformed

    private void jButton13MouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jButton13MouseEntered
        // TODO add your handling code here:
    }//GEN-LAST:event_jButton13MouseEntered

    private void jButton13MouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jButton13MouseExited
        // TODO add your handling code here:
    }//GEN-LAST:event_jButton13MouseExited

    private void jButton13ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton13ActionPerformed
    TambahNilai tambahSiswaForm = new TambahNilai();
    tambahSiswaForm.setVisible(true);
    this.dispose();        // TODO add your handling code here:
    }//GEN-LAST:event_jButton13ActionPerformed

    private void jButton14MouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jButton14MouseEntered
        // TODO add your handling code here:
    }//GEN-LAST:event_jButton14MouseEntered

    private void jButton14MouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jButton14MouseExited
        // TODO add your handling code here:
    }//GEN-LAST:event_jButton14MouseExited

    private void jButton14ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton14ActionPerformed
        // TODO add your handling code here:
    int selectedRow = jTable1.getSelectedRow();
    
    if (selectedRow == -1) {
        JOptionPane.showMessageDialog(this, "Pilih data yang akan dihapus!");
        return;
    }
    
    // Konfirmasi hapus
    int confirm = JOptionPane.showConfirmDialog(this, 
        "Apakah Anda yakin ingin menghapus data rapor ini?", 
        "Konfirmasi Hapus", 
        JOptionPane.YES_NO_OPTION);
    
    if (confirm == JOptionPane.YES_OPTION) {
        try {
            // Ambil NIS dari baris yang dipilih (kolom ke-1, index 0)
            String nis = jTable2.getValueAt(selectedRow, 0).toString();
            // Ambil semester dan tahun ajaran untuk identifikasi unik
            String semester = jTable2.getValueAt(selectedRow, 2).toString();
            String tahunAjaran = jTable2.getValueAt(selectedRow, 3).toString();
            
            Connection conn = getConnection();
            if (conn != null) {
                String query = "DELETE FROM nilai WHERE NIS = ? AND semester = ? AND tahun_ajaran = ?";
                PreparedStatement pst = conn.prepareStatement(query);
                pst.setString(1, nis);
                pst.setString(2, semester);
                pst.setString(3, tahunAjaran);
                
                int result = pst.executeUpdate();
                
                if (result > 0) {
                    JOptionPane.showMessageDialog(this, "Data rapor berhasil dihapus!");
                    loadDataToTable(); // Refresh table
                } else {
                    JOptionPane.showMessageDialog(this, "Data rapor gagal dihapus!");
                }
                
                conn.close();
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(this, "Error deleting data: " + e.getMessage());
        }
    }
    
    }//GEN-LAST:event_jButton14ActionPerformed
    
    private void scaleButtonIcon(JButton button, String imagePath) {
    ImageIcon icon = new ImageIcon(getClass().getResource(imagePath));
    Image img = icon.getImage();
    
    // Dapatkan ukuran button
    int btnWidth = button.getWidth();
    int btnHeight = button.getHeight();
    
    // Scale hanya jika button sudah memiliki ukuran
    if (btnWidth > 0 && btnHeight > 0) {
        Image scaledImg = img.getScaledInstance(btnWidth, btnHeight, Image.SCALE_SMOOTH);
        button.setIcon(new ImageIcon(scaledImg));
    } else {
        // Jika button belum di-render, gunakan ukuran default
        Image scaledImg = img.getScaledInstance(50, 50, Image.SCALE_SMOOTH);
        button.setIcon(new ImageIcon(scaledImg));
    }
}
   
    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(Rapor.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Rapor.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Rapor.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Rapor.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Rapor().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton10;
    private javax.swing.JButton jButton11;
    private javax.swing.JButton jButton12;
    private javax.swing.JButton jButton13;
    private javax.swing.JButton jButton14;
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButton3;
    private javax.swing.JButton jButton4;
    private javax.swing.JButton jButton5;
    private javax.swing.JButton jButton6;
    private javax.swing.JButton jButton7;
    private javax.swing.JButton jButton8;
    private javax.swing.JButton jButton9;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JTable jTable1;
    private javax.swing.JTable jTable2;
    private javax.swing.JTextField jTextField1;
    // End of variables declaration//GEN-END:variables

    
}
