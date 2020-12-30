package process.explorer;

import process.explorer.filters.ImageChooserFilter;
import process.explorer.filters.PngChooserFilter;
import process.explorer.filters.TextChooserFilter;
import process.explorer.filters.ZipChooserFilter;

import javax.swing.*;
import java.io.File;

public class FileChooser {

    // TODO try to implement generalization

    public static File pickZipFromFileChooser() {

        JFileChooser fc = new JFileChooser();
        fc.setCurrentDirectory(new File("src/resources/"));
        fc.setDialogTitle("Select the file to open... ");
        fc.setMultiSelectionEnabled(false);
        fc.addChoosableFileFilter(new ZipChooserFilter());
        fc.setAcceptAllFileFilterUsed(false);

        int returnVal = fc.showOpenDialog(new JDialog());

        if (returnVal == JFileChooser.APPROVE_OPTION) {
            return fc.getSelectedFile();
        }
        return null;
    }

    public static File pickImageFromFileChooser() {

        JFileChooser fc = new JFileChooser();
        fc.setCurrentDirectory(new File("src/resources/"));
        fc.setDialogTitle("Select the file to open... ");
        fc.setMultiSelectionEnabled(false);
        // fc.setFileSelectionMode(JFileChooser.FILES_ONLY);
        fc.addChoosableFileFilter(new ImageChooserFilter());
        fc.setAcceptAllFileFilterUsed(false);

        int returnVal = fc.showOpenDialog(new JDialog());

        if (returnVal == JFileChooser.APPROVE_OPTION) {
            //File f = fc.getSelectedFile();
            return fc.getSelectedFile();
        }
        return null;
    }

    public static File[] pickMultipleFilesFromFileChooser() {

        JFileChooser fc = new JFileChooser();
        fc.setCurrentDirectory(new File("src/resources/"));
        fc.setDialogTitle("Select the file to open... ");
        fc.setMultiSelectionEnabled(true);
        // fc.setFileSelectionMode(JFileChooser.FILES_ONLY);
        // fc.addChoosableFileFilter(new ImageChooserFilter());
        fc.setAcceptAllFileFilterUsed(true);

        int returnVal = fc.showOpenDialog(new JDialog());

        if (returnVal == JFileChooser.APPROVE_OPTION) {

            File[] files = fc.getSelectedFiles();
            StringBuilder fileNames = new StringBuilder();
            for (File file : files) {
                fileNames.append(file.getName()).append(" ");
            }

            //File f = fc.getSelectedFile();
            return files;
        }
        return null;
    }

    public static File pickSingleTextFileFromFileChooser() {

        JFileChooser fc = new JFileChooser();
        fc.setCurrentDirectory(new File("src/resources/"));
        fc.setDialogTitle("Select the file to open... ");
        fc.setMultiSelectionEnabled(false);
        // fc.setFileSelectionMode(JFileChooser.FILES_ONLY);
        fc.addChoosableFileFilter(new TextChooserFilter());
        fc.setAcceptAllFileFilterUsed(false);

        int returnVal = fc.showOpenDialog(new JDialog());

        if (returnVal == JFileChooser.APPROVE_OPTION) {
            //File f = fc.getSelectedFile();
            return fc.getSelectedFile();
        }
        return null;
    }

    public static File pickPngFromFileChooser() {

        JFileChooser fc = new JFileChooser();
        fc.setCurrentDirectory(new File("src/resources/"));
        fc.setDialogTitle("Select the file to open... ");
        fc.setMultiSelectionEnabled(false);
        fc.addChoosableFileFilter(new PngChooserFilter());
        fc.setAcceptAllFileFilterUsed(false);

        int returnVal = fc.showOpenDialog(new JDialog());

        if (returnVal == JFileChooser.APPROVE_OPTION) {
            return fc.getSelectedFile();
        }
        return null;
    }

    public static String getExtension(File f) {

        String ext = null;
        String s = f.getName();
        int i = s.lastIndexOf('.');

        if (i > 0 &&  i < s.length() - 1) {
            ext = s.substring(i+1).toLowerCase();
        }
        return ext;
    }
}
