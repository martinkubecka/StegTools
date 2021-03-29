package model.explorer.filters;

import model.explorer.FileChooser;
import model.explorer.FileFormats;

import javax.swing.filechooser.FileFilter;
import java.io.File;

public class ImageChooserFilter extends FileFilter {

    /**
     * Validating if provided file is an image file
     *
     * @param f Chosen file from FileChooser
     * @return boolean value based on if provided file is an image
     */
    @Override
    public boolean accept(File f) {
        if (f.isDirectory()) {
            return true;
        }

        String extension = FileChooser.getExtension(f);
        if (extension != null) {
            return extension.equals(FileFormats.JPEG.getFormat()) ||
                    extension.equals(FileFormats.JPG.getFormat()) ||
                    extension.equals(FileFormats.GIF.getFormat()) ||
                    extension.equals(FileFormats.TIFF.getFormat()) ||
                    extension.equals(FileFormats.TIF.getFormat()) ||
                    extension.equals(FileFormats.BMP.getFormat()) ||
                    extension.equals(FileFormats.PNG.getFormat());
        }
        return false;
    }

    /**
     * Return description for FileChooser with only image filter
     *
     * @return FileChooser "File of Type" field string
     */
    @Override
    public String getDescription() {
        return "Image Only";
    }
}
