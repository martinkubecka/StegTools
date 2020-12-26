package gui.tools.general;

import com.drew.metadata.Metadata;
import process.Compression;
import process.ExtractMetadata;
import process.FileChooser;

import javax.swing.*;
import java.awt.*;
import java.io.File;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;

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
        showMetadataButton.setFocusable(false);
        showMetadataButton.addActionListener(e -> {

            File file = FileChooser.pickImageFromFileChooser();
            if (file != null) {
                // TODO Show Metadata in DialogWindow
                Metadata metadata = ExtractMetadata.extractMetadata(file);
                MetadataWindow metadataWindow = new MetadataWindow("Metadata", metadata);
                metadataWindow.setVisible(true);
            }

        });

        JButton compressFilesButton = new JButton("Compress Files");
        compressFilesButton.setForeground(Color.BLACK);
        compressFilesButton.setFont(new Font("Source Code Pro", Font.BOLD, 14));
        compressFilesButton.setBackground(new Color(60, 63, 65));
        compressFilesButton.setFocusable(false);
        compressFilesButton.addActionListener(e -> {

            List<File> filesToCompress = null;

            try {
                filesToCompress = Arrays.asList(Objects.requireNonNull(FileChooser.pickMultipleFromFileChooser()));
            } catch (Exception exception) {

                //System.out.println(exception);
                JOptionPane.showMessageDialog(
                        this,
                        "No files selected",
                        "Warning",
                        JOptionPane.WARNING_MESSAGE);
            }

            if (filesToCompress != null) {

                JPasswordField passwordField = new JPasswordField();
                int okCxl = JOptionPane.showConfirmDialog(
                        this,
                        passwordField,
                        "Enter Password",
                        JOptionPane.OK_CANCEL_OPTION,
                        JOptionPane.PLAIN_MESSAGE);

                if (okCxl == JOptionPane.OK_OPTION) {

                    String password = new String(passwordField.getPassword());
                    //System.err.println("You entered: " + password);

                    if (!password.isEmpty()) {

                        Compression.compressFilesToZip(filesToCompress, password);
                    } else {

                        JOptionPane.showMessageDialog(
                                this,
                                "No password entered",
                                "Warning",
                                JOptionPane.WARNING_MESSAGE);
                    }
                }
            }
        });

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
