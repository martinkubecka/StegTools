package gui.menu;

import gui.tools.ToolsDialog;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;

public class MainMenuPanel extends JFrame {

    private JPanel mainMenuPanel;
    private JLabel mainMenuLabel;
    private JButton toolsButton;
    private JButton helpButton;
    private JButton exitButton;
    private JLabel authorLabel;

    public MainMenuPanel(String title) {
        super(title);

        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setContentPane(mainMenuPanel);

        //Dimension dimension = Toolkit.getDefaultToolkit().getScreenSize();
        // TODO : Buttons dimensions based on frame dimensions

        //this.pack();


        toolsButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                JDialog toolsDialog = new ToolsDialog(mainMenuPanel, "Tools Menu");
                toolsDialog.getContentPane().setBackground(new Color(72, 0, 0));

                Dimension dimension = Toolkit.getDefaultToolkit().getScreenSize();
                int width = (int) (dimension.width * 0.2);
                int height = (int) (dimension.width * 0.2);
                toolsDialog.setSize(width, height);
                int x = (int) ((dimension.getWidth() - toolsDialog.getWidth()) / 2);
                int y = (int) ((dimension.getHeight() - toolsDialog.getHeight()) / 2);
                toolsDialog.setLocation(x, y);

                toolsDialog.setVisible(true);
            }
        });


        helpButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                JDialog instructionsDialog = new InstructionsDialog("Instructions");
                instructionsDialog.getContentPane().setBackground(new Color(72, 0, 0));

                Dimension dimension = Toolkit.getDefaultToolkit().getScreenSize();
                int width = (int) (dimension.width * 0.25);
                int height = (int) (dimension.width * 0.25);
                instructionsDialog.setSize(width, height);
                int x = (int) ((dimension.getWidth() - instructionsDialog.getWidth()) / 2);
                int y = (int) ((dimension.getHeight() - instructionsDialog.getHeight()) / 2);
                instructionsDialog.setLocation(x, y);

                instructionsDialog.setVisible(true);
            }
        });


        exitButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                MainMenuPanel.this.processWindowEvent(
                        new WindowEvent(
                                MainMenuPanel.this, WindowEvent.WINDOW_CLOSING));
            }
        });

    }

}
