package model.explorer;

public enum FileFormats {

    JPEG("jpeg"),
    JPG("jpg"),
    GIF("gif"),
    TIFF("tiff"),
    TIF("tif"),
    PNG("png"),
    ZIP("zip"),
    TXT("txt");

    private String format;

    FileFormats(String fileFormat) {

        this.format = fileFormat;
    }

    public String getFormat() {
        return format;
    }
}
