package model.image.general;

import model.explorer.FileChooser;
import model.explorer.FileFormats;

import java.io.*;
import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.util.logging.Level;
import java.util.logging.Logger;

public class AppendedData {

    private final static Logger LOGGER = Logger.getLogger(Logger.GLOBAL_LOGGER_NAME);

    /**
     * Extract appended data of a PNG or a BMP image file.
     * <p>
     * @param file  chosen file by user
     * @return extracted appended data from chosen image file
     */
    public String extractAppendedData(File file) {

        String extractedData = null;
        InputStream inputStream;
        DataInputStream dataInputStream = null;

        try {

            inputStream = new FileInputStream(file);
            dataInputStream = new DataInputStream(inputStream);

        } catch (Exception exception) {

            LOGGER.log(Level.SEVERE, exception.toString(), exception);
        }

        if (FileFormats.BMP.getFormat().equals(FileChooser.getExtension(file))) {

//            extractedData = hexToAscii(getBMPAppendedData(dataInputStream));
            extractedData = getBMPAppendedData(dataInputStream);
        }
        if (FileFormats.PNG.getFormat().equals(FileChooser.getExtension(file))) {

//            extractedData = hexToAscii(getPNGAppendedData(dataInputStream));
            extractedData = getPNGAppendedData(dataInputStream);
        }

        return extractedData;
    }

    /**
     * Read an extra data, if it exists, append to a BMP image from a DataInputStream.
     * <p>
     * @param dataInputStream  DataInputStream representing chosen file image
     * @return extracted data represented as a String of hexadecimal values
     */
    private String getBMPAppendedData(DataInputStream dataInputStream) {

        StringBuilder extractedData = new StringBuilder();

        try {
            // Read the fileType : always 0x4d42 = "BM"
            dataInputStream.readShort();
            // Read the file size
            byte[] fileSizeBuffer = new byte[4];
            dataInputStream.readFully(fileSizeBuffer);

            // Get integer type of the file size
            // Shifts bits dataInputStream an integer to the left by the specified number of positions
            // Then combines the pieces into a single number
            // (Two options)
            // final int fileSize = dataInputStream.readUnsignedByte() | dataInputStream.readUnsignedByte() << 8 | dataInputStream.readUnsignedByte() << 16 | dataInputStream.readUnsignedByte() << 24;
            final int fileSize = ByteBuffer.wrap(fileSizeBuffer, 0, 4).order(ByteOrder.LITTLE_ENDIAN).getInt();
            int bytesToSkip = fileSize - 6; // 2 byte for the signature, 4 bytes for the filesize.

            // Skip the rest of bytes
            while (bytesToSkip > 0) {

                bytesToSkip -= dataInputStream.skipBytes(bytesToSkip);
            }

            // Read appended byte by byte if present
            while (true) {
                try {

                    byte b = dataInputStream.readByte();
                    extractedData.append(String.format("%02X", b));

                } catch (Exception e) {

                    break;
                }
            }

        } catch (Exception exception) {

            LOGGER.log(Level.SEVERE, exception.toString(), exception);
        }

        return extractedData.toString();
    }

    /* ------------------------------------------------------------------------------------ */

    /**
     * Read an extra data, if it exists, append to a PNG image from a DataInputStream.
     * Modified PNGDecoder from the book by O'Reilly Media, Inc.
     * Java 2D Graphics
     * https://resources.oreilly.com/examples/9781565924840/blob/master/examples/PNGDecoder.java
     * <p>
     * @param dataInputStream  DataInputStream representing chosen file image
     * @return extracted data represented as a String of hexadecimal values
     */
    private String getPNGAppendedData(DataInputStream dataInputStream) {

        StringBuilder extractedData = new StringBuilder();
        String EOF = "IEND";

        try {

            // Reading the signature
            long signature = dataInputStream.readLong();
//            if (signature != 0x89504e470d0a1a0aL)
//                throw new IOException("PNG signature not found!");

            byte[] data;
            String typeOfChunk;

            // Read until the end of file = "IEND" chunk
            boolean trucking = true;

            while (trucking) {
                try {
                    // Read the length
                    int length = dataInputStream.readInt();

                    // Read the type of chunk
                    byte[] typeBytes = new byte[4];
                    dataInputStream.readFully(typeBytes);
                    typeOfChunk = getTypeString(typeBytes);

                    // Read the data
                    data = new byte[length];
                    dataInputStream.readFully(data);

                    // Read the CRC
                    int crc = dataInputStream.readInt();

                    if (typeOfChunk.equals(EOF)) {
                        trucking = false;
                    }

                } catch (Exception exception) {

                    LOGGER.log(Level.SEVERE, exception.toString(), exception);
                }
            }

            // Read appended byte by byte if present
            while (true) {
                try {

                    byte b = dataInputStream.readByte();
                    extractedData.append(String.format("%02X", b));

                } catch (Exception exception) {

                    break;
                }
            }
        } catch (Exception exception) {

            LOGGER.log(Level.SEVERE, exception.toString(), exception);
        }

        return extractedData.toString();
    }

    /**
     * UTF-8 encoding of provided chunk of data.
     * <p>
     * @param typeBytes  recent read chunk of data
     * @return type of chunk as a String
     */
    private String getTypeString(byte[] typeBytes) {

        try {

            return new String(typeBytes, "UTF8");

        } catch (UnsupportedEncodingException uee) {

            return "";
        }
    }

    /**
     * Convert Hex to ASCII.
     * https://www.baeldung.com/java-convert-hex-to-ascii
     * <p>
     * @param hexStr  extracted message represented as a String of hexadecimal values
     * @return converted hexadecimal String to ASCII
     */
    public String hexToAscii(String hexStr) {

        StringBuilder output = new StringBuilder("");

        for (int i = 0; i < hexStr.length(); i += 2) {

            String str = hexStr.substring(i, i + 2);
            output.append((char) Integer.parseInt(str, 16));
        }

        return output.toString();
    }
}
