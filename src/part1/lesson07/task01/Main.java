package part1.lesson07.task01;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.util.Set;
import java.util.TreeSet;

public class Main {
    public static void main(String[] args) {
        Set<String> words = new TreeSet<>();
        try (BufferedReader br = new BufferedReader(new InputStreamReader(
                new FileInputStream("D:\\Proj\\STC-21-04_Java Middle_Online\\HelloWorld\\src\\words.txt"), StandardCharsets.UTF_8))) {

            String line;

            while ((line = br.readLine()) != null) {

                words.add(line);

            }

        } catch (IOException e) {
            e.printStackTrace();
        }

        String fileName = "D:\\Proj\\STC-21-04_Java Middle_Online\\HelloWorld\\src\\output.txt";

        FileOutputStream fos = null;
        try {
            fos = new FileOutputStream(fileName);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

        if (fos != null) {
            try (OutputStreamWriter osw = new OutputStreamWriter(fos,
                    StandardCharsets.UTF_8)) {

                words.forEach(word -> {
                    try {
                        osw.write(word+"\n");
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                });


            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
