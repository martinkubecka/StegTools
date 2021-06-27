package view.menu;

import javax.swing.*;
import java.awt.*;

public class HelpWindow extends JFrame {

    private JPanel panel;
    private JTextArea textArea;
    private JScrollPane scrollPane;

    public HelpWindow(String title) throws HeadlessException {
        super(title);

        setUpFrame();
        setUpPanel();
    }

    /**
     * Sets frame dimensions and location.
     */
    private void setUpFrame() {

        // HELP WINDOW DIMENSIONS
        Dimension dimension = Toolkit.getDefaultToolkit().getScreenSize();
        int width = (int) (dimension.width * 0.3);
        int height = (int) (dimension.width * 0.3);
        this.setSize(width, height);
        int x = (int) ((dimension.getWidth() - this.getWidth()) / 2);
        int y = (int) ((dimension.getHeight() - this.getHeight()) / 2);

        // HELP WINDOW LOCATION
        this.setLocation(x, y);
    }

    /**
     * Appends the specified components to the panel.
     */
    private void setUpPanel() {

        panel = new JPanel();
        panel.setLayout(new GridLayout(1, 1, 8, 8));
        panel.setBackground(new Color(72, 0, 0));
        panel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        textArea = new JTextArea();
        textArea.setLineWrap(true);
        textArea.setWrapStyleWord(true);
        textArea.setForeground(Color.BLACK);
        textArea.setFont(new Font("Source Code Pro", Font.PLAIN, 14));
        textArea.setEditable(false);
        textArea.setFont(new Font(Font.MONOSPACED, Font.PLAIN, 12));

        textArea.append("    _          _       \n");
        textArea.append("   | |__   ___| |_ __  \n");
        textArea.append("   | '_ \\ / _ \\ | '_ \\ \n");
        textArea.append("   | | | |  __/ | |_) |\n");
        textArea.append("   |_| |_|\\___|_|  __/ \n");
        textArea.append("                |_|   \n");
        textArea.append(" ------------------------\n");
        textArea.append("   General Tools\n\n");
        textArea.append("   > Show Metadata\n");
        textArea.append("   >>> supported image formats : BMP, PNG, JPEG, GIF, TIFF and RAW\n");
        textArea.append("   > Compress Files & Decompress Files\n");
        textArea.append("   >>> password protected compression and decompression\n");
        textArea.append("   > Message Shortening with Synonym Dictionary\n");
        textArea.append("   >>> supported file format : TXT\n\n\n");

        textArea.append("   Image Tools\n\n");
        textArea.append("   > Bit Plane Viewer\n");
        textArea.append("   >>> supported RGB images with 8‑bits per channel\n");
        textArea.append("   > Appended Data Extraction\n");
        textArea.append("   >>> supported image formats : BMP, PNG\n");
        textArea.append("   > Check Header\n");
        textArea.append("   >>> automatic repair of corrupted image header\n");
        textArea.append("   >>> supported image format : PNG\n");
        textArea.append("   > Least Significant Bit\n");
        textArea.append("   >>> LSB insertion and extraction\n");
        textArea.append("   >>> supported image format : BMP\n");

        scrollPane = new JScrollPane(textArea);
        scrollPane.setPreferredSize(new Dimension(500, 300));
        scrollPane.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);

        panel.add(scrollPane);
        this.add(panel);
    }
}
