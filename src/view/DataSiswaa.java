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
import java.awt.HeadlessException;
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

public class DataSiswaa extends javax.swing.JFrame {

    private String reportPath;
    
    public DataSiswaa() {
    try {
        ImageIcon appIcon = new ImageIcon(home.class.getResource("/photos/logo.png"));
        setIconImage(appIcon.getImage());
    } catch (Exception e) {
        System.out.println("Could not load application icon");
    }
    
    initComponents();
    this.setExtendedState(JFrame.MAXIMIZED_BOTH);
    
    // Inisialisasi tabel dengan struktur UJIAN yang baru
    DefaultTableModel model = new DefaultTableModel();
    model.setColumnIdentifiers(new String[]{
        "ID Ujian", "Kode Ujian", "Nama Ujian", "ID Pelajaran", 
        "ID Kelas", "ID Ruang", "Jenis Ujian", "Tanggal Ujian", 
        "Waktu Mulai", "Waktu Selesai", "Durasi (Menit)", "Jumlah Peserta"
    });
    jTable1.setModel(model);
    
    // PENGATURAN MANUAL UNTUK TABEL
    jTable1.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
    jTable1.getTableHeader().setReorderingAllowed(false);
    jTable1.setRowHeight(25);
    
    // TAMBAHKAN INI: Kustomisasi header tabel
    customizeTableHeader();
    
    aktif();
    kosong();
    loadDataToTable();
    addComponentListener(new java.awt.event.ComponentAdapter() {
        @Override
        public void componentShown(java.awt.event.ComponentEvent evt) {
            SwingUtilities.invokeLater(() -> {
                setManualColumnWidths();
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
    
    private void setManualColumnWidths() {
    // Gunakan SwingUtilities.invokeLater untuk memastikan tabel sudah ter-render
    SwingUtilities.invokeLater(() -> {
        TableColumnModel columnModel = jTable1.getColumnModel();
        
        // ATUR LEBAR SETIAP KOLOM SECARA MANUAL (dalam pixel)
        int[] columnWidths = {
            300,  // Nama
            80,   // NIS
            50,   // JK
            110,  // NISN
            100,  // Tempat Lahir
            100,  // Tanggal Lahir
            80,   // Agama
            670,  // Alamat - lebih lebar
            120,  // No Telp
            100,  // Tahun Masuk
            80    // Status
        };
        
        // Terapkan lebar ke setiap kolom
        for (int i = 0; i < columnWidths.length && i < columnModel.getColumnCount(); i++) {
            TableColumn column = columnModel.getColumn(i);
            column.setPreferredWidth(columnWidths[i]);
            column.setMinWidth(50); // Set minimum width yang wajar
            column.setMaxWidth(columnWidths[i] * 2); // Beri sedikit fleksibilitas
        }
        
        // Pastikan auto resize OFF dan tabel di-repaint
        jTable1.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
        jTable1.revalidate();
        jTable1.repaint();
    });
}
    
protected void loadDataToTable() {
    try {
        Connection conn = getConnection();
        if (conn != null) {
            String query = "SELECT * FROM siswa";
            PreparedStatement pst = conn.prepareStatement(query);
            ResultSet rs = pst.executeQuery();
            
            // Clear existing data
            DefaultTableModel model = (DefaultTableModel) jTable1.getModel();
            model.setRowCount(0);
            
            // Set column headers sesuai struktur database baru
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
            
            // GANTI INI: Jangan panggil autoResizeTableColumns()
            // autoResizeTableColumns(); // HAPUS BARIS INI
            
            // TAMBAH INI: Pastikan lebar manual tetap diterapkan
            SwingUtilities.invokeLater(() -> {
            setManualColumnWidths();
            });
        }
    } catch (SQLException e) {
        JOptionPane.showMessageDialog(this, "Error loading data: " + e.getMessage());
    }
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
        jLabel3 = new javax.swing.JLabel();
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
        jButton2 = new javax.swing.JButton();
        jButton9 = new javax.swing.JButton();
        jButton11 = new javax.swing.JButton();
        jButton12 = new javax.swing.JButton();
        jButton4 = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jPanel1.setBackground(new java.awt.Color(15, 65, 67));

        jPanel5.setBackground(new java.awt.Color(245, 244, 230));

        jLabel3.setBackground(new java.awt.Color(15, 65, 67));
        jLabel3.setFont(new java.awt.Font("Tahoma", 1, 72)); // NOI18N
        jLabel3.setForeground(new java.awt.Color(15, 65, 67));
        jLabel3.setText("Tiara School");
        jLabel3.setHorizontalTextPosition(javax.swing.SwingConstants.LEADING);

        javax.swing.GroupLayout jPanel5Layout = new javax.swing.GroupLayout(jPanel5);
        jPanel5.setLayout(jPanel5Layout);
        jPanel5Layout.setHorizontalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addGap(16, 16, 16)
                .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 805, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel5Layout.setVerticalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel3, javax.swing.GroupLayout.DEFAULT_SIZE, 129, Short.MAX_VALUE)
                .addContainerGap())
        );

        jPanel4.setBackground(new java.awt.Color(245, 244, 230));
        jPanel4.setAlignmentX(0.0F);
        jPanel4.setAlignmentY(0.0F);
        jPanel4.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jButton3.setBackground(new java.awt.Color(15, 65, 67));
        jButton3.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jButton3.setForeground(new java.awt.Color(245, 244, 230));
        jButton3.setText("Cetak Surat Kelulusan");
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
        jPanel4.add(jButton3, new org.netbeans.lib.awtextra.AbsoluteConstraints(15, 111, 669, -1));

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
        jPanel4.add(jButton7, new org.netbeans.lib.awtextra.AbsoluteConstraints(1144, 111, 669, -1));

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
        jPanel4.add(jButton6, new org.netbeans.lib.awtextra.AbsoluteConstraints(1144, 27, 669, -1));

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
        jPanel4.add(jButton5, new org.netbeans.lib.awtextra.AbsoluteConstraints(15, 27, 669, -1));

        jLabel2.setBackground(new java.awt.Color(15, 65, 67));
        jLabel2.setFont(new java.awt.Font("Tahoma", 1, 72)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(15, 65, 67));
        jLabel2.setText("Data Siswa");
        jLabel2.setHorizontalTextPosition(javax.swing.SwingConstants.LEADING);
        jPanel4.add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(722, 54, -1, 91));

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

        jPanel4.add(jScrollPane1, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 210, 1820, 610));

        jTextField1.setBackground(new java.awt.Color(15, 65, 67));
        jTextField1.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jTextField1.setForeground(new java.awt.Color(255, 255, 255));
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
                .addGap(0, 13, Short.MAX_VALUE)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jButton2, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 61, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButton9, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 61, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButton11, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 61, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButton12, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 61, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jPanel5, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel4, javax.swing.GroupLayout.DEFAULT_SIZE, 1835, Short.MAX_VALUE)))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jTextField1, javax.swing.GroupLayout.PREFERRED_SIZE, 278, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 143, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jButton4, javax.swing.GroupLayout.PREFERRED_SIZE, 118, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jPanel5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jButton1, javax.swing.GroupLayout.DEFAULT_SIZE, 53, Short.MAX_VALUE)
                            .addComponent(jTextField1, javax.swing.GroupLayout.PREFERRED_SIZE, 53, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jButton4, javax.swing.GroupLayout.DEFAULT_SIZE, 53, Short.MAX_VALUE))
                        .addGap(18, 18, 18)
                        .addComponent(jPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(226, 226, 226)
                        .addComponent(jButton2, javax.swing.GroupLayout.PREFERRED_SIZE, 61, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(jButton9, javax.swing.GroupLayout.PREFERRED_SIZE, 61, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(jButton11, javax.swing.GroupLayout.PREFERRED_SIZE, 61, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(jButton12, javax.swing.GroupLayout.PREFERRED_SIZE, 61, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
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
                .addGap(0, 1, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jTextField1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextField1ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jTextField1ActionPerformed

    private void jButton5ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton5ActionPerformed
        // TODO add your handling code here:
        TambahSiswa tambahSiswaForm = new TambahSiswa();
    tambahSiswaForm.setVisible(true);
    this.dispose();
    }//GEN-LAST:event_jButton5ActionPerformed

    private void jButton3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton3ActionPerformed
        // TODO add your handling code here:
    try {
    // Pastikan ada baris yang dipilih di tabel
    int selectedRow = jTable1.getSelectedRow();
    if (selectedRow == -1) {
        JOptionPane.showMessageDialog(this, 
            "Pilih data siswa terlebih dahulu!", 
            "Peringatan", 
            JOptionPane.WARNING_MESSAGE);
        return;
    }
    
    // Ambil data dari baris yang dipilih berdasarkan struktur kolom tabel
    String selectedNIS = jTable1.getValueAt(selectedRow, 1).toString();    // NIS (kolom 1)
    String selectedNama = jTable1.getValueAt(selectedRow, 0).toString();   // Nama (kolom 0)
    
    // Validasi data yang diambil
    if (selectedNIS == null || selectedNIS.trim().isEmpty()) {
        JOptionPane.showMessageDialog(this, 
            "Data NIS tidak valid!", 
            "Error", 
            JOptionPane.ERROR_MESSAGE);
        return;
    }
    
    // Deklarasi reportPath di luar blok if
    String reportPath = null;
    
    // Coba beberapa lokasi untuk file report
    String[] possiblePaths = {
        "src/view/Suket_lulus.jasper",
        "./src/view/Suket_lulus.jasper",
        "./view/Suket_lulus.jasper",
        "Suket_lulus.jasper"
    };
    
    // Cari file report
    for (String path : possiblePaths) {
        File reportFile = new File(path);
        System.out.println("Checking path: " + path);
        System.out.println("File exists: " + reportFile.exists());
        System.out.println("Absolute path: " + reportFile.getAbsolutePath());
        
        if (reportFile.exists()) {
            reportPath = reportFile.getAbsolutePath();
            System.out.println("Found report at: " + reportPath);
            break;
        }
    }
    
    // Jika file tidak ditemukan dengan path external, coba dari resources
    if (reportPath == null) {
        System.out.println("File tidak ditemukan di external path, mencoba dari resources...");
        
        // Coba dari resources
        InputStream reportStream = getClass().getResourceAsStream("/view/Suket_lulus.jasper");
        if (reportStream == null) {
            reportStream = getClass().getResourceAsStream("/Suket_lulus.jasper");
        }
        
        if (reportStream != null) {
            System.out.println("Found report in resources");
            
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
            
            System.out.println("Filtering report untuk NIS: " + selectedNIS);
            
            // Compile dan tampilkan report dari InputStream
            JasperPrint jasperPrint = JasperFillManager.fillReport(
                reportStream, 
                parameters, 
                conn
            );
            
            // Cek apakah report menghasilkan data
            if (jasperPrint.getPages().isEmpty()) {
                JOptionPane.showMessageDialog(this, 
                    "Tidak ada data untuk dicetak!\n" +
                    "Pastikan siswa dengan NIS: " + selectedNIS + " memiliki data nilai.", 
                    "Peringatan", 
                    JOptionPane.WARNING_MESSAGE);
                conn.close();
                return;
            }
            
            // Tampilkan report dalam viewer
            JasperViewer viewer = new JasperViewer(jasperPrint, false);
            viewer.setTitle("Surat Keterangan Lulus - " + selectedNama + " (NIS: " + selectedNIS + ")");
            viewer.setVisible(true);
            
            // Tutup koneksi database
            conn.close();
            System.out.println("Report berhasil ditampilkan untuk siswa: " + selectedNama);
            return;
        }
    }
    
    // Jika file report tidak ditemukan sama sekali
    if (reportPath == null) {
        JOptionPane.showMessageDialog(this, 
            "File report 'Suket_lulus.jasper' tidak ditemukan!\n" +
            "Pastikan file ada di folder:\n" +
            "- src/view/\n" +
            "- atau di resources", 
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
            "Pastikan siswa dengan NIS: " + selectedNIS + " memiliki data nilai.", 
            "Peringatan", 
            JOptionPane.WARNING_MESSAGE);
        conn.close();
        return;
    }
    
    // Tampilkan report dalam viewer
    JasperViewer viewer = new JasperViewer(jasperPrint, false);
    viewer.setTitle("Surat Keterangan Lulus - " + selectedNama + " (NIS: " + selectedNIS + ")");
    viewer.setVisible(true);
    
    // Tutup koneksi database
    conn.close();
    System.out.println("Report berhasil ditampilkan untuk siswa: " + selectedNama);
    
} catch (JRException e) {
    JOptionPane.showMessageDialog(this, 
        "Error JasperReport:\n" + e.getMessage() + 
        "\nPastikan file Suket_lulus.jasper sudah dikompilasi dengan benar.", 
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
    
    }//GEN-LAST:event_jButton3ActionPerformed

    private void jButton6ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton6ActionPerformed
        // TODO add your handling code here:
        int selectedRow = jTable1.getSelectedRow();
    
        if (selectedRow == -1) {
            JOptionPane.showMessageDialog(this, "Pilih data yang akan diubah!");
            return;
        }

        // Get data from selected row
        String[] studentData = new String[11];
        for (int i = 0; i < 11; i++) {
            Object value = jTable1.getValueAt(selectedRow, i);
            studentData[i] = (value != null) ? value.toString() : "";
        }

        // Open UbahSiswa with the selected data
        UbahSiswaa ubahSiswaForm = new UbahSiswaa(studentData);
        ubahSiswaForm.setVisible(true);
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
        "Apakah Anda yakin ingin menghapus data ini?", 
        "Konfirmasi Hapus", 
        JOptionPane.YES_NO_OPTION);
    
    if (confirm == JOptionPane.YES_OPTION) {
        try {
            // Ambil NIS dari baris yang dipilih (kolom ke-2, index 1)
            String nis = jTable1.getValueAt(selectedRow, 1).toString();
            
            Connection conn = getConnection();
            if (conn != null) {
                String query = "DELETE FROM siswa WHERE NIS = ?";
                PreparedStatement pst = conn.prepareStatement(query);
                pst.setString(1, nis);
                
                int result = pst.executeUpdate();
                
                if (result > 0) {
                    JOptionPane.showMessageDialog(this, "Data berhasil dihapus!");
                    loadDataToTable(); // Refresh table
                } else {
                    JOptionPane.showMessageDialog(this, "Data gagal dihapus!");
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
            
            DefaultTableModel model = (DefaultTableModel) jTable1.getModel();
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
            
            
            
            conn.close();
            
            // GANTI INI: Jangan panggil autoResizeTableColumns()
            // autoResizeTableColumns(); // HAPUS BARIS INI
            
            // TAMBAH INI: Terapkan lebar manual
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
    DataSiswaa homeForm = new DataSiswaa();
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
        Ujian homeForm = new Ujian();
        homeForm.setVisible(true);

        this.dispose();
    }//GEN-LAST:event_jButton11MouseClicked

    private void jButton11ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton11ActionPerformed
        // TODO add your handling code here:
    Ujian homeForm = new Ujian();
    homeForm.setVisible(true);
    
    this.dispose(); 
    }//GEN-LAST:event_jButton11ActionPerformed

    private void jButton12MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jButton12MouseClicked
        // TODO add your handling code here:
         Rapor homeForm = new Rapor();
    homeForm.setVisible(true);
    
    this.dispose();
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
            java.util.logging.Logger.getLogger(DataSiswaa.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(DataSiswaa.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(DataSiswaa.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(DataSiswaa.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new DataSiswaa().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton11;
    private javax.swing.JButton jButton12;
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButton3;
    private javax.swing.JButton jButton4;
    private javax.swing.JButton jButton5;
    private javax.swing.JButton jButton6;
    private javax.swing.JButton jButton7;
    private javax.swing.JButton jButton9;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable jTable1;
    private javax.swing.JTextField jTextField1;
    // End of variables declaration//GEN-END:variables

    
}
