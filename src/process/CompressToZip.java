package process;

import net.lingala.zip4j.ZipFile;
import net.lingala.zip4j.model.ZipParameters;
import net.lingala.zip4j.model.enums.AesKeyStrength;
import net.lingala.zip4j.model.enums.EncryptionMethod;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

public class CompressToZip {

    public static void compressFilesToZip() {

        System.out.println("\n--------------------------------------------------------------");


        ZipParameters zipParameters = new ZipParameters();
        zipParameters.setEncryptFiles(true);
        zipParameters.setEncryptionMethod(EncryptionMethod.AES);
        // AES 256 is used by default
        // Override it use AES 128 (AES 192 is supported only for extracting)
        zipParameters.setAesKeyStrength(AesKeyStrength.KEY_STRENGTH_256);

        //Contents to be zipped.
        List<File> filesToAdd = Arrays.asList(
                new File("E:\\Programming\\Java\\StegTools\\src\\cat.jpg"),
                new File("E:\\Programming\\Java\\StegTools\\src\\dog.png"),
                new File("E:\\Programming\\Java\\StegTools\\src\\cat_and_dog.jpg")
        );

        Scanner sc = new Scanner(System.in);
        String pass = null;
        System.out.print("Enter password: ");
        pass = sc.nextLine();

        System.out.println(">>  Zipping the contents of input directory");
        try {
            ZipFile zipFile = new ZipFile("friends.zip", pass.toCharArray());
            zipFile.addFiles(filesToAdd, zipParameters);
        } catch (Exception e) {
            e.printStackTrace();
        }
        System.out.println(">>  Successfully zipped contents");

    }

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
