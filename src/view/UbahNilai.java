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

public class UbahNilai extends javax.swing.JFrame {
    
      public UbahNilai() {
        try {
            ImageIcon appIcon = new ImageIcon(home.class.getResource("/photos/logo.png"));
            setIconImage(appIcon.getImage());
        } catch (Exception e) {
            System.out.println("Could not load application icon");
        }
        
        initComponents();
        this.setExtendedState(JFrame.MAXIMIZED_BOTH);
        loadPelajaranData();
        loadKelasData();
    }
    
    public UbahNilai(String[] nilaiData) {
        this();
        
        jLabel4.setText("Ubah Data Nilai");
        jButton6.setText("Simpan Perubahan");
        
        if (nilaiData != null) {
            fillFormWithData(nilaiData);
        }
        
        // Make NIS field non-editable
        jTextField2.setEditable(false);
    }
           
    protected void kosong() {
        jTextField2.setText("");
        jComboBox9.setSelectedIndex(0);
        jComboBox10.setSelectedIndex(0);
        jComboBox8.setSelectedIndex(0);
        jTextField3.setText("");
        jTextField1.setText("");
       
    }
    
    private void loadPelajaranData() {
        try {
            Connection conn = getConnection();
            if (conn != null) {
                String query = "SELECT nama_pelajaran FROM mata_pelajaran";
                Statement stmt = conn.createStatement();
                ResultSet rs = stmt.executeQuery(query);
                
                jComboBox9.removeAllItems();
                while (rs.next()) {
                    jComboBox9.addItem(rs.getString("nama_pelajaran"));
                }
                
                rs.close();
                stmt.close();
                conn.close();
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(this, "Error loading pelajaran data: " + e.getMessage());
        }
    }
    
    private void loadKelasData() {
        try {
            Connection conn = getConnection();
            if (conn != null) {
                String query = "SELECT nama_kelas FROM kelas";
                Statement stmt = conn.createStatement();
                ResultSet rs = stmt.executeQuery(query);
                
                jComboBox10.removeAllItems();
                while (rs.next()) {
                    jComboBox10.addItem(rs.getString("nama_kelas"));
                }
                
                rs.close();
                stmt.close();
                conn.close();
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(this, "Error loading kelas data: " + e.getMessage());
        }
    }
    
    private Connection getConnection() {
        try {
            String url = "jdbc:mysql://localhost:3306/tiara_school";
            String username = "root";
            String password = "";
            return DriverManager.getConnection(url, username, password);
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(this, "Koneksi database gagal: " + e.getMessage());
            return null;
        }
    }

    private void fillFormWithData(String[] nilaiData) {
        if (nilaiData == null || nilaiData.length < 6) return;
        
        try {
            // Fill the form fields
            jTextField2.setText(nilaiData[0]); // NIS (read-only)
            jComboBox9.setSelectedItem(nilaiData[1]); // nama_pelajaran
            jComboBox10.setSelectedItem(nilaiData[2]); // id_kelas
            jComboBox8.setSelectedItem(nilaiData[3]); // semester
            jTextField3.setText(nilaiData[4]); // tahun_ajaran
            jTextField1.setText(nilaiData[5]); // nilai_akhir
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Error filling form: " + e.getMessage());
        }
    }

    
    private void loadKompreData() {
    try {
        String nis = jTextField2.getText();
        String namaPelajaran = (String) jComboBox9.getSelectedItem();
        
        if (nis.isEmpty() || namaPelajaran == null) {
            return;
        }
        
        Connection conn = getConnection();
        if (conn != null) {
            String query = "SELECT kompre FROM nilai WHERE NIS = ? AND nama_pelajaran = ?";
            PreparedStatement pst = conn.prepareStatement(query);
            pst.setString(1, nis);
            pst.setString(2, namaPelajaran);
            
            ResultSet rs = pst.executeQuery();
            
            if (rs.next()) {
                String kompre = rs.getString("kompre");
                if (kompre != null) {
                    jTextArea1.setText(kompre);
                }
            }
            
            rs.close();
            pst.close();
            conn.close();
        }
    } catch (SQLException e) {
        JOptionPane.showMessageDialog(this, "Error loading kompre data: " + e.getMessage());
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
        jPanel4 = new javax.swing.JPanel();
        jButton7 = new javax.swing.JButton();
        jButton6 = new javax.swing.JButton();
        jButton5 = new javax.swing.JButton();
        jLabel2 = new javax.swing.JLabel();
        jTextField1 = new javax.swing.JTextField();
        jLabel3 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        jLabel13 = new javax.swing.JLabel();
        jLabel14 = new javax.swing.JLabel();
        jTextField2 = new javax.swing.JTextField();
        jComboBox8 = new javax.swing.JComboBox<>();
        jPanel2 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTextArea1 = new javax.swing.JTextArea();
        jTextField3 = new javax.swing.JTextField();
        jLabel6 = new javax.swing.JLabel();
        jComboBox9 = new javax.swing.JComboBox<>();
        jLabel4 = new javax.swing.JLabel();
        jComboBox10 = new javax.swing.JComboBox<>();
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
        jButton7.setText("Tampilkan Kompetensi");
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
        jLabel2.setText("Nilai");
        jLabel2.setHorizontalTextPosition(javax.swing.SwingConstants.LEADING);
        jPanel4.add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(780, 560, -1, 30));

        jTextField1.setBackground(new java.awt.Color(245, 244, 230));
        jTextField1.setFont(new java.awt.Font("Dialog", 0, 18)); // NOI18N
        jTextField1.setForeground(new java.awt.Color(15, 65, 67));
        jTextField1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextField1ActionPerformed(evt);
            }
        });
        jPanel4.add(jTextField1, new org.netbeans.lib.awtextra.AbsoluteConstraints(1020, 550, 420, 40));

