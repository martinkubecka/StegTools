package gui.menu;

import javax.swing.*;
import javax.swing.border.LineBorder;
import java.awt.*;

public class HeaderPanel extends JPanel {

    public HeaderPanel() {

        this.setLayout(new FlowLayout(FlowLayout.LEFT, 3, 3));
        this.setBackground(new Color(72, 0, 0));
        this.setBorder(new LineBorder(Color.WHITE, 3));

        JButton toolsButton = new JButton("Tools");
        toolsButton.setForeground(Color.BLACK);
        toolsButton.setFont(new Font("Source Code Pro", Font.BOLD, 14));
        toolsButton.setBackground(new Color(60, 63, 65));

        toolsButton.addActionListener(e -> {
            // TODO Tools Button Action
        });

        JButton helpButton = new JButton("Help");
        helpButton.setForeground(Color.BLACK);
        helpButton.setFont(new Font("Source Code Pro", Font.BOLD, 14));
        helpButton.setBackground(new Color(60, 63, 65));

        helpButton.addActionListener(e -> {

            JDialog instructionsDialog = new InstructionsDialog("Instructions");
            instructionsDialog.getContentPane().setBackground(new Color(72, 0, 0));

            Dimension dimension1 = Toolkit.getDefaultToolkit().getScreenSize();
            int width1 = (int) (dimension1.width * 0.25);
            int height1 = (int) (dimension1.width * 0.25);
            instructionsDialog.setSize(width1, height1);
            int x1 = (int) ((dimension1.getWidth() - instructionsDialog.getWidth()) / 2);
            int y1 = (int) ((dimension1.getHeight() - instructionsDialog.getHeight()) / 2);
            instructionsDialog.setLocation(x1, y1);

            instructionsDialog.setVisible(true);
        });

//        JButton exitButton = new JButton("Exit");
//        exitButton.setForeground(Color.WHITE);
//        exitButton.setFont(new Font("Source Code Pro", Font.BOLD, 14));
//        exitButton.setBackground(new Color(60, 63, 65));

        this.add(toolsButton);
        this.add(helpButton);
        //this.add(exitButton);

    }
}
