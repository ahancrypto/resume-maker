package ResumeMaker;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.ArrayList;
import java.util.Arrays;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.io.BufferedReader;
import java.io.FileReader;

public class SignUpDialog extends JDialog {

    private JTextField emailField;
    private JTextField phoneField;
    private JTextField usernameField;
    private JPasswordField passwordField;
    private JPasswordField confirmPasswordField;
    private JCheckBox showPasswordCheckbox;
    private JCheckBox showConfirmPasswordCheckbox;
    private JLabel usernameAvailabilityLabel;
    private JButton signUpButton;
    private JButton cancelButton;
    private boolean signUpSuccessful;
    private String emailDomain;

    public SignUpDialog() {
        super((Frame)null, "Sign Up", true);
        setSize(350, 500);
        setLocationRelativeTo(null);

        // Set background color
        getContentPane().setBackground(new Color(255, 182, 193)); // Light Pink

        // Initialize components
        emailField = new JTextField(20);
        phoneField = new JTextField(20);
        usernameField = new JTextField(20);
        passwordField = new JPasswordField(20);
        confirmPasswordField = new JPasswordField(20);
        signUpButton = new JButton("Sign Up");
        cancelButton = new JButton("Cancel");
        showPasswordCheckbox = new JCheckBox("Show Password");
        showConfirmPasswordCheckbox = new JCheckBox("Show Confirm Password");
        usernameAvailabilityLabel = new JLabel("");

        // Layout components
        JPanel panel = new JPanel(new GridBagLayout());
        GridBagConstraints constraints = new GridBagConstraints();
        constraints.insets = new Insets(10, 10, 10, 10);
        constraints.fill = GridBagConstraints.HORIZONTAL;

        constraints.gridx = 0;
        constraints.gridy = 0;
        panel.add(new JLabel("Email: "), constraints);

        constraints.gridx = 1;
        panel.add(emailField, constraints);

        constraints.gridx = 0;
        constraints.gridy = 1;
        panel.add(new JLabel("Phone Number: "), constraints);

        constraints.gridx = 1;
        panel.add(phoneField, constraints);

        constraints.gridx = 0;
        constraints.gridy = 2;
        panel.add(new JLabel("Username: "), constraints);

        constraints.gridx = 1;
        panel.add(usernameField, constraints);

        constraints.gridy = 3;
        panel.add(usernameAvailabilityLabel, constraints);

        constraints.gridx = 0;
        constraints.gridy = 4;
        panel.add(new JLabel("Password: "), constraints);

        constraints.gridx = 1;
        panel.add(passwordField, constraints);

        constraints.gridy = 5;
        panel.add(showPasswordCheckbox, constraints);

        constraints.gridx = 0;
        constraints.gridy = 6;
        panel.add(new JLabel("Confirm Password: "), constraints);

        constraints.gridx = 1;
        panel.add(confirmPasswordField, constraints);

        constraints.gridy = 7;
        panel.add(showConfirmPasswordCheckbox, constraints);

        constraints.gridx = 0;
        constraints.gridy = 8;
        constraints.gridwidth = 3;
        panel.add(signUpButton, constraints);

        constraints.gridy = 9;
        panel.add(cancelButton, constraints);

        // Add action listeners
        signUpButton.addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent e) {
                    performSignUp();
                }
            });

        cancelButton.addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent e) {
                    dispose(); // Close the dialog
                }
            });

        showPasswordCheckbox.addItemListener(new ItemListener() {
                public void itemStateChanged(ItemEvent e) {
                    if (e.getStateChange() == ItemEvent.SELECTED) {
                        passwordField.setEchoChar((char) 0);
                    } else {
                        passwordField.setEchoChar('*');
                    }
                }
            });

        showConfirmPasswordCheckbox.addItemListener(new ItemListener() {
                public void itemStateChanged(ItemEvent e) {
                    if (e.getStateChange() == ItemEvent.SELECTED) {
                        confirmPasswordField.setEchoChar((char) 0);
                    } else {
                        confirmPasswordField.setEchoChar('*');
                    }
                }
            });

        usernameField.addKeyListener(new KeyListener() {
                public void keyTyped(KeyEvent e) {
                    checkUsernameAvailability();
                }

                public void keyPressed(KeyEvent e) {
                }

                public void keyReleased(KeyEvent e) {
                }
            });

        add(panel);
        pack();
        setVisible(true);
    }

    private void performSignUp() {
        // Collect sign up information
        String email = emailField.getText();
        String phone = phoneField.getText();
        String username = usernameField.getText();
        String password = new String(passwordField.getPassword());
        String confirmPassword = new String(confirmPasswordField.getPassword());

        // Check for incomplete fields
        if (email.isEmpty() || phone.isEmpty() || username.isEmpty() || password.isEmpty() || confirmPassword.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Please fill in all fields", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        // Validate email format
        if (!isValidEmail(email)) {
            JOptionPane.showMessageDialog(this, "Invalid email format", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        // Validate phone number format
        if (!isValidPhoneNumber(phone)) {
            JOptionPane.showMessageDialog(this, "Invalid phone number format (must be 10 digits)", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        // Check if passwords match
        if (!password.equals(confirmPassword)) {
            JOptionPane.showMessageDialog(this, "Passwords do not match", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        // Check if username is available
        if (!isUsernameAvailable(username)) {
            JOptionPane.showMessageDialog(this, "Username is already in use", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        // Check if email is available
        if (!isEmailAvailable(email)) {
            JOptionPane.showMessageDialog(this, "Email is already in use", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        // Check if phone number is available
        if (!isPhoneNumberAvailable(phone)) {
            JOptionPane.showMessageDialog(this, "Phone number is already in use", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        // Display confirmation message
        JOptionPane.showMessageDialog(this, "Sign up successful!");

        // Write user information to file
        writeUserInfoToFile(email, phone, username, password);

        signUpSuccessful = true;
        dispose(); // Close the dialog
    }

    private boolean isValidEmail(String email) {
        String emailRegex = "^[a-zA-Z0-9_+&*-]+(?:\\.[a-zA-Z0-9_+&*-]+)*@(?:[a-zA-Z0-9-]+\\.)+[a-zA-Z]{2,7}$";
        Pattern pattern = Pattern.compile(emailRegex);
        Matcher matcher = pattern.matcher(email);
        return matcher.matches();
    }

    private boolean isValidPhoneNumber(String phone) {
        return phone.matches("\\d{10}");
    }

    private boolean isUsernameAvailable(String username) {
        try (BufferedReader reader = new BufferedReader(new FileReader("user_info.txt"))) {
            String line;
            while ((line = reader.readLine()) != null) {
                if (line.contains("Username: " + username)) {
                    return false; // Username already exists
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
            // Handle exception appropriately
        }
        return true; // Username is available
    }

    private boolean isEmailAvailable(String email) {
        try (BufferedReader reader = new BufferedReader(new FileReader("user_info.txt"))) {
            String line;
            while ((line = reader.readLine()) != null) {
                if (line.contains("Email: " + email)) {
                    return false; // Email already exists
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
            // Handle exception appropriately
        }
        return true; // Email is available
    }

    private boolean isPhoneNumberAvailable(String phone) {
        try (BufferedReader reader = new BufferedReader(new FileReader("user_info.txt"))) {
            String line;
            while ((line = reader.readLine()) != null) {
                if (line.contains("Phone: " + phone)) {
                    return false; // Phone number already exists
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
            // Handle exception appropriately
        }
        return true; // Phone number is available
    }

    private void generateAndDisplayAnagrams() {
        String email = emailField.getText();
        String[] parts = email.split("@");

        if (parts.length == 2) {
            String usernameSuggestion = parts[0];
            ArrayList<String> anagrams = generateAnagrams(usernameSuggestion);
            if (!anagrams.isEmpty()) {
                Frame parentFrame = (Frame) SwingUtilities.getWindowAncestor(this);
                SuggestionsDialog suggestionsDialog = new SuggestionsDialog(parentFrame, anagrams);
                String selectedUsername = suggestionsDialog.getSelectedUsername();
                if (selectedUsername != null) {
                    usernameField.setText(selectedUsername);
                }
            }
        }
    }


    private ArrayList<String> generateAnagrams(String input) {
        ArrayList<String> anagrams = new ArrayList<>();
        // Generate anagrams
        for (int i = 0; i <= input.length(); i++) {
            String anagram = input.substring(0, i) + generateRandomSymbol() + generateRandomNumbers() + input.substring(i);
            if (isUsernameAvailable(anagram)) {
                anagrams.add(anagram);
            }
        }
        return anagrams;
    }

    private char generateRandomSymbol() {
        // Generate random symbol ('_' or '@')
        return Math.random() < 0.5 ? '_' : '@';
    }

    private String generateRandomNumbers() {
        // Generate random numbers
        StringBuilder numbers = new StringBuilder();
        for (int i = 0; i < 3; i++) {
            numbers.append((int) (Math.random() * 10));
        }
        return numbers.toString();
    }


    private void nextPermutation(char[] array) {
        int i = array.length - 2;
        while (i >= 0 && array[i] >= array[i + 1]) {
            i--;
        }
        if (i < 0) {
            return;
        }
        int j = array.length - 1;
        while (array[j] <= array[i]) {
            j--;
        }
        swap(array, i, j);
        reverse(array, i + 1);
    }

    private void swap(char[] array, int i, int j) {
        char temp = array[i];
        array[i] = array[j];
        array[j] = temp;
    }

    private void reverse(char[] array, int start) {
        int i = start;
        int j = array.length - 1;
        while (i < j) {
            swap(array, i, j);
            i++;
            j--;
        }
    }

    private void checkUsernameAvailability() {
        String username = usernameField.getText();
        boolean isAvailable = isUsernameAvailable(username);

        if (isAvailable) {
            usernameAvailabilityLabel.setText("✔ Username available");
            usernameAvailabilityLabel.setForeground(Color.GREEN);
        } else {
            String usernam = "";
            for (int i = username.length() - 1; i >= 0; i--) {
                usernam += username.charAt(i);
                if (!isUsernameAvailable(usernam)) {
                    usernameAvailabilityLabel.setText("✘ Username unavailable");
                    usernameAvailabilityLabel.setForeground(Color.RED);
                    generateAndDisplayAnagrams();
                    return;
                }
            }
        }
    }

    private void writeUserInfoToFile(String email, String phone, String username, String password) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter("user_info.txt", true))) {
            // Write user information to file
            writer.write("Username: " + username);
            writer.newLine();
            writer.write("Email: " + email);
            writer.newLine();
            writer.write("Phone: " + phone);
            writer.newLine();
            
            writer.write("Password: " + password);
            writer.newLine();
            writer.write("Resumes: ");
            writer.newLine(); // Add an empty line for separation between users
        } catch (IOException e) {
            e.printStackTrace(); // Handle the exception appropriately
        }
    }

    public boolean isSignUpSuccessful() {
        return signUpSuccessful;
    }

    public static void main(String[] args) {
        new SignUpDialog();
    }
}
