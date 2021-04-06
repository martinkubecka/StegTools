package model.explorer.filters;

import model.explorer.FileChooser;

import javax.swing.filechooser.FileFilter;
import java.io.File;

public class SingleFileChooserFilter extends FileFilter {

    private String fileFormat;

    /**
     * Set file extension for FileChooser filter
     *
     * @param fileFormat defined file extension for FileChooser filter
     */
    public SingleFileChooserFilter(String fileFormat) {

        this.fileFormat = fileFormat;
    }

    /**
     * Validating if provided file is the same format as the defined file extension
     *
     * @param f Chosen file from FileChooser
     * @return boolean value based on if provided file acceptable
     */
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

    /**
     * Return description for FileChooser with custom filter
     *
     * @return FileChooser "File of Type" field string
     */
    @Override
    public String getDescription() {
        return "*." + fileFormat;
    }

}
