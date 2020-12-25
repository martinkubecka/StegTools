package gui.tools;

import javax.swing.*;
import java.awt.*;

public class ToolsPanel extends JPanel {

    public ToolsPanel() {

        this.setBackground(new Color(72, 0, 0));
        this.setBorder(BorderFactory.createEmptyBorder(8, 8, 16, 16));
        this.setLayout(new GridLayout(3, 1));

        // HEADER LABEL
        JLabel toolsMenuLabel = new JLabel("Tools Menu", SwingConstants.CENTER);
        toolsMenuLabel.setVerticalAlignment(SwingConstants.CENTER);
        toolsMenuLabel.setForeground(new Color(244, 244, 244));
        toolsMenuLabel.setFont(new Font("Consolas", Font.PLAIN, 36));

        // PANEL FOR BUTTONS
        JPanel toolsButtonsPanel = new JPanel();
        toolsButtonsPanel.setBackground(new Color(72, 0, 0));
        toolsButtonsPanel.setLayout(new GridLayout(2, 2, 5, 5));

        JButton generalToolsButton = new JButton("General");
        generalToolsButton.setForeground(Color.BLACK);
        generalToolsButton.setFont(new Font("Source Code Pro", Font.BOLD, 14));
        generalToolsButton.setBackground(new Color(60, 63, 65));

        JButton textToolsButton = new JButton("Text");
        textToolsButton.setForeground(Color.BLACK);
        textToolsButton.setFont(new Font("Source Code Pro", Font.BOLD, 14));
        textToolsButton.setBackground(new Color(60, 63, 65));

        JButton pngToolsButton = new JButton("PNG");
        pngToolsButton.setForeground(Color.BLACK);
        pngToolsButton.setFont(new Font("Source Code Pro", Font.BOLD, 14));
        pngToolsButton.setBackground(new Color(60, 63, 65));

        JButton bmpToolsButton = new JButton("BMP");
        bmpToolsButton.setForeground(Color.BLACK);
        bmpToolsButton.setFont(new Font("Source Code Pro", Font.BOLD, 14));
        bmpToolsButton.setBackground(new Color(60, 63, 65));

        toolsButtonsPanel.add(generalToolsButton);
        toolsButtonsPanel.add(textToolsButton);
        toolsButtonsPanel.add(pngToolsButton);
        toolsButtonsPanel.add(bmpToolsButton);

        this.add(toolsMenuLabel, BorderLayout.NORTH);
        this.add(toolsButtonsPanel, BorderLayout.CENTER);
    }
}
