package view.tools.image;

import controller.AppController;
import view.components.Button;

import javax.swing.*;
import javax.swing.border.LineBorder;
import java.awt.*;

public class ExtractedDataWindow extends JFrame {

    private String extractedMessage;
    private AppController baseController;

    private JPanel panel;
    private JPanel footer;
    private JTextArea textArea;
    private JScrollPane scrollPane;
    private Button getASCIIButton;

    public ExtractedDataWindow(String title, AppController baseController, String extractedMessage) {
        super(title);

        this.baseController = baseController;
        this.extractedMessage = extractedMessage;

        setUpFrame();
        setUpPanel();
        setUpListeners();
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
        panel.setLayout(new BorderLayout());

        footer = new JPanel();
        footer.setLayout(new FlowLayout());
        footer.setBackground(new Color(72, 0, 0));
        footer.setBorder(new LineBorder(Color.WHITE, 1));

        textArea = new JTextArea();
        textArea.setLineWrap(true);
        textArea.setWrapStyleWord(true);
        textArea.setBorder(BorderFactory.createBevelBorder(1));
        textArea.setForeground(Color.BLACK);
        textArea.setFont(new Font("Source Code Pro", Font.PLAIN, 14));
        textArea.setEditable(false);

        textArea.append(extractedMessage + "\n");

        scrollPane = new JScrollPane(textArea);
        scrollPane.setPreferredSize(new Dimension(500, 300));
        scrollPane.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);

        getASCIIButton = new Button("Convert to ASCII");

        footer.add(getASCIIButton);

        panel.add(scrollPane, BorderLayout.CENTER);
        panel.add(footer, BorderLayout.PAGE_END);
        this.add(panel);
    }

    /**
     * Adds ActionListener to the panel buttons an carries out minimal logic.
     */
    private void setUpListeners() {

        getASCIIButton.addActionListener(e -> {

            textArea.setText(baseController.getAppendedData().hexToAscii(extractedMessage));
        });
    }

}
