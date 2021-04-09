package model.image.bmp;

import model.explorer.FileChooser;
import model.general.Dictionary;

import java.io.File;
import java.util.List;

public class LeastSignificantBit {

    // TODO
    public void insertion(File file) {

        System.out.println("Inserting a file to a carrier ...");
    }

    /**
     * Iterates over a list of files and shortens present text file with synonym dictionary.
     * <p>
     * @param filesToHide  list of files with one or more text files
     * @return  list of files with shorten text files
     */
    public List<File> textFileShortening(List<File> filesToHide) {

        Dictionary dictionary = new Dictionary();

        for (File file : filesToHide) {

            if (FileChooser.getExtension(file).equals("txt")) {

                file = dictionary.applyMessageShortening(file, 2);
            }
        }

        return filesToHide;
    }

    /**
     * Determine if a text file is present in the list of chosen files.
     * <p>
     *
     * @param filesToHide files chosen by user for LSB insertion
     * @return boolean value based on if a text file is present
     */
    public boolean isTextFilePresent(List<File> filesToHide) {

        String textFile = "txt";
        String currentExt;
        int isPresent = 0;

        for (File file : filesToHide) {

            currentExt = FileChooser.getExtension(file);
            System.out.println(currentExt);

            if (currentExt.equals(textFile)) isPresent++;
        }

        return (isPresent > 0);
    }

    /**
     * Determine if more than one file was chosen.
     * <p>
     *
     * @param filesToHide files chosen by user for LSB insertion
     * @return boolean value based on if more files were selected
     */
    public boolean areMoreFilesSelected(List<File> filesToHide) {

        return (filesToHide.size() != 1);
    }
}
