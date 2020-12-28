package process.explorer.filters;

import process.explorer.FileFormats;

import javax.swing.filechooser.FileFilter;
import java.io.File;

public class TextChooserFilter extends FileFilter {

    @Override
    public boolean accept(File f) {
        if (f.isDirectory()) {
            return true;
        }

        String extension = getExtension(f);
        if (extension != null) {
            return extension.equals(FileFormats.TXT.getFormat());
        }

        return false;
    }

    @Override
    public String getDescription() {
        return ".txt";
    }

    String getExtension(File f) {
        String ext = null;
        String s = f.getName();
        int i = s.lastIndexOf('.');

        if (i > 0 && i < s.length() - 1) {
            ext = s.substring(i + 1).toLowerCase();
        }
        return ext;
    }

}
