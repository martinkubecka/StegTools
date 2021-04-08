package model.general;

import net.lingala.zip4j.ZipFile;
import net.lingala.zip4j.exception.ZipException;
import net.lingala.zip4j.model.ZipParameters;
import net.lingala.zip4j.model.enums.AesKeyStrength;
import net.lingala.zip4j.model.enums.EncryptionMethod;

import java.io.File;
import java.util.List;

public class Compression {

    public boolean compressFilesToZip(List<File> filesToCompress, String password) {

        System.out.println("\n--------------------------------------------------------------");

        ZipParameters zipParameters = new ZipParameters();
        zipParameters.setEncryptFiles(true);
        zipParameters.setEncryptionMethod(EncryptionMethod.AES);
        // AES 256 is used by default
        // Override it use AES 128 (AES 192 is supported only for extracting)
        zipParameters.setAesKeyStrength(AesKeyStrength.KEY_STRENGTH_256);

        System.out.println(">>  Zipping the contents of input directory");
        try {
            ZipFile zipFile = new ZipFile("resource.zip", password.toCharArray());
            // TODO set default location for zip after compression
            zipFile.addFiles(filesToCompress, zipParameters);
            System.out.println(">>  Successfully zipped contents");
            return true;
        } catch (Exception e) {

            e.printStackTrace();
            return false;
        }
    }

    public boolean deCompressUnencryptedZip(File file) {

        System.out.println(">>  Unzipping provided unencrypted zip file");
        try {
            ZipFile zipFile = new ZipFile(file);
            zipFile.extractAll("src/resources/extract");
            System.out.println(">>  Successfully unzipped contents of zip file");
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    public boolean deCompressEncryptedZip(File file, String password) {

        System.out.println(">>  Unzipping provided encrypted zip file");
        try {
            ZipFile zipFile = new ZipFile(file, password.toCharArray());
            zipFile.extractAll("src/resources/extract");
            System.out.println(">>  Successfully unzipped contents of zip file");
            return true;
        } catch (ZipException zipException) {
            return false;
        }
    }

    /*
    public static void deCompressFilesToZip() {

        System.out.println(">>  Unzipping provided zip file");
        try {
            ZipFile zipFile = new ZipFile("E:\\Programming\\Java\\StegTools\\friends.zip", "heslo".toCharArray());
            zipFile.extractAll("/unziped");
        } catch (Exception e) {
            e.printStackTrace();
        }
        System.out.println(">>  Successfully unzipped contents of zip file");

        // Check if a zip file is password protected
        // new ZipFile("encrypted_zip_file.zip").isEncrypted();

        // unprotected zip
        // new ZipFile("filename.zip").extractAll("/destination_directory");

        // password protected zip
        // new ZipFile("filename.zip", "password".toCharArray()).extractAll("/destination_directory");

    }*/

    /*public static void compressFilesToZip() {

        System.out.println("\n--------------------------------------------------------------");

        //Path of output zip file
        String outputZip = "E:\\Programming\\Java\\StegTools\\src\\friends.zip";

        //Contents to be zipped.
        List<File> files = Arrays.asList(
                new File("E:\\Programming\\Java\\StegTools\\src\\cat.jpg"),
                new File("E:\\Programming\\Java\\StegTools\\src\\dog.png"),
                new File("E:\\Programming\\Java\\StegTools\\src\\cat_and_dog.jpg")
        );
        System.out.println(">>  Zipping the contents of input directory");
        try{
            createZipFile(outputZip, files);
        } catch (IOException e) {
            e.printStackTrace();
        }

        System.out.println(">>  Successfully zipped contents");

    }*/

    /*private static void createZipFile(String outputZip, List<File> listOfFiles)
            throws IOException {

        //Create zip file
        ZipOutputStream zipOutputStream = new ZipOutputStream(
                new FileOutputStream(outputZip));

        for (File file : listOfFiles) {

            String fileName = file.getName();

            //Read files
            FileInputStream readFile = new FileInputStream(file);

            //Create zip entry
            ZipEntry zipEntry = new ZipEntry(fileName);
            //Set zip entry
            zipOutputStream.putNextEntry(zipEntry);

            int readChar = -1;
            while ((readChar = readFile.read()) != -1) {
                zipOutputStream.write(readChar);
            }

            readFile.close();
            zipOutputStream.closeEntry();
            System.out.println(">>  Zipping input file: " + fileName);
        }
        zipOutputStream.close();
    }*/

}
