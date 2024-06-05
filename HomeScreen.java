package ResumeMaker;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.*;

public class HomeScreen extends JFrame {
    private JButton profileButton;
    private JButton generateButton;
    private int resumeCounter;
    private Color primaryBlue = new Color(30, 144, 255); // Dodger Blue
    private Color darkBlue = new Color(0, 0, 139); // Dark Blue

    public HomeScreen() {
        setTitle("ResumeHub");
        setSize(10, 1200);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        // Read resume counter from file
        resumeCounter = readResumeCounterFromFile("user_info.txt");

        createUI();
        setVisible(true);
        setExtendedState(JFrame.MAXIMIZED_BOTH);

    }

    private void createUI() {
        JPanel mainPanel = new JPanel(new BorderLayout()) {
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
                    g2d.dispose();
                }
            };

        mainPanel.setLayout(new BoxLayout(mainPanel, BoxLayout.Y_AXIS));
        mainPanel.setBackground(primaryBlue);

        JPanel titlePanel=new JPanel();
        titlePanel.setBackground(new Color(0,0,0,0));
        titlePanel.setForeground(Color.WHITE);
        JLabel titleLabel=new JLabel("ResumeHub");
        titleLabel.setFont(new Font("Cavilant",Font.BOLD,20));
        titleLabel.setForeground(Color.WHITE);
        titlePanel.add(titleLabel); // Add this line
        mainPanel.add(titlePanel);
        // Gallery Section
        JPanel galleryPanel = new JPanel();
        galleryPanel.setBackground(new Color(0, 0, 0, 0));
        galleryPanel.setPreferredSize(new Dimension(50,50));
        galleryPanel.setLayout(new BoxLayout(galleryPanel, BoxLayout.X_AXIS));

         /**Gallery title
        JLabel galleryTitle = new JLabel("Gallery");
        galleryTitle.setFont(new Font("Raleway", Font.BOLD, 16));
        galleryTitle.setForeground(Color.WHITE);
        galleryTitle.setHorizontalAlignment(JLabel.CENTER);
        mainPanel.add(galleryTitle, BorderLayout.SOUTH);**/

        // Add images to the gallery
        addImageToGallery(galleryPanel, "H:\\ClassXI\\ResumeMaker\\template1.png", "Template 1(Deafult)");
        addImageToGallery(galleryPanel, "H:\\ClassXI\\ResumeMaker\\template2.png", "Template 2");
        addImageToGallery(galleryPanel, "H:\\ClassXI\\ResumeMaker\\template3.png", "Template 3");
        addImageToGallery(galleryPanel, "H:\\ClassXI\\ResumeMaker\\template4.png", "Template 4");
        /** Create a scroll pane with a custom style
        JPanel scrollablePanel = new JPanel(new BorderLayout());
        scrollablePanel.add(galleryPanel, BorderLayout.CENTER);

        JScrollPane scrollPane = new JScrollPane(scrollablePanel);
        scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
        scrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
        scrollPane.getVerticalScrollBar().setUI(new CustomScrollBarUI());
        scrollPane.getViewport().setBackground(new Color(0, 0, 0, 0));
        scrollPane.setBorder(BorderFactory.createEmptyBorder());

         //Add the scroll pane to the frame
        mainPanel.add(scrollPane, BorderLayout.EAST);**/

        // Profile Button
        profileButton = new JButton("Profile");
        profileButton.setFont(new Font("Raleway", Font.BOLD, 12)); // Adjust the font size
        profileButton.setBackground(primaryBlue);
        profileButton.setForeground(Color.WHITE);
        profileButton.setFocusPainted(false);
        profileButton.setCursor(new Cursor(Cursor.HAND_CURSOR));
        profileButton.setBorder(BorderFactory.createEmptyBorder(5, 10, 5, 10)); // Adjust the border size
        mainPanel.add(profileButton);
        profileButton.setAlignmentX(Component.CENTER_ALIGNMENT); // Align button horizontally to the center

