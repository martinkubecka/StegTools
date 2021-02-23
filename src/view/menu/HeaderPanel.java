package view.menu;

import javax.swing.*;
import javax.swing.border.LineBorder;
import java.awt.*;

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

        changeLogButton = new JButton("Home");
        changeLogButton.setForeground(Color.BLACK);
        changeLogButton.setFont(new Font("Source Code Pro", Font.BOLD, 14));
        changeLogButton.setBackground(new Color(60, 63, 65));
        changeLogButton.setFocusable(false);

        toolsButton = new JButton("Tools");
        toolsButton.setForeground(Color.BLACK);
        toolsButton.setFont(new Font("Source Code Pro", Font.BOLD, 14));
        toolsButton.setBackground(new Color(60, 63, 65));
        toolsButton.setFocusable(false);

        helpButton = new JButton("Help");
        helpButton.setForeground(Color.BLACK);
        helpButton.setFont(new Font("Source Code Pro", Font.BOLD, 14));
        helpButton.setBackground(new Color(60, 63, 65));
        helpButton.setFocusable(false);

//        JButton exitButton = new JButton("Exit");
//        exitButton.setForeground(Color.WHITE);
//        exitButton.setFont(new Font("Source Code Pro", Font.BOLD, 14));
//        exitButton.setBackground(new Color(60, 63, 65));

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
