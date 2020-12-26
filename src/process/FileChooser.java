package process;

import javax.swing.*;
import java.io.File;

public class FileChooser {

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
            for(File file: files){
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
        fc.setFileSelectionMode(JFileChooser.FILES_ONLY);

        int returnVal = fc.showOpenDialog(null);

        if (returnVal == JFileChooser.APPROVE_OPTION) {
            return fc.getSelectedFile();
        }
        return null;
    }



}
