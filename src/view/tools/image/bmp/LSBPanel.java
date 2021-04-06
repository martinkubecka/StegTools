package view.tools.image.bmp;

import controller.AppController;
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

    private void setUpLayout() {

        this.setBackground(new Color(72, 0, 0));
        this.setBorder(BorderFactory.createEmptyBorder(32, 32, 32, 32));
        this.setLayout(new BorderLayout());

        centerPanel.setLayout(new GridLayout(5, 1, 8, 50));
        centerPanel.setBackground(new Color(72, 0, 0));
        centerPanel.setBorder(BorderFactory.createEmptyBorder(8, 150, 24, 150));
    }

    private void setUpPanel() {

        centerPanel.add(Box.createRigidArea(new Dimension(0, 60)));
        centerPanel.add(insertDataButton);
        centerPanel.add(extractDataButton);

        this.add(lsbLabel, BorderLayout.NORTH);
        this.add(centerPanel, BorderLayout.CENTER);
    }

    private void setUpListeners() {

        insertDataButton.addActionListener(e -> {

            // Pick carrier - bmp only
            File fileCarrier = baseController.getFileChooser().pickSingleFileChooser("bmp");

            if (fileCarrier != null) {

                // 0 = yes, 1 = no, 2 = cancel
                int chooseFiles = JOptionPane.showConfirmDialog(this,
                        "Would you like to choose some secret files?", "Carrier picked successfully!", JOptionPane.YES_NO_CANCEL_OPTION);

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

                        filesToHide = Arrays.asList(Objects.requireNonNull(baseController.getFileChooser().pickMultipleFilesFromFileChooser()));
                        // TODO compute if it is possible to insert selected files into a carrier

                        /* ---------------------------------------------------------------------------------------------------------- */
                        boolean isTextFilePresent = baseController.getLeastSignificantBit().isTextFilePresent(filesToHide);

                        // Text file is present
                        if (isTextFilePresent) {

                            // 0 = yes, 1 = no, 2 = cancel
                            int applyDictionary = JOptionPane.showConfirmDialog(this,
                                    "Would you like to perform a method of word shortening with synonym dictionary?", "Text file has been detected.", JOptionPane.YES_NO_CANCEL_OPTION);

                            if (applyDictionary == 0) {

                                System.out.println("Applying shortening ...");
                            }
                        }

                        /* ---------------------------------------------------------------------------------------------------------- */
                        boolean areMoreFilesSelected = baseController.getLeastSignificantBit().areMoreFilesSelected(filesToHide);

                        // Only one file was selected
                        if (!areMoreFilesSelected) {

                            // 0 = yes, 1 = no, 2 = cancel
                            int compressionOption = JOptionPane.showConfirmDialog(this,
                                    "Would you like to compress your secret file to a password protected zip file?", " Recommendation", JOptionPane.YES_NO_CANCEL_OPTION);

                            // Compression selected
                            if (compressionOption == 0) {

                                compressBeforeInsertion();
                            }
                            // No compression
                            else {

                                // Insertion
                                System.out.println("Inserting a file to a carrier ...");
                            }
                        }
                        // More files were selected
                        else {

                            JOptionPane.showMessageDialog(this, "More files were selected thus compression is required.", "Information", JOptionPane.PLAIN_MESSAGE);

                            compressBeforeInsertion();
                        }

                    } catch (Exception exception) {

                        LOGGER.log(Level.SEVERE, exception.toString(), exception);
                    }
                }
            }
        });

        extractDataButton.addActionListener(e -> {

            // TODO
        });
    }

    private void compressBeforeInsertion() {

        String password = "";
        int okCxl = 0;

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

                    //boolean result = baseController.getCompression().compressFilesToZip(filesToHide, password);
                    System.out.println("Compressing file / files ...");
                    System.out.println("Compression was successful!");
                    // TODO rework to return the compressed zip file
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
    }
}
