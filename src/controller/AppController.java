package controller;

import model.general.Compression;
import model.general.Dictionary;
import model.general.ExtractMetadata;
import model.image.bmp.BitPlaneSlicing;
import model.explorer.FileChooser;
import model.image.png.ImageHeader;
import view.MainFrame;

import javax.swing.*;

public class AppController {

    private MainFrame frame;
    private Compression compression;
    private Dictionary dictionary;
    private ExtractMetadata extractMetadata;
    private FileChooser fileChooser;
    private ImageHeader imageHeader;
    private BitPlaneSlicing bitPlaneSlicing2;

    public AppController() {

        compression = new Compression();
        dictionary = new Dictionary();
        extractMetadata = new ExtractMetadata();
        fileChooser = new FileChooser();
        imageHeader = new ImageHeader();
        bitPlaneSlicing2 = new BitPlaneSlicing();
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

    public BitPlaneSlicing getBitPlaneSlicing() {
        return bitPlaneSlicing2;
    }

    public MainFrame getFrame() {
        return frame;
    }
}
