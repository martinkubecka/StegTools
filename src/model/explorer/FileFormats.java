package model.explorer;

public enum FileFormats {

    JPEG("jpeg"),
    JPG("jpg"),
    GIF("gif"),
    TIFF("tiff"),
    TIF("tif"),
    PNG("png"),
    ZIP("zip"),
    BMP("bmp"),
    TXT("txt");

    /**
     * File format name.
     **/
    private String format;

    /**
     * Constructor.
     * <p>
     * @param fileFormat  file format name
     */
    FileFormats(String fileFormat) {

        this.format = fileFormat;
    }

    /**
     * Get a file format name.
     * <p>
     * @return file format name.
     */
    public String getFormat() {
        return format;
    }
}
