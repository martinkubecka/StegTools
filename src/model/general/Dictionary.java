package model.general;

import model.explorer.FileChooser;

import java.io.*;
import java.util.HashMap;
import java.util.Map;

public class Dictionary {

    public boolean applyMessageShortening() {

        Map<String, String> dictionary = new HashMap<String, String>();

        try {
            // TODO rework dictionary load to only once per software run
            BufferedReader in = new BufferedReader(new FileReader("src/resources/dictionary.txt"));
            String line = "";
            while ((line = in.readLine()) != null) {
                String[] inputSplit = line.split(",");
                dictionary.put(inputSplit[0], inputSplit[1]);
            }
            in.close();
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }

        try {
            FileChooser fileChooser = new FileChooser();
            File secretMessage = fileChooser.pickSingleFileChooser("txt");

            if (secretMessage != null) {

                BufferedReader in = new BufferedReader(new FileReader(secretMessage));
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

                writeToOutput(message);
                System.out.println(">>  Successfully applied dictionary");
                return true;
            }

        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }

        return false;
    }

    private void writeToOutput(String content) {

        File file = new File("src/resources/secret_message_shorten.txt");

        //Create the file
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
            } catch (IOException ex) {
                System.out.println(">>  Error writing to file");
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
