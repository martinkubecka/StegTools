package gui.menu;

import javax.swing.*;
import java.awt.*;

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

        JLabel creatorLabel = new JLabel("Martin Kubecka, 2020, v0.6", SwingConstants.RIGHT);
        creatorLabel.setVerticalAlignment(SwingConstants.BOTTOM);
        creatorLabel.setForeground(new Color(244, 244, 244));
        creatorLabel.setFont(new Font("Consolas", Font.PLAIN, 12));

        // TODO load version_control.txt automatically to Change Log
        JTextArea textArea = new JTextArea();
        textArea.setText(
                        "2020-26-12 : v0.8\n" +
                        "- implemented fully working panel changing based on user choice\n" +
                        "- reworked main frame layout (updated to card layout)\n" +
                        "- created own panel for hiding message\n" +
                        "- created own panel for text manipulation\n" +
                        "- replaced instructions JDialog with own coded window\n" +
                        "- added home navigation from the header panel\n" +
                        "\n" +
                        "2020-26-12 : v0.7\n" +
                        "- rewritten buttons action listeners\n" +
                        "- exception handled when no files chosen for compression\n" +
                        "- exception handled when no password entered for compression\n" +
                        "- exception handled when no picture chosen for metadata extraction\n" +
                        "- metadata output to separated window\n" +
                        "\n" +
                        "2020-25-12 : v0.6\n" +
                        "- removed all automatically generated gui forms\n" +
                        "- reworked UI logics\n" +
                        "- rewritten UI from the scratch\n" +
                        "- deep code refactoring\n" +
                        "\n" +
                        "2020-12-03 : v0.4\n" +
                        "- implemented console testing\n" +
                        "- extraction of files from a password protected zip\n" +
                        "- message shortening with synonym dictionary\n" +
                        "- file chooser + image chooser filter\n" +
                        "- github readme\n" +
                        "\n" +
                        "2020-11-17 : v0.2\n" +
                        "- metadata extraction\n" +
                        "- compression with encryption\n" +
                        "\n" +
                        "2020-11-14 : v0.1\n" +
                        "- gui set up\n" +
                        "\n" +
                        "2020-11-12\n" +
                        "- version control set up");

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
