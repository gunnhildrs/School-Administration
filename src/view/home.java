package view;

import javax.swing.*;
import java.awt.*;
import java.awt.geom.RoundRectangle2D;
import java.awt.geom.Ellipse2D;

public class home extends JFrame {
    
    private final String[] photoPaths = {"/photos/library.JPG", "/photos/66.JPEG", "/photos/99.JPEG", "/photos/77.JPEG"};
    private final String[] photoLabels = {"Perpustakaan", "Lapangan", "Ruang Kelas", "UKS"};
    private final String[] menuItems = {
        "Dashboard", 
        "Manajemen Siswa", 
        "Manajemen Ujian", 
        "Nilai dan Rapor"
    };
    private final String[] menuIcons = {
        "/photos/school2.png", 
        "/photos/student2.png", 
        "/photos/calendar2.png", 
        "/photos/exam2.png"
    };
    private int index;

    public home() {
        setTitle("Home - Tiara School");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setExtendedState(JFrame.MAXIMIZED_BOTH);
        setLocationRelativeTo(null);
        
        try {
            ImageIcon appIcon = new ImageIcon(home.class.getResource("/photos/logo.png"));
            setIconImage(appIcon.getImage());
        } catch (Exception e) {
            System.out.println("Could not load application icon");
        }

        JPanel mainPanel = new JPanel(new BorderLayout());
        mainPanel.setBackground(Color.WHITE);
        add(mainPanel);

        // Left Panel (15%) - Aligned left with margin, full width background
        JPanel leftPanel = new JPanel();
        leftPanel.setPreferredSize(new Dimension(
            (int)(Toolkit.getDefaultToolkit().getScreenSize().getWidth()*0.15), 
            (int)(Toolkit.getDefaultToolkit().getScreenSize().getHeight())
        ));
        leftPanel.setLayout(new BorderLayout());
        leftPanel.setBackground(new Color(15, 65, 67)); // Full background color
        mainPanel.add(leftPanel, BorderLayout.WEST);
        
        // Left panel content
        JPanel leftContentWrapper = new JPanel();
        leftContentWrapper.setLayout(new BorderLayout());
        leftContentWrapper.setBackground(new Color(15, 65, 67));
        leftContentWrapper.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20)); // Padding inside the panel
        
        leftPanel.add(leftContentWrapper, BorderLayout.CENTER);
        
        // Logo and school name panel with left alignment
        JPanel logoPanel = new JPanel(new FlowLayout(FlowLayout.LEFT, 20, 20));
        logoPanel.setOpaque(false);
        
        try {
            ImageIcon logoIcon = new ImageIcon(home.class.getResource("/photos/logo.png"));
            Image logoImage = logoIcon.getImage().getScaledInstance(80, 80, Image.SCALE_SMOOTH);
            JLabel logoLabel = new JLabel(new ImageIcon(logoImage));
            logoPanel.add(logoLabel);
        } catch (Exception e) {
            System.out.println("Could not load logo image");
        }
        
        JPanel namePanel = new JPanel();
        namePanel.setLayout(new BoxLayout(namePanel, BoxLayout.Y_AXIS));
        namePanel.setOpaque(false);
        
        JLabel schoolNameLine1 = new JLabel("TIARA");
        schoolNameLine1.setFont(new Font("Arial", Font.BOLD, 34));
        schoolNameLine1.setForeground(Color.WHITE);
        schoolNameLine1.setAlignmentX(Component.LEFT_ALIGNMENT);
        
        JLabel schoolNameLine2 = new JLabel(" SCHOOL");
        schoolNameLine2.setFont(new Font("Arial", Font.BOLD, 22));
        schoolNameLine2.setForeground(Color.WHITE);
        schoolNameLine2.setAlignmentX(Component.LEFT_ALIGNMENT);
        
        namePanel.add(schoolNameLine1);
        namePanel.add(schoolNameLine2);
        logoPanel.add(namePanel);
        
        leftContentWrapper.add(logoPanel, BorderLayout.NORTH);

        // Menu buttons panel - Left aligned and closer to edge
        JPanel menuPanel = new JPanel();
        menuPanel.setLayout(new BoxLayout(menuPanel, BoxLayout.Y_AXIS));
        menuPanel.setOpaque(false);
        menuPanel.setBorder(BorderFactory.createEmptyBorder(5, 5, 10, 5));
        
        for (int i = 0; i < menuItems.length; i++) {
            JPanel buttonPanel = new JPanel(new BorderLayout());
            buttonPanel.setOpaque(false);
            buttonPanel.setMaximumSize(new Dimension(Integer.MAX_VALUE, 60));
            buttonPanel.setAlignmentX(Component.LEFT_ALIGNMENT);
            
            final int menuIndex = i; // Final variable for action listener
            JButton menuButton = new JButton(menuItems[i]) {
                @Override
                protected void paintComponent(Graphics g) {
                    if (getModel().isPressed()) {
                        g.setColor(new Color(30, 85, 87));
                    } else if (getModel().isRollover()) {
                        g.setColor(new Color(20, 75, 77));
                    } else {
                        g.setColor(new Color(15, 65, 67));
                    }
                    g.fillRect(0, 0, getWidth(), getHeight());
                    super.paintComponent(g);
                }
            };
            
            // Add action listener for each menu button
            menuButton.addActionListener(e -> {
                switch(menuIndex) {
                    case 0: // Profil Sekolah
                        // Add code to open Profil Sekolah window
                        JOptionPane.showMessageDialog(this, "Anda sudah di Dashboard");
                        break;
                    case 1: // Data Siswa
                        openDataSiswa();
                        this.dispose();
                        break;
                    case 2: // Mapel
                        // Add code to open Kalender Akademik window
                        openUjian();
                        this.dispose();
                        break;
                    case 3: // Ujian Akademik
                        // Add code to open Jadwal Ujian window
                        openRapor();
                        this.dispose();
                        break;                        
                }
            });
            
            // Create icon component
            try {
                ImageIcon icon = new ImageIcon(home.class.getResource(menuIcons[i]));
                Image img = icon.getImage().getScaledInstance(30, 30, Image.SCALE_SMOOTH);
                menuButton.setIcon(new ImageIcon(img));
                menuButton.setIconTextGap(15); // Space between icon and text
            } catch (Exception e) {
                System.out.println("Could not load menu icon: " + menuIcons[i]);
            }
            
            menuButton.setHorizontalAlignment(SwingConstants.LEFT);
            menuButton.setFont(new Font("Arial", Font.PLAIN, 20));
            menuButton.setForeground(Color.WHITE);
            menuButton.setBackground(new Color(15, 65, 67));
            menuButton.setBorderPainted(false);
            menuButton.setFocusPainted(false);
            menuButton.setContentAreaFilled(false);
            menuButton.setBorder(BorderFactory.createEmptyBorder(40, 5, 0, 0));
            
            buttonPanel.add(menuButton, BorderLayout.CENTER);
            menuPanel.add(buttonPanel);
            menuPanel.add(Box.createRigidArea(new Dimension(0, 18)));
        }
        
        // Logout button with functionality
        JButton logoutButton = new JButton("Log Out") {
            @Override
            protected void paintComponent(Graphics g) {
                if (getModel().isPressed()) {
                    g.setColor(new Color(30, 85, 87));
                } else if (getModel().isRollover()) {
                    g.setColor(new Color(20, 75, 77));
                } else {
                    g.setColor(new Color(15, 65, 67));
                }
                g.fillRect(0, 0, getWidth(), getHeight());
                super.paintComponent(g);
            }
        };
        
        logoutButton.addActionListener(e -> {
            this.dispose();
            new TiaraSchoolLogin().setVisible(true);
        });
        
        try {
            ImageIcon logoutIcon = new ImageIcon(home.class.getResource("/photos/logout.png"));
            Image img = logoutIcon.getImage().getScaledInstance(24, 24, Image.SCALE_SMOOTH);
            logoutButton.setIcon(new ImageIcon(img));
            logoutButton.setIconTextGap(15);
        } catch (Exception e) {
            System.out.println("Could not load logout icon");
        }
        
        JPanel logoutPanel = new JPanel(new BorderLayout());
        logoutPanel.setOpaque(false);
        logoutPanel.setMaximumSize(new Dimension(Integer.MAX_VALUE, 50));
        logoutPanel.setAlignmentX(Component.LEFT_ALIGNMENT);
        
        logoutButton.setHorizontalAlignment(SwingConstants.LEFT);
        logoutButton.setFont(new Font("Arial", Font.PLAIN, 26));
        logoutButton.setForeground(Color.WHITE);
        logoutButton.setBackground(new Color(15, 65, 67));
        logoutButton.setBorderPainted(false);
        logoutButton.setFocusPainted(false);
        logoutButton.setContentAreaFilled(false);
        logoutButton.setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 0));
        
        logoutPanel.add(logoutButton, BorderLayout.CENTER);
        
        menuPanel.add(Box.createVerticalGlue());
        menuPanel.add(logoutPanel);
        
        leftContentWrapper.add(menuPanel, BorderLayout.CENTER);

        // Middle Panel (45%) - Larger photo and text box
        JPanel middlePanel = new JPanel();
        middlePanel.setLayout(new BorderLayout());
        middlePanel.setBackground(Color.WHITE);
        mainPanel.add(middlePanel, BorderLayout.CENTER);
        
        JPanel contentPanel = new JPanel();
        contentPanel.setLayout(new BoxLayout(contentPanel, BoxLayout.Y_AXIS));
        contentPanel.setBorder(BorderFactory.createEmptyBorder(30, 30, 30, 30));
        contentPanel.setBackground(new Color(250, 247, 240));
          
        JLabel title = new JLabel("Hello, let's realizing:");
        title.setFont(new Font("Arial", Font.BOLD, 24));
        title.setAlignmentX(Component.LEFT_ALIGNMENT);
        contentPanel.add(title);
        
        JLabel subtitle = new JLabel("Bina iman, Gali Potensi, Gapai Prestasi");
        subtitle.setFont(new Font("Arial", Font.BOLD, 36));
        subtitle.setAlignmentX(Component.LEFT_ALIGNMENT);
        contentPanel.add(subtitle);
        
        contentPanel.add(Box.createRigidArea(new Dimension(0, 30)));
        
        // Photo panel
        JPanel photoPanel = new JPanel(new BorderLayout()) {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                Graphics2D g2 = (Graphics2D) g.create();
                g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
                
                int width = getWidth();
                int height = getHeight();
                int arc = 28;
                
                RoundRectangle2D roundedRect = new RoundRectangle2D.Float(0, 0, width, height, arc, arc);
                g2.setClip(roundedRect);
                
                try {
                    ImageIcon photoIcon = new ImageIcon(home.class.getResource("/photos/tiaraa.JPG"));
                    g2.drawImage(photoIcon.getImage(), 0, 0, width, height, this);
                } catch (Exception e) {
                    g2.setColor(Color.LIGHT_GRAY);
                    g2.fill(roundedRect);
                    g2.setColor(Color.DARK_GRAY);
                    g2.drawString("School Photo", 20, height/2);
                }
                
                g2.dispose();
            }
            
            @Override
            public Dimension getPreferredSize() {
                return new Dimension(
                    (int)(Toolkit.getDefaultToolkit().getScreenSize().getWidth()*0.4),
                    (int)(Toolkit.getDefaultToolkit().getScreenSize().getHeight()*0.49)
                );
            }
        };
        
        contentPanel.add(photoPanel);
        contentPanel.add(Box.createRigidArea(new Dimension(0, 30)));
        
        // Text box with different font sizes for address and description
        JPanel textBoxPanel = new JPanel(new BorderLayout()) {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                Graphics2D g2 = (Graphics2D) g.create();
                g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
                
                int width = getWidth();
                int height = getHeight();
                int arc = 28;
                
                g2.setColor(new Color(15, 65, 67));
                g2.fillRoundRect(0, 0, width, height, arc, arc);
                
                g2.dispose();
            }
        };
        
        textBoxPanel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));
        textBoxPanel.setOpaque(false);
        
        JTextPane textContent = new JTextPane();
        textContent.setContentType("text/html");
        textContent.setText("<html><body style='color:white; font-family:Arial;'>" +
                          "<div style='font-size:24px; margin-bottom:15px;'>Komp. DKI Blok P No. 147-148 Pondok Kelapa Kee, Duren Sawit Jakarta Timur I3450</div>" +
                          "<div style='font-size:12px;'>Sekolah Dasar Tiara School berdiri pada tanggal 14 Februari 2005 dibawah naungan Yayasan Tiara Bangsa, yang beralamat di Komplek DKI Blok P-6 Kelurahan Pondok Kelapa Kecamatan Duren Sawit Jakarta Timur, pendirian sekolah Tiara School diinisiasi oleh Bapak Nurrahman Kosasih, Bapak Ongen Sangaji dan Ibu Tavina Pahlawani yang ingin mewujudkan Sekolah Nasional yang berorientasi kebangsaan yang dapat menghantarkan putra/putri terbaik bangsa untuk dapat sukses sesuai dengan bakat, minat dan kompetensi yang dimiliknya yang berdasarkan nilai-nilai religius yang berasaskan pada Pancasila, hal ini sesuai dengan tujuan pendidikan Nasional yaitu untuk mencerdaskan kehidupan bangsa dan mengembangkan manusia Indonesia seutuhnya, yaitu insan yang beriman serta bertaqwa terhadap yang kuasa yang Maha Esa serta berbudi pekerti luhur, mempunyai pengetahuan serta keterampilan, kesehatan jasmani dan rohani, kepribadian yg mantap serta dapat berdikari dan bertanggung jawab pada masyarakat dan bangsa</div></body></html>");
        textContent.setEditable(false);
        textContent.setOpaque(false);
        
        JScrollPane scrollPane = new JScrollPane(textContent);
        scrollPane.setBorder(BorderFactory.createEmptyBorder());
        scrollPane.setOpaque(false);
        scrollPane.getViewport().setOpaque(false);
        
        textBoxPanel.add(scrollPane, BorderLayout.CENTER);
        textBoxPanel.setPreferredSize(new Dimension(
            photoPanel.getPreferredSize().width,
            (int)(photoPanel.getPreferredSize().height * 0.6)
        ));
        
        contentPanel.add(textBoxPanel);
        middlePanel.add(contentPanel, BorderLayout.NORTH);
        
        // Right Panel (40%) - Matching middle panel style
        JPanel rightPanel = new JPanel();
        rightPanel.setLayout(new BorderLayout());
        rightPanel.setBackground(new Color(250, 247, 240));
        rightPanel.setBorder(BorderFactory.createEmptyBorder(30, 20, 30, 20));
        mainPanel.add(rightPanel, BorderLayout.EAST);
        
        // Profile panel with matching style
        JPanel profilePanel = new JPanel(new BorderLayout()) {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                Graphics2D g2 = (Graphics2D) g.create();
                g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
                
                int width = getWidth();
                int height = getHeight();
                int arc = 28; // Matching rounded corners
                
                g2.setColor(new Color(15, 65, 67));
                g2.fillRoundRect(0, 0, width, height, arc, arc);
                
                g2.dispose();
            }
        };
        
        // Save profile panel width for consistency
        int panelWidth = (int)(Toolkit.getDefaultToolkit().getScreenSize().getWidth()*0.35 * 0.8);
        profilePanel.setPreferredSize(new Dimension(
            panelWidth,
            (int)(Toolkit.getDefaultToolkit().getScreenSize().getHeight()*0.1 * 0.8)
        ));
        profilePanel.setOpaque(false);
        profilePanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        
        // Profile photo with left margin
        JPanel profilePhotoPanel = new JPanel(new BorderLayout()) {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                Graphics2D g2 = (Graphics2D) g.create();
                g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

                // Buat area clipping berbentuk oval
                int diameter = Math.min(getWidth(), getHeight());
                Ellipse2D clip = new Ellipse2D.Float(0, 0, diameter, diameter);
                g2.setClip(clip);

                try {
                    ImageIcon profileIcon = new ImageIcon(home.class.getResource("/photos/usr.png"));
                    Image img = profileIcon.getImage();

                    // Hitung aspect ratio gambar asli
                    double imgWidth = img.getWidth(null);
                    double imgHeight = img.getHeight(null);
                    double imgAspect = imgWidth / imgHeight;

                    // Hitung dimensi gambar yang akan ditampilkan (pertahankan aspect ratio)
                    int drawWidth, drawHeight;
                    if (diameter / imgAspect < diameter) {
                        drawWidth = diameter;
                        drawHeight = (int) (diameter / imgAspect);
                    } else {
                        drawHeight = diameter;
                        drawWidth = (int) (diameter * imgAspect);
                    }

                    // Posisi gambar di tengah
                    int x = (getWidth() - drawWidth) / 2;
                    int y = (getHeight() - drawHeight) / 2;

                    g2.drawImage(img, x, y, drawWidth, drawHeight, this);
                } catch (Exception e) {
                    System.out.println("Could not load image");
                }

                g2.dispose();
            }

            @Override
            public boolean isOpaque() {
                return false; // Membuat panel transparan
            }
        };

        // Atur panel agar transparan
        profilePhotoPanel.setOpaque(false);
        
        profilePhotoPanel.setPreferredSize(new Dimension(70, 70));
        
        // Profile info
        JPanel profileInfoPanel = new JPanel();
        profileInfoPanel.setLayout(new BoxLayout(profileInfoPanel, BoxLayout.Y_AXIS));
        profileInfoPanel.setOpaque(false);
        profileInfoPanel.setBorder(BorderFactory.createEmptyBorder(15, 10, 15, 20));
        
        JLabel nameLabel = new JLabel("Admin");
        nameLabel.setFont(new Font("Arial", Font.BOLD, 18));
        nameLabel.setForeground(Color.WHITE);
        
        JLabel positionLabel = new JLabel("Administrator");
        positionLabel.setFont(new Font("Arial", Font.PLAIN, 14));
        positionLabel.setForeground(Color.WHITE);
        
        profileInfoPanel.add(nameLabel);
        profileInfoPanel.add(positionLabel);
        
        profilePanel.add(profilePhotoPanel, BorderLayout.WEST);
        profilePanel.add(profileInfoPanel, BorderLayout.CENTER);
        
        rightPanel.add(profilePanel, BorderLayout.NORTH);
        
        // Add "beberapa fasilitas" panel between admin and photos
        JPanel facilityHeaderPanel = new JPanel(new BorderLayout()) {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                Graphics2D g2 = (Graphics2D) g.create();
                g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
                
                int width = getWidth();
                int height = getHeight();
                int arc = 28; // Matching rounded corners
                
                g2.setColor(new Color(15, 65, 67));
                g2.fillRoundRect(0, 0, width, height, arc, arc);
                
                g2.dispose();
            }
        };
        
        // Make facility header half the height of admin panel
        int adminPanelHeight = (int)(Toolkit.getDefaultToolkit().getScreenSize().getHeight()*0.1 * 0.8);
        facilityHeaderPanel.setPreferredSize(new Dimension(
            panelWidth,
            adminPanelHeight / 2
        ));
        facilityHeaderPanel.setOpaque(false);
        facilityHeaderPanel.setBorder(BorderFactory.createEmptyBorder(5, 20, 5, 10));
        
        JLabel facilityLabel = new JLabel("Beberapa Fasilitas");
        facilityLabel.setFont(new Font("Arial", Font.BOLD, 24));
        facilityLabel.setForeground(Color.WHITE);
        facilityLabel.setHorizontalAlignment(SwingConstants.CENTER);
        
        facilityHeaderPanel.add(facilityLabel, BorderLayout.CENTER);
        
        // Add spacing after profile panel
        JPanel spacingPanel = new JPanel();
        spacingPanel.setBackground(Color.WHITE);
        spacingPanel.setPreferredSize(new Dimension(panelWidth, 20));
        
        // Add spacing after facility header
        JPanel spacingPanel2 = new JPanel();
        spacingPanel2.setBackground(Color.WHITE);
        spacingPanel2.setPreferredSize(new Dimension(panelWidth, 20));
        
        // Create central panel to hold them
        JPanel rightCenterPanel = new JPanel();
        rightCenterPanel.setLayout(new BoxLayout(rightCenterPanel, BoxLayout.Y_AXIS));
        rightCenterPanel.setBackground(Color.WHITE);
        
        rightCenterPanel.add(spacingPanel);
        rightCenterPanel.add(facilityHeaderPanel);
        rightCenterPanel.add(spacingPanel2);
        
        rightPanel.add(rightCenterPanel, BorderLayout.CENTER);
        
        // Photos panel with matching style and border
        JPanel photosOuterPanel = new JPanel(new BorderLayout());
        photosOuterPanel.setBackground(Color.WHITE);
        
        JPanel photosPanel = new JPanel(new GridLayout(2, 2, 15, 15)) {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                Graphics2D g2 = (Graphics2D) g.create();
                g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
                
                int width = getWidth();
                int height = getHeight();
                int arc = 28; // Matching rounded corners
                
                g2.setColor(new Color(15, 65, 67));
                g2.fillRoundRect(0, 0, width, height, arc, arc);
                
                g2.dispose();
            }
        };
        
        photosPanel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));
        photosPanel.setOpaque(false);
        
        // Calculate remaining space and set photos panel to fill it
        int availableHeight = (int)(Toolkit.getDefaultToolkit().getScreenSize().getHeight() - 
                              adminPanelHeight - (adminPanelHeight/2) - 100); // Account for spacing
        photosPanel.setPreferredSize(new Dimension(panelWidth, availableHeight));
        
        // Replace the photo loading section in your photosPanel loop with this improved version:

        for (int i = 0; i < 4; i++) {
    final int index = i;

    // Container for the whole photo + caption
    JPanel containerOuter = new JPanel();
    containerOuter.setLayout(new BoxLayout(containerOuter, BoxLayout.Y_AXIS));
    containerOuter.setOpaque(false);

    // Photo container with 3:4 aspect ratio
    JPanel photoContainer = new JPanel() {
        @Override
        protected void paintComponent(Graphics g) {
            super.paintComponent(g);
            Graphics2D g2 = (Graphics2D) g.create();
            g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

            int width = getWidth();
            int height = getHeight();
            int arc = 20;

            RoundRectangle2D photoRect = new RoundRectangle2D.Float(0, 0, width, height, arc, arc);
            g2.setClip(photoRect);

            ImageIcon photoIcon = loadImageWithLaunch4jSupport(photoPaths[index]);
            
            if (photoIcon != null && photoIcon.getIconWidth() > 0 && photoIcon.getIconHeight() > 0) {
                g2.drawImage(photoIcon.getImage(), 0, 0, width, height, this);
            } else {
                // Fallback if image couldn't be loaded
                g2.setColor(Color.LIGHT_GRAY);
                g2.fill(photoRect);
                g2.setColor(Color.DARK_GRAY);
                g2.setFont(new Font("Arial", Font.BOLD, 12));

                // Center the text
                FontMetrics fm = g2.getFontMetrics();
                int textWidth = fm.stringWidth(photoLabels[index]);
                int textHeight = fm.getHeight();
                int x = (width - textWidth) / 2;
                int y = (height - textHeight) / 2 + fm.getAscent();

                g2.drawString(photoLabels[index], x, y);
            }

            g2.dispose();
        }

        @Override
        public Dimension getPreferredSize() {
            int width = (int)(Toolkit.getDefaultToolkit().getScreenSize().getWidth()*0.07);
            // 3:4 aspect ratio (width:height)
            return new Dimension(width, (int)(width * 4/3.0));
        }
    };

    photoContainer.setOpaque(false);
    photoContainer.setAlignmentX(Component.CENTER_ALIGNMENT);

    // Caption label below photo
    JLabel label = new JLabel(photoLabels[index], SwingConstants.CENTER);
    label.setFont(new Font("Arial", Font.BOLD, 14));
    label.setForeground(Color.WHITE);
    label.setOpaque(false);
    label.setAlignmentX(Component.CENTER_ALIGNMENT);

    // Add spacing between photo and caption
    containerOuter.add(photoContainer);
    containerOuter.add(Box.createRigidArea(new Dimension(0, 10)));
    containerOuter.add(label);

    photosPanel.add(containerOuter);
}
        
        photosOuterPanel.add(photosPanel, BorderLayout.CENTER);
        rightPanel.add(photosOuterPanel, BorderLayout.SOUTH);

        setVisible(true);
    }
    
    // Method to open Data Siswa window
    private void openDataSiswa() {
        try {
            // Create and show the DataSiswaa window
            SwingUtilities.invokeLater(() -> {
                DataSiswaa dataSiswaWindow = new DataSiswaa();
                dataSiswaWindow.setVisible(true);
            }); 
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, 
                "Error opening Data Siswa window: " + e.getMessage(), 
                "Error", 
                JOptionPane.ERROR_MESSAGE);
            e.printStackTrace();
        }
    }
    
    private void openUjian() {
        try {
            // Create and show the DataSiswaa window
            SwingUtilities.invokeLater(() -> {
                Ujian dataSiswaWindow = new Ujian();
                dataSiswaWindow.setVisible(true);
            }); 
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, 
                "Error opening Data Siswa window: " + e.getMessage(), 
                "Error", 
                JOptionPane.ERROR_MESSAGE);
            e.printStackTrace();
        }    
    }
    
    private void openRapor() {
        try {
            // Create and show the DataSiswaa window
            SwingUtilities.invokeLater(() -> {
                Rapor dataSiswaWindow = new Rapor();
                dataSiswaWindow.setVisible(true);
            }); 
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, 
                "Error opening Data Siswa window: " + e.getMessage(), 
                "Error", 
                JOptionPane.ERROR_MESSAGE);
            e.printStackTrace();
        }    
    }
    
 // Add this debug method to your home class:
