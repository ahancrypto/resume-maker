package ResumeMaker;

import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter;
import java.awt.*;
import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.*;
import javax.imageio.ImageIO;
import java.util.Base64;
import java.awt.event.WindowEvent;

public class ResumeBuilder extends JFrame implements ActionListener {

    private JTextField nameField, emailField, phoneField;
    private JTextArea educationArea, workExperienceArea, skillsArea, aboutMeArea;
    private BufferedImage uploadedImage;
    private boolean isImageUploaded = false; 
    private boolean isResumeGenerated = false; 
    private File tempHtmlFile;
    private JComboBox<String> templateDropdown; 
    private JLabel staticTextLabel;

    public ResumeBuilder() {
        setTitle("Online CV/Resume Builder");
        setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
        setPreferredSize(new Dimension(600, 650));

        // Custom colors
        Color buttonColor = new Color(60, 110, 113);
        Color textColor = new Color(217, 217, 217);
        Color bgColor = new Color(53, 53, 53);

        getContentPane().setBackground(bgColor);

        JPanel formPanel = new JPanel(new GridLayout(0, 2, 10, 10));
        formPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        formPanel.setBackground(bgColor);

        JLabel nameLabel = new JLabel("Name:");
        nameLabel.setForeground(textColor);
        nameField = new JTextField();
        nameField.setColumns(15);
        nameField.setBackground(bgColor);
        nameField.setForeground(textColor);
        nameField.setCaretColor(Color.WHITE);

        JLabel emailLabel = new JLabel("Email:");
        emailLabel.setForeground(textColor);
        emailField = new JTextField();
        emailField.setColumns(15);
        emailField.setBackground(bgColor);
        emailField.setForeground(textColor);
        emailField.setCaretColor(Color.WHITE);

        JLabel phoneLabel = new JLabel("Phone:");
        phoneLabel.setForeground(textColor);
        phoneField = new JTextField();
        phoneField.setColumns(15);
        phoneField.setBackground(bgColor);
        phoneField.setForeground(textColor);
        phoneField.setCaretColor(Color.WHITE);

        JLabel educationLabel = new JLabel("Education:");
        educationLabel.setForeground(textColor);
        educationArea = new JTextArea(3,10);
        JScrollPane educationScrollPane = new JScrollPane(educationArea);
        educationArea.setBackground(bgColor);
        educationArea.setForeground(textColor);
        educationScrollPane.setBackground(bgColor);
        educationScrollPane.setForeground(textColor);
        educationArea.setCaretColor(Color.WHITE);

        JLabel workExperienceLabel = new JLabel("Work Experience:");
        workExperienceLabel.setForeground(textColor);
        workExperienceArea = new JTextArea(3,10);
        JScrollPane workExperienceScrollPane = new JScrollPane(workExperienceArea);
        workExperienceArea.setBackground(bgColor);
        workExperienceArea.setForeground(textColor);
        workExperienceScrollPane.setBackground(bgColor);
        workExperienceScrollPane.setForeground(textColor);
        workExperienceArea.setCaretColor(Color.WHITE);

        JLabel skillsLabel = new JLabel("Skills:");
        skillsLabel.setForeground(textColor);
        skillsArea = new JTextArea(3,10);
        JScrollPane skillsScrollPane = new JScrollPane(skillsArea);
        skillsArea.setBackground(bgColor);
        skillsArea.setForeground(textColor);
        skillsScrollPane.setBackground(bgColor);
        skillsScrollPane.setForeground(textColor);
        skillsArea.setCaretColor(Color.WHITE);

        JLabel aboutMeLabel = new JLabel("About Me:");
        aboutMeLabel.setForeground(textColor);
        aboutMeArea = new JTextArea(3,10);
        JScrollPane aboutMeScrollPane = new JScrollPane(aboutMeArea);
        aboutMeArea.setBackground(bgColor);
        aboutMeArea.setForeground(textColor);
        aboutMeScrollPane.setBackground(bgColor);
        aboutMeScrollPane.setForeground(textColor);
        aboutMeArea.setCaretColor(Color.WHITE);

        // Initialize the template dropdown

        //JLabel templateLabel = new JLabel("Select Template:");
        //templateLabel.setForeground(textColor);
        templateDropdown = new JComboBox<>(new String[]{"<Select Template>","Default", "Template 1", "Template 2","Template 3"});
        templateDropdown.setBackground(buttonColor);
        templateDropdown.setForeground(textColor);
        //templateDropdown.setCaretColor(Color.WHITE);

        // Add action listener to the template dropdown
        templateDropdown.addActionListener(this);
        staticTextLabel = new JLabel("Select Template:");

        // Set the foreground color for the static text label
        staticTextLabel.setForeground(textColor);

        // Add the static text label to the form panel

        // Add the template dropdown to the form panel
        //formPanel.add(new JLabel("Select Template:")); // Label for the dropdown

        formPanel.add(nameLabel);
        formPanel.add(nameField);
        formPanel.add(emailLabel);
        formPanel.add(emailField);
        formPanel.add(phoneLabel);
        formPanel.add(phoneField);
        formPanel.add(aboutMeLabel);
        formPanel.add(aboutMeScrollPane);
        formPanel.add(educationLabel);
        formPanel.add(educationScrollPane);
        formPanel.add(workExperienceLabel);
        formPanel.add(workExperienceScrollPane);
        formPanel.add(skillsLabel);
        formPanel.add(skillsScrollPane);
        formPanel.add(staticTextLabel);
        formPanel.add(templateDropdown);

        JPanel uploadPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        JButton uploadButton = new JButton("Upload Image");
        uploadButton.addActionListener(this);
        uploadButton.setBackground(buttonColor);
        uploadButton.setForeground(textColor);
        uploadPanel.add(uploadButton);

        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.CENTER)); 
        JButton generateButton = new JButton("Generate Resume");
        generateButton.addActionListener(this);
        generateButton.setBackground(buttonColor);
        generateButton.setForeground(textColor);
        buttonPanel.add(generateButton);

        JButton downloadButton = new JButton("Download Resume as PDF");
        downloadButton.addActionListener(this);
        downloadButton.setBackground(buttonColor);
        downloadButton.setForeground(textColor);
        buttonPanel.add(downloadButton);

        uploadPanel.setBackground(bgColor);
        buttonPanel.setBackground(bgColor);

        JPanel mainPanel = new JPanel(new BorderLayout());
        mainPanel.add(formPanel, BorderLayout.NORTH);
        mainPanel.add(uploadPanel, BorderLayout.CENTER);
        mainPanel.add(buttonPanel, BorderLayout.SOUTH);

        mainPanel.setBackground(bgColor);

        add(mainPanel, BorderLayout.NORTH);

        pack();
        setLocationRelativeTo(null);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        
        if (e.getActionCommand().equals("Upload Image")) {
            if (!isImageUploaded) {
                JFileChooser fileChooser = new JFileChooser();
                FileNameExtensionFilter filter = new FileNameExtensionFilter("Images", "jpg", "jpeg", "png");
                fileChooser.setFileFilter(filter);

                int returnVal = fileChooser.showOpenDialog(this);
                if (returnVal == JFileChooser.APPROVE_OPTION) {
                    File file = fileChooser.getSelectedFile();
                    try {
                        BufferedImage image = ImageIO.read(file);
                        if (image.getWidth() <= 300 && image.getHeight() <= 300) {
                            uploadedImage = image;
                            isImageUploaded = true;
                        } else {
                            JOptionPane.showMessageDialog(this, "Please select an image with dimensions up to 300x300 pixels.", "Invalid Image", JOptionPane.WARNING_MESSAGE);
                        }
                    } catch (IOException ex) {
                        ex.printStackTrace();
                    }
                }
            } else {
                JOptionPane.showMessageDialog(this, "Only one image can be uploaded.", "Multiple Images", JOptionPane.WARNING_MESSAGE);
            }
        } else if (e.getActionCommand().equals("Generate Resume")) {
            if (validateFields()) {
                isResumeGenerated = true;
                String selectedTemplate = (String) templateDropdown.getSelectedItem();
                if (selectedTemplate.equals("Default")) {
                    DefaultTemplate defaultTemplate = new DefaultTemplate();
                    // Run methods or perform operations specific to DefaultTemplate class
                    JOptionPane.showMessageDialog(this, "Default template applied. Resume generated successfully.", "Success", JOptionPane.INFORMATION_MESSAGE);
                } else if (selectedTemplate.equals("Template 1")) {
                    Template1 template1 = new Template1();
                    // Run methods or perform operations specific to Template1 class
                    JOptionPane.showMessageDialog(this, "Template 1 applied. Resume generated successfully.", "Success", JOptionPane.INFORMATION_MESSAGE);
                } else if (selectedTemplate.equals("Template 2")) {
                    Template2 template2 = new Template2();
                    // Run methods or perform operations specific to Template2 class
                    JOptionPane.showMessageDialog(this, "Template 2 applied. Resume generated successfully.", "Success", JOptionPane.INFORMATION_MESSAGE);
                } else if (selectedTemplate.equals("Template 3")) {
                    Template3 template3 = new Template3();
                    template3.main();
                    // Run methods or perform operations specific to Template3 class
                    JOptionPane.showMessageDialog(this, "Template 3 applied. Resume generated successfully.", "Success", JOptionPane.INFORMATION_MESSAGE);
                }else {
                    JOptionPane.showMessageDialog(this, "Please select a template to continue", "Template not selected", JOptionPane.WARNING_MESSAGE);
                }
            } else {
                JOptionPane.showMessageDialog(this, "Please fill in all required fields.", "Incomplete Form", JOptionPane.WARNING_MESSAGE);
            }
        } else if (e.getActionCommand().equals("Download Resume as PDF")) {
            int option = JOptionPane.showConfirmDialog(this, "The resume will be opened in your default browser. You may click the download PDF button to download your resume. Continue?", "Download PDF Instructions", JOptionPane.YES_NO_OPTION, JOptionPane.INFORMATION_MESSAGE);
            if (option == JOptionPane.YES_OPTION) {
                // Call the method to send inputs to the resume editor
                sendInputsToResumeEditor();
            }
        }
    }

    private boolean validateEmail(String email) {
        String emailRegex = "^[a-zA-Z0-9_+&*-]+(?:\\.[a-zA-Z0-9_+&*-]+)*@(?:[a-zA-Z0-9-]+\\.)+[a-zA-Z]{2,}$";
        return email.matches(emailRegex);
    }

    private boolean validatePhoneNumber(String phone) {
        return phone.matches("\\d{10}");
    }

    private boolean validateFields() {
        String name = nameField.getText();
        String email = emailField.getText();
        String phone = phoneField.getText();
        String education = educationArea.getText();
        String workExperience = workExperienceArea.getText();
        String skills = skillsArea.getText();
        String aboutMe = aboutMeArea.getText();
        return !name.isEmpty() && !phone.isEmpty() && !email.isEmpty() && !education.isEmpty() && !workExperience.isEmpty() && !skills.isEmpty() && !aboutMe.isEmpty();
    }

    private void sendInputsToResumeEditor() {
        String name = nameField.getText();
        String email = emailField.getText();
        String phone = phoneField.getText();
        String education = educationArea.getText();
        String workExperience = workExperienceArea.getText();
        String skills = skillsArea.getText();
        String aboutMe = aboutMeArea.getText();
        BufferedImage image=uploadedImage;
        String templateSelect=(String) templateDropdown.getSelectedItem();
        String template="";
        if(templateSelect=="Default"){
            template="Template_default.html";
        }
        if(templateSelect=="Template 1"){
            template="Template_1.html";
        }
        if(templateSelect=="Template 2"){
            template="Template_2.html";
        }
        if(templateSelect=="Template 3"){
            template="Template_3.html";
        }

        // Call the method in ResumeEditor to process the inputs
        ResumeEditor.generateResume(name, email, phone, education, workExperience, skills, aboutMe,image,template);
    }

    public boolean isResumeGenerated() {
        return isResumeGenerated;
    }

    @Override
    public void dispose() {
        super.dispose();
        if (tempHtmlFile != null) {
            tempHtmlFile.delete();
        }
    }

    @Override
    protected void processWindowEvent(WindowEvent e) {
        super.processWindowEvent(e);
        if (e.getID() == WindowEvent.WINDOW_CLOSING) {
            if (isResumeGenerated()) {
                dispose();
            } else {
                JOptionPane.showMessageDialog(this, "Please generate the resume before closing the application.", "Resume Not Generated", JOptionPane.WARNING_MESSAGE);
            }
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
                    ResumeBuilder resumeBuilder = new ResumeBuilder();
                    resumeBuilder.setVisible(true);
            });
    }
}
