package gui.tools.general;

import com.drew.metadata.Metadata;
import net.lingala.zip4j.ZipFile;
import process.Compression;
import process.ExtractMetadata;
import process.explorer.FileChooser;

import javax.swing.*;
import java.awt.*;
import java.io.File;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;

public class GeneralToolsPanel extends JPanel {

    public GeneralToolsPanel() {

        this.setLayout(new GridLayout(5, 1, 8, 70));
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

                Metadata metadata = ExtractMetadata.extractMetadata(file);
                MetadataWindow metadataWindow = new MetadataWindow("Metadata", metadata);
                metadataWindow.setVisible(true);
            }
        });

        JLabel compressionResultLabel = new JLabel("Compression Result", SwingConstants.CENTER);
        compressionResultLabel.setVerticalAlignment(SwingConstants.TOP);
        compressionResultLabel.setForeground(new Color(244, 244, 244));
        compressionResultLabel.setFont(new Font("Consolas", Font.ITALIC, 15));
        compressionResultLabel.setVisible(false);

        JButton compressFilesButton = new JButton("Compress Files");
        compressFilesButton.setForeground(Color.BLACK);
        compressFilesButton.setFont(new Font("Source Code Pro", Font.BOLD, 14));
        compressFilesButton.setBackground(new Color(60, 63, 65));
        compressFilesButton.setFocusable(false);
        compressFilesButton.addActionListener(e -> {

            List<File> filesToCompress = null;

            try {
                filesToCompress = Arrays.asList(Objects.requireNonNull(FileChooser.pickMultipleFilesFromFileChooser()));

            } catch (Exception exception) {

                //System.out.println(exception);
//                JOptionPane.showMessageDialog(
//                        this,
//                        "No files selected",
//                        "Warning",
//                        JOptionPane.WARNING_MESSAGE);
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

                        boolean result = Compression.compressFilesToZip(filesToCompress, password);
                        if (result) {
                            compressionResultLabel.setVisible(true);
                            compressionResultLabel.setText("Files successfully compressed.");
                        }

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

        JButton decompressFilesButton = new JButton("Decompress Files");
        decompressFilesButton.setForeground(Color.BLACK);
        decompressFilesButton.setFont(new Font("Source Code Pro", Font.BOLD, 14));
        decompressFilesButton.setBackground(new Color(60, 63, 65));
        decompressFilesButton.setFocusable(false);
        decompressFilesButton.addActionListener(e -> {

            // TODO Refactor this implementation !
            File file = FileChooser.pickZipFromFileChooser();

            if (file != null) {

                try {

                    // ZipFile zipFile = new ZipFile(file);

                    // Zip is encrypted with password
                    if (new ZipFile(file).isEncrypted()) {

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

                                boolean result = Compression.deCompressEncryptedZip(file, password);
                                if (result) {
                                    compressionResultLabel.setVisible(true);
                                    compressionResultLabel.setText("Files successfully decompressed.");
                                }

                            } else {

                                JOptionPane.showMessageDialog(
                                        this,
                                        "No password entered",
                                        "Warning",
                                        JOptionPane.WARNING_MESSAGE);
                            }
                        }
                    }

                    // Zip without password
                    else {

                        boolean result = Compression.deCompressUnencryptedZip(file);
                        if (result) {
                            compressionResultLabel.setVisible(true);
                            compressionResultLabel.setText("Files successfully decompressed.");
                        }
                    }
                } catch (IOException ioException) {
                    ioException.printStackTrace();
                }
            }
        });

        this.add(nameLabel);
        this.add(showMetadataButton);
        this.add(compressFilesButton);
        this.add(decompressFilesButton);
        this.add(compressionResultLabel);
    }
}
