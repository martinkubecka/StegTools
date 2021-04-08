package model.image.png;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.util.logging.Level;
import java.util.logging.Logger;

public class ImageHeader {

    private final static Logger LOGGER = Logger.getLogger(Logger.GLOBAL_LOGGER_NAME);

    /**
     * Check PNG image file signature correctness.
     * <p>
     * The first eight bytes of a PNG file always contain the following values:
     * Dec: 137 80 78 71 13 10 26 10
     * Hex: 89 50 4e 47 0d 0a 1a 0a -> bytes: -119, 80, 78, 71, 13, 10, 26, 10
     * <p>
     *
     * @param file chosen for signature check
     * @return boolean value based on if the image signature is correct or not
     */
    public boolean headerChecker(File file) {

        byte[] data = readContentIntoByteArray(file);
        byte[] correctHeader = new byte[]{-119, 80, 78, 71, 13, 10, 26, 10};

        for (int i = 0; i < correctHeader.length; i++) {

            if (correctHeader[i] != data[i]) {

                return false;
            }
        }

        return true;
    }

    /**
     * Repair PNG signature.
     * <p>
     *
     * @param file chosen for header repair
     * @return repair result represented as a boolean value
     */
    public boolean headerRepair(File file) {

        byte[] data = readContentIntoByteArray(file);
        byte[] correctHeader = new byte[]{-119, 80, 78, 71, 13, 10, 26, 10};

//        for (int i = 0; i < correctHeader.length; i++) {
//
//            data[i] = correctHeader[i];
//        }

        System.arraycopy(correctHeader, 0, data, 0, correctHeader.length);

        try {

            saveImage(data);
            return true;

        } catch (Exception e) {

            return false;
        }
    }

    /**
     * Save byte array as an image.
     * <p>
     *
     * @param bytes array representing rebuilt PNG image
     */
    private void saveImage(final byte[] bytes) {

        try {

            final File file = new File("src/resources/rebuilt.png");
            final FileOutputStream fileOut = new FileOutputStream(file);
            fileOut.write(bytes);
            fileOut.flush();
            fileOut.close();

        } catch (Exception exception) {

            LOGGER.log(Level.SEVERE, exception.toString(), exception);
        }
    }

    /**
     * Convert a file into an array of bytes.
     * <p>
     *
     * @param file  chosen to be read and saved as an array of bytes
     * @return file as an array of bytes
     */
    private byte[] readContentIntoByteArray(File file) {

        FileInputStream fileInputStream;
        byte[] bFile = new byte[(int) file.length()];

        try {
            // Convert file into array of bytes
            fileInputStream = new FileInputStream(file);
            fileInputStream.read(bFile);
            fileInputStream.close();

            // hex form:    printf("%02x ", bFile[i])
            // dec form:    printf("%d ", bFile[i]) or print(bFile[i] + " ")
//            for (byte b : bFile) {
//                System.out.printf("%02x", b);
//            }

        } catch (Exception exception) {

            LOGGER.log(Level.SEVERE, exception.toString(), exception);
        }

        return bFile;
    }
}
