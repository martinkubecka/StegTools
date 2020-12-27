package process.explorer;

import javax.swing.filechooser.FileFilter;
import java.io.File;

public class ZipChooserFilter extends FileFilter {
    public final static String ZIP = "zip";

    @Override
    public boolean accept(File f) {
        if (f.isDirectory()) {
            return true;
        }

        String extension = getExtension(f);
        if (extension != null) {
            return extension.equals(ZIP);
        }
        return false;
    }

    @Override
    public String getDescription() {
        return "Zip Only";
    }

    String getExtension(File f) {
        String ext = null;
        String s = f.getName();
        int i = s.lastIndexOf('.');

        if (i > 0 &&  i < s.length() - 1) {
            ext = s.substring(i+1).toLowerCase();
        }
        return ext;
    }
}
