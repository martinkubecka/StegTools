package view.tools.image.bmp;

import controller.AppController;
import model.explorer.FileChooser;
import view.Panels;
import view.components.Button;
import view.components.Label;

import javax.swing.*;
import java.awt.*;
import java.io.File;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;
import java.util.logging.Level;
import java.util.logging.Logger;

public class LSBPanel extends JPanel {

    private final static Logger LOGGER = Logger.getLogger(Logger.GLOBAL_LOGGER_NAME);

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

    /**
     * Sets panel layout, background color and border.
     */
    private void setUpLayout() {

        this.setBackground(new Color(72, 0, 0));
        this.setBorder(BorderFactory.createEmptyBorder(32, 32, 32, 32));
        this.setLayout(new BorderLayout());

        centerPanel.setLayout(new GridLayout(5, 1, 8, 50));
        centerPanel.setBackground(new Color(72, 0, 0));
        centerPanel.setBorder(BorderFactory.createEmptyBorder(8, 150, 24, 150));
    }

    /**
     * Appends the specified components to the panel.
     */
    private void setUpPanel() {

        centerPanel.add(Box.createRigidArea(new Dimension(0, 60)));
        centerPanel.add(insertDataButton);
        centerPanel.add(extractDataButton);

        this.add(lsbLabel, BorderLayout.NORTH);
        this.add(centerPanel, BorderLayout.CENTER);
    }

    /**
     * Adds ActionListener to the panel buttons an carries out minimal logic.
     */
    private void setUpListeners() {

        insertDataButton.addActionListener(e -> {

            // Pick carrier - bmp only
            File fileCarrier = baseController.getFileChooser().pickSingleFileChooser("bmp");
            boolean successfulInsertion = false;

            if (fileCarrier != null) {

                boolean isCarrierSupported = baseController.getLeastSignificantBit().isCarrierSupported(fileCarrier);

                if (!isCarrierSupported) {

                    JOptionPane.showMessageDialog(
                            this,
                            "Chosen image file has less than 8bits per pixel.",
                            "Error",
                            JOptionPane.WARNING_MESSAGE);
                } else {

                    // 0 = yes, 1 = no, 2 = cancel
                    int chooseFiles = JOptionPane.showConfirmDialog(this,
                            "Would you like to choose some secret files?", "Carrier picked successfully", JOptionPane.YES_NO_CANCEL_OPTION);

                    if (chooseFiles == 0) {

                        // 1. Pick some secret files
                        //
                        // 2. Check if a text files are present
                        //      2.1 Yes = give an option to apply the synonym dictionary
                        //          2.1.1 = Yes = apply shortening
                        //          2.1.2 = No = move on
                        //
                        //      2.2 No = move on
                        //
                        // 3. Check if one or more files were selected
                        //      3.1 One = Give an option to compress
                        //          3.1.1 Yes = ask for a password and compress
                        //          3.1.2 No = move on
                        //
                        //      3.2 More = Inform about compression, ask for password and compress
                        //
                        // 4. Insert a file to carrier

                        List<File> filesToHide = null;
                        try {

                            // throws false positive error
                            filesToHide = Arrays.asList(Objects.requireNonNull(baseController.getFileChooser().pickMultipleFilesFromFileChooser()));

                            boolean isEnoughSpace = baseController.getLeastSignificantBit().isEnoughSpace(fileCarrier, filesToHide);

                            if (!isEnoughSpace) {

                                JOptionPane.showMessageDialog(
                                        this,
                                        "Chosen data to hide are larger than carrier capacity.",
                                        "Error",
                                        JOptionPane.WARNING_MESSAGE);
                            } else {

                                /* ---------------------------------------------------------------------------------------------------------- */
                                boolean isTextFilePresent = baseController.getLeastSignificantBit().isTextFilePresent(filesToHide);

                                // Text file is present
                                if (isTextFilePresent) {

                                    // 0 = yes, 1 = no, 2 = cancel
                                    int applyDictionary = JOptionPane.showConfirmDialog(this,
                                            "Would you like to perform a method of word shortening with synonym dictionary?", "Text file has been detected.", JOptionPane.YES_NO_CANCEL_OPTION);

                                    if (applyDictionary == 0) {

                                        System.out.println("Applying shortening ...");
                                        filesToHide = baseController.getLeastSignificantBit().textFileShortening(filesToHide);
                                    }
                                }

                                /* ---------------------------------------------------------------------------------------------------------- */
                                boolean areMoreFilesSelected = baseController.getLeastSignificantBit().areMoreFilesSelected(filesToHide);

                                // Only one file was selected
                                if (!areMoreFilesSelected) {

                                    if (!FileChooser.getExtension(filesToHide.get(0)).equals("zip")) {

                                        // 0 = yes, 1 = no, 2 = cancel
                                        int compressionOption = JOptionPane.showConfirmDialog(this,
                                                "Would you like to compress your secret file to a password protected zip file?", " Recommendation", JOptionPane.YES_NO_CANCEL_OPTION);

                                        // Compression selected
                                        if (compressionOption == 0) {

                                            File zipFile = compressBeforeInsertion(filesToHide);

                                            if (zipFile != null) {

                                                baseController.getLeastSignificantBit().insertLSB(fileCarrier, zipFile);
                                                successfulInsertion = true;
                                            }
                                        }
                                        // No compression
                                        else {

                                            if (FileChooser.getExtension(filesToHide.get(0)).length() == 3) {

                                                baseController.getLeastSignificantBit().insertLSB(fileCarrier, filesToHide.get(0));
                                                successfulInsertion = true;

                                            } else {

                                                JOptionPane.showMessageDialog(
                                                        this,
                                                        "Only files with 3 character extension are allowed to be inserted.",
                                                        "Warning",
                                                        JOptionPane.WARNING_MESSAGE);
                                            }
                                        }
                                    } else {

                                        baseController.getLeastSignificantBit().insertLSB(fileCarrier, filesToHide.get(0));
                                        successfulInsertion = true;
                                    }
                                }
                                // More files were selected
                                else {

                                    JOptionPane.showMessageDialog(this, "More files were selected thus compression is required.", "Information", JOptionPane.PLAIN_MESSAGE);

                                    File zipFile = compressBeforeInsertion(filesToHide);

                                    if (zipFile != null) {

                                        baseController.getLeastSignificantBit().insertLSB(fileCarrier, zipFile);
                                        successfulInsertion = true;
                                    }
                                }
                            }
                        } catch (Exception exception) {

                            LOGGER.log(Level.SEVERE, exception.toString(), exception);
                        }
                    }

                    if (successfulInsertion) {
                        JOptionPane.showMessageDialog(
                                this,
                                "Successfully inserted data.",
                                "Information",
                                JOptionPane.PLAIN_MESSAGE);
                    }
                }
            }
        });

        extractDataButton.addActionListener(e -> {

            // Pick carrier - bmp only
            File fileCarrier = baseController.getFileChooser().pickSingleFileChooser("bmp");

            if (fileCarrier != null) {

                int result = baseController.getLeastSignificantBit().extractLSB(fileCarrier);

                if (result == 1) {

                    JOptionPane.showMessageDialog(
                            this,
                            "Successfully extracted hidden data.",
                            "Information",
                            JOptionPane.PLAIN_MESSAGE);

                } else {

                    JOptionPane.showMessageDialog(
                            this,
                            "There was an error while extracting secret data.",
                            "Error",
                            JOptionPane.WARNING_MESSAGE);
                }
            }
        });
    }

