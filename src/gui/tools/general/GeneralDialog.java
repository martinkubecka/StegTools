package gui.tools.general;

import process.Compression;
import process.ExtractMetadata;
import process.FileChooser;

import javax.swing.*;
import java.awt.event.*;
import java.io.File;

public class GeneralDialog extends JDialog {
    private JPanel contentPane;
    private JButton buttonCancel;
    private JButton metadataButton;
    private JButton compressButton;

    public GeneralDialog() {
        setContentPane(contentPane);
        setModal(true);
        getRootPane().setDefaultButton(buttonCancel);

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


        // METADATA
        metadataButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                File file = FileChooser.pickImageFromFileChooser();
                ExtractMetadata.extractMetadata(file);
            }
        });

        // COMPRESS FILES INTO ZIP
        compressButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                Compression.compressFilesToZip();
            }
        });
    }

    private void onCancel() {
        // add your code here if necessary
        dispose();
    }

}
