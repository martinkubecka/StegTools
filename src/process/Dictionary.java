package process;

import java.io.*;
import java.util.HashMap;
import java.util.Map;

public class Dictionary {

    public static void applyMessageShortening() {

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
            File secretMessage = FileChooser.pickSingleFromFileChooser();
            BufferedReader in = new BufferedReader(new FileReader(secretMessage));
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

            writeToOutput(message);
            System.out.println(">>  Successfully applied dictionary");

        } catch (IOException e) {
            e.printStackTrace();
        }


    }

    private static void writeToOutput(String content) {

        File file = new File("src/resources/secret_message_shorten.txt");

        if (file.exists()) {
            BufferedWriter bw = null;
            try {
                bw = new BufferedWriter(new FileWriter(file));
                bw.write(content);
                bw.flush();
                bw.close();
            } catch (IOException ex) {
                System.out.println("Error writing to file");
            }
        }

    }
}
