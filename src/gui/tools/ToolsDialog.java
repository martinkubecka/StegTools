package gui.tools;

import gui.menu.InstructionsDialog;
import gui.tools.general.GeneralDialog;
import gui.tools.text.TextDialog;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class ToolsDialog extends JDialog {
    private JPanel contentPane;
    private JButton cancelButton;
    private JButton generealButton;
    private JLabel toolsLabel;
    private JButton textButton;
    private JButton pngButton;
    private JButton bmpButton;

    public ToolsDialog(JPanel mainMenuPanel, String name) {
        //super(mainMenuPanel, name);
        this.setName(name);
        setContentPane(contentPane);
        setModal(true);

        cancelButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                onCancel();
            }
        });

        // call onCancel() when cross is clicked
        setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);
        addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent e) {
                onCancel();
            }
        });

        // call onCancel() on ESCAPE
        contentPane.registerKeyboardAction(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                onCancel();
            }
        }, KeyStroke.getKeyStroke(KeyEvent.VK_ESCAPE, 0), JComponent.WHEN_ANCESTOR_OF_FOCUSED_COMPONENT);

        // MENU BUTTONS
        generealButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JDialog generalDialog = new GeneralDialog();
                generalDialog.getContentPane().setBackground(new Color(72, 0, 0));

                Dimension dimension = Toolkit.getDefaultToolkit().getScreenSize();
                int width = (int) (dimension.width * 0.25);
                int height = (int) (dimension.width * 0.25);
                generalDialog.setSize(width, height);
                int x = (int) ((dimension.getWidth() - generalDialog.getWidth()) / 2);
                int y = (int) ((dimension.getHeight() - generalDialog.getHeight()) / 2);
                generalDialog.setLocation(x, y);

                generalDialog.setVisible(true);
            }
        });

        textButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JDialog textDialog = new TextDialog();
                textDialog.getContentPane().setBackground(new Color(72, 0, 0));

                Dimension dimension = Toolkit.getDefaultToolkit().getScreenSize();
                int width = (int) (dimension.width * 0.25);
                int height = (int) (dimension.width * 0.25);
                textDialog.setSize(width, height);
                int x = (int) ((dimension.getWidth() - textDialog.getWidth()) / 2);
                int y = (int) ((dimension.getHeight() - textDialog.getHeight()) / 2);
                textDialog.setLocation(x, y);

                textDialog.setVisible(true);
            }
        });

        pngButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // TODO PNG
            }
        });

        bmpButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // TODO BMP
            }
        });
    }

    private void onOK() {
        // add your code here
        dispose();
    }

    private void onCancel() {
        // add your code here if necessary
        dispose();
    }

//    public static void main(String[] args) {
//        ToolsDialog dialog = new ToolsDialog("Tools");
//        dialog.pack();
//        dialog.setVisible(true);
//        System.exit(0);
//    }
}
