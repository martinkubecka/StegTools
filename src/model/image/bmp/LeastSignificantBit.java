package model.image.bmp;

import model.explorer.FileChooser;

import java.io.File;
import java.util.List;

public class LeastSignificantBit {

    // TODO


    /**
     * Determine if a text file is present in the list of chosen files.
     * <p>
     * @param filesToHide  files chosen by user for LSB insertion
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
     * @param filesToHide  files chosen by user for LSB insertion
     * @return boolean value based on if more files were selected
     */
    public boolean areMoreFilesSelected(List<File> filesToHide) {

        return (filesToHide.size() != 1);
    }
}
