package view;

import controller.AppController;
import view.menu.ChangeLogPanel;
import view.menu.HeaderPanel;
import view.tools.ToolsPanel;
import view.tools.general.GeneralToolsPanel;
import view.tools.image.ImageToolsPanel;
import view.tools.image.planes.BitPlanePanel;
import view.tools.image.planes.LSBPanel;

import javax.swing.*;
import java.awt.*;

public class Panels extends JPanel {

    private AppController baseController;
    private CardLayout cardLayout;
    private HeaderPanel headerPanel;
    private ChangeLogPanel changeLogPanel;
    private ToolsPanel toolsPanel;
    private GeneralToolsPanel generalToolsPanel;
    private ImageToolsPanel imageToolsPanel;
//    private BitPlanePanel bitPlanePanel;
    private LSBPanel leastSignificantBitPanel;

    public Panels(AppController baseController) {

        this.baseController = baseController;

        cardLayout = new CardLayout();

        // HEADER PANEL
        headerPanel = new HeaderPanel(this, cardLayout);

        // CENTER PANELS
        changeLogPanel = new ChangeLogPanel();
        toolsPanel = new ToolsPanel(this, cardLayout);
        generalToolsPanel = new GeneralToolsPanel(baseController);
        imageToolsPanel = new ImageToolsPanel(this, baseController, cardLayout);
//        bitPlanePanel = new BitPlanePanel(baseController);
        leastSignificantBitPanel = new LSBPanel(this, baseController, cardLayout);

        setUpPanel();
    }

    private void setUpPanel() {

        this.setLayout(cardLayout);
        this.add(changeLogPanel, "ChangeLog Panel");
        this.add(toolsPanel, "Tools Panel");
        this.add(generalToolsPanel, "General Tools Panel");
        this.add(imageToolsPanel, "Image Tools Panel");
//        this.add(bitPlanePanel, "Bit Plane Panel");
        this.add(leastSignificantBitPanel, "LSB Panel");
    }

    public HeaderPanel getHeaderPanel() {
        return headerPanel;
    }
}
