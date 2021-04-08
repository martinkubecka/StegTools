package view.components;

import javax.swing.*;
import java.awt.*;

public class Button extends JButton {

    /**
     * Constructor.
     * <p>
     *
     * @param label for the button
     */
    public Button(String label) {
        super(label);

        this.setForeground(Color.BLACK);
        this.setFont(new Font("Source Code Pro", Font.BOLD, 14));
        this.setBackground(new Color(60, 63, 65));
        this.setFocusable(false);
    }
}
