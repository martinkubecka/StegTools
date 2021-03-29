package view.tools;

import view.components.Button;
import view.components.Label;

import javax.swing.*;
import java.awt.*;

public class ToolsPanel extends JPanel {

    private JPanel parentPanel;
    private CardLayout cardLayout;
    private Label toolsMenuLabel;
    private JPanel toolsButtonsPanel;
    private JButton generalToolsButton;
    private JButton imageToolsButton;

    public ToolsPanel(JPanel parentPanel, CardLayout cardLayout) {

        this.parentPanel = parentPanel;
        this.cardLayout = cardLayout;

        // HEADER LABEL
        toolsMenuLabel = new Label("Tools Menu", SwingConstants.CENTER, SwingConstants.CENTER, Font.PLAIN, 36);

        // PANEL FOR BUTTONS
        toolsButtonsPanel = new JPanel();
        toolsButtonsPanel.setBackground(new Color(72, 0, 0));
        toolsButtonsPanel.setLayout(new GridLayout(2, 2, 5, 5));

        generalToolsButton = new Button("General");
        imageToolsButton = new Button("Image");

        setUpLayout();
        setUpPanel();
        setUpListeners();
    }

    private void setUpLayout() {

        this.setBackground(new Color(72, 0, 0));
        this.setBorder(BorderFactory.createEmptyBorder(8, 8, 16, 16));
        this.setLayout(new GridLayout(3, 1));
    }

    private void setUpPanel() {

        toolsButtonsPanel.add(generalToolsButton);
        toolsButtonsPanel.add(imageToolsButton);
        toolsButtonsPanel.add(imageToolsButton);

        this.add(toolsMenuLabel, BorderLayout.NORTH);
        this.add(toolsButtonsPanel, BorderLayout.CENTER);
    }

    private void setUpListeners() {

        generalToolsButton.addActionListener(e -> {

            String generalTools = "General Tools Panel";
            cardLayout.show(parentPanel, generalTools);
        });

        imageToolsButton.addActionListener(e -> {

            String imageTools = "Image Tools Panel";
            cardLayout.show(parentPanel, imageTools);
        });
    }
}
