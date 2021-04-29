package part1.lesson10.task01;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

public class MyClassLoader extends ClassLoader {


    @Override
    protected Class<?> findClass(String name) throws ClassNotFoundException {
        File classFile = new File("./src/part1/lesson10/task01/" + name + ".class");

        try (
                BufferedInputStream bis = new BufferedInputStream(
                        new FileInputStream(
                                classFile
                        )
                )
        ) {
            byte[] content = new byte[(int) classFile.length()];
            bis.read(content);

            final Class<?> simple = defineClass(name, content, 0, content.length);
            return simple;
        } catch (IOException e) {
            System.out.println("Что-то пошло не так");
            return null;
        }
    }
}
