package view;

import java.awt.*;
import java.awt.image.BufferedImage;
import javax.swing.*;
import javax.swing.border.*;
import java.awt.event.*;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;

public class TiaraSchoolUI extends JFrame {
    
    // Components
    private JPanel headerPanel;
    private JPanel contentPanel;
    private JPanel topPanel;
    private JPanel bottomPanel;
    private JPanel leftBottomPanel;
    private JPanel rightBottomPanel;
    private JLabel logoLabel;
    private JLabel schoolNameLabel;
    private JButton logoutButton;
    private JLabel mainImageLabel;
    private JLabel mottoLabel;
    private JTextArea descriptionArea;
    private JButton moreButton;
    
    // Colors
    private final Color SAGE_GREEN = new Color(125, 153, 117);
    
    // Image path
    private final String IMAGE_PATH = "/photos/";
    
    public TiaraSchoolUI() {
        setTitle("Tiara School");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        
        // Set the application to run in full screen
        setExtendedState(JFrame.MAXIMIZED_BOTH);
        setUndecorated(true);
        
        // Set layout for the main frame
        setLayout(new BorderLayout());
        
        // Initialize components
        initComponents();
        
        // Add components to the frame
        add(headerPanel, BorderLayout.NORTH);
        add(contentPanel, BorderLayout.CENTER);
        
        // Set background color for the entire frame
        getContentPane().setBackground(new Color(242, 242, 242));
        
        // Add key listener to exit fullscreen with ESC key
        KeyStroke escapeKeyStroke = KeyStroke.getKeyStroke(KeyEvent.VK_ESCAPE, 0);
        getRootPane().getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW).put(escapeKeyStroke, "ESCAPE");
        getRootPane().getActionMap().put("ESCAPE", new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.exit(0);
            }
        });
    }
    
    private void initComponents() {
        // Initialize header panel
        initHeaderPanel();
        
        // Initialize content panel
        initContentPanel();
    }
    
    private void initHeaderPanel() {
        headerPanel = new JPanel();
        headerPanel.setLayout(new BorderLayout());
        headerPanel.setBackground(Color.WHITE);
        headerPanel.setBorder(BorderFactory.createEmptyBorder(10, 20, 10, 20));
        
        // Logo and school name panel
        JPanel logoNamePanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        logoNamePanel.setBackground(Color.WHITE);
        
        // Logo
        logoLabel = new JLabel();
        try {
            ImageIcon logoIcon = loadImageIcon("logo.png", 60, 60);
            logoLabel.setIcon(logoIcon);
        } catch (Exception e) {
            // Fallback to generated logo if image loading fails
            ImageIcon logo = createCircularImage(60, 60, SAGE_GREEN);
            logoLabel.setIcon(logo);
            System.err.println("Could not load logo: " + e.getMessage());
        }
        
        // School name
        schoolNameLabel = new JLabel("TIARA SCHOOL");
        schoolNameLabel.setFont(new Font("Arial", Font.BOLD, 24));
        
        logoNamePanel.add(logoLabel);
        logoNamePanel.add(Box.createHorizontalStrut(10));
        logoNamePanel.add(schoolNameLabel);
        
        // Logout button - smaller size and sage green color
        logoutButton = new JButton("Log Out");
        logoutButton.setBackground(SAGE_GREEN);
        logoutButton.setForeground(Color.WHITE);
        logoutButton.setFocusPainted(false);
        logoutButton.setCursor(new Cursor(Cursor.HAND_CURSOR));
        logoutButton.setPreferredSize(new Dimension(90, 30)); // Smaller size
        logoutButton.addActionListener(e -> System.exit(0));
        
        headerPanel.add(logoNamePanel, BorderLayout.WEST);
        headerPanel.add(logoutButton, BorderLayout.EAST);
    }
    

    private void initContentPanel() {
        contentPanel = new JPanel();
        contentPanel.setLayout(new BorderLayout());
        contentPanel.setBackground(new Color(242, 242, 242));
        
        // Get screen dimensions
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        int screenWidth = screenSize.width;
        int screenHeight = screenSize.height - headerPanel.getPreferredSize().height;
        
        // Calculate heights for top (60%) and bottom (40%) panels
        int topHeight = (int)(screenHeight * 0.6);
        int bottomHeight = screenHeight - topHeight;
        
        // Top panel (60% height, full width)
        topPanel = new JPanel(new BorderLayout());
        topPanel.setPreferredSize(new Dimension(screenWidth, topHeight));
        topPanel.setBackground(Color.WHITE);
        
        // Main image (full width, 60% height)
        mainImageLabel = new JLabel();
        mainImageLabel.setHorizontalAlignment(JLabel.CENTER);
        
        try {
            ImageIcon mainImage = loadImageIcon("tiaraa.jpg", screenWidth, topHeight);
            mainImageLabel.setIcon(mainImage);
        } catch (Exception e) {
            mainImageLabel.setBackground(SAGE_GREEN);
            mainImageLabel.setOpaque(true);
            mainImageLabel.setHorizontalAlignment(JLabel.CENTER);
            mainImageLabel.setText("TIARA SCHOOL");
            mainImageLabel.setForeground(Color.WHITE);
            mainImageLabel.setFont(new Font("Arial", Font.BOLD, 32));
            System.err.println("Could not load main image: " + e.getMessage());
        }
        
        topPanel.add(mainImageLabel, BorderLayout.CENTER);
        
        // Bottom panel (40% height)
        bottomPanel = new JPanel(new BorderLayout());
        bottomPanel.setPreferredSize(new Dimension(screenWidth, bottomHeight));
        bottomPanel.setBackground(new Color(242, 242, 242));
        
        // Left bottom panel (40% width in bottom panel)
        leftBottomPanel = new JPanel();
        leftBottomPanel.setLayout(new BoxLayout(leftBottomPanel, BoxLayout.Y_AXIS));
        leftBottomPanel.setBackground(Color.WHITE);
        leftBottomPanel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));
        
        // Motto and description in left bottom panel
        mottoLabel = new JLabel("Bina Iman, Gali Potensi, Raih Prestasi");
        mottoLabel.setFont(new Font("Arial", Font.BOLD, 24));
        mottoLabel.setAlignmentX(Component.LEFT_ALIGNMENT);
        
        descriptionArea = new JTextArea("Lorem ipsum dolor sit amet, consectetur adipiscing elit, "
                + "sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. Ut enim ad minim veniam.");
        descriptionArea.setFont(new Font("Arial", Font.PLAIN, 14));
        descriptionArea.setLineWrap(true);
        descriptionArea.setWrapStyleWord(true);
        descriptionArea.setEditable(false);
        descriptionArea.setBackground(Color.WHITE);
        descriptionArea.setAlignmentX(Component.LEFT_ALIGNMENT);
        descriptionArea.setBorder(BorderFactory.createEmptyBorder(10, 0, 10, 0));
        
        // More button with sage green color
        moreButton = new JButton("Selengkapnya");
        moreButton.setBackground(SAGE_GREEN);
        moreButton.setForeground(Color.WHITE);
        moreButton.setFocusPainted(false);
        moreButton.setCursor(new Cursor(Cursor.HAND_CURSOR));
        moreButton.setAlignmentX(Component.LEFT_ALIGNMENT);
        
        leftBottomPanel.add(mottoLabel);
        leftBottomPanel.add(Box.createVerticalStrut(10));
        leftBottomPanel.add(descriptionArea);
        leftBottomPanel.add(Box.createVerticalStrut(15));
        leftBottomPanel.add(moreButton);
        
        // Right bottom panel (60% width in bottom panel) - Now with just 3 buttons
        rightBottomPanel = new JPanel();
        rightBottomPanel.setLayout(new GridLayout(1, 3, 15, 10)); // 1 row, 3 columns
        rightBottomPanel.setBackground(new Color(242, 242, 242));
        rightBottomPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        
        // Create 3 function buttons with images
        String[] imageNames = {"card1.jpg", "card2.jpg", "card3.jpg"};
        String[] buttonLabels = {"Students", "Teachers", "Reports"};
        
        for (int i = 0; i < imageNames.length; i++) {
            rightBottomPanel.add(createFunctionButton(imageNames[i], buttonLabels[i]));
        }
        
        // Add left and right panels to bottom panel
        bottomPanel.add(leftBottomPanel, BorderLayout.WEST);
        bottomPanel.add(rightBottomPanel, BorderLayout.CENTER);
        
        // Set preferred sizes for left bottom and right bottom panels
        int leftBottomWidth = (int)(screenWidth * 0.4);
        leftBottomPanel.setPreferredSize(new Dimension(leftBottomWidth, bottomHeight));
        
        // Add top and bottom panels to content panel
        contentPanel.add(topPanel, BorderLayout.NORTH);
        contentPanel.add(bottomPanel, BorderLayout.CENTER);
    }
    
    private JButton createFunctionButton(String imageName, String buttonLabel) {
        JButton button = new JButton();
        button.setLayout(new BorderLayout());
        button.setBorder(BorderFactory.createLineBorder(new Color(220, 220, 220)));
        button.setCursor(new Cursor(Cursor.HAND_CURSOR));
        
        // Create image panel
        JPanel imagePanel = new JPanel(new BorderLayout());
        imagePanel.setBackground(Color.WHITE);
        
        JLabel imageLabel = new JLabel();
        imageLabel.setHorizontalAlignment(JLabel.CENTER);
        imageLabel.setVerticalAlignment(JLabel.CENTER);
        
        try {
            // Calculate button image dimensions
            Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
            int buttonWidth = (int)(screenSize.width * 0.6 / 3) - 20; // 60% of screen / 3 columns - padding
            int buttonHeight = (int)(screenSize.height * 0.4) - 50; // 40% of screen height - padding
            
            ImageIcon buttonImage = loadImageIcon(imageName, buttonWidth, buttonHeight - 30); // Reserve space for label
            imageLabel.setIcon(buttonImage);
        } catch (Exception e) {
            // If image loading fails, use colored panel
            imageLabel.setText(buttonLabel);
            imageLabel.setOpaque(true);
            imageLabel.setBackground(new Color(200, 220, 200));
            System.err.println("Could not load button image " + imageName + ": " + e.getMessage());
        }
        
        imagePanel.add(imageLabel, BorderLayout.CENTER);
        
        // Create label panel
        JPanel labelPanel = new JPanel(new BorderLayout());
        labelPanel.setBackground(SAGE_GREEN);
        
        JLabel label = new JLabel(buttonLabel, JLabel.CENTER);
        label.setForeground(Color.WHITE);
        label.setFont(new Font("Arial", Font.BOLD, 14));
        label.setBorder(BorderFactory.createEmptyBorder(8, 0, 8, 0));
        
        labelPanel.add(label, BorderLayout.CENTER);
        
        // Add components to button
        button.add(imagePanel, BorderLayout.CENTER);
        button.add(labelPanel, BorderLayout.SOUTH);
        
        // Add action listener
        button.addActionListener(e -> JOptionPane.showMessageDialog(this, 
                                      "You clicked on " + buttonLabel, 
                                      "Button Clicked", 
                                      JOptionPane.INFORMATION_MESSAGE));
        
        return button;
    }
    
    // Helper method to load and resize images from /photos directory
    private ImageIcon loadImageIcon(String fileName, int width, int height) throws IOException {
        String filePath = IMAGE_PATH + fileName;
        
        // Try to load as resource first
        java.net.URL imgURL = getClass().getResource(filePath);
        
        BufferedImage originalImage;
        if (imgURL != null) {
            originalImage = ImageIO.read(imgURL);
        } else {
            // If not found as resource, try direct file path
            originalImage = ImageIO.read(new File(System.getProperty("user.dir") + filePath));
        }
        
        // Resize image
        Image resizedImage = originalImage.getScaledInstance(width, height, Image.SCALE_SMOOTH);
        return new ImageIcon(resizedImage);
    }
    
    // Helper method to create a circular image for the logo (fallback if logo.png not found)
    private ImageIcon createCircularImage(int width, int height, Color color) {
        BufferedImage image = new BufferedImage(width, height, BufferedImage.TYPE_INT_ARGB);
        Graphics2D g2d = image.createGraphics();
        
        g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        g2d.setColor(color);
        g2d.fillOval(0, 0, width, height);
        
        // Add the text "TS" in the center of the circle
        g2d.setColor(Color.WHITE);
        g2d.setFont(new Font("Arial", Font.BOLD, 20));
        FontMetrics fm = g2d.getFontMetrics();
        String text = "TS";
        int textWidth = fm.stringWidth(text);
        int textHeight = fm.getHeight();
        g2d.drawString(text, (width - textWidth) / 2, height / 2 + textHeight / 4);
        
        g2d.dispose();
        
        return new ImageIcon(image);
    }
    
    public static void main(String[] args) {
        // Set look and feel to system default
        try {
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        } catch (Exception e) {
            e.printStackTrace();
        }
        
        // Run the application
        SwingUtilities.invokeLater(() -> {
            TiaraSchoolUI app = new TiaraSchoolUI();
            app.setVisible(true);
        });
    }
}