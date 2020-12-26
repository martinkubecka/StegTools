import gui.menu.ChangeLogPanel;
import gui.menu.HeaderPanel;
import gui.menu.InstructionsDialog;
import gui.tools.ToolsPanel;
import gui.tools.general.GeneralToolsPanel;
import gui.tools.text.TextToolsPanel;

import javax.swing.*;
import javax.swing.border.LineBorder;
import java.awt.*;

public class MainFrame extends JFrame {

    public MainFrame(String title) {
        super(title);

        // MAIN WINDOW DIMENSIONS
        Dimension dimension = Toolkit.getDefaultToolkit().getScreenSize();
        int width = (int) (dimension.width * 0.3);
        int height = (int) (dimension.width * 0.3);
        this.setSize(width, height);
        int x = (int) ((dimension.getWidth() - this.getWidth()) / 2);
        int y = (int) ((dimension.getHeight() - this.getHeight()) / 2);

        // MAIN WINDOW LOCATION
        this.setLocation(x, y);

        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // CONTAINER
        Container mainContainer = this.getContentPane();
        mainContainer.setLayout(new BorderLayout());

        JPanel headerPanel = new HeaderPanel();
        mainContainer.add(headerPanel, BorderLayout.NORTH);

        // CENTER PANEL
        JPanel bottomPanel = new ChangeLogPanel();
        //JPanel bottomPanel = new ToolsPanel();
        //JPanel bottomPanel = new GeneralToolsPanel();
        //JPanel bottomPanel = new TextToolsPanel();

        mainContainer.add(bottomPanel, BorderLayout.CENTER);
    }

    // TODO switching panels

    // TOP PANEL
    public JPanel topPanel(){

        // TOP PANEL
        JPanel mainMenuPanel = new JPanel();
        mainMenuPanel.setLayout(new FlowLayout(FlowLayout.LEFT, 3, 3));
        mainMenuPanel.setBackground(new Color(72, 0, 0));
        mainMenuPanel.setBorder(new LineBorder(Color.WHITE, 3));

        JButton toolsButton = new JButton("Tools");
        toolsButton.setForeground(Color.BLACK);
        toolsButton.setFont(new Font("Source Code Pro", Font.BOLD, 14));
        toolsButton.setBackground(new Color(60, 63, 65));

        toolsButton.addActionListener(e -> {
            // TODO Tools Button Action
        });

        JButton helpButton = new JButton("Help");
        helpButton.setForeground(Color.BLACK);
        helpButton.setFont(new Font("Source Code Pro", Font.BOLD, 14));
        helpButton.setBackground(new Color(60, 63, 65));

        helpButton.addActionListener(e -> {

            JDialog instructionsDialog = new InstructionsDialog("Instructions");
            instructionsDialog.getContentPane().setBackground(new Color(72, 0, 0));

            Dimension dimension1 = Toolkit.getDefaultToolkit().getScreenSize();
            int width1 = (int) (dimension1.width * 0.25);
            int height1 = (int) (dimension1.width * 0.25);
            instructionsDialog.setSize(width1, height1);
            int x1 = (int) ((dimension1.getWidth() - instructionsDialog.getWidth()) / 2);
            int y1 = (int) ((dimension1.getHeight() - instructionsDialog.getHeight()) / 2);
            instructionsDialog.setLocation(x1, y1);

            instructionsDialog.setVisible(true);
        });

        JButton exitButton = new JButton("Exit");
        exitButton.setForeground(Color.WHITE);
        exitButton.setFont(new Font("Source Code Pro", Font.BOLD, 14));
        exitButton.setBackground(new Color(60, 63, 65));

        mainMenuPanel.add(toolsButton);
        mainMenuPanel.add(helpButton);
        //mainMenuPanel.add(exitButton);

        return mainMenuPanel;
    }

