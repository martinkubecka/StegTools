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

    public void insertLSB(File fileCarrier, File filesToHide) {

        FileInputStream originalCarrier = null;
        FileInputStream fileToHide = null;
        InputStream sizeInputStream = null;
        InputStream extension = null;
        FileOutputStream outputCarrier = null;
        String fileToHideExt;

        try {

            originalCarrier = new FileInputStream(fileCarrier);
            fileToHide = new FileInputStream(filesToHide);
//            fileToHide = new FileInputStream(hide);
            // The file is created when FileOutputStream object is instantiated. If the file already exists, it is overridden.
            outputCarrier = new FileOutputStream("secret.bmp");

            // Create custom header
            // Save a file extension (3B) and size of inserting data (8B)
            fileToHideExt = FileChooser.getExtension(filesToHide);
            extension = new ByteArrayInputStream(fileToHideExt.getBytes()); // TODO verify only 3 chars are returned

            long filesToHideSize = filesToHide.length();
            String fileSize = Long.toString(filesToHideSize);
            sizeInputStream = new ByteArrayInputStream(fileSize.getBytes());
            SequenceInputStream customHeader = new SequenceInputStream(extension, sizeInputStream);

            // Merge custom header and files to hide data stream
            SequenceInputStream dataToHide = new SequenceInputStream(customHeader, fileToHide);

            // TESTING
            streamTesting(dataToHide);

            int pictureByte, dataByte;
            byte clearBit = (byte) 0xFE; // 254 = 11111110

            // Copy header to the output image
            outputCarrier.write(originalCarrier.readNBytes(54));

            // Do for all bytes in the data to hide
            while ((dataByte = dataToHide.read()) != -1) {

                // 1 bit a time from the data to hide
                for (int bit = 7; bit >= 0; bit--) {

                    // Get picture-byte and clear the last bit
                    pictureByte = originalCarrier.read() & clearBit;
                    // Put MSG-bit in end of picture-byte
                    pictureByte = (pictureByte | ((dataByte >> bit) & 1));
                    // Write picture-byte in to the output image
                    outputCarrier.write(pictureByte);
                }
            }

            // Copy rest of the carrier to the output image
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
                    lsbByteArray[arrayOffset] |= extractLSB; // "append" the lsb bit
                    arrayOffset++;

                    OR

                    extractedLSB = dataByte & (byte) 1;
                    lsbByteArray[counter/8] |=  ( extractedLSB << (counter & 8));
                    counter++;
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
            image.read(image.readNBytes(54));

            // Read Custom header (11B)
            // File extension (3B = read 32B) + Size of inserting data (8B = 64B)
            int dataByte;

            for (int i = 0; i < 32; i++) {


            }


            // Based on the size do for all bytes


            // Create extracted file


        } catch (Exception exception) {

            LOGGER.log(Level.SEVERE, exception.toString(), exception);
            return -1;
        }

        return -1;
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
            System.out.println(currentExt);

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
