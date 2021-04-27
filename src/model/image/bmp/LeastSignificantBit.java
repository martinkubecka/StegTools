package model.image.bmp;

import model.explorer.FileChooser;
import model.general.Dictionary;


import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.awt.image.ColorModel;
import java.io.*;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public class LeastSignificantBit {

    private final static Logger LOGGER = Logger.getLogger(Logger.GLOBAL_LOGGER_NAME);

    public void insertLSBOld(File fileCarrier, File filesToHide) {

        final long HEADSIZE = 120;

        FileInputStream originalCarrier = null;
        FileInputStream fileToHide = null;
        InputStream sizeInputStream = null;
        InputStream extension = null;
        FileOutputStream outputCarrier = null;
        String fileToHideExt;

        try {

            originalCarrier = new FileInputStream(fileCarrier);
            fileToHide = new FileInputStream(filesToHide);
            // The file is created when FileOutputStream object is instantiated. If the file already exists, it is overridden.
            outputCarrier = new FileOutputStream("secret.bmp");

            // Create custom header
            // Save a file extension (3B) and size of inserting data (8B)
            // EXTENSION
            fileToHideExt = FileChooser.getExtension(filesToHide);
            extension = new ByteArrayInputStream(fileToHideExt.getBytes()); // TODO verify only 3 chars are returned

            // FILE SIZE
            long filesToHideSize = filesToHide.length();
            String fileSize = Long.toString(filesToHideSize);
            sizeInputStream = new ByteArrayInputStream(fileSize.getBytes());

            // Merge extension and "file size" data stream = customHeader
            SequenceInputStream customHeader = new SequenceInputStream(extension, sizeInputStream);

            // Merge custom header and files to hide data stream
            SequenceInputStream dataToHide = new SequenceInputStream(customHeader, fileToHide);

            // TESTING
            streamTesting(dataToHide);

            int pictureByte, dataByte;
            byte clearBit = (byte) 0xFE; // 254 = 11111110

            // Copy header to the output image
            outputCarrier.write(originalCarrier.readNBytes(54));
//            for (int i = 1; i <= HEADSIZE; i++) outputCarrier.write(originalCarrier.read()); //copy header

            // Do for all bytes in the data to hide
            while ((dataByte = dataToHide.read()) != -1) {

                // Insert 1 bit a time from the data to hide
                for (int bit = 7; bit >= 0; bit--) {

                    // Get picture-byte and clear the last bit
                    pictureByte = originalCarrier.read() & clearBit;
                    // Sift dataByte to the right to get the bit currently inserting
                    // Insert selected bit at the last position in the pictureByte
                    pictureByte = (pictureByte | ((dataByte >> bit) & 1));
                    // Write picture-byte in to the output image
                    outputCarrier.write(pictureByte);
                }
            }

//            // Copy rest of the carrier to the output image
            while ((pictureByte = originalCarrier.read()) != -1) {

                outputCarrier.write(pictureByte);
            }

            originalCarrier.close();
            dataToHide.close();
            outputCarrier.close();

        } catch (Exception exception) {

            LOGGER.log(Level.SEVERE, exception.toString(), exception);
        }
    }

    public void insertLSB(File fileCarrier, File filesToHide) {

        FileInputStream originalCarrier = null;
        FileInputStream fileToHide = null;
        InputStream sizeInputStream = null;
        InputStream extensionInputStream = null;
        FileOutputStream outputCarrier = null;
        String fileToHideExt;

        try {

            originalCarrier = new FileInputStream(fileCarrier);
            fileToHide = new FileInputStream(filesToHide);
            outputCarrier = new FileOutputStream("secret.bmp");

            int pictureByte, dataByte;
            byte clearBit = (byte) 0xFE; // 254 = 11111110

            // Copy header to the output image
            outputCarrier.write(originalCarrier.readNBytes(54));

            /* ------------------------------------------------------------------ */
            /* ---------------------------- Extension --------------------------- */
            fileToHideExt = FileChooser.getExtension(filesToHide);
            System.out.println(fileToHideExt);
            extensionInputStream = new ByteArrayInputStream(fileToHideExt.getBytes());

            // Do for all bytes in the data to hide
            while ((dataByte = extensionInputStream.read()) != -1) {

                // Insert 1 bit a time from the data to hide
                for (int bit = 7; bit >= 0; bit--) {

                    // Get picture-byte and clear the last bit
                    pictureByte = originalCarrier.read() & clearBit;
                    // Sift dataByte to the right to get the bit currently inserting
                    // Insert selected bit at the last position in the pictureByte
                    pictureByte = (pictureByte | ((dataByte >> bit) & 1));
                    // Write picture-byte in to the output image
                    outputCarrier.write(pictureByte);
                }
            }
            /* ------------------------------------------------------------------ */
            /* ------------------------------------------------------------------ */

            /* ------------------------------------------------------------------ */
            /* -------------------------- File Size ----------------------------- */
            long filesToHideSize = filesToHide.length();
            String fileSize = Long.toString(filesToHideSize);
            sizeInputStream = new ByteArrayInputStream(fileSize.getBytes());

            // Do for all bytes in the data to hide
            while ((dataByte = sizeInputStream.read()) != -1) {

                // Insert 1 bit a time from the data to hide
                for (int bit = 7; bit >= 0; bit--) {

                    // Get picture-byte and clear the last bit
                    pictureByte = originalCarrier.read() & clearBit;
                    // Sift dataByte to the right to get the bit currently inserting
                    // Insert selected bit at the last position in the pictureByte
                    pictureByte = (pictureByte | ((dataByte >> bit) & 1));
                    // Write picture-byte in to the output image
                    outputCarrier.write(pictureByte);
                }
            }
            /* ------------------------------------------------------------------ */
            /* ------------------------- Hide data ------------------------------ */
            // Do for all bytes in the data to hide
            while ((dataByte = fileToHide.read()) != -1) {

                // Insert 1 bit a time from the data to hide
                for (int bit = 7; bit >= 0; bit--) {

                    // Get picture-byte and clear the last bit
                    pictureByte = originalCarrier.read() & clearBit;
                    // Sift dataByte to the right to get the bit currently inserting
                    // Insert selected bit at the last position in the pictureByte
                    pictureByte = (pictureByte | ((dataByte >> bit) & 1));
                    // Write picture-byte in to the output image
                    outputCarrier.write(pictureByte);
                }
            }
            /* ------------------------------------------------------------------ */
            /* ------------------------------------------------------------------ */

            // Copy rest of the carrier to the output image
            while ((pictureByte = originalCarrier.read()) != -1) {

                outputCarrier.write(pictureByte);
            }

            originalCarrier.close();
            outputCarrier.close();
            extensionInputStream.close();
            sizeInputStream.close();

        } catch (Exception exception) {

            LOGGER.log(Level.SEVERE, exception.toString(), exception);
        }
    }

    /* -------------------------------------------------------------------------------------*/
    /* -------------------------------------- TESTING --------------------------------------*/
    private void streamTesting(SequenceInputStream dataToHide) throws IOException {

        // Read custom header
        // File extension (3B) + Size of inserting data (8B)
        byte[] typeBytes = new byte[3];
        dataToHide.read(typeBytes);
        String extension = getTypeString(typeBytes);

        byte[] sizeBytes = new byte[8];
        dataToHide.read(sizeBytes);
        String insertingSize = getTypeString(sizeBytes);

        System.out.println("Read Ext : " + extension);
        System.out.println("Read Size : " + insertingSize + " bytes");
    }

    private String getTypeString(byte[] typeBytes) {

        try {

            return new String(typeBytes, "UTF8");

        } catch (UnsupportedEncodingException uee) {

            return "";
        }
    }

    public byte[] extractLSB(File file, int size) {

        FileInputStream fileInputStream = null;
        byte[] lsbByteArray = new byte[size];
        int arrayOffset = 0;

        int dataByte, extractedLSB;
        byte clearingByte = (byte) 0x01; // 0000 0001

        try {
            fileInputStream = new FileInputStream(file);

            // Read byte by byte from the file input stream
            while ((dataByte = fileInputStream.read()) != -1) {

                // extract lsb and save it to the lsbByteArray
                /*
                //I've been trying something like this

                    extractedLSB = dataByte & clearingByte; // ? get lsb
                    lsbByteArray[arrayOffset] <<= 1;        // make space for a new bit
                    lsbByteArray[arrayOffset/8] |= extractLSB; // "append" the lsb bit
                    arrayOffset++;

                 */
            }

            fileInputStream.close();

        } catch (Exception exception) {

            exception.printStackTrace();
        }

        return lsbByteArray;
    }
    /* -------------------------------------------------------------------------------------*/
    /* -------------------------------------------------------------------------------------*/


    public int extractLSB(File fileCarrier) {

        FileInputStream image = null;
        FileOutputStream extractedFile = null;

        try {

            image = new FileInputStream(fileCarrier);

            // Read BMP header
            image.readNBytes(54);
            //for (int i = 1; i <= HEADSIZE; i++) image.read();

            // Read Custom header (11B)
            // File extension (3B) + Size of inserting data (8B)

            int dataByte = 0, extractedLSB;
            int counter = 0;
            byte[] extByteArray = new byte[3];

            // Extract extension
            // 3B = 24bits hidden inside 24B
            for (int i = 0; i < 24; i++) {

                dataByte = image.read();
                extractedLSB = dataByte & (byte) 1;
                extByteArray[counter / 8] |= (extractedLSB << (7 - (counter % 8)));
                counter++;
            }

            String extension = getTypeString(extByteArray);
            System.out.println("\nExtracted extension : " + extension);

            // Extract length
            dataByte = 0;
            extractedLSB = 0;
            counter = 0;
            byte[] lengthByteArray = new byte[8];

            // Extract extension
            // 8B = 64bits hidden inside 64B
            for (int i = 0; i < 64; i++) {

                dataByte = image.read();
                extractedLSB = dataByte & (byte) 1;
                lengthByteArray[counter / 8] |= (extractedLSB << (7 - (counter % 8)));
                counter++;
            }

            String length = getTypeString(lengthByteArray).replaceAll("[^\\d.]", "");
            int size = Integer.parseInt(length);
            System.out.println("\nExtracted size of the hidden file : " + size);

            // Extract file
            byte[] hiddenFile = new byte[size];
            // Based on the size do for all bytes
            int iterator = size * 8;
            dataByte = 0;
            extractedLSB = 0;
            counter = 0;
            // secret.txt = 6B = 48b hidden inside 48B
            for (int i = 0; i < iterator; i++) {

                //System.out.print(i + " ");
                dataByte = image.read();
                extractedLSB = dataByte & (byte) 1;
                hiddenFile[counter / 8] |= (extractedLSB << (7 - (counter % 8)));
                counter++;
            }

            // Create extracted file
            String fileName = "extracted." + extension;

            // first option
            try (FileOutputStream outputStream = new FileOutputStream(fileName)) {
                outputStream.write(hiddenFile);
            }

            // second option
//            BufferedOutputStream bs = null;
//
//            try {
//
//                FileOutputStream fs = new FileOutputStream(fileName);
//                bs = new BufferedOutputStream(fs);
//                bs.write(hiddenFile);
//                bs.close();
//
//            } catch (Exception e) {
//                e.printStackTrace();
//            }

            return 1;

        } catch (Exception exception) {

            LOGGER.log(Level.SEVERE, exception.toString(), exception);
            return -1;
        }
    }

    /**
     * Calculate if it is possible to hide chosen data inside a carrier.
     * <p>
     * dataToHide < availableSpace
     * <p>
     * availableSpace = (carrierFileSize - BMP_header - Custom_header) / 8
     * <p>
     *
     * @param file        image file used as a data carrier
     * @param filesToHide files chosen by user for LSB insertion
     * @return boolean value based on if it is possible to hide chosen data inside the provided carrier
     */
    public boolean isEnoughSpace(File file, List<File> filesToHide) {

        long fileByteSize = file.length();
        long filesToHideByteSize = 0;
        long availableByteSpace = 0;

        // Less than 60B would mean that we can not even write our own header
        if (fileByteSize > 60) {

            for (File toHide : filesToHide) {

                filesToHideByteSize += toHide.length();
            }

//            System.out.println("fileByteSize : " + fileByteSize);
//            System.out.println("filesToHideByteSize : " + filesToHideByteSize);

            availableByteSpace = (fileByteSize - 54 - 11) / 8;

//            System.out.println("availableByteSpace : " + availableByteSpace);

            return filesToHideByteSize < availableByteSpace;
        }

        return false;
    }

    /**
     * Validate if chosen carrier is supported.
     * Image files with less than 8bits per pixel are not supported.
     * <p>
     *
     * @param file image file used as a data carrier
     * @return boolean value based on if the provided file is supported
     */
    public boolean isCarrierSupported(File file) {

        try {

            BufferedImage carrier = ImageIO.read(file);
            ColorModel colorModel = carrier.getColorModel();

            int bitsPerPixel = colorModel.getPixelSize();

            if (bitsPerPixel >= 8) {

                return true;
            }

        } catch (Exception exception) {

            LOGGER.log(Level.SEVERE, exception.toString(), exception);
        }

        return false;
    }

    /**
     * Iterates over a list of files and shortens present text file with synonym dictionary.
     * <p>
     *
     * @param filesToHide list of files with one or more text files
     * @return list of files with shorten text files
     */
    public List<File> textFileShortening(List<File> filesToHide) {

        Dictionary dictionary = new Dictionary();

        for (File file : filesToHide) {

            if (FileChooser.getExtension(file).equals("txt")) {

                file = dictionary.applyMessageShortening(file, 2);
            }
        }

        return filesToHide;
    }

    /**
     * Determine if a text file is present in the list of chosen files.
     * <p>
     *
     * @param filesToHide files chosen by user for LSB insertion
     * @return boolean value based on if a text file is present
     */
    public boolean isTextFilePresent(List<File> filesToHide) {

        String textFile = "txt";
        String currentExt;
        int isPresent = 0;

        for (File file : filesToHide) {

            currentExt = FileChooser.getExtension(file);
            //System.out.println(currentExt);

            if (currentExt.equals(textFile)) isPresent++;
        }

        return (isPresent > 0);
    }

    /**
     * Determine if more than one file was chosen.
     * <p>
     *
     * @param filesToHide files chosen by user for LSB insertion
     * @return boolean value based on if more files were selected
     */
    public boolean areMoreFilesSelected(List<File> filesToHide) {

        return (filesToHide.size() != 1);
    }
}
