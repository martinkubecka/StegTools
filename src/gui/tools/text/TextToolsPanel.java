package gui.tools.text;

import javax.swing.*;
import java.awt.*;

public class TextToolsPanel extends JPanel {

    public TextToolsPanel(JPanel contentPane, CardLayout cardLayout) {

        this.setLayout(new GridLayout(6, 1, 8, 35));
        this.setBackground(new Color(72, 0, 0));
        this.setBorder(BorderFactory.createEmptyBorder(8, 120, 35, 120));

        JLabel nameLabel = new JLabel("Text Tools", SwingConstants.CENTER);
        nameLabel.setVerticalAlignment(SwingConstants.CENTER);
        nameLabel.setForeground(new Color(244, 244, 244));
        nameLabel.setFont(new Font("Consolas", Font.PLAIN, 36));

        JButton messageShorteningButton = new JButton("Message Shortening");
        messageShorteningButton.setForeground(Color.BLACK);
        messageShorteningButton.setFont(new Font("Source Code Pro", Font.BOLD, 14));
        messageShorteningButton.setBackground(new Color(60, 63, 65));
        messageShorteningButton.setFocusable(false);
        messageShorteningButton.addActionListener(e -> {
            // TODO Message Shortening Button Action
        });

        JLabel compressionResultLabel = new JLabel("Message Shortening Result", SwingConstants.CENTER);
        compressionResultLabel.setVerticalAlignment(SwingConstants.TOP);
        compressionResultLabel.setForeground(new Color(244, 244, 244));
        compressionResultLabel.setFont(new Font("Consolas", Font.PLAIN, 15));
        compressionResultLabel.setVisible(false);

        JLabel zeroWidthLabel = new JLabel("Zero-Width Characters", SwingConstants.CENTER);
        zeroWidthLabel.setVerticalAlignment(SwingConstants.CENTER);
        zeroWidthLabel.setForeground(new Color(244, 244, 244));
        zeroWidthLabel.setFont(new Font("Consolas", Font.PLAIN, 28));

        JButton multipleToolsZeroWidthButton = new JButton("Detect-Extract-Remove");
        multipleToolsZeroWidthButton.setForeground(Color.BLACK);
        multipleToolsZeroWidthButton.setFont(new Font("Source Code Pro", Font.BOLD, 14));
        multipleToolsZeroWidthButton.setBackground(new Color(60, 63, 65));
        multipleToolsZeroWidthButton.setFocusable(false);
        multipleToolsZeroWidthButton.addActionListener(e -> {

            String textManipulationPanel = "Text Manipulation Panel";
            cardLayout.show(contentPane, textManipulationPanel);
        });

        JButton hideMessageButton = new JButton("Hide Message");
        hideMessageButton.setForeground(Color.BLACK);
        hideMessageButton.setFont(new Font("Source Code Pro", Font.BOLD, 14));
        hideMessageButton.setBackground(new Color(60, 63, 65));
        hideMessageButton.setFocusable(false);
        hideMessageButton.addActionListener(e -> {

            String hideMessagePanel = "Hide Message Panel";
            cardLayout.show(contentPane, hideMessagePanel);

        });

        this.add(nameLabel);
        this.add(messageShorteningButton);
        this.add(compressionResultLabel);
        this.add(zeroWidthLabel);
        this.add(multipleToolsZeroWidthButton);
        this.add(hideMessageButton);
    }
}
