package gui.menu;

import gui.tools.text.TextToolsPanel;

import javax.swing.*;
import javax.swing.border.LineBorder;
import java.awt.*;

public class HeaderPanel extends JPanel {

    public HeaderPanel(JPanel contentPane, CardLayout cardLayout) {

        this.setLayout(new FlowLayout(FlowLayout.LEFT, 3, 3));
        this.setBackground(new Color(72, 0, 0));
        this.setBorder(new LineBorder(Color.WHITE, 3));

        JButton changeLogButton = new JButton("Home");
        changeLogButton.setForeground(Color.BLACK);
        changeLogButton.setFont(new Font("Source Code Pro", Font.BOLD, 14));
        changeLogButton.setBackground(new Color(60, 63, 65));
        changeLogButton.setFocusable(false);
        changeLogButton.addActionListener(e -> {

            String changeLogPanel = "ChangeLog Panel";
            cardLayout.show(contentPane, changeLogPanel);
        });

        JButton toolsButton = new JButton("Tools");
        toolsButton.setForeground(Color.BLACK);
        toolsButton.setFont(new Font("Source Code Pro", Font.BOLD, 14));
        toolsButton.setBackground(new Color(60, 63, 65));
        toolsButton.setFocusable(false);
        toolsButton.addActionListener(e -> {

            String toolsPanel = "Tools Panel";
            cardLayout.show(contentPane, toolsPanel);
        });

        JButton helpButton = new JButton("Help");
        helpButton.setForeground(Color.BLACK);
        helpButton.setFont(new Font("Source Code Pro", Font.BOLD, 14));
        helpButton.setBackground(new Color(60, 63, 65));
        helpButton.setFocusable(false);
        helpButton.addActionListener(e -> {

            HelpWindow helpWindow = new HelpWindow("Instructions");
            helpWindow.setVisible(true);

        });

//        JButton exitButton = new JButton("Exit");
//        exitButton.setForeground(Color.WHITE);
//        exitButton.setFont(new Font("Source Code Pro", Font.BOLD, 14));
//        exitButton.setBackground(new Color(60, 63, 65));

        this.add(changeLogButton);
        this.add(toolsButton);
        this.add(helpButton);
        //this.add(exitButton);

    }
}
