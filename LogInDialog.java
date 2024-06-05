package ResumeMaker;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;

public class LogInDialog extends JFrame {

    private JTextField usernameField;
    private JPasswordField passwordField;

    public LogInDialog() {
        setTitle("Sign In ");
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        JPanel contentPane = new JPanel(new BorderLayout());
        contentPane.setBackground(new Color(0xFC, 0xA3, 0x11)); // #FCA311
        setContentPane(contentPane);

        // Raised platform effect for the entire form
        JPanel formPanel = new JPanel(new BorderLayout()) {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                Graphics2D g2d = (Graphics2D) g.create();
                int radius = 10; // adjust as needed
                g2d.setColor(getBackground());
                g2d.fillRoundRect(0, 0, getWidth() - 1, getHeight() - 1, radius, radius);
                g2d.dispose();
            }
        };
        formPanel.setBackground(new Color(0xFC, 0xA3, 0x11)); // #FCA311
         // Adjust corner radius as needed

        contentPane.add(formPanel, BorderLayout.CENTER);

        JPanel formContentPanel = new JPanel();
        formContentPanel.setLayout(new BoxLayout(formContentPanel, BoxLayout.Y_AXIS));
        formContentPanel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20)); // Padding
        formPanel.add(formContentPanel, BorderLayout.NORTH);

        // Form fields
        addFormField(formContentPanel, "Username:", usernameField = new JTextField(20));
        addFormField(formContentPanel, "Password:", passwordField = new JPasswordField(20));

        // Sign In button
        JButton signInButton = new JButton("Sign In");
        signInButton.setFont(new Font("Antipasto Pro Extrabold", Font.BOLD, 16));
        signInButton.setForeground(Color.WHITE);
        signInButton.setBackground(new Color(0x14, 0x21, 0x3D));
        signInButton.setFocusPainted(false);
        signInButton.setBorderPainted(false);
        signInButton.setOpaque(true);
        signInButton.setBorder(BorderFactory.createEmptyBorder(10, 20, 10, 20));
        signInButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                signIn();
            }
        });
        formPanel.add(signInButton, BorderLayout.SOUTH);

        pack();
        setLocationRelativeTo(null); // Center the window
        setVisible(true);
    }

    private void addFormField(JPanel formPanel, String label, JComponent component) {
        JPanel fieldPanel = new JPanel(new BorderLayout());
        fieldPanel.setBackground(Color.WHITE); // White background
        JLabel fieldLabel = new JLabel(label);
        fieldLabel.setFont(new Font("Antipasto Pro Extrabold", Font.BOLD, 14));
        fieldPanel.add(fieldLabel, BorderLayout.NORTH);
        fieldPanel.add(component, BorderLayout.CENTER);
        formPanel.add(fieldPanel);
    }

    private void signIn() {
        // Get user input
        String username = usernameField.getText();
        String password = new String(passwordField.getPassword());

        // Access user details file
        String userDetailsFile = "user_info.txt";

        // Check if user exists and password matches
        if (isValidCredentials(userDetailsFile, username, password)) {
            // Authentication successful
            JOptionPane.showMessageDialog(this, "Welcome back! You have successfully signed in.", "Success", JOptionPane.INFORMATION_MESSAGE);
            // Open home page
            dispose(); // Close the sign-in form
            new HomeScreen(); // Open the home page
        } else if (isNewUser(userDetailsFile, username)) {
            // User not found in details file
            int option = JOptionPane.showConfirmDialog(this, "You are a new user. Would you like to sign up?", "New User", JOptionPane.YES_NO_OPTION);
            if (option == JOptionPane.YES_OPTION) {
                // Open sign-up form
                new SignUpDialog();
            } else {
                // User chose not to sign up
                JOptionPane.showMessageDialog(this, "Hope we get together soon!", "Message", JOptionPane.INFORMATION_MESSAGE);
            }
        } else {
            // Invalid credentials
            JOptionPane.showMessageDialog(this, "Invalid mobile number/email ID or password. Please try again.", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    private boolean isValidCredentials(String userDetailsFile, String username, String password) {
        try (BufferedReader reader = new BufferedReader(new FileReader(userDetailsFile))) {
            String line;
            boolean isUserFound = false;
            

            while ((line = reader.readLine()) != null) {
                if (line.startsWith("Username: ")) {
                    String savedusername = line.substring(line.indexOf(":") + 1).trim();

                    if ((savedusername.equals(username)) ) {
                        isUserFound = true;
                        saveSignedInUserDetails(userDetailsFile, savedusername);
                        // Additional checks if the password matches
                        // Return true if password matches, else false
                    }
                }
            }
            return isUserFound;
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }
    }

    private void saveSignedInUserDetails(String userDetailsFile, String mobileOrEmail) {
        try (BufferedReader reader = new BufferedReader(new FileReader(userDetailsFile));
             BufferedWriter writer = new BufferedWriter(new FileWriter("sign_in.txt"))) {
            String line;
            while ((line = reader.readLine()) != null) {
                if (line.startsWith("Username: ") || line.startsWith("Phone: ") || line.startsWith("Email: ") || line.startsWith("Password: ")){
                    writer.write(line);
                    writer.newLine();
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private boolean isNewUser(String userDetailsFile, String username) {
        try (BufferedReader reader = new BufferedReader(new FileReader(userDetailsFile))) {
            String line;
            while ((line = reader.readLine()) != null) {
                if ((line.startsWith("Username: ") && line.substring(line.indexOf(":") + 1).trim().equals(username)) ){
                    return false; // User found
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return true; // User not found
    }

    private boolean isAlreadySignedIn() {
        return Files.exists(Paths.get("sign_in.txt"));
    }

    private void saveSignedInUser(String username) {
        try (PrintWriter writer = new PrintWriter(new FileWriter("sign_in.txt"));
             BufferedReader reader = new BufferedReader(new FileReader("user_info.txt"))) {
            String line;
            boolean isUserFound = false;
            
            while ((line = reader.readLine()) != null) {
                if (line.startsWith("Username: ") ) {
                    String savedusername= line.substring(line.indexOf(":") + 1).trim();
                    if (savedusername.equals(username)) {
                        // Save details of the signed-in user
                        writer.println("Username: " + savedusername);
                        writer.println("Email: " + reader.readLine());
                        writer.println("Phone: " + reader.readLine());
                        writer.println("Password: " + reader.readLine());
                        if (reader.readLine() != " ")
                            break;

                        isUserFound = true;

                    }
                }
            }

            if (!isUserFound) {
                // If user not found, display an error message
                System.out.println("User not found!");
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        // Run the sign-in form
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                new LogInDialog();
            }
        });
    }
}
