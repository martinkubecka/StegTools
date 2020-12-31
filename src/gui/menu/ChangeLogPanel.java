package gui.menu;

import javax.swing.*;
import java.awt.*;
import java.io.FileReader;

public class ChangeLogPanel extends JPanel {

    public ChangeLogPanel() {

        this.setLayout(new GridLayout(4, 1, 8, 8));
        this.setBackground(new Color(72, 0, 0));
        this.setBorder(BorderFactory.createEmptyBorder(8, 16, 8, 16));

        JLabel nameLabel = new JLabel("StegTools", SwingConstants.CENTER);
        nameLabel.setVerticalAlignment(SwingConstants.CENTER);
        nameLabel.setForeground(new Color(244, 244, 244));
        nameLabel.setFont(new Font("Consolas", Font.PLAIN, 36));

        JLabel changeLogLabel = new JLabel("Change Log:", SwingConstants.LEFT);
        changeLogLabel.setVerticalAlignment(SwingConstants.BOTTOM);
        changeLogLabel.setForeground(new Color(244, 244, 244));
        changeLogLabel.setFont(new Font("Consolas", Font.PLAIN, 20));

        JLabel creatorLabel = new JLabel("Martin Kubecka, 2020, v0.86", SwingConstants.RIGHT);
        creatorLabel.setVerticalAlignment(SwingConstants.BOTTOM);
        creatorLabel.setForeground(new Color(244, 244, 244));
        creatorLabel.setFont(new Font("Consolas", Font.PLAIN, 12));

        JTextArea textArea = new JTextArea();

        try {
            FileReader reader = new FileReader("src/resources/version_control.txt");
            textArea.read(reader, "src/resources/version_control.txt"); //Object of JTextArea
        } catch (Exception exception) {
            exception.printStackTrace();
        }

        textArea.setLineWrap(true);
        textArea.setWrapStyleWord(true);
        //textArea.setBackground(new Color());
        textArea.setBorder(BorderFactory.createBevelBorder(1));
        textArea.setForeground(Color.BLACK);
        textArea.setFont(new Font("Source Code Pro", Font.PLAIN, 14));
        textArea.setEditable(false);

        JScrollPane scrollPane = new JScrollPane(textArea);
        scrollPane.setPreferredSize(new Dimension(500, 300));
        scrollPane.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);

        this.add(nameLabel);
        this.add(changeLogLabel);
        this.add(scrollPane);
        this.add(creatorLabel);
    }
}
