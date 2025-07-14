package view;

import javax.swing.*;
import java.awt.*;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import model.DatabaseConnection;

public class TiaraSchoolLogin extends JFrame {

    private static final long serialVersionUID = 1L;
    private final JTextField idField;
    private final JPasswordField passField;

    public TiaraSchoolLogin() {
        setTitle("Login");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setExtendedState(JFrame.MAXIMIZED_BOTH);
        ImageIcon frameIcon = new ImageIcon(getClass().getResource("/photos/logo.png"));
        setIconImage(frameIcon.getImage());
        
        setLayout(new BorderLayout());        
        Color creamColor = new Color(250, 247, 240);

        // Left panel 
        JPanel leftPanel = new JPanel();
        leftPanel.setBackground(creamColor);
        leftPanel.setLayout(new GridBagLayout());
        leftPanel.setPreferredSize(new Dimension((int)(Toolkit.getDefaultToolkit().getScreenSize().width * 0.6), 0));

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridwidth = GridBagConstraints.REMAINDER;
        gbc.anchor = GridBagConstraints.CENTER;
        gbc.insets = new Insets(10, 10, 30, 10);

        //  logo
        ImageIcon logoIcon = new ImageIcon(getClass().getResource("/photos/logo.png"));
        Image logoImage = logoIcon.getImage().getScaledInstance(400, 400, Image.SCALE_SMOOTH);
        JLabel logoLabel = new JLabel(new ImageIcon(logoImage));
        leftPanel.add(logoLabel, gbc);

        // ID Access field 
        idField = createTextFieldWithIcon("ID Access", "/photos/usr.png");
        leftPanel.add(wrapFieldWithIcon(idField, "/photos/usr.png", 600, 50), gbc);

        // Password field 
        passField = createPasswordFieldWithIcon("Password", "/photos/ps.png");
        leftPanel.add(wrapFieldWithIcon(passField, "/photos/ps.png", 600, 50), gbc);

        // Login button with action listener
        JButton loginButton = new JButton("Log in");
        loginButton.setPreferredSize(new Dimension(600, 40));
        loginButton.setBackground(new Color(0, 120, 215));
        loginButton.setForeground(Color.WHITE);
        loginButton.setFont(new Font("Arial", Font.BOLD, 14));
        
        // Add action listener to loginButton
        loginButton.addActionListener((ActionEvent e) -> {
            attemptLogin();
        });
        
        leftPanel.add(loginButton, gbc);

        // Right panel
        JPanel rightPanel = new JPanel(new BorderLayout());
        rightPanel.setPreferredSize(new Dimension((int)(Toolkit.getDefaultToolkit().getScreenSize().width * 0.4), 0));
        
        ImageIcon bgIcon = new ImageIcon(getClass().getResource("/photos/bgv.JPG"));
        Image bgImage = bgIcon.getImage().getScaledInstance(
            (int)(Toolkit.getDefaultToolkit().getScreenSize().width * 0.4), 
            Toolkit.getDefaultToolkit().getScreenSize().height, 
            Image.SCALE_SMOOTH);
        JLabel bgLabel = new JLabel(new ImageIcon(bgImage));
        rightPanel.add(bgLabel, BorderLayout.CENTER);

        add(leftPanel, BorderLayout.CENTER);
        add(rightPanel, BorderLayout.EAST);

        // Add key listener to passField for Enter key login
        passField.addActionListener((ActionEvent e) -> {
            attemptLogin();
        });

        pack();
        setVisible(true);
    }

    // Method to handle login attempt - Updated to go directly to home page
    private void attemptLogin() {
        String username = idField.getText();
        String password = new String(passField.getPassword());
        
        // Skip validation if placeholders are still showing
        if (username.equals("ID Access") || password.equals("Password")) {
            return; // Do nothing if placeholders are still showing
        }
        
        // Validate with database
        if (DatabaseConnection.validateUser(username, password)) {
            // Successful login - go directly to home page
            dispose(); // Close login window
            SwingUtilities.invokeLater(() -> {
                new home(); // Open homepage
            });
        }
        // If login fails, do nothing (no notification)
    }

    private JTextField createTextFieldWithIcon(String placeholder, String iconPath) {
        JTextField textField = new JTextField();
        textField.setText(placeholder);
        textField.setForeground(new Color(0, 0, 0, 0.6f));
        textField.setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5));
        // Perbesar ukuran font
        textField.setFont(new Font("Arial", Font.PLAIN, 16));
        
        textField.addFocusListener(new FocusListener() {
            @Override
            public void focusGained(FocusEvent e) {
                if (textField.getText().equals(placeholder)) {
                    textField.setText("");
                    textField.setForeground(Color.BLACK);
                }
            }

            @Override
            public void focusLost(FocusEvent e) {
                if (textField.getText().isEmpty()) {
                    textField.setForeground(new Color(0, 0, 0, 0.6f));
                    textField.setText(placeholder);
                }
            }
        });
        
        return textField;
    }

    private JPasswordField createPasswordFieldWithIcon(String placeholder, String iconPath) {
        JPasswordField passwordField = new JPasswordField();
        passwordField.setEchoChar((char)0);
        passwordField.setText(placeholder);
        passwordField.setForeground(new Color(0, 0, 0, 0.6f));
        passwordField.setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5));
        // Perbesar ukuran font
        passwordField.setFont(new Font("Arial", Font.PLAIN, 16));
        
        passwordField.addFocusListener(new FocusListener() {
            @Override
            public void focusGained(FocusEvent e) {
                if (String.valueOf(passwordField.getPassword()).equals(placeholder)) {
                    passwordField.setText("");
                    passwordField.setEchoChar('•');
                    passwordField.setForeground(Color.BLACK);
                }
            }

            @Override
            public void focusLost(FocusEvent e) {
                if (passwordField.getPassword().length == 0) {
                    passwordField.setEchoChar((char)0);
                    passwordField.setForeground(new Color(0, 0, 0, 0.6f));
                    passwordField.setText(placeholder);
                }
            }
        });
        
        return passwordField;
    }

    private JPanel wrapFieldWithIcon(JComponent field, String iconPath, int width, int height) {
        JPanel panel = new JPanel(new BorderLayout());
        panel.setPreferredSize(new Dimension(width, height));
        panel.setBorder(BorderFactory.createLineBorder(Color.GRAY));
        
        // Load icon dengan mempertahankan aspect ratio
        ImageIcon originalIcon = new ImageIcon(getClass().getResource(iconPath));
        int iconSize = 30; // Ukuran ikon yang lebih besar
        
        // Hitung scaling yang mempertahankan aspect ratio
        int originalWidth = originalIcon.getIconWidth();
        int originalHeight = originalIcon.getIconHeight();
        double aspectRatio = (double)originalWidth / originalHeight;
        
        int newWidth, newHeight;
        if (originalWidth > originalHeight) {
            newWidth = iconSize;
            newHeight = (int)(iconSize / aspectRatio);
        } else {
            newHeight = iconSize;
            newWidth = (int)(iconSize * aspectRatio);
        }
        
        Image scaledImage = originalIcon.getImage().getScaledInstance(newWidth, newHeight, Image.SCALE_SMOOTH);
        JLabel iconLabel = new JLabel(new ImageIcon(scaledImage));
        iconLabel.setBorder(BorderFactory.createEmptyBorder(0, 10, 0, 10));
        
        panel.add(iconLabel, BorderLayout.WEST);
        panel.add(field, BorderLayout.CENTER);
        
        return panel;
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            new TiaraSchoolLogin();
        });
    }
}