private ImageIcon loadImageWithLaunch4jSupport(String imagePath) {
    try {
        // Strategy 1: Try loading from JAR resources (works in Launch4j)
        java.net.URL imageUrl = getClass().getResource(imagePath);
        if (imageUrl != null) {
            ImageIcon icon = new ImageIcon(imageUrl);
            if (icon.getIconWidth() > 0 && icon.getIconHeight() > 0) {
                return icon;
            }
        }

        // Strategy 2: Try with ClassLoader (alternative for Launch4j)
        String pathWithoutSlash = imagePath.startsWith("/") ? imagePath.substring(1) : imagePath;
        imageUrl = getClass().getClassLoader().getResource(pathWithoutSlash);
        if (imageUrl != null) {
            ImageIcon icon = new ImageIcon(imageUrl);
            if (icon.getIconWidth() > 0 && icon.getIconHeight() > 0) {
                return icon;
            }
        }

        // Strategy 3: Try loading from external resources folder (for development/deployment)
        try {
            // Get the directory where the JAR/EXE is located
            String jarPath = new java.io.File(getClass().getProtectionDomain()
                .getCodeSource().getLocation().toURI()).getParent();
            
            // Try multiple possible external paths
            String[] possiblePaths = {
                jarPath + java.io.File.separator + "resources" + imagePath.replace("/", java.io.File.separator),
                jarPath + java.io.File.separator + "photos" + java.io.File.separator + 
                    imagePath.substring(imagePath.lastIndexOf("/") + 1),
                jarPath + imagePath.replace("/", java.io.File.separator),
                System.getProperty("user.dir") + imagePath.replace("/", java.io.File.separator)
            };
            
            for (String path : possiblePaths) {
                java.io.File imageFile = new java.io.File(path);
                if (imageFile.exists() && imageFile.isFile()) {
                    ImageIcon icon = new ImageIcon(imageFile.getAbsolutePath());
                    if (icon.getIconWidth() > 0 && icon.getIconHeight() > 0) {
                        return icon;
                    }
                }
            }
        } catch (Exception e) {
            System.err.println("Error loading external image: " + imagePath + " - " + e.getMessage());
        }

        // Strategy 4: Try loading from InputStream (works well with Launch4j)
        java.io.InputStream is = getClass().getResourceAsStream(imagePath);
        if (is != null) {
            try {
                byte[] imageData = readAllBytes(is);
                ImageIcon icon = new ImageIcon(imageData);
                if (icon.getIconWidth() > 0 && icon.getIconHeight() > 0) {
                    return icon;
                }
            } catch (Exception e) {
                System.err.println("Error loading image from stream: " + imagePath + " - " + e.getMessage());
            } finally {
                try {
                    is.close();
                } catch (Exception e) {
                    // Ignore
                }
            }
        }

        // Strategy 5: Try with Thread context ClassLoader (Launch4j fallback)
        imageUrl = Thread.currentThread().getContextClassLoader().getResource(pathWithoutSlash);
        if (imageUrl != null) {
            ImageIcon icon = new ImageIcon(imageUrl);
            if (icon.getIconWidth() > 0 && icon.getIconHeight() > 0) {
                return icon;
            }
        }

    } catch (Exception e) {
        System.err.println("Error loading image: " + imagePath + " - " + e.getMessage());
    }
    
    return null; // Return null if image couldn't be loaded
}

