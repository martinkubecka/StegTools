package gui.tools.text;

import javax.swing.*;
import javax.swing.border.EtchedBorder;
import java.awt.*;

public class HideMessagePanel extends JPanel {

    public HideMessagePanel() {

        this.setLayout(new GridLayout(6, 1, 2, 2));
        this.setBackground(new Color(72, 0, 0));
        this.setBorder(BorderFactory.createEmptyBorder(2, 16, 2, 16));

        JLabel publicLabel = new JLabel("Public Message", SwingConstants.LEFT);
        publicLabel.setVerticalAlignment(SwingConstants.BOTTOM);
        publicLabel.setForeground(new Color(244, 244, 244));
        publicLabel.setFont(new Font("Consolas", Font.PLAIN, 20));

        JTextArea textAreaPublic = new JTextArea();
        textAreaPublic.setLineWrap(true);
        textAreaPublic.setWrapStyleWord(true);
        textAreaPublic.setBorder(BorderFactory.createBevelBorder(1));
        textAreaPublic.setForeground(Color.BLACK);
        textAreaPublic.setFont(new Font("Source Code Pro", Font.PLAIN, 14));

        JScrollPane scrollPanePublic = new JScrollPane(textAreaPublic);
        scrollPanePublic.setPreferredSize(new Dimension(500, 300));
        scrollPanePublic.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);

        JLabel privateLabel = new JLabel("Private Message", SwingConstants.LEFT);
        privateLabel.setVerticalAlignment(SwingConstants.BOTTOM);
        privateLabel.setForeground(new Color(244, 244, 244));
        privateLabel.setFont(new Font("Consolas", Font.PLAIN, 20));

        JTextArea textAreaPrivate = new JTextArea();
        textAreaPrivate.setLineWrap(true);
        textAreaPrivate.setWrapStyleWord(true);
        textAreaPrivate.setBorder(BorderFactory.createBevelBorder(1));
        textAreaPrivate.setForeground(Color.BLACK);
        textAreaPrivate.setFont(new Font("Source Code Pro", Font.PLAIN, 14));

        JScrollPane scrollPanePrivate = new JScrollPane(textAreaPrivate);
        scrollPanePrivate.setPreferredSize(new Dimension(500, 300));
        scrollPanePrivate.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);

        JButton hideMessageButton = new JButton("Hide");
        hideMessageButton.setForeground(Color.BLACK);
        hideMessageButton.setFont(new Font("Source Code Pro", Font.BOLD, 14));
        hideMessageButton.setBackground(new Color(60, 63, 65));
        hideMessageButton.setFocusable(false);
        hideMessageButton.addActionListener(e -> {
            // TODO Hide Message Button Action
        });

        JTextArea textAreaHidden = new JTextArea();
        textAreaHidden.setLineWrap(true);
        textAreaHidden.setWrapStyleWord(true);
        textAreaHidden.setBorder(
                BorderFactory.createCompoundBorder(
                        BorderFactory.createEtchedBorder(EtchedBorder.RAISED),
                        BorderFactory.createEtchedBorder(EtchedBorder.LOWERED)));
        textAreaHidden.setForeground(Color.BLACK);
        textAreaHidden.setFont(new Font("Source Code Pro", Font.PLAIN, 14));
        textAreaHidden.setEditable(false);

        JScrollPane scrollPaneHidden = new JScrollPane(textAreaHidden);
        scrollPaneHidden.setPreferredSize(new Dimension(500, 300));
        scrollPaneHidden.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);

        this.add(publicLabel);
        this.add(scrollPanePublic);
        this.add(privateLabel);
        this.add(scrollPanePrivate);
        this.add(hideMessageButton);
        this.add(scrollPaneHidden);
    }
}
