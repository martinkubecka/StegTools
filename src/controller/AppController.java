package controller;

import model.Compression;
import model.Dictionary;
import model.ExtractMetadata;
import model.explorer.FileChooser;
import model.png.ImageHeader;
import view.MainFrame;

import javax.swing.*;

public class AppController {

    private MainFrame frame;
    private Compression compression;
    private Dictionary dictionary;
    private ExtractMetadata extractMetadata;
    private FileChooser fileChooser;
    private ImageHeader imageHeader;

    public AppController() {

        compression = new Compression();
        dictionary = new Dictionary();
        extractMetadata = new ExtractMetadata();
        fileChooser = new FileChooser();
        imageHeader = new ImageHeader();
    }

    /**
     * Initialize the main application Frame
     */
    public void start() {

        try {
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        } catch (UnsupportedLookAndFeelException e) {
            UIManager.getCrossPlatformLookAndFeelClassName();
        } catch (ClassNotFoundException | InstantiationException | IllegalAccessException e) {
            e.printStackTrace();
        }

        frame = new MainFrame(this);
    }

    public Compression getCompression() {
        return compression;
    }

    public Dictionary getDictionary() {
        return dictionary;
    }

    public ExtractMetadata getExtractMetadata() {
        return extractMetadata;
    }

    public FileChooser getFileChooser() {
        return fileChooser;
    }

    public ImageHeader getImageHeader() {
        return imageHeader;
    }
}
