import org.w3c.dom.css.RGBColor;

import javax.swing.*;
import java.awt.*;

public class PrettierButton extends JButton {
    PrettierButton(String name){
        super(name);
    }
    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);

        g.setColor(new Color(0, 255, 255));
        g.fillRect(0,0, getWidth(), getHeight());

    }
}
