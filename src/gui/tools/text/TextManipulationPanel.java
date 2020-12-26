package gui.tools.text;

import javax.swing.*;
import java.awt.*;

public class TextManipulationPanel extends JPanel {

    public TextManipulationPanel() {

        this.setLayout(new GridLayout(4, 1, 2, 2));
        this.setBackground(new Color(72, 0, 0));
        this.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        JTextArea textAreaTextManipulation = new JTextArea();
        textAreaTextManipulation.setLineWrap(true);
        textAreaTextManipulation.setWrapStyleWord(true);
        textAreaTextManipulation.setBorder(BorderFactory.createBevelBorder(1));
        textAreaTextManipulation.setForeground(Color.BLACK);
        textAreaTextManipulation.setFont(new Font("Source Code Pro", Font.PLAIN, 14));

        JScrollPane scrollPaneTextMan = new JScrollPane(textAreaTextManipulation);
        scrollPaneTextMan.setPreferredSize(new Dimension(500, 300));
        scrollPaneTextMan.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);

        // PANEL FOR BUTTONS
        JPanel buttonsPanel = new JPanel();
        buttonsPanel.setLayout(new FlowLayout(FlowLayout.CENTER, 10, 2));
        buttonsPanel.setBackground(new Color(72, 0, 0));

        JButton uploadButton = new JButton("Upload message");
        uploadButton.setForeground(Color.BLACK);
        uploadButton.setFont(new Font("Source Code Pro", Font.BOLD, 14));
        uploadButton.setBackground(new Color(60, 63, 65));
        uploadButton.setFocusable(false);
        uploadButton.addActionListener(e -> {
            // TODO Upload Button Action
        });

        JButton detectButton = new JButton("Detect");
        detectButton.setForeground(Color.BLACK);
        detectButton.setFont(new Font("Source Code Pro", Font.BOLD, 14));
        detectButton.setBackground(new Color(60, 63, 65));
        detectButton.setFocusable(false);
        detectButton.addActionListener(e -> {
            // TODO Detect Button Action
        });

        JButton extractButton = new JButton("Extract");
        extractButton.setForeground(Color.BLACK);
        extractButton.setFont(new Font("Source Code Pro", Font.BOLD, 14));
        extractButton.setBackground(new Color(60, 63, 65));
        extractButton.setFocusable(false);
        extractButton.addActionListener(e -> {
            // TODO Extract Button Action
        });

        JButton removeButton = new JButton("Remove");
        removeButton.setForeground(Color.BLACK);
        removeButton.setFont(new Font("Source Code Pro", Font.BOLD, 14));
        removeButton.setBackground(new Color(60, 63, 65));
        removeButton.setFocusable(false);
        removeButton.addActionListener(e -> {
            // TODO Remove Button Action
        });

        buttonsPanel.add(uploadButton);
        buttonsPanel.add(detectButton);
        buttonsPanel.add(extractButton);
        buttonsPanel.add(removeButton);

        // MESSAGE EXTRACTION
        JLabel extractedLabel = new JLabel("Extracted Message", SwingConstants.LEFT);
        extractedLabel.setVerticalAlignment(SwingConstants.BOTTOM);
        extractedLabel.setForeground(new Color(244, 244, 244));
        extractedLabel.setFont(new Font("Consolas", Font.PLAIN, 22));

        JTextArea textAreaExtractedMessage = new JTextArea();
        textAreaExtractedMessage.setLineWrap(true);
        textAreaExtractedMessage.setWrapStyleWord(true);
        textAreaExtractedMessage.setBorder(BorderFactory.createBevelBorder(1));
        textAreaExtractedMessage.setForeground(Color.BLACK);
        textAreaExtractedMessage.setFont(new Font("Source Code Pro", Font.PLAIN, 14));
        textAreaExtractedMessage.setEditable(false);

        JScrollPane scrollPaneExtracted = new JScrollPane(textAreaExtractedMessage);
        scrollPaneExtracted.setPreferredSize(new Dimension(500, 300));
        scrollPaneExtracted.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);

        this.add(scrollPaneTextMan);
        this.add(buttonsPanel);
        this.add(extractedLabel);
        this.add(scrollPaneExtracted);
    }
}
