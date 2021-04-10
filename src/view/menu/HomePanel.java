package view.menu;

import view.components.Label;

import javax.swing.*;
import java.awt.*;
import java.io.FileReader;
import java.net.URL;
import java.util.logging.Level;
import java.util.logging.Logger;

public class HomePanel extends JPanel {

    private Label creatorLabel;
    private JLabel logoCanvas;

    public HomePanel() {

        logoCanvas = new JLabel("", SwingConstants.CENTER);

        creatorLabel = new Label("Martin Kubecka, 2020/2021", SwingConstants.LEFT, SwingConstants.BOTTOM, Font.PLAIN, 12);

        setUpLayout();
        setUpPanel();
    }

    /**
     * Sets panel layout, background color and border.
     */
    private void setUpLayout() {

        this.setLayout(new BorderLayout());
        this.setBackground(new Color(72, 0, 0));
        this.setBorder(BorderFactory.createEmptyBorder(8, 16, 8, 16));
    }

    /**
     * Appends the specified components to the panel.
     */
    private void setUpPanel() {


        URL imageURL = HomePanel.class.getResource("/resources/images/logo.png");
        logoCanvas.setIcon(new ImageIcon(imageURL));

        this.add(logoCanvas, BorderLayout.CENTER);
        this.add(creatorLabel, BorderLayout.SOUTH);
    }
}
