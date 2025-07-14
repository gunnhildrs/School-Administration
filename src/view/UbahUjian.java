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
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JOptionPane;

/**
 *
 * @author Ahmad Irsyad Rosyadi
 */

public class UbahUjian extends javax.swing.JFrame {
    
    public UbahUjian() {
    try {
        ImageIcon appIcon = new ImageIcon(home.class.getResource("/photos/logo.png"));
        setIconImage(appIcon.getImage());
    } catch (Exception e) {
        System.out.println("Could not load application icon");
    }
    
    initComponents();
    this.setExtendedState(JFrame.MAXIMIZED_BOTH);
        
    aktif();
    kosong();
}
    
    protected void aktif() {
    jTextField1.requestFocus();
    jTextField1.setEnabled(true);
    }
   
    protected void kosong() {
    jTextField2.setText("");
    jTextField4.setText("");
    jTextField1.setText("");
    jComboBox1.setSelectedIndex(0);
    jComboBox2.setSelectedIndex(0);
     jComboBox8.setSelectedIndex(0);
    jComboBox4.setSelectedIndex(0);
    jComboBox5.setSelectedIndex(0);
    jComboBox6.setSelectedIndex(0);
    jComboBox7.setSelectedIndex(0);
     jComboBox11.setSelectedIndex(0);
     jComboBox10.setSelectedIndex(0);
     jComboBox9.setSelectedIndex(0);
} 
    
  public UbahUjian(String[] ujianData) {
    try {
        ImageIcon appIcon = new ImageIcon(home.class.getResource("/photos/logo.png"));
        setIconImage(appIcon.getImage());
    } catch (Exception e) {
        System.out.println("Could not load application icon");
    }
    
    initComponents();
    this.setExtendedState(JFrame.MAXIMIZED_BOTH);
    
    // Load data untuk combobox
    loadKelasData();
    loadPelajaranData();
    loadRuangData();
    
    // Set data from parameter
    if (ujianData != null && ujianData.length >= 10) {
        setFormData(ujianData);
    }
    
    aktif();
}
  