        titlePanel.add(profileButton);
        // Add the profile panel to the main panel

        mainPanel.add(Box.createVerticalGlue()); // Add vertical glue to push components to the top
        mainPanel.add(galleryPanel);
        mainPanel.add(Box.createVerticalGlue()); // Add vertical glue to create space between gallery and button

        // Generate Button
        generateButton = new JButton("Generate Your Resume Now");
        generateButton.setFont(new Font("Raleway", Font.BOLD, 12)); // Adjust the font size
        generateButton.setBackground(primaryBlue);
        generateButton.setForeground(Color.WHITE);
        generateButton.setFocusPainted(false);
        generateButton.setCursor(new Cursor(Cursor.HAND_CURSOR));
        generateButton.setBorder(BorderFactory.createEmptyBorder(5, 10, 5, 10)); // Adjust the border size
        mainPanel.add(generateButton);
        generateButton.setAlignmentX(Component.CENTER_ALIGNMENT); // Align button horizontally to the center

        getContentPane().add(mainPanel);

        // Profile button click listener
        profileButton.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    // Open the Profile window
                    Profile profile = new Profile();
                    profile.setVisible(true);
                }
            });

        // Generate button click listener
        generateButton.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    // Open the ResumeBuilder window
                    ResumeBuilder resumeBuilder = new ResumeBuilder();
                    resumeBuilder.setVisible(true);
                    resumeBuilder.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

                    // Increment resume counter and save to file
                    resumeCounter++;
                    saveResumeCounterToFile("user_info.txt", resumeCounter);
                }
            });
    }

    private int readResumeCounterFromFile(String filename) {
        try (BufferedReader reader = new BufferedReader(new FileReader(filename))) {
            String line;
            while ((line = reader.readLine()) != null) {
                if (line.startsWith("Resumes: ")) {
                    return Integer.parseInt(line.substring("Resumes: ".length()).trim());
                }
            }
        } catch (IOException | NumberFormatException e) {
            e.printStackTrace();
        }
        return 0; // Default valueif not found or error occurs
    }

    private void addImageToGallery(JPanel galleryPanel, String imagePath, String imageLabelText) {
        Image image = new ImageIcon(imagePath).getImage();
        Image thumbnail = image.getScaledInstance(300, 300, java.awt.Image.SCALE_SMOOTH); 
        ImageIcon thumbnailIcon = new ImageIcon(thumbnail);

        JLabel imageIconLabel = new JLabel(thumbnailIcon);
        imageIconLabel.setCursor(new Cursor(Cursor.HAND_CURSOR));
        imageIconLabel.addMouseListener(new MouseAdapter() {
                @Override
                public void mouseClicked(MouseEvent e) {
                    ImageViewer imageViewer = new ImageViewer(imagePath);
                    imageViewer.setVisible(true);
                }
            });

        JLabel imageNameLabel = new JLabel(imageLabelText);
        imageNameLabel.setFont(new Font("Raleway", Font.PLAIN, 12));
        imageNameLabel.setBounds(0, -50, 100, 20);
        imageNameLabel.setForeground(Color.WHITE);

        JPanel imagePanel = new JPanel();
        imagePanel.setBackground(new Color(0, 0, 0, 0));
        imagePanel.setLayout(new BorderLayout());
        imagePanel.add(imageIconLabel, BorderLayout.CENTER);
        imagePanel.add(imageNameLabel, BorderLayout.NORTH);

        galleryPanel.add(imagePanel);
        galleryPanel.add(Box.createVerticalStrut(10)); // Add vertical spacing
    }

    private void saveResumeCounterToFile(String filename, int resumeCounter) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(filename))) {
            writer.write("Resumes: " + resumeCounter);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
                    HomeScreen homeScreen = new HomeScreen();
                    homeScreen.setVisible(true);
            });
    }
}