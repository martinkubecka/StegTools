package gui.menu;

import javax.swing.*;
import java.awt.*;

public class HelpWindow extends JFrame {

    public HelpWindow(String title) throws HeadlessException {
        super(title);

        // HELP WINDOW DIMENSIONS
        Dimension dimension = Toolkit.getDefaultToolkit().getScreenSize();
        int width = (int) (dimension.width * 0.25);
        int height = (int) (dimension.width * 0.25);
        this.setSize(width, height);
        int x = (int) ((dimension.getWidth() - this.getWidth()) / 2);
        int y = (int) ((dimension.getHeight() - this.getHeight()) / 2);

        // HELP WINDOW LOCATION
        this.setLocation(x, y);

        JPanel panel = new JPanel();
        panel.setLayout(new GridLayout(1, 1, 8, 8));
        panel.setBackground(new Color(72, 0, 0));
        panel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        JTextArea textArea = new JTextArea();
        textArea.setLineWrap(true);
        textArea.setWrapStyleWord(true);
        textArea.setForeground(Color.BLACK);
        textArea.setFont(new Font("Source Code Pro", Font.PLAIN, 14));
        textArea.setEditable(false);

        JScrollPane scrollPane = new JScrollPane(textArea);
        scrollPane.setPreferredSize(new Dimension(500, 300));
        scrollPane.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);

        panel.add(scrollPane);
        this.add(panel);
    }
}