  private void setFormData(String[] ujianData) {
    // ID Ujian dan Nama Ujian
    jTextField2.setText(ujianData[0]); // ID Ujian
    jTextField4.setText(ujianData[1]); // Nama Ujian
    
    // Set Pelajaran
    setComboBoxValue(jComboBox10, ujianData[2]);
    
    // Set Kelas
    setComboBoxValue(jComboBox11, ujianData[3]);
    
    // Set Ruang
    setComboBoxValue(jComboBox9, ujianData[4]);
    
    // Tanggal Ujian (format: yyyy-mm-dd)
    if (ujianData[5] != null && !ujianData[5].isEmpty()) {
        String[] dateParts = ujianData[5].split("-");
        if (dateParts.length == 3) {
            jComboBox1.setSelectedItem(dateParts[0]); // Tahun
            jComboBox8.setSelectedIndex(Integer.parseInt(dateParts[1]) - 1); // Bulan
            jComboBox4.setSelectedItem(dateParts[2]); // Hari
        }
    }
    
    // Waktu Mulai (format: hh:mm:ss)
    if (ujianData[6] != null && !ujianData[6].isEmpty()) {
        String[] timeParts = ujianData[6].split(":");
        if (timeParts.length >= 2) {
            jComboBox7.setSelectedItem(timeParts[0]); // Jam
            jComboBox6.setSelectedItem(timeParts[1]); // Menit
        }
    }
    
    // Waktu Selesai (format: hh:mm:ss)
    if (ujianData[7] != null && !ujianData[7].isEmpty()) {
        String[] timeParts = ujianData[7].split(":");
        if (timeParts.length >= 2) {
            jComboBox5.setSelectedItem(timeParts[0]); // Jam
            jComboBox2.setSelectedItem(timeParts[1]); // Menit
        }
    }
    
    // Jumlah Peserta
    jTextField1.setText(ujianData[9]);
}

private void setComboBoxValue(JComboBox<String> comboBox, String value) {
    for (int i = 0; i < comboBox.getItemCount(); i++) {
        String item = comboBox.getItemAt(i);
        if (item.startsWith(value + " - ")) {
            comboBox.setSelectedIndex(i);
            return;
        }
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

private void loadKelasData() {
    try {
        Connection conn = getConnection();
        if (conn != null) {
            String query = "SELECT id_kelas, nama_kelas FROM kelas ORDER BY nama_kelas";
            PreparedStatement pst = conn.prepareStatement(query);
            ResultSet rs = pst.executeQuery();
            
             jComboBox11.removeAllItems();
             jComboBox11.addItem("-- Pilih Kelas --");
            
            while (rs.next()) {
                String displayText = rs.getString("id_kelas") + " - " + rs.getString("nama_kelas");
                 jComboBox11.addItem(displayText);
            }
            conn.close();
        }
    } catch (SQLException e) {
        JOptionPane.showMessageDialog(this, "Error loading kelas data: " + e.getMessage());
    }
}

private void loadPelajaranData() {
    try {
        Connection conn = getConnection();
        if (conn != null) {
            String query = "SELECT id_pelajaran, nama_pelajaran FROM mata_pelajaran ORDER BY nama_pelajaran";
            PreparedStatement pst = conn.prepareStatement(query);
            ResultSet rs = pst.executeQuery();
            
             jComboBox10.removeAllItems();
             jComboBox10.addItem("-- Pilih Pelajaran --");
            
            while (rs.next()) {
                String displayText = rs.getString("id_pelajaran") + " - " + rs.getString("nama_pelajaran");
                 jComboBox10.addItem(displayText);
            }
            conn.close();
        }
    } catch (SQLException e) {
        JOptionPane.showMessageDialog(this, "Error loading pelajaran data: " + e.getMessage());
    }
}

private void loadRuangData() {
    try {
        Connection conn = getConnection();
        if (conn != null) {
            String query = "SELECT id_ruang, nama_ruang FROM ruangan ORDER BY nama_ruang";
            PreparedStatement pst = conn.prepareStatement(query);
            ResultSet rs = pst.executeQuery();
            
             jComboBox9.removeAllItems();
             jComboBox9.addItem("-- Pilih Ruang --");
            
            while (rs.next()) {
                String displayText = rs.getString("id_ruang") + " - " + rs.getString("nama_ruang");
                 jComboBox9.addItem(displayText);
            }
            conn.close();
        }
    } catch (SQLException e) {
        JOptionPane.showMessageDialog(this, "Error loading ruang data: " + e.getMessage());
    }
}

// Method untuk mengambil ID dari combobox selection
private String getIdFromComboBox(JComboBox<String> comboBox) {
    String selected = (String) comboBox.getSelectedItem();
    if (selected != null && !selected.startsWith("--")) {
        return selected.split(" - ")[0]; // Ambil bagian ID sebelum " - "
    }
    return "";
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
        jPanel4 = new javax.swing.JPanel();
        jButton7 = new javax.swing.JButton();
        jButton6 = new javax.swing.JButton();
        jButton5 = new javax.swing.JButton();
        jLabel2 = new javax.swing.JLabel();
        jTextField1 = new javax.swing.JTextField();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        jLabel10 = new javax.swing.JLabel();
        jLabel11 = new javax.swing.JLabel();
        jLabel12 = new javax.swing.JLabel();
        jLabel13 = new javax.swing.JLabel();
        jTextField2 = new javax.swing.JTextField();
        jTextField4 = new javax.swing.JTextField();
        jComboBox1 = new javax.swing.JComboBox<>();
        jComboBox2 = new javax.swing.JComboBox<>();
        jComboBox4 = new javax.swing.JComboBox<>();
        jComboBox5 = new javax.swing.JComboBox<>();
        jComboBox6 = new javax.swing.JComboBox<>();
        jComboBox7 = new javax.swing.JComboBox<>();
        jLabel15 = new javax.swing.JLabel();
        jLabel16 = new javax.swing.JLabel();
        jComboBox8 = new javax.swing.JComboBox<>();
        jComboBox9 = new javax.swing.JComboBox<>();
        jComboBox10 = new javax.swing.JComboBox<>();
        jComboBox11 = new javax.swing.JComboBox<>();
        jButton2 = new javax.swing.JButton();
        jButton10 = new javax.swing.JButton();
        jButton11 = new javax.swing.JButton();
        jButton12 = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jPanel1.setBackground(new java.awt.Color(15, 65, 67));

        jPanel4.setBackground(new java.awt.Color(245, 244, 230));
        jPanel4.setAlignmentX(0.0F);
        jPanel4.setAlignmentY(0.0F);
        jPanel4.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jButton7.setBackground(new java.awt.Color(15, 65, 67));
        jButton7.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jButton7.setForeground(new java.awt.Color(245, 244, 230));
        jButton7.setText("Kosongkan Data");
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
        jPanel4.add(jButton7, new org.netbeans.lib.awtextra.AbsoluteConstraints(400, 970, 320, -1));

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
        jPanel4.add(jButton6, new org.netbeans.lib.awtextra.AbsoluteConstraints(750, 970, 320, -1));

        jButton5.setBackground(new java.awt.Color(15, 65, 67));
        jButton5.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jButton5.setForeground(new java.awt.Color(245, 244, 230));
        jButton5.setText("Batal");
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
        jPanel4.add(jButton5, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 970, 320, -1));

        jLabel2.setBackground(new java.awt.Color(15, 65, 67));
        jLabel2.setFont(new java.awt.Font("Tahoma", 1, 24)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(15, 65, 67));
        jLabel2.setText("Jumlah Peserta");
        jLabel2.setHorizontalTextPosition(javax.swing.SwingConstants.LEADING);
        jPanel4.add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(790, 480, -1, 30));

        jTextField1.setBackground(new java.awt.Color(245, 244, 230));
        jTextField1.setFont(new java.awt.Font("Dialog", 0, 18)); // NOI18N
        jTextField1.setForeground(new java.awt.Color(15, 65, 67));
        jTextField1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextField1ActionPerformed(evt);
            }
        });
        jPanel4.add(jTextField1, new org.netbeans.lib.awtextra.AbsoluteConstraints(1030, 480, 350, 40));

        jLabel3.setBackground(new java.awt.Color(15, 65, 67));
        jLabel3.setFont(new java.awt.Font("Tahoma", 1, 72)); // NOI18N
        jLabel3.setForeground(new java.awt.Color(15, 65, 67));
        jLabel3.setText("Management Ujian Tiara School");
        jLabel3.setHorizontalTextPosition(javax.swing.SwingConstants.LEADING);
        jPanel4.add(jLabel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 10, 1252, 129));

        jLabel4.setBackground(new java.awt.Color(15, 65, 67));
        jLabel4.setFont(new java.awt.Font("Tahoma", 1, 24)); // NOI18N
        jLabel4.setForeground(new java.awt.Color(15, 65, 67));
        jLabel4.setText("Tmabah Jadwal Ujian");
        jLabel4.setHorizontalTextPosition(javax.swing.SwingConstants.LEADING);
        jPanel4.add(jLabel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 140, -1, 30));

        jLabel5.setBackground(new java.awt.Color(15, 65, 67));
        jLabel5.setFont(new java.awt.Font("Tahoma", 1, 24)); // NOI18N
        jLabel5.setForeground(new java.awt.Color(15, 65, 67));
        jLabel5.setText("ID Ujian");
        jLabel5.setHorizontalTextPosition(javax.swing.SwingConstants.LEADING);
        jPanel4.add(jLabel5, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 240, -1, 30));

        jLabel7.setBackground(new java.awt.Color(15, 65, 67));
        jLabel7.setFont(new java.awt.Font("Tahoma", 1, 24)); // NOI18N
        jLabel7.setForeground(new java.awt.Color(15, 65, 67));
        jLabel7.setText("Nama Ujian");
        jLabel7.setHorizontalTextPosition(javax.swing.SwingConstants.LEADING);
        jPanel4.add(jLabel7, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 320, -1, 30));

        jLabel8.setBackground(new java.awt.Color(15, 65, 67));
        jLabel8.setFont(new java.awt.Font("Tahoma", 1, 24)); // NOI18N
        jLabel8.setForeground(new java.awt.Color(15, 65, 67));
        jLabel8.setText("ID Pelajaran");
        jLabel8.setHorizontalTextPosition(javax.swing.SwingConstants.LEADING);
        jPanel4.add(jLabel8, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 400, -1, 30));

        jLabel9.setBackground(new java.awt.Color(15, 65, 67));
        jLabel9.setFont(new java.awt.Font("Tahoma", 1, 24)); // NOI18N
        jLabel9.setForeground(new java.awt.Color(15, 65, 67));
        jLabel9.setText("ID Kelas");
        jLabel9.setHorizontalTextPosition(javax.swing.SwingConstants.LEADING);
        jPanel4.add(jLabel9, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 480, -1, 30));

        jLabel10.setBackground(new java.awt.Color(15, 65, 67));
        jLabel10.setFont(new java.awt.Font("Tahoma", 1, 24)); // NOI18N
        jLabel10.setForeground(new java.awt.Color(15, 65, 67));
        jLabel10.setText("ID Ruang");
        jLabel10.setHorizontalTextPosition(javax.swing.SwingConstants.LEADING);
        jPanel4.add(jLabel10, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 560, -1, 30));

        jLabel11.setBackground(new java.awt.Color(15, 65, 67));
        jLabel11.setFont(new java.awt.Font("Tahoma", 1, 24)); // NOI18N
        jLabel11.setForeground(new java.awt.Color(15, 65, 67));
        jLabel11.setText(":");
        jLabel11.setHorizontalTextPosition(javax.swing.SwingConstants.LEADING);
        jPanel4.add(jLabel11, new org.netbeans.lib.awtextra.AbsoluteConstraints(1120, 320, -1, 30));

        jLabel12.setBackground(new java.awt.Color(15, 65, 67));
        jLabel12.setFont(new java.awt.Font("Tahoma", 1, 24)); // NOI18N
        jLabel12.setForeground(new java.awt.Color(15, 65, 67));
        jLabel12.setText("Waktu Mulai");
        jLabel12.setHorizontalTextPosition(javax.swing.SwingConstants.LEADING);
        jPanel4.add(jLabel12, new org.netbeans.lib.awtextra.AbsoluteConstraints(790, 320, -1, 30));

        jLabel13.setBackground(new java.awt.Color(15, 65, 67));
        jLabel13.setFont(new java.awt.Font("Tahoma", 1, 24)); // NOI18N
        jLabel13.setForeground(new java.awt.Color(15, 65, 67));
        jLabel13.setText("Waktu Selesai");
        jLabel13.setHorizontalTextPosition(javax.swing.SwingConstants.LEADING);
        jPanel4.add(jLabel13, new org.netbeans.lib.awtextra.AbsoluteConstraints(790, 400, -1, 30));

        jTextField2.setBackground(new java.awt.Color(245, 244, 230));
        jTextField2.setFont(new java.awt.Font("Dialog", 0, 18)); // NOI18N
        jTextField2.setForeground(new java.awt.Color(15, 65, 67));
        jTextField2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextField2ActionPerformed(evt);
            }
        });
        jPanel4.add(jTextField2, new org.netbeans.lib.awtextra.AbsoluteConstraints(300, 240, 350, 40));

        jTextField4.setBackground(new java.awt.Color(245, 244, 230));
        jTextField4.setFont(new java.awt.Font("Dialog", 0, 18)); // NOI18N
        jTextField4.setForeground(new java.awt.Color(15, 65, 67));
        jTextField4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextField4ActionPerformed(evt);
            }
        });
        jPanel4.add(jTextField4, new org.netbeans.lib.awtextra.AbsoluteConstraints(300, 320, 350, 40));

        jComboBox1.setBackground(new java.awt.Color(245, 244, 230));
        jComboBox1.setFont(new java.awt.Font("Dialog", 1, 14)); // NOI18N
        jComboBox1.setForeground(new java.awt.Color(15, 65, 67));
        jComboBox1.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "2025", "2026", "2027", "2028", "2029", "2030" }));
        jComboBox1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jComboBox1ActionPerformed(evt);
            }
        });
        jPanel4.add(jComboBox1, new org.netbeans.lib.awtextra.AbsoluteConstraints(1300, 240, 150, 40));

        jComboBox2.setBackground(new java.awt.Color(245, 244, 230));
        jComboBox2.setFont(new java.awt.Font("Dialog", 1, 14)); // NOI18N
        jComboBox2.setForeground(new java.awt.Color(15, 65, 67));
        jComboBox2.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "00", "01", "02", "03", "04", "05", "06", "07", "08", "09", "10", "11", "12", "13", "14", "15", "16", "17", "18", "19", "20", "21", "22", "23", "24", "25", "26", "27", "28", "29", "30", "31", "32", "33", "34", "35", "36", "37", "38", "39", "40", "41", "42", "43", "44", "45", "46", "47", "48", "49", "50", "51", "52", "53", "54", "55", "56", "57", "58", "59" }));
        jPanel4.add(jComboBox2, new org.netbeans.lib.awtextra.AbsoluteConstraints(1150, 400, 60, 40));

        jComboBox4.setBackground(new java.awt.Color(245, 244, 230));
        jComboBox4.setFont(new java.awt.Font("Dialog", 1, 14)); // NOI18N
        jComboBox4.setForeground(new java.awt.Color(15, 65, 67));
        jComboBox4.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "1", "2", "3", "4", "5", "6", "7", "8", "9", "10", "11", "12", "13", "14", "15", "16", "17", "18", "19", "20", "21", "22", "23", "24", "25", "26", "27", "28", "29", "30", "31" }));
        jPanel4.add(jComboBox4, new org.netbeans.lib.awtextra.AbsoluteConstraints(1035, 240, 60, 40));

        jComboBox5.setBackground(new java.awt.Color(245, 244, 230));
        jComboBox5.setFont(new java.awt.Font("Dialog", 1, 14)); // NOI18N
        jComboBox5.setForeground(new java.awt.Color(15, 65, 67));
        jComboBox5.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "00", "01", "02", "03", "04", "05", "06", "07", "08", "09", "10", "11", "12", "13", "14", "15", "16", "17", "18", "19", "20", "21", "22", "23" }));
        jPanel4.add(jComboBox5, new org.netbeans.lib.awtextra.AbsoluteConstraints(1035, 400, 60, 40));

        jComboBox6.setBackground(new java.awt.Color(245, 244, 230));
        jComboBox6.setFont(new java.awt.Font("Dialog", 1, 14)); // NOI18N
        jComboBox6.setForeground(new java.awt.Color(15, 65, 67));
        jComboBox6.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "00", "01", "02", "03", "04", "05", "06", "07", "08", "09", "10", "11", "12", "13", "14", "15", "16", "17", "18", "19", "20", "21", "22", "23", "24", "25", "26", "27", "28", "29", "30", "31", "32", "33", "34", "35", "36", "37", "38", "39", "40", "41", "42", "43", "44", "45", "46", "47", "48", "49", "50", "51", "52", "53", "54", "55", "56", "57", "58", "59" }));
        jPanel4.add(jComboBox6, new org.netbeans.lib.awtextra.AbsoluteConstraints(1150, 320, 60, 40));

        jComboBox7.setBackground(new java.awt.Color(245, 244, 230));
        jComboBox7.setFont(new java.awt.Font("Dialog", 1, 14)); // NOI18N
        jComboBox7.setForeground(new java.awt.Color(15, 65, 67));
        jComboBox7.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "00", "01", "02", "03", "04", "05", "06", "07", "08", "09", "10", "11", "12", "13", "14", "15", "16", "17", "18", "19", "20", "21", "22", "23" }));
        jPanel4.add(jComboBox7, new org.netbeans.lib.awtextra.AbsoluteConstraints(1035, 320, 60, 40));

        jLabel15.setBackground(new java.awt.Color(15, 65, 67));
        jLabel15.setFont(new java.awt.Font("Tahoma", 1, 24)); // NOI18N
        jLabel15.setForeground(new java.awt.Color(15, 65, 67));
        jLabel15.setText("Tanggal Ujian");
        jLabel15.setHorizontalTextPosition(javax.swing.SwingConstants.LEADING);
        jPanel4.add(jLabel15, new org.netbeans.lib.awtextra.AbsoluteConstraints(790, 240, -1, 30));

        jLabel16.setBackground(new java.awt.Color(15, 65, 67));
        jLabel16.setFont(new java.awt.Font("Tahoma", 1, 24)); // NOI18N
        jLabel16.setForeground(new java.awt.Color(15, 65, 67));
        jLabel16.setText(":");
        jLabel16.setHorizontalTextPosition(javax.swing.SwingConstants.LEADING);
        jPanel4.add(jLabel16, new org.netbeans.lib.awtextra.AbsoluteConstraints(1120, 400, -1, 30));

        jComboBox8.setBackground(new java.awt.Color(245, 244, 230));
        jComboBox8.setFont(new java.awt.Font("Dialog", 1, 14)); // NOI18N
        jComboBox8.setForeground(new java.awt.Color(15, 65, 67));
        jComboBox8.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Januari", "Februari", "Maret", "April", "Mei", "Juni", "Juli ", "Agustus ", "September", "Oktober", "November", "Desember" }));
        jComboBox8.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jComboBox8ActionPerformed(evt);
            }
        });
        jPanel4.add(jComboBox8, new org.netbeans.lib.awtextra.AbsoluteConstraints(1120, 240, 150, 40));

        jComboBox9.setBackground(new java.awt.Color(245, 244, 230));
        jComboBox9.setFont(new java.awt.Font("Dialog", 1, 14)); // NOI18N
        jComboBox9.setForeground(new java.awt.Color(15, 65, 67));
        jComboBox9.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));
        jPanel4.add(jComboBox9, new org.netbeans.lib.awtextra.AbsoluteConstraints(300, 560, 280, 40));

        jComboBox10.setBackground(new java.awt.Color(245, 244, 230));
        jComboBox10.setFont(new java.awt.Font("Dialog", 1, 14)); // NOI18N
        jComboBox10.setForeground(new java.awt.Color(15, 65, 67));
        jComboBox10.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));
        jPanel4.add(jComboBox10, new org.netbeans.lib.awtextra.AbsoluteConstraints(300, 400, 280, 40));

        jComboBox11.setBackground(new java.awt.Color(245, 244, 230));
        jComboBox11.setFont(new java.awt.Font("Dialog", 1, 14)); // NOI18N
        jComboBox11.setForeground(new java.awt.Color(15, 65, 67));
        jComboBox11.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));
        jPanel4.add(jComboBox11, new org.netbeans.lib.awtextra.AbsoluteConstraints(300, 480, 280, 40));

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

        jButton10.setIcon(new javax.swing.ImageIcon(getClass().getResource("/photos/facy2.png"))); // NOI18N
        jButton10.setBorder(javax.swing.BorderFactory.createCompoundBorder());
        jButton10.setBorderPainted(false);
        jButton10.setContentAreaFilled(false);
        jButton10.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        jButton10.setMargin(new java.awt.Insets(0, 0, 0, 0));
        jButton10.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jButton10MouseClicked(evt);
            }
        });
        jButton10.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton10ActionPerformed(evt);
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
                .addContainerGap(13, Short.MAX_VALUE)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jButton2, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 61, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButton10, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 61, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButton11, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 61, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButton12, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 61, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addComponent(jPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, 1835, javax.swing.GroupLayout.PREFERRED_SIZE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(226, 226, 226)
                .addComponent(jButton2, javax.swing.GroupLayout.PREFERRED_SIZE, 61, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jButton10, javax.swing.GroupLayout.PREFERRED_SIZE, 61, javax.swing.GroupLayout.PREFERRED_SIZE)
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
        scaleButtonIcon(jButton10, "/photos/facy2.png");
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
 Rapor homeForm = new Rapor();
    homeForm.setVisible(true);
    
    this.dispose();        // TODO add your handling code here:
    }//GEN-LAST:event_jButton12ActionPerformed

    private void jButton12MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jButton12MouseClicked
     Rapor homeForm = new Rapor();
    homeForm.setVisible(true);
    
    this.dispose();    // TODO add your handling code here:
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
    
    this.dispose();    // TODO add your handling code here:
    }//GEN-LAST:event_jButton11MouseClicked

    private void jButton10ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton10ActionPerformed
