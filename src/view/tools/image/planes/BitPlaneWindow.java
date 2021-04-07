package view.tools.image.planes;

import controller.AppController;

import javax.swing.*;
import java.awt.*;

public class BitPlaneWindow extends JFrame {

    private BitPlanePanel bitPlanePanel;

    public BitPlaneWindow(String title, AppController baseController) throws HeadlessException {
        super(title);

        bitPlanePanel = new BitPlanePanel(baseController);
        setUpFrame();
        setUpPanel();
    }

    /**
     * Sets frame dimensions and location.
     */
    private void setUpFrame() {

        // BIT PLANE WINDOW DIMENSIONS
        this.setExtendedState(JFrame.MAXIMIZED_BOTH);

        // BIT PLANE WINDOW LOCATION
        this.setLocationRelativeTo(null);
    }

    /**
     * Appends the specified components to the panel.
     */
    private void setUpPanel() {

        this.add(bitPlanePanel);
    }
}

