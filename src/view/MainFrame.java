package view;

import controller.AppController;

import javax.swing.*;
import java.awt.*;

public class MainFrame extends JFrame {

    private Panels panels;

    /**
     * Create a Frame object
     *
     * @param baseController The reference to the AppController object
     */
    public MainFrame(AppController baseController) {

        panels = new Panels(baseController);
        setUpFrame();
    }

    /**
     * Sets the UI theme, content pane, frame dimensions and location, visibility
     */
    private void setUpFrame() {

        this.getContentPane().add(panels, BorderLayout.CENTER);
        this.getContentPane().add(panels.getHeaderPanel(), BorderLayout.PAGE_START);

        // MAIN WINDOW DIMENSIONS
        Dimension dimension = Toolkit.getDefaultToolkit().getScreenSize();
        int width = (int) (dimension.width * 0.3);
        int height = (int) (dimension.width * 0.3);
        this.setSize(width, height);
        int x = (int) ((dimension.getWidth() - this.getWidth()) / 2);
        int y = (int) ((dimension.getHeight() - this.getHeight()) / 2);

        // MAIN WINDOW LOCATION
        this.setLocation(x, y);

        this.setTitle("Stegtools");
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setVisible(true);
    }
}
