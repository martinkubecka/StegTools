package view.tools.general;

import com.drew.metadata.Metadata;
import controller.AppController;
import net.lingala.zip4j.ZipFile;

import javax.swing.*;
import java.awt.*;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;

public class GeneralToolsPanel extends JPanel {

    private AppController baseController;
    private JLabel nameLabel;
    private JButton showMetadataButton;
    private JLabel compressionResultLabel;
    private JButton compressFilesButton;
    private JButton decompressFilesButton;
    private JLabel messageShorteningResultLabel;
    private JButton messageShorteningButton;

    public GeneralToolsPanel(AppController baseController) {

        this.baseController = baseController;

        nameLabel = new JLabel("General Tools", SwingConstants.CENTER);
        nameLabel.setVerticalAlignment(SwingConstants.CENTER);
        nameLabel.setForeground(new Color(244, 244, 244));
        nameLabel.setFont(new Font("Consolas", Font.PLAIN, 36));

        showMetadataButton = new JButton("Show Metadata");
        showMetadataButton.setForeground(Color.BLACK);
        showMetadataButton.setFont(new Font("Source Code Pro", Font.BOLD, 14));
        showMetadataButton.setBackground(new Color(60, 63, 65));
        showMetadataButton.setFocusable(false);

        compressionResultLabel = new JLabel("Compression Result", SwingConstants.CENTER);
        compressionResultLabel.setVerticalAlignment(SwingConstants.TOP);
        compressionResultLabel.setForeground(new Color(244, 244, 244));
        compressionResultLabel.setFont(new Font("Consolas", Font.ITALIC, 15));
        compressionResultLabel.setVisible(false);

        compressFilesButton = new JButton("Compress Files");
        compressFilesButton.setForeground(Color.BLACK);
        compressFilesButton.setFont(new Font("Source Code Pro", Font.BOLD, 14));
        compressFilesButton.setBackground(new Color(60, 63, 65));
        compressFilesButton.setFocusable(false);

        decompressFilesButton = new JButton("Decompress Files");
        decompressFilesButton.setForeground(Color.BLACK);
        decompressFilesButton.setFont(new Font("Source Code Pro", Font.BOLD, 14));
        decompressFilesButton.setBackground(new Color(60, 63, 65));
        decompressFilesButton.setFocusable(false);

        messageShorteningButton = new JButton("Message Shortening");
        messageShorteningButton.setForeground(Color.BLACK);
        messageShorteningButton.setFont(new Font("Source Code Pro", Font.BOLD, 14));
        messageShorteningButton.setBackground(new Color(60, 63, 65));
        messageShorteningButton.setFocusable(false);

        messageShorteningResultLabel = new JLabel("", SwingConstants.CENTER);
        messageShorteningResultLabel.setVerticalAlignment(SwingConstants.TOP);
        messageShorteningResultLabel.setForeground(new Color(244, 244, 244));
        messageShorteningResultLabel.setFont(new Font("Consolas", Font.ITALIC, 15));
        messageShorteningResultLabel.setVisible(false);

        setUpLayout();
        setUpPanel();
        setUpListeners();
    }

    private void setUpLayout() {

        this.setLayout(new GridLayout(7, 1, 8, 37));
        this.setBackground(new Color(72, 0, 0));
        this.setBorder(BorderFactory.createEmptyBorder(8, 150, 8, 150));
    }

    private void setUpPanel() {

        this.add(nameLabel);
        this.add(showMetadataButton);
        this.add(compressFilesButton);
        this.add(decompressFilesButton);
        this.add(compressionResultLabel);
        this.add(messageShorteningButton);
        this.add(messageShorteningResultLabel);
    }

    private void setUpListeners() {

        showMetadataButton.addActionListener(e -> {

            File file = baseController.getFileChooser().pickSingleFileChooser("images");

            if (file != null) {

                Metadata metadata = baseController.getExtractMetadata().extractMetadata(file);
                // EXTRACT strings from METADATA object
                ArrayList<String> metadataParsed = baseController.getExtractMetadata().parseMetadata(metadata);

                MetadataWindow metadataWindow = new MetadataWindow("Metadata", metadataParsed);
                metadataWindow.setVisible(true);
            }
        });

        compressFilesButton.addActionListener(e -> {

            List<File> filesToCompress = null;

            try {
                filesToCompress = Arrays.asList(Objects.requireNonNull(baseController.getFileChooser().pickMultipleFilesFromFileChooser()));

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

                        boolean result = baseController.getCompression().compressFilesToZip(filesToCompress, password);
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

        decompressFilesButton.addActionListener(e -> {

            File file = baseController.getFileChooser().pickSingleFileChooser("zip");

            if (file != null) {

                // Zip encrypted with a password
                try {
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

                            if (!password.isEmpty()) {

                                boolean result = baseController.getCompression().deCompressEncryptedZip(file, password);
                                if (result) {
                                    compressionResultLabel.setVisible(true);
                                    compressionResultLabel.setText("Files successfully decompressed.");
                                } else {
                                    JOptionPane.showMessageDialog(this,
                                            "Wrong password!",
                                            "Warning",
                                            JOptionPane.ERROR_MESSAGE);
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

                    // Zip without a password
                    else {

                        boolean result = baseController.getCompression().deCompressUnencryptedZip(file);
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

        messageShorteningButton.addActionListener(e -> {

            boolean result = baseController.getDictionary().applyMessageShortening();
            messageShorteningResultLabel.setVisible(true);
            if (result) {
                messageShorteningResultLabel.setText("Successfully applied dictionary");
            }

        });
    }
}
