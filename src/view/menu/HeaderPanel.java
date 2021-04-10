package view.menu;

import javax.swing.*;
import javax.swing.border.LineBorder;
import java.awt.*;

import view.components.Button;

public class HeaderPanel extends JPanel {

    private JPanel parentPanel;
    private CardLayout cardLayout;
    private Button homeButton;
    private Button toolsButton;
    private Button helpButton;
    private Button changeLogButton;
//    private JButton exitButton;

    public HeaderPanel(JPanel parentPanel, CardLayout cardLayout) {

        this.parentPanel = parentPanel;
        this.cardLayout = cardLayout;

        homeButton = new Button("Home");
        toolsButton = new Button("Tools");
        helpButton = new Button("Help");
        changeLogButton = new Button("Change Log");

        setUpLayout();
        setUpPanel();
        setUpListeners();
    }

    /**
     * Sets panel layout, background color and border.
     */
    private void setUpLayout() {

        this.setLayout(new FlowLayout(FlowLayout.LEFT, 3, 3));
        this.setBackground(new Color(72, 0, 0));
        this.setBorder(new LineBorder(Color.WHITE, 3));
    }

    /**
     * Appends the specified components to the panel.
     */
    private void setUpPanel() {

        this.add(homeButton);
        this.add(toolsButton);
        this.add(helpButton);
        this.add(changeLogButton);
    }

    /**
     * Adds ActionListener to the panel buttons an carries out minimal logic.
     */
    private void setUpListeners() {

        homeButton.addActionListener(e -> {

            String homePanel = "Home Panel";
            cardLayout.show(parentPanel, homePanel);

        });

        toolsButton.addActionListener(e -> {

            String toolsPanel = "Tools Panel";
            cardLayout.show(parentPanel, toolsPanel);
        });

        helpButton.addActionListener(e -> {

            HelpWindow helpWindow = new HelpWindow("Instructions");
            helpWindow.setVisible(true);

        });

        changeLogButton.addActionListener(e -> {

            String changeLogPanel = "Change Log Panel";
            cardLayout.show(parentPanel, changeLogPanel);

        });
    }
}
