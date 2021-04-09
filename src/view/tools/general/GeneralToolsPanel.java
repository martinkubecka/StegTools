package view.tools.general;

import com.drew.metadata.Metadata;
import controller.AppController;
import net.lingala.zip4j.ZipFile;
import view.components.Button;
import view.components.Label;

import javax.swing.*;
import java.awt.*;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;
import java.util.logging.Level;
import java.util.logging.Logger;

public class GeneralToolsPanel extends JPanel {

    private final static Logger LOGGER = Logger.getLogger(Logger.GLOBAL_LOGGER_NAME);

    private AppController baseController;
    private Label nameLabel;
    private JButton showMetadataButton;
    private Label compressionResultLabel;
    private JButton compressFilesButton;
    private JButton decompressFilesButton;
    private Label messageShorteningResultLabel;
    private JButton messageShorteningButton;

    public GeneralToolsPanel(AppController baseController) {

        this.baseController = baseController;

        nameLabel = new Label("General Tools", SwingConstants.CENTER, SwingConstants.CENTER, Font.PLAIN, 36);

        showMetadataButton = new Button("Show Metadata");

        compressionResultLabel = new Label("Compression Result", SwingConstants.CENTER, SwingConstants.TOP, Font.ITALIC, 15);
        compressionResultLabel.setVisible(false);

        compressFilesButton = new Button("Compress Files");
        decompressFilesButton = new Button("Decompress Files");
        messageShorteningButton = new Button("Message Shortening");

        messageShorteningResultLabel = new Label("", SwingConstants.CENTER, SwingConstants.TOP, Font.ITALIC, 15);
        messageShorteningResultLabel.setVisible(false);

        setUpLayout();
        setUpPanel();
        setUpListeners();
    }

    /**
     * Sets panel layout, background color and border.
     */
    private void setUpLayout() {

        this.setLayout(new GridLayout(7, 1, 8, 37));
        this.setBackground(new Color(72, 0, 0));
        this.setBorder(BorderFactory.createEmptyBorder(8, 150, 8, 150));
    }

    /**
     * Appends the specified components to the panel.
     */
    private void setUpPanel() {

        this.add(nameLabel);
        this.add(showMetadataButton);
        this.add(compressFilesButton);
        this.add(decompressFilesButton);
        this.add(compressionResultLabel);
        this.add(messageShorteningButton);
        this.add(messageShorteningResultLabel);
    }

    /**
     * Adds ActionListener to the panel buttons an carries out minimal logic.
     */
    private void setUpListeners() {

        showMetadataButton.addActionListener(e -> {

            File file = baseController.getFileChooser().pickSingleFileChooser("images");

            if (file != null) {

                // EXTRACT strings from METADATA object
                ArrayList<String> metadataParsed = baseController.getExtractMetadata().extractMetadata(file);

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

                    LOGGER.log(Level.SEVERE, ioException.toString(), ioException);
                }
            }
        });

        messageShorteningButton.addActionListener(e -> {

            File file = baseController.getFileChooser().pickSingleFileChooser("txt");

            if (file != null) {

                File modifiedFile = baseController.getDictionary().applyMessageShortening(file, 1);

                if (modifiedFile != null) {

                    messageShorteningResultLabel.setVisible(true);
                    messageShorteningResultLabel.setText("Successfully applied dictionary");
                }
            }
        });
    }
}
