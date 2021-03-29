package model.image.bmp;

import model.explorer.FileChooser;

import java.io.File;
import java.util.List;

public class LeastSignificantBit {

    // TODO


    public boolean isTextFilePresent(List<File> filesToHide) {

        String textFile = "txt";
        String currentExt = null;
        int isPresent = 0;

        for (File file : filesToHide) {

            currentExt = FileChooser.getExtension(file);
            System.out.println(currentExt);

            if (currentExt.equals(textFile)) isPresent++;
        }

        return isPresent > 0;
    }
}