    // TEXT TOOLS PANEL
    public JPanel textToolsPanel() {

        JPanel textToolsPanel = new JPanel();
        textToolsPanel.setLayout(new GridLayout(6, 1, 8, 35));
        textToolsPanel.setBackground(new Color(72, 0, 0));
        textToolsPanel.setBorder(BorderFactory.createEmptyBorder(8, 120, 35, 120));

        JLabel nameLabel = new JLabel("Text Tools", SwingConstants.CENTER);
        nameLabel.setVerticalAlignment(SwingConstants.CENTER);
        nameLabel.setForeground(new Color(244, 244, 244));
        nameLabel.setFont(new Font("Consolas", Font.PLAIN, 36));

        JButton messageShorteningButton = new JButton("Message Shortening");
        messageShorteningButton.setForeground(Color.BLACK);
        messageShorteningButton.setFont(new Font("Source Code Pro", Font.BOLD, 14));
        messageShorteningButton.setBackground(new Color(60, 63, 65));

        JLabel compressionResultLabel = new JLabel("Message Shortening Result", SwingConstants.CENTER);
        compressionResultLabel.setVerticalAlignment(SwingConstants.TOP);
        compressionResultLabel.setForeground(new Color(244, 244, 244));
        compressionResultLabel.setFont(new Font("Consolas", Font.PLAIN, 15));
        compressionResultLabel.setVisible(false);

        JLabel zeroWidthLabel = new JLabel("Zero-Width Characters", SwingConstants.CENTER);
        zeroWidthLabel.setVerticalAlignment(SwingConstants.CENTER);
        zeroWidthLabel.setForeground(new Color(244, 244, 244));
        zeroWidthLabel.setFont(new Font("Consolas", Font.PLAIN, 28));

        JButton multipleToolsZeroWidthButton = new JButton("Detect-Extract-Remove");
        multipleToolsZeroWidthButton.setForeground(Color.BLACK);
        multipleToolsZeroWidthButton.setFont(new Font("Source Code Pro", Font.BOLD, 14));
        multipleToolsZeroWidthButton.setBackground(new Color(60, 63, 65));

        JButton hideMessageButton = new JButton("Hide Message");
        hideMessageButton.setForeground(Color.BLACK);
        hideMessageButton.setFont(new Font("Source Code Pro", Font.BOLD, 14));
        hideMessageButton.setBackground(new Color(60, 63, 65));

        textToolsPanel.add(nameLabel);
        textToolsPanel.add(messageShorteningButton);
        textToolsPanel.add(compressionResultLabel);
        textToolsPanel.add(zeroWidthLabel);
        textToolsPanel.add(multipleToolsZeroWidthButton);
        textToolsPanel.add(hideMessageButton);

        return textToolsPanel;
    }

    // GENERAL TOOLS PANEL
    public JPanel generalToolsPanel() {

        JPanel generalToolsPanel = new JPanel();
        generalToolsPanel.setLayout(new GridLayout(4, 1, 8, 100));
        generalToolsPanel.setBackground(new Color(72, 0, 0));
        generalToolsPanel.setBorder(BorderFactory.createEmptyBorder(8, 150, 8, 150));

        JLabel nameLabel = new JLabel("General Tools", SwingConstants.CENTER);
        nameLabel.setVerticalAlignment(SwingConstants.CENTER);
        nameLabel.setForeground(new Color(244, 244, 244));
        nameLabel.setFont(new Font("Consolas", Font.PLAIN, 36));

        JButton showMetadataButton = new JButton("Show Metadata");
        showMetadataButton.setForeground(Color.BLACK);
        showMetadataButton.setFont(new Font("Source Code Pro", Font.BOLD, 14));
        showMetadataButton.setBackground(new Color(60, 63, 65));

        JButton compressFilesButton = new JButton("Compress Files");
        compressFilesButton.setForeground(Color.BLACK);
        compressFilesButton.setFont(new Font("Source Code Pro", Font.BOLD, 14));
        compressFilesButton.setBackground(new Color(60, 63, 65));

        JLabel compressionResultLabel = new JLabel("Compression Result", SwingConstants.CENTER);
        compressionResultLabel.setVerticalAlignment(SwingConstants.TOP);
        compressionResultLabel.setForeground(new Color(244, 244, 244));
        compressionResultLabel.setFont(new Font("Consolas", Font.PLAIN, 15));
        compressionResultLabel.setVisible(false);

        generalToolsPanel.add(nameLabel);
        generalToolsPanel.add(showMetadataButton);
        generalToolsPanel.add(compressFilesButton);
        generalToolsPanel.add(compressionResultLabel);

        return generalToolsPanel;
    }

