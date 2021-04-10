package view.menu;

import view.components.Label;

import javax.swing.*;
import java.awt.*;
import java.io.File;
import java.io.FileReader;
import java.net.URL;
import java.util.logging.Level;
import java.util.logging.Logger;

public class ChangeLogPanel extends JPanel {

    private final static Logger LOGGER = Logger.getLogger(Logger.GLOBAL_LOGGER_NAME);

    private Label changeLogLabel;
    private JTextArea textArea;
    private JScrollPane jScrollPane;

    public ChangeLogPanel() {

        changeLogLabel = new Label("Change Log:", SwingConstants.LEFT,SwingConstants.BOTTOM, Font.PLAIN, 20);

        textArea = new JTextArea();

        try {

            ClassLoader classLoader = getClass().getClassLoader();
            File textFile = new File(classLoader.getResource("resources/version_control.txt").getFile());

            FileReader reader = new FileReader(textFile);
            textArea.read(reader, textFile);

        } catch (Exception exception) {

            LOGGER.log(Level.SEVERE, exception.toString(), exception);
        }

        textArea.setLineWrap(true);
        textArea.setWrapStyleWord(true);
        //textArea.setBackground(new Color());
        textArea.setBorder(BorderFactory.createBevelBorder(1));
        textArea.setForeground(Color.BLACK);
        textArea.setFont(new Font("Source Code Pro", Font.PLAIN, 14));
        textArea.setEditable(false);

        jScrollPane = new JScrollPane(textArea);
        jScrollPane.setPreferredSize(new Dimension(500, 300));
        jScrollPane.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);

        setUpLayout();
        setUpPanel();
    }

    /**
     * Sets panel layout, background color and border.
     */
    private void setUpLayout() {

        this.setLayout(new BorderLayout());
        this.setBackground(new Color(72, 0, 0));
        this.setBorder(BorderFactory.createEmptyBorder(16, 16, 16, 16));
    }
    /**
     * Appends the specified components to the panel.
     */
    private void setUpPanel() {

        this.add(changeLogLabel, BorderLayout.NORTH);
        this.add(jScrollPane, BorderLayout.CENTER);
    }

}
