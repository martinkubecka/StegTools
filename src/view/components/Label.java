package view.components;

import javax.swing.*;
import java.awt.*;

public class Label extends JLabel {

    /**
     * Constructor.
     * <p>
     *
     * @param text                to be displayed
     * @param horizontalAlignment for label text
     * @param verticalAlignment   for label text
     * @param fontType            of label text
     * @param fontSize            of label text
     */
    public Label(String text, int horizontalAlignment,
                 int verticalAlignment, int fontType, int fontSize) {
        super(text, horizontalAlignment);

        this.setVerticalAlignment(verticalAlignment);
        this.setForeground(new Color(244, 244, 244));
        this.setFont(new Font("Consolas", fontType, fontSize));
    }
}
