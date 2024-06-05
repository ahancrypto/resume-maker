package ResumeMaker;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.FileWriter;

public class Profile extends JFrame {
    private User currentUser;

    private JLabel usernameLabel;
    private JLabel mobileLabel;
    private JLabel emailLabel;
    private JLabel countLabel;

    private JButton editInfoButton;
    private JButton logoutButton;
    //private JButton ordersButton;
    private JButton changePasswordButton;

    private CirclePanel circlePanel;

    public Profile() {
        setTitle("Profile");
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setSize(400, 400);  // Set preferred size
        setLocationRelativeTo(null); // Center the window

        currentUser = new User();

        // Load user details
        loadCurrentUserDetails();

        JPanel panel = new JPanel(new GridLayout(0, 1, 10, 10));
        panel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10)); // Add padding
        panel.setBackground(new Color(0xFF, 0xFF, 0xC7)); // #FFFFC7

        circlePanel = new CirclePanel(currentUser.getuserName());
        circlePanel.setBackground(new Color(0xFF, 0xFF, 0xC7));
        panel.add(circlePanel);
         
        usernameLabel = new JLabel("Username: " + currentUser.getuserName());
        usernameLabel.setForeground(new Color(0, 2, 0)); // Dark gray text
        panel.add(usernameLabel);

        mobileLabel = new JLabel("Phone: " + currentUser.getMobilePhone());
        mobileLabel.setForeground(new Color(0, 2, 0)); // Dark gray text
        panel.add(mobileLabel);

        emailLabel = new JLabel("Email: " + currentUser.getEmail());
        emailLabel.setForeground(new Color(0, 2, 0)); // Dark gray text
        panel.add(emailLabel);

        logoutButton = new JButton("Logout");
        logoutButton.setBackground(new Color(255, 102, 102)); // Red button
        logoutButton.setForeground(Color.WHITE); // White text
        logoutButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                logout();
            }
        });
        panel.add(logoutButton);

        changePasswordButton = new JButton("Change Password"); // Create change password button
        changePasswordButton.setBackground(new Color(223, 96, 23)); // 223, 96, 23
        changePasswordButton.setForeground(Color.WHITE); // White text
        changePasswordButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Open window/dialog to change password
                changePassword();
            }
        });
        panel.add(changePasswordButton);

        add(panel);
        setVisible(true);
    }

    private void loadCurrentUserDetails() {
        try (BufferedReader reader = new BufferedReader(new FileReader("sign_in.txt"))) {
            String line;
            while ((line = reader.readLine()) != null) {
                if (line.startsWith("Username:")) {
                    currentUser.setuserName(line.substring("Username:".length()).trim());
                } else if (line.startsWith("Email:")) {
                    currentUser.setEmail(line.substring("Email:".length()).trim());
                } else if (line.startsWith("Phone:")) {
                    currentUser.setMobilePhone(line.substring("Phone:".length()).trim());
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void changePassword() {
        // Create text fields for existing password, new password, and confirm password
        JTextField existingPasswordField = new JPasswordField();
        JTextField newPasswordField = new JPasswordField();
        JTextField confirmPasswordField = new JPasswordField();

        JPanel panel = new JPanel(new GridLayout(0, 1));
        panel.add(new JLabel("Existing Password:"));
        panel.add(existingPasswordField);
        panel.add(new JLabel("New Password:"));
        panel.add(newPasswordField);
        panel.add(new JLabel("Confirm Password:"));
        panel.add(confirmPasswordField);

        int result = JOptionPane.showConfirmDialog(null, panel, "Change Password",
                JOptionPane.OK_CANCEL_OPTION, JOptionPane.PLAIN_MESSAGE);

        if (result == JOptionPane.OK_OPTION) {
            String existingPassword = existingPasswordField.getText();
            String newPassword = newPasswordField.getText();
            String confirmPassword = confirmPasswordField.getText();

            // Retrieve existing password from signed-in file
            String retrievedPassword = readPasswordFromSignedInFile();

            if (retrievedPassword == null) {
                JOptionPane.showMessageDialog(this, "Error loading existing password.", "Error", JOptionPane.ERROR_MESSAGE);
                return;
            }

            if (!existingPassword.equals(retrievedPassword)) {
                JOptionPane.showMessageDialog(this, "Incorrect existing password.", "Error", JOptionPane.ERROR_MESSAGE);
                return;
            }

            if (existingPassword.equals(newPassword)) {
                JOptionPane.showMessageDialog(this, "New password should be different from existing password.", "Error", JOptionPane.ERROR_MESSAGE);
                return;
            }

            if (!newPassword.equals(confirmPassword)) {
                JOptionPane.showMessageDialog(this, "New password and confirm password do not match.", "Error", JOptionPane.ERROR_MESSAGE);
                return;
            }

            // Update password in user details file
            updateUserPasswordInUserFile(currentUser.getEmail(), newPassword);
            JOptionPane.showMessageDialog(this, "Password changed successfully.", "Success", JOptionPane.INFORMATION_MESSAGE);
        }
    }

    private String readPasswordFromSignedInFile() {
        String password = null;
        try (BufferedReader reader = new BufferedReader(new FileReader("sign_in.txt"))) {
            String line;
            while ((line = reader.readLine()) != null) {
                if (line.startsWith("Password:")) {
                    password = line.substring("Password:".length()).trim();
                    break; // Stop reading once password is found
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return password;
    }

    private void updateUserPasswordInUserFile(String username, String newPassword) {
        // Read the existing user details from the file and update the password
        try (BufferedReader reader = new BufferedReader(new FileReader("user_info.txt"))) {
            String line;
            StringBuilder updatedContent = new StringBuilder();
            boolean userFound = false;

            while ((line = reader.readLine()) != null) {
                // Check if the line contains the password for the user
                if (line.startsWith("Password:") && !userFound) {
                    // Replace the existing password with the new password
                    updatedContent.append("Password: ").append(newPassword).append("\n");
                    userFound = true; // Set flag to indicate user's password is updated
                } else {
                    updatedContent.append(line).append("\n"); // Append other lines as they are
                }
            }

            if (!userFound) {
                // User not found in the file
                JOptionPane.showMessageDialog(this, "User details not found.", "Error", JOptionPane.ERROR_MESSAGE);
                return;
            }

            // Write the updated content back to the file
            try (FileWriter writer = new FileWriter("user_info.txt")) {
                writer.write(updatedContent.toString());
                JOptionPane.showMessageDialog(this, "Password changed successfully.", "Success", JOptionPane.INFORMATION_MESSAGE);
            } catch (IOException e) {
                e.printStackTrace();
                JOptionPane.showMessageDialog(this, "Error updating user details.", "Error", JOptionPane.ERROR_MESSAGE);
            }
        } catch (IOException e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(this, "Error updating user details.", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void logout() {
        // Remove user details from signed in file
        try (FileWriter writer = new FileWriter("sign_in.txt")) {
            writer.write(""); // Clear the file
        } catch (IOException e) {
            e.printStackTrace();
        }

        JOptionPane.showMessageDialog(this, "You are logged out.", "Logout", JOptionPane.INFORMATION_MESSAGE);
        int option = JOptionPane.showConfirmDialog(this, "Do you want to go to the Sign In page?", "Logout", JOptionPane.YES_NO_OPTION);
        if (option == JOptionPane.YES_OPTION) {
            // Open sign-in page
            new LogInDialog();
        } else {
            // Exit the application
            System.exit(0);
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                new Profile();
            }
        });
    }
}

class User {
    private String username;
    private String email;
    private String mobilePhone;
    String address;
    private String password;

    public User() {
    }

    public User(String username, String email, String mobilePhone, String password) {
        this.username = username;
        this.email = email;
        this.mobilePhone = mobilePhone;

        this.password = password;
    }

    // Getters and setters
    public String getuserName() {
        return username;
    }

    public void setuserName(String username) {
        this.username = username;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getMobilePhone() {
        return mobilePhone;
    }

    public void setMobilePhone(String mobilePhone) {
        this.mobilePhone = mobilePhone;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}

class CirclePanel extends JPanel {
    private String text;

    public CirclePanel(String text) {
        this.text = text;
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2d = (Graphics2D) g.create();

        // Draw circle
        int diameter = Math.min(getWidth(), getHeight());
        int x = (getWidth() - diameter) / 2;
        int y = (getHeight() - diameter) / 2;
        g2d.setColor(new Color(0xF6, 0x76, 0x06));
        g2d.fillOval(x, y, diameter, diameter);

        // Draw circle border
        g2d.setColor(new Color(0x00, 0x02, 0x00));// Green border color
        g2d.setStroke(new BasicStroke(5)); // Border thickness
        g2d.drawOval(x, y, diameter, diameter);

        // Draw text
        g2d.setColor(new Color(0xFF, 0xFF, 0xC7)); 
        g2d.setFont(new Font("Arial", Font.BOLD, diameter / 2));
        FontMetrics metrics = g2d.getFontMetrics();
        int textWidth = metrics.stringWidth(text);
        int textHeight = metrics.getHeight();
        int textX = (getWidth() - textWidth) / 2 + 12;
        int textY = (getHeight() + textHeight) / 2-5;
        g2d.drawString(text.substring(0, Math.min(2, text.length())), textX, textY);

        g2d.dispose();
    }
}
