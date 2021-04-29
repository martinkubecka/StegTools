package model.explorer;

import model.explorer.filters.*;

import javax.swing.*;
import java.io.File;

public class FileChooser {

    /**
     * Initialize a file chooser with a filter based on the provided String.
     * <p>
     *
     * @param fileFormat file format name used to choose a file filter
     * @return selected file from the file chooser
     */
    public File pickSingleFileChooser(String fileFormat) {

        JFileChooser fc = new JFileChooser();
        fc.setCurrentDirectory(new File("src/resources/"));
        fc.setDialogTitle("Select the file to open... ");
        fc.setMultiSelectionEnabled(false);

        switch (fileFormat) {
            case "txt" -> fc.addChoosableFileFilter(new SingleFileChooserFilter("txt"));
            case "zip" -> fc.addChoosableFileFilter(new SingleFileChooserFilter("zip"));
            case "png" -> fc.addChoosableFileFilter(new SingleFileChooserFilter("png"));
            case "bmp" -> fc.addChoosableFileFilter(new SingleFileChooserFilter("bmp"));
            case "images" -> fc.addChoosableFileFilter(new ImageChooserFilter());
            case "png&bmp" -> fc.addChoosableFileFilter(new PNGBMPChooserFilter());
        }

        fc.setAcceptAllFileFilterUsed(false);

        int returnVal = fc.showOpenDialog(new JDialog());

        if (returnVal == JFileChooser.APPROVE_OPTION) {

            return fc.getSelectedFile();
        }
        return null;
    }

    /**
     * Initialize a file chooser without any filters.
     * <p>
     *
     * @return selected files from the file chooser
     */
    public File[] pickMultipleFilesFromFileChooser() {

        JFileChooser fc = new JFileChooser();
        fc.setCurrentDirectory(new File("src/resources/"));
        fc.setDialogTitle("Select the file to open... ");
        fc.setMultiSelectionEnabled(true);
        fc.setAcceptAllFileFilterUsed(true);

        int returnVal = fc.showOpenDialog(new JDialog());

        if (returnVal == JFileChooser.APPROVE_OPTION) {

            File[] files = fc.getSelectedFiles();
            StringBuilder fileNames = new StringBuilder();

            for (File file : files) {

                fileNames.append(file.getName()).append(" ");
            }

            return files;
        }
        return null;
    }

    /**
     * Get a file extension from the name of the provided file.
     * <p>
     *
     * @param file chosen file from the file chooser
     * @return file extension without the dot
     */
    public static String getExtension(File file) {

        String extension = null;
        String s = file.getName();
        int i = s.lastIndexOf('.');

        if (i > 0 && i < s.length() - 1) {

            extension = s.substring(i + 1).toLowerCase();
        }
        return extension;
    }
}
