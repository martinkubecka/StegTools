import com.drew.imaging.jpeg.JpegMetadataReader;
import com.drew.imaging.jpeg.JpegProcessingException;
import com.drew.imaging.jpeg.JpegSegmentMetadataReader;
import com.drew.metadata.Directory;
import com.drew.metadata.Metadata;
import com.drew.metadata.Tag;
import com.drew.metadata.exif.ExifReader;
import com.drew.metadata.iptc.IptcReader;
import net.lingala.zip4j.ZipFile;
import net.lingala.zip4j.model.ZipParameters;
import net.lingala.zip4j.model.enums.AesKeyStrength;
import net.lingala.zip4j.model.enums.EncryptionMethod;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;
import java.util.stream.Collectors;

public class ConsoleTesting {

    public static void testingMenu() {

        System.out.println("\n--------------------------------------------------------------");
        System.out.println("----------------------| Console Testing |---------------------");
        System.out.println("--------------------------------------------------------------\n");

        System.out.println("Menu: ");
        System.out.println("0 : exit");
        System.out.println("1 : show Metadata");
        System.out.println("2 : compress files into a zip with password");
        System.out.println("3 : extract all files from a password protected zip");
        System.out.println("4 : apply dictionary on a provided message");
        System.out.println("5 : use zero-width characters to hide a secret message");

        Scanner sc = new Scanner(System.in);
        System.out.print("\nInput: ");
        int input = sc.nextInt();

        while (input != 0) {

            switch (input) {
                case 1: {
                    metadataTest();
                    break;
                }

                case 2: {
                    compressToZipTest();
                    break;
                }

                case 3: {
                    extractFromZipTest();
                    break;
                }

                case 4: {
                    applyDictionaryTest();
                    break;
                }

                case 5: {
                    zeroWidthTest();
                    break;
                }

                case 6: {
                    System.out.println("Secret ahhaha");
                    break;
                }
            }
            System.out.print("\nInput: ");
            input = sc.nextInt();
        }
    }


    private static void zeroWidthTest() {

        // Convert a string into binary data
        String s = "foo foo";
        System.out.println("Input: " + s);
        byte[] bytes = s.getBytes();
        System.out.println("Bytes : " + Arrays.toString(bytes));

        StringBuilder bin = new StringBuilder();

        for (byte b : bytes) {
            int val = b;
            for (int i = 0; i < 8; i++) {
                bin.append((val & 128) == 0 ? 0 : 1);
                val <<= 1;
            }
            bin.append(' ');
        }
        System.out.println("Binary: " + bin);

        // Convert binary data into a string
        String conversion = bin.toString();
        String raw = Arrays.stream(conversion.split(" "))
                .map(binary -> Integer.parseInt(binary, 2))
                .map(Character::toString)
                .collect(Collectors.joining()); // cut the space

        System.out.println("Conversion : " + raw);

        /* PROCESS */

        // Grab the public message string and break it up into characters


        // Find the half-way point in the string


        // Grab the private message


        // Convert it to binary data


        // And convert that into a string of zero-width characters


        // Finally, wrap it with a distinct boundary character


        // Inject the encoded private message into the approximate half-way point in the public string


        // Reassemble the public string

    }

    private static void applyDictionaryTest() {

        Map<String, String> dictionary = new HashMap<String, String>();

        try {
            BufferedReader in = new BufferedReader(new FileReader("src/resources/dictionary.txt"));
            String line = "";
            while ((line = in.readLine()) != null) {
                String[] inputSplit = line.split(",");
                dictionary.put(inputSplit[0], inputSplit[1]);
            }
            in.close();
            //System.out.println(dictionary.toString());
        } catch (IOException e) {
            e.printStackTrace();
        }

        try {
            BufferedReader in = new BufferedReader(new FileReader("src/resources/secret_message.txt"));
            String line = "";
            StringBuilder input = new StringBuilder();
            while ((line = in.readLine()) != null) {
                input.append(line);
            }
            in.close();

            System.out.println(">>  Successfully loaded secret message");
            System.out.println(input);
            String message = input.toString();
            for (Map.Entry<String, String> entry : dictionary.entrySet()) {

                message = message.replace(entry.getKey(), entry.getValue());

            }

            System.out.println(message);
            System.out.println(">>  Successfully applied dictionary");

        } catch (IOException e) {
            e.printStackTrace();
        }


    }

    private static void extractFromZipTest() {

        //TODO specify extraction folder location

        System.out.println("\n--------------------------------------------------------------");
        System.out.println(">>  Unzipping provided zip file");
        try {
            ZipFile zipFile = new ZipFile("E:\\Programming\\Java\\StegTools\\friends.zip", "heslo".toCharArray());
            zipFile.extractAll("/unziped");
        } catch (Exception e) {
            e.printStackTrace();
        }
        System.out.println(">>  Successfully unzipped contents of zip file");

    }

    private static void compressToZipTest() {


        ZipParameters zipParameters = new ZipParameters();
        zipParameters.setEncryptFiles(true);
        zipParameters.setEncryptionMethod(EncryptionMethod.AES);
        // AES 256 is used by default
        // Override it use AES 128 (AES 192 is supported only for extracting)
        zipParameters.setAesKeyStrength(AesKeyStrength.KEY_STRENGTH_256);

        //Contents to be zipped.
        List<File> filesToAdd = Arrays.asList(
                new File("E:\\Programming\\Java\\StegTools\\src\\resources\\cat.jpg"),
                new File("E:\\Programming\\Java\\StegTools\\src\\resources\\dog.png")
        );

        System.out.println("\n--------------------------------------------------------------");

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

    private static void metadataTest() {

        File file = new File("E:\\Programming\\Java\\StegTools\\src\\resources\\cat.jpg");

        try {
            // We are only interested in handling
            Iterable<JpegSegmentMetadataReader> readers = Arrays.asList(new ExifReader(), new IptcReader());

            Metadata metadata = JpegMetadataReader.readMetadata(file, readers);

            print(metadata, "Using JpegMetadataReader for Exif and IPTC only");
        } catch (JpegProcessingException | IOException e) {
            print(e);
        }
    }

    private static void print(Metadata metadata, String method) {

        System.out.println("\n--------------------------------------------------------------");
        System.out.print(">>    " + method + "\n\n");

        //
        // A Metadata object contains multiple Directory objects
        //
        for (Directory directory : metadata.getDirectories()) {

            //
            // Each Directory stores values in Tag objects
            //
            for (Tag tag : directory.getTags()) {
                System.out.println(tag);
            }

            //
            // Each Directory may also contain error messages
            //
            for (String error : directory.getErrors()) {
                System.err.println("ERROR: " + error);
            }
        }
    }

    private static void print(Exception exception) {
        System.err.println("EXCEPTION: " + exception);
    }


}
