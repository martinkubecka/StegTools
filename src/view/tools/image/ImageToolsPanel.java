package view.tools.image;

import controller.AppController;
import view.Panels;
import view.tools.image.planes.BitPlaneWindow;
import view.components.Button;
import view.components.Label;

import javax.swing.*;
import java.awt.*;
import java.io.File;

public class ImageToolsPanel extends JPanel {

    private JPanel parentPanel;
    private AppController baseController;
    private CardLayout cardLayout;

    private Label nameLabelImageTools;
    private Label nameLabelPNG;
    private JButton checkHeaderButton;
    private Label nameLabelBMP;
    private JButton bitPlanesButton;
    private JButton appendedData;
    private JButton leastSignificantBitButton;

    public ImageToolsPanel(Panels parentPanel, AppController baseController, CardLayout cardLayout) {

        this.parentPanel = parentPanel;
        this.baseController = baseController;
        this.cardLayout = cardLayout;

        nameLabelImageTools = new Label("Image Tools", SwingConstants.CENTER, SwingConstants.CENTER, Font.PLAIN, 36);

        bitPlanesButton = new Button("Bit Planes Viewer");

        appendedData = new Button("Appended Data Extraction");

        nameLabelPNG = new Label("PNG Tools", SwingConstants.CENTER, SwingConstants.CENTER, Font.PLAIN, 30);

        checkHeaderButton = new Button("Check Header");

        nameLabelBMP = new Label("BMP Tools", SwingConstants.CENTER, SwingConstants.CENTER, Font.PLAIN, 30);

        leastSignificantBitButton = new Button("Least Significant Bit");

        setUpLayout();
        setUpPanel();
        setUpListeners();
    }

    /**
     * Sets panel layout, background color and border.
     */
    private void setUpLayout() {

        this.setLayout(new GridLayout(7, 1, 8, 10));
        this.setBackground(new Color(72, 0, 0));
        this.setBorder(BorderFactory.createEmptyBorder(8, 150, 24, 150));
    }

    /**
     * Appends the specified components to the panel.
     */
    private void setUpPanel() {

        this.add(nameLabelImageTools);
        this.add(bitPlanesButton);
        this.add(appendedData);
        this.add(nameLabelPNG);
        this.add(checkHeaderButton);
        this.add(nameLabelBMP);
        this.add(leastSignificantBitButton);
    }

    /**
     * Adds ActionListener to the panel buttons an carries out minimal logic.
     */
    private void setUpListeners() {

        checkHeaderButton.addActionListener(e -> {

            File file = baseController.getFileChooser().pickSingleFileChooser("png");

            if (file != null) {

                boolean resultChecker = baseController.getImageHeader().headerChecker(file);

                if (resultChecker) {

                    JOptionPane.showMessageDialog(
                            this,
                            "Your image header is correct.");

                } else {

                    // 0 = yes, 1 = no, 2 = cancel
                    int input = JOptionPane.showConfirmDialog(this,
                            "Perform automatic repair?", "Something wrong with the header!", JOptionPane.YES_NO_CANCEL_OPTION);

                    if (input == 0) {

                        boolean resultRepair = baseController.getImageHeader().headerRepair(file);

                        if (resultRepair) {
                            JOptionPane.showMessageDialog(this,
                                    "Successfully repaired your image!");
                        } else {
                            JOptionPane.showMessageDialog(this,
                                    "There was an error while repairing, sorry..",
                                    "Warning",
                                    JOptionPane.ERROR_MESSAGE);
                        }
                    }
                }
            }
        });

        bitPlanesButton.addActionListener(e -> {

            BitPlaneWindow bitPlaneWindow = new BitPlaneWindow("Bit Plane Viewer", baseController);
            bitPlaneWindow.setVisible(true);
        });

        appendedData.addActionListener(e -> {

            File file = baseController.getFileChooser().pickSingleFileChooser("png&bmp");

            if (file != null) {

                String extractedMessage = baseController.getAppendedData().extractAppendedData(file);

                ExtractedDataWindow extractedDataWindow = new ExtractedDataWindow("Extracted Message", baseController, extractedMessage);
                extractedDataWindow.setVisible(true);
            }
        });

        leastSignificantBitButton.addActionListener(e -> {

            String lsbTools = "LSB Panel";
            cardLayout.show(parentPanel, lsbTools);
        });
    }
}