// Helper method to read all bytes from InputStream (for Java 8 compatibility)
private byte[] readAllBytes(java.io.InputStream is) throws java.io.IOException {
    java.io.ByteArrayOutputStream buffer = new java.io.ByteArrayOutputStream();
    byte[] data = new byte[1024];
    int nRead;
    while ((nRead = is.read(data, 0, data.length)) != -1) {
        buffer.write(data, 0, nRead);
    }
    return buffer.toByteArray();
}


private ImageIcon loadExternalImage(String imageName) {
    try {
        // Try to load from resources folder next to JAR
        String jarPath = new java.io.File(home.class.getProtectionDomain()
            .getCodeSource().getLocation().toURI()).getParent();
        
        String imagePath = jarPath + java.io.File.separator + "resources" + 
                          java.io.File.separator + "photos" + 
                          java.io.File.separator + imageName;
        
        java.io.File imageFile = new java.io.File(imagePath);
        if (imageFile.exists()) {
            return new ImageIcon(imageFile.getAbsolutePath());
        }
        
        // Fallback to original method
        return loadImageIcon("/photos/" + imageName);
        
    } catch (Exception e) {
        System.err.println("Error loading external image: " + imageName + " - " + e.getMessage());
        return null;
    }
}

// Update your photoPaths array to use just filenames:
private final String[] photoFiles = {"library.JPG", "66.JPEG", "99.JPEG", "77.JPEG"};

