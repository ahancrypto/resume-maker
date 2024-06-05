package ResumeMaker;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;

public class ImageViewer extends JFrame {
    private Image image;
    private ImagePanel imagePanel;
    private double zoomFactor = 1.0;
    private Color primaryBlue = new Color(30, 144, 255); // Dodger Blue
    private Color darkBlue = new Color(0, 0, 139); // Dark Blue

    public ImageViewer(String imagePath) {
        try {
            image = ImageIO.read(new File(imagePath));
        } catch (IOException e) {
            e.printStackTrace();
        }

        setTitle("Image Viewer");
        setSize(600, 500);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);

        imagePanel = new ImagePanel(image);
        add(imagePanel, BorderLayout.CENTER);

        JPanel buttonPanel = new JPanel();
        buttonPanel.setBackground(primaryBlue);
        JButton zoomInButton = new JButton("Zoom In");
        JButton zoomOutButton = new JButton("Zoom Out");

        zoomInButton.setBackground(primaryBlue);
        zoomInButton.setForeground(Color.WHITE);
        zoomInButton.setFocusPainted(false);
        zoomInButton.setCursor(new Cursor(Cursor.HAND_CURSOR));
        zoomInButton.setBorder(BorderFactory.createEmptyBorder(5, 10, 5, 10));

        zoomOutButton.setBackground(primaryBlue);
        zoomOutButton.setForeground(Color.WHITE);
        zoomOutButton.setFocusPainted(false);
        zoomOutButton.setCursor(new Cursor(Cursor.HAND_CURSOR));
        zoomOutButton.setBorder(BorderFactory.createEmptyBorder(5, 10, 5, 10));

        zoomInButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                zoomFactor *= 1.1;
                imagePanel.repaint();
            }
        });

        zoomOutButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                zoomFactor /= 1.1;
                imagePanel.repaint();
            }
        });

        buttonPanel.add(zoomInButton);
        buttonPanel.add(zoomOutButton);

        add(buttonPanel, BorderLayout.SOUTH);

        pack();
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            ImageViewer imageViewer = new ImageViewer("path/to/image.jpg");
            imageViewer.setVisible(true);
        });
    }

    private class ImagePanel extends JPanel {
        private Image image;

        public ImagePanel(Image image) {
            this.image = image;
            setBackground(darkBlue);
        }

        @Override
        protected void paintComponent(Graphics g) {
            super.paintComponent(g);
            Graphics2D g2d = (Graphics2D) g.create();
            int w = getWidth();
            int h = getHeight();
            int imageWidth = (int) (image.getWidth(null) * zoomFactor);
            int imageHeight = (int) (image.getHeight(null) * zoomFactor);
            int x = (w - imageWidth) / 2;
            int y = (h - imageHeight) / 2;
            g2d.drawImage(image, x, y, imageWidth, imageHeight, null);
            g2d.dispose();
        }
    }
}