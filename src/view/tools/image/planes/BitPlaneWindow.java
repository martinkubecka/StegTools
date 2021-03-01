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

    private void setUpFrame() {

        // BIT PLANE WINDOW DIMENSIONS
        this.setExtendedState(JFrame.MAXIMIZED_BOTH);

        // BIT PLANE WINDOW LOCATION
        this.setLocationRelativeTo(null);
    }

    private void setUpPanel() {

        this.add(bitPlanePanel);
    }
}

