package view.menu;

import javax.swing.*;
import javax.swing.border.LineBorder;
import java.awt.*;
import view.components.Button;

public class HeaderPanel extends JPanel {

    private JPanel parentPanel;
    private CardLayout cardLayout;
    private JButton changeLogButton;
    private JButton toolsButton;
    private JButton helpButton;
//    private JButton exitButton;

    public HeaderPanel(JPanel parentPanel, CardLayout cardLayout) {

        this.parentPanel = parentPanel;
        this.cardLayout = cardLayout;

        changeLogButton = new Button("Home");
        toolsButton = new Button("Tools");
        helpButton = new Button("Help");

//        JButton exitButton = new Button("Exit");

        setUpLayout();
        setUpPanel();
        setUpListeners();
    }

    private void setUpLayout() {

        this.setLayout(new FlowLayout(FlowLayout.LEFT, 3, 3));
        this.setBackground(new Color(72, 0, 0));
        this.setBorder(new LineBorder(Color.WHITE, 3));
    }

    private void setUpPanel() {

        this.add(changeLogButton);
        this.add(toolsButton);
        this.add(helpButton);
        //this.add(exitButton);
    }

    private void setUpListeners() {

        changeLogButton.addActionListener(e -> {

            String changeLogPanel = "ChangeLog Panel";
            cardLayout.show(parentPanel, changeLogPanel);

        });

        toolsButton.addActionListener(e -> {

            String toolsPanel = "Tools Panel";
            cardLayout.show(parentPanel, toolsPanel);
        });

        helpButton.addActionListener(e -> {

            HelpWindow helpWindow = new HelpWindow("Instructions");
            helpWindow.setVisible(true);

        });
    }
}