DataSiswaa homeForm = new DataSiswaa();
    homeForm.setVisible(true);
    
    this.dispose();        // TODO add your handling code here:
    }//GEN-LAST:event_jButton10ActionPerformed

    private void jButton10MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jButton10MouseClicked
        DataSiswaa homeForm = new DataSiswaa();
    homeForm.setVisible(true);
    
    this.dispose();

    }//GEN-LAST:event_jButton10MouseClicked

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

    private void jTextField1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextField1ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jTextField1ActionPerformed

    private void jButton5ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton5ActionPerformed
        // TODO add your handling code here:
        Ujian ujianForm = new Ujian();
        ujianForm.setVisible(true);
        this.dispose();
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
    try {
        // Validate required fields
        if (jTextField2.getText().isEmpty() || jTextField4.getText().isEmpty() || 
             jComboBox10.getSelectedIndex() == 0 || jComboBox11.getSelectedIndex() == 0 ||
             jComboBox9.getSelectedIndex() == 0 || 
            jTextField1.getText().isEmpty()) {
            JOptionPane.showMessageDialog(this, "Harap isi semua field yang diperlukan!");
            return;
        }
        
        // Build the date string from comboboxes
        String tanggalUjian = jComboBox1.getSelectedItem() + "-" + 
                            String.format("%02d", jComboBox8.getSelectedIndex() + 1) + "-" + 
                            String.format("%02d", Integer.parseInt((String)jComboBox4.getSelectedItem()));
        
        // Build time strings
        String waktuMulai = jComboBox7.getSelectedItem() + ":" + jComboBox6.getSelectedItem() + ":00";
        String waktuSelesai = jComboBox5.getSelectedItem() + ":" + jComboBox2.getSelectedItem() + ":00";
        
        // Get IDs from comboboxes
        String idPelajaran = getIdFromComboBox(jComboBox10);
        String idKelas = getIdFromComboBox(jComboBox11);
        String idRuang = getIdFromComboBox(jComboBox9);
        
        Connection conn = getConnection();
        if (conn != null) {
            String query = "UPDATE ujian SET " +
                         "nama_ujian = ?, " +
                         "id_pelajaran = ?, " +
                         "id_kelas = ?, " +
                         "id_ruang = ?, " +
                         "tanggal_ujian = ?, " +
                         "waktu_mulai = ?, " +
                         "waktu_selesai = ?, " +
                         "jumlah_peserta = ? " +
                         "WHERE id_ujian = ?";
            
            PreparedStatement pst = conn.prepareStatement(query);
            pst.setString(1, jTextField4.getText());  // nama_ujian
            pst.setString(2, idPelajaran);            // id_pelajaran
            pst.setString(3, idKelas);                // id_kelas
            pst.setString(4, idRuang);                // id_ruang
            pst.setString(5, tanggalUjian);           // tanggal_ujian
            pst.setString(6, waktuMulai);             // waktu_mulai
            pst.setString(7, waktuSelesai);           // waktu_selesai
            pst.setString(8, jTextField1.getText());  // jumlah_peserta
            pst.setString(9, jTextField2.getText());  // id_ujian (WHERE condition)
            
            int result = pst.executeUpdate();
            
            if (result > 0) {
                JOptionPane.showMessageDialog(this, "Data ujian berhasil diubah!");
                kosong(); // Clear form after successful save
            } else {
                JOptionPane.showMessageDialog(this, "Gagal mengubah data ujian!");
            }
            
            conn.close();
        }
    } catch (SQLException e) {
        JOptionPane.showMessageDialog(this, "Error updating data: " + e.getMessage());
        e.printStackTrace();
    } catch (NumberFormatException e) {
        JOptionPane.showMessageDialog(this, "Format data tidak valid: " + e.getMessage());
    } catch (Exception e) {
        JOptionPane.showMessageDialog(this, "Unexpected error: " + e.getMessage());
        e.printStackTrace();
    }
    
    Ujian ujianForm = new Ujian();
    ujianForm.setVisible(true);
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
        kosong();
        JOptionPane.showMessageDialog(this, "Form telah dikosongkan");
    }//GEN-LAST:event_jButton7ActionPerformed

    private void jButton7MouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jButton7MouseExited
        jButton7.setBackground(new Color(15, 65, 67)); // Warna default
        jButton7.setCursor(new Cursor(Cursor.DEFAULT_CURSOR)); // Kursor normal
    }//GEN-LAST:event_jButton7MouseExited

    private void jButton7MouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jButton7MouseEntered
        jButton7.setBackground(new Color(30, 90, 92)); // Warna hover
        jButton7.setCursor(new Cursor(Cursor.HAND_CURSOR)); // Kursor tangan
    }//GEN-LAST:event_jButton7MouseEntered

    private void jTextField2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextField2ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jTextField2ActionPerformed

    private void jTextField4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextField4ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jTextField4ActionPerformed

    private void jComboBox1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jComboBox1ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jComboBox1ActionPerformed

    private void jComboBox8ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jComboBox8ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jComboBox8ActionPerformed
    
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
            java.util.logging.Logger.getLogger(UbahUjian.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(UbahUjian.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(UbahUjian.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(UbahUjian.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new UbahUjian().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButton10;
    private javax.swing.JButton jButton11;
    private javax.swing.JButton jButton12;
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButton5;
    private javax.swing.JButton jButton6;
    private javax.swing.JButton jButton7;
    private javax.swing.JComboBox<String> jComboBox1;
    private javax.swing.JComboBox<String> jComboBox10;
    private javax.swing.JComboBox<String> jComboBox11;
    private javax.swing.JComboBox<String> jComboBox2;
    private javax.swing.JComboBox<String> jComboBox4;
    private javax.swing.JComboBox<String> jComboBox5;
    private javax.swing.JComboBox<String> jComboBox6;
    private javax.swing.JComboBox<String> jComboBox7;
    private javax.swing.JComboBox<String> jComboBox8;
    private javax.swing.JComboBox<String> jComboBox9;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel16;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JTextField jTextField1;
    private javax.swing.JTextField jTextField2;
    private javax.swing.JTextField jTextField4;
    // End of variables declaration//GEN-END:variables
    
}
