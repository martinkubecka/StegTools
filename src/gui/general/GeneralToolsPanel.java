package gui.general;

import javax.swing.*;
import java.awt.*;

public class GeneralToolsPanel extends JPanel {

    public GeneralToolsPanel() {

        this.setLayout(new GridLayout(4, 1, 8, 100));
        this.setBackground(new Color(72, 0, 0));
        this.setBorder(BorderFactory.createEmptyBorder(8, 150, 8, 150));

        JLabel nameLabel = new JLabel("General Tools", SwingConstants.CENTER);
        nameLabel.setVerticalAlignment(SwingConstants.CENTER);
        nameLabel.setForeground(new Color(244, 244, 244));
        nameLabel.setFont(new Font("Consolas", Font.PLAIN, 36));

        JButton showMetadataButton = new JButton("Show Metadata");
        showMetadataButton.setForeground(Color.BLACK);
        showMetadataButton.setFont(new Font("Source Code Pro", Font.BOLD, 14));
        showMetadataButton.setBackground(new Color(60, 63, 65));

        JButton compressFilesButton = new JButton("Compress Files");
        compressFilesButton.setForeground(Color.BLACK);
        compressFilesButton.setFont(new Font("Source Code Pro", Font.BOLD, 14));
        compressFilesButton.setBackground(new Color(60, 63, 65));

        JLabel compressionResultLabel = new JLabel("Compression Result", SwingConstants.CENTER);
        compressionResultLabel.setVerticalAlignment(SwingConstants.TOP);
        compressionResultLabel.setForeground(new Color(244, 244, 244));
        compressionResultLabel.setFont(new Font("Consolas", Font.PLAIN, 15));
        compressionResultLabel.setVisible(false);

        this.add(nameLabel);
        this.add(showMetadataButton);
        this.add(compressFilesButton);
        this.add(compressionResultLabel);
    }
}
