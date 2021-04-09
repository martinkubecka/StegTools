package model.general;

import model.explorer.FileChooser;

import java.io.*;
import java.util.HashMap;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Dictionary {

    private static final Map<String, String> dictionary = new HashMap<String, String>();
    private final static Logger LOGGER = Logger.getLogger(Logger.GLOBAL_LOGGER_NAME);
    private static int newFileCounter = 0;

    public Dictionary() {

        try {

            BufferedReader in = new BufferedReader(new FileReader("src/resources/dictionary.txt"));
            String line = "";

            while ((line = in.readLine()) != null) {

                String[] inputSplit = line.split(",");
                dictionary.put(inputSplit[0], inputSplit[1]);
            }

            in.close();

        } catch (IOException ioException) {

            LOGGER.log(Level.SEVERE, ioException.toString(), ioException);
        }
    }

    // mode 1 = to the new file | 2 = rewrite provided file
    public File applyMessageShortening(File file, int mode) {

        try {

            BufferedReader in = new BufferedReader(new FileReader(file));
            String line = "";
            StringBuilder input = new StringBuilder();

            while ((line = in.readLine()) != null) {

                input.append(line);
            }

            in.close();

            System.out.println(">>  Successfully loaded secret message");
            //System.out.println(input);
            String message = input.toString();

            for (Map.Entry<String, String> entry : dictionary.entrySet()) {

                message = message.replace(entry.getKey(), entry.getValue());
            }

            if (mode == 1) {

                writeToOutput(message);
                return file;

            } else if (mode == 2) {

                return writeToText(file, message);
            }
            System.out.println(">>  Successfully applied dictionary");


        } catch (IOException ioException) {

            LOGGER.log(Level.SEVERE, ioException.toString(), ioException);
        }

        return null;
    }

    private File writeToText(File file, String message) {

        try {

            FileWriter f2 = new FileWriter(file, false);
            f2.write(message);
            f2.close();

        } catch (IOException ioException) {

            LOGGER.log(Level.SEVERE, ioException.toString(), ioException);
        }

        return file;
    }

    private void writeToOutput(String content) {

        //Create the file
        String newFileName = "src/resources/secret_message_shorten_" + (++newFileCounter) + ".txt";
        File file = new File(newFileName);

        try {

            if (file.createNewFile()) {

                System.out.println(">>  New Text File is created!");
            } else {

                System.out.println(">>  File already exists.");
            }

            //Write content
            BufferedWriter bw = null;

            try {

                bw = new BufferedWriter(new FileWriter(file));
                bw.write(content);
                bw.flush();
                bw.close();

            } catch (IOException ioException) {

                System.out.println(">>  Error writing to file");
                LOGGER.log(Level.SEVERE, ioException.toString(), ioException);
            }
        } catch (IOException ioException) {

            LOGGER.log(Level.SEVERE, ioException.toString(), ioException);
        }
    }
}
