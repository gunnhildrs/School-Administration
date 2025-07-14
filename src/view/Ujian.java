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
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import model.DatabaseConnection;
import javax.swing.table.TableColumn;
import javax.swing.table.TableColumnModel;
import java.awt.FontMetrics;
import java.awt.Rectangle;
import java.io.File;
import java.io.InputStream;
import java.util.HashMap;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.SwingUtilities;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.view.JasperViewer;
/**
 *
 * @author Ahmad Irsyad Rosyadi
 */

public class Ujian extends javax.swing.JFrame {
    
    public Ujian() {
    try {
        ImageIcon appIcon = new ImageIcon(home.class.getResource("/photos/logo.png"));
        setIconImage(appIcon.getImage());
    } catch (Exception e) {
        System.out.println("Could not load application icon");
    }
    
    initComponents();
    this.setExtendedState(JFrame.MAXIMIZED_BOTH);
    
    // Initialize exam table
    DefaultTableModel model = new DefaultTableModel();
    model.setColumnIdentifiers(new String[]{
        "ID Ujian", "Nama Ujian", "ID Pelajaran", 
        "ID Kelas", "ID Ruang", "Tanggal Ujian", 
        "Waktu Mulai", "Waktu Selesai", "Durasi (Menit)", "Jumlah Peserta"
    });
    jTable1.setModel(model);
    
    // Initialize student table
    DefaultTableModel studentModel = new DefaultTableModel();
    studentModel.setColumnIdentifiers(new String[]{
        "Nama", "NIS", "JK", "NISN", "Tempat Lahir", 
        "Tanggal Lahir", "Agama", "Alamat", "No Telp", 
        "Tahun Masuk", "Status"
    });
    jTable2.setModel(studentModel);
    
    // Apply center renderers
    CenterCellRenderer centerRenderer = new CenterCellRenderer();
    for (int i = 0; i < jTable1.getColumnCount(); i++) {
        jTable1.getColumnModel().getColumn(i).setCellRenderer(centerRenderer);
    }
    for (int i = 0; i < jTable2.getColumnCount(); i++) {
        jTable2.getColumnModel().getColumn(i).setCellRenderer(centerRenderer);
    }
    
    // Table settings
    jTable1.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
    jTable1.getTableHeader().setReorderingAllowed(false);
    jTable1.setRowHeight(25);
    
    jTable2.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
    jTable2.getTableHeader().setReorderingAllowed(false);
    jTable2.setRowHeight(25);
    
    // Customize headers
    customizeTableHeader(jTable1);
    customizeTableHeader(jTable2);
    
    aktif();
    kosong();
    loadDataToTable();
    loadDataToTable2();
    
    addComponentListener(new java.awt.event.ComponentAdapter() {
        @Override
        public void componentShown(java.awt.event.ComponentEvent evt) {
            SwingUtilities.invokeLater(() -> {
                setManualColumnWidths();
                setManualColumnWidthsForStudentTable();
            });
        }
    });
}
    
