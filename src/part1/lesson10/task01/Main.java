package part1.lesson10.task01;

import javax.tools.JavaCompiler;
import javax.tools.ToolProvider;
import java.io.*;
import java.nio.charset.StandardCharsets;
import java.util.ResourceBundle;
import java.util.Scanner;

public class Main {

    final static private String FILE_PATH = "./src/part1/lesson10/task01/SomeClass.java";
    final static private String COMPILED_FILE_PATH = "./src/part1/lesson10/task01/SomeClass.class";

    public static void main(String[] args) {

        MyClassLoader classLoader = new MyClassLoader();

        String code = readClassAndReplace(getCodeFromConsole());

        writeToFile(code);

        compileJavaFile(FILE_PATH);

        try {
            final Class<?> someClass = Class.forName("part1.lesson10.task01.SomeClass", true, classLoader);
            SomeClass someObj = (SomeClass) someClass.newInstance();
            someObj.doWork();
        } catch (ClassNotFoundException | IllegalAccessException | InstantiationException e) {
            e.printStackTrace();
        }

    }

    private static String getCodeFromConsole() {
        Scanner keyboard = new Scanner(System.in);
        String line;
        StringBuilder sb = new StringBuilder();

        while (!(line = keyboard.nextLine()).isEmpty()) {
            sb.append(line);
        }

        return sb.toString();
    }

    private static String readClassAndReplace(String code) {
        StringBuilder fileAsString = new StringBuilder();

        try (BufferedReader br = new BufferedReader(new InputStreamReader(
                new FileInputStream(FILE_PATH), StandardCharsets.UTF_8))) {

            String line;

            while ((line = br.readLine()) != null) {
                fileAsString.append(line);
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
        return fileAsString
                .toString()
                .replaceFirst("(?![void doWork()\\s*]\\{)([\\w\\d\\s;:'\"\\-+()\\[\\]!?*^&$#@/\\\\`~.,]+)(?=})", code);
    }

    private static void writeToFile(String code) {
        try (FileOutputStream fos = new FileOutputStream(FILE_PATH);
             BufferedOutputStream bos = new BufferedOutputStream(fos)) {
            bos.write(code.getBytes());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    static private void compileJavaFile(String path) {
        JavaCompiler compiler = ToolProvider.getSystemJavaCompiler();
        compiler.run(null, null, null, path);
    }

}
