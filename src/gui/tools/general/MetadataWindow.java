package gui.tools.general;

import com.drew.metadata.Directory;
import com.drew.metadata.Metadata;
import com.drew.metadata.Tag;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

public class MetadataWindow extends JFrame {

    public MetadataWindow(String title, Metadata metadata) {
        super(title);

        // METADATA WINDOW DIMENSIONS
        Dimension dimension = Toolkit.getDefaultToolkit().getScreenSize();
        int width = (int) (dimension.width * 0.25);
        int height = (int) (dimension.width * 0.25);
        this.setSize(width, height);
        int x = (int) ((dimension.getWidth() - this.getWidth()) / 2);
        int y = (int) ((dimension.getHeight() - this.getHeight()) / 2);

        // METADATA WINDOW LOCATION
        this.setLocation(x, y);

        JPanel panel = new JPanel();
        panel.setLayout(new GridLayout(1, 1, 8, 8));

        JTextArea textArea = new JTextArea();
        textArea.setLineWrap(true);
        textArea.setWrapStyleWord(true);
        textArea.setBorder(BorderFactory.createBevelBorder(1));
        textArea.setForeground(Color.BLACK);
        textArea.setFont(new Font("Source Code Pro", Font.PLAIN, 14));
        textArea.setEditable(false);

        // EXTRACT strings from METADATA object
        ArrayList<String> metadataParsed = new ArrayList<>();
        metadataParsed = parseMetadata(metadata);

        for (String data : metadataParsed){

            textArea.append(data + "\n");
        }

        JScrollPane scrollPane = new JScrollPane(textArea);
        scrollPane.setPreferredSize(new Dimension(500, 300));
        scrollPane.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);

        panel.add(scrollPane);
        this.add(panel);
    }

    private ArrayList<String> parseMetadata(Metadata metadata) {

        ArrayList<String> metadataParsed = new ArrayList<>();

        // A Metadata object contains multiple Directory objects
        for (Directory directory : metadata.getDirectories()) {

            // Each Directory stores values in Tag objects
            for (Tag tag : directory.getTags()) {
                metadataParsed.add(tag.toString());
                //System.out.println(tag.toString());
            }

            // Each Directory may also contain error messages
            for (String error : directory.getErrors()) {
                //System.err.println("ERROR: " + error);
                metadataParsed.add("ERROR " + error);
            }
        }

        return metadataParsed;
    }
}