    protected void aktif() {
    jTextField1.requestFocus();
    jTextField1.setEnabled(true);
    }
    protected void kosong() {
    jTextField1.setText("");
     } 
    
private void customizeTableHeader(JTable table) {
    javax.swing.table.JTableHeader header = table.getTableHeader();
    
    header.setFont(new java.awt.Font("Tahoma", java.awt.Font.BOLD, 16));
    header.setBackground(new java.awt.Color(15, 65, 67));
    header.setForeground(new java.awt.Color(245, 244, 230));
    header.setPreferredSize(new java.awt.Dimension(header.getWidth(), 40));
    header.setReorderingAllowed(false);
    header.setResizingAllowed(false);
    header.setDefaultRenderer(new CustomHeaderRenderer());
}

private void setManualColumnWidths() {
    SwingUtilities.invokeLater(() -> {
        TableColumnModel columnModel = jTable1.getColumnModel();
        
        // ATUR LEBAR SETIAP KOLOM UNTUK TABEL UJIAN
        int[] columnWidths = {
            160,  // ID Ujian
            250,  // Nama Ujian
            160,  // ID Pelajaran
            160,  // ID Kelas
            160,  // ID Ruang
            180,  // Tanggal Ujian
            180,  // Waktu Mulai
            180,  // Waktu Selesai
            180,  // Durasi (Menit)
            195   // Jumlah Peserta
        };
        
        // Terapkan lebar ke setiap kolom
        for (int i = 0; i < columnWidths.length && i < columnModel.getColumnCount(); i++) {
            TableColumn column = columnModel.getColumn(i);
            column.setPreferredWidth(columnWidths[i]);
            column.setMinWidth(50);
            column.setMaxWidth(columnWidths[i] * 2);
            
            // Pastikan renderer center diaplikasikan
            column.setCellRenderer(new CenterCellRenderer());
        }
        
        // Set auto resize mode to off after setting widths
        jTable1.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
        
        // Revalidate and repaint
        jTable1.revalidate();
        jTable1.repaint();
        
        // Scroll to top
        jTable1.scrollRectToVisible(new Rectangle(0, 0, 1, 1));
    });
}
    
protected void loadDataToTable() {
    try {
        Connection conn = getConnection();
        if (conn != null) {
            String query = "SELECT * FROM ujian";
            PreparedStatement pst = conn.prepareStatement(query);
            ResultSet rs = pst.executeQuery();
            
            // Clear existing data
            DefaultTableModel model = (DefaultTableModel) jTable1.getModel();
            model.setRowCount(0);
            
            // Set column headers sesuai struktur database ujian
            model.setColumnIdentifiers(new String[]{
                "ID Ujian",  "Nama Ujian", "ID Pelajaran", 
                "ID Kelas", "ID Ruang", "Tanggal Ujian", 
                "Waktu Mulai", "Waktu Selesai", "Durasi (Menit)", "Jumlah Peserta"
            });
            
            // Add data to table
            while (rs.next()) {
                Object[] row = {
                    rs.getString("id_ujian"),
                    rs.getString("nama_ujian"),
                    rs.getString("id_pelajaran"),
                    rs.getString("id_kelas"),
                    rs.getString("id_ruang"),
                    rs.getDate("tanggal_ujian"),
                    rs.getTime("waktu_mulai"),
                    rs.getTime("waktu_selesai"),
                    rs.getInt("durasi_menit"),
                    rs.getInt("jumlah_peserta")
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

private void setManualColumnWidthsForStudentTable() {
    SwingUtilities.invokeLater(() -> {
        TableColumnModel columnModel = jTable2.getColumnModel();
        
        // Column widths for student table
        int[] columnWidths = {
            300,  // Nama
            80,   // NIS
            50,   // JK
            110,  // NISN
            100,  // Tempat Lahir
            100,  // Tanggal Lahir
            80,   // Agama
            670,  // Alamat
            120,  // No Telp
            100,  // Tahun Masuk
            80    // Status
        };
        
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
    
protected void loadDataToTable2() {
    try {
        Connection conn = getConnection();
        if (conn != null) {
            String query = "SELECT * FROM siswa";
            PreparedStatement pst = conn.prepareStatement(query);
            ResultSet rs = pst.executeQuery();
            
            // Clear existing data
            DefaultTableModel model = (DefaultTableModel) jTable2.getModel();
            model.setRowCount(0);
            
            // Set column headers
            model.setColumnIdentifiers(new String[]{
                "Nama", "NIS", "JK", "NISN", "Tempat Lahir", 
                "Tanggal Lahir", "Agama", "Alamat", "No Telp", 
                "Tahun Masuk", "Status"
            });
            
            // Add data to table
            while (rs.next()) {
                Object[] row = {
                    rs.getString("nama"),
                    rs.getString("NIS"),
                    rs.getString("JK"),
                    rs.getString("NISN"),
                    rs.getString("tempatLahir"),
                    rs.getDate("tanggalLahir"),
                    rs.getString("agama"),
                    rs.getString("alamat"),
                    rs.getString("noTelp"),
                    rs.getInt("tahunMasuk"),
                    rs.getString("status")
                };
                model.addRow(row);
            }
            
            conn.close();
            
            SwingUtilities.invokeLater(() -> {
                setManualColumnWidthsForStudentTable();
            });
        }
    } catch (SQLException e) {
        JOptionPane.showMessageDialog(this, "Error loading student data: " + e.getMessage());
    }
}

private void resetStudentSearch() {
    jTextField2.setText("");
    loadDataToTable2(); 
    
    SwingUtilities.invokeLater(() -> {
        setManualColumnWidthsForStudentTable();
    });
    
    JOptionPane.showMessageDialog(this, "Menampilkan semua data siswa");
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

private void setupHorizontalScrolling() {
    // Pastikan JScrollPane bisa scroll horizontal
    jScrollPane1.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
    jScrollPane1.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
    
    // Set preferred size untuk tabel agar scrolling bekerja
    jTable1.setPreferredScrollableViewportSize(jTable1.getPreferredSize());
}

// Tambahkan method ini ke dalam class Ujian Anda (setelah constructor)

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
        setHorizontalAlignment(javax.swing.JLabel.CENTER); // Pastikan header juga center
        setOpaque(true);
    }
    
    @Override
    public java.awt.Component getTableCellRendererComponent(
        javax.swing.JTable table, Object value, boolean isSelected, 
        boolean hasFocus, int row, int column) {
        
        // Set background dan foreground
        setBackground(new java.awt.Color(15, 65, 67));
        setForeground(new java.awt.Color(245, 244, 230));
        
        // Set DefaultTableCellRenderer 
        setFont(new java.awt.Font("Tahoma", java.awt.Font.BOLD, 14));
        
        // Set text
        setText(value != null ? value.toString() : "");
        
        return this;
    }
}

// Custom renderer untuk sel tabel (center alignment)
class CenterCellRenderer extends javax.swing.table.DefaultTableCellRenderer {
    public CenterCellRenderer() {
        setHorizontalAlignment(javax.swing.JLabel.CENTER); // Set alignment ke center
    }

    @Override
    public java.awt.Component getTableCellRendererComponent(
        javax.swing.JTable table, Object value, boolean isSelected, 
        boolean hasFocus, int row, int column) {
        
        super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column);
        
        // Atur warna background dan foreground berdasarkan selection
        if (isSelected) {
            setBackground(new Color(30, 90, 92)); // Warna saat dipilih
            setForeground(Color.WHITE);
        } else {
            setBackground(row % 2 == 0 ? new Color(245, 244, 230) : new Color(230, 230, 220)); // Warna selang-seling
            setForeground(Color.BLACK);
        }
        
        return this;
    }
}

// MODIFIKASI CONSTRUCTOR ANDA
// Ganti bagian constructor Anda yang ada dengan ini:




    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jPanel4 = new javax.swing.JPanel();
        jButton3 = new javax.swing.JButton();
        jButton7 = new javax.swing.JButton();
        jButton6 = new javax.swing.JButton();
        jButton5 = new javax.swing.JButton();
        jLabel2 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTable1 = new javax.swing.JTable();
        jTextField1 = new javax.swing.JTextField();
        jButton1 = new javax.swing.JButton();
        jButton4 = new javax.swing.JButton();
        jLabel3 = new javax.swing.JLabel();
        jScrollPane2 = new javax.swing.JScrollPane();
        jTable2 = new javax.swing.JTable();
        jTextField2 = new javax.swing.JTextField();
        jButton8 = new javax.swing.JButton();
        jButton13 = new javax.swing.JButton();
        jButton2 = new javax.swing.JButton();
        jButton9 = new javax.swing.JButton();
        jButton11 = new javax.swing.JButton();
        jButton12 = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jPanel1.setBackground(new java.awt.Color(15, 65, 67));

        jPanel4.setBackground(new java.awt.Color(245, 244, 230));
        jPanel4.setAlignmentX(0.0F);
        jPanel4.setAlignmentY(0.0F);
        jPanel4.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jButton3.setBackground(new java.awt.Color(15, 65, 67));
        jButton3.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jButton3.setForeground(new java.awt.Color(245, 244, 230));
        jButton3.setText("+ Daftar Ujian Baru");
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
        jPanel4.add(jButton3, new org.netbeans.lib.awtextra.AbsoluteConstraints(1180, 50, 320, -1));

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
        jPanel4.add(jButton7, new org.netbeans.lib.awtextra.AbsoluteConstraints(360, 590, 320, 50));

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
        jPanel4.add(jButton6, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 590, 320, 50));

        jButton5.setBackground(new java.awt.Color(15, 65, 67));
        jButton5.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jButton5.setForeground(new java.awt.Color(245, 244, 230));
        jButton5.setText("Cetak Kartu Ujian");
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
        jPanel4.add(jButton5, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 980, 320, -1));

        jLabel2.setBackground(new java.awt.Color(15, 65, 67));
        jLabel2.setFont(new java.awt.Font("Tahoma", 1, 36)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(15, 65, 67));
        jLabel2.setText("Daftar Ujian");
        jLabel2.setHorizontalTextPosition(javax.swing.SwingConstants.LEADING);
        jPanel4.add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 300, -1, 30));

        jTable1.setBackground(new java.awt.Color(245, 244, 230));
        jTable1.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
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

        jPanel4.add(jScrollPane1, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 330, 1813, 240));

        jTextField1.setBackground(new java.awt.Color(245, 244, 230));
        jTextField1.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jTextField1.setForeground(new java.awt.Color(15, 65, 67));
        jTextField1.setEnabled(false);
        jTextField1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextField1ActionPerformed(evt);
            }
        });
        jPanel4.add(jTextField1, new org.netbeans.lib.awtextra.AbsoluteConstraints(1260, 270, 278, 50));

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
        jPanel4.add(jButton1, new org.netbeans.lib.awtextra.AbsoluteConstraints(1550, 270, 143, 50));

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
        jPanel4.add(jButton4, new org.netbeans.lib.awtextra.AbsoluteConstraints(1700, 270, 118, 50));

        jLabel3.setBackground(new java.awt.Color(15, 65, 67));
        jLabel3.setFont(new java.awt.Font("Tahoma", 1, 72)); // NOI18N
        jLabel3.setForeground(new java.awt.Color(15, 65, 67));
        jLabel3.setText("Management Ujian Tiara School");
        jLabel3.setHorizontalTextPosition(javax.swing.SwingConstants.LEADING);
        jPanel4.add(jLabel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 10, 1252, 129));

        jTable2.setBackground(new java.awt.Color(245, 244, 230));
        jTable2.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
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

        jPanel4.add(jScrollPane2, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 660, 1813, 300));

        jTextField2.setBackground(new java.awt.Color(245, 244, 230));
        jTextField2.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jTextField2.setForeground(new java.awt.Color(15, 65, 67));
        jTextField2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextField2ActionPerformed(evt);
            }
        });
        jPanel4.add(jTextField2, new org.netbeans.lib.awtextra.AbsoluteConstraints(1260, 590, 278, 50));

        jButton8.setBackground(new java.awt.Color(245, 244, 230));
        jButton8.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jButton8.setForeground(new java.awt.Color(15, 65, 67));
        jButton8.setText("Cari");
        jButton8.setBorder(null);
        jButton8.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton8ActionPerformed(evt);
            }
        });
        jPanel4.add(jButton8, new org.netbeans.lib.awtextra.AbsoluteConstraints(1550, 590, 143, 50));

        jButton13.setBackground(new java.awt.Color(245, 244, 230));
        jButton13.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jButton13.setForeground(new java.awt.Color(15, 65, 67));
        jButton13.setText("Reset");
        jButton13.setBorder(null);
        jButton13.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton13ActionPerformed(evt);
            }
        });
        jPanel4.add(jButton13, new org.netbeans.lib.awtextra.AbsoluteConstraints(1700, 590, 118, 50));

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

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addGap(0, 13, Short.MAX_VALUE)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jButton2, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 61, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButton9, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 61, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButton11, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 61, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButton12, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 61, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addComponent(jPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, 1845, javax.swing.GroupLayout.PREFERRED_SIZE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(226, 226, 226)
                .addComponent(jButton2, javax.swing.GroupLayout.PREFERRED_SIZE, 61, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jButton9, javax.swing.GroupLayout.PREFERRED_SIZE, 61, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jButton11, javax.swing.GroupLayout.PREFERRED_SIZE, 61, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jButton12, javax.swing.GroupLayout.PREFERRED_SIZE, 61, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addComponent(jPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, 1053, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );

        scaleButtonIcon(jButton2, "/photos/school2.png");
        scaleButtonIcon(jButton9, "/photos/student2.png");
        scaleButtonIcon(jButton11, "/photos/calendar2.png");
        scaleButtonIcon(jButton12, "/photos/exam2.png");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jButton12ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton12ActionPerformed
        // TODO add your handling code here:
        Rapor homeForm = new Rapor();
    homeForm.setVisible(true);
    
    this.dispose();
    }//GEN-LAST:event_jButton12ActionPerformed

    private void jButton12MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jButton12MouseClicked
      Rapor homeForm = new Rapor();
    homeForm.setVisible(true);
    
    this.dispose();   // TODO add your handling code here:
    }//GEN-LAST:event_jButton12MouseClicked

    private void jButton11ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton11ActionPerformed
        // TODO add your handling code here:
        Ujian homeForm = new Ujian();
    homeForm.setVisible(true);
    
    this.dispose();
    }//GEN-LAST:event_jButton11ActionPerformed

    private void jButton11MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jButton11MouseClicked
      Ujian homeForm = new Ujian();
    homeForm.setVisible(true);
    
    this.dispose();  // TODO add your handling code here:
    }//GEN-LAST:event_jButton11MouseClicked

    private void jButton9ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton9ActionPerformed
        this.dispose();
        DataSiswaa homeForm = new DataSiswaa();
        homeForm.setVisible(true);    }//GEN-LAST:event_jButton9ActionPerformed

    private void jButton9MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jButton9MouseClicked
    DataSiswaa homeForm = new DataSiswaa();
    homeForm.setVisible(true);
    
    this.dispose();

    }//GEN-LAST:event_jButton9MouseClicked

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
home homeForm = new home();
    homeForm.setVisible(true);
    
    this.dispose();        // TODO add your handling code here:
    }//GEN-LAST:event_jButton2ActionPerformed

    private void jButton2MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jButton2MouseClicked
        home homeForm = new home();
        homeForm.setVisible(true);

        this.dispose();
    }//GEN-LAST:event_jButton2MouseClicked

    private void jButton4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton4ActionPerformed
        // TODO add your handling code here:
        resetSearch();
    }//GEN-LAST:event_jButton4ActionPerformed

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
            String query = "SELECT * FROM ujian WHERE " +
            "id_ujian LIKE ? OR " +
            "nama_ujian LIKE ? OR " +
            "id_pelajaran LIKE ? OR " +
            "id_kelas LIKE ? OR " +
            "id_ruang LIKE ? OR " +
            "DATE_FORMAT(tanggal_ujian, '%Y-%m-%d') LIKE ? OR " +
            "TIME_FORMAT(waktu_mulai, '%H:%i') LIKE ? OR " +
            "TIME_FORMAT(waktu_selesai, '%H:%i') LIKE ? OR " +
            "durasi_menit LIKE ? OR " +
            "jumlah_peserta LIKE ?";

            PreparedStatement pst = conn.prepareStatement(query);
            String searchPattern = "%" + keyword + "%";

            for (int i = 1; i <= 12; i++) {
                pst.setString(i, searchPattern);
            }

            ResultSet rs = pst.executeQuery();

            DefaultTableModel model = (DefaultTableModel) jTable1.getModel();
            model.setRowCount(0);

            model.setColumnIdentifiers(new String[]{
                "ID Ujian",  "Nama Ujian", "ID Pelajaran", 
                "ID Kelas", "ID Ruang", "Tanggal Ujian", 
                "Waktu Mulai", "Waktu Selesai", "Durasi (Menit)", "Jumlah Peserta"
            });

            boolean hasResults = false;
            while (rs.next()) {
                hasResults = true;
                Object[] row = {
                    rs.getString("id_ujian"),
                    rs.getString("nama_ujian"),
                    rs.getString("id_pelajaran"),
                    rs.getString("id_kelas"),
                    rs.getString("id_ruang"),
                    rs.getDate("tanggal_ujian"),
                    rs.getTime("waktu_mulai"),
                    rs.getTime("waktu_selesai"),
                    rs.getInt("durasi_menit"),
                    rs.getInt("jumlah_peserta")
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

    private void jTextField1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextField1ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jTextField1ActionPerformed

    private void jTable1AncestorMoved(javax.swing.event.AncestorEvent evt) {//GEN-FIRST:event_jTable1AncestorMoved
        // TODO add your handling code here:
    }//GEN-LAST:event_jTable1AncestorMoved

    private void jButton5ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton5ActionPerformed
        // TODO add your handling code here:
    // Button Action Method for Kartu Ujian
    try {
        // Pastikan ada baris yang dipilih di tabel
        int selectedRow = jTable2.getSelectedRow();
        if (selectedRow == -1) {
            JOptionPane.showMessageDialog(this, 
                "Pilih data siswa terlebih dahulu!", 
                "Peringatan", 
                JOptionPane.WARNING_MESSAGE);
            return;
        }
        
        // Ambil data dari baris yang dipilih berdasarkan struktur kolom tabel
        String selectedNIS = jTable2.getValueAt(selectedRow, 1).toString();    // NIS (kolom 1)
        String selectedNama = jTable2.getValueAt(selectedRow, 0).toString();   // Nama (kolom 0)
        
        // Validasi data yang diambil
        if (selectedNIS == null || selectedNIS.trim().isEmpty()) {
            JOptionPane.showMessageDialog(this, 
                "Data NIS tidak valid!", 
                "Error", 
                JOptionPane.ERROR_MESSAGE);
            return;
        }
        
        // Cari file report dengan multiple path
        String reportPath = findKartuUjianReportFile();
        
        if (reportPath == null) {
            // Try to load from resources as fallback
            InputStream reportStream = getClass().getResourceAsStream("/view/Kartufinal.jasper");
            if (reportStream == null) {
                reportStream = getClass().getResourceAsStream("/Kartufinal.jasper");
            }
            
            if (reportStream != null) {
                System.out.println("Found Kartufinal.jasper in resources");
                generateKartuUjianFromStream(reportStream, selectedNIS, selectedNama);
                return;
            }
            
            // If still not found, show error
            JOptionPane.showMessageDialog(this, 
                "File laporan tidak ditemukan: Kartufinal.jasper\n" + 
                "Pastikan file ada di salah satu lokasi berikut:\n" +
                "- Folder yang sama dengan aplikasi\n" +
                "- Folder src/view/\n" +
                "- Folder view/\n" +
                "- Atau di resources", 
                "Error", 
                JOptionPane.ERROR_MESSAGE);
            return;
        }
        
        // Dapatkan koneksi database
        Connection conn = getConnection();
        if (conn == null) {
            JOptionPane.showMessageDialog(this, 
                "Koneksi database gagal!", 
                "Error", 
                JOptionPane.ERROR_MESSAGE);
            return;
        }
        
        // Buat parameter untuk filter data berdasarkan NIS
        HashMap<String, Object> parameters = new HashMap<>();
        parameters.put("NIS_PARAMETER", selectedNIS);
        
        // Debug: Tampilkan NIS yang akan difilter
        System.out.println("Filtering report untuk NIS: " + selectedNIS);
        System.out.println("Using report file: " + reportPath);
        
        // Compile dan tampilkan report
        JasperPrint jasperPrint = JasperFillManager.fillReport(
            reportPath, 
            parameters, 
            conn
        );
        
        // Cek apakah report menghasilkan data
        if (jasperPrint.getPages().isEmpty()) {
            JOptionPane.showMessageDialog(this, 
                "Tidak ada data untuk dicetak!\n" +
                "Pastikan siswa dengan NIS: " + selectedNIS + " memiliki data yang lengkap.", 
                "Peringatan", 
                JOptionPane.WARNING_MESSAGE);
            conn.close();
            return;
        }
        
        // Tampilkan report dalam viewer
        JasperViewer viewer = new JasperViewer(jasperPrint, false);
        viewer.setTitle("Kartu Ujian - " + selectedNama + " (NIS: " + selectedNIS + ")");
        viewer.setVisible(true);
        
        // Tutup koneksi database
        conn.close();
        System.out.println("Report berhasil ditampilkan untuk siswa: " + selectedNama);
        
    } catch (JRException e) {
        JOptionPane.showMessageDialog(this, 
            "Error JasperReport:\n" + e.getMessage() + 
            "\nPastikan file Kartufinal.jasper sudah dikompilasi dengan benar.", 
            "Error JasperReport", 
            JOptionPane.ERROR_MESSAGE);
        e.printStackTrace();
    } catch (SQLException e) {
        JOptionPane.showMessageDialog(this, 
            "Error Database:\n" + e.getMessage() + 
            "\nPastikan koneksi database dan query SQL sudah benar.", 
            "Error Database", 
            JOptionPane.ERROR_MESSAGE);
        e.printStackTrace();
    } catch (Exception e) {
        JOptionPane.showMessageDialog(this, 
            "Error saat mencetak laporan:\n" + e.getMessage(), 
            "Error", 
            JOptionPane.ERROR_MESSAGE);
        e.printStackTrace();
    }
}

private String findKartuUjianReportFile() {
    // Try different possible paths for Kartufinal.jasper
    String[] possiblePaths = {
        "Kartufinal.jasper",                     // Same folder as exe
        "./reports/Kartufinal.jasper",           // reports subfolder
        "./src/view/Kartufinal.jasper",          // Original NetBeans structure
        "./view/Kartufinal.jasper",              // view subfolder
        "./Kartufinal.jasper",                   // Current directory
        "src/view/Kartufinal.jasper"             // Relative to project root
    };
    
    for (String path : possiblePaths) {
        File reportFile = new File(path);
        System.out.println("Checking path: " + path);
        System.out.println("File exists: " + reportFile.exists());
        System.out.println("Absolute path: " + reportFile.getAbsolutePath());
        
        if (reportFile.exists()) {
            System.out.println("Found Kartufinal.jasper at: " + path);
            return reportFile.getAbsolutePath();
        }
    }
    
    System.out.println("Kartufinal.jasper not found in any location");
    return null;
}

private void generateKartuUjianFromStream(InputStream reportStream, String selectedNIS, String selectedNama) {
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
        parameters.put("NIS_PARAMETER", selectedNIS);
        
        System.out.println("Generating Kartu Ujian from resources for NIS: " + selectedNIS);
        
        // Generate report from InputStream
        JasperPrint jasperPrint = JasperFillManager.fillReport(
            reportStream, 
            parameters, 
            conn
        );
        
        // Check if report has content
        if (jasperPrint.getPages().isEmpty()) {
            JOptionPane.showMessageDialog(this, 
                "Tidak ada data untuk dicetak!\n" +
                "Pastikan siswa dengan NIS: " + selectedNIS + " memiliki data yang lengkap.", 
                "Peringatan", 
                JOptionPane.WARNING_MESSAGE);
        } else {
            // Display report
            JasperViewer viewer = new JasperViewer(jasperPrint, false);
            viewer.setTitle("Kartu Ujian - " + selectedNama + " (NIS: " + selectedNIS + ")");
            viewer.setVisible(true);
            System.out.println("Report berhasil ditampilkan dari resources untuk siswa: " + selectedNama);
        }
        
    } catch (JRException e) {
        JOptionPane.showMessageDialog(this, 
            "Error JasperReport:\n" + e.getMessage() + 
            "\nPastikan file Kartufinal.jasper sudah dikompilasi dengan benar.", 
            "Error JasperReport", 
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

    }//GEN-LAST:event_jButton5ActionPerformed

    private void jButton5MouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jButton5MouseExited
        jButton5.setBackground(new Color(15, 65, 67)); // Warna default
        jButton5.setCursor(new Cursor(Cursor.DEFAULT_CURSOR)); // Kursor normal
    }//GEN-LAST:event_jButton5MouseExited

    private void jButton5MouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jButton5MouseEntered
        jButton5.setBackground(new Color(30, 90, 92)); // Warna hover
        jButton5.setCursor(new Cursor(Cursor.HAND_CURSOR)); // Kursor tangan
    }//GEN-LAST:event_jButton5MouseEntered

    private void jButton6ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton6ActionPerformed
        // TODO add your handling code here:
    int selectedRow = jTable1.getSelectedRow();
    
    if (selectedRow == -1) {
        JOptionPane.showMessageDialog(this, "Pilih data yang akan diubah!");
        return;
    }

    // Get data from selected row
    String[] ujianData = new String[10];
    for (int i = 0; i < 10; i++) {
        Object value = jTable1.getValueAt(selectedRow, i);
        ujianData[i] = (value != null) ? value.toString() : "";
    }

    // Open UbahUjian with the selected data
    UbahUjian ubahUjianForm = new UbahUjian(ujianData);
    ubahUjianForm.setVisible(true);
    this.dispose();
    
    }//GEN-LAST:event_jButton6ActionPerformed

    private void jButton6MouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jButton6MouseExited
        jButton6.setBackground(new Color(15, 65, 67)); // Warna default
        jButton6.setCursor(new Cursor(Cursor.DEFAULT_CURSOR)); // Kursor normal
    }//GEN-LAST:event_jButton6MouseExited

    private void jButton6MouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jButton6MouseEntered
        jButton6.setBackground(new Color(30, 90, 92)); // Warna hover
        jButton6.setCursor(new Cursor(Cursor.HAND_CURSOR)); // Kursor tangan
    }//GEN-LAST:event_jButton6MouseEntered

    private void jButton7ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton7ActionPerformed
        // TODO add your handling code here:
        int selectedRow = jTable1.getSelectedRow();

    if (selectedRow == -1) {
        JOptionPane.showMessageDialog(this, "Pilih data yang akan dihapus!");
        return;
    }

    // Konfirmasi hapus
    int confirm = JOptionPane.showConfirmDialog(this,
        "Apakah Anda yakin ingin menghapus data ujian ini?",
        "Konfirmasi Hapus",
        JOptionPane.YES_NO_OPTION);

    if (confirm == JOptionPane.YES_OPTION) {
        try {
            // Ambil ID Ujian dari baris yang dipilih (kolom ke-1, index 0)
            String idUjian = jTable1.getValueAt(selectedRow, 0).toString();

            Connection conn = getConnection();
            if (conn != null) {
                String query = "DELETE FROM ujian WHERE id_ujian = ?";
                PreparedStatement pst = conn.prepareStatement(query);
                pst.setString(1, idUjian);

                int result = pst.executeUpdate();

                if (result > 0) {
                    JOptionPane.showMessageDialog(this, "Data ujian berhasil dihapus!");
                    loadDataToTable(); // Refresh table
                } else {
                    JOptionPane.showMessageDialog(this, "Data ujian gagal dihapus!");
                }

                conn.close();
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(this, "Error deleting data: " + e.getMessage());
        }
    }
    }//GEN-LAST:event_jButton7ActionPerformed

    private void jButton7MouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jButton7MouseExited
        jButton7.setBackground(new Color(15, 65, 67)); // Warna default
        jButton7.setCursor(new Cursor(Cursor.DEFAULT_CURSOR)); // Kursor normal
    }//GEN-LAST:event_jButton7MouseExited

    private void jButton7MouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jButton7MouseEntered
        jButton7.setBackground(new Color(30, 90, 92)); // Warna hover
        jButton7.setCursor(new Cursor(Cursor.HAND_CURSOR)); // Kursor tangan
    }//GEN-LAST:event_jButton7MouseEntered

    private void jButton3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton3ActionPerformed
        // TODO add your handling code here:
    TambahUjian tambahUjianForm = new TambahUjian();
    tambahUjianForm.setVisible(true);
    this.dispose(); 
    }//GEN-LAST:event_jButton3ActionPerformed

    private void jButton3MouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jButton3MouseExited
        jButton3.setBackground(new Color(15, 65, 67)); // Warna default
        jButton3.setCursor(new Cursor(Cursor.DEFAULT_CURSOR)); // Kursor normal
    }//GEN-LAST:event_jButton3MouseExited

    private void jButton3MouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jButton3MouseEntered
        jButton3.setBackground(new Color(30, 90, 92)); // Warna hover
        jButton3.setCursor(new Cursor(Cursor.HAND_CURSOR)); // Kursor tangan
    }//GEN-LAST:event_jButton3MouseEntered

    private void jTable2AncestorMoved(javax.swing.event.AncestorEvent evt) {//GEN-FIRST:event_jTable2AncestorMoved
        // TODO add your handling code here:
    }//GEN-LAST:event_jTable2AncestorMoved

    private void jTextField2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextField2ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jTextField2ActionPerformed

    private void jButton8ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton8ActionPerformed
        // TODO add your handling code here:
    String keyword = jTextField2.getText().trim();
    
    if (keyword.isEmpty()) {
        JOptionPane.showMessageDialog(this, "Masukkan kata kunci pencarian siswa!");
        loadDataToTable2();
        return;
    }
    
    try {
        Connection conn = getConnection();
        if (conn != null) {
            String query = "SELECT * FROM siswa WHERE " +
                          "nama LIKE ? OR " +
                          "NIS LIKE ? OR " +
                          "JK LIKE ? OR " +
                          "NISN LIKE ? OR " +
                          "tempatLahir LIKE ? OR " +
                          "DATE_FORMAT(tanggalLahir, '%Y-%m-%d') LIKE ? OR " +
                          "agama LIKE ? OR " +
                          "alamat LIKE ? OR " +
                          "noTelp LIKE ? OR " +
                          "tahunMasuk LIKE ? OR " +
                          "status LIKE ?";
            
            PreparedStatement pst = conn.prepareStatement(query);
            String searchPattern = "%" + keyword + "%";
            
            for (int i = 1; i <= 11; i++) {
                pst.setString(i, searchPattern);
            }
            
            ResultSet rs = pst.executeQuery();
            
            DefaultTableModel model = (DefaultTableModel) jTable2.getModel();
            model.setRowCount(0);
            
            model.setColumnIdentifiers(new String[]{
                "Nama", "NIS", "JK", "NISN", "Tempat Lahir", 
                "Tanggal Lahir", "Agama", "Alamat", "No Telp", 
                "Tahun Masuk", "Status"
            });
            
            boolean hasResults = false;
            while (rs.next()) {
                hasResults = true;
                Object[] row = {
                    rs.getString("nama"),
                    rs.getString("NIS"),
                    rs.getString("JK"),
                    rs.getString("NISN"),
                    rs.getString("tempatLahir"),
                    rs.getDate("tanggalLahir"),
                    rs.getString("agama"),
                    rs.getString("alamat"),
                    rs.getString("noTelp"),
                    rs.getInt("tahunMasuk"),
                    rs.getString("status")
                };
                model.addRow(row);
            }
            
            if (!hasResults) {
                JOptionPane.showMessageDialog(this, "Data siswa tidak ditemukan untuk: " + keyword);
            } else {
                JOptionPane.showMessageDialog(this, "Ditemukan " + model.getRowCount() + " data siswa untuk: " + keyword);
            }
            
            conn.close();
            
            SwingUtilities.invokeLater(() -> {
                setManualColumnWidthsForStudentTable();
            });
        }
    } catch (SQLException e) {
        JOptionPane.showMessageDialog(this, "Error searching student data: " + e.getMessage());
    }                                      
    }//GEN-LAST:event_jButton8ActionPerformed

    private void jButton13ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton13ActionPerformed
        // TODO add your handling code here:
    resetStudentSearch();
                                        
    }//GEN-LAST:event_jButton13ActionPerformed
    
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
            java.util.logging.Logger.getLogger(Ujian.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Ujian.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Ujian.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Ujian.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Ujian().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton11;
    private javax.swing.JButton jButton12;
    private javax.swing.JButton jButton13;
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
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JTable jTable1;
    private javax.swing.JTable jTable2;
    private javax.swing.JTextField jTextField1;
    private javax.swing.JTextField jTextField2;
    // End of variables declaration//GEN-END:variables

    
}
