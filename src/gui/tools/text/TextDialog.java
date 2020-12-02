package gui.tools.text;

import process.Dictionary;

import javax.swing.*;
import java.awt.event.*;

public class TextDialog extends JDialog {
    private JPanel contentPane;
    private JButton buttonCancel;
    private JButton messageShorteningButton;
    private JButton button2;
    private JButton button3;
    private JButton button4;

    public TextDialog() {
        setContentPane(contentPane);
        setModal(true);

        buttonCancel.addActionListener(new ActionListener() {
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

        // Message shortening
        messageShorteningButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                Dictionary.applyMessageShortening();
            }
        });
    }

    private void onCancel() {
        // add your code here if necessary
        dispose();
    }
}
