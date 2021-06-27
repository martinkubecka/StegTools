package model.general;

import net.lingala.zip4j.ZipFile;
import net.lingala.zip4j.model.ZipParameters;
import net.lingala.zip4j.model.enums.AesKeyStrength;
import net.lingala.zip4j.model.enums.EncryptionMethod;

import java.io.File;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Compression {

    private final static Logger LOGGER = Logger.getLogger(Logger.GLOBAL_LOGGER_NAME);
    private static int zipNumber = 0;

    /**
     * Compresses files to an encrypted zip file with provided password.
     * <p>
     *
     * @param filesToCompress list of files to be compressed
     * @param password        password used to encrypt zip file
     * @return boolean value based on if compression was successful
     */
    public boolean compressFilesToZip(List<File> filesToCompress, String password) {

        ZipParameters zipParameters = new ZipParameters();
        zipParameters.setEncryptFiles(true);
        zipParameters.setEncryptionMethod(EncryptionMethod.AES);
        zipParameters.setAesKeyStrength(AesKeyStrength.KEY_STRENGTH_256);

        try {

            String zipFileName = "resources_" + (++zipNumber) + ".zip";
            ZipFile zipFile = new ZipFile(zipFileName, password.toCharArray());
            zipFile.addFiles(filesToCompress, zipParameters);

            return true;

        } catch (Exception exception) {

            LOGGER.log(Level.SEVERE, exception.toString(), exception);

            return false;
        }
    }

    /**
     * Decompresses provided unencrypted zip file.
     * <p>
     *
     * @param file unencrypted zip file
     * @return boolean value based on if decompression was successful
     */
    public boolean deCompressUnencryptedZip(File file) {

        try {

            ZipFile zipFile = new ZipFile(file);
//            zipFile.extractAll("src/resources/extract");
            String path = System.getProperty("user.dir");
            zipFile.extractAll(path);

            return true;

        } catch (Exception exception) {

            LOGGER.log(Level.SEVERE, exception.toString(), exception);

            return false;
        }
    }

    /**
     * Decompresses provided encrypted zip file.
     * <p>
     *
     * @param file encrypted zip file
     * @param password zip file password
     * @return boolean value based on if decompression was successful
     */
    public boolean deCompressEncryptedZip(File file, String password) {

        try {

            ZipFile zipFile = new ZipFile(file, password.toCharArray());
//            zipFile.extractAll("src/resources/extract");
            String path = System.getProperty("user.dir");
            zipFile.extractAll(path);

            return true;

        } catch (Exception exception) {

            LOGGER.log(Level.SEVERE, exception.toString(), exception);

            return false;
        }
    }
}
