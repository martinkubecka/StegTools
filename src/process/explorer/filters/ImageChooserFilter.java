package process.explorer.filters;

import process.explorer.FileChooser;
import process.explorer.FileFormats;

import javax.swing.filechooser.FileFilter;
import java.io.File;

public class ImageChooserFilter extends FileFilter {

    @Override
    public boolean accept(File f) {
        if (f.isDirectory()) {
            return true;
        }

        String extension = FileChooser.getExtension(f);
        if (extension != null) {
            if (extension.equals(FileFormats.JPEG.getFormat()) ||
                    extension.equals(FileFormats.JPG.getFormat()) ||
                    extension.equals(FileFormats.GIF.getFormat()) ||
                    extension.equals(FileFormats.TIFF.getFormat()) ||
                    extension.equals(FileFormats.TIF.getFormat()) ||
                    extension.equals(FileFormats.PNG.getFormat())) {
                return true;
            } else {
                return false;
            }
        }
        return false;
    }

    @Override
    public String getDescription() {
        return "Image Only";
    }
}
