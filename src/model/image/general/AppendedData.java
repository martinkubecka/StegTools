package model.image.general;

import model.explorer.FileChooser;
import model.explorer.FileFormats;

import java.io.*;
import java.util.logging.Level;
import java.util.logging.Logger;

public class AppendedData {

    private final static Logger LOGGER = Logger.getLogger(Logger.GLOBAL_LOGGER_NAME);

    /**
     * Extract appended data of PNG or BMP image file
     *
     * @param file Chosen file by user
     * @return Extracted appended data from chosen image file
     */
    public String extractAppendedMessage(File file) {

        String extractedMessage = null;
        InputStream inputStream;
        DataInputStream in = null;

        try {

            inputStream = new FileInputStream(file);
            in = new DataInputStream(inputStream);

        } catch (Exception exception) {

            LOGGER.log(Level.SEVERE, exception.toString(), exception);
        }

        if (FileFormats.BMP.getFormat().equals(FileChooser.getExtension(file))) {

            extractedMessage = hexToAscii(getBMPAppendedData(in));
//            extractedMessage = getBMPAppendedData(in);
        }
        if (FileFormats.PNG.getFormat().equals(FileChooser.getExtension(file))) {

            extractedMessage = hexToAscii(getPNGAppendedData(in));
//            extractedMessage = getPNGAppendedData(in);
        }

        return extractedMessage;
    }

    private String getBMPAppendedData(DataInputStream in) {


        return "";
    }

    /* ------------------------------------------------------------------------------------ */

    /**
     * Modified PNGDecoder from the book by O'Reilly Media, Inc.
     * Java 2D Graphics
     * https://resources.oreilly.com/examples/9781565924840/blob/master/examples/PNGDecoder.java
     *
     * @param in DataInputStream representing chosen file image
     * @return Extracted message represented as a String of hexadecimal values
     */
    private String getPNGAppendedData(DataInputStream in) {

        StringBuilder message = new StringBuilder("");
        String EOF = "IEND";

        try {

            // Reading the signature
            long signature = in.readLong();
            if (signature != 0x89504e470d0a1a0aL)
                throw new IOException("PNG signature not found!");

            byte[] data;
            String typeOfChunk;

            // Read until the end of file = "IEND" chunk
            boolean trucking = true;

            while (trucking) {
                try {
                    // Read the length
                    int length = in.readInt();

                    // Read the type of chunk
                    byte[] typeBytes = new byte[4];
                    in.readFully(typeBytes);
                    typeOfChunk = getTypeString(typeBytes);

                    // Read the data
                    data = new byte[length];
                    in.readFully(data);

                    // Read the CRC
                    int crc = in.readInt();

                    if (typeOfChunk.equals(EOF)) {
                        trucking = false;
                    }

                } catch (Exception e) {
                    e.printStackTrace();
                }
            }

            // Read appended byte by byte if present
            boolean areSecretDataPresent = true;

            while (areSecretDataPresent) {
                try {

                    byte b = in.readByte();
                    message.append(String.format("%02X", b));

                } catch (Exception e) {

                    areSecretDataPresent = false;
                }
            }

        } catch (Exception e) {

            e.printStackTrace();
        }

        return message.toString();
    }

    /**
     * UTF-8 encoding of provided chunk of data
     *
     * @param typeBytes recent read chunk of data
     * @return type of chunk as String
     */
    private String getTypeString(byte[] typeBytes) {

        try {

            return new String(typeBytes, "UTF8");

        } catch (UnsupportedEncodingException uee) {

            return "";
        }
    }

    /**
     * Convert Hex to ASCII
     * https://www.baeldung.com/java-convert-hex-to-ascii
     *
     * @param hexStr Extracted message represented as a String of hexadecimal values
     * @return Converted hexadecimal String to ASCII
     */
    private String hexToAscii(String hexStr) {

        StringBuilder output = new StringBuilder("");

        for (int i = 0; i < hexStr.length(); i += 2) {

            String str = hexStr.substring(i, i + 2);
            output.append((char) Integer.parseInt(str, 16));
        }

        return output.toString();
    }
}
