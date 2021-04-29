package model.image.bmp;

import model.explorer.FileChooser;
import model.general.Dictionary;
import model.image.general.AppendedData;


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
            /* -------------------------- File Size ----------------------------- */
            long filesToHideSize = filesToHide.length();
            String fileSize = Long.toString(filesToHideSize);

            int length = String.valueOf(filesToHideSize).length();
            int numDigitsToFill = 8 - length;

            String prepend = "";

            for (int i = 0; i < numDigitsToFill; i++) {

                prepend += "0";
            }

            String insertSize = prepend + fileSize;

            sizeInputStream = new ByteArrayInputStream(insertSize.getBytes());

            while ((dataByte = sizeInputStream.read()) != -1) {

                for (int bit = 7; bit >= 0; bit--) {

                    pictureByte = originalCarrier.read() & clearBit;
                    pictureByte = (pictureByte | ((dataByte >> bit) & 1));
                    outputCarrier.write(pictureByte);
                }
            }
            /* ------------------------------------------------------------------ */
            /* ------------------------- Hide data ------------------------------ */
            while ((dataByte = fileToHide.read()) != -1) {

                for (int bit = 7; bit >= 0; bit--) {

                    pictureByte = originalCarrier.read() & clearBit;
                    pictureByte = (pictureByte | ((dataByte >> bit) & 1));
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

    public int extractLSB(File fileCarrier) {

        FileInputStream image = null;
        FileOutputStream extractedFile = null;

        try {

            // TODO return 0 if nothing was inserted

            image = new FileInputStream(fileCarrier);

            // Read BMP header
            image.readNBytes(54);

            // Custom header (11B)

            // Extract extension
            // 3B = 24bits hidden inside 24B
            byte[] extByteArray = new byte[3];
            int dataByte = 0, extractedLSB = 0, counter = 0;

            for (int i = 0; i < 24; i++) {

                dataByte = image.read();
                extractedLSB = dataByte & (byte) 1;
                extByteArray[counter / 8] |= (extractedLSB << (7 - (counter % 8)));
                counter++;
            }

            String extension = AppendedData.getTypeString(extByteArray);

            // Extract length
            // 8B = 64bits hidden inside 64B
            byte[] lengthByteArray = new byte[8];
            dataByte = 0; extractedLSB = 0; counter = 0;

            for (int i = 0; i < 64; i++) {

                dataByte = image.read();
                extractedLSB = dataByte & (byte) 1;
                lengthByteArray[counter / 8] |= (extractedLSB << (7 - (counter % 8)));
                counter++;
            }

            String length = AppendedData.getTypeString(lengthByteArray);
            int size = Integer.parseInt(length);

            // Extract file
            byte[] hiddenFile = new byte[size];
            dataByte = 0; extractedLSB = 0; counter = 0;
            int iterator = size * 8;

            for (int i = 0; i < iterator; i++) {

                dataByte = image.read();
                extractedLSB = dataByte & (byte) 1;
                hiddenFile[counter / 8] |= (extractedLSB << (7 - (counter % 8)));
                counter++;
            }

            // Create extracted file
            FileOutputStream outputStream;

            try {

                String fileName = "extracted." + extension;
                outputStream = new FileOutputStream(fileName);
                outputStream.write(hiddenFile);

            } catch (Exception e) {

                LOGGER.log(Level.SEVERE, e.toString(), e);
                return -1;
            }

            outputStream.close();

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

        // Less than 66B would mean that we can not even write our own header and a single character
        if (fileByteSize > 66) {

            for (File toHide : filesToHide) {

                filesToHideByteSize += toHide.length();
            }

            availableByteSpace = (fileByteSize - 54 - 11) / 8;

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
