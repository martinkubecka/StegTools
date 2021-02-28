package view.tools.image;

import controller.AppController;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class BitPlanePanel extends JPanel {

    private AppController baseController;
    private JLabel currentPlain;
    private JLabel imageCanvas;
    private JPanel footerPanel;
    private JButton uploadButton;
    private JButton forwardButton;
    private JButton backwardButton;
    private BufferedImage image;

    public BitPlanePanel(AppController baseController) {

        this.baseController = baseController;

        // HEADER
        currentPlain = new JLabel("", SwingConstants.CENTER);
        currentPlain.setVerticalAlignment(SwingConstants.TOP);
        currentPlain.setForeground(new Color(244, 244, 244));
        currentPlain.setFont(new Font("Consolas", Font.PLAIN, 15));

        // CANVAS
        imageCanvas = new JLabel("", SwingConstants.CENTER);

        // FOOTER
        footerPanel = new JPanel();
        footerPanel.setBackground(new Color(72, 0, 0));
        footerPanel.setLayout(new FlowLayout());

        uploadButton = new JButton("Upload Image");
        uploadButton.setForeground(Color.BLACK);
        uploadButton.setFont(new Font("Source Code Pro", Font.BOLD, 14));
        uploadButton.setBackground(new Color(60, 63, 65));
        uploadButton.setFocusable(false);

        forwardButton = new JButton(">");
        forwardButton.setForeground(Color.BLACK);
        forwardButton.setFont(new Font("Source Code Pro", Font.BOLD, 14));
        forwardButton.setBackground(new Color(60, 63, 65));
        forwardButton.setFocusable(false);

        backwardButton = new JButton("<");
        backwardButton.setForeground(Color.BLACK);
        backwardButton.setFont(new Font("Source Code Pro", Font.BOLD, 14));
        backwardButton.setBackground(new Color(60, 63, 65));
        backwardButton.setFocusable(false);

        setUpLayout();
        setUpPanel();
        setUpListeners();
    }

    private void setUpLayout() {

        this.setBackground(new Color(72, 0, 0));
        this.setBorder(BorderFactory.createEmptyBorder(8, 16, 8, 16));
        this.setLayout(new BorderLayout());
    }

    private void setUpPanel() {

        this.add(currentPlain, BorderLayout.NORTH);

        footerPanel.add(backwardButton);
        footerPanel.add(uploadButton);
        footerPanel.add(forwardButton);

        this.add(imageCanvas, BorderLayout.CENTER);
        this.add(footerPanel, BorderLayout.SOUTH);
    }

    private void setUpListeners() {

        uploadButton.addActionListener(e -> {

            try {
                //File file = baseController.getFileChooser().pickSingleFileChooser("bmp");
                File file = baseController.getFileChooser().pickSingleFileChooser("images");

                if (file != null) {

                    image = ImageIO.read(file);
                    baseController.getBitPlaneSlicing().setWorkingImage(image);
                    updateImage();
                }

            } catch (IOException ioException) {
                ioException.printStackTrace();
            }
        });

        forwardButton.addActionListener(e -> {

            if (image == null) {

                return;
            }

            baseController.getBitPlaneSlicing().forwardAction();
            updateImage();
        });

        backwardButton.addActionListener(e -> {

            if (image == null) {

                return;
            }

            baseController.getBitPlaneSlicing().backwardAction();
            updateImage();
        });
    }

    private void updateImage() {

        currentPlain.setText(baseController.getBitPlaneSlicing().getDescription());
        imageCanvas.setIcon(new ImageIcon(baseController.getBitPlaneSlicing().getImage()));
        repaint();
    }
}
