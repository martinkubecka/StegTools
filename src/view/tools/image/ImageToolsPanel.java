package view.tools.image;

import controller.AppController;
import view.Panels;

import javax.swing.*;
import java.awt.*;
import java.io.File;

public class ImageToolsPanel extends JPanel {

    private JPanel parentPanel;
    private AppController baseController;
    private CardLayout cardLayout;
    private JLabel nameLabelPNG;
    private JButton checkHeaderButton;
    private JLabel nameLabelBMP;
    private JButton bitPlanesButton;
    private JButton leastSignificantBitButton;

    public ImageToolsPanel(Panels parentPanel, AppController baseController, CardLayout cardLayout) {

        this.parentPanel = parentPanel;
        this.baseController = baseController;
        this.cardLayout = cardLayout;

        nameLabelPNG = new JLabel("PNG Tools", SwingConstants.CENTER);
        nameLabelPNG.setVerticalAlignment(SwingConstants.CENTER);
        nameLabelPNG.setForeground(new Color(244, 244, 244));
        nameLabelPNG.setFont(new Font("Consolas", Font.PLAIN, 36));

        checkHeaderButton = new JButton("Check header");
        checkHeaderButton.setForeground(Color.BLACK);
        checkHeaderButton.setFont(new Font("Source Code Pro", Font.BOLD, 14));
        checkHeaderButton.setBackground(new Color(60, 63, 65));
        checkHeaderButton.setFocusable(false);

        nameLabelBMP = new JLabel("BMP Tools", SwingConstants.CENTER);
        nameLabelBMP.setVerticalAlignment(SwingConstants.CENTER);
        nameLabelBMP.setForeground(new Color(244, 244, 244));
        nameLabelBMP.setFont(new Font("Consolas", Font.PLAIN, 36));

        bitPlanesButton = new JButton("Bit Planes Viewer");
        bitPlanesButton.setForeground(Color.BLACK);
        bitPlanesButton.setFont(new Font("Source Code Pro", Font.BOLD, 14));
        bitPlanesButton.setBackground(new Color(60, 63, 65));
        bitPlanesButton.setFocusable(false);

        leastSignificantBitButton = new JButton("Least Significant Bit");
        leastSignificantBitButton.setForeground(Color.BLACK);
        leastSignificantBitButton.setFont(new Font("Source Code Pro", Font.BOLD, 14));
        leastSignificantBitButton.setBackground(new Color(60, 63, 65));
        leastSignificantBitButton.setFocusable(false);

        setUpLayout();
        setUpPanel();
        setUpListeners();
    }

    private void setUpLayout() {

        this.setLayout(new GridLayout(5, 1, 8, 50));
        this.setBackground(new Color(72, 0, 0));
        this.setBorder(BorderFactory.createEmptyBorder(8, 150, 24, 150));
    }

    private void setUpPanel() {

        this.add(nameLabelPNG);
        this.add(checkHeaderButton);
        this.add(nameLabelBMP);
        this.add(bitPlanesButton);
        this.add(leastSignificantBitButton);
    }

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

            String bitPlane = "Bit Plane Panel";
            cardLayout.show(parentPanel, bitPlane);

        });

        leastSignificantBitButton.addActionListener(e -> {

            // TODO

        });
    }
}