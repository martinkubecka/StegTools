package view.tools.general;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

public class MetadataWindow extends JFrame {

    private ArrayList<String> metadataParsed;
    private JPanel panel;
    private JTextArea textArea;
    private JScrollPane scrollPane;

    public MetadataWindow(String title, ArrayList<String> metadataParsed) {
        super(title);

        this.metadataParsed = metadataParsed;

        setUpFrame();
        setUpPanel();
    }

    /**
     * Sets frame dimensions and location.
     */
    private void setUpFrame() {

        // METADATA WINDOW DIMENSIONS
        Dimension dimension = Toolkit.getDefaultToolkit().getScreenSize();
        int width = (int) (dimension.width * 0.25);
        int height = (int) (dimension.width * 0.25);
        this.setSize(width, height);
        int x = (int) ((dimension.getWidth() - this.getWidth()) / 2);
        int y = (int) ((dimension.getHeight() - this.getHeight()) / 2);

        // METADATA WINDOW LOCATION
        this.setLocation(x, y);
    }

    /**
     * Appends the specified components to the panel.
     */
    private void setUpPanel() {

        panel = new JPanel();
        panel.setLayout(new GridLayout(1, 1, 8, 8));

        textArea = new JTextArea();
        textArea.setLineWrap(true);
        textArea.setWrapStyleWord(true);
        textArea.setBorder(BorderFactory.createBevelBorder(1));
        textArea.setForeground(Color.BLACK);
        textArea.setFont(new Font("Source Code Pro", Font.PLAIN, 14));
        textArea.setEditable(false);

        for (String data : metadataParsed) {

            textArea.append(data + "\n");
        }

        scrollPane = new JScrollPane(textArea);
        scrollPane.setPreferredSize(new Dimension(500, 300));
        scrollPane.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);

        panel.add(scrollPane);
        this.add(panel);
    }
}