    private File compressBeforeInsertion(List<File> filesToHide) {

        String password = "";
        int okCxl = 0;
        File zipFile = null;

        while ((password.isEmpty()) && (okCxl == 0)) {

            JPasswordField passwordField = new JPasswordField();
            okCxl = JOptionPane.showConfirmDialog(
                    this,
                    passwordField,
                    "Enter Password",
                    JOptionPane.OK_CANCEL_OPTION,
                    JOptionPane.PLAIN_MESSAGE);

            if (okCxl == JOptionPane.OK_OPTION) {

                password = new String(passwordField.getPassword());

                if (!password.isEmpty()) {

                    // compress
                    System.out.println("Compressing file / files ...");
                    boolean result = baseController.getCompression().compressFilesToZip(filesToHide, password);

                    if (result) {

                        System.out.println("Compression was successful!");

                        // pick a zip file from a file chooser

                        JOptionPane.showMessageDialog(this, "Please choose a zip file we compressed for you.", "Successful compression", JOptionPane.PLAIN_MESSAGE);

                        zipFile = baseController.getFileChooser().pickSingleFileChooser("zip");

                        return zipFile;

                    } else {

                        JOptionPane.showMessageDialog(
                                this,
                                "An error was encountered while compressing.",
                                "Error",
                                JOptionPane.WARNING_MESSAGE);
                    }

                    System.out.println("Inserting a zip file to a carrier ...");
                    //break;

                } else {

                    JOptionPane.showMessageDialog(
                            this,
                            "No password entered",
                            "Warning",
                            JOptionPane.WARNING_MESSAGE);
                }
            }
        }

        return null;
    }
}
