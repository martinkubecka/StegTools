import gui.menu.ChangeLogPanel;
import gui.menu.HeaderPanel;
import gui.tools.ToolsPanel;
import gui.tools.general.GeneralToolsPanel;
import gui.tools.png.PngToolsPanel;
import gui.tools.text.HideMessagePanel;
import gui.tools.text.TextManipulationPanel;
import gui.tools.text.TextToolsPanel;

import javax.swing.*;
import java.awt.*;

public class MainFrame extends JFrame {

    public MainFrame(String title) {
        super(title);

        // MAIN WINDOW DIMENSIONS
        Dimension dimension = Toolkit.getDefaultToolkit().getScreenSize();
        int width = (int) (dimension.width * 0.3);
        int height = (int) (dimension.width * 0.3);
        this.setSize(width, height);
        int x = (int) ((dimension.getWidth() - this.getWidth()) / 2);
        int y = (int) ((dimension.getHeight() - this.getHeight()) / 2);

        // MAIN WINDOW LOCATION
        this.setLocation(x, y);

        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        JPanel contentPane = new JPanel();
        CardLayout cardLayout = new CardLayout();
        contentPane.setLayout(cardLayout);

        // HEADER PANEL
        JPanel headerPanel = new HeaderPanel(contentPane, cardLayout);

        // CENTER PANELS
        JPanel changeLogPanel = new ChangeLogPanel();
        JPanel toolsPanel = new ToolsPanel(contentPane, cardLayout);
        JPanel generalToolsPanel = new GeneralToolsPanel();
        JPanel textToolsPanel = new TextToolsPanel(contentPane, cardLayout);
        JPanel textManipulationPanel = new TextManipulationPanel();
        JPanel hideMessagePanel = new HideMessagePanel();
        JPanel pngToolsPanel= new PngToolsPanel();

        contentPane.add(changeLogPanel, "ChangeLog Panel");
        contentPane.add(toolsPanel, "Tools Panel");
        contentPane.add(generalToolsPanel, "General Tools Panel");
        contentPane.add(textToolsPanel, "Text Tools Panel");
        contentPane.add(textManipulationPanel, "Text Manipulation Panel");
        contentPane.add(hideMessagePanel, "Hide Message Panel");
        contentPane.add(pngToolsPanel, "PNG Tools Panel");

        this.getContentPane().add(headerPanel, BorderLayout.PAGE_START);
        this.getContentPane().add(contentPane, BorderLayout.CENTER);
    }
}