    // CHANGE LOG PANEL
    public JPanel changeLogPanel() {

        JPanel changeLogPanel = new JPanel();
        changeLogPanel.setLayout(new GridLayout(4, 1, 8, 8));
        changeLogPanel.setBackground(new Color(72, 0, 0));
        changeLogPanel.setBorder(BorderFactory.createEmptyBorder(8, 16, 8, 16));

        JLabel nameLabel = new JLabel("StegTools", SwingConstants.CENTER);
        nameLabel.setVerticalAlignment(SwingConstants.CENTER);
        nameLabel.setForeground(new Color(244, 244, 244));
        nameLabel.setFont(new Font("Consolas", Font.PLAIN, 36));

        JLabel changeLogLabel = new JLabel("Change Log:", SwingConstants.LEFT);
        changeLogLabel.setVerticalAlignment(SwingConstants.BOTTOM);
        changeLogLabel.setForeground(new Color(244, 244, 244));
        changeLogLabel.setFont(new Font("Consolas", Font.PLAIN, 20));

        JLabel creatorLabel = new JLabel("Martin Kubecka, 2020, v0.4", SwingConstants.RIGHT);
        creatorLabel.setVerticalAlignment(SwingConstants.BOTTOM);
        creatorLabel.setForeground(new Color(244, 244, 244));
        creatorLabel.setFont(new Font("Consolas", Font.PLAIN, 12));

        JTextArea textArea = new JTextArea();
        textArea.setText("2020-12-03 : v0.4\n" +
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
                "- version control set up\n");

        //textArea.setBounds(50, 50, 300, 300);
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

        changeLogPanel.add(nameLabel);
        changeLogPanel.add(changeLogLabel);
        changeLogPanel.add(scrollPane);
        changeLogPanel.add(creatorLabel);

        return changeLogPanel;
    }

    // TOOLS MENU PANEL
    public JPanel toolsMenuPanel() {

        JPanel toolsMenuPanel = new JPanel();
        toolsMenuPanel.setBackground(new Color(72, 0, 0));
        toolsMenuPanel.setBorder(BorderFactory.createEmptyBorder(8, 8, 16, 16));
        toolsMenuPanel.setLayout(new GridLayout(3, 1));

        // HEADER LABEL
        JLabel toolsMenuLabel = new JLabel("Tools Menu", SwingConstants.CENTER);
        toolsMenuLabel.setVerticalAlignment(SwingConstants.CENTER);
        toolsMenuLabel.setForeground(new Color(244, 244, 244));
        toolsMenuLabel.setFont(new Font("Consolas", Font.PLAIN, 36));

        // PANEL FOR BUTTONS
        JPanel toolsButtonsPanel = new JPanel();
        toolsButtonsPanel.setBackground(new Color(72, 0, 0));
        toolsButtonsPanel.setLayout(new GridLayout(2, 2, 5, 5));

        JButton generalToolsButton = new JButton("General");
        generalToolsButton.setForeground(Color.BLACK);
        generalToolsButton.setFont(new Font("Source Code Pro", Font.BOLD, 14));
        generalToolsButton.setBackground(new Color(60, 63, 65));

        JButton textToolsButton = new JButton("Text");
        textToolsButton.setForeground(Color.BLACK);
        textToolsButton.setFont(new Font("Source Code Pro", Font.BOLD, 14));
        textToolsButton.setBackground(new Color(60, 63, 65));

        JButton pngToolsButton = new JButton("PNG");
        pngToolsButton.setForeground(Color.BLACK);
        pngToolsButton.setFont(new Font("Source Code Pro", Font.BOLD, 14));
        pngToolsButton.setBackground(new Color(60, 63, 65));

        JButton bmpToolsButton = new JButton("BMP");
        bmpToolsButton.setForeground(Color.BLACK);
        bmpToolsButton.setFont(new Font("Source Code Pro", Font.BOLD, 14));
        bmpToolsButton.setBackground(new Color(60, 63, 65));

        toolsButtonsPanel.add(generalToolsButton);
        toolsButtonsPanel.add(textToolsButton);
        toolsButtonsPanel.add(pngToolsButton);
        toolsButtonsPanel.add(bmpToolsButton);

        toolsMenuPanel.add(toolsMenuLabel, BorderLayout.NORTH);
        toolsMenuPanel.add(toolsButtonsPanel, BorderLayout.CENTER);

        return toolsMenuPanel;
    }

}
