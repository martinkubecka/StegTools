package gui.tools.png;

import com.drew.metadata.Metadata;
import gui.tools.general.MetadataWindow;
import process.Compression;
import process.ExtractMetadata;
import process.explorer.FileChooser;
import process.explorer.filters.PngChooserFilter;
import process.png.ImageHeader;

import javax.swing.*;
import java.awt.*;
import java.io.File;

public class PngToolsPanel extends JPanel {

    public PngToolsPanel() {

        this.setLayout(new GridLayout(5, 1, 8, 70));
        this.setBackground(new Color(72, 0, 0));
        this.setBorder(BorderFactory.createEmptyBorder(8, 150, 8, 150));

        JLabel nameLabel = new JLabel("PNG Tools", SwingConstants.CENTER);
        nameLabel.setVerticalAlignment(SwingConstants.CENTER);
        nameLabel.setForeground(new Color(244, 244, 244));
        nameLabel.setFont(new Font("Consolas", Font.PLAIN, 36));

        JButton checkHeaderButton = new JButton("Check header");
        checkHeaderButton.setForeground(Color.BLACK);
        checkHeaderButton.setFont(new Font("Source Code Pro", Font.BOLD, 14));
        checkHeaderButton.setBackground(new Color(60, 63, 65));
        checkHeaderButton.setFocusable(false);
        checkHeaderButton.addActionListener(e -> {

            // TODO refactor after implementation of controller
           // File file = FileChooser.pickPngFromFileChooser();
            File file = FileChooser.pickSingleFileChooser("png");


            if (file != null) {

                boolean resultChecker = ImageHeader.headerChecker(file);

                if (resultChecker) {

                    JOptionPane.showMessageDialog(
                            this,
                            "Your image header is correct.");

                } else {

                    // 0 = yes, 1 = no, 2 = cancel
                    int input = JOptionPane.showConfirmDialog(this,
                            "Perform automatic repair?", "Something wrong with the header!", JOptionPane.YES_NO_CANCEL_OPTION);

                    if (input == 0) {

                        boolean resultRepair = ImageHeader.headerRepair(file);

                        if (resultRepair) {
                            JOptionPane.showMessageDialog(this,
                                    "Successfully repaired your image!");
                        } else {
                            JOptionPane.showMessageDialog(this,
                                    "There was an error while repairing, sorry..",
                                    "Warning",
                                    JOptionPane.ERROR_MESSAGE);
                        }

                    }
                }
            }
        });

        this.add(nameLabel);
        this.add(checkHeaderButton);
    }
}
