package view.tools.image.planes;

import controller.AppController;
import view.components.Button;
import view.components.Label;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.util.logging.Level;
import java.util.logging.Logger;

public class BitPlanePanel extends JPanel {

    private final static Logger LOGGER = Logger.getLogger(Logger.GLOBAL_LOGGER_NAME);

    private AppController baseController;
    private Label currentPlain;
    private JLabel imageCanvas;

//    private JPanel imageCanvasPanel;

    private JPanel footerPanel;

    private JPanel footerPanelControls;
    private JButton uploadButton;
    private JButton forwardButton;
    private JButton backwardButton;

    private JPanel footerPanelMore;
//    private JButton saveImageButton;

    private BufferedImage image;

    public BitPlanePanel(AppController baseController) {

        this.baseController = baseController;

        // HEADER
        currentPlain = new Label("", SwingConstants.CENTER, SwingConstants.TOP, Font.PLAIN, 15);

        // CANVAS
        imageCanvas = new JLabel("", SwingConstants.CENTER);
//        imageCanvasPanel = new JPanel();
        // TODO add scroll bars

        // Footer Panel Controls
        footerPanelControls = new JPanel();
        footerPanelControls.setBackground(new Color(72, 0, 0));
        footerPanelControls.setLayout(new FlowLayout());

        // Footer Panel More
        footerPanelMore = new JPanel();
        footerPanelMore.setBackground(new Color(72, 0, 0));
        footerPanelMore.setLayout(new FlowLayout());

        // FOOTER Panel
        footerPanel = new JPanel();
        footerPanel.setBackground(new Color(72, 0, 0));
        footerPanel.setLayout(new GridLayout(2, 1));

        uploadButton = new Button("Upload Image");
        forwardButton = new Button(">");
        backwardButton = new Button("<");
//        saveImageButton = new Button("Save Image");

        setUpLayout();
        setUpPanel();
        setUpListeners();
    }

    /**
     * Sets panel layout, background color and border.
     */
    private void setUpLayout() {

        this.setBackground(new Color(72, 0, 0));
        this.setBorder(BorderFactory.createEmptyBorder(8, 16, 8, 16));
        this.setLayout(new BorderLayout());
    }

    /**
     * Appends the specified components to the panel.
     */
    private void setUpPanel() {

        this.add(currentPlain, BorderLayout.NORTH);

        footerPanelControls.add(backwardButton);
        footerPanelControls.add(uploadButton);
        footerPanelControls.add(forwardButton);

//        footerPanelMore.add(saveImageButton);

        footerPanel.add(footerPanelControls);
        footerPanel.add(footerPanelMore);

        this.add(imageCanvas, BorderLayout.CENTER);
//        this.add(imageCanvasPanel, BorderLayout.CENTER);
        this.add(footerPanel, BorderLayout.SOUTH);
    }

    /**
     * Adds ActionListener to the panel buttons an carries out minimal logic.
     */
    private void setUpListeners() {

        uploadButton.addActionListener(e -> {

            try {
                //File file = baseController.getFileChooser().pickSingleFileChooser("bmp");
                File file = baseController.getFileChooser().pickSingleFileChooser("images");

                if (file != null) {

                    image = ImageIO.read(file);
                    boolean isSuported = baseController.getBitPlaneSlicing().setWorkingImage(image);

                    if (isSuported){
                        updateImage();
                    } else {
                        JOptionPane.showMessageDialog(this,
                                "RGB images with 8‑bits per channel (24‑bit or 32-bit images) are supported.",
                                "Image not supported",
                                JOptionPane.PLAIN_MESSAGE);
                    }
                }

            } catch (Exception exception) {

                LOGGER.log(Level.SEVERE, exception.toString(), exception);
            }
        });

//        saveImageButton.addActionListener(e -> {
//
//            // TODO
//        });

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
