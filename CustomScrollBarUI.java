package ResumeMaker;
import javax.swing.plaf.basic.BasicScrollBarUI;
import javax.swing.*;
import java.awt.*;

public class CustomScrollBarUI extends BasicScrollBarUI {
    @Override
    protected void configureScrollBarColors() {
        this.thumbColor = new Color(30, 144, 255); // Dodger Blue
        this.trackColor = new Color(0, 0, 0, 0); // Transparent
    }
}