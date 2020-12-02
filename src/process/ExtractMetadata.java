package process;

import com.drew.imaging.ImageMetadataReader;
import com.drew.imaging.ImageProcessingException;
import com.drew.imaging.jpeg.JpegMetadataReader;
import com.drew.imaging.jpeg.JpegProcessingException;
import com.drew.imaging.jpeg.JpegSegmentMetadataReader;
import com.drew.metadata.Directory;
import com.drew.metadata.Metadata;
import com.drew.metadata.Tag;
import com.drew.metadata.exif.ExifReader;
import com.drew.metadata.iptc.IptcReader;

import java.io.File;
import java.io.IOException;
import java.util.Arrays;

public class ExtractMetadata {

    public static void extractMetadata(File file) {

       // File file = new File("E:\\Programming\\Java\\StegTools\\src\\cat.jpg");

        // APPROACH 3: SPECIFIC METADATA TYPE
        //
        // If you only wish to read a subset of the supported metadata types, you can do this by
        // passing the set of readers to use.
        //
        // This currently only applies to JPEG file processing.
        try {
            // We are only interested in handling
            Iterable<JpegSegmentMetadataReader> readers = Arrays.asList(new ExifReader(), new IptcReader());

            Metadata metadata = JpegMetadataReader.readMetadata(file, readers);

            print(metadata, "Using JpegMetadataReader for Exif and IPTC only");
        } catch (JpegProcessingException | IOException e) {
            print(e);
        }

        // SCENARIO 1: UNKNOWN FILE TYPE
        //
        // This is the most generic approach.  It will transparently determine the file type and invoke the appropriate
        // readers.  In most cases, this is the most appropriate usage.  This will handle JPEG, TIFF, GIF, BMP and RAW
        // (CRW/CR2/NEF/RW2/ORF) files and extract whatever metadata is available and understood.
//
//        try {
//            Metadata metadata = ImageMetadataReader.readMetadata(file);
//
//            System.out.println("------------------------- " + "UNKNOWN FILE TYPE" + "------------------------- ");
//            print(metadata, "Using ImageMetadataReader");
//        } catch (ImageProcessingException | IOException e) {
//            print(e);
//        }

        // SCENARIO 2: SPECIFIC FILE TYPE
        //
        // If you know the file to be a JPEG, you may invoke the JpegMetadataReader, rather than the generic reader
        // used in approach 1.  Similarly, if you knew the file to be a TIFF/RAW image you might use TiffMetadataReader,
        // PngMetadataReader for PNG files, BmpMetadataReader for BMP files, or GifMetadataReader for GIF files.
        //
        // Using the specific reader offers a very, very slight performance improvement.
        //
//        try {
//            Metadata metadata = JpegMetadataReader.readMetadata(file);
//
//            print(metadata, "Using JpegMetadataReader");
//        } catch (JpegProcessingException | IOException e) {
//            print(e);
//        }

//        try {
//
//            Metadata metadata = ImageMetadataReader.readMetadata(file);
//            for (Directory directory : metadata.getDirectories()) {
//                for (Tag tag : directory.getTags()) {
//                    System.out.format("[%s] - %s = %s",
//                            directory.getName(), tag.getTagName(), tag.getDescription());
//                    System.out.println("\n");
//                }
//                if (directory.hasErrors()) {
//                    for (String error : directory.getErrors()) {
//                        System.err.format("ERROR: %s", error);
//                    }
//                }
//            }
//
//        } catch (Exception e) {
//            System.out.println("Exception");
//        }
    }

    /**
     * Write all extracted values to stdout.
     */
    private static void print(Metadata metadata, String method) {

        System.out.println("\n--------------------------------------------------------------");
        System.out.print(">>    " + method + "\n\n");

        //
        // A Metadata object contains multiple Directory objects
        //
        for (Directory directory : metadata.getDirectories()) {

            //
            // Each Directory stores values in Tag objects
            //
            for (Tag tag : directory.getTags()) {
                System.out.println(tag);
            }

            //
            // Each Directory may also contain error messages
            //
            for (String error : directory.getErrors()) {
                System.err.println("ERROR: " + error);
            }
        }
    }

    private static void print(Exception exception) {
        System.err.println("EXCEPTION: " + exception);
    }

}