// Then in your paintComponent method:
ImageIcon photoIcon = loadExternalImage(photoFiles[index]);


private ImageIcon loadImageIcon(String path) {
    try {
        // Try multiple loading strategies
        java.net.URL imageUrl = null;
        
        // Strategy 1: Direct class resource loading
        imageUrl = getClass().getResource(path);
        
        if (imageUrl == null) {
            // Strategy 2: Remove leading slash and try again
            String pathWithoutSlash = path.startsWith("/") ? path.substring(1) : path;
            imageUrl = getClass().getClassLoader().getResource(pathWithoutSlash);
        }
        
        if (imageUrl == null) {
            // Strategy 3: Try system classloader
            String pathWithoutSlash = path.startsWith("/") ? path.substring(1) : path;
            imageUrl = ClassLoader.getSystemResource(pathWithoutSlash);
        }
        
        if (imageUrl == null) {
            // Strategy 4: Try as file path (for development)
            try {
                java.io.File file = new java.io.File(path);
                if (file.exists()) {
                    return new ImageIcon(file.getAbsolutePath());
                }
            } catch (Exception e) {
                // Ignore and continue
            }
        }
        
        if (imageUrl != null) {
            ImageIcon icon = new ImageIcon(imageUrl);
            // Verify the image loaded successfully
            if (icon.getIconWidth() > 0 && icon.getIconHeight() > 0) {
                return icon;
            }
        }
        
    } catch (Exception e) {
        System.err.println("Error loading image: " + path + " - " + e.getMessage());
    }
    
    return null; // Return null if image couldn't be loaded
}  
}