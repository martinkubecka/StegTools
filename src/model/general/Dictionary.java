package model.general;


import java.io.*;
import java.util.HashMap;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Dictionary {

    private static final Map<String, String> dictionary = new HashMap<String, String>();
    private final static Logger LOGGER = Logger.getLogger(Logger.GLOBAL_LOGGER_NAME);
    private static int newFileCounter = 0;

    /**
     * Dictionary constructor.
     * <p>
     * Load an English synonym dictionary into a HashMap.
     */
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

    /**
     * Apply message shortening on the provided text file based on the selected mode.
     * <p>
     * Mode = 1 : write to the new file
     * Mode = 2 : rewrite provided file
     *
     * @param file text file
     * @param mode write mode
     * @return shortened text file
     */
    public File applyMessageShortening(File file, int mode) {

        try {

            BufferedReader in = new BufferedReader(new FileReader(file));
            String line = "";
            StringBuilder input = new StringBuilder();

            while ((line = in.readLine()) != null) {

                input.append(line);
            }

            in.close();

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

        } catch (IOException ioException) {

            LOGGER.log(Level.SEVERE, ioException.toString(), ioException);
        }

        return null;
    }

    /**
     * Write a message to the text file.
     * <p>
     *
     * @param file    text file
     * @param message message to be written
     * @return text file with a message
     */
    private File writeToText(File file, String message) {

        try {

            FileWriter fw = new FileWriter(file, false);
            fw.write(message);
            fw.close();

        } catch (IOException ioException) {

            LOGGER.log(Level.SEVERE, ioException.toString(), ioException);
        }

        return file;
    }

    /**
     * Write a message to a new file.
     * <p>
     *
     * @param content message to be written
     */
    private void writeToOutput(String content) {

        String newFileName = "src/resources/secret_message_shorten_" + (++newFileCounter) + ".txt";
        File file = new File(newFileName);

        try {

            BufferedWriter bw = null;

            try {

                bw = new BufferedWriter(new FileWriter(file));
                bw.write(content);
                bw.flush();
                bw.close();

            } catch (IOException ioException) {

                LOGGER.log(Level.SEVERE, ioException.toString(), ioException);
            }

        } catch (Exception exception) {

            LOGGER.log(Level.SEVERE, exception.toString(), exception);
        }
    }
}