        jLabel3.setBackground(new java.awt.Color(15, 65, 67));
        jLabel3.setFont(new java.awt.Font("Tahoma", 1, 72)); // NOI18N
        jLabel3.setForeground(new java.awt.Color(15, 65, 67));
        jLabel3.setText("Tiara School");
        jLabel3.setHorizontalTextPosition(javax.swing.SwingConstants.LEADING);
        jPanel4.add(jLabel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 10, 1252, 129));

        jLabel5.setBackground(new java.awt.Color(15, 65, 67));
        jLabel5.setFont(new java.awt.Font("Tahoma", 1, 24)); // NOI18N
        jLabel5.setForeground(new java.awt.Color(15, 65, 67));
        jLabel5.setText("NIS");
        jLabel5.setHorizontalTextPosition(javax.swing.SwingConstants.LEADING);
        jPanel4.add(jLabel5, new org.netbeans.lib.awtextra.AbsoluteConstraints(60, 390, -1, 30));

        jLabel7.setBackground(new java.awt.Color(15, 65, 67));
        jLabel7.setFont(new java.awt.Font("Tahoma", 1, 24)); // NOI18N
        jLabel7.setForeground(new java.awt.Color(15, 65, 67));
        jLabel7.setText("Kelas");
        jLabel7.setHorizontalTextPosition(javax.swing.SwingConstants.LEADING);
        jPanel4.add(jLabel7, new org.netbeans.lib.awtextra.AbsoluteConstraints(60, 470, -1, 30));

        jLabel9.setBackground(new java.awt.Color(15, 65, 67));
        jLabel9.setFont(new java.awt.Font("Tahoma", 1, 24)); // NOI18N
        jLabel9.setForeground(new java.awt.Color(15, 65, 67));
        jLabel9.setText("Semester");
        jLabel9.setHorizontalTextPosition(javax.swing.SwingConstants.LEADING);
        jPanel4.add(jLabel9, new org.netbeans.lib.awtextra.AbsoluteConstraints(780, 390, -1, 30));

        jLabel13.setBackground(new java.awt.Color(15, 65, 67));
        jLabel13.setFont(new java.awt.Font("Tahoma", 1, 24)); // NOI18N
        jLabel13.setForeground(new java.awt.Color(15, 65, 67));
        jLabel13.setText("Catatan Kompetensi");
        jLabel13.setHorizontalTextPosition(javax.swing.SwingConstants.LEADING);
        jPanel4.add(jLabel13, new org.netbeans.lib.awtextra.AbsoluteConstraints(60, 630, -1, 30));

        jLabel14.setBackground(new java.awt.Color(15, 65, 67));
        jLabel14.setFont(new java.awt.Font("Tahoma", 1, 24)); // NOI18N
        jLabel14.setForeground(new java.awt.Color(15, 65, 67));
        jLabel14.setText("Pelajaran");
        jLabel14.setHorizontalTextPosition(javax.swing.SwingConstants.LEADING);
        jPanel4.add(jLabel14, new org.netbeans.lib.awtextra.AbsoluteConstraints(60, 550, -1, 30));

        jTextField2.setBackground(new java.awt.Color(245, 244, 230));
        jTextField2.setFont(new java.awt.Font("Dialog", 0, 18)); // NOI18N
        jTextField2.setForeground(new java.awt.Color(15, 65, 67));
        jTextField2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextField2ActionPerformed(evt);
            }
        });
        jPanel4.add(jTextField2, new org.netbeans.lib.awtextra.AbsoluteConstraints(310, 390, 350, 40));

        jComboBox8.setBackground(new java.awt.Color(245, 244, 230));
        jComboBox8.setFont(new java.awt.Font("Dialog", 1, 18)); // NOI18N
        jComboBox8.setForeground(new java.awt.Color(15, 65, 67));
        jComboBox8.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "1", "2" }));
        jComboBox8.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jComboBox8ActionPerformed(evt);
            }
        });
        jPanel4.add(jComboBox8, new org.netbeans.lib.awtextra.AbsoluteConstraints(1020, 390, 350, 40));

        jPanel2.setBackground(new java.awt.Color(15, 65, 67));

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 1850, Short.MAX_VALUE)
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 100, Short.MAX_VALUE)
        );

        jPanel4.add(jPanel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(-10, 120, 1850, -1));

        jTextArea1.setBackground(new java.awt.Color(245, 244, 230));
        jTextArea1.setColumns(20);
        jTextArea1.setFont(new java.awt.Font("Dialog", 0, 24)); // NOI18N
        jTextArea1.setForeground(new java.awt.Color(15, 65, 67));
        jTextArea1.setLineWrap(true);
        jTextArea1.setRows(5);
        jTextArea1.setWrapStyleWord(true);
        jScrollPane1.setViewportView(jTextArea1);

        jPanel4.add(jScrollPane1, new org.netbeans.lib.awtextra.AbsoluteConstraints(320, 640, 1120, -1));

        jTextField3.setBackground(new java.awt.Color(245, 244, 230));
        jTextField3.setFont(new java.awt.Font("Dialog", 0, 18)); // NOI18N
        jTextField3.setForeground(new java.awt.Color(15, 65, 67));
        jTextField3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextField3ActionPerformed(evt);
            }
        });
        jPanel4.add(jTextField3, new org.netbeans.lib.awtextra.AbsoluteConstraints(1020, 470, 420, 40));

        jLabel6.setBackground(new java.awt.Color(15, 65, 67));
        jLabel6.setFont(new java.awt.Font("Tahoma", 1, 24)); // NOI18N
        jLabel6.setForeground(new java.awt.Color(15, 65, 67));
        jLabel6.setText("Tahun Ajaran");
        jLabel6.setHorizontalTextPosition(javax.swing.SwingConstants.LEADING);
        jPanel4.add(jLabel6, new org.netbeans.lib.awtextra.AbsoluteConstraints(780, 470, -1, 30));

        jComboBox9.setBackground(new java.awt.Color(245, 244, 230));
        jComboBox9.setFont(new java.awt.Font("Dialog", 1, 18)); // NOI18N
        jComboBox9.setForeground(new java.awt.Color(15, 65, 67));
        jComboBox9.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Islam", "Kristen", "Hindu", "Budha", "Konghucu" }));
        jComboBox9.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jComboBox9ActionPerformed(evt);
            }
        });
        jPanel4.add(jComboBox9, new org.netbeans.lib.awtextra.AbsoluteConstraints(310, 550, 350, 40));

        jLabel4.setBackground(new java.awt.Color(245, 244, 230));
        jLabel4.setFont(new java.awt.Font("Tahoma", 1, 48)); // NOI18N
        jLabel4.setForeground(new java.awt.Color(15, 65, 67));
        jLabel4.setText("Ubah Data Rapor Yang Dipilih");
        jLabel4.setHorizontalTextPosition(javax.swing.SwingConstants.LEADING);
        jPanel4.add(jLabel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 250, -1, 90));

        jComboBox10.setBackground(new java.awt.Color(245, 244, 230));
        jComboBox10.setFont(new java.awt.Font("Dialog", 1, 18)); // NOI18N
        jComboBox10.setForeground(new java.awt.Color(15, 65, 67));
        jComboBox10.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Islam", "Kristen", "Hindu", "Budha", "Konghucu" }));
        jComboBox10.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jComboBox10ActionPerformed(evt);
            }
        });
        jPanel4.add(jComboBox10, new org.netbeans.lib.awtextra.AbsoluteConstraints(310, 470, 350, 40));

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
    
    this.dispose();  // TODO add your handling code here:
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

    private void jComboBox8ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jComboBox8ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jComboBox8ActionPerformed

    private void jTextField2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextField2ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jTextField2ActionPerformed

    private void jTextField1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextField1ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jTextField1ActionPerformed

    private void jButton5ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton5ActionPerformed
        // TODO add your handling code here:
        Rapor dataSiswaForm = new Rapor();
        dataSiswaForm.setVisible(true);
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
    try {
        // Validate required fields
        if (jTextField2.getText().trim().isEmpty() || 
            jComboBox9.getSelectedItem() == null || 
            jComboBox10.getSelectedItem() == null || 
            jComboBox8.getSelectedItem() == null || 
            jTextField3.getText().trim().isEmpty() ||
            jTextField1.getText().trim().isEmpty()) {
            JOptionPane.showMessageDialog(this, "Harap isi semua field yang diperlukan!");
            return;
        }

        // Get values from form
        String nis = jTextField2.getText();
        String namaPelajaran = (String) jComboBox9.getSelectedItem();
        String idKelas = (String) jComboBox10.getSelectedItem();
        String semester = (String) jComboBox8.getSelectedItem();
        String tahunAjaran = jTextField3.getText();
        String nilaiAkhir = jTextField1.getText();
        String kompre = jTextArea1.getText();

        // Validate nilai_akhir is numeric
        try {
            Double.parseDouble(nilaiAkhir);
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(this, "Format nilai tidak valid! Harap masukkan angka.");
            return;
        }

        // Database connection
        Connection conn = getConnection();
        if (conn != null) {
            // Update query sesuai struktur database
            String query = "UPDATE nilai SET " +
                    "nilai_akhir = ?, " +
                    "kompre = ?, " +
                    "updated_at = CURRENT_TIMESTAMP " +
                    "WHERE NIS = ? AND nama_pelajaran = ? AND id_kelas = ? AND semester = ? AND tahun_ajaran = ?";
            
            PreparedStatement pst = conn.prepareStatement(query);
            pst.setString(1, nilaiAkhir);
            pst.setString(2, kompre);
            pst.setString(3, nis);
            pst.setString(4, namaPelajaran);
            pst.setString(5, idKelas);
            pst.setString(6, semester);
            pst.setString(7, tahunAjaran);

            int result = pst.executeUpdate();

            if (result > 0) {
                JOptionPane.showMessageDialog(this, "Data nilai berhasil diperbarui!");
                // Return to previous form
                Rapor raporForm = new Rapor();
                raporForm.setVisible(true);
                this.dispose();
            } else {
                JOptionPane.showMessageDialog(this, "Gagal memperbarui data nilai! Data mungkin tidak ditemukan.");
            }

            pst.close();
            conn.close();
        }
    } catch (SQLException e) {
        JOptionPane.showMessageDialog(this, "Error database: " + e.getMessage());
    } catch (Exception e) {
        JOptionPane.showMessageDialog(this, "Error: " + e.getMessage());
    }

            
        Rapor dataSiswaForm = new Rapor();
        dataSiswaForm.setVisible(true);
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
    loadKompreData();
    }//GEN-LAST:event_jButton7ActionPerformed

    private void jButton7MouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jButton7MouseExited
        jButton7.setBackground(new Color(15, 65, 67)); // Warna default
        jButton7.setCursor(new Cursor(Cursor.DEFAULT_CURSOR)); // Kursor normal
    }//GEN-LAST:event_jButton7MouseExited

    private void jButton7MouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jButton7MouseEntered
        jButton7.setBackground(new Color(30, 90, 92)); // Warna hover
        jButton7.setCursor(new Cursor(Cursor.HAND_CURSOR)); // Kursor tangan
    }//GEN-LAST:event_jButton7MouseEntered

    private void jTextField3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextField3ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jTextField3ActionPerformed

    private void jComboBox9ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jComboBox9ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jComboBox9ActionPerformed

    private void jComboBox10ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jComboBox10ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jComboBox10ActionPerformed
    
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
            java.util.logging.Logger.getLogger(UbahNilai.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(UbahNilai.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(UbahNilai.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(UbahNilai.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
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
                new UbahNilai().setVisible(true);
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
    private javax.swing.JComboBox<String> jComboBox10;
    private javax.swing.JComboBox<String> jComboBox8;
    private javax.swing.JComboBox<String> jComboBox9;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTextArea jTextArea1;
    private javax.swing.JTextField jTextField1;
    private javax.swing.JTextField jTextField2;
    private javax.swing.JTextField jTextField3;
    // End of variables declaration//GEN-END:variables
    
}
