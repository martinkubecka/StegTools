package view.tools.image.planes;

import controller.AppController;
import view.Panels;
import view.components.Button;
import view.components.Label;

import javax.swing.*;
import java.awt.*;

public class LSBPanel extends JPanel {

    private JPanel parentPanel;
    private AppController baseController;
    private CardLayout cardLayout;

    private JPanel centerPanel;

    private Label lsbLabel;
    private Button insertDataButton;
    private Button extractDataButton;

    public LSBPanel(Panels parentPanel, AppController baseController, CardLayout cardLayout) {

        this.parentPanel = parentPanel;
        this.baseController = baseController;
        this.cardLayout = cardLayout;

        centerPanel = new JPanel();

        lsbLabel = new Label("LSB Method Tools", SwingConstants.CENTER, SwingConstants.CENTER, Font.PLAIN, 22);

        insertDataButton = new Button("Insert");
        extractDataButton = new Button("Extract");

        setUpLayout();
        setUpPanel();
        setUpListeners();
    }

    private void setUpLayout() {

        this.setBackground(new Color(72, 0, 0));
        this.setBorder(BorderFactory.createEmptyBorder(32, 32, 32, 32));
        this.setLayout(new BorderLayout());

        centerPanel.setLayout(new GridLayout(5, 1, 8, 50));
        centerPanel.setBackground(new Color(72, 0, 0));
        centerPanel.setBorder(BorderFactory.createEmptyBorder(8, 150, 24, 150));
    }

    private void setUpPanel() {

        centerPanel.add(Box.createRigidArea(new Dimension(0, 60)));
        centerPanel.add(insertDataButton);
        centerPanel.add(extractDataButton);

        this.add(lsbLabel, BorderLayout.NORTH);
        this.add(centerPanel, BorderLayout.CENTER);
    }

    private void setUpListeners() {

        insertDataButton.addActionListener(e -> {

            // TODO

        });

        extractDataButton.addActionListener(e -> {

            // TODO
        });
    }
}
