package ResumeMaker;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Timer;
import java.util.TimerTask;
import java.io.File;
import java.io.IOException;

public class MainApplication {

    private JFrame mainFrame;
    private JPanel welcomePanel;
    private Timer fadeTimer;
    private float alpha = 0.0f;

    // Define color palette
    private Color primaryBlue = new Color(30, 144, 255); // Dodger Blue
    private Color lightBlue = new Color(173, 216, 230); // Light Blue
    private Color darkBlue = new Color(0, 0, 139); // Dark Blue
    private Color accentBlue = new Color(135, 206, 250); // Sky Blue
    private Color backgroundColor = Color.WHITE; // White background
    private Color textColor = Color.BLACK; // Black text color

    public MainApplication() {
        mainFrame = new JFrame("Welcome!!!");
        mainFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        mainFrame.setSize(400, 300);
        mainFrame.setLocationRelativeTo(null);

        // Load custom font
        try {
            Font customFont = Font.createFont(Font.TRUETYPE_FONT, new File("Cavilant-X3jaZ.ttf")).deriveFont(20f);
            GraphicsEnvironment ge = GraphicsEnvironment.getLocalGraphicsEnvironment();
            ge.registerFont(customFont);
        } catch (IOException | FontFormatException e) {
            e.printStackTrace();
        }

        createWelcomePanel();
        fadeInWelcomePanel();
        mainFrame.setVisible(true);
    }

    private void createWelcomePanel() {
        welcomePanel = new JPanel(new GridBagLayout()) {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                Graphics2D g2d = (Graphics2D) g.create();
                int w = getWidth();
                int h = getHeight();
                Color startColor = primaryBlue;
                Color endColor = darkBlue;
                GradientPaint gradientPaint = new GradientPaint(0, 0, startColor, w, h, endColor);
                g2d.setPaint(gradientPaint);
                g2d.fillRect(0, 0, w, h);
                g2d.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, alpha));
                g2d.dispose();
            }
        };

        GridBagConstraints constraints = new GridBagConstraints();
        constraints.insets = new Insets(10, 10, 10, 10);

        JLabel titleLabel = new JLabel("Resume Hub");
        titleLabel.setFont(new Font("Cavilant", Font.BOLD, 30)); // Custom font and size
        titleLabel.setForeground(Color.WHITE); // Custom text color
        constraints.gridx = 0;
        constraints.gridy = 0;
        constraints.gridwidth = 2;
        welcomePanel.add(titleLabel, constraints);

        JButton loginButton = new JButton("Log In");
        loginButton.setBackground(lightBlue);
        loginButton.setForeground(textColor);
        loginButton.setFocusPainted(false); // Remove focus border
        loginButton.setBorder(BorderFactory.createEmptyBorder(10, 25, 10, 25)); // Add padding
        loginButton.setOpaque(true);
        loginButton.setBorderPainted(false); // Remove button border
        loginButton.setFont(new Font("Raleway", Font.BOLD, 14)); // Set button font
        loginButton.setCursor(new Cursor(Cursor.HAND_CURSOR)); // Change cursor to hand
        // Add hover effect
        loginButton.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                loginButton.setBackground(accentBlue);
            }

            public void mouseExited(java.awt.event.MouseEvent evt) {
                loginButton.setBackground(lightBlue);
            }
        });
        constraints.gridx = 0;
        constraints.gridy = 1;
        constraints.gridwidth = 1;
        welcomePanel.add(loginButton, constraints);

        JButton signUpButton = new JButton("Sign Up");
        signUpButton.setBackground(accentBlue);
        signUpButton.setForeground(textColor);
        signUpButton.setFocusPainted(false);
        signUpButton.setBorder(BorderFactory.createEmptyBorder(10, 25, 10, 25));
        signUpButton.setOpaque(true);
        signUpButton.setBorderPainted(false);
        signUpButton.setFont(new Font("Raleway", Font.BOLD, 14));
        signUpButton.setCursor(new Cursor(Cursor.HAND_CURSOR));
        // Add hover effect
        signUpButton.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                signUpButton.setBackground(primaryBlue);
            }

            public void mouseExited(java.awt.event.MouseEvent evt) {
                signUpButton.setBackground(accentBlue);
            }
        });
        constraints.gridx = 1;
        welcomePanel.add(signUpButton, constraints);

        mainFrame.getContentPane().removeAll();
        mainFrame.getContentPane().add(welcomePanel);
        mainFrame.revalidate();
        mainFrame.repaint();

        loginButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                showLoginDialog();
            }
        });

        signUpButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                showSignUpDialog();
            }
        });
    }

    private void fadeInWelcomePanel() {
        fadeTimer = new Timer();
        fadeTimer.schedule(new TimerTask() {
            @Override
            public void run() {
                if (alpha < 1.0f) {
                    alpha += 0.01f;
                    welcomePanel.repaint();
                } else {
                    fadeTimer.cancel();
                }
            }
        }, 10, 10); // Delay of 10ms, repeat every 10ms
    }

    private void showLoginDialog() {
        LogInDialog loginDialog = new LogInDialog();
        // You can add more functionality here if needed
    }

    private void showSignUpDialog() {
        SignUpDialog signUpDialog = new SignUpDialog();
        // You can add more functionality here if needed
    }

    private void showHomeScreen(String username) {
        HomeScreen homeScreen = new HomeScreen();
        mainFrame.getContentPane().removeAll();
        mainFrame.getContentPane().add(homeScreen);
        mainFrame.revalidate();
        mainFrame.repaint();
        mainFrame.pack();
        mainFrame.setVisible(true);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                new MainApplication();
            }
        });
    }
}
