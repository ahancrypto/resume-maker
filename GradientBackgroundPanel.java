package ResumeMaker;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class GradientBackgroundPanel extends JPanel implements ActionListener {

    private Color startColor;
    private Color endColor;
    private Timer timer;
    private float hue;

    public GradientBackgroundPanel(Color startColor, Color endColor) {
        this.startColor = startColor;
        this.endColor = endColor;
        this.hue = 0.0f;
        this.timer = new Timer(20, this); // Timer to update color every 20 milliseconds
        this.timer.start();
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2d = (Graphics2D) g;

        int width = getWidth();
        int height = getHeight();

        // Create a gradient paint
        GradientPaint gradientPaint = new GradientPaint(0, 0, startColor, width, height, endColor);

        // Set the paint to the graphics context
        g2d.setPaint(gradientPaint);

        // Fill the panel with the gradient paint
        g2d.fillRect(0, 0, width, height);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        // Update hue to create color animation
        hue += 0.005f; // Adjust the speed of the animation as needed

        // Convert hue to RGB colors
        Color newStartColor = Color.getHSBColor(hue, 1.0f, 1.0f);
        Color newEndColor = Color.getHSBColor((hue + 0.5f) % 1.0f, 1.0f, 1.0f);

        // Update start and end colors
        startColor = newStartColor;
        endColor = newEndColor;

        // Repaint the panel
        repaint();
    }

    // Example usage:
    public static void main(String[] args) {
        JFrame frame = new JFrame("Gradient Background Panel Test");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(400, 300);
        frame.setLocationRelativeTo(null);

        // Create a gradient background panel
        GradientBackgroundPanel gradientPanel = new GradientBackgroundPanel(Color.RED, Color.BLUE);

        // Add other components to the panel
        gradientPanel.setLayout(new BorderLayout());
        gradientPanel.add(new JButton("Button"), BorderLayout.CENTER);

        frame.getContentPane().add(gradientPanel);
        frame.setVisible(true);
    }
}
