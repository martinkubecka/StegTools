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
                int input = JOptionPane.showConfirmDialog(this,
                        "Would you like to choose some secret files?", "Carrier picked successfully!", JOptionPane.YES_NO_CANCEL_OPTION);

                if (input == 0) {

//                    // Pick secret files
                    List<File> filesToHide = null;
                    try {

                        filesToHide = Arrays.asList(Objects.requireNonNull(baseController.getFileChooser().pickMultipleFilesFromFileChooser()));

                        boolean isTextFilePresent = baseController.getLeastSignificantBit().isTextFilePresent(filesToHide);

                        if (isTextFilePresent){
                            System.out.println("What now?");
                        } else {
                            System.out.println("Uff..");
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
}
