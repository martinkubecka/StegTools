package view.components;

import javax.swing.*;
import java.awt.*;

public class Label extends JLabel {

    public Label(String text, int horizontalAlignment,
                 int verticalAlignment, int fontType, int fontSize) {
        super(text, horizontalAlignment);

        this.setVerticalAlignment(verticalAlignment);
        this.setForeground(new Color(244, 244, 244));
        this.setFont(new Font("Consolas", fontType, fontSize));
    }
}
