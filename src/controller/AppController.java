package controller;

import log.LogError;
import model.general.Compression;
import model.general.Dictionary;
import model.general.ExtractMetadata;
import model.image.bmp.LeastSignificantBit;
import model.image.general.BitPlaneSlicing;
import model.explorer.FileChooser;
import model.image.png.ImageHeader;
import view.MainFrame;

import javax.swing.*;

public class AppController {

    private LogError LOGGER;

    private MainFrame frame;
    private Compression compression;
    private Dictionary dictionary;
    private ExtractMetadata extractMetadata;
    private FileChooser fileChooser;
    private ImageHeader imageHeader;
    private BitPlaneSlicing bitPlaneSlicing;
    private LeastSignificantBit leastSignificantBit;

    public AppController() {

        compression = new Compression();
        dictionary = new Dictionary();
        extractMetadata = new ExtractMetadata();
        fileChooser = new FileChooser();
        imageHeader = new ImageHeader();
        bitPlaneSlicing = new BitPlaneSlicing();
        leastSignificantBit = new LeastSignificantBit();
    }

    /**
     * Initialize the main application Frame and Logger
     */
    public void start() {

        try {
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        } catch (UnsupportedLookAndFeelException e) {
            UIManager.getCrossPlatformLookAndFeelClassName();
        } catch (ClassNotFoundException | InstantiationException | IllegalAccessException e) {
            e.printStackTrace();
        }

        //LOGGER = new LogError();
        frame = new MainFrame(this);
    }

    /**
     * Access Compression class methods
     *
     * @return Compression class
     */
    public Compression getCompression() {
        return compression;
    }

    /**
     * Access Dictionary class methods
     *
     * @return Dictionary class
     */
    public Dictionary getDictionary() {
        return dictionary;
    }

    /**
     * Access ExtractMetadata class methods
     *
     * @return ExtractMetadata class
     */
    public ExtractMetadata getExtractMetadata() {
        return extractMetadata;
    }

    /**
     * Access FileChooser class methods
     *
     * @return FileChooser class
     */
    public FileChooser getFileChooser() {
        return fileChooser;
    }

    /**
     * Access ImageHeader class methods
     *
     * @return ImageHeader class
     */
    public ImageHeader getImageHeader() {
        return imageHeader;
    }

    /**
     * Access BitPlaneSlicing class methods
     *
     * @return BitPlaneSlicing class
     */
    public BitPlaneSlicing getBitPlaneSlicing() {
        return bitPlaneSlicing;
    }

    /**
     * Access LeastSignificantBit class methods
     *
     * @return LeastSignificantBit class
     */
    public LeastSignificantBit getLeastSignificantBit() {
        return leastSignificantBit;
    }

//    public MainFrame getFrame() {
//        return frame;
//    }
}
