package ResumeMaker;

import javax.swing.border.AbstractBorder;
import java.awt.*;

public class RoundedBorder extends AbstractBorder {
    private int radius;

    public RoundedBorder(int radius) {
        this.radius = radius;
    }

    @Override
    public void paintBorder(Component c, Graphics g, int x, int y, int width, int height) {
        super.paintBorder(c, g, x, y, width, height);
        Graphics2D g2d = (Graphics2D) g;
        g2d.setColor(getColor(c));
        g2d.drawRoundRect(x, y, width - 1, height - 1, radius, radius);
    }

    private Color getColor(Component c) {
        return c.isEnabled() ? Color.BLACK : Color.GRAY; // Change the color as needed
    }
}
