package model.explorer.filters;

import model.explorer.FileChooser;
import model.explorer.FileFormats;

import javax.swing.filechooser.FileFilter;
import java.io.File;

public class PNGBMPChooserFilter extends FileFilter {

    /**
     * Validating if provided file is an image file.
     * <p>
     * @param file  chosen file from FileChooser
     * @return boolean value based on if provided file is an image
     */
    @Override
    public boolean accept(File file) {

        if (file.isDirectory()) {
            return true;
        }

        String extension = FileChooser.getExtension(file);

        if (extension != null) {

            return extension.equals(FileFormats.BMP.getFormat()) ||
                    extension.equals(FileFormats.PNG.getFormat());
        }

        return false;
    }

    /**
     * Return description for FileChooser with only image filter
     * <p>
     * @return FileChooser "File of Type" field string
     */
    @Override
    public String getDescription() {
        return "*.png, *.bmp";
    }
}
