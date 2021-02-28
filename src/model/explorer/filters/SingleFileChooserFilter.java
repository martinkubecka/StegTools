package model.explorer.filters;

import model.explorer.FileChooser;

import javax.swing.filechooser.FileFilter;
import java.io.File;

public class SingleFileChooserFilter extends FileFilter {

    private String fileFormat;

    public SingleFileChooserFilter(String fileFormat) {

        this.fileFormat = fileFormat;
    }

    @Override
    public boolean accept(File f) {
        if (f.isDirectory()) {
            return true;
        }

        String extension = FileChooser.getExtension(f);
        if (extension != null) {
            return extension.equals(fileFormat);
        }

        return false;
    }

    @Override
    public String getDescription() {
        return "." + fileFormat;
    }

}
