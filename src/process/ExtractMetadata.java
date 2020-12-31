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
import process.explorer.FileChooser;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;

public class ExtractMetadata {

    // TODO handle exception : File format could not be determined

    public static Metadata extractMetadata(File file) {

        String fileFormat = FileChooser.getExtension(file);
        Metadata metadata = null;

        // SPECIFIC METADATA TYPE
        //
        // If you only wish to read a subset of the supported metadata types, you can do this by
        // passing the set of readers to use.
        //
        // This currently only applies to JPEG file processing.

        if (fileFormat.equals("jpg")) {

            try {
                // We are only interested in handling
                Iterable<JpegSegmentMetadataReader> readers = Arrays.asList(new ExifReader(), new IptcReader());

                metadata = JpegMetadataReader.readMetadata(file, readers);

                //print(metadata, "Using JpegMetadataReader for Exif and IPTC only");
                return metadata;

            } catch (JpegProcessingException | IOException e) {
                print(e);
            }

            // UNKNOWN FILE TYPE
            //
            // This is the most generic approach.
            // It will transparently determine the file type and invoke the appropriate readers.
            // In most cases, this is the most appropriate usage.
            // This will handle JPEG, TIFF, GIF, BMP and RAW
            // (CRW/CR2/NEF/RW2/ORF) files and extract whatever metadata is available and understood.

        } else {
            try {
                metadata = ImageMetadataReader.readMetadata(file);

//                print(metadata, "Using ImageMetadataReader");
                return metadata;

            } catch (ImageProcessingException | IOException e) {
                print(e);
            }
        }

        return null;
    }

    public static ArrayList<String> parseMetadata(Metadata metadata) {

        ArrayList<String> metadataParsed = new ArrayList<>();

        // A Metadata object contains multiple Directory objects
        for (Directory directory : metadata.getDirectories()) {

            // Each Directory stores values in Tag objects
            for (Tag tag : directory.getTags()) {
                metadataParsed.add(tag.toString());
                //System.out.println(tag.toString());
            }

            // Each Directory may also contain error messages
            for (String error : directory.getErrors()) {
                //System.err.println("ERROR: " + error);
                metadataParsed.add("ERROR " + error);
            }
        }

        return metadataParsed;
    }

    // Write all extracted values to stdout.
    private static void print(Metadata metadata, String method) {

        System.out.println("\n--------------------------------------------------------------");
        System.out.print(">>    " + method + "\n\n");

        // A Metadata object contains multiple Directory objects
        for (Directory directory : metadata.getDirectories()) {

            // Each Directory stores values in Tag objects
            for (Tag tag : directory.getTags()) {
                System.out.println(tag);
            }

            // Each Directory may also contain error messages
//            for (String error : directory.getErrors()) {
//                System.err.println("ERROR: " + error);
//            }
        }
    }

    private static void print(Exception exception) {

        System.err.println("EXCEPTION: " + exception);
    }

}
