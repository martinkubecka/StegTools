package process.explorer.filters;

import process.explorer.FileChooser;
import process.explorer.FileFormats;

import javax.swing.filechooser.FileFilter;
import java.io.File;

public class ZipChooserFilter extends FileFilter {

    @Override
    public boolean accept(File f) {
        if (f.isDirectory()) {
            return true;
        }

        String extension = FileChooser.getExtension(f);
        if (extension != null) {
            return extension.equals(FileFormats.ZIP.getFormat());
        }
        return false;
    }

    @Override
    public String getDescription() {
        return ".zip";
    }
